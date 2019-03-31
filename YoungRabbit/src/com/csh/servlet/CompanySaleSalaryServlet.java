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

public class CompanySaleSalaryServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if (!LoginControlPublicServlet.LoginSessionValidate(request, response, 1))
			return;
		getSaleSalaryList(request, response);
		// return;
	}

	// add by lizhenyu
	public void getSaleSalaryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONArray jsonarr1 = new JSONArray();
		Object USER_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");
		jsonarr1 = getSaleSalaryListData(request, USER_ID);

		request.setAttribute("sale_salary_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_sale_salary.jsp").forward(request, response);
	}

	private JSONArray getSaleSalaryListData(HttpServletRequest request, Object USER_ID) {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT a.USERNAME,b.GROLE,b.SALARY_ID,b.MONTH,b.BONUS,b.SALE_BROKERAGE,b.COVER_BROKERAGE,b.PERSONAL_SALE,b.GROUP_SALE,b.ESPECIAL_REWARD,b.SUM FROM SALARY b LEFT JOIN USERS a ON a.USER_ID=b.USER_ID WHERE b.USER_ID =" + USER_ID + " ORDER BY b.MONTH DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM SALARY b LEFT JOIN USERS a ON a.USER_ID=b.USER_ID WHERE b.USER_ID =" + USER_ID;

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
