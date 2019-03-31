package com.csh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.csh.dao.BaseDao;
import com.csh.domain.Store;

public class CreateShopServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;


	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		
		System.out.println("doPost----servlet-shopname!!");
		//Account ac = (Account) request.getSession().getAttribute("account");		
		String shopname = request.getParameter("shopname");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		/*从USERS表里查询到对应用户的USER_ID*/
		String sql1 = "SELECT * FROM USERS WHERE EMAIL=? AND PWD=?";
		Object[] param = new Object[2];
		param[0] = email;
		param[1] = password;
		//List<Account> list = AccountService.findAccount(sql1, param);
		//Account ac = list.get(0);
		//int userId= ac.getUSER_ID();/整合出错注释
		String message = "";
		/*已登录的用户是否已建过店铺，若建过直接登录，若未建店铺则添加*/
		String sql0="SELECT * FROM WEB_STORE_CREATE WHERE EMAIL=? AND PASSWORD=? ";
		Object[] param1=new Object[2];
		param1[0]=email;
		param1[1]=password;
		int result0=BaseDao.exeSql(sql0, param1);
		if(result0!=1){
			String sql2 = "INSERT INTO WEB_STORE_CREATE(STORE_NAME,EMAIL,PASSWORD,USER_ID) VALUES(?,?,?,?)";
			Object[] param2 = new Object[4];
			param2[0]=shopname;
			param2[1]=email;
			param2[2]=password;
			param2[3]= "1" ;    //userId; 整 合报错注 释
			int result = BaseDao.exeSql(sql2, param2);
			//List<Store> list2 = StoreService.findStore(sql1, param);
			//Store ac2 = list2.get(0);
			if(result == 1){
			//	request.getSession().setAttribute("account", ac);		
				request.getRequestDispatcher("/products/list?method=listProduct").forward(request, response);
			//message = "Create Success!";
			}else{
				message ="Failure to create!";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
			}
		}
		else{
			message ="你已建过店铺!请直接登录！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);	
		}
		
	}
	
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    	doPost(request,response);
	}

}
