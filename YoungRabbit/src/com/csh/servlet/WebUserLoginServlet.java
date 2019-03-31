package com.csh.servlet;
/*********************
 * Author shaohui-chen
 ******************** */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csh.domain.Users;
import com.csh.service.WebUsersService;

public class WebUserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		String method = request.getParameter("method");
		if (method == null) {
			request.getRequestDispatcher("/zh_CN/company/yb_user_login.jsp").forward(request, response);
			return;
		}
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");

		String sql = "SELECT * FROM USERS t where t.email=? and t.pwd=? and t.bak=1";
		Object[] param = new Object[2];
		param[0]=email;
		param[1]=pwd;

		List<Users> list = 	WebUsersService.findWebUsers(sql, param);
		if(list.size()== 0){
			String message = "�����������������µ�¼  ����  �ʻ�δͨ����ˣ���";
			request.setAttribute("message", message);		
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			
		
		}else{
			Users webusers = list.get(0);
			String status = webusers.getStatus();
			System.out.println( "login-dopost webusers-userid: " + webusers.getUserId() + "-status:" + webusers.getStatus()  );
			request.getSession().setAttribute("webusers", webusers);
			
			if ( status.equals(1+"") ){	
				
			   	 request.getRequestDispatcher("/jsp/main1.jsp").forward(request, response);
	
			   	 
			} else if ( status.equals(2+"") )	{
			
				request.getRequestDispatcher("/jsp/main2.jsp").forward(request, response);
			} else if ( status.equals(3+"") )	{
				
				request.getRequestDispatcher("/jsp/main3.jsp").forward(request, response);
			}else if ( status.equals(4+"") )	{
			
				request.getRequestDispatcher("/jsp/main4.jsp").forward(request, response);
								
			} else {
							
				request.getRequestDispatcher("/jsp/main1.jsp").forward(request, response);
				
			}
			}
		
	}
	
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    	doPost(request,response);
	}
	
	
	
	
}
