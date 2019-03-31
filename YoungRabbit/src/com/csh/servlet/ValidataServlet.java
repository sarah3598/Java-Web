package com.csh.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csh.dao.BaseDao;
public class ValidataServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;


	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		String method = request.getParameter("method");
		if("validateEmail".equals(method)){
			validateEmail(request,response);
		}
		if("validateCard".equals(method)){
			validateCard(request,response);
		}
		
	}
	
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    	doPost(request,response);
	}
    
    //验证email
    public void validateEmail(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    	String email = request.getParameter("email");
    	String sql = "select count(1) from account t where t.accountEmail='"+email+"'";
    	int i = BaseDao.getRowCount(sql, null);
    	response.setCharacterEncoding("UTF-8");  
    	response.setContentType("application/json; charset=utf-8"); 
    	response.getWriter().print(i);
	}
    
    //验证身份证号
    public void validateCard (HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    	String shenfenzhengNo = request.getParameter("shenfenzhengNo");
    	String sql = "SELECT count(1) from  enroll_person t where t.shenfenzheng_No='"+shenfenzhengNo+"'";
    	int i = BaseDao.getRowCount(sql, null);
    	response.setCharacterEncoding("UTF-8");  
    	response.setContentType("application/json; charset=utf-8"); 
    	response.getWriter().print(i);
	}
}
