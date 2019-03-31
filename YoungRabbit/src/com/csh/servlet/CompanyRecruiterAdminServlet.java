package com.csh.servlet;

/*********************
 * Author shaohui-chen
 ******************** */
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.service.CompanyRecruiterService;
import com.csh.service.JsonService;
import com.csh.util.PageModel;
import com.csh.util.Pagination;

public class CompanyRecruiterAdminServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String op = request.getParameter("OP");

		if (op == null) {
			getRecruiterList(request, response);
			return;
		}

		switch (op) {
		// manage Recruiter ====================================
		case "1":// add_Recruiter
			try {
				addRecruiter(request, response);
			} catch (NumberFormatException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "2":// update_Recruiter
			updateRecruiter(request, response);
			break;
		case "3":// delete_Recruiter
			deleteRecruiter(request, response);
			break;		
		case "4":// query_Recruiter
			try {
				employRecruiter(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	// by lizhenyu 添加用户,并返回新列表
	public void addRecruiter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, JSONException {
		String FIRST_NAME = request.getParameter("FIRST_NAME");
		String LAST_NAME = request.getParameter("LAST_NAME");
		String EMAIL = request.getParameter("EMAIL");
		String USERNAME = LAST_NAME + FIRST_NAME;
		String PWD = request.getParameter("PWD");
		String TRAIN_ID = request.getParameter("TRAIN_ID");
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取
		//String PARENT_ID = request.getParameter("PARENT_ID");
		//String ROLE = request.getParameter("ROLE");

		String sql_count="SELECT COUNT FROM TRAIN_TEAM WHERE TRAIN_ID="+TRAIN_ID;
		String sql_sum="SELECT COUNT(1) FROM TRAIN_MEMBER WHERE TRAIN_ID="+TRAIN_ID;
		
		JSONArray rs_count=BaseDao.getRsJSONArray(sql_count,null);
		//查询团队可容纳人数
		int count=Integer.parseInt(rs_count.getJSONObject(0).getString("COUNT"));
		//查询团队已有人数
		int sum=BaseDao.getRowCount(sql_sum,null);
		
		String msg ="";
		String normal_json ="";
		
		//判断该培训团队已有人数是否超过上线
		if(sum<count)
		{
			// 先判断该用户是否存在，若存在不允许添加
			String select_sql = "SELECT COUNT(1) FROM USERS WHERE USERNAME='" + USERNAME + "' OR EMAIL='" + EMAIL + "'";
			int rows = BaseDao.getRowCount(select_sql, null);
			if (rows > 0) 
			{
				msg = "该用户名或邮箱已存在，请勿重复添加！";
				// 取出数据，刷新列表
				JSONArray jsonarr = new JSONArray();
				jsonarr = getRecruiterListData(request);							
				normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
				JsonService.ResponseJson(request, response, normal_json);
				return;
			}
			else
			{
				// 该用户不存在，允许添加
				String sql = "INSERT INTO USERS(FIRST_NAME,LAST_NAME,EMAIL,PWD,ROLE,GROLE,USERNAME) VALUES(?,?,?,?,?,?,?)";
				Object[] param = new Object[7];
				param[0] = FIRST_NAME;
				param[1] = LAST_NAME;
				param[2] = EMAIL;
				param[3] = PWD;
				param[4] = 0;
				param[5] = 0;
				param[6] = USERNAME;

				int result = BaseDao.exeSql(sql, param);
				if (result == 1) 
				{
					sql = "SELECT USER_ID FROM USERS WHERE USERNAME='" + USERNAME + "' OR EMAIL='" + EMAIL + "'";
					long USER_ID = CompanyRecruiterService.getUserID(sql, null);
					if (USER_ID > 0) 
					{
						sql = "INSERT INTO TRAIN_MEMBER(TRAIN_ID,MEMBER_ID,ROLE,FLAG,CREATE_ID) VALUES(?,?,?,?,?)";
						Object[] param1 = new Object[5];
						param1[0] = TRAIN_ID;
						param1[1] = USER_ID;
						param1[2] = 0;
						param1[3] = 0;
						param1[4] = CREATE_ID;
						
						int result1 = BaseDao.exeSql(sql, param1);
						if (result1 == 1) 
						{
							// 添加完成后，取出数据，刷新列表
							JSONArray jsonarr = new JSONArray();
							jsonarr = getRecruiterListData(request);			
							msg = "添加成功！";	
							normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
						}
						else 
							normal_json = JsonService.getErrorMsgNormalJson( "添加失败！");	
					}
					else 
						normal_json = JsonService.getErrorMsgNormalJson( "添加失败！");	
				}
				else 
					normal_json = JsonService.getErrorMsgNormalJson( "添加失败！");					
			}
			JsonService.ResponseJson(request, response, normal_json);
		}
		else
		{
			msg = "该培训团队人数已满！";
			// 取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getRecruiterListData(request);							
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
			JsonService.ResponseJson(request, response, normal_json);
			return;
		}

}		
	
	// by lizhenyu 修改用户信息
	public void updateRecruiter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String FIRST_NAME = request.getParameter("FIRST_NAME");
		String LAST_NAME = request.getParameter("LAST_NAME");
		String EMAIL = request.getParameter("EMAIL");
		String USERNAME = LAST_NAME + FIRST_NAME;
		String PWD = request.getParameter("PWD");
		//String PARENT_ID = request.getParameter("PARENT_ID");
		//String ROLE = request.getParameter("ROLE");
		String USER_ID = request.getParameter("USER_ID");		
		//String TEAM_ID = request.getParameter("TEAM_ID");
		// String CREATE_ID = "10011";// 应该从session中获取当前用户名

		String msg ="";
		String normal_json ="";				
		
		String sql = "UPDATE USERS SET FIRST_NAME=?,LAST_NAME=?,EMAIL=?,PWD=?,USERNAME=? WHERE USER_ID=?";
		Object[] param = new Object[6];
		param[0] = FIRST_NAME;
		param[1] = LAST_NAME;
		param[2] = EMAIL;
		param[3] = PWD;
		param[4] = USERNAME;
		param[5] = USER_ID;
		
		int result = BaseDao.exeSql(sql, param);
		if (result == 1) {
			
			// 修改完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getRecruiterListData(request);			
			msg = "修改成功！";	
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "修改失败！");		
		
	JsonService.ResponseJson(request, response, normal_json);
}

	// by lizhenyu 聘入员工，返回新表
	private void employRecruiter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, JSONException {
		// TODO Auto-generated method stub
		String USER_ID = request.getParameter("USER_ID");
		String TRAIN_ID = request.getParameter("TRAIN_ID");
		String PARENT_ID = request.getParameter("PARENT_ID");
		String PARENT_ROLE = request.getParameter("PARENT_ROLE");
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");
		
		Calendar cal = Calendar.getInstance();
		int YEAR = cal.get(Calendar.YEAR);
		int MONTH = cal.get(Calendar.MONTH)+1;
		int DATE = cal.get(Calendar.DATE);
		int HOUR = cal.get(Calendar.HOUR_OF_DAY); 
		int MINUTE = cal.get(Calendar.MINUTE); 
		int SECOND = cal.get(Calendar.SECOND); 
		String current_month ="";
		String current_year=Integer.toString(YEAR);
		String current_date=Integer.toString(DATE);
		String current_hour=Integer.toString(HOUR);
		String current_minute=Integer.toString(MINUTE);
		String current_second=Integer.toString(SECOND);
		
		if(DATE< 10)
			current_date="0"+ Integer.toString(DATE);
		else
			current_date= Integer.toString(DATE);	
		
		if(MONTH< 10)
			current_month="0"+ Integer.toString(MONTH);
		else
			current_month= Integer.toString(MONTH);	
		
		if(HOUR< 10)
			current_hour="0"+ Integer.toString(HOUR);
		else
			current_hour= Integer.toString(HOUR);
		
		if(MINUTE< 10)
			current_minute="0"+ Integer.toString(MINUTE);
		else
			current_minute= Integer.toString(MINUTE);	
		
		if(SECOND< 10)
			current_second="0"+ Integer.toString(SECOND);
		else
			current_second= Integer.toString(SECOND);	
		
		String END_DATE=current_year+current_month+current_date;
		String END_TIME=current_hour+current_minute+current_second;
		
		String normal_json = "";
		String msg ="";
		//修改用户信息
		String sql = "UPDATE USERS SET ROLE=1,FLAG=0,GROLE=1 WHERE USER_ID=?";
		Object[] param = new Object[1];
		param[0] = USER_ID;

		int result = BaseDao.exeSql(sql, param);
		if(result==1)
		{
			//添加评级记录
			String sql1 = "INSERT INTO GRADE_RECORD(USER_ID,ROLE,NEW_ROLE,CREATE_ID) VALUES(?,?,?,?)";
			Object[] param1 = new Object[4];
			param1[0] = USER_ID;
			param1[1] = 0;
			param1[2] = 1;
			param1[3] = CREATE_ID;
			int result1 = BaseDao.exeSql(sql1, param1);
			if(result1==1)
			{
				//修改培训成员信息
				String sql2 ="UPDATE TRAIN_MEMBER SET FLAG=1,END_DATE=?,END_TIME=?,CREATE_ID=? WHERE TRAIN_ID=? AND MEMBER_ID = ? AND FLAG=0";
				Object[] param2 = new Object[5];
				param2[0] = END_DATE;
				param2[1] = END_TIME;
				param2[2] = CREATE_ID;
				param2[3] = TRAIN_ID;
				param2[4] = USER_ID;
				int result2 = BaseDao.exeSql(sql2, param2);
				if(result2==1)
				{
					if(Integer.parseInt(PARENT_ROLE) == 2)
					{
						String sql4 ="SELECT TEAM_ID FROM SALES_MEMBER WHERE MEMBER_ID ="+PARENT_ID+" AND ROLE ="+PARENT_ROLE;
						JSONArray rs_sql4=BaseDao.getRsJSONArray(sql4,null);
						int TEAM_ID=Integer.parseInt(rs_sql4.getJSONObject(0).getString("TEAM_ID"));
						
					    if(TEAM_ID>0)
					    {
					    	//在销售团队成员中添加一条记录:在独家代理（组长）所在的上级团队中插入该成员
					    	String sql5 ="INSERT INTO SALES_MEMBER(TEAM_ID,MEMBER_ID,ROLE,PARENT_ID) VALUES(?,?,?,?)";
					    	Object[] param3 = new Object[4];
							param3[0] = Integer.toString(TEAM_ID);
							param3[1] = USER_ID;
							param3[2] = 1;
							param3[3] = PARENT_ID;
							
							int result3 = BaseDao.exeSql(sql5, param3);
							if(result3==1)
							{
								// 聘入完成后，取出数据，刷新列表
								JSONArray jsonarr = new JSONArray();
								jsonarr = getRecruiterListData(request);			
								msg = "聘入成功！";
								normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
							}
							else 
								normal_json = JsonService.getErrorMsgNormalJson( "聘入失败！");									
					    }
					    else 
							normal_json = JsonService.getErrorMsgNormalJson( "数据异常！");		
					}
					else if(Integer.parseInt(PARENT_ROLE) == 4)
					{
						//直接在自己的组长团队中插入该成员
						String sql6 ="INSERT INTO SALES_MEMBER(TEAM_ID,MEMBER_ID,ROLE,PARENT_ID) VALUES(?,?,?,?)";
				    	Object[] param4 = new Object[4];
						param4[0] = PARENT_ID;
						param4[1] = USER_ID;
						param4[2] = 1;
						param4[3] = PARENT_ID;
						
						int result4 = BaseDao.exeSql(sql6, param4);
						if(result4==1)
						{
							// 聘入完成后，取出数据，刷新列表
							JSONArray jsonarr = new JSONArray();
							jsonarr = getRecruiterListData(request);			
							msg = "聘入成功！";
							normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
						}
						else 
							normal_json = JsonService.getErrorMsgNormalJson( "聘入失败！");									
					}
				}
				  else 
						normal_json = JsonService.getErrorMsgNormalJson( "数据异常！");	
			}
			  else 
					normal_json = JsonService.getErrorMsgNormalJson( "数据异常！");				
		}
		  else 
				normal_json = JsonService.getErrorMsgNormalJson( "数据异常！");	
	JsonService.ResponseJson(request, response, normal_json);	
}
		/*String rs_json = "";
		if (result == 1) {
			sql = "SELECT ROLE FROM USERS WHERE USER_ID=" + parentId;
			long ROLE = CompanyRecruiterService.getROLE(sql, null);
			if (ROLE == 1 || ROLE == 2) {
				sql = "SELECT TEAM_ID FROM SALES_MEMBER WHERE MEMBER_ID=" + parentId;
				long teamId = CompanyRecruiterService.getTEAMID(sql, null);
				if (teamId > 0) {
					sql = "INSERT INTO SALES_MEMBER(TEAM_ID,MEMBER_ID,ROLE,PARENT_ID) VALUES(?,?,?,?)";
					Object[] param1 = new Object[4];
					param1[0] = teamId;
					param1[1] = userId;
					param1[2] = 1;
					param1[3] = parentId;

					int result1 = BaseDao.exeSql(sql, param1);
					if (result1 == 1) {
						int uid = CompanyRecruiterService.deleteSalesMember(userId, parentId);
						if (uid == -1)
							rs_json = JsonService.getOperateResultNormalJson("false", null, "删除失败！");
						// 取出数据，刷新列表
						int pageSize = 10;
						int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
						int startRow = (currentPage - 1) * pageSize;
						int endRow = startRow + pageSize;// include the end row

						String listSql = "select TEAM_ID,MEMBER_ID from SALES_MEMBER where ROLE=0 ";
						String rs = CompanyRecruiterService.getRecruiterListRs(startRow, endRow, listSql, null);
						if (rs != null) {
							String msg = "聘入成功！";
							rs_json = JsonService.getNormalJson(rs, msg);
						} else {
							rs_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");
						}
					} else
						rs_json = JsonService.getOperateResultNormalJson("false", null, "聘入失败！");

				} else
					rs_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");

			} else if (ROLE == 4 || ROLE == 8 || ROLE == 16) {
				sql = "INSERT INTO SALES_MEMBER(TEAM_ID,MEMBER_ID,ROLE,PARENT_ID) VALUES(?,?,?,?)";
				// TEAM_ID = PARENT_ID;MEMBER_ID = USER_ID; ROLE=1;PARENT_ID =
				// PARENT_ID;
				Object[] param1 = new Object[4];
				param1[0] = parentId;
				param1[1] = userId;
				param1[2] = 1;
				param1[3] = parentId;

				int result1 = BaseDao.exeSql(sql, param1);
				if (result1 == 1) {
					int uid = CompanyRecruiterService.deleteSalesMember(userId, parentId);
					if (uid == -1)
						rs_json = JsonService.getOperateResultNormalJson("false", null, "删除失败！");
					// 取出数据，刷新列表
					int pageSize = 10;
					int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
					int startRow = (currentPage - 1) * pageSize;
					int endRow = startRow + pageSize;// include the end row

					String listSql = "select TEAM_ID,MEMBER_ID from SALES_MEMBER where ROLE=0 ";
					String rs = CompanyRecruiterService.getRecruiterListRs(startRow, endRow, listSql, null);
					if (rs != null) {
						String msg = "聘入成功！";
						rs_json = JsonService.getNormalJson(rs, msg);
					} else {
						rs_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");
					}
				} else
					rs_json = JsonService.getOperateResultNormalJson("false", null, "聘入失败！");

			}
		} else
			rs_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");

		JsonService.ResponseJson(request, response, rs_json);
	}*/

	// by lizhenyu 删除用户，返回新列表
	public void deleteRecruiter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String USER_ID = request.getParameter("USER_ID");
		String TRAIN_ID = request.getParameter("TRAIN_ID");
		
		//删除培训团队成员表TRAIN_MEMBER的成员信息
		String sql = "DELETE FROM TRAIN_MEMBER WHERE TRAIN_ID=? AND MEMBER_ID=?";
		Object[] param = new Object[2];
		param[0] = TRAIN_ID;
		param[1] = USER_ID;
		
		int result = BaseDao.exeSql(sql, param);
		String normal_json = "";
		String msg ="";
		if (result == 1) {
			//删除用户表USERS的成员信息
			String sql1 = "DELETE FROM USERS WHERE USER_ID=?";
			Object[] param1 = new Object[1];
			param1[0] = USER_ID;
			int result1 = BaseDao.exeSql(sql1, param1);
			if (result1 == 1) {
				// 删除完成后，取出数据，刷新列表
				JSONArray jsonarr = new JSONArray();
				jsonarr = getRecruiterListData(request);			
				msg = "删除成功！";
				normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
			}
			else 
				normal_json = JsonService.getErrorMsgNormalJson( "删除失败！");		
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "删除失败！");		
		JsonService.ResponseJson(request, response, normal_json);

	}
	
	// add by lizhenyu
	private void getRecruiterList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getRecruiterListData(request);
		request.setAttribute("recruiter_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_recruiter_admin.jsp").forward(request, response);				
		
	}

	private JSONArray getRecruiterListData(HttpServletRequest request) {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String TRAIN_ID = request.getParameter("TRAIN_ID");
		String PARENT_ID = request.getParameter("PARENT_ID");
		String PARENT_ROLE = request.getParameter("PARENT_ROLE");
		String USERNAME = request.getParameter("USERNAME");
		
		
		//查询TRAIN_MEMBER表中被培训的人员
		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.USER_ID,A.EMAIL,A.PWD,A.FIRST_NAME,A.LAST_NAME,A.USERNAME,A.ROLE,B.FLAG,B.HAPPEN_DATE FROM USERS A LEFT JOIN TRAIN_MEMBER B ON A.USER_ID=B.MEMBER_ID WHERE TRAIN_ID="+TRAIN_ID+")C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1)  FROM USERS  A LEFT JOIN TRAIN_MEMBER B ON A.USER_ID=B.MEMBER_ID WHERE TRAIN_ID="+TRAIN_ID;	

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		request.setAttribute("train_id", TRAIN_ID);
		request.setAttribute("parent_id", PARENT_ID);
		request.setAttribute("parent_role", PARENT_ROLE);
		request.setAttribute("USERNAME", USERNAME);
		return jsonarr;
	}

	/*private void deleteRecruiter_1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		int uid = CompanyRecruiterService.deleteRecruiterAdmin_1(userId);
		String rs_json = "";
		if (uid == -1) {
			// error message
			rs_json = JsonService.getOperateResultNormalJson("false", null, "删除失败！");
		} else {
			int pageSize = 10;
			int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
			int startRow = (currentPage - 1) * pageSize;
			int endRow = startRow + pageSize;// include the end row

			String listSql = "select TEAM_ID,MEMBER_ID from SALES_MEMBER where ROLE=0 ";
			String rs = CompanyRecruiterService.getRecruiterListRs(startRow, endRow, listSql, null);
			String msg = "";
			if (rs != "") {
				msg = "删除成功！";
				rs_json = JsonService.getNormalJson(rs, msg);
			} else {
				rs_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");
				// rows = 0;
			}
		}
		JsonService.ResponseJson(request, response, rs_json);

	}*/

	
		/*int result = BaseDao.exeSql(sql, param);
		String rs_json = "";
		if (result == 1) {
			sql = "SELECT TEAM_ID FROM SALES_MEMBER WHERE MEMBER_ID=" + USER_ID;
			long teamId = CompanyRecruiterService.getTEAMID(sql, null);
			if (teamId >= 0) {
				sql = "UPDATE SALES_MEMBER SET TEAM_ID=?,ROLE=?,PARENT_ID=?  WHERE MEMBER_ID=?";
				Object[] param1 = new Object[4];
				param1[0] = PARENT_ID;
				param1[1] = ROLE;
				param1[2] = PARENT_ID;
				param1[3] = USER_ID;

				int result1 = BaseDao.exeSql(sql, param1);
				if (result1 == 1) {
					// 取出数据，刷新列表
					int pageSize = 10;
					int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
					int startRow = (currentPage - 1) * pageSize;
					int endRow = startRow + pageSize;// include the end row

					String listSql = "select TEAM_ID,MEMBER_ID from SALES_MEMBER where ROLE=0 ";
					String rs = CompanyRecruiterService.getRecruiterListRs(startRow, endRow, listSql, null);
					if (rs != null) {
						String msg = "更新成功！";
						rs_json = JsonService.getNormalJson(rs, msg);
					} else {
						rs_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");
					}
				} else {
					rs_json = JsonService.getOperateResultNormalJson("false", null, "更新失败！");
				}

			} else {
				rs_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");
			}

		} else {
			rs_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");
		}
*/
	
	
	

	/*public void getRecruiterList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize;// include the end row

		String listSql = "select TEAM_ID,MEMBER_ID from SALES_MEMBER where ROLE=0 ";
		String rowSql = "select count(1) from  SALES_MEMBER where ROLE=0 ";

		String rs_json = CompanyRecruiterService.getRecruiterListRs(startRow, endRow, listSql, null);
		int rows = CompanyRecruiterService.getRecruiterListRows(rowSql, null);

		String listSql1 = "select a.USER_ID,a.USERNAME from USERS a left join SALES_TEAM  b on b.TEAM_ID=a.USER_ID where b.TYPE=1 ";
		String rowSql1 = "select count(1) from USERS a left join SALES_TEAM  b on b.TEAM_ID=a.USER_ID where b.TYPE=1";
		Connection con = BaseDao.getConn();
		ResultSet rs1 = BaseDao.getRs(con, listSql1, null);
		int rows_team = BaseDao.getRowCount(rowSql1, null);
		;

		String[][] param = new String[rows_team][2];
		String s = "\"[";
		int i = 0;
		try {
			while (rs1.next()) {
				param[i][0] = String.valueOf(rs1.getLong("USER_ID"));
				param[i][1] = rs1.getString("USERNAME");

				String s1 = "['";
				s1 = s1 + param[i][0] + "','" + param[i][1] + "']";
				if (i != rows_team - 1)
					s1 += ",";
				s += s1;
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		s += "]\"";
		request.setAttribute("s1", s);

		if (rs_json != null) {
			request.setAttribute("recruiter_list", rs_json);
			request.setAttribute("rows", rows);
		} else {
			rs_json = null;
			rows = 0;
		}
		request.getRequestDispatcher("/zh_CN/company/yb_db_recruiter_admin.jsp").forward(request, response);
	}*/

	public void listFormalUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		String sql = "select * from USERS t where 1=1 ";
		String rowSql = "select count(1) from USERS t where 1=1 ";
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		PageModel<Users> page = CompanyRecruiterService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.getSession().setAttribute("page", page);
		request.getSession().setAttribute("webusers", oper);
		try {
			request.getRequestDispatcher("/jsp/webuserlist.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// ��δ����û�?
	public void listNoAuditWebUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users user = (Users) request.getSession().getAttribute("webusers");// ��¼����Ա
		if (user == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		String sql = "select * from USERS t where 1=1 and t.bak=0";
		String rowSql = "select count(1) from USERS t where 1=1 and t.bak=0 ";
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		PageModel<Users> page = CompanyRecruiterService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		System.out.println("page.getTotalRecord-->" + page.getTotalRecord());
		request.setAttribute("page", page);
		request.getSession().setAttribute("webusers", user);
		System.out.println("login-dopost--enter listWebUsers-end->");
		try {
			request.getRequestDispatcher("/jsp/webuseraudit.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// ����û�?
	public void auditWebUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("enter servlet audituser->");

		String user_Id = request.getParameter("userId");
		System.out.println("-auditWebUsers-userId->" + user_Id);

		int uid = CompanyRecruiterService.auditWebUsers(user_Id);
		if (uid == -1) {
			String message = "ʧ��";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		} else {

			listNoAuditWebUsers(request, response);
		}

	}

	public void listSearchWebUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reportLevelSe = request.getParameter("reportLevelSe");
		String reportStatusSe = request.getParameter("reportDistrictSe");
		String reportEmailSe = request.getParameter("reportDaySe");
		Users user = (Users) request.getSession().getAttribute("webusers");
		if (user == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		String sqlwhere = "";
		if (!"0".equals(reportLevelSe))
			sqlwhere = "and STATUS = " + reportLevelSe;
		if (!"9".equals(reportStatusSe))
			sqlwhere = sqlwhere + " and BAK = " + reportStatusSe;
		if (!"".equals(reportEmailSe))
			sqlwhere = sqlwhere + " and EMAIL like \'" + reportEmailSe + "\'";

		String sql = "select * from USERS t where 1=1 ";
		String rowSql = "select count(1) from USERS t where 1=1 ";
		sql = sql + sqlwhere;
		rowSql = rowSql + sqlwhere;

		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		PageModel<Users> page = CompanyRecruiterService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.setAttribute("page", page);
		request.getSession().setAttribute("webusers", user);
		try {
			// ��Ҫ�����쳣�Ĵ���
			request.getRequestDispatcher("/jsp/webuserlist.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// �༭�޸��û�
	public void editaddUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		String email = request.getParameter("userEmail");
		String firstname = request.getParameter("userFirstname");
		String lastname = request.getParameter("userLastname");
		String username = firstname + lastname;
		String address = request.getParameter("userAddress");
		String city = request.getParameter("userCity");
		String zipcode = request.getParameter("userZipcode");
		String country = request.getParameter("userCountry");
		String phone = request.getParameter("userPhone");
		String password = request.getParameter("userPassword");
		String status = request.getParameter("Status");
		String contract = request.getParameter("userContract");

		String sql = "INSERT INTO USERS(Email,FIRST_NAME,LAST_NAME,USER_NAME,ADDRESS,CITY,ZIP_CODE,COUNTRY,PHONE,PWD,STATUS,CONTRACT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] param = new Object[12];
		param[0] = email;
		param[1] = firstname;
		param[2] = lastname;
		param[3] = username;
		param[4] = address;
		param[5] = city;
		param[6] = zipcode;
		param[7] = country;
		param[8] = phone;
		param[9] = password;
		param[10] = status;
		param[11] = contract;

		int result = BaseDao.exeSql(sql, param);
		String message = "";
		if (result == 1) {
			message = "editAddUser Success!";
		} else {
			message = "Failure to editAddUser !";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);

	}

}
