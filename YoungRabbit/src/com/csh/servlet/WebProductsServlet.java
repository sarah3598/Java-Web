package com.csh.servlet;

import java.io.IOException;
import org.hibernate.Session;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;

import com.csh.domain.ProductInfo;
import com.csh.domain.Users;
import com.csh.domain.WebGoodsBasicInfo;

import com.csh.service.WebOrdersService;
import com.csh.service.WebProductsService;
import com.csh.service.WebUsersService;
import com.csh.util.PageModel;
/*********************
 * Author shaohui-chen
 ******************** */
public class WebProductsServlet extends HttpServlet {

	//------------------------------
	private static final long serialVersionUID = 1L;

	   public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	    	doPost(request,response);
		}
	

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
	
		String method = request.getParameter("method");
		
		if(method.equals("listGoods")){
			listGoods(request,response);
		
	
		}
		
		if(method.equals("editGood")){
			editGood(request,response);
		}
		
		
		if(method.equals("listProducts")){
			listProducts(request,response);

		}
		
		if(method.equals("showProduct")){
			showProduct(request,response);
		}
		
		
		if(method.equals("deleteWebUsers")){
			deleteWebOrders(request,response);
		}
		
	}
	
	//显示showGood
	 public void editGood(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	 	
		 	Users user = (Users) request.getSession().getAttribute("webusers");
		 	WebGoodsBasicInfo goodinfo =  ( WebGoodsBasicInfo )request.getSession().getAttribute("goodsinfo");

			if( user == null ){
		 	   request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
		 	   return;
		    } 
		    
		String goodId = request.getParameter("goodId");
	
	 	String sql = "select * from WEB_GOODS_BASIC_INFO t where t.GOOD_ID = "+goodId;
	 
	 	WebGoodsBasicInfo goodsinfo = WebProductsService.findGoodsInfo(sql, null).get(0);  // 由ID查商品信息
	
	 
	 	request.getSession().setAttribute("goodsinfo", goodsinfo);//前台显示信息用到,查到的信息后传;
	 	request.getSession().setAttribute("goodId", goodId);
	 	
	     request.getRequestDispatcher("/jsp/webGoodslist.jsp").forward(request, response);
	     
	 }  
	
	
	 //获取列表数据 (request,response)
	 public void listGoods(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	    String sql = "select * from WEB_GOODS_BASIC_INFO t where 1=1";
	    String rowSql = "select count(1) from WEB_GOODS_BASIC_INFO t where 1=1" ;
	    int pageSize = 10;
	    int currentPage = request.getParameter("currentPage")== null?1:Integer.parseInt(request.getParameter("currentPage"));

	   PageModel<WebGoodsBasicInfo> page = WebProductsService.findGoodsPageModel(currentPage, pageSize, sql,rowSql,null);

	    request.setAttribute("page", page);

	    request.getRequestDispatcher("/jsp/webgoodslist.jsp").forward(request, response);
	    
	 }
	
	 //获取列表数据 (request,response)
	 public void listProducts(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	 
	    String sql = "select * from WEB_GOODS_BASIC_INFO t where 1=1";
	    String rowSql = "select count(1) from WEB_GOODS_BASIC_INFO t where 1=1" ;
	    int pageSize = 10;
	    int currentPage = request.getParameter("currentPage")== null?1:Integer.parseInt(request.getParameter("currentPage"));
        PageModel<WebGoodsBasicInfo> page = WebProductsService.findGoodsPageModel(currentPage, pageSize, sql,rowSql,null);
	    request.setAttribute("page", page);
        request.getRequestDispatcher("/jsp/webproductslist.jsp").forward(request, response);
	    
	 }
	

	//显示Product
	 public void showProduct(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	 	
		 	Users user = (Users) request.getSession().getAttribute("webusers");
		 	WebGoodsBasicInfo goodinfo =  ( WebGoodsBasicInfo )request.getSession().getAttribute("goodsinfo");
		 	
			System.out.println( "login-dopost--enter showProduct-->"+user.getUserId()+"_"+user.getUserName() );
		    if( user == null ){
		 	   request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
		 	   return;
		    } 
		    
		String goodId = request.getParameter("goodId");
	
	 	String sql = "select * from WEB_GOODS_BASIC_INFO t where t.GOOD_ID = "+goodId;
	 
	 	WebGoodsBasicInfo goodsinfo = WebProductsService.findGoodsInfo(sql, null).get(0);  // 由ID查商品信息
	 	
	 	request.getSession().setAttribute("goodsinfo", goodsinfo);//前台显示信息用到,查到的信息后传;
	 	request.getSession().setAttribute("goodId", goodId);
	 	request.getRequestDispatcher("/jsp/webproductdetails.jsp").forward(request, response);
	     
	 }  
	 
	public void findWebOrderList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/jsp/webreportorderslist.jsp").forward(request, response);

	}
	 
	public void listSearchWebOrders(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    
	 }
	
	 
	 
	 //删除信息editWebOrders
	 public void deleteWebOrders(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

	 	
	 }  
	
	
	
	
	
	
}
