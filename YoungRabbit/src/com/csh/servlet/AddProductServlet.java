package com.csh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csh.dao.BaseDao;

/*
 * Author yuanshuo
 * */

public class AddProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	//添加商品信息
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		System.out.println("doPost----servlet-addproduct!!");
		
		String title = request.getParameter("title");
		String price = request.getParameter("price");
		String description=request.getParameter("description");
		String taxmark=request.getParameter("tax_mark");
		String vendor = request.getParameter("vendor");
		String onlinestore=request.getParameter("onlinestore");
		String comparePrice=request.getParameter("comparePrice");
		String sku=request.getParameter("sku");
		String barcode=request.getParameter("barcode");
		String inventory_policy=request.getParameter("inventory_policy");
		String quantity=request.getParameter("quantity");
		String weight_sku=request.getParameter("weight_sku");
		String service=request.getParameter("service");
		String shipping_mark=request.getParameter("shipping_mark");
		String sql = "INSERT INTO WEB_GOODS_BASIC_INFO(TITLE,PRICING,DESCRIPTION,TAX_MARK,COMPARE_AT_PRICE,ONLINE_STORE) VALUES(?,?,?,?,?,?)";
		Object[] param = new Object[6];
		param[0]=title;
		param[1]=price;
		param[2]=description;
		param[3]=taxmark;
		param[4]=comparePrice;
		param[5]=onlinestore;
		int result = BaseDao.exeSql(sql, param);
		String sql2="INSERT INTO WEB_GOODS_KINDS(ORGA_NAME) VALUES(?)";
		Object[] param2 = new Object[1];
		param2[0]=vendor;
		int result2 = BaseDao.exeSql(sql2, param2);
		String sql3="INSERT INTO WEB_GOODS_INVENTORY(SKU,BARCODE,INVENTORY_POLICY,QUANTITY,WEIGHT_SKU,SERVICE_WAYS,SHIPPING_MARK) VALUES(?,?,?,?,?,?,?)";
		Object[] param3 = new Object[7];
		param3[0]=sku;
		param3[1]=barcode;
		param3[2]=inventory_policy;
		param3[3]=quantity;
		param3[4]=weight_sku;
		param3[5]=0;
		param3[6]=shipping_mark;
		int result3 = BaseDao.exeSql(sql3, param3);
		
		String message = "";
		if(result==1&&result2==1&&result3==1){
			message = "Save Success!";
		}else{
			message ="Failure to Save!";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		
		
	}
	
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    	doPost(request,response);
	}


}
