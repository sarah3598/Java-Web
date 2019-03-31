package com.csh.servlet;

/*********************
 * Author shaohui-chen
 ******************** */
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.service.CustomerUsersService;
import com.csh.service.JsonService;
import com.csh.util.Encoder;
import com.csh.util.PageModel;
import com.csh.util.Pagination;

public class CustomerUserManageServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String op = request.getParameter("OP");

		if (op == null) {
			getUsersList(request, response);
			return;
		}

		switch (op) {
		// manage users ====================================
		case "1":// add_Users
			addUsers(request, response);
			break;
		case "2":// update_Users
			updateUsers(request, response);
			break;
		case "3":// delete_Users
			deleteUsers(request, response);
			break;
		case "4":// query_Users
			queryUsers(request, response);
			break;
		}
	}

	// by lizhenyu query Company Users
	private void queryUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String users = request.getParameter("users");
		// System.out.println(users);
		String searchUsers = request.getParameter("searchUsers");
		// System.out.println(searchUsers);
		String rs_json = "";
		String msg = "";
		if (searchUsers == "") {
			// 输入为空
			getUsersList(request, response);
		} else {
			switch (users) {
			case "USER_ID":
				String sqlwhere = "";
				sqlwhere = "AND USER_ID LIKE \'%" + searchUsers + "%\'";

				int pageSize = 10;
				int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
				int startRow = (currentPage - 1) * pageSize;
				int endRow = startRow + pageSize;// include the end row

				String listSql = "select * from USERS t where ROLE=9999 ";
				String rowSql = "select count(1) from USERS t where ROLE=9999 ";

				listSql = listSql + sqlwhere;
				rowSql = rowSql + sqlwhere;

				ResultSet rs = CustomerUsersService.getUsersListRs(startRow, endRow, listSql, null);
				int rows = CustomerUsersService.getUsersListRows(rowSql, null);

				try {
					if (rs.isBeforeFirst()) {
						msg = "";
						rs_json = JsonService.getNormalJson(rs, msg);

					} else {
						msg = "没有相关搜索结果！";
						rs_json = JsonService.getNormalJson(rs, msg);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg = "搜索成功！数据加载失败！";
					rs_json = JsonService.getOperateResultNormalJson("true", null, msg);
				}
				break;

			case "EMAIL":
				sqlwhere = "AND EMAIL LIKE \'%" + searchUsers + "%\'";
				pageSize = 10;
				currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
				startRow = (currentPage - 1) * pageSize;
				endRow = startRow + pageSize;// include the end row

				listSql = "select * from USERS t where ROLE=9999 ";
				rowSql = "select count(1) from USERS t where ROLE=9999";

				listSql = listSql + sqlwhere;
				rowSql = rowSql + sqlwhere;

				rs = CustomerUsersService.getUsersListRs(startRow, endRow, listSql, null);
				rows = CustomerUsersService.getUsersListRows(rowSql, null);

				try {
					if (rs.isBeforeFirst()) {
						msg = "";
						rs_json = JsonService.getNormalJson(rs, msg);

					} else {
						msg = "没有相关搜索结果！";
						rs_json = JsonService.getNormalJson(rs, msg);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg = "搜索成功！数据加载失败！";
					rs_json = JsonService.getOperateResultNormalJson("true", null, msg);
				}
				break;

			case "USERNAME":
				sqlwhere = "AND USERNAME LIKE \'%" + searchUsers + "%\'";

				pageSize = 10;
				currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
				startRow = (currentPage - 1) * pageSize;
				endRow = startRow + pageSize;// include the end row

				listSql = "select * from USERS t where ROLE=9999 ";
				rowSql = "select count(1) from USERS t where ROLE=9999";

				listSql = listSql + sqlwhere;
				rowSql = rowSql + sqlwhere;

				rs = CustomerUsersService.getUsersListRs(startRow, endRow, listSql, null);
				rows = CustomerUsersService.getUsersListRows(rowSql, null);

				try {
					if (rs.isBeforeFirst()) {
						msg = "";
						rs_json = JsonService.getNormalJson(rs, msg);

					} else {
						msg = "没有相关搜索结果！";
						rs_json = JsonService.getNormalJson(rs, msg);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg = "搜索成功！数据加载失败！";
					rs_json = JsonService.getOperateResultNormalJson("true", null, msg);
				}
				break;
			}

		}
		JsonService.ResponseJson(request, response, rs_json);

	}

	// by lizhenyu 删除用户，返回新列表
	public void deleteUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String USER_ID = request.getParameter("USER_ID");

		String sql = "DELETE FROM USERS WHERE USER_ID=?";
		Object[] param = new Object[1];
		param[0] = USER_ID;
		int flag = BaseDao.exeSql(sql, param);
		String normal_json = "";
		String msg = "";
		if (flag > 0) {
			msg = "删除成功！";
			normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

		} else {// error message
			msg = "删除失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	// by lizhenyu 添加用户,并返回新列表
	public void addUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String FIRST_NAME = request.getParameter("FIRST_NAME");
		String LAST_NAME = request.getParameter("LAST_NAME");
		String EMAIL = request.getParameter("EMAIL");
		String USERNAME = LAST_NAME + FIRST_NAME;
		String PWD = request.getParameter("PWD");
		if (!(PWD.equals(""))) {
			try {
				PWD = Encoder.EncoderByMd5(PWD);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String ROLE = request.getParameter("ROLE");
		String PHONE = request.getParameter("PHONE");
		String WECHAT = request.getParameter("WECHAT");
		String FACEBOOK = request.getParameter("FACEBOOK");
		String msg = "";
		String mormal_json = "";
		// 先判断该用户是否存在，若存在不允许添加
		String select_sql = "SELECT COUNT(1) FROM USERS WHERE USERNAME='" + USERNAME + "' OR EMAIL='" + EMAIL + "'";
		int rows = BaseDao.getRowCount(select_sql, null);
		if (rows > 0) {
			msg = "该用户名或邮箱已存在，请勿重复添加！";
			mormal_json = JsonService.getErrorMsgNormalJson(msg);
		} else {

			// 该用户不存在，允许添加
			String sql = "INSERT INTO USERS(FIRST_NAME,LAST_NAME,EMAIL,PWD,ROLE,PHONE,WECHAT,FACEBOOK,USERNAME) VALUES(?,?,?,?,?,?,?,?,?)";
			Object[] param = new Object[9];
			param[0] = FIRST_NAME;
			param[1] = LAST_NAME;
			param[2] = EMAIL;
			param[3] = PWD;
			param[4] = ROLE;
			param[5] = PHONE;
			param[6] = WECHAT;
			param[7] = FACEBOOK;
			param[8] = USERNAME;

			int result = BaseDao.exeSql(sql, param);
			if (result > 0) {
				msg = "添加成功！";
				mormal_json = JsonService.getOperateResultNormalJson("true", null, msg);
			} else {
				msg = "添加失败！";
				mormal_json = JsonService.getErrorMsgNormalJson(msg);
			}
		}
		JsonService.ResponseJson(request, response, mormal_json);

	}

	// by lizhenyu 修改用户信息
	public void updateUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String FIRST_NAME = request.getParameter("FIRST_NAME");
		String LAST_NAME = request.getParameter("LAST_NAME");
		// String EMAIL = request.getParameter("EMAIL");
		String USERNAME = LAST_NAME + FIRST_NAME;
		String PWD = request.getParameter("PWD");
		if (!(PWD.equals(""))) {
			try {
				PWD = Encoder.EncoderByMd5(PWD);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String ROLE = request.getParameter("ROLE");
		String PHONE = request.getParameter("PHONE");
		String WECHAT = request.getParameter("WECHAT");
		String FACEBOOK = request.getParameter("FACEBOOK");
		String USER_ID = request.getParameter("USER_ID");
		// String CREATE_ID = "10011";// 应该从session中获取当前用户名
		String normal_json = "";
		String msg = "";

		if (!(PWD.equals(""))) {// 密码非空
			String sql = "UPDATE USERS SET FIRST_NAME=?,LAST_NAME=?,PWD=?,ROLE=?,PHONE=?,WECHAT=?,FACEBOOK=?,USERNAME=? WHERE USER_ID=?";
			Object[] param = new Object[9];
			param[0] = FIRST_NAME;
			param[1] = LAST_NAME;
			param[2] = PWD;
			param[3] = ROLE;
			param[4] = PHONE;
			param[5] = WECHAT;
			param[6] = FACEBOOK;
			param[7] = USERNAME;
			param[8] = USER_ID;

			int result = BaseDao.exeSql(sql, param);
			if (result > 0) {
				msg = "更新成功！";
				normal_json = JsonService.getOperateResultNormalJson("true", null, msg);
			} else {
				msg = "更新失败！";
				normal_json = JsonService.getErrorMsgNormalJson(msg);
			}
		} else {
			String sql = "UPDATE USERS SET FIRST_NAME=?,LAST_NAME=?,ROLE=?,PHONE=?,WECHAT=?,FACEBOOK=?,USERNAME=? WHERE USER_ID=?";
			Object[] param = new Object[8];
			param[0] = FIRST_NAME;
			param[1] = LAST_NAME;
			param[2] = ROLE;
			param[3] = PHONE;
			param[4] = WECHAT;
			param[5] = FACEBOOK;
			param[6] = USERNAME;
			param[7] = USER_ID;

			int result = BaseDao.exeSql(sql, param);
			if (result > 0) {
				msg = "更新成功！";
				normal_json = JsonService.getOperateResultNormalJson("true", null, msg);
			} else {
				msg = "更新失败！";
				normal_json = JsonService.getErrorMsgNormalJson(msg);
			}
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public void getUsersList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getUsersListData(request);
		request.setAttribute("member_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_customer_manage.jsp").forward(request, response);
	}

	private JSONArray getUsersListData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,B.* FROM(select * from USERS where ROLE=9999)B";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "select count(1) from USERS  where ROLE=9999";

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

	public void listFormalUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray user_arr = (JSONArray) request.getSession().getAttribute("users");
		// user_arr.equals(null);
		String path = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath();// 项目名称

		if (user_arr == null || user_arr.length() == 0) {
			String strBackUrl = path + request.getServletPath() // 请求页面或其他地址
					+ "?" + (request.getQueryString()); // 参数
			// request.setAttribute("strBackUrl", strBackUrl);

			response.sendRedirect(path + "/zh_CN/customer/yb_user_login.jsp?token=" + strBackUrl);
			return;
		}

		String sql = "select * from USERS t where 1=1 where ROLE=9999 ";
		String rowSql = "select count(1) from USERS t where 1=1 where ROLE=9999 ";
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		PageModel<Users> page = CustomerUsersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.getSession().setAttribute("page", page);
		request.getSession().setAttribute("users", user_arr);
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
		PageModel<Users> page = CustomerUsersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
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

		String USER_ID = request.getParameter("USER_ID");
		System.out.println("-auditWebUsers-USER_ID->" + USER_ID);

		int uid = CustomerUsersService.auditWebUsers(USER_ID);
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
		PageModel<Users> page = CustomerUsersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.setAttribute("page", page);
		request.getSession().setAttribute("webusers", user);
		try {
			// ��Ҫ�����쳣�Ĵ���
			request.getRequestDispatcher("/jsp/webuserlist.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// �༭�û�
	public void editWebUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		String USER_ID = request.getParameter("USER_ID");

		String sql = "select * from USERS t where t.USER_ID=" + USER_ID;
		Users userinfo = CustomerUsersService.findWebUsers(sql, null).get(0);
		request.getSession().setAttribute("userinfo", userinfo);// ǰ̨��ʾ��Ϣ�õ�,�鵽����Ϣ��;
		request.getSession().setAttribute("USER_ID", USER_ID);
		request.getRequestDispatcher("/jsp/webUseredit.jsp").forward(request, response);

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
