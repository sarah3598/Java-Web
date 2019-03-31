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
import com.csh.util.PageModel;
import com.csh.util.Pagination;

/*********************
 * Author shaohui-chen
 */

public class CustomerOrdersServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		if (!LoginControlPublicServlet.LoginSessionValidate(request, response, 2))
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
			JSONArray jsonarr1 = new JSONArray();
			String OP = request.getParameter("OP");
			switch (OP) {
			case "1":// 未支付订单
				jsonarr1 = getOrderNoPayListData(request);
				break;
			case "2":// 支付失败订单
				jsonarr1 = getOrderFailedPayListData(request);
				break;
			case "3":// 已取消订单
				jsonarr1 = getOrderCancelListData(request);
				break;
			case "4":// 已取消待处理订单
				jsonarr1 = getOrderCancelListData(request);
				break;
			}

			msg = "订单删除成功！";
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else {
			msg = "订单删除失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);

	}

	// add by xiaopan
	public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String OP = request.getParameter("OP");
		switch (OP) {
		case "1":// 确认收货
			receiveOrder(request, response);
			break;
		case "2":// 取消订单
			cancelOrder(request, response);
			break;
		}

	}

	// add by xiaopan
	public void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, ServletException {
		String ORDER_ID = request.getParameter("ORDER_ID");// 唯一性

		// update order info
		String sql1 = "UPDATE WEB_ORDERS SET ORDER_STATUS=? WHERE ORDER_ID=?";
		Object[] param1 = new Object[2];
		param1[0] = 5;
		param1[1] = ORDER_ID;

		int flag1 = BaseDao.exeSql(sql1, param1);
		String normal_json = "";
		String msg = "";
		// 一个订单的基本信息更新成功
		if (flag1 > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getOrderListData(request);
			msg = "确认收货成功！";
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else {
			msg = "确认收货失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, ServletException {
		String ORDER_ID = request.getParameter("ORDER_ID");// 唯一性

		String CANCEL_REASON = request.getParameter("CANCEL_REASON");
		String sql0 = "SELECT ORDER_STATUS FROM WEB_ORDERS WHERE ORDER_ID=?";
		Object[] param0 = new Object[1];
		param0[0] = ORDER_ID;
		JSONArray jsonarr0 = BaseDao.getRsJSONArray(sql0, param0);
		int CANCEL_SRC_STATUS = 1;
		try {
			CANCEL_SRC_STATUS = jsonarr0.getJSONObject(0).getInt("ORDER_STATUS");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// update order info
		String sql1 = "UPDATE WEB_ORDERS SET CANCEL_SRC_STATUS=?,ORDER_STATUS=?,CANCEL_REMARK=? WHERE ORDER_ID=?";
		Object[] param1 = new Object[4];
		param1[0] = CANCEL_SRC_STATUS * (-1);
		param1[1] = 0;
		param1[2] = CANCEL_REASON;
		param1[3] = ORDER_ID;
		int flag1 = BaseDao.exeSql(sql1, param1);

		String normal_json = "";
		String msg = "";

		if (flag1 > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getOrderListData(request);
			msg = "订单已取消！";
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else {
			msg = "订单取消失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
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
		request.getRequestDispatcher("/zh_CN/customer/yb_order_list.jsp").forward(request, response);

	}

	// add by xiaopan
	public void getOrderNoPayList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getOrderNoPayListData(request);
		request.setAttribute("order_no_pay_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_order_no_pay_list.jsp").forward(request, response);
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

		Object USER_ID = LoginControlPublicServlet.getSessionKeyValue(request, "USER_ID");

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.*,B.TITLE FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.USER_ID=? ORDER BY A.ORDER_ID DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[3];
		param[0] = USER_ID;
		param[1] = startRow;
		param[2] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_ORDERS A LEFT JOIN WEB_GOODS_BASIC_INFO B ON A.GOOD_ID=B.GOOD_ID WHERE A.USER_ID=?";
		Object[] param1 = new Object[1];
		param1[0] = USER_ID;

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

}
