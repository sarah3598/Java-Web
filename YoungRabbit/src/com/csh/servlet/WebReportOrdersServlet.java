package com.csh.servlet;

import java.io.IOException;
import org.hibernate.Session;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;

import com.csh.domain.Users;
import com.csh.domain.WebOrders;

import com.csh.service.WebOrdersService;
import com.csh.service.WebUsersService;
import com.csh.util.PageModel;
/*********************
 * Author shaohui-chen
 ******************** */

public class WebReportOrdersServlet extends HttpServlet {

	//------------------------------
	private static final long serialVersionUID = 1L;

	   public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	    	doPost(request,response);
		}
	

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
	
		String method = request.getParameter("method");

		
		if(method.equals("listSearchWebOrders")){
			listSearchWebOrders(request,response);
	
			System.out.println("doPost-servlet-searchConditionReport");
		}
		if(method.equals("listWebOrders")){
			listWebOrders(request,response);
		
			System.out.println("doPost-servlet-listWebOrders");
		}
		
		if(method.equals("editWebOrders")){
			editWebOrders(request,response);
		}
		
		
		if(method.equals("deleteWebUsers")){
			deleteWebOrders(request,response);
		}
		
	}
	

	//编辑Orders
	 public void editWebOrders(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	 	
		 WebOrders weborders = (WebOrders) request.getSession().getAttribute("weborders");
	
	 	String orderId = request.getParameter("orderId");

	 	String sql = "select * from WEB_ORDERS t where t.orderId="+orderId;
	 	WebOrders webor = WebOrdersService.findWebOrders(sql, null).get(0);  // 由ID查订单信息
	 	request.getSession().setAttribute("weborders", webor);//前台显示信息用到,查到的信息后传;
	 	request.getSession().setAttribute("orderId", orderId);
	     request.getRequestDispatcher("/jsp/meetingregisteredit.jsp").forward(request, response);
	     
	 }  
	 
 
	public void findWebOrderList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Users user = (Users) request.getSession().getAttribute("webusers");
		if (user == null ) {
			request.getRequestDispatcher("/jsp/login.jsp").forward(request,
					response);
			return;
		}

		int pageSize = 30;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize;

		String listSql = "SELECT * FROM(SELECT ROWNUM RN,WEB_ORDERS.* FROM WEB_ORDERS ) WHERE RN BETWEEN ? AND ?";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(*) AS NUM FROM WEB_ORDERS " ;
		
		PageModel<WebOrders> page = WebOrdersService.findInventoryPageModel(
				currentPage, pageSize, listSql, rowSql, param);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/jsp/webreportorderslist.jsp").forward(request, response);

	}
	 
	public void listSearchWebOrders(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	 
	 	String reportYearSe = request.getParameter("reportYearSe");
	 	String reportMonthSe = request.getParameter("reportMonthSe");
	 	String reportStatusSe = request.getParameter("reportStatusSe");
     	String reportDaySe = request.getParameter("reportDaySe");

	 	Users user = (Users) request.getSession().getAttribute("webusers");
	
	    if( user == null ){
	 	   request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
	 	   return;
	    }

	    String searchtime = reportYearSe +reportMonthSe+reportDaySe;

	    String sqlwhere = "and HAPPEN_DATE = "+searchtime+" and STATUS like "+reportStatusSe;
	    String sql = "select * from WEB_ORDERS t where 1=1 ";
	    sql=sql+sqlwhere;

	    String rowSql = "select count(1) from WEB_ORDERS t where 1=1 ";
	    rowSql=rowSql+sqlwhere;
	    int pageSize = 10;
	    int currentPage = request.getParameter("currentPage")== null?1:Integer.parseInt(request.getParameter("currentPage"));

	    PageModel<WebOrders> page =WebOrdersService.findPageModel(currentPage, pageSize, sql,rowSql,null);
	    request.setAttribute("page", page);
	    request.getSession().setAttribute("webusers", user);
	    request.getRequestDispatcher("/jsp/webreportorderslist.jsp").forward(request, response);
	    
	 }
	
	 
	 //获取列表数据 (request,response)
	 public void listWebOrders(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	    Users user = (Users) request.getSession().getAttribute("webusers");
	    if( user == null ){
	 	   request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
	 	   return;
	    }
	    //  String userId = request.getParameter("userId");
	    String sql = "select * from WEB_ORDERS t where 1=1 ";
	    String rowSql = "select count(1) from WEB_ORDERS t where 1=1 ";
	    int pageSize = 10;
	    int currentPage = request.getParameter("currentPage")== null?1:Integer.parseInt(request.getParameter("currentPage"));

	    PageModel<WebOrders> page =WebOrdersService.findPageModel(currentPage, pageSize, sql,rowSql,null);
	    request.setAttribute("page", page);
	    request.getSession().setAttribute("webusers", user);
	    request.getRequestDispatcher("/jsp/webreportorderslist.jsp").forward(request, response);
	    
	 }
	
	 
	 //删除信息editWebOrders
	 public void deleteWebOrders(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	 	String orderId = request.getParameter("orderId");
	  	System.out.println( "login-dopost--enter deleteWebOrders-->" );
	 	int uid = WebOrdersService.deleteWebOrders(orderId);
	 	if(uid== -1){
	 		String message = "删除失败";
	 		request.setAttribute("message", message);
	 		request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
	 	}else{
	 		//request.getRequestDispatcher("/servlet/meetingusers?method=listMeetingUsers").forward(request, response);
	 		listWebOrders(request,response);
	 	}
	 	
	 }  
	
	
	
	
	
	
}
