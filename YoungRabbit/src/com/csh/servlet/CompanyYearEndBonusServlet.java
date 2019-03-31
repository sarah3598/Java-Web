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

import com.csh.dao.BaseDao;
import com.csh.service.JsonService;
import com.csh.util.Pagination;

public class CompanyYearEndBonusServlet extends HttpServlet {

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
			getYearEndBonusList(request, response);
			return;
		}

		// by lizhenyu
		switch (op) {
		
		// Bonus====================================
		case "1":// add_Bonus_rule
			addYearEndBonus(request, response);
			break;		
		case "2":// update_Bonus_rule
			updateYearEndBonus(request, response);
			break;
		case "3"://delete_Bonus_rule
			deleteYearEndBonus(request, response);
			break;
		}

	}

	// by lizhenyu 添加年底奖金条款,并返回新列表
	public void addYearEndBonus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String TYPE = request.getParameter("TYPE");
		String ROLE = request.getParameter("ROLE");
		//String TARGET = request.getParameter("TARGET");
		String BONUS = request.getParameter("BONUS");	
		String VALID = request.getParameter("VALID");
		//String PERCENTAGE = request.getParameter("PERCENTAGE");	
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取		
   
		String normal_json = "";
		String msg ="";
		
		if(Integer.parseInt(TYPE)==1){
		// 添加年底奖金条款
		String sql = "INSERT INTO BONUS(TYPE,ROLE,VALID,BONUS,CREATE_ID) VALUES(?,?,?,?,?)";
		Object[] param = new Object[5];
		param[0] = TYPE;
		param[1] = ROLE;
		//param[2] = TARGET;		
		param[2] = VALID;
		param[3] = BONUS;
		//param[4] = PERCENTAGE;
		param[4] = CREATE_ID;

		int result = BaseDao.exeSql(sql, param);
		if (result == 1) {
			
			// 添加奖金完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getYearEndBonusListData(request);			
			msg = "年底奖金添加成功！";	
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "年底奖金添加失败！");	
		}
		
		/*if(Integer.parseInt(TYPE)==2){
			// 添加年底奖金条款
			String sql = "INSERT INTO BONUS(TYPE,ROLE,TARGET,BONUS,CREATE_ID) VALUES(?,?,?,?,?)";
			Object[] param = new Object[5];
			param[0] = TYPE;
			param[1] = ROLE;
			param[2] = TARGET;
			param[3] = BONUS;			
			param[4] = CREATE_ID;

			int result = BaseDao.exeSql(sql, param);
			if (result == 1) {
				
				// 添加基本就业条款完成后，取出数据，刷新列表
				JSONArray jsonarr = new JSONArray();
				jsonarr = getYearEndBonusListData(request);			
				msg = "年底奖金添加成功！";	
				normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
			} 
			else 
				normal_json = JsonService.getErrorMsgNormalJson( "年底奖金添加失败！");	
			}*/
		
		JsonService.ResponseJson(request, response, normal_json);
	}
	
	// by xiaoapn 删除评级规则
	public void deleteYearEndBonus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		String BONUS_ID = request.getParameter("BONUS_ID");

		String sql = "DELETE FROM BONUS WHERE BONUS_ID=?";
		Object[] param = new Object[1];
		param[0] = BONUS_ID;
		int result = BaseDao.exeSql(sql, param);
		String normal_json = "";
		String msg ="";
		if (result == 1) {
			// 删除完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getYearEndBonusListData(request);			
			msg = "删除成功！";
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "删除失败！");		
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by lizhenyu 修改年底奖金条款
	public void updateYearEndBonus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String BONUS_ID = request.getParameter("BONUS_ID");
		String TYPE = request.getParameter("TYPE");
		String ROLE = request.getParameter("ROLE");
		//String TARGET = request.getParameter("TARGET");
		String BONUS = request.getParameter("BONUS");	
		String VALID = request.getParameter("VALID");
		//String PERCENTAGE = request.getParameter("PERCENTAGE");						
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取		
   
		String normal_json = "";
		String msg ="";
		
		if(Integer.parseInt(TYPE)==1){
		String sql = "UPDATE BONUS SET TYPE=?,ROLE=?,BONUS=?,VALID=?,CREATE_ID=? WHERE BONUS_ID=?";
		Object[] param = new Object[6];
		param[0] = TYPE;
		param[1] = ROLE;
		//param[2] = TARGET;
		param[2] = BONUS;
		param[3] = VALID;
		//param[5] = PERCENTAGE;
		param[4] = CREATE_ID;
		param[5] = BONUS_ID;

		int result = BaseDao.exeSql(sql, param);
		if (result == 1) {
			
			// 修改奖金完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getYearEndBonusListData(request);			
			msg = "修改成功！";	
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "修改失败！");		
		}
		
		/*if(Integer.parseInt(TYPE)==2){
			String sql = "UPDATE BONUS SET TYPE=?,ROLE=?,TARGET=?,BONUS=?,VALID=?,PERCENTAGE=?,CREATE_ID=? WHERE BONUS_ID=?";
			Object[] param = new Object[8];
			param[0] = TYPE;
			param[1] = ROLE;
			param[2] = TARGET;
			param[3] = BONUS;
			param[4] = null;
			param[5] = null;
			param[6] = CREATE_ID;
			param[7] = BONUS_ID;

			int result = BaseDao.exeSql(sql, param);
			if (result == 1) {
				
				// 添加基本就业条款完成后，取出数据，刷新列表
				JSONArray jsonarr = new JSONArray();
				jsonarr = getYearEndBonusListData(request);			
				msg = "修改成功！";	
				normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
			} 
			else 
				normal_json = JsonService.getErrorMsgNormalJson( "修改失败！");		
			}*/
		JsonService.ResponseJson(request, response, normal_json);
	}

	
	

	// add by lizhenyu
	public void getYearEndBonusList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getYearEndBonusListData(request);
		request.setAttribute("bonus_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_year_end_bonus.jsp").forward(request, response);				
}

	private JSONArray getYearEndBonusListData(HttpServletRequest request) {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,C.* FROM (select * from BONUS  where 1=1 ORDER BY TYPE ASC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "select count(1) from BONUS  where 1=1 ";	

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
