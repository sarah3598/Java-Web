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

public class CompanySaleReportServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if (!LoginControlPublicServlet.LoginSessionValidate(request, response, 1))
			return;
		String op = request.getParameter("OP");
		if (op == null) {
			getSaleReportList(request, response);
			return;
		}	
		// by lizhenyu
		switch (op) {
		
		// sale report====================================
		case "1":// personal_report
			detail_personal(request, response);
			break;	
		case "2":// group_report
			detail_group(request, response);
			break;	
		case "999":// sale_report_list for admin
			getSaleReportList2(request, response);
			break;
		}
	}

	

	private void detail_group(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Object USER_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");	   
		String MONTH = request.getParameter("MONTH");
		String year=MONTH.substring(0,4);
		String TABLE_SALES="SALES_"+year;
		String START = MONTH + "01";
		String END = MONTH + "31";
		String normal_json = "";
		String msg ="";
		
		
		String sql = "SELECT a.HAPPEN_DATE,a.HAPPEN_TIME,a.ORDER_ID,a.GOOD_ID,a.GOOD_MONEY,a.TYPE,b.TITLE FROM "+TABLE_SALES+" a LEFT JOIN WEB_GOODS_BASIC_INFO b ON a.GOOD_ID=b.GOOD_ID WHERE (a.MA_ID=? OR a.VP_ID=?) AND a.HAPPEN_DATE>=? AND a.HAPPEN_DATE<=? ORDER BY a.HAPPEN_DATE DESC";		
		Object[] param = new Object[4];		
		param[0] = USER_ID;
		param[1] = USER_ID;		
		param[2] = START;
		param[3] = END;
		
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql, param);
		
		if (jsonarr != null && jsonarr.length() > 0)			
			normal_json = JsonService.getNormalJson(jsonarr.toString(),"");
		
		else
			normal_json = JsonService.getNormalJson(jsonarr.toString(),msg);	
		JsonService.ResponseJson(request, response, normal_json);
		
	}

	private void detail_personal(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Object USER_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");	   
		String MONTH = request.getParameter("MONTH");
		String year=MONTH.substring(0,4);
		String TABLE_SALES="SALES_"+year;
		String START = MONTH + "01";
		String END = MONTH + "31";
		String normal_json = "";
		String msg ="";
		
		
		String sql = "SELECT a.HAPPEN_DATE,a.HAPPEN_TIME,a.ORDER_ID,a.GOOD_ID,a.GOOD_MONEY,a.TYPE,b.TITLE FROM "+TABLE_SALES+" a LEFT JOIN WEB_GOODS_BASIC_INFO b ON a.GOOD_ID=b.GOOD_ID WHERE (a.SALES_ID=? AND a.TYPE=1) OR (a.SALES_ID=? AND a.TYPE=2) OR (a.MA_ID=? AND a.TYPE=4)  OR (a.MA_ID=? AND a.TYPE=8) OR (a.VP_ID=? AND a.TYPE=16) AND a.HAPPEN_DATE>=? AND a.HAPPEN_DATE<=? ORDER BY a.HAPPEN_DATE DESC";		
		Object[] param = new Object[7];		
		param[0] = USER_ID;
		param[1] = USER_ID;
		param[2] = USER_ID;		
		param[3] = USER_ID;
		param[4] = USER_ID;
		param[5] = START;
		param[6] = END;
		
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql, param);
		
		if (jsonarr != null && jsonarr.length() > 0)			
			normal_json = JsonService.getNormalJson(jsonarr.toString(),"");
		
		else
			normal_json = JsonService.getNormalJson(jsonarr.toString(),msg);	
		JsonService.ResponseJson(request, response, normal_json);	
	}

	// add by lizhenyu
	public void getSaleReportList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object USER_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取				   
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getSaleReportListData(request,USER_ID);
		request.setAttribute("sale_report_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_sale_report.jsp").forward(request, response);				
	}
	
	// add by lizhenyu
	public void getSaleReportList2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
						   
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getSaleReportListData2(request);
		request.setAttribute("sale_report_list", jsonarr1.toString());
		String user_name=request.getParameter("USERNAME"); // 
		request.setAttribute("GROUP_NAME", user_name);
		request.getRequestDispatcher("/zh_CN/company/yb_db_sale_report2.jsp").forward(request, response);				
	}

	private JSONArray getSaleReportListData(HttpServletRequest request, Object USER_ID) {
		Object user_id=USER_ID;
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT USER_ID,GROLE,MONTH,SUM,PERSONAL_SALE,GROUP_SALE FROM SALARY WHERE USER_ID="+user_id+" ORDER BY MONTH DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "select count(1) from SALARY  where USER_ID="+user_id;	

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
		
	private JSONArray getSaleReportListData2(HttpServletRequest request) {
		String user_id =request.getParameter("TEAM_ID"); // 
		
		
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT USER_ID,GROLE,MONTH,SUM,PERSONAL_SALE,GROUP_SALE FROM SALARY WHERE USER_ID="+user_id+" ORDER BY MONTH DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "select count(1) from SALARY  where USER_ID="+user_id;	

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
