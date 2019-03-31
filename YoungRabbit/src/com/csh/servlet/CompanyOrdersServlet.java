package com.csh.servlet;

import java.io.IOException;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.domain.WebOrders;
import com.csh.service.CompanyProductService;
import com.csh.service.JsonService;
import com.csh.service.WebOrdersService;
import com.csh.service.WebUsersService;
import com.csh.util.DateUtil;
import com.csh.util.PageModel;
import com.csh.util.Pagination;

/*********************
 * Author shaohui-chen
 */

public class CompanyOrdersServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		if (!LoginControlPublicServlet.LoginSessionValidate(request, response, 1))
			return;

		String method = request.getParameter("method");
		switch (method) {
		case "order_list":// 订单列表
			getOrderList(request, response);
			break;
		case "update_order":// 更新订单
			updateOrder(request, response);
			break;
		case "delete_order":// 删除订单
			deleteOrder(request, response);
			break;

		case "order_no_pay_list":// 未支付订单列表
			getOrderNoPayList(request, response);
			break;
		case "order_already_pay_list":// 已支付订单列表
			getOrderAlreadyPayList(request, response);
			break;
		case "order_wait_delivery_list":// 待发货(打包中)订单列表
			getOrderWaitDeliveryList(request, response);
			break;
		case "order_on_delivery_list":// 配送中订单列表
			getOrderOnDeliveryList(request, response);
			break;
		case "order_received_list":// 已收货订单列表
			getOrderReceivedList(request, response);
			break;
		case "order_failed_pay_list":// 支付失败订单列表
			getOrderFailedPayList(request, response);
			break;
		case "order_cancel_list":// 已取消订单列表,普通取消，无需退款的
			getOrderCancelList(request, response);
			break;
		case "order_need_check_cancel_list":// 待处理的已取消订单列表，需退款的
			getOrderNeedCheckCancelList(request, response);
			break;
		case "996":// 更新订单
			updateOrder(request, response);
			break;
		case "997":// 所有订单
			getOrderList(request, response);
			break;
		case "998":// 查询订单
			getQueryOrderList(request, response);
			break;
		case "999":// 订单分页
			paging(request, response);
			break;
		}

		// if (method.equals("listSearchWebOrders")) {
		// listSearchWebOrders(request, response);
		//
		// System.out.println("doPost-servlet-searchConditionReport");
		// }
		// if (method.equals("listWebOrders")) {
		// listWebOrders(request, response);
		//
		// System.out.println("doPost-servlet-listWebOrders");
		// }
		//
		// if (method.equals("editWebOrders")) {
		// editWebOrders(request, response);
		// }
		//
		// if (method.equals("deleteWebUsers")) {
		// deleteWebOrders(request, response);
		// }

	}

	// add by xiaopan
	public void paging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getPagingData(request);
		request.setAttribute("order_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_list.jsp").forward(request, response);
	}

	public JSONArray getPagingData(HttpServletRequest request) throws NumberFormatException {

		Pagination paging = (Pagination) request.getSession().getAttribute("page");

		if (paging == null) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getOrderListData(request);
			return jsonarr1;
		}

		String sql = paging.getCurrentSql();

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String rsql = "SELECT ROWNUM AS RN,C.* FROM (" + sql + ")C";

		String listSql = "SELECT * FROM (" + rsql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM (" + rsql + ")";

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// paging
		Pagination page = (Pagination) request.getSession().getAttribute("page");
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);

		request.setAttribute("page", page);
		request.getSession().setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ORDER_ID = request.getParameter("ORDER_ID");// 唯一性

		// 删除订单表
		String sql1 = "DELETE FROM WEB_ORDERS WHERE ORDER_ID=?";
		Object[] param1 = new Object[1];
		param1[0] = ORDER_ID;
		int flag1 = BaseDao.exeSql(sql1, param1);

		// 取出销售记录销售表
		String sql2 = "SELECT SALES_ID FROM SALES_2017 WHERE ORDER_ID=?";
		Object[] param2 = new Object[1];
		param2[0] = ORDER_ID;

		String SALES_ID = "";
		JSONArray arr2 = BaseDao.getRsJSONArray(sql2, param2);
		if (arr2 == null || arr2.length() == 0) {

		} else {
			try {
				SALES_ID = arr2.getJSONObject(0).getString("SALES_ID");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 删除销售记录
		String sql3 = "DELETE FROM SALES_2017 WHERE ORDER_ID=?";
		Object[] param3 = new Object[1];
		param3[0] = ORDER_ID;
		int flag3 = BaseDao.exeSql(sql3, param3);

		// 删除佣金记录
		String sql4 = "DELETE FROM BROKERAGE_2017 WHERE SALES_ID=?";
		Object[] param4 = new Object[1];
		param4[0] = SALES_ID;
		int flag4 = BaseDao.exeSql(sql4, param4);

		String normal_json = "";
		String msg = "";
		// 一个订单的基本信息更新成功
		if (flag1 > 0 && flag3 >= 0 && flag4 >= 0) {

			msg = "订单删除成功！";
			normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

		} else {
			msg = "订单删除失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);

	}

	// add by xiaopan
	public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ORDER_ID = request.getParameter("ORDER_ID");// 唯一性
		String ORDER_TOTAL_MONEY = request.getParameter("ORDER_TOTAL_MONEY");// 订单总价
		String ORDER_STATUS = request.getParameter("ORDER_STATUS");// 订单状态
		String ORDER_REMARKS = request.getParameter("ORDER_REMARKS");// 订单备注
		
		String ORDER_NO = request.getParameter("ORDER_NO");// 
		String TITLE = request.getParameter("TITLE");//
		String PRODUCT_PRICE = request.getParameter("PRODUCT_PRICE");// 
		String BUY_NUM = request.getParameter("BUY_NUM");//
		
		String SHIPPING_PRICE = request.getParameter("SHIPPING_PRICE");// 
		String DISCOUNT_MONEY = request.getParameter("DISCOUNT_MONEY");// 
		String INVOICE_CLIENT = request.getParameter("INVOICE_CLIENT");// 
		String ADDRESS = request.getParameter("ADDRESS");// 
		
		String msg="";
		String normal_json="";
		
		if(ORDER_ID==null||ORDER_ID.equals("")){
			msg="操作失败！";
			normal_json=JsonService.getOperateResultNormalJson("true",null,msg);
		}else{
			
			String sql=" UPDATE WEB_ORDERS SET ORDER_ID="+ORDER_ID;
			
			if(!(ORDER_TOTAL_MONEY==null||ORDER_TOTAL_MONEY.equals(""))){
				sql+=", ORDER_TOTAL_MONEY="+ORDER_TOTAL_MONEY;
			}
			
			if(!(ORDER_STATUS==null||ORDER_STATUS.equals(""))){
				sql+=", ORDER_STATUS="+ORDER_STATUS;
				
				if(ORDER_STATUS.equals("4"))//若发货，记录发货时间
					sql+=", DELIVERY_TIME="+DateUtil.getTimastampString();
				
			}
			
			if(!(ORDER_REMARKS==null||ORDER_REMARKS.replace(" ", "").equals(""))){
				sql+=", ORDER_REMARKS='"+ORDER_REMARKS+"'";
			}
			
//			if(!(ORDER_NO==null||ORDER_NO.replace(" ", "").equals(""))){
//				sql+=", ORDER_NO="+ORDER_NO;
//			}
//			
//			if(!(TITLE==null||TITLE.replace(" ", "").equals(""))){
//				sql+=", TITLE='"+TITLE+"'";
//			}
//			
//			if(!(PRODUCT_PRICE==null||PRODUCT_PRICE.replace(" ", "").equals(""))){
//				sql+=", PRODUCT_PRICE="+PRODUCT_PRICE;
//			}
//			
//			if(!(BUY_NUM==null||BUY_NUM.replace(" ", "").equals(""))){
//				sql+=", BUY_NUM="+BUY_NUM;
//			}
//			
//			if(!(SHIPPING_PRICE==null||SHIPPING_PRICE.replace(" ", "").equals(""))){
//				sql+=", SHIPPING_PRICE="+SHIPPING_PRICE;
//			}
//			
//			if(!(DISCOUNT_MONEY==null||DISCOUNT_MONEY.replace(" ", "").equals(""))){
//				sql+=", DISCOUNT_MONEY="+DISCOUNT_MONEY;
//			}
//			
//			if(!(INVOICE_CLIENT==null||INVOICE_CLIENT.replace(" ", "").equals(""))){
//				sql+=", INVOICE_CLIENT="+INVOICE_CLIENT;
//			}
//			
//			if(!(ADDRESS==null||ADDRESS.replace(" ", "").equals(""))){
//				sql+=", ADDRESS="+ADDRESS;
//			}
			
			sql+=" WHERE ORDER_ID="+ORDER_ID;
			
			int flag1 = BaseDao.exeSql(sql, null);
			
			if(flag1>0){
				msg="操作成功！";
				normal_json=JsonService.getOperateResultNormalJson("true",null,msg);
			}
		}
		JsonService.ResponseJson(request,response,normal_json);					
		
	}
	
	

	// add by xiaopan
	public void updateNoPayOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, ServletException {
		// 未支付订单的修改，允许修改订单总价

		Object LOGIN_ACCOUNT = LoginControlPublicServlet.getSessionKeyValue(request, response, "EMAIL");

		String ORDER_ID = request.getParameter("ORDER_ID");// 唯一性

		String ORDER_TOTAL_MONEY = request.getParameter("ORDER_TOTAL_MONEY");

		// update order info
		String sql1 = "UPDATE WEB_ORDERS SET LOGIN_ACCOUNT=?,ORDER_TOTAL_MONEY=? WHERE ORDER_ID=?";
		Object[] param1 = new Object[3];
		param1[0] = LOGIN_ACCOUNT;
		param1[1] = ORDER_TOTAL_MONEY;
		param1[2] = ORDER_ID;

		int flag1 = BaseDao.exeSql(sql1, param1);
		String normal_json = "";
		String msg = "";
		// 一个订单的基本信息更新成功
		if (flag1 > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getOrderNoPayListData(request);
			msg = "订单更新成功！";
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else {
			msg = "订单更新失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public void doDelivery(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, ServletException {
		// 未支付订单的修改，允许修改订单总价

		Object LOGIN_ACCOUNT = LoginControlPublicServlet.getSessionKeyValue(request, response, "EMAIL");
		String ORDER_ID = request.getParameter("ORDER_ID");// 唯一性

		// update order info
		String sql1 = "UPDATE WEB_ORDERS SET LOGIN_ACCOUNT=?,ORDER_STATUS=? WHERE ORDER_ID=?";
		Object[] param1 = new Object[3];
		param1[0] = LOGIN_ACCOUNT;
		param1[1] = 4;// 已发货，显示配送中
		param1[2] = ORDER_ID;

		int flag1 = BaseDao.exeSql(sql1, param1);
		String normal_json = "";
		String msg = "";
		// 一个订单的基本信息更新成功
		if (flag1 > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getOrderWaitDeliveryListData(request);
			msg = "发货成功！";
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else {
			msg = "发货失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public void updatePayOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, ServletException {
		// 未支付订单的修改，允许修改订单总价

		Object LOGIN_ACCOUNT = LoginControlPublicServlet.getSessionKeyValue(request, response, "EMAIL");

		String ORDER_ID = request.getParameter("ORDER_ID");// 唯一性

		String PRODUCT_PRICE = request.getParameter("PRODUCT_PRICE");
		String BUY_NUM = request.getParameter("BUY_NUM");
		String SHIPPING_PRICE = request.getParameter("SHIPPING_PRICE");
		String DISCOUNT_MONEY = request.getParameter("DISCOUNT_MONEY");
		String ORDER_TOTAL_MONEY = request.getParameter("ORDER_TOTAL_MONEY");
		String ORDER_STATUS = request.getParameter("ORDER_STATUS");

		// update order info
		String sql1 = "UPDATE WEB_ORDERS SET LOGIN_ACCOUNT=?,PRODUCT_PRICE=?,BUY_NUM=?,SHIPPING_PRICE=?,DISCOUNT_MONEY=?,ORDER_TOTAL_MONEY=?,ORDER_STATUS=? WHERE ORDER_ID=?";
		Object[] param1 = new Object[9];
		param1[0] = LOGIN_ACCOUNT;
		param1[1] = PRODUCT_PRICE;
		param1[2] = BUY_NUM;
		param1[3] = SHIPPING_PRICE;
		param1[4] = DISCOUNT_MONEY;
		param1[5] = PRODUCT_PRICE;
		param1[6] = ORDER_TOTAL_MONEY;
		param1[7] = ORDER_STATUS;
		param1[8] = ORDER_ID;

		int flag1 = BaseDao.exeSql(sql1, param1);
		String normal_json = "";
		String msg = "";
		// 一个订单的基本信息更新成功
		if (flag1 > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getOrderListData(request);
			msg = "订单更新成功！";
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else {
			msg = "订单更新失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public void getOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getOrderListData(request);
		request.setAttribute("order_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_list.jsp").forward(request, response);

	}

	// add by xiaopan
	public void getOrderNoPayList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getOrderNoPayListData(request);
		request.setAttribute("order_no_pay_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_no_pay_list.jsp").forward(request, response);
	}

	// add by xiaopan
	public void getQueryOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getQueryOrderListData(request);
		request.setAttribute("order_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_list.jsp").forward(request, response);
	}

	// add by xiaopan
	public void getOrderAlreadyPayList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getOrderAlredayPayListData(request);
		request.setAttribute("order_already_pay_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_already_pay_list.jsp").forward(request, response);
	}

	// add by xiaopan
	public void getOrderWaitDeliveryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getOrderWaitDeliveryListData(request);
		request.setAttribute("order_wait_delivery_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_wait_delivery_list.jsp").forward(request, response);
	}

	// add by xiaopan
	public void getOrderReceivedList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getOrderReceivedListData(request);
		request.setAttribute("order_received_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_received_list.jsp").forward(request, response);
	}

	// add by xiaopan
	public void getOrderOnDeliveryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getOrderOnDeliveryListData(request);
		request.setAttribute("order_on_delivery_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_on_delivery_list.jsp").forward(request, response);
	}

	// add by xiaopan
	public void getOrderFailedPayList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getOrderFailedPayListData(request);
		request.setAttribute("order_failed_pay_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_failed_pay_list.jsp").forward(request, response);
	}

	// add by xiaopan
	public void getOrderCancelList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getOrderCancelListData(request);
		request.setAttribute("order_cancel_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_cancel_list.jsp").forward(request, response);
	}

	// add by xiaopan
	public void getOrderNeedCheckCancelList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getOrderNeedCheckCancelListData(request);
		request.setAttribute("order_need_check_cancel_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_need_check_cancel_list.jsp").forward(request, response);
	}

	// add by xiaopan
	public JSONArray getOrderListData(HttpServletRequest request) throws NumberFormatException {

		String ORDER_STATUS = request.getParameter("ORDER_STATUS");
		if (ORDER_STATUS == null || ORDER_STATUS.equals("")) {// 为空时，默认显示所有
			ORDER_STATUS = " ORDER_STATUS=0 OR ORDER_STATUS=1 OR ORDER_STATUS=2 OR ORDER_STATUS=3 OR ORDER_STATUS=4 OR ORDER_STATUS=5";
		}

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		// no pay ORDER_STATUS=1&PAY_STATUS=0
		String sql = "SELECT A.*,B.TITLE FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID ";
		String osql = "SELECT * FROM (" + sql + ") WHERE " + ORDER_STATUS + " ORDER BY ORDER_ID DESC";
		String rsql = "SELECT ROWNUM AS RN,C.* FROM (" + osql + ")C";

		String listSql = "SELECT * FROM (" + rsql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM (" + rsql + ")";

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		page.setCurrentSql(osql);

		request.setAttribute("page", page);
		request.getSession().setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getOrderAlredayPayListData(HttpServletRequest request) throws NumberFormatException {

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		// already pay
		// ORDER_STATUS=2&PAY_STATUS=1
		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.*,B.TITLE FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=? ORDER BY A.ORDER_ID DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[4];
		param[0] = 2;// ORDER_STATUS=2
		param[1] = 1;// PAY_STATUS=1
		param[2] = startRow;
		param[3] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=?";
		Object[] param1 = new Object[2];
		param1[0] = 2;// ORDER_STATUS=2
		param1[1] = 1;// PAY_STATUS=1

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, param1);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getOrderWaitDeliveryListData(HttpServletRequest request) throws NumberFormatException {

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		// wait delivery
		// ORDER_STATUS=3&PAY_STATUS=1
		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.*,B.TITLE,T.USER_NAME,T.AREA_ID1,T.AREA_ID2,T.AREA_ID3,T.ADDRESS,T.USER_PHONE,T.USER_TEL FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID LEFT JOIN USER_ADDRESS T ON A.ADDRESS_ID=T.ADDRESS_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=? ORDER BY A.ORDER_ID DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[4];
		param[0] = 3;// ORDER_STATUS=3
		param[1] = 1;// PAY_STATUS=1
		param[2] = startRow;
		param[3] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=?";
		Object[] param1 = new Object[2];
		param1[0] = 3;// ORDER_STATUS=3
		param1[1] = 1;// PAY_STATUS=1

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, param1);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getOrderReceivedListData(HttpServletRequest request) throws NumberFormatException {

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		// wait delivery
		// ORDER_STATUS=5&PAY_STATUS=1
		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.*,B.TITLE FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=? ORDER BY A.ORDER_ID DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[4];
		param[0] = 5;// ORDER_STATUS=5
		param[1] = 1;// PAY_STATUS=1
		param[2] = startRow;
		param[3] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=?";
		Object[] param1 = new Object[2];
		param1[0] = 5;// ORDER_STATUS=5
		param1[1] = 1;// PAY_STATUS=1

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, param1);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getOrderOnDeliveryListData(HttpServletRequest request) throws NumberFormatException {

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		// on delivery
		// ORDER_STATUS=4&PAY_STATUS=1
		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.*,B.TITLE,T.USER_NAME,T.AREA_ID1,T.AREA_ID2,T.AREA_ID3,T.ADDRESS,T.USER_PHONE,T.USER_TEL FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID LEFT JOIN USER_ADDRESS T ON A.ADDRESS_ID=T.ADDRESS_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=? ORDER BY A.ORDER_ID DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[4];
		param[0] = 4;// ORDER_STATUS=4
		param[1] = 1;// PAY_STATUS=1
		param[2] = startRow;
		param[3] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=?";
		Object[] param1 = new Object[2];
		param1[0] = 4;// ORDER_STATUS=4
		param1[1] = 1;// PAY_STATUS=1

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, param1);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getOrderNoPayListData(HttpServletRequest request) throws NumberFormatException {

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		// no pay ORDER_STATUS=1&PAY_STATUS=0
		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.*,B.TITLE FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=? ORDER BY A.ORDER_ID DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[4];
		param[0] = 1;// ORDER_STATUS=1
		param[1] = 0;// PAY_STATUS=0
		param[2] = startRow;
		param[3] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=?";
		Object[] param1 = new Object[2];
		param1[0] = 1;// ORDER_STATUS=1
		param1[1] = 0;// PAY_STATUS=0

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, param1);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getQueryOrderListData(HttpServletRequest request) throws NumberFormatException {

		String ORDER_STATUS = request.getParameter("ORDER_STATUS");
		if (ORDER_STATUS == null || ORDER_STATUS.equals("")) {// 为空时，默认显示所有
			ORDER_STATUS = " ORDER_STATUS=0 OR ORDER_STATUS=1 OR ORDER_STATUS=2 OR ORDER_STATUS=3 OR ORDER_STATUS=4 OR ORDER_STATUS=5";
		}

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		// no pay ORDER_STATUS=1&PAY_STATUS=0
		String sql = "SELECT A.*,B.TITLE FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID ";
		String osql = "SELECT * FROM (" + sql + ") WHERE " + ORDER_STATUS + " ORDER BY ORDER_ID DESC";
		String rsql = "SELECT ROWNUM AS RN,C.* FROM (" + osql + ")C";

		String listSql = "SELECT * FROM (" + rsql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM (" + rsql + ")";

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		page.setCurrentSql(osql);
		page.setPageParams(ORDER_STATUS.replace("ORDER_STATUS=", "").replace("OR", ",").replace(" ", ""));
		request.setAttribute("page", page);
		request.getSession().setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getOrderFailedPayListData(HttpServletRequest request) throws NumberFormatException {

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		// failed pay PAY_STATUS=-1
		// ORDER_STATUS=1&PAY_STATUS=-1
		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.*,B.TITLE FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=? ORDER BY A.ORDER_ID DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[4];
		param[0] = 1;// ORDER_STATUS=1
		param[1] = -1;// PAY_STATUS=-1
		param[2] = startRow;
		param[3] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=?";
		Object[] param1 = new Object[2];
		param1[0] = 1;// ORDER_STATUS=1
		param1[1] = -1;// PAY_STATUS=-1

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, param1);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getOrderCancelListData(HttpServletRequest request) throws NumberFormatException {

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		// cancel PAY_STATUS=-1
		// ORDER_STATUS=0&PAY_STATUS=-1||0&CANCEL_SRC_STATUS=-1
		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.*,B.TITLE FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND (A.PAY_STATUS=? OR A.PAY_STATUS=?) AND A.CANCEL_SRC_STATUS=? ORDER BY A.ORDER_ID DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[6];
		param[0] = 0;// ORDER_STATUS=0
		param[1] = -1;// PAY_STATUS=-1
		param[2] = 0;// PAY_STATUS=0
		param[3] = -1;// CANCEL_SRC_STATUS=-1
		param[4] = startRow;
		param[5] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND (A.PAY_STATUS=? OR A.PAY_STATUS=?) AND A.CANCEL_SRC_STATUS=?";
		Object[] param1 = new Object[4];
		param1[0] = 0;// ORDER_STATUS=0
		param1[1] = -1;// PAY_STATUS=-1
		param1[2] = 0;// PAY_STATUS=0
		param1[3] = -1;// CANCEL_SRC_STATUS=-1

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, param1);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getOrderNeedCheckCancelListData(HttpServletRequest request) throws NumberFormatException {

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		// cancel
		// ORDER_STATUS=0&PAY_STATUS=1&CANCEL_SRC_STATUS<=-2
		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.*,B.TITLE FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=? AND A.CANCEL_SRC_STATUS<=? ORDER BY A.ORDER_ID DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[5];
		param[0] = 0;// ORDER_STATUS=0
		param[1] = 1;// PAY_STATUS=-1
		param[2] = -2;// CANCEL_SRC_STATUS<=-2
		param[3] = startRow;
		param[4] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.ORDER_STATUS=? AND A.PAY_STATUS=? AND A.CANCEL_SRC_STATUS<=?";
		Object[] param1 = new Object[3];
		param[0] = 0;// ORDER_STATUS=0
		param[1] = 1;// PAY_STATUS=-1
		param[2] = -2;// CANCEL_SRC_STATUS<=-2

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, param1);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	// �༭Orders
	public void editWebOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		WebOrders weborders = (WebOrders) request.getSession().getAttribute("weborders");

		String orderId = request.getParameter("orderId");

		String sql = "select * from WEB_ORDERS t where t.orderId=" + orderId;
		WebOrders webor = WebOrdersService.findWebOrders(sql, null).get(0); // ��ID�鶩����Ϣ
		request.getSession().setAttribute("weborders", webor);// ǰ̨��ʾ��Ϣ�õ�,�鵽����Ϣ��;
		request.getSession().setAttribute("orderId", orderId);
		request.getRequestDispatcher("/jsp/meetingregisteredit.jsp").forward(request, response);

	}

	public void findWebOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users user = (Users) request.getSession().getAttribute("webusers");
		if (user == null) {
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			return;
		}

		int pageSize = 30;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize;

		String listSql = "SELECT * FROM(SELECT ROWNUM RN,WEB_ORDERS.* FROM WEB_ORDERS ) WHERE RN BETWEEN ? AND ?";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(*) AS NUM FROM WEB_ORDERS ";

		PageModel<WebOrders> page = WebOrdersService.findInventoryPageModel(currentPage, pageSize, listSql, rowSql, param);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/jsp/webreportorderslist.jsp").forward(request, response);

	}

	public void listSearchWebOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String reportYearSe = request.getParameter("reportYearSe");
		String reportMonthSe = request.getParameter("reportMonthSe");
		String reportStatusSe = request.getParameter("reportStatusSe");
		String reportDaySe = request.getParameter("reportDaySe");

		Users user = (Users) request.getSession().getAttribute("webusers");

		if (user == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		String searchtime = reportYearSe + reportMonthSe + reportDaySe;

		String sqlwhere = "and HAPPEN_DATE = " + searchtime + " and STATUS like " + reportStatusSe;
		String sql = "select * from WEB_ORDERS t where 1=1 ";
		sql = sql + sqlwhere;

		String rowSql = "select count(1) from WEB_ORDERS t where 1=1 ";
		rowSql = rowSql + sqlwhere;
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));

		PageModel<WebOrders> page = WebOrdersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.setAttribute("page", page);
		request.getSession().setAttribute("webusers", user);
		request.getRequestDispatcher("/jsp/webreportorderslist.jsp").forward(request, response);

	}

	// ��ȡ�б����� (request,response)
	public void listWebOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = (Users) request.getSession().getAttribute("webusers");
		if (user == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		// String userId = request.getParameter("userId");
		String sql = "select * from WEB_ORDERS t where 1=1 ";
		String rowSql = "select count(1) from WEB_ORDERS t where 1=1 ";
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));

		PageModel<WebOrders> page = WebOrdersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.setAttribute("page", page);
		request.getSession().setAttribute("webusers", user);
		request.getRequestDispatcher("/jsp/webreportorderslist.jsp").forward(request, response);

	}

	// ɾ����ϢeditWebOrders
	public void deleteWebOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		System.out.println("login-dopost--enter deleteWebOrders-->");
		int uid = WebOrdersService.deleteWebOrders(orderId);
		if (uid == -1) {
			String message = "ɾ��ʧ��";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		} else {
			// request.getRequestDispatcher("/servlet/meetingusers?method=listMeetingUsers").forward(request,
			// response);
			listWebOrders(request, response);
		}

	}

}
