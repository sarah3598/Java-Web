/*********************
 * Author shaohui-chen
 ******************** */

package com.csh.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.csh.dao.BaseDao;
import com.csh.service.CompanyUsersService;
import com.csh.service.JsonService;
import com.csh.util.Encoder;

public class CompanyUserRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
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
	public void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		String FIRST_NAME = request.getParameter("FIRST_NAME");
		String LAST_NAME = request.getParameter("LAST_NAME");
		String EMAIL = request.getParameter("EMAIL");
		String PWD = request.getParameter("PWD");
		if(!(PWD.equals(""))){
			try {
				PWD =Encoder.EncoderByMd5(PWD);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String ROLE = request.getParameter("ROLE");// 注册时默认为实习状态
		String STATUS = "1";// 表示在此步提交后，个人信息已完成
		String USERNAME = LAST_NAME + FIRST_NAME;

		String select_email_sql = "SELECT * FROM USERS WHERE  EMAIL=?";
		Object[] params = new Object[1];
		params[0] = EMAIL;

		// 验证邮箱是否已被注册
		Boolean result = CompanyUsersService.verify_Email(select_email_sql, params);
		if (result) {
			String json = "";
			json = JsonService.ResultJson("0", "USER_ID", "");
			JsonService.ResponseJson(request, response, json);
			return;
		} else {
			String insert_sql = "INSERT INTO USERS(FIRST_NAME,LAST_NAME,EMAIL,PWD,ROLE,STATUS,USERNAME) VALUES(?,?,?,?,?,?,?)";
			Object[] param = new Object[7];
			param[0] = FIRST_NAME;
			param[1] = LAST_NAME;
			param[2] = EMAIL;
			param[3] = PWD;
			param[4] = ROLE;
			param[5] = STATUS;
			param[6] = USERNAME;

			Boolean flag = CompanyUsersService.doUserRegister(insert_sql, param);
			int USER_ID = -1;
			String json = "";
			if (flag) {
				String get_user_id_sql = "SELECT USER_ID FROM USERS WHERE EMAIL=?";
				Object[] param1 = new Object[1];
				param1[0] = EMAIL;
				USER_ID = CompanyUsersService.getUserID(get_user_id_sql, param1);
			}

			if (USER_ID > 0) {
				json = JsonService.ResultJson("1", "USER_ID", Integer.toString(USER_ID));
			} else {
				json = JsonService.ResultJson("0", "USER_ID", Integer.toString(USER_ID));
			}

			JsonService.ResponseJson(request, response, json);
		}
	}

	public void addContactInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get from servlet
		if (!LoginControlPublicServlet.LoginSessionValidate(request, response,1))
			return;
		String PHONE = request.getParameter("PHONE");
		String WECHAT = request.getParameter("WECHAT");
		String FACEBOOK = request.getParameter("FACEBOOK");
		String ADDRESS = request.getParameter("ADDRESS");
		String USER_ID = request.getParameter("USER_ID");// 获取用户id

		String update_sql = "UPDATE USERS SET PHONE=?, WECHAT=?, FACEBOOK=?, ADDRESS=? ,STATUS=? WHERE USER_ID=?";
		Object[] param = new Object[6];
		param[0] = PHONE;
		param[1] = WECHAT;
		param[2] = FACEBOOK;
		param[3] = ADDRESS;
		param[4] = 2;
		param[5] = USER_ID;

		int flag = BaseDao.exeSql(update_sql, param);
		
		String normal_json = "";
		String json = "";
		String msg = "";
		String path = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath();// 项目名称
		String url = "";
		if (flag > 0) {
			// 联络信息注册成功
			msg = "联络信息注册成功，请继续注册银行信息！";
			url = path + "/zh_CN/company/yb_register_step_3.jsp";
			json = JsonService.convertKeyValToJsonObject("url", url);
			normal_json = JsonService.getNormalJson(json, msg);
		} else {
			msg = "联络信息注册失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void addBankInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!LoginControlPublicServlet.LoginSessionValidate(request, response,1))
			return;

		String BANK_USER = request.getParameter("BANK_USER");
		String BANK_CARD = request.getParameter("BANK_CARD");
		String BANK_NAME = request.getParameter("BANK_NAME");
		String ACCOUNT_NAME = request.getParameter("ACCOUNT_NAME");
		String USER_ID = request.getParameter("USER_ID");// 获取用户id

		String update_sql = "UPDATE USERS SET BANK_USER=?, BANK_CARD=?, BANK_NAME=?, ACCOUNT_NAME=? ,STATUS=? WHERE USER_ID=?";
		Object[] param = new Object[6];
		param[0] = BANK_USER;
		param[1] = BANK_CARD;
		param[2] = BANK_NAME;
		param[3] = ACCOUNT_NAME;
		param[4] = 4;
		param[5] = USER_ID;

		int flag = BaseDao.exeSql(update_sql, param);
		
		String normal_json = "";
		String json = "";
		String msg = "";
		String path = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath();// 项目名称
		String url = "";
		if (flag > 0) {
			// 联络信息注册成功
			msg = "银行信息注册成功！";
			url = path + "/zh_CN/company/yb_dashboard.jsp";
			json = JsonService.convertKeyValToJsonObject("url", url);
			normal_json = JsonService.getNormalJson(json, msg);
		} else {
			msg = "银行信息注册失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}

		JsonService.ResponseJson(request, response, normal_json);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
