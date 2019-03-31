package com.csh.servlet;

/*********************
 * Author shaohui-chen
 ******************** */
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.csh.dao.BaseDao;
import com.csh.service.JsonService;
import com.csh.util.Pagination;

public class CompanyStaffLeaveManageServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if(!LoginControlPublicServlet.LoginSessionValidate(request, response,1))return;
		String op = request.getParameter("OP");
		if (op == null) {
			getLeaveList(request, response);
			return;
		}

		// by lizhenyu
		switch (op) {
		
		// staff leave==================================
		case "1":// add_staff leave
			addLeave(request, response);
			break;		
		case "2":// update_staff leave
			updateLeave(request, response);
			break;
		case "3":// back_staff leave
			backLeave(request, response);
			break;
		case "4":// delete_staff leave
			deleteLeave(request, response);
			break;
		}

	}

	private void backLeave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String LEAVE_ID = request.getParameter("LEAVE_ID");
		String normal_json = "";
		String msg ="";	
		
		String sql = "UPDATE LEAVE SET STATUS=1 WHERE LEAVE_ID=?";
		Object[] param = new Object[1];
		param[0] = LEAVE_ID;
		int result = BaseDao.exeSql(sql, param);
		
		if (result == 1) {
			// 销假完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getLeaveListData(request);			
			msg = "销假成功！";
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "销假失败！");		
		JsonService.ResponseJson(request, response, normal_json);
		
	}

	// by lizhenyu 添加请假,并返回新列表
	public void addLeave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		String USERNAME = request.getParameter("USERNAME");
		String EMAIL = request.getParameter("EMAIL");
		String TYPE = request.getParameter("TYPE");		
		String STATUS = request.getParameter("STATUS");	
		String START_DATE = request.getParameter("START_DATE");		
		String END_DATE = request.getParameter("END_DATE");	
		String DAYS = request.getParameter("DAYS");					
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取		
   
		String normal_json = "";
		String msg ="";
	    String USER_ID="";
		String sql = "SELECT USER_ID FROM USERS WHERE USERNAME=? AND EMAIL=? ";
		Object[] param = new Object[2];
		param[0] = USERNAME;
		param[1] = EMAIL;
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql, param);
		
		if (jsonarr != null && jsonarr.length() > 0){
				try {
					USER_ID = jsonarr.getJSONObject(0).getString("USER_ID");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		else{
				msg="该员工不存在，请进行核对！";
				normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
				JsonService.ResponseJson(request, response, normal_json);
				return;
		}
		
		// 添加请假
		String sql1 = "INSERT INTO LEAVE(USER_ID,TYPE,STATUS,START_DATE,END_DATE,DAYS,CREATE_ID) VALUES(?,?,?,?,?,?,?)";
		Object[] param1 = new Object[7];
		param1[0] = USER_ID;		
		param1[1] = TYPE;
		param1[2] = STATUS;
		param1[3] = START_DATE;
		param1[4] = END_DATE;	
		param1[5] = DAYS;
		param1[6] = CREATE_ID;

		int result = BaseDao.exeSql(sql1, param1);
		if (result == 1) {
			
			// 添加请假完成后，取出数据，刷新列表
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getLeaveListData(request);			
			msg = "请假添加成功！";	
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "请假添加失败！");		
		JsonService.ResponseJson(request, response, normal_json);
	}
	
	// by lizhenyu 删除请假
	public void deleteLeave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		String LEAVE_ID = request.getParameter("LEAVE_ID");

		String sql = "DELETE FROM LEAVE WHERE LEAVE_ID=?";
		Object[] param = new Object[1];
		param[0] = LEAVE_ID;
		int result = BaseDao.exeSql(sql, param);
		String normal_json = "";
		String msg ="";
		if (result == 1) {
			// 删除完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getLeaveListData(request);			
			msg = "删除成功！";
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "删除失败！");		
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by lizhenyu 修改请假
	public void updateLeave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{	
		String LEAVE_ID = request.getParameter("LEAVE_ID");	
		String TYPE = request.getParameter("TYPE");		
		String STATUS = request.getParameter("STATUS");	
		String START_DATE = request.getParameter("START_DATE");		
		String END_DATE = request.getParameter("END_DATE");	
		String DAYS = request.getParameter("DAYS");							
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取		
   
		String normal_json = "";
		String msg ="";
	    
		// 修改请假
		String sql = "UPDATE LEAVE SET TYPE=?,STATUS=?,START_DATE=?,END_DATE=?,DAYS=?,CREATE_ID=? WHERE LEAVE_ID=?";
		Object[] param = new Object[7];
		param[0] = TYPE;
		param[1] = STATUS;
		param[2] = START_DATE;
		param[3] = END_DATE;		
		param[4] = DAYS;
		param[5] = CREATE_ID;
		param[6] = LEAVE_ID;

		int result = BaseDao.exeSql(sql, param);
		if (result == 1) {
			
			// 修改请假完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getLeaveListData(request);			
			msg = "修改成功！";	
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "修改失败！");		
		JsonService.ResponseJson(request, response, normal_json);
	}

	
	

	// add by lizhenyu
	public void getLeaveList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getLeaveListData(request);
		request.setAttribute("leave_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_staff_leave.jsp").forward(request, response);				
}

	private JSONArray getLeaveListData(HttpServletRequest request) {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row
		
		Calendar cal = Calendar.getInstance();
		int YEAR = cal.get(Calendar.YEAR);
		String current_year=Integer.toString(YEAR);
		String current_year_start= current_year+"0101";
		String current_year_end= current_year+"1231";
		
		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT a.USERNAME,a.EMAIL,b.* FROM USERS a LEFT JOIN LEAVE  b ON a.USER_ID=b.USER_ID "
				+ "WHERE b.START_DATE >="+current_year_start+" and b.END_DATE <="+current_year_end +"order BY b.START_DATE DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "select count(1) from LEAVE  WHERE START_DATE >="+current_year_start+" and END_DATE <="+current_year_end;	

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