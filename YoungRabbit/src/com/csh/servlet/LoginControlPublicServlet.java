package com.csh.servlet;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginControlPublicServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// add by xiaopan
	public static Boolean LoginSessionIsNull(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONArray user_arr = (JSONArray) request.getSession().getAttribute("users");
		if (user_arr == null || user_arr.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	// add by xiaopan
	public static Boolean LoginSessionValidate(HttpServletRequest request, HttpServletResponse response, int flag) throws IOException {
		JSONArray user_arr = (JSONArray) request.getSession().getAttribute("users");
		String path = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath();// 项目名称

		if (user_arr == null || user_arr.length() == 0) {
			String strBackUrl = path + request.getServletPath(); // 请求页面或其他地址
			String params=	request.getQueryString();
			if(!(params==null||params.equals("")))
				strBackUrl+= "?" + (request.getQueryString()); // 参数
			if (flag == 1) {//员工用户
				response.sendRedirect(path + "/zh_CN/company/yb_user_login.jsp?token=" + strBackUrl);
			} else if (flag == 2){//顾客
				response.sendRedirect(path + "/zh_CN/customer/yb_user_login.jsp?token=" + strBackUrl);
			}

			return false;
		} else {
			return true;
		}
	}

	// add by xiaopan
	public static Boolean LoginSessionValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONArray user_arr = (JSONArray) request.getSession().getAttribute("users");
		String path = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath();// 项目名称

		if (user_arr == null || user_arr.length() == 0) {
			String strBackUrl = path + request.getServletPath() // 请求页面或其他地址
					+ "?" + (request.getQueryString()); // 参数

			response.sendRedirect(path + "/zh_CN/customer/yb_user_login.jsp?token=" + strBackUrl);
			return false;
		} else {
			return true;
		}
	}

	// add by xiaopan
	public static Object getSessionKeyValue(HttpServletRequest request, HttpServletResponse response, String key) throws IOException {
		JSONArray user_arr = (JSONArray) request.getSession().getAttribute("users");
		try {
			return user_arr.getJSONObject(0).get(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// add by xiaopan
	public static Object getSessionKeyValue(HttpServletRequest request, String key) {
		JSONArray user_arr = (JSONArray) request.getSession().getAttribute("users");
		try {
			return user_arr.getJSONObject(0).get(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
