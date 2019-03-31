package com.csh.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//import net.StrUtils;



import org.hibernate.Query;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.domain.WebOrders;
import com.csh.util.PageModel;
/*********************
 * Author shaohui-chen
 ******************** */
public class WebOrdersReportService {

// 查询orders

	public static List<WebOrders> findWebOrders(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		List<WebOrders> list = new ArrayList<WebOrders>();
		try {
			while (rs.next()) {
				WebOrders od = new WebOrders();
				
				//int orderId = rs.getInt("ORDER_ID");
				long orderId = rs.getLong("ORDER_ID");
				int goodId = rs.getInt("GOOD_ID");
				int customerId = rs.getInt("CUSTOMER_ID");
				String marketPrice = rs.getString("MARKET_PRICE");
				String compareAtPrice = rs.getString("COMPARE_AT_PRICE");
				String chargeTaxes = rs.getString("CHARGE_TAXES");
				String costPrice = rs.getString("COST_PRICE");
				String discountMoney = rs.getString("DISCOUNT_MONEY");
				String shippingPrice = rs.getString("SHIPPING_PRICE");
				String totalCharge = rs.getString("TOTAL_CHARGE");
				String emailInvoice = rs.getString("EMAIL_INVOICE");
				String acceptPayment = rs.getString("ACCEPT_PAYMENT");
				String paymentWays = rs.getString("PAYMENT_WAYS");
				String status = rs.getString("STATUS");
				String tags = rs.getString("TAGS");
				//int happenDate = rs.getInt("happenDate");
				//int happenTime = rs.getInt("happenTime");
				String loginAccount = rs.getString("LOGIN_ACCOUNT");
				String bak = rs.getString("BAK");
				String bak1 = rs.getString("BAK1");
				//String orderName = rs.getString("ORDER_NAME");

				od.setOrderId(orderId);
				od.setGoodId(goodId);
				od.setCustomerId(customerId);
				od.setMarketPrice(marketPrice);
				od.setCompareAtPrice(compareAtPrice);
				od.setChargeTaxes(chargeTaxes);
				od.setCostPrice(costPrice);
				od.setDiscountMoney(discountMoney);
				od.setShippingPrice(shippingPrice);
				od.setTotalCharge(totalCharge);
				od.setEmailInvoice(emailInvoice);
				od.setAcceptPayment(acceptPayment);
				od.setPaymentWays(paymentWays);
				od.setStatus(status);
				od.setTags(tags);
				//od.setHappenDate(happenDate);
				//od.setHappenTime(happenTime);
				od.setLoginAccount(loginAccount);
				od.setBak(bak);
				od.setBak1(bak1);
				//od.setTags(orderName);
				
				list.add(od);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	

	
	public static List<WebOrders> getOrderList(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		List<WebOrders> list = new ArrayList<WebOrders>();

		try {

			while (rs.next()) {
				WebOrders wo = new WebOrders();

				int orderId = rs.getInt("orderId");
				int goodId = rs.getInt("goodId");
				int customerId = rs.getInt("customerId");
//				int orderId = rs.getInt("orderId");
//				int productId = rs.getInt("productId");
//				int customerId = rs.getInt("customerId");
				String marketPrice = rs.getString("marketPrice");
				String compareAtPrice = rs.getString("compareAtPrice");
				String chargeTaxes = rs.getString("chargeTaxes");
				String costPrice = rs.getString("costPrice");
				String discountMoney = rs.getString("discountMoney");
				String shippingPrice = rs.getString("shippingPrice");
				String totalCharge = rs.getString("totalCharge");
				String emailInvoice = rs.getString("emailInvoice");
				String acceptPayment = rs.getString("acceptPayment");
				String paymentWays = rs.getString("paymentWays");
				String status = rs.getString("status");
				String tags = rs.getString("tags");
				//int happenDate = rs.getInt("happenDate");
				//int happenTime = rs.getInt("happenTime");
				String loginAccount = rs.getString("loginAccount");
				String bak = rs.getString("bak");
				String bak1 = rs.getString("bak1");
				String orderName = rs.getString("orderName");

				wo.setOrderId(orderId);
				wo.setGoodId(goodId);
				wo.setCustomerId(customerId);
				wo.setMarketPrice(marketPrice);
				wo.setCompareAtPrice(compareAtPrice);
				wo.setChargeTaxes(chargeTaxes);
				wo.setCostPrice(costPrice);
				wo.setDiscountMoney(discountMoney);
				wo.setShippingPrice(shippingPrice);
				wo.setTotalCharge(totalCharge);
				wo.setEmailInvoice(emailInvoice);
				wo.setAcceptPayment(acceptPayment);
				wo.setPaymentWays(paymentWays);
				wo.setStatus(status);
				wo.setTags(tags);
				//od.setHappenDate(happenDate);
				//od.setHappenTime(happenTime);
				wo.setLoginAccount(loginAccount);
				wo.setBak(bak);
				wo.setBak1(bak1);
				wo.setTags(orderName);

				list.add(wo);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	
	
	public static int getOrderRows(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		int rows = 0;
		try {
			if (rs.next()) {
				rows = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rows;
	}
	
	
	public static PageModel<WebOrders> findInventoryPageModel(int currentPage, int pageSize, String listSql, String rowSql,
			Object[] param) {

		int rowCount = getOrderRows(rowSql, null);
		List<WebOrders> invList = getOrderList(listSql, param);
		
		System.out.println("doPost-servlet-findWebOrderList--size:"+invList.size()); 
		
		PageModel<WebOrders> pageModel = new PageModel<WebOrders>();
		pageModel.setCurrentPage(currentPage);
		pageModel.setList(invList);
		pageModel.setPageSize(pageSize);
		pageModel.setTotalRecord(rowCount);
		return pageModel;
	}
	

	
	
	
	//查询用户分页对象
	public static PageModel<WebOrders> findPageModel(int currentPage,int pageSize,String sql,String rowContSql,Object[] param)
	{
		System.out.println("enter--PageModel--findPageModel-->");
		System.out.println("enter--PageModel--findPageModel-sql1->"+sql);
		PageModel<WebOrders> pageModel=new PageModel<WebOrders>();
		pageModel.setCurrentPage(currentPage);
		sql =  "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (" + sql+") A WHERE ROWNUM <= "+(currentPage)*pageSize+ ") WHERE RN >= "+((currentPage-1)*pageSize);
		
		//sql =  "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (" + sql+") A WHERE ROWNUM <= "+(currentPage)*pageSize+ ") WHERE RN >= "+((currentPage-1)*pageSize);
//		sql = "select * from ";
//		sql = sql +"(select rownum rn,a.* from ";
//		sql = sql +"(select * from WEB_ORDERS t order by t.order_id desc) a) b "; 
//		sql = sql + "where rownum between "+(currentPage-1)*pageSize +" and "+ (currentPage)*pageSize;
		//sql = sql + "where row_num between "+(currentPage-1)*pageSize +" and "+ (currentPage)*pageSize;
		//sql =  "SELECT * FROM (SELECT A.orderId,A.productId,A.customerId,A.marketPrice,A.compareAtPrice,A.chargeTaxes,A.costPrice,A.discountMoney,A.shippingPrice,A.totalCharge,A.emailInvoice,A.acceptPayment,A.paymentWays,A.status,A.tags,A.happenDate,A.happenTime,A.loginAccount,A.bak,A.bak1,A.orderName,ROWNUM RN FROM (" + sql+") A WHERE ROWNUM <= "+(currentPage)*pageSize+ ") WHERE RN >= "+((currentPage-1)*pageSize);
		
		System.out.println("enter--PageModel--findPageModel--sql2-->"+sql);
		//sql = sql + " order by userId desc limit "+ (currentPage-1)*pageSize +","+pageSize + " ";
		System.out.println("PageModel--findPageModel---sql3-->"+sql);
		pageModel.setList(findWebOrders(sql,param));
		System.out.println("PageModel--findWebOrders-->"+sql);
		pageModel.setPageSize(pageSize);
		System.out.println("PageModel--findWebOrders--pageSize->"+pageSize);
		
		System.out.println("PageModel--findWebUsers--BaseDao.getRowCount(rowContSql, param)->"+BaseDao.getRowCount(rowContSql, param));
		pageModel.setTotalRecord(BaseDao.getRowCount(rowContSql, param));
		return pageModel;
		
	
		
		
	}
	
	//假删除订单
	public static int deleteWebOrders(String orderId){
		String sql = "delete from WEB_ORDERS  where userId="+orderId;
		int i = BaseDao.exeSql(sql, null);
		if(i == -1){
			return -1;
		}
		return i;
		
	}
	
	
	
}
