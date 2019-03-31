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

public class CompanyBasicRuleManageServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		String op = request.getParameter("OP");
		if (op == null) {
			getBasicRuleList(request, response);
			return;
		}

		// by lizhenyu
		switch (op) {
		
		// basic rule====================================
		case "1":// add_basic_rule
			addBasicRule(request, response);
			break;		
		case "2":// update_basic_rule
			updateBasicRule(request, response);
			break;
		case "3"://delete_rule
			deleteBasicRule(request, response);
			break;
		}

	}

	// by lizhenyu 添加基本就业条款,并返回新列表
	public void addBasicRule(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		String ROLE = request.getParameter("ROLE");
		//String SALARY = request.getParameter("SALARY");		
		String SALE_BROKERAGE = request.getParameter("SALE_BROKERAGE");
		String COVER_BROKERAGE = request.getParameter("COVER_BROKERAGE");		
		String PERSONAL_SALE = request.getParameter("PERSONAL_SALE");		
		String GROUP_SALE = request.getParameter("GROUP_SALE");						
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取		
   
		String normal_json = "";
		String msg ="";
		// 先判断该ROLE的基本就业条款是否存在，若存在直接让其修改
		String select_sql = "SELECT COUNT(1) FROM BASIC_TERMS WHERE ROLE=" + ROLE;
		int rows = BaseDao.getRowCount(select_sql, null);
		if (rows > 0) {
			msg = "该级别的基本就业条款已设定，不可重复添加，请直接修改！";
			JSONArray jsonarr = new JSONArray();
			jsonarr = getBasicRuleListData(request);			
			if (jsonarr!=null) 
					normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
			else 
					normal_json = JsonService.getErrorMsgNormalJson("数据读取异常！");						
			JsonService.ResponseJson(request, response, normal_json);
			return;
		}
		// 添加基本就业条款
		String sql = "INSERT INTO BASIC_TERMS(ROLE,SALE_BROKERAGE,COVER_BROKERAGE,PERSONAL_SALE,GROUP_SALE,CREATE_ID) VALUES(?,?,?,?,?,?)";
		Object[] param = new Object[6];
		param[0] = ROLE;		
		param[1] = SALE_BROKERAGE;
		param[2] = COVER_BROKERAGE;		
		param[3] = PERSONAL_SALE;
		param[4] = GROUP_SALE;		
		param[5] = CREATE_ID;

		int result = BaseDao.exeSql(sql, param);
		if (result == 1) {
			
			// 添加基本就业条款完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getBasicRuleListData(request);			
			msg = "基本就业条款添加成功！";	
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "基本就业条款添加失败！");		
		JsonService.ResponseJson(request, response, normal_json);
	}

	// by lizhenyu 删除基本就业条款
	public void deleteBasicRule(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		String ROLE = request.getParameter("ROLE");

		String sql = "DELETE FROM BASIC_TERMS WHERE ROLE=?";
		Object[] param = new Object[1];
		param[0] = ROLE;
		int result = BaseDao.exeSql(sql, param);
		String normal_json = "";
		String msg ="";
		if (result == 1) {
			// 删除完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getBasicRuleListData(request);			
			msg = "删除成功！";
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "删除失败！");		
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by lizhenyu 修改基本就业条款
	public void updateBasicRule(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		String ROLE = request.getParameter("ROLE");
		//String SALARY = request.getParameter("SALARY");		
		String SALE_BROKERAGE = request.getParameter("SALE_BROKERAGE");
		String COVER_BROKERAGE = request.getParameter("COVER_BROKERAGE");		
		String PERSONAL_SALE = request.getParameter("PERSONAL_SALE");		
		String GROUP_SALE = request.getParameter("GROUP_SALE");			
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取		
		   

		String sql = "UPDATE BASIC_TERMS SET SALE_BROKERAGE=?,COVER_BROKERAGE=?,PERSONAL_SALE=?,GROUP_SALE=?,CREATE_ID=? WHERE ROLE=?";
		Object[] param = new Object[6];
		param[0] = SALE_BROKERAGE;
		param[1] = COVER_BROKERAGE;
		param[2] = PERSONAL_SALE;
		param[3] = GROUP_SALE;		
		param[4] = CREATE_ID;
		param[5] = ROLE;

		int result = BaseDao.exeSql(sql, param);
		String normal_json = "";
		String msg ="";
		if (result == 1) {
			// 修改基本条款完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getBasicRuleListData(request);			
			msg = "修改成功！";
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "修改失败！");		
		JsonService.ResponseJson(request, response, normal_json);
	}
	

	// add by lizhenyu
	public void getBasicRuleList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getBasicRuleListData(request);
		request.setAttribute("basic_rule_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_basic_rule.jsp").forward(request, response);				
}

	private JSONArray getBasicRuleListData(HttpServletRequest request) {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,C.* FROM (select * from BASIC_TERMS  where 1=1 ORDER BY ROLE ASC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "select count(1) from BASIC_TERMS  where 1=1 ";	

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
