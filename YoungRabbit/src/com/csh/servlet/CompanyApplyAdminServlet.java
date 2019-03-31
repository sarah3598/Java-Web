package com.csh.servlet;

/*********************
 * Author shaohui-chen
 ******************** */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.csh.dao.BaseDao;
import com.csh.service.JsonService;
import com.csh.util.Pagination;

public class CompanyApplyAdminServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		LoginControlPublicServlet.LoginSessionValidate(request, response);
		String op = request.getParameter("OP");
		if (op == null) {
			getApplyList(request, response);
			return;
		}

		// by lizhenyu
		switch (op) {
		
		// basic rule====================================
		case "1":// approve_apply
			try {
				approve_Apply(request, response);
			} catch (NumberFormatException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;				
		case "2"://refuse_apply
			refuse_Apply(request, response);
			break;
		}

	}

	// by xiaoapn 拒绝申请
		public void refuse_Apply(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{

			String USER_ID = request.getParameter("USER_ID");

			String sql = "UPDATE USERS SET FLAG=0 WHERE USER_ID=?";
			Object[] param = new Object[1];
			param[0] = USER_ID;
			int result = BaseDao.exeSql(sql, param);
			String normal_json = "";
			String msg ="";
			if (result == 1) {
				// 删除完成后，取出数据，刷新列表
				JSONArray jsonarr = new JSONArray();
				jsonarr = getApplyListData(request);			
				msg = "拒绝申请成功！";
				normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
			} 
			else 
				normal_json = JsonService.getErrorMsgNormalJson( "拒绝申请失败！");		
			JsonService.ResponseJson(request, response, normal_json);
		}

	
	// by lizhenyu 批准申请,并返回新列表
	public void approve_Apply(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, JSONException{
		String TRAIN_ID = request.getParameter("TRAIN_ID");
		String USER_ID = request.getParameter("USER_ID");
		String FLAG = request.getParameter("FLAG");
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取		
   				
		String normal_json = "";
		String msg ="";
		
		//批准申请入职培训
		if(Integer.parseInt(FLAG)==1){
			String sql_count="SELECT COUNT FROM TRAIN_TEAM WHERE TRAIN_ID="+TRAIN_ID;
			String sql_sum="SELECT COUNT(1) FROM TRAIN_MEMBER WHERE TRAIN_ID="+TRAIN_ID;
			
			JSONArray rs_count=BaseDao.getRsJSONArray(sql_count,null);
			//查询团队可容纳人数
			int count=Integer.parseInt(rs_count.getJSONObject(0).getString("COUNT"));
			//查询团队已有人数
			int sum=BaseDao.getRowCount(sql_sum,null);
			//判断该培训团队已有人数是否超过上线
			if(sum<count)
			{
				String sql = "UPDATE USERS SET FLAG=0 WHERE USER_ID=?";
				Object[] param = new Object[1];
				param[0] = USER_ID;
				
				int result=BaseDao.exeSql(sql, param);
				if(result==1)
				{
					String sql1 = "INSERT INTO TRAIN_MEMBER(TRAIN_ID,MEMBER_ID,ROLE,FLAG,CREATE_ID) VALUES(?,?,?,?,?)";
					Object[] param1 = new Object[5];
					param1[0] = TRAIN_ID;
					param1[1] = USER_ID;
					param1[2] = 0;		
					param1[3] = 0;
					param1[4] = CREATE_ID;
					
					int result1 = BaseDao.exeSql(sql1, param1);
					if (result1 == 1) 
					{
						// 批准完成后，取出数据，刷新列表
						JSONArray jsonarr = new JSONArray();
						jsonarr = getApplyListData(request);			
						msg = "批准申请成功！";	
						normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
					}
					else 
						normal_json = JsonService.getErrorMsgNormalJson( "批准申请失败！");	
				}
				else 
					normal_json = JsonService.getErrorMsgNormalJson( "数据异常！");
			}
			else
			{
				msg = "该培训团队人数已满！";
				// 取出数据，刷新列表
				JSONArray jsonarr = new JSONArray();
				jsonarr = getApplyListData(request);							
				normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
				JsonService.ResponseJson(request, response, normal_json);
				return;
			}	
		}
		//批准申请成为独家代理
		if(Integer.parseInt(FLAG)==2){
			
				String sql = "UPDATE USERS SET FLAG=0,GROLE=2 WHERE USER_ID=?";
				Object[] param = new Object[1];
				param[0] = USER_ID;
				
				int result=BaseDao.exeSql(sql, param);
				if(result==1)
				{
					//添加评级记录
					String sql1 = "INSERT INTO GRADE_RECORD(USER_ID,ROLE,NEW_ROLE,CREATE_ID) VALUES(?,?,?,?)";
					Object[] param1 = new Object[4];
					param1[0] = USER_ID;
					param1[1] = 1;
					param1[2] = 2;		
					param1[3] = CREATE_ID;
					
					int result1 = BaseDao.exeSql(sql1, param1);
					if (result1 == 1) 
					{
						// 批准完成后，取出数据，刷新列表
						JSONArray jsonarr = new JSONArray();
						jsonarr = getApplyListData(request);			
						msg = "批准申请成功！";	
						normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
					}
					else 
						normal_json = JsonService.getErrorMsgNormalJson( "批准申请失败！");	
				}
				else 
					normal_json = JsonService.getErrorMsgNormalJson( "数据异常！");
			
		}
		//批准申请成为组长的培训
		if(Integer.parseInt(FLAG)==3){
			
			String sql = "UPDATE USERS SET FLAG=4 WHERE USER_ID=?";
			Object[] param = new Object[1];
			param[0] = USER_ID;
			
			int result=BaseDao.exeSql(sql, param);
			if(result==1)
			{
				String sql_train_id="SELECT TRAIN_ID FROM TRAIN_TEAM WHERE TEAM_ID ="+USER_ID+"AND TYPE = 4 AND STATUS = 1";				
				
				JSONArray rs_train_id=BaseDao.getRsJSONArray(sql_train_id,null);
				//String train_id=rs_train_id.getJSONObject(0).getString("TRAIN_ID");										
				
				if(rs_train_id.length()>0)
				{
					msg = "该独家代理正在培训中！";
					// 取出数据，刷新列表
					JSONArray jsonarr = new JSONArray();
					jsonarr = getApplyListData(request);							
					normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
					JsonService.ResponseJson(request, response, normal_json);
					return;
				}
				else
				{				
					String sql1 = "INSERT INTO TRAIN_TEAM(TEAM_ID,TYPE,STATUS,COUNT,CREATE_ID) VALUES(?,?,?,?,?)";
					Object[] param1 = new Object[5];
					param1[0] = USER_ID;
					param1[1] = 4;
					param1[2] = 1;
					param1[3] = 1;
					param1[4] = CREATE_ID;
					
					int result1 = BaseDao.exeSql(sql1, param1);
					if (result1 == 1) 
					{
						// 批准完成后，取出数据，刷新列表
						JSONArray jsonarr = new JSONArray();
						jsonarr = getApplyListData(request);			
						msg = "批准申请成功！";	
						normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
					}
					else 
						normal_json = JsonService.getErrorMsgNormalJson( "批准申请失败！");	
				}			
			}
			else 
				normal_json = JsonService.getErrorMsgNormalJson( "数据异常！");
	}
		JsonService.ResponseJson(request, response, normal_json);
	}
	
	

	// add by lizhenyu
	public void getApplyList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getApplyListData(request);
		String sel_json = getTrainTeamListData();
		request.setAttribute("apply_list", jsonarr1.toString());
		request.setAttribute("train_team_list", sel_json);
		request.getRequestDispatcher("/zh_CN/company/yb_db_apply_admin.jsp").forward(request, response);				
}

	// add by lizhenyu:查询TRAIN_TEAM表中的入职培训团队信息
	public String getTrainTeamListData() throws NumberFormatException {
		String sql = "SELECT A.TRAIN_ID,B.USERNAME,B.ROLE FROM TRAIN_TEAM A LEFT JOIN USERS B ON A.TEAM_ID=B.USER_ID WHERE A.TYPE=1 AND A.STATUS=1";
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql, null);
		String rowSql = "SELECT COUNT(1) FROM TRAIN_TEAM A LEFT JOIN USERS B ON A.TEAM_ID=B.USER_ID WHERE A.TYPE=1 AND A.STATUS=1 ";
		int rows = BaseDao.getRowCount(rowSql, null);
		String select_json = "[";
		// each column
		try {
			for (int i = 0; i < rows; i++) {
				String train_id = jsonarr.getJSONObject(i).getString("TRAIN_ID");
				String user_name = jsonarr.getJSONObject(i).getString("USERNAME");
				int role = jsonarr.getJSONObject(i).getInt("ROLE");
				String role_name = "";
				if (role == 4)
					role_name = "组长";
				else
					role_name = "独家销售代理";
				select_json += "[\"" + train_id + "\",\"" + role_name + " " + user_name + "\"]";
				if (i < rows - 1) {
					select_json += ",";
				}
			}
			select_json += "]";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return select_json;
	}
		
	// add by lizhenyu:查询USERS表中的信息:申请信息
	private JSONArray getApplyListData(HttpServletRequest request) {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT USER_ID,USERNAME,ROLE,FLAG FROM USERS WHERE FLAG>=1 AND FLAG <4 ORDER BY FLAG ASC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM USERS WHERE FLAG>=1 AND FLAG <4 ";	

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}		
}
