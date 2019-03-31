package com.csh.servlet;

/*********************
 * Author shaohui-chen
 ******************** */
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.service.JsonService;
import com.csh.service.WebUsersService;
import com.csh.util.PageModel;

public class WebReportUsersServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String method = request.getParameter("method");

		// by xiaopan
		switch (method) {
		case "users_list":
			getUsersList(request, response);
			break;
		}

		// if (method.equals("usereditadd")) {
		//
		// editaddUsers(request, response);
		// }
		//
		// if (method.equals("useradd")) {
		//
		// addUsers(request, response);
		// }
		//
		// if (method.equals("listFormalUsers")) {
		// listFormalUsers(request, response);
		//
		// }
		//
		// if (method.equals("editWebUsers")) {
		//
		// editWebUsers(request, response);
		// }
		//
		// if (method.equals("listSearchWebUsers")) {
		// listSearchWebUsers(request, response);
		//
		// }
		//
		// if (method.equals("listWebUsers")) {
		// listWebUsers(request, response);
		//
		// }
		//
		// if (method.equals("auditWebUsers")) {
		//
		// auditWebUsers(request, response);
		// }
		//
		// if (method.equals("deleteWebUsers")) {
		// deleteWebUsers(request, response);
		// }

	}

	public void listFormalUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		String sql = "select * from USERS t where 1=1 ";
		String rowSql = "select count(1) from USERS t where 1=1 ";
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		PageModel<Users> page = WebUsersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.getSession().setAttribute("page", page);
		request.getSession().setAttribute("webusers", oper);
		try {
			request.getRequestDispatcher("/jsp/webuserlist.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// ��δ����û�
	public void listNoAuditWebUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Users user = (Users) request.getSession().getAttribute("webusers");// ��¼����Ա
		if (user == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		String sql = "select * from USERS t where 1=1 and t.bak=0";
		String rowSql = "select count(1) from USERS t where 1=1 and t.bak=0 ";
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		PageModel<Users> page = WebUsersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
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

	// ����û�
	public void auditWebUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("enter servlet audituser->");

		String user_Id = request.getParameter("userId");
		System.out.println("-auditWebUsers-userId->" + user_Id);

		int uid = WebUsersService.auditWebUsers(user_Id);
		if (uid == -1) {
			String message = "ʧ��";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		} else {

			listNoAuditWebUsers(request, response);
		}

	}

	public void listSearchWebUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		PageModel<Users> page = WebUsersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.setAttribute("page", page);
		request.getSession().setAttribute("webusers", user);
		try {
			// ��Ҫ�����쳣�Ĵ���
			request.getRequestDispatcher("/jsp/webuserlist.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// ��ȡ�б����� listSearchWebUsers
	public void listWebUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Users user = (Users) request.getSession().getAttribute("webusers");
		if (user == null) {
			request.getRequestDispatcher("/zh_CN/company/yb_register_step_1.jsp").forward(request, response);
			return;
		}

		String sql = "select * from USERS t where 1=1 ";
		String rowSql = "select count(1) from USERS t where 1=1 ";
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		PageModel<Users> page = WebUsersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.setAttribute("page", page);
		request.getSession().setAttribute("webusers", user);
		try {
			request.getRequestDispatcher("/jsp/webreportuserslist.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void getUsersList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Users user = (Users) request.getSession().getAttribute("webusers");
		// if (user == null) {
		// request.getRequestDispatcher("/zh_CN/company/yb_register_step_1.jsp").forward(request,
		// response);
		// return;
		// }

		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize;// include the end row

		String listSql = "select * from USERS t where 1=1 ";
		String rowSql = "select count(1) from USERS t where 1=1 ";

		ResultSet rs = WebUsersService.getUsersListRs(startRow, endRow, listSql, null);
		int rows = WebUsersService.getUsersListRows(rowSql, null);

		String rs_json = "";
		if (rs != null) {
			try {
				rs_json = JsonService.ConvertResultSetToJson(rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// json = JsonService.ConvertResultSetToJson("false", "USER_ID",
			// USER_ID);
		}

		// String json = "{\"rows\":\""+rows+"\",\"data\":";
		// json += rs_json;
		// json += "}";

		// JsonService.ResponseJson(request, response, json);
		request.setAttribute("user_list", rs_json);
		request.setAttribute("rows", rows);
		request.getRequestDispatcher("/zh_CN/company/yb_user_list.jsp").forward(request, response);
	}

	// ɾ����ϢeditWebUsers
	public void deleteWebUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");

		int uid = WebUsersService.deleteWebUsers(userId);
		if (uid == -1) {
			String message = "ɾ��ʧ��";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		} else {
			listWebUsers(request, response);
		}

	}

	// �༭�û�
	public void editWebUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		String userId = request.getParameter("userId");

		String sql = "select * from USERS t where t.USER_Id=" + userId;
		Users userinfo = WebUsersService.findWebUsers(sql, null).get(0);
		request.getSession().setAttribute("userinfo", userinfo);// ǰ̨��ʾ��Ϣ�õ�,�鵽����Ϣ��;
		request.getSession().setAttribute("userId", userId);
		request.getRequestDispatcher("/jsp/webUseredit.jsp").forward(request, response);

	}

	// ����û���Ϣ
	public void addUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		String sql = "INSERT INTO USERS(Email,FIRST_NAME,LAST_NAME,USER_NAME,ADDRESS,CITY,ZIP_CODE,COUNTRY,PHONE,PWD,STATUS,CONTRACT,BAK) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] param = new Object[13];
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
		param[12] = 1;

		int result = BaseDao.exeSql(sql, param);
		String message = "";
		if (result == 1) {
			message = "AddUser Success!";
		} else {
			message = "Failure to AddUser !";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);

	}

	// �༭�޸��û�
	public void editaddUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
