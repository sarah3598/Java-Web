package com.csh.servlet;

/*********************
 * Author shaohui-chen
 ******************** */
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.service.CompanyGradeService;
import com.csh.service.CompanyUsersService;
import com.csh.service.JsonService;
import com.csh.service.WebUsersService;
import com.csh.util.DateUtil;
import com.csh.util.PageModel;
import com.csh.util.Pagination;

public class CompanyGradeServlet extends HttpServlet {

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
			getGradeRuleList(request, response);
			return;
		}

		// by xiaopan
		switch (op) {
		case "999":
			getGradeRecordList(request, response);
			break;
		case "998":
			getGradeRecordList(request, response);
			break;

		// grade rule====================================
		case "grade_rule_list":
			getGradeRuleList1(request, response);
			break;
		case "1":// add_grade_rule
			addGradeRule(request, response);
			break;
		case "3"://delete_rule
			deleteGradeRule(request, response);
			break;
		case "2":// update_grade_rule
			updateGradeRule(request, response);
			break;
		case "get_grade_rule_detail":
			getGradeRuleDetail(request, response);
			break;
		}

	}

	// by xiaoapn 添加评级规则,并返回新列表
	public void addGradeRule(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ROLE = request.getParameter("ROLE");
		String PERIOD = request.getParameter("PERIOD");
		String RECRUIT = request.getParameter("RECRUIT");
		String SALES = request.getParameter("SALES");
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取				   
		String normal_json = "";
		String msg ="";
		
		// 先判断该ROLE的规则是否存在，若存在直接让其修改
		String select_sql = "SELECT * FROM GRADE_RULE WHERE ROLE=?";
		Object[] param = new Object[1];
		param[0] = ROLE;
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(select_sql, param);
		if (jsonarr != null && jsonarr.length() > 0){
			 msg = "该评级规则已存在，请勿重复添加！";
			 normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
			 JsonService.ResponseJson(request, response, normal_json);
			 return;
		}
 
		// 该规则不存在，允许添加
		String sql = "INSERT INTO GRADE_RULE(ROLE,PERIOD,RECRUIT,SALES,CREATE_ID) VALUES(?,?,?,?,?)";
		Object[] param1 = new Object[5];
		param1[0] = ROLE;
		param1[1] = PERIOD;
		param1[2] = RECRUIT;
		param1[3] = SALES;
		param1[4] = CREATE_ID;

		int result = BaseDao.exeSql(sql, param1);
		if (result == 1) {			
			// 添加规则完成后，取出数据，刷新列表
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getGradeRuleListData(request);			
			msg = "评级规则添加成功！";	
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else 
			normal_json = JsonService.getErrorMsgNormalJson("评级规则添加失败！");		
		JsonService.ResponseJson(request, response, normal_json);
	}

	// by xiaoapn 删除评级规则
	public void deleteGradeRule(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ROLE = request.getParameter("ROLE");

		String sql = "DELETE FROM GRADE_RULE WHERE ROLE=?";
		Object[] param = new Object[1];
		param[0] = ROLE;
		
		int result = BaseDao.exeSql(sql, param);
		String normal_json = "";
		String msg ="";
		if (result == 1) {
			// 删除完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getGradeRuleListData(request);			
			msg = "删除成功！";
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "删除失败！");		
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan 修改评级
	public void updateGradeRule(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ROLE = request.getParameter("ROLE");
		String PERIOD = request.getParameter("PERIOD");
		String RECRUIT = request.getParameter("RECRUIT");
		String SALES = request.getParameter("SALES");
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取				   
		String normal_json = "";
		String msg ="";

		String sql = "UPDATE GRADE_RULE SET PERIOD=?,RECRUIT=?,SALES=?,CREATE_ID=? WHERE ROLE=?";
		Object[] param = new Object[5];
		param[0] = PERIOD;
		param[1] = RECRUIT;
		param[2] = SALES;
		param[3] = CREATE_ID;
		param[4] = ROLE;

		int result = BaseDao.exeSql(sql, param);
		if (result == 1) {
			
			// 修改请假完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getGradeRuleListData(request);			
			msg = "修改成功！";	
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "修改失败！");		
		JsonService.ResponseJson(request, response, normal_json);
	}

	// by xiaoapn 获取评级规则详情
	public void getGradeRuleDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ROLE = request.getParameter("ROLE");

		String sql = "SELECT * FROM GRADE_RULE WHERE ROLE=?";
		Object[] param = new Object[1];
		param[0] = ROLE;
		ResultSet result = CompanyGradeService.doGradeRuleOperate(sql, param);

		String normal_json = "";
		try {
			if (result.isBeforeFirst()) {
				normal_json = JsonService.getNormalJson(result,null);
			} else {
				normal_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			normal_json = JsonService.getOperateResultNormalJson("false", null, "数据读取异常！");
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public void getGradeRecordList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize;// include the end row

		String listSql = "select * from GRADE_RECORD t where 1=1 ";
		String rowSql = "select count(1) from GRADE_RECORD t where 1=1 ";

		ResultSet rs = CompanyGradeService.getGradeRecordListRs(startRow, endRow, listSql, null);
		int rows = CompanyGradeService.getGradeRecordListRows(rowSql, null);

		String rs_json = "";
		try {
			if (rs.isBeforeFirst()) {
				rs_json = JsonService.ConvertResultSetToJson(rs);

			} else {
				rs_json = "";
				rows = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("grade_record_list", rs_json);
		request.setAttribute("rows", rows);
		request.getRequestDispatcher("/zh_CN/company/yb_db_grade_record_list.jsp").forward(request, response);
	}

	// add by xiaopan
		public void getGradeRuleList1(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {		
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getGradeRuleListData(request);
			String normal_json = JsonService.getNormalJson(jsonarr1.toString(), "");
			JsonService.ResponseJson(request, response, normal_json);
			
		}
	// add by xiaopan
	public void getGradeRuleList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getGradeRuleListData(request);
		request.setAttribute("grade_rule_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_grade_rule.jsp").forward(request, response);		
	}

	private JSONArray getGradeRuleListData(HttpServletRequest request) {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row
				
		
		String sql = "SELECT ROWNUM AS RN,C.* FROM (select * from GRADE_RULE where 1=1 )C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "select count(1) from GRADE_RULE where 1=1";	

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
