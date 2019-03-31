/*********************
 * Author shaohui-chen
 ******************** */

package com.csh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.csh.dao.BaseDao;
import com.csh.service.JsonService;
import com.csh.service.WebUsersService;

public class WebUserRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String step = request.getParameter("step");
		if (step == null) {
			request.getRequestDispatcher("/zh_CN/company/yb_register_step_1.jsp").forward(request, response);
			return;
		}
		switch (step) {
		case "1":// create a user
			createUser(request, response);
			break;
		case "2":// add contact_info
			addContactInfo(request, response);
			break;
		case "3":// add bank_info
			addBankInfo(request, response);
			break;
		default:// may be illegal invasion
			break;
		}

	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void createUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String FIRST_NAME = request.getParameter("FIRST_NAME");
		String LAST_NAME = request.getParameter("LAST_NAME");
		String EMAIL = request.getParameter("EMAIL");
		String PWD = request.getParameter("PWD");
		String ROLE = request.getParameter("ROLE");// 注册时默认为实习状态
		String STATUS = "1";// 表示在此步提交后，个人信息已完成

		String select_email_sql = "SELECT * FROM USERS WHERE  EMAIL=?";
		Object[] params = new Object[1];
		params[0] = EMAIL;

		// 验证邮箱是否已被注册
		Boolean result = WebUsersService.verify_Email(select_email_sql, params);
		if (result) {
			String json = "";
			json = JsonService.ResultJson("0", "USER_ID", "");
			JsonService.ResponseJson(request, response, json);
			return;
		} else {
			String insert_sql = "INSERT INTO USERS(FIRST_NAME,LAST_NAME,EMAIL,PWD,ROLE,STATUS) VALUES(?,?,?,?,?,?)";
			Object[] param = new Object[6];
			param[0] = FIRST_NAME;
			param[1] = LAST_NAME;
			param[2] = EMAIL;
			param[3] = PWD;
			param[4] = ROLE;
			param[5] = STATUS;

			Boolean flag = WebUsersService.doUserRegister(insert_sql, param);
			int USER_ID = -1;
			String json = "";
			if (flag) {
				String get_user_id_sql = "SELECT USER_ID FROM USERS WHERE FIRST_NAME=? AND LAST_NAME=? AND EMAIL=? AND PWD=? AND ROLE=? AND STATUS=?";
				USER_ID = WebUsersService.getUserID(get_user_id_sql, param);
			}

			if (USER_ID > 0) {
				json = JsonService.ResultJson("1", "USER_ID", Integer.toString(USER_ID));
			} else {
				json = JsonService.ResultJson("0", "USER_ID", Integer.toString(USER_ID));
			}

			JsonService.ResponseJson(request, response, json);
		}
	}

	public void addContactInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get from servlet
		String user_id = request.getParameter("user_id");
		if (user_id != null) {
			request.setAttribute("USER_ID", user_id);
			request.getRequestDispatcher("/zh_CN/company/yb_register_step_2.jsp").forward(request, response);
			return;
		}
		String PHONE = request.getParameter("PHONE");
		String WECHAT = request.getParameter("WECHAT");
		String FACEBOOK = request.getParameter("FACEBOOK");
		String ADDRESS = request.getParameter("ADDRESS");
		String USER_ID = request.getParameter("USER_ID");// 获取用户id

		String update_sql = "UPDATE USERS SET PHONE=?, WECHAT=?, FACEBOOK=?, ADDRESS=?  WHERE USER_ID=?";
		Object[] param = new Object[5];
		param[0] = PHONE;
		param[1] = WECHAT;
		param[2] = FACEBOOK;
		param[3] = ADDRESS;
		param[4] = USER_ID;

		Boolean result = WebUsersService.doUserRegister(update_sql, param);
		String json = "";
		if (result) {
			json = JsonService.ResultJson("1", "USER_ID", USER_ID);
		} else {
			json = JsonService.ResultJson("0", "USER_ID", USER_ID);
		}

		JsonService.ResponseJson(request, response, json);

	}

	public void addBankInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		if (user_id != null) {
			request.setAttribute("USER_ID", user_id);
			request.getRequestDispatcher("/zh_CN/company/yb_register_step_3.jsp").forward(request, response);
			return;
		}
		
		String BANK_USER = request.getParameter("BANK_USER");
		String BANK_CARD = request.getParameter("BANK_CARD");
		String BANK_NAME = request.getParameter("BANK_NAME");
		String ACCOUNT_NAME = request.getParameter("ACCOUNT_NAME");
		String USER_ID = request.getParameter("USER_ID");// 获取用户id

		String update_sql = "UPDATE USERS SET BANK_USER=?, BANK_CARD=?, BANK_NAME=?, ACCOUNT_NAME=?  WHERE USER_ID=?";
		Object[] param = new Object[5];
		param[0] = BANK_USER;
		param[1] = BANK_CARD;
		param[2] = BANK_NAME;
		param[3] = ACCOUNT_NAME;
		param[4] = USER_ID;

		Boolean result = WebUsersService.doUserRegister(update_sql, param);
		String json = "";
		if (result) {
			json = JsonService.ResultJson("1", "USER_ID", USER_ID);
		} else {
			json = JsonService.ResultJson("0", "USER_ID", USER_ID);
		}

		JsonService.ResponseJson(request, response, json);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
