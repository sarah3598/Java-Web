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

import com.csh.service.CustomerUsersService;
import com.csh.service.JsonService;
import com.csh.util.Encoder;

public class CustomerUserRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String step = request.getParameter("step");
		if (step == null) {
			request.getRequestDispatcher("/zh_CN/customer/yb_register_step_1.jsp").forward(request, response);
			return;
		}
		switch (step) {
		case "1":// create a user
			createUser(request, response);
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
		
		if(!(PWD.equals(""))){
			try {
				PWD =Encoder.EncoderByMd5(PWD);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String ROLE = "9999";// 普通用户注册 ROLE=9999
		String STATUS = "1";// 表示在此步提交后，个人信息已完成
		String USERNAME = LAST_NAME + FIRST_NAME;

		String select_email_sql = "SELECT * FROM USERS WHERE EMAIL=?";
		Object[] params = new Object[1];
		params[0] = EMAIL;

		// 验证邮箱是否已被注册
		Boolean result = CustomerUsersService.verify_Email(select_email_sql, params);
		if (result) {
			String json = "";
			String msg="该邮箱已被注册！";
			json = JsonService.getErrorMsgNormalJson(msg);
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

			Boolean flag = CustomerUsersService.doUserRegister(insert_sql, param);
			
			String json = "";
			String msg="";
			if (flag) {
				
				msg="注册成功！";
				json = JsonService.getOperateResultNormalJson("true", null, msg);
			} else {
				msg="注册失败！";
				json = JsonService.getErrorMsgNormalJson(msg);
			}

			JsonService.ResponseJson(request, response, json);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
