package com.csh.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.csh.dao.BaseDao;
import com.csh.service.JsonService;
import com.csh.util.DateUtil;
import com.csh.util.Pagination;

public class CustomerUserAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		 if(!LoginControlPublicServlet.LoginSessionValidate(request, response,2))return;

		String op = request.getParameter("OP");

		if (op == null) {
			getAddressList(request, response);
			return;
		}

		switch (op) {
		// manage Sales ====================================
		case "1":// add_Sales
			addUserAddress(request, response);
			break;
		case "2":// update_Sales
			editUserAddress(request, response);
			break;
		case "3":// delete_Sales
			deleteUserAddress(request, response);
			break;
		case "4":// query_Sales
			// querySales(request, response);
			break;
		}

		// System.out.println("test");
		// addUserAddress(request, response);

	}

	private void deleteUserAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ADDRESS_ID = request.getParameter("ADDRESS_ID");

		String sql = "DELETE FROM USER_ADDRESS WHERE ADDRESS_ID=?";
		Object[] param = new Object[1];
		param[0] = ADDRESS_ID;
		int flag = BaseDao.exeSql(sql, param);

		String normal_json = "";
		String msg = "";

		if (flag > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getAddressListData(request, response);
			msg = "地址删除成功！";
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else {
			msg = "地址删除失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public static JSONArray getAddressListData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object CUSTOMER_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");

		// String M_TEAM_ID = jsonarr.getJSONObject(i).getString("M_TEAM_ID");

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,A.* FROM(SELECT * FROM USER_ADDRESS WHERE USER_ID=? ORDER BY IS_DEFAULT DESC)A";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[3];
		param[0] = CUSTOMER_ID;
		param[1] = startRow;
		param[2] = endRow;

		String rowSql = "SELECT COUNT(1) FROM USER_ADDRESS WHERE USER_ID=?";
		Object[] param1 = new Object[1];
		param1[0] = CUSTOMER_ID;

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, param1);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}
	
	// add by xiaopan
		public static JSONArray getAddressListDataNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Object CUSTOMER_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");

			// String M_TEAM_ID = jsonarr.getJSONObject(i).getString("M_TEAM_ID");

			String page_size = request.getParameter("page_size");
			String current_page = request.getParameter("current_page");
			Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
			Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

			int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
			int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;// include the end row

			String sql = "SELECT ROWNUM AS RN,A.* FROM(SELECT * FROM USER_ADDRESS WHERE USER_ID=? ORDER BY TIMESTAMP DESC)A";
			String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
			Object[] param = new Object[3];
			param[0] = CUSTOMER_ID;
			param[1] = startRow;
			param[2] = endRow;

			String rowSql = "SELECT COUNT(1) FROM USER_ADDRESS WHERE USER_ID=?";
			Object[] param1 = new Object[1];
			param1[0] = CUSTOMER_ID;

			JSONArray jsonarr = new JSONArray();
			jsonarr = BaseDao.getRsJSONArray(listSql, param);
			int rows = BaseDao.getRowCount(rowSql, param1);

			// paging
			Pagination page = new Pagination();
			page.setCurrentPage(currentPage);
			page.setPageSize(pageSize);
			page.setTotalRecord(rows);
			request.setAttribute("page", page);
			return jsonarr;
		}

	private void getAddressList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getAddressListData(request, response);
		request.setAttribute("address_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_settle.jsp").forward(request, response);

	}

	private void editUserAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Users oper = (Users) request.getSession().getAttribute("users");
		// long user_id = oper.getUserId();
		Object USER_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取

		String ADDRESS_ID = request.getParameter("ADDRESS_ID");
		String USER_NAME = request.getParameter("USER_NAME");
		String USER_PHONE = request.getParameter("USER_PHONE");
		String USER_TEL = request.getParameter("USER_TEL");
		String AREA_ID1 = request.getParameter("AREA_ID1");
		String AREA_ID2 = request.getParameter("AREA_ID2");
		String AREA_ID3 = request.getParameter("AREA_ID3");
		String ADDRESS = request.getParameter("ADDRESS");
		String IS_DEFAULT = request.getParameter("IS_DEFAULT");
		String TIMESTAMP=DateUtil.getTimastampString();
		if(IS_DEFAULT!=null&&IS_DEFAULT.equals("1")){//only one default address
			String sql0 = "update USER_ADDRESS set IS_DEFAULT=? where USER_ID=?";
			Object[] param0 = new Object[2];
			param0[0] = 0;
			param0[1] = USER_ID;
			BaseDao.exeSql(sql0, param0);
		}

		String edit_sql = "update USER_ADDRESS set USER_NAME=?,USER_PHONE=?,USER_TEL=?,AREA_ID1=?,AREA_ID2=?,AREA_ID3=?,ADDRESS=?,IS_DEFAULT=?,TIMESTAMP=? where ADDRESS_ID=?";
		Object[] param = new Object[10];
		param[0] = USER_NAME;
		param[1] = USER_PHONE;
		param[2] = USER_TEL;
		param[3] = AREA_ID1;
		param[4] = AREA_ID2;
		param[5] = AREA_ID3;
		param[6] = ADDRESS;
		param[7] = IS_DEFAULT;
		param[8] = TIMESTAMP;
		param[9] = ADDRESS_ID;
		
		int flag = BaseDao.exeSql(edit_sql, param);

		String normal_json = "";
		String msg = "";

		if (flag > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getAddressListDataNew(request, response);
			msg = "地址修改成功！";
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else {
			msg = "地址修改失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	private void addUserAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Object CUSTOMER_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");

		// String USER_ID = request.getParameter("USER_ID");
		String USER_NAME = request.getParameter("USER_NAME");
		String USER_PHONE = request.getParameter("USER_PHONE");
		String USER_TEL = request.getParameter("USER_TEL");
		String AREA_ID1 = request.getParameter("AREA_ID1");
		String AREA_ID2 = request.getParameter("AREA_ID2");
		String AREA_ID3 = request.getParameter("AREA_ID3");
		String ADDRESS = request.getParameter("ADDRESS");
		String IS_DEFAULT = request.getParameter("IS_DEFAULT");
		String TIMESTAMP=DateUtil.getTimastampString();

		String insert_sql = "INSERT INTO USER_ADDRESS(USER_ID,USER_NAME,USER_PHONE,USER_TEL,AREA_ID1,AREA_ID2,AREA_ID3,ADDRESS,IS_DEFAULT,TIMESTAMP) VALUES(?,?,?,?,?,?,?,?,?,?)";
		Object[] param = new Object[10];
		param[0] = CUSTOMER_ID;
		param[1] = USER_NAME;
		param[2] = USER_PHONE;
		param[3] = USER_TEL;
		param[4] = AREA_ID1;
		param[5] = AREA_ID2;
		param[6] = AREA_ID3;
		param[7] = ADDRESS;
		param[8] = IS_DEFAULT;
		param[9] = TIMESTAMP;
		int flag = BaseDao.exeSql(insert_sql, param);

		String normal_json = "";
		String msg = "";

		if (flag > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getAddressListDataNew(request, response);
			msg = "地址添加成功！";
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else {
			msg = "地址添加失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}


}
