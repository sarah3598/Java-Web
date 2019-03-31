package com.csh.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;

import com.csh.dao.BaseDao;

import com.csh.domain.Users;
import com.csh.domain.WebOrders;
import com.csh.service.JsonService;
import com.csh.service.WebOrdersService;
import com.csh.service.WebUsersService;
import com.csh.util.DateUtil;
import com.csh.util.PageModel;

public class CustomerBuyServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if (!LoginControlPublicServlet.LoginSessionValidate(request, response, 2))
			return;
		// request.setCharacterEncoding("GBK");
		String method = request.getParameter("method");

		switch (method) {
		case "buy_detail":// 显示下单之前的信息
			showBuyDetail(request, response);
			break;
		case "submit_order":// 提交购买订单
			submitBuyOrder(request, response);
			break;
		case "pay_order":// 支付订单
			payOrder(request, response);
			break;
		case "sales_link":// 生成购买链接
			createSalesLink(request, response);
			break;

		default:
			showBuyDetail(request, response);
			break;
		}

		// if (method.equals("addOrder")) {
		//
		// addOrder(request, response);
		// }
		//
		// if (method.equals("listSearchWebOrders")) {
		// listSearchWebOrders(request, response);
		//
		// }
		// if (method.equals("listWebOrders")) {
		// listWebOrders(request, response);
		//
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
	public void showBuyDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 取出商品信息
		JSONArray product_detail_json = new JSONArray();
		product_detail_json = CustomerProductServlet.getProductDetailData(request);

		// 只取出1条用户地址信息（默认地址优先），若没有则返回空
		JSONArray addr_json = new JSONArray();
		addr_json = CustomerUserAddressServlet.getAddressListData(request, response);

		String GOOD_ID = request.getParameter("GOOD_ID");
		String SALES_PERSON_ID = request.getParameter("SALES_PERSON_ID");
		String ROLE = request.getParameter("ROLE");

		request.setAttribute("product_detail", product_detail_json.toString());
		request.setAttribute("address_list", addr_json.toString());
		request.setAttribute("SALES_PERSON_ID", SALES_PERSON_ID);
		request.setAttribute("ROLE", ROLE);
		request.getRequestDispatcher("/zh_CN/customer/yb_order_settle.jsp").forward(request, response);

	}

	// add by xiaopan
	public void createSalesLink(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Object USER_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");
		Object ROLE = LoginControlPublicServlet.getSessionKeyValue(request, response, "ROLE");

		int flag = Integer.parseInt((String) ROLE);
		String GOOD_ID = request.getParameter("GOOD_ID");

		String path = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath();// 项目名称
		String url = "";
		if (flag == 9999) {
			url = path + "/zh_CN/customer/yb_product_buy_page.jsp?GOOD_ID=" + GOOD_ID;
		} else {
			url = path + "/zh_CN/customer/yb_product_buy_page.jsp?SALES_PERSON_ID=" + USER_ID + "&ROLE=" + ROLE + "&GOOD_ID=" + GOOD_ID;
		}

		response.sendRedirect(url);
		return;

		// 取出商品信息
		// JSONArray product_detail_json = new JSONArray();
		// product_detail_json =
		// CompanyProductServlet.getProductDetailData(request);

		// 取出用户默认地址信息，若没有则返回空
		// JSONArray addr_json = new JSONArray();
		// addr_json = CustomerUserAddressServlet.getAddressListData(request);
		//
		// request.setAttribute("product_detail",
		// product_detail_json.toString());
		// request.setAttribute("address_list", addr_json.toString());
		// request.getRequestDispatcher("/zh_CN/customer/yb_db_order_settle.jsp").forward(request,
		// response);

	}

	// add by xiaopan
	public void submitBuyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1）创建订单记录到ORDER表
		String ORDER_ID = addOrderDetail(request, response);

		// 2）添加分销金额记录到DISTRIBUT_MONEY_2017表
		Boolean flag = addDistributSales(ORDER_ID, request, response);
		String msg = "";
		String normal_json = "";
		String json = "";
		if (flag) {
			msg = "订单提交成功！";
			json = JsonService.convertKeyValToJsonObject("ORDER_ID", ORDER_ID);
			normal_json = JsonService.getNormalJson(json, msg);

		} else {
			msg = "订单提交失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public void payOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1）创建订单记录到ORDER表
		String ORDER_ID = request.getParameter("ORDER_ID");

		String sql = "UPDATE WEB_ORDERS SET PAY_STATUS=?,ORDER_STATUS=? WHERE ORDER_ID=?";
		Object[] param = new Object[3];
		param[0] = 1;
		param[1] = 2;
		param[2] = ORDER_ID;
		int flag = BaseDao.exeSql(sql, param);

		String msg = "";
		String normal_json = "";
		String json = "";
		if (flag > 0) {
			msg = "订单支付成功！";
			json = JsonService.convertKeyValToJsonObject("ORDER_ID", ORDER_ID);
			normal_json = JsonService.getNormalJson(json, msg);

		} else {
			msg = "订单支付失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public String addOrderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object USER_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");
		Object EMAIL = LoginControlPublicServlet.getSessionKeyValue(request, response, "EMAIL");

		String BUY_NUM = request.getParameter("BUY_NUM");
		String PRODUCT_PRICE = request.getParameter("PRODUCT_PRICE");

		String DISCOUNT_MONEY = request.getParameter("DISCOUNT_MONEY");
		String SHIPPING_PRICE = request.getParameter("SHIPPING_PRICE");

		String PRODUCT_TOTAL_MONEY = request.getParameter("PRODUCT_TOTAL_MONEY");
		String ORDER_TOTAL_MONEY = request.getParameter("ORDER_TOTAL_MONEY");

		String ADDRESS_ID = request.getParameter("ADDR_ID");
		String PAYMENT_WAYS = request.getParameter("PAYMENT_WAYS");
		String GOOD_ID = request.getParameter("GOOD_ID");
		int ORDER_STATUS = 1;// 收到订单
		int PAY_STATUS = 0;// 未支付
		String INSERT_SEQ = UUID.randomUUID().toString();// 插入安全码

		String IS_INVOICE = request.getParameter("IS_INVOICE");
		String INVOICE_TYPE = request.getParameter("INVOICE_TYPE");
		String INVOICE_CLIENT = request.getParameter("INVOICE_CLIENT");
		IS_INVOICE = IS_INVOICE == null ? "0" : "1";
		INVOICE_CLIENT = INVOICE_CLIENT == null ? "" : INVOICE_CLIENT;

		String ORDER_NO = DateUtil.getTimastampString();// 订单编号以时间戳来记录

		Object[] param = new Object[18];
		param[0] = EMAIL;
		param[1] = USER_ID;
		param[2] = BUY_NUM;
		param[3] = PRODUCT_PRICE;
		param[4] = SHIPPING_PRICE;
		param[5] = ORDER_TOTAL_MONEY;
		param[6] = ADDRESS_ID;
		param[7] = PAYMENT_WAYS;
		param[8] = GOOD_ID;
		param[9] = ORDER_STATUS;
		param[10] = PAY_STATUS;
		param[11] = INSERT_SEQ;
		param[12] = IS_INVOICE;
		param[13] = INVOICE_CLIENT;
		param[14] = ORDER_NO;
		param[15] = PRODUCT_TOTAL_MONEY;
		param[16] = DISCOUNT_MONEY;
		param[17] = INVOICE_TYPE;

		String sql = " INSERT INTO WEB_ORDERS (LOGIN_ACCOUNT,USER_ID,BUY_NUM,PRODUCT_PRICE,SHIPPING_PRICE,ORDER_TOTAL_MONEY,ADDRESS_ID,PAYMENT_WAYS,GOOD_ID,ORDER_STATUS,PAY_STATUS,INSERT_SEQ,IS_INVOICE,INVOICE_CLIENT,ORDER_NO,PRODUCT_TOTAL_MONEY,DISCOUNT_MONEY,INVOICE_TYPE)" + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int flag1 = BaseDao.exeSql(sql, param);

		String order_id = "";
		if (flag1 > 0) {
			String sql1 = "SELECT ORDER_ID FROM WEB_ORDERS WHERE INSERT_SEQ=?";
			Object[] param1 = new Object[1];
			param1[0] = INSERT_SEQ;
			JSONArray arr = BaseDao.getRsJSONArray(sql1, param1);
			if (arr.length() > 0) {
				try {
					order_id = arr.getJSONObject(0).getString("ORDER_ID");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return order_id;
	}

	// add by xiaopan
	public Boolean addDistributSales(String ORDER_ID, HttpServletRequest request, HttpServletResponse response) throws IOException {

		Object BUYER_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");

		String USER_ID = request.getParameter("SALES_PERSON_ID");
		String S_ROLE = request.getParameter("ROLE");
		USER_ID = (USER_ID == null || USER_ID.equals("")) ? "0" : USER_ID;
		S_ROLE = (S_ROLE == null || S_ROLE.equals("")) ? "0" : S_ROLE;

		int ROLE = Integer.parseInt(S_ROLE);
		int BUY_NUM = Integer.parseInt(request.getParameter("BUY_NUM"));
		float PRODUCT_PRICE = Float.parseFloat(request.getParameter("PRODUCT_PRICE"));
		String GOOD_ID = request.getParameter("GOOD_ID");
		float GOOD_MONEY = BUY_NUM * PRODUCT_PRICE;

		// 从产品表GOOD中查询分销佣金,直接按金额计算
		int SALE_COMMISSION = 0;
		int SS_COMMISSION = 0;
		int GL_COMMISSION = 0;
		int GL_OVERRIDING = 0;
		int MA_COMMISSION = 0;
		int MA_OVERRIDING = 0;
		int VP_COMMISSION = 0;
		int VP_OVERRIDING = 0;
		try {

			String sql1 = "SELECT SALE_COMMISSION, SS_COMMISSION, GL_COMMISSION, GL_OVERRIDING, MA_COMMISSION,MA_OVERRIDING,VP_COMMISSION,VP_OVERRIDING FROM WEB_GOODS_BASIC_INFO WHERE GOOD_ID=?";
			Object[] param1 = new Object[1];
			param1[0] = GOOD_ID;
			JSONArray arr1 = BaseDao.getRsJSONArray(sql1, param1);
			if (arr1.length() > 0) {

				SALE_COMMISSION = arr1.getJSONObject(0).getInt("SALE_COMMISSION");
				SS_COMMISSION = arr1.getJSONObject(0).getInt("SS_COMMISSION");
				GL_COMMISSION = arr1.getJSONObject(0).getInt("GL_COMMISSION");
				GL_OVERRIDING = arr1.getJSONObject(0).getInt("GL_OVERRIDING");
				MA_COMMISSION = arr1.getJSONObject(0).getInt("MA_COMMISSION");
				MA_OVERRIDING = arr1.getJSONObject(0).getInt("MA_OVERRIDING");
				VP_COMMISSION = arr1.getJSONObject(0).getInt("VP_COMMISSION");
				VP_OVERRIDING = arr1.getJSONObject(0).getInt("VP_OVERRIDING");

			}

			if (ROLE == 0) { // 无销售员，只记录销售金额（VP_ID,MA_ID,SALES_ID默认为0）
				String sql2 = "INSERT INTO SALES_2017(TYPE,BUYER_ID,ORDER_ID,GOOD_ID,GOOD_MONEY) VALUES(?,?,?,?,?)";
				// TYPE=0,BUYER_ID=BUYER_ID,ORDER_ID=ORDER_ID,GOOD_ID=GOOD_ID,GOOD_MONEY=GOOD_MONEY
				Object[] param2 = new Object[5];
				param2[0] = 0;
				param2[1] = BUYER_ID;
				param2[2] = ORDER_ID;
				param2[3] = GOOD_ID;
				param2[4] = GOOD_MONEY;
				int flag = BaseDao.exeSql(sql2, param2);

			} else if (ROLE == 1) {// 销售代理销售
				// 从SALES_MEMBER中查询该销售员的上层经理MA_ID，上层副总裁VP_ID;和团队类型MTYPE
				String TEAM_ID = "", MA_ID = "",VP_ID = "";
				int TYPE = 0;

				String sql3 = "select a.TEAM_ID,b.TYPE from SALES_MEMBER a left join SALES_TEAM b on a.TEAM_ID=b.TEAM_ID where a.MEMBER_ID=?";
				Object[] param3 = new Object[1];
				param3[0] = USER_ID;
				JSONArray arr3 = BaseDao.getRsJSONArray(sql3, param3);
				if (arr3.length() > 0) {
					try {
						TEAM_ID = arr3.getJSONObject(0).getString("TEAM_ID");
						TYPE = arr3.getJSONObject(0).getInt("TYPE");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//根据上级团队的类型，判断取到的上级是什么身份：
				int MTYPE = 0;
				
				if(TYPE == 1){		//组长
					MTYPE = 1;
					MA_ID = TEAM_ID;
				}
				else if(TYPE == 2){	//经理
					MTYPE = 2;
					MA_ID = TEAM_ID;
				}
				else if(TYPE == 3){	//副总裁（跨级领导）
					VP_ID = TEAM_ID;
				}


				//如果上级团队是组长或者经理，继续取出其上级VP_ID
				if(MTYPE>0){
					String sql4 = "select a.TEAM_ID from SALE_MEMBER a left join SALE_TEAM b on a.TEAM_ID=b.TEAM_ID where a.MEMBER_ID=? and b.TYPE=3";
					Object[] param4 = new Object[1];
					param4[0] = MA_ID;
					JSONArray arr4 = BaseDao.getRsJSONArray(sql4, param4);
					if (arr4.length() > 0) {
						try {
							VP_ID = arr4.getJSONObject(0).getString("TEAM_ID");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				

				// 插入自己销售的金额
				String INSERT_SEQ5 = UUID.randomUUID().toString();// 插入安全码
				String sql5 = "INSERT INTO SALES_2017(TYPE,VP_ID,MA_ID,SALES_ID,BUYER_ID,ORDER_ID,GOOD_ID,GOOD_MONEY,INSERT_SEQ) VALUES(?,?,?,?,?,?,?,?,?)";
				// TYPE=ROLE,VP_ID=VP_ID,MA_ID=MA_ID,SALES_ID=USER_ID,BUYER_ID=BUYER_ID,ORDER_ID=ORDER_ID,GOOD_ID=GOOD_ID,GOOD_MONEY=GOOD_MONEY
				Object[] param5 = new Object[9];
				param5[0] = ROLE;
				param5[1] = VP_ID;
				param5[2] = MA_ID;
				param5[3] = USER_ID;
				param5[4] = BUYER_ID;
				param5[5] = ORDER_ID;
				param5[6] = GOOD_ID;
				param5[7] = GOOD_MONEY;
				param5[8] = INSERT_SEQ5;

				int flag5 = BaseDao.exeSql(sql5, param5);

				int SALES_ID = 0;
				if (flag5 > 0) {
					// 查询到刚插入的销售表id（SALES_ID）；
					String sql6 = "SELECT SALES_ID FROM SALES_2017 WHERE INSERT_SEQ=?";
					Object[] param6 = new Object[1];
					param6[1] = INSERT_SEQ5;
					JSONArray arr6 = BaseDao.getRsJSONArray(sql6, param6);
					if (arr6.length() > 0) {
						try {
							SALES_ID = arr6.getJSONObject(0).getInt("SALES_ID");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				// 销售都拿分销佣金，上级组长/经理和副总裁都拿覆盖佣金
				String sql7 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";
				Object[] param7 = new Object[4];
				param7[0] = SALES_ID;
				param7[1] = USER_ID;
				param7[2] = 1;
				param7[3] = SALE_COMMISSION;
				int flag7 = BaseDao.exeSql(sql7, param7);

				// 插入上级经理的覆盖佣金
				if (MTYPE == 1) {// 组长团队，组长的覆盖佣金TYPE=6，GL_OVERRIDING
					String sql8 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";

					Object[] param8 = new Object[4];
					param8[0] = SALES_ID;
					param8[1] = MA_ID;
					param8[2] = 6;
					param8[3] = GL_OVERRIDING;
					int flag8 = BaseDao.exeSql(sql8, param8);
				} else if (MTYPE == 2) {// 经理团队，经理的覆盖佣金TYPE=7，MA_OVERRIDING
					String sql9 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";

					Object[] param9 = new Object[4];
					param9[0] = SALES_ID;
					param9[1] = MA_ID;
					param9[2] = 7;
					param9[3] = MA_OVERRIDING;
					int flag9 = BaseDao.exeSql(sql9, param9);
				}
				
				

				// 如果VP_ID存在，插入上级副总裁的覆盖佣金：TYPE=8，VP_OVERRIDING
				if(!(VP_ID.equals(""))){
					String sql10 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";
					Object[] param10 = new Object[4];
					param10[0] = SALES_ID;
					param10[1] = VP_ID;
					param10[2] = 8;
					param10[3] = VP_OVERRIDING;
					int flag9 = BaseDao.exeSql(sql10, param10);
				}
				

			} else if (ROLE == 2) {// 独家代理销售
				// 从SALES_MEMBER中查询该销售员的上层经理MA_ID，上层副总裁VP_ID;和团队类型MTYPE
				String TEAM_ID = "", MA_ID = "",VP_ID = "";
				int TYPE = 0;

				String sql3 = "select a.TEAM_ID,b.TYPE from SALES_MEMBER a left join SALES_TEAM b on a.TEAM_ID=b.TEAM_ID where a.MEMBER_ID=?";
				Object[] param3 = new Object[1];
				param3[0] = USER_ID;
				JSONArray arr3 = BaseDao.getRsJSONArray(sql3, param3);
				if (arr3.length() > 0) {
					try {
						TEAM_ID = arr3.getJSONObject(0).getString("TEAM_ID");
						TYPE = arr3.getJSONObject(0).getInt("TYPE");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//根据上级团队的类型，判断取到的上级是什么身份：
				int MTYPE = 0;
				
				if(TYPE == 1){		//组长
					MTYPE = 1;
					MA_ID = TEAM_ID;
				}
				else if(TYPE == 2){	//经理
					MTYPE = 2;
					MA_ID = TEAM_ID;
				}
				else if(TYPE == 3){	//副总裁（跨级领导）
					VP_ID = TEAM_ID;
				}


				//如果上级团队是组长或者经理，继续取出其上级VP_ID
				if(MTYPE>0){
					String sql4 = "select a.TEAM_ID from SALE_MEMBER a left join SALE_TEAM b on a.TEAM_ID=b.TEAM_ID where a.MEMBER_ID=? and b.TYPE=3";
					Object[] param4 = new Object[1];
					param4[0] = MA_ID;
					JSONArray arr4 = BaseDao.getRsJSONArray(sql4, param4);
					if (arr4.length() > 0) {
						try {
							VP_ID = arr4.getJSONObject(0).getString("TEAM_ID");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				// 插入自己销售的金额
				String INSERT_SEQ5 = UUID.randomUUID().toString();// 插入安全码
				String sql5 = "INSERT INTO SALES_2017(TYPE,VP_ID,MA_ID,SALES_ID,BUYER_ID,ORDER_ID,GOOD_ID,GOOD_MONEY,INSERT_SEQ) VALUES(?,?,?,?,?,?,?,?,?)";
				// TYPE=ROLE,VP_ID=VP_ID,MA_ID=MA_ID,SALES_ID=USER_ID,BUYER_ID=BUYER_ID,ORDER_ID=ORDER_ID,GOOD_ID=GOOD_ID,GOOD_MONEY=GOOD_MONEY
				Object[] param5 = new Object[9];
				param5[0] = ROLE;
				param5[1] = VP_ID;
				param5[2] = MA_ID;
				param5[3] = USER_ID;
				param5[4] = BUYER_ID;
				param5[5] = ORDER_ID;
				param5[6] = GOOD_ID;
				param5[7] = GOOD_MONEY;
				param5[8] = INSERT_SEQ5;

				int flag5 = BaseDao.exeSql(sql5, param5);

				int SALES_ID = 0;
				if (flag5 > 0) {
					// 查询到刚插入的销售表id（SALES_ID）；
					String sql6 = "SELECT SALES_ID FROM SALES_2017 WHERE INSERT_SEQ=?";
					Object[] param6 = new Object[1];
					param6[1] = INSERT_SEQ5;
					JSONArray arr6 = BaseDao.getRsJSONArray(sql6, param6);
					if (arr6.length() > 0) {
						try {
							SALES_ID = arr6.getJSONObject(0).getInt("SALES_ID");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				// 销售都拿分销佣金，上级组长/经理和副总裁都拿覆盖佣金
				String sql7 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";

				Object[] param7 = new Object[4];
				param7[0] = SALES_ID;
				param7[1] = USER_ID;
				param7[2] = 2;
				param7[3] = SS_COMMISSION;
				int flag7 = BaseDao.exeSql(sql7, param7);

				// 插入上级经理的覆盖佣金
				if (MTYPE == 1) {// 组长团队，组长的覆盖佣金TYPE=6，GL_OVERRIDING
					String sql8 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";

					Object[] param8 = new Object[4];
					param8[0] = SALES_ID;
					param8[1] = MA_ID;
					param8[2] = 6;
					param8[3] = GL_OVERRIDING;
					int flag8 = BaseDao.exeSql(sql8, param8);
				} else if (MTYPE == 2) {// 经理团队，经理的覆盖佣金TYPE=7，MA_OVERRIDING
					String sql9 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";

					Object[] param9 = new Object[4];
					param9[0] = SALES_ID;
					param9[1] = MA_ID;
					param9[2] = 7;
					param9[3] = MA_OVERRIDING;
					int flag9 = BaseDao.exeSql(sql9, param9);
				}

				
				// 如果VP_ID存在，插入上级副总裁的覆盖佣金：TYPE=8，VP_OVERRIDING
				if(!(VP_ID.equals(""))){
					String sql10 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";
					Object[] param10 = new Object[4];
					param10[0] = SALES_ID;
					param10[1] = VP_ID;
					param10[2] = 8;
					param10[3] = VP_OVERRIDING;
					int flag9 = BaseDao.exeSql(sql10, param10);
				}

			} else if (ROLE == 4) {// 组长销售
				// 查询该经理的上层副总裁VP_ID;
				String VP_ID = "";
				String sql10 = "SELECT TEAM_ID FROM SALES_MEMBER WHERE MEMBER_ID=?";
				Object[] param10 = new Object[1];
				param10[0] = USER_ID;
				JSONArray arr10 = BaseDao.getRsJSONArray(sql10, param10);
				if (arr10.length() > 0) {
					try {
						VP_ID = arr10.getJSONObject(0).getString("TEAM_ID");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// 组长/经理拿分销佣金，上级副总裁拿覆盖佣金
				// //插入自己销售的金额（SALES_ID默认的为0）
				String INSERT_SEQ11 = UUID.randomUUID().toString();// 插入安全码
				String sql11 = "INSERT INTO SALES_2017(TYPE,VP_ID,MA_ID,BUYER_ID,ORDER_ID,GOOD_ID,GOOD_MONEY,INSERT_SEQ) VALUES(?,?,?,?,?,?,?,?)";
				// //TYPE=TYPE,VP_ID=VP_ID,MA_ID=USER_ID,BUYER_ID=BUYER_ID,ORDER_ID=ORDER_ID,GOOD_ID=GOOD_ID,GOOD_MONEY=GOOD_MONEY
				Object[] param11 = new Object[8];
				param11[0] = ROLE;
				param11[1] = VP_ID;
				param11[2] = USER_ID;
				param11[3] = BUYER_ID;
				param11[4] = ORDER_ID;
				param11[5] = GOOD_ID;
				param11[6] = GOOD_MONEY;
				param11[7] = INSERT_SEQ11;

				int flag11 = BaseDao.exeSql(sql11, param11);

				int SALES_ID = 0;
				if (flag11 > 0) {
					// 查询到刚插入的销售表id（SALES_ID）；
					String sql12 = "SELECT SALES_ID FROM SALES_2017 WHERE INSERT_SEQ=?";
					Object[] param12 = new Object[1];
					param12[0] = INSERT_SEQ11;
					JSONArray arr12 = BaseDao.getRsJSONArray(sql12, param12);
					if (arr12.length() > 0) {
						try {
							SALES_ID = arr12.getJSONObject(0).getInt("SALES_ID");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				// 查询到刚插入的销售表id（SALES_ID）；

				// //插入经理的分销佣金

				String sql13 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";
				Object[] param13 = new Object[4];
				param13[0] = SALES_ID;
				param13[1] = USER_ID;
				param13[2] = 3;
				param13[3] = GL_COMMISSION;

				int flag13 = BaseDao.exeSql(sql13, param13);

				// 插入上级副总裁的覆盖佣金
				if(!(VP_ID.equals(""))){
					String sql14 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";
					// SALES_ID=SALES_ID,MEMBER_ID=VP_ID,RATE=VP_OVERRIDING_M,BROKERAGE=BROKERAGE
					Object[] param14 = new Object[4];
					param14[0] = SALES_ID;
					param14[1] = VP_ID;
					param14[2] = 8;
					param14[3] = VP_OVERRIDING;

					int flag14 = BaseDao.exeSql(sql14, param14);
				}

			} else if (ROLE == 8) {// 经理销售
				// 查询该经理的上层副总裁VP_ID;
				String VP_ID = "";
				String sql10 = "SELECT TEAM_ID FROM SALES_MEMBER WHERE MEMBER_ID=?";
				Object[] param10 = new Object[1];
				param10[0] = USER_ID;
				JSONArray arr10 = BaseDao.getRsJSONArray(sql10, param10);
				if (arr10.length() > 0) {
					try {
						VP_ID = arr10.getJSONObject(0).getString("TEAM_ID");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// 组长/经理拿分销佣金，上级副总裁拿覆盖佣金
				// //插入自己销售的金额（SALES_ID默认的为0）
				String INSERT_SEQ11 = UUID.randomUUID().toString();// 插入安全码
				String sql11 = "INSERT INTO SALES_2017(TYPE,VP_ID,MA_ID,BUYER_ID,ORDER_ID,GOOD_ID,GOOD_MONEY,INSERT_SEQ) VALUES(?,?,?,?,?,?,?,?)";
				// //TYPE=TYPE,VP_ID=VP_ID,MA_ID=USER_ID,BUYER_ID=BUYER_ID,ORDER_ID=ORDER_ID,GOOD_ID=GOOD_ID,GOOD_MONEY=GOOD_MONEY
				Object[] param11 = new Object[8];
				param11[0] = ROLE;
				param11[1] = VP_ID;
				param11[2] = USER_ID;
				param11[3] = BUYER_ID;
				param11[4] = ORDER_ID;
				param11[5] = GOOD_ID;
				param11[6] = GOOD_MONEY;
				param11[7] = INSERT_SEQ11;

				int flag11 = BaseDao.exeSql(sql11, param11);

				int SALES_ID = 0;
				if (flag11 > 0) {
					// 查询到刚插入的销售表id（SALES_ID）；
					String sql12 = "SELECT SALES_ID FROM SALES_2017 WHERE INSERT_SEQ=?";
					Object[] param12 = new Object[1];
					param12[0] = INSERT_SEQ11;
					JSONArray arr12 = BaseDao.getRsJSONArray(sql12, param12);
					if (arr12.length() > 0) {
						try {
							SALES_ID = arr12.getJSONObject(0).getInt("SALES_ID");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				// 查询到刚插入的销售表id（SALES_ID）；

				// //插入经理的分销佣金

				String sql13 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";
				Object[] param13 = new Object[4];
				param13[0] = SALES_ID;
				param13[1] = USER_ID;
				param13[2] = 4;
				param13[3] = MA_COMMISSION;

				int flag13 = BaseDao.exeSql(sql13, param13);

				// 插入上级副总裁的覆盖佣金
				if(!(VP_ID.equals(""))){
					String sql14 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";
					// SALES_ID=SALES_ID,MEMBER_ID=VP_ID,RATE=VP_OVERRIDING_M,BROKERAGE=BROKERAGE
					Object[] param14 = new Object[4];
					param14[0] = SALES_ID;
					param14[1] = VP_ID;
					param14[2] = 8;
					param14[3] = VP_OVERRIDING;

					int flag14 = BaseDao.exeSql(sql14, param14);
				}

			} else if (ROLE == 16) {// 副总裁销售

				// 插入自己销售的金额（SALES_ID, MA_ID默认的为0）
				String INSERT_SEQ15 = UUID.randomUUID().toString();// 插入安全码
				String sql15 = "INSERT INTO SALES_2017(TYPE,VP_ID,BUYER_ID,ORDER_ID,GOOD_ID,GOOD_MONEY,INSERT_SEQ) VALUES(?,?,?,?,?,?,?)";
				// TYPE=TYPE,VP_ID=USER_ID,BUYER_ID=BUYER_ID,ORDER_ID=ORDER_ID,GOOD_ID=GOOD_ID,GOOD_MONEY=GOOD_MONEY
				Object[] param15 = new Object[7];
				param15[0] = ROLE;
				param15[1] = USER_ID;
				param15[2] = BUYER_ID;
				param15[3] = ORDER_ID;
				param15[4] = GOOD_ID;
				param15[5] = GOOD_MONEY;
				param15[6] = INSERT_SEQ15;

				int flag15 = BaseDao.exeSql(sql15, param15);

				// 查询到刚插入的销售表id（SALES_ID）；
				int SALES_ID = 0;
				if (flag15 > 0) {
					// 查询到刚插入的销售表id（SALES_ID）；
					String sql16 = "SELECT SALES_ID FROM SALES_2017 WHERE INSERT_SEQ=?";
					Object[] param16 = new Object[1];
					param16[0] = INSERT_SEQ15;
					JSONArray arr16 = BaseDao.getRsJSONArray(sql16, param16);
					if (arr16.length() > 0) {
						try {
							SALES_ID = arr16.getJSONObject(0).getInt("SALES_ID");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				// 插入副总裁的分销佣金
				String sql17 = "INSERT INTO BROKERAGE_2017(SALES_ID,MEMBER_ID,TYPE,BROKERAGE) VALUES(?,?,?,?)";
				Object[] param17 = new Object[4];
				param17[0] = SALES_ID;
				param17[1] = USER_ID;
				param17[2] = 5;
				param17[3] = VP_COMMISSION;

				int flag17 = BaseDao.exeSql(sql17, param17);

			}

			// 备注：上面的每条记录都要加上HAPPEN_DATE和HAPPEN_TIME

			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// ��Ӷ�����Ϣ
	public void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users user = (Users) request.getSession().getAttribute("webusers");
		if (user == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		Object[] param = new Object[9];
		String buynum = request.getParameter("buynum");

		String customerID = request.getParameter("customerID");

		String shippingPrice = request.getParameter("shippingPrice");

		String totalCharge = request.getParameter("totalCharge");
		String soldTo = request.getParameter("soldTo");
		String pricing = request.getParameter("pricing");
		String compareAtPrice = request.getParameter("compareAtPrice");
		String goodId = request.getParameter("goodId");
		Integer status = 0;
		String operatorName = user.getUserName();

		param[0] = user.getUserId();
		System.out.println("user--user.getUserId()-->" + param[0]);
		param[1] = goodId;
		param[2] = customerID;
		param[3] = buynum;
		param[4] = pricing;
		param[5] = compareAtPrice;
		param[6] = shippingPrice;
		param[7] = totalCharge;
		param[8] = status;

		String sql = " insert into WEB_ORDERS (LOGIN_ACCOUNT,GOOD_ID,USER_ID,SALES_QTY_NUM," + "MARKET_PRICE, COMPARE_AT_PRICE	,SHIPPING_PRICE	,TOTAL_CHARGE,STATUS)" + " values(?,?,?,?,?,?,?,?,?)";

		int result = BaseDao.exeSql(sql, param);
		System.out.println("orderinsert-result->" + result);
		String message = "";
		if (result != -1) {

			message = "ִ�гɹ�";
			request.getRequestDispatcher("/servlet/weborders?method=listWebOrders").forward(request, response);

		} else {
			message = "ִ��ʧ��";
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}

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

		String sql = "select * from WEB_ORDERS t where 1=1";
		String rowSql = "select count(1) from WEB_ORDERS t where 1=1";
		int pageSize = 10;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		System.out.println("currentPage-->" + currentPage);

		PageModel<WebOrders> page = WebOrdersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.setAttribute("page", page);
		request.getSession().setAttribute("webusers", user);

		request.getRequestDispatcher("/jsp/weborderslist.jsp").forward(request, response);

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
