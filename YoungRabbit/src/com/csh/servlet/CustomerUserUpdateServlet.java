package com.csh.servlet;

/*********************
 * Author wendy
 ******************** */
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.csh.dao.BaseDao;
import com.csh.service.CompanyUsersService;
import com.csh.service.JsonService;
import com.csh.util.Pagination;

public class CustomerUserUpdateServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		 if(!LoginControlPublicServlet.LoginSessionValidate(request, response,2))return;
		String op = request.getParameter("OP");
		if (op == null) {
			getUserList(request, response);
		} else {
			// by wendy
			switch (op) {
			case "1":
				updateUsers(request, response);
				break;
			}
		}
	}

	private void getUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object USER_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取
		request.setAttribute("user_id", USER_ID);
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getUserListData(request, USER_ID);
		request.setAttribute("user_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/customer/yb_user_update.jsp").forward(request, response);
	}

	private JSONArray getUserListData(HttpServletRequest request, Object USER_ID) {
		Object user_id = USER_ID;
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,C.* FROM (select * from USERS  where USER_ID=" + user_id + " )C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		request.setAttribute("page", page);

		return jsonarr;
	}

	// by wendy 修改用户信息
	public void updateUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String FIRST_NAME = request.getParameter("FIRST_NAME");
		String LAST_NAME = request.getParameter("LAST_NAME");
		String USERNAME = LAST_NAME + FIRST_NAME;
//		String EMAIL = request.getParameter("EMAIL");
		String PWD_NEW = request.getParameter("PWD_NEW");

		String USER_ID = request.getParameter("USER_ID");
		// String CREATE_ID = "10011";// 应该从session中获取当前用户名

		String normal_json = "";
		String msg = "";
//		// 先判断该EMAIL是否存在，若存在不让其修改
//		String select_sql = "SELECT * FROM USERS WHERE EMAIL=? AND USER_ID <>?";
//		Object[] param1 = new Object[2];
//		param1[0] = EMAIL;
//		param1[1] = USER_ID;
//		JSONArray jsonarr = new JSONArray();
//		jsonarr = BaseDao.getRsJSONArray(select_sql, param1);
//		if (jsonarr != null && jsonarr.length() > 0) {
//			msg = "该邮箱已存在！";
//			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
//			JsonService.ResponseJson(request, response, normal_json);
//			return;
//		}

		if (PWD_NEW == "") {
			String sql = "UPDATE USERS SET FIRST_NAME=?,LAST_NAME=?,USERNAME=? WHERE USER_ID=?";
			Object[] param = new Object[4];
			param[0] = FIRST_NAME;
			param[1] = LAST_NAME;
			param[2] = USERNAME;
			param[3] = USER_ID;

			int result = BaseDao.exeSql(sql, param);
			if (result == 1) {

				// 修改用户完成后，取出数据，刷新列表
				JSONArray jsonarr1 = new JSONArray();
				jsonarr1 = getUserListData(request, USER_ID);
				msg = "修改成功！";
				normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);
			} else
				normal_json = JsonService.getErrorMsgNormalJson("修改失败！");
			JsonService.ResponseJson(request, response, normal_json);
			return;
		} else {

			String sql = "UPDATE USERS SET FIRST_NAME=?,LAST_NAME=?,USERNAME=?,PWD=? WHERE USER_ID=?";
			Object[] param = new Object[5];
			param[0] = FIRST_NAME;
			param[1] = LAST_NAME;
			param[2] = USERNAME;
			param[3] = PWD_NEW;
			param[4] = USER_ID;

			int result = BaseDao.exeSql(sql, param);
			if (result == 1) {

				// 修改用户完成后，取出数据，刷新列表
				JSONArray jsonarr1 = new JSONArray();
				jsonarr1 = getUserListData(request, USER_ID);
				msg = "修改成功！";
				normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);
			} else
				normal_json = JsonService.getErrorMsgNormalJson("修改失败！");
			JsonService.ResponseJson(request, response, normal_json);
		}
	}
}