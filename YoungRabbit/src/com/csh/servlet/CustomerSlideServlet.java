package com.csh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.csh.service.SlideService;




/**
 * @author wendy
 *
 */
public class CustomerSlideServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		System.out.println("test");
		getSlideList(request, response);
	}

	

	private void getSlideList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize;// include the end row

		String listSql = "select * from SLIDER t where 1=1 ";
		String rowSql = "select count(1) from SLIDER t where 1=1 ";

		String rs = SlideService.getSlideListRs(startRow, endRow, listSql, null);
		int rows = SlideService.getSlideListRows(rowSql, null);

		if (rs != null) {
			
			request.setAttribute("slider_list", rs);
			System.out.println("test2");
			request.setAttribute("rows", rows);

		} else {
			rs = null;
			rows = 0;
		}
		request.getRequestDispatcher("/zh_CN/customer/yb_home_page.jsp").forward(request, response);

	}

	
	
}
