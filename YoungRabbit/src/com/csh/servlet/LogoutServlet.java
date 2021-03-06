package com.csh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String OP = request.getParameter("OP");
		String path = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath();// 项目名称
		switch (OP) {
		case "1":
			request.getSession().invalidate();
			response.sendRedirect(path+"/zh_CN/company/yb_user_login.jsp");
			break;
		case "2":
			request.getSession().invalidate();
			response.sendRedirect(path+"/zh_CN/customer/yb_user_login.jsp");
			break;
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
