package com.csh.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csh.dao.BaseDao;
import com.csh.domain.Customers;
import com.csh.service.JsonService;
import com.csh.util.Encoder;
import com.csh.service.CustomerUsersService;

import org.json.JSONArray;
import org.json.JSONException;

public class CustomerUserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
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

		String sql = "SELECT EMAIL,USER_ID,FIRST_NAME,LAST_NAME,USERNAME,STATUS,ROLE FROM USERS WHERE EMAIL=? AND PWD=?";
		Object[] param = new Object[2];
		param[0] = EMAIL;
		param[1] = PWD;

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql, param);

		String normal_json = "";
		String json = "";
		String msg = "";
		int ROLE = 0;

		String path = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath();// 项目名称
		String url = "";

		if (jsonarr != null && jsonarr.length() > 0) {

			String strBackUrl = request.getParameter("token");
			try {
				ROLE = Integer.parseInt(jsonarr.getJSONObject(0).getString("ROLE"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (ROLE == 9999) {
				request.getSession().setAttribute("users", jsonarr);
				if (strBackUrl != null && strBackUrl != "") {
					msg = "登录成功！";
					url = strBackUrl;
				} else {
					msg = "登录成功！";
					url = path + "/zh_CN/customer/yb_home_page.jsp";
				}
			} else {
				msg = "用户名或密码错误！！！";
				url = "";
			}

		} else {
			msg = "用户名或密码错误！！！";
			url = "";
		}
		json = JsonService.convertKeyValToJsonObject("url", url);
		normal_json = JsonService.getNormalJson(json, msg);
		JsonService.ResponseJson(request, response, normal_json);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
