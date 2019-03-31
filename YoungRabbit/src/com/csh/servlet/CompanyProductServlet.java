package com.csh.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csh.dao.BaseDao;
import com.csh.domain.Inventory;
import com.csh.domain.ProductInfo;
import com.csh.domain.Users;
import com.csh.service.CompanyProductService;
import com.csh.service.InventoryService;
import com.csh.service.JsonService;
import com.csh.service.ProductService;
import com.csh.util.DateUtil;
import com.csh.util.PageModel;
import com.csh.util.Pagination;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Xp Lyu
 *
 */
public class CompanyProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if(!LoginControlPublicServlet.LoginSessionValidate(request, response,1))return;
		String method = request.getParameter("method");
		String OP = "";
		switch (method) {
		case "product_add_single":
			addSingleProductInfo(request, response);
			break;
		case "product_list":
			findProductList(request, response);
			break;
		case "product_detail":
			findProductDetail(request, response);
			break;
		case "product_delete":// 注意此处是产品下架
			productDeleteRelative(request, response);
			break;
		case "product_update_single":
			productUpdateSingle(request, response);
			break;
		case "product_get_image_url":
			getImageUrl(request, response);
			break;

		case "product_stock_avai_list":
			findProductStockAvaiList(request, response);
			break;
		case "product_stock_add":
			OP = request.getParameter("OP");
			switch (OP) {
			case "1":
				updateProductStock(request, response);
				break;
			case "2":
				addProductStock(request, response);
				break;
			}
			break;
		case "product_stock_incoming_list":
			findProductStockIncomingList(request, response);
			break;
		case "product_stock_incoming_receive":
			OP = request.getParameter("OP");
			switch (OP) {
			case "1":
				receiveProductIncoming(request, response);
				break;
			case "2":
				rejectProductIncoming(request, response);
				break;
			}
			break;
		default:
			findProductList(request, response);
			break;
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 鑾峰彇鎶ュ悕鍙�
	public void getInventoryNo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "SELECT count(1) from Inventory_person t where t.InventoryNo LIKE ? ";
		String dateString = DateUtil.getDateString(new Date(), "yyyyMMdd");
		Object[] param = new Object[1];
		param[0] = "%" + dateString + "%";
		int size = BaseDao.getRowCount(sql, param);
		size++;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(dateString + "_" + size);
	}

	// 鎵撳嵃棰勮
	public void showInventoryDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String GOOD_ID = request.getParameter("GOOD_ID");
		String detailSql = "SELECT B.*, " + "  WEB_GOODS_INVENTORY.INVENTORY_ID, " + "  WEB_GOODS_INVENTORY.SKU, " + "  WEB_GOODS_INVENTORY.BARCODE, " + "  WEB_GOODS_INVENTORY.INVENTORY_POLICY, " + "  WEB_GOODS_INVENTORY.QUANTITY, " + "  WEB_GOODS_INVENTORY.WEIGHT_SKU, " + "  WEB_GOODS_INVENTORY.SERVICE_WAYS, " + "  WEB_GOODS_INVENTORY.SHIPPING_MARK " + "FROM " + "  (SELECT A.*, " + "    WEB_GOODS_KINDS.ORGA_NAME " + "  FROM " + "    (SELECT GOOD_ID, " + "      TITLE, " + "      PRICING, " + "      DESCRIPTION, " + "      TAX_MARK, " + "      COMPARE_AT_PRICE, " + "      ONLINE_STORE, " + "      ORGA_ID " + "    FROM WEB_GOODS_BASIC_INFO " + "    WHERE GOOD_ID=? " + "    )A " + "  INNER JOIN WEB_GOODS_KINDS ON　A.ORGA_ID = WEB_GOODS_KINDS.ORGA_ID " + "  )B "
				+ "INNER JOIN WEB_GOODS_INVENTORY " + "ON B.GOOD_ID = WEB_GOODS_INVENTORY.GOOD_ID";

		Object[] param = new Object[1];
		param[0] = GOOD_ID;

		ProductInfo product = ProductService.findProductDeatil(detailSql, param);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/jsp/products/productDetail.jsp").forward(request, response);
	}

	/**
	 * @fun:generate inventory list page
	 * @author:Xp Lyu
	 * @date:Nov 19, 2016
	 */
	public void findInventoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users ac = (Users) request.getSession().getAttribute("account");
		if (ac == null) {
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			return;
		}

		int pageSize = 20;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize - 1;// include the end row

		String listSql = "SELECT * " + "FROM " + "  (SELECT ROWNUM AS RN, " + "    B.* " + "  FROM " + "    (SELECT A.*, " + "      WEB_GOODS_INVENTORY.INVENTORY_ID, " + "      WEB_GOODS_INVENTORY.SKU, " + "      WEB_GOODS_INVENTORY.OUT_STOCK_MARK, " + "      WEB_GOODS_INVENTORY.INCOMING, " + "      WEB_GOODS_INVENTORY.QUANTITY " + "    FROM " + "      (SELECT WEB_GOODS_BASIC_INFO.USER_ID, " + "        WEB_GOODS_BASIC_INFO.GOOD_ID, " + "        WEB_GOODS_BASIC_INFO.TITLE, " + "        WEB_GOODS_BASIC_INFO.DESCRIPTION, " + "        WEB_GOODS_BASIC_INFO.IMAGES_ID " + "      FROM WEB_GOODS_BASIC_INFO " + "      WHERE WEB_GOODS_BASIC_INFO.USER_ID=? " + "      ) A " + "    INNER JOIN WEB_GOODS_INVENTORY " + "    ON A.GOOD_ID=WEB_GOODS_INVENTORY.GOOD_ID " + "    ORDER BY ? DESC " + "    ) B "
				+ "  ) " + "WHERE RN BETWEEN ? AND ? ";

		Object[] param = new Object[4];
		param[0] = ac.getUserId();
		param[1] = "A.GOOD_ID";
		param[2] = startRow;
		param[3] = endRow;

		String rowSql = "SELECT COUNT(*) AS NUM " + "FROM " + "  (SELECT WEB_GOODS_BASIC_INFO.GOOD_ID " + "  FROM WEB_GOODS_BASIC_INFO " + "  WHERE WEB_GOODS_BASIC_INFO.USER_ID= " + ac.getUserId() + "  ) A " + "INNER JOIN WEB_GOODS_INVENTORY " + "ON A.GOOD_ID=WEB_GOODS_INVENTORY.GOOD_ID";

		PageModel<Inventory> page = ProductService.findInventoryPageModel(currentPage, pageSize, listSql, rowSql, param);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/jsp/products/inventoryList.jsp").forward(request, response);

	}

	// 鍒犻櫎鎶ュ悕淇℃伅
	public void deleteInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String INVENTORY_ID = request.getParameter("INVENTORY_ID");
		int flag = InventoryService.deleteInventory(INVENTORY_ID);
		if (flag == -1) {
			String message = "鍒犻櫎澶辫触";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		} else {
			findInventoryList(request, response);
		}

	}

	// 鑾峰彇鍟嗗搧缂栧彿
	public void getGoodId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "SELECT count(1) from WEB_GOODS_BASIC_INFO where GOOD_ID LIKE ? ";
		String dateString = DateUtil.getDateString(new Date(), "yyyyMMdd");
		Object[] param = new Object[1];
		param[0] = "%" + dateString + "%";
		int size = BaseDao.getRowCount(sql, param);
		size++;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(dateString + "_" + size);
	}

	public void findProductDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String GOOD_ID = request.getParameter("GOOD_ID");
		Object[] param = new Object[1];
		param[0] = GOOD_ID;
		String sql = "SELECT * FROM WEB_GOODS_BASIC_INFO WHERE GOOD_ID=?";
		JSONArray arr=BaseDao.getRsJSONArray(sql, param);
		
		String msg="";
		String normal_json="";
		if(arr.length()>0){
			msg="成功";
			normal_json=JsonService.getNormalJson(arr.toString(), msg);
		}else{
			msg="失败";
			normal_json=JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public void findProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getProductListData(request);
		request.setAttribute("product_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_product_list.jsp").forward(request, response);
	}

	public void findProductStockAvaiList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getProductStockAvaiListData(request);
		request.setAttribute("stock_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_product_stock_avai_list.jsp").forward(request, response);
	}

	public void findProductStockIncomingList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getProductStockIncomingListData(request);
		request.setAttribute("stock_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_product_stock_incoming_list.jsp").forward(request, response);
	}

	public void getImageUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String IAMGE_ID = request.getParameter("IMAGE_ID");
		String sql = "SELECT * FROM WEB_IMAGE_URL WHERE IMAGE_ID=?";
		Object[] param = new Object[1];
		param[0] = IAMGE_ID;

		JSONArray arr = BaseDao.getRsJSONArray(sql, param);

		String normal_json = "";
		String msg = "";
		String json_data = "";
		if (arr.length() > 0) {

			msg = "图片加载成功！";
			normal_json = JsonService.getOperateResultNormalJson("true", arr.toString(), msg);

		} else {

			msg = "图片加载失败！";
			json_data = JsonService.convertKeyValToJsonObject("success", "false");
			normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);
		}

		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public JSONArray getProductListData(HttpServletRequest request) throws NumberFormatException {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,WEB_GOODS_BASIC_INFO.* FROM WEB_GOODS_BASIC_INFO WHERE DELETE_FLAG=? ORDER BY RN ASC";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[3];
		param[0] = 0;
		param[1] = startRow;
		param[2] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_GOODS_BASIC_INFO WHERE DELETE_FLAG=?";
		Object[] param1 = new Object[1];
		param1[0] = 0;

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
	public static JSONArray getProductDetailData(HttpServletRequest request) throws NumberFormatException {

		String GOOD_ID = request.getParameter("GOOD_ID");
		String sql = "SELECT * FROM WEB_GOODS_BASIC_INFO WHERE GOOD_ID=?";
		Object[] param = new Object[1];
		param[0] = GOOD_ID;

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql, param);

		return jsonarr;
	}

	public JSONArray getProductStockAvaiListData(HttpServletRequest request) throws NumberFormatException {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.TITLE,B.* FROM WEB_GOODS_BASIC_INFO A LEFT JOIN WEB_GOODS_INVENTORY B ON A.GOOD_ID=B.GOOD_ID WHERE A.DELETE_FLAG=? AND B.NOW_STOCK_QUANTITY>=? ORDER BY A.GOOD_ID ASC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[4];
		param[0] = 0;
		param[1] = 0;
		param[2] = startRow;
		param[3] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_GOODS_BASIC_INFO A LEFT JOIN WEB_GOODS_INVENTORY B ON A.GOOD_ID=B.GOOD_ID WHERE A.DELETE_FLAG=0 AND B.NOW_STOCK_QUANTITY>=0";

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	public JSONArray getProductStockIncomingListData(HttpServletRequest request) throws NumberFormatException {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT A.TITLE,B.* FROM WEB_GOODS_BASIC_INFO A LEFT JOIN WEB_GOODS_INCOMING B ON A.GOOD_ID=B.GOOD_ID WHERE A.DELETE_FLAG=0 AND B.RECEIVE_FLAG=0 ORDER BY A.GOOD_ID ASC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM WEB_GOODS_BASIC_INFO A LEFT JOIN WEB_GOODS_INCOMING B ON A.GOOD_ID=B.GOOD_ID WHERE A.DELETE_FLAG=0 AND B.RECEIVE_FLAG=0";

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	/**
	 * function:edit product list author:pc date:2017年4月13日
	 */
	public void productListEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		int pageSize = 6;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String listSql = "SELECT * FROM (SELECT ROWNUM AS RN, WEB_GOODS_BASIC_INFO.GOOD_ID,WEB_GOODS_BASIC_INFO.TITLE,WEB_IMAGE.IMAGE_ID,WEB_IMAGE.IMAGE_PATH FROM WEB_GOODS_BASIC_INFO LEFT JOIN WEB_IMAGE ON WEB_GOODS_BASIC_INFO.IMAGES_ID=WEB_IMAGE.IMAGE_ID WHERE WEB_GOODS_BASIC_INFO.USER_ID=?) WHERE RN BETWEEN ? AND ? ";
		Object[] param = new Object[3];
		param[0] = oper.getUserId();
		param[1] = startRow;
		param[2] = endRow;

		String rowSql = "SELECT COUNT(*) FROM WEB_GOODS_BASIC_INFO WHERE USER_ID=" + oper.getUserId();

		PageModel<ProductInfo> page = ProductService.findProductsPageModel(currentPage, pageSize, listSql, rowSql, param);
		request.setAttribute("product", page);
		request.getRequestDispatcher("/jsp/products/productListEdit.jsp").forward(request, response);

	}

	public void updateProductStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object LOGIN_ACCOUNT = LoginControlPublicServlet.getSessionKeyValue(request, response, "EMAIL");
		
		String GOOD_ID = request.getParameter("GOOD_ID");
		String NOW_STOCK_QUANTITY = request.getParameter("NOW_STOCK_QUANTITY");

		// 更新库存信息
		String sql1 = "UPDATE WEB_GOODS_INVENTORY SET NOW_STOCK_QUANTITY=?,LOGIN_ACCOUNT=? WHERE GOOD_ID=?";
		Object[] param = new Object[3];
		param[0] = NOW_STOCK_QUANTITY;
		param[1] = LOGIN_ACCOUNT;
		param[2] = GOOD_ID;

		int flag1 = BaseDao.exeSql(sql1, param);
		String normal_json = "";
		String msg = "";

		if (flag1 > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getProductStockAvaiListData(request);
			msg = "更新成功！";
			normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);

		} else {
			msg = "更新失败！";
			normal_json = JsonService.getOperateResultNormalJson("false", null, msg);
		}
		JsonService.ResponseJson(request, response, normal_json);

	}

	public void receiveProductIncoming(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object LOGIN_ACCOUNT = LoginControlPublicServlet.getSessionKeyValue(request, response, "EMAIL");
		
		String INCOMING_ID = request.getParameter("INCOMING_ID");
		String GOOD_ID = request.getParameter("GOOD_ID");
		String INCOMING_RECEIPT_IMAGE_ID = request.getParameter("INCOMING_RECEIPT_IMAGE_ID");
		String REAL_INCOMING_QUANTITY = request.getParameter("REAL_INCOMING_QUANTITY");
		String TIMESTAMP = DateUtil.getTimastampString();

		// 确认收货
		String sql1 = "UPDATE WEB_GOODS_INCOMING SET INCOMING_RECEIPT_IMAGE_ID=?,REAL_INCOMING_QUANTITY=?,LOGIN_ACCOUNT=?,REAL_INCOMING_ARRI_TIMESTAMP=?,RECEIVE_FLAG=? WHERE INCOMING_ID=?";
		Object[] param1 = new Object[6];
		param1[0] = INCOMING_RECEIPT_IMAGE_ID;
		param1[1] = REAL_INCOMING_QUANTITY;
		param1[2] = LOGIN_ACCOUNT;
		param1[3] = TIMESTAMP;
		param1[4] = 2;// 货物已签收，进货完成
		param1[5] = INCOMING_ID;
		int flag1 = BaseDao.exeSql(sql1, param1);

		// 增加WEB_GOODS_INVENTORY NOW_STOCK_QUANTITY
		String sql4 = "UPDATE WEB_GOODS_INVENTORY SET NOW_STOCK_QUANTITY=NOW_STOCK_QUANTITY+? WHERE GOOD_ID=?";
		Object[] param4 = new Object[2];
		param4[0] = REAL_INCOMING_QUANTITY;
		param4[1] = GOOD_ID;
		int flag4 = BaseDao.exeSql(sql4, param4);

		// 查询该商品是否还有进货订单，若没有则修改WEB_GOODS_INVENTORY INCOMING_FLAG=0 表示已经没有进货的订单
		String sql2 = "SELECT COUNT(1) FROM WEB_GOODS_INCOMING WHERE GOOD_ID=? AND RECEIVE_FLAG=?";
		Object[] param2 = new Object[2];
		param2[0] = GOOD_ID;
		param2[1] = 0;
		int flag2 = BaseDao.exeSql(sql2, param2);

		if (flag2 == 0) {
			// 更新WEB_GOODS_INVENTORY INCOMING_FLAG=0 表示已经没有进货的订单
			String sql3 = "UPDATE WEB_GOODS_INVENTORY SET INCOMING_FLAG=0 WHERE GOOD_ID=" + GOOD_ID;
			BaseDao.exeSql(sql3, null);
		}

		String normal_json = "";
		String msg = "";

		if (flag1 > 0 && flag4 > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getProductStockIncomingListData(request);
			msg = "收货成功！";
			normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);

		} else {
			msg = "收货失败！";
			normal_json = JsonService.getOperateResultNormalJson("false", null, msg);
		}
		JsonService.ResponseJson(request, response, normal_json);

	}

	public void rejectProductIncoming(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object LOGIN_ACCOUNT = LoginControlPublicServlet.getSessionKeyValue(request, response, "EMAIL");
		
		String INCOMING_ID = request.getParameter("INCOMING_ID");
		String REJECT_REASON = request.getParameter("REJECT_REASON");


		// 更新信息
		String sql1 = "UPDATE WEB_GOODS_INCOMING SET RECEIVE_FLAG=?,LOGIN_ACCOUNT=?,REJECT_REASON=? WHERE INCOMING_ID=?";
		Object[] param = new Object[3];
		param[0] = 1;
		param[1] = LOGIN_ACCOUNT;
		param[2] = REJECT_REASON;
		param[3] = INCOMING_ID;

		int flag1 = BaseDao.exeSql(sql1, param);
		String normal_json = "";
		String msg = "";

		if (flag1 > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getProductStockIncomingListData(request);
			msg = "拒收货成功！";
			normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);

		} else {
			msg = "拒收货失败！";
			normal_json = JsonService.getOperateResultNormalJson("false", null, msg);
		}
		JsonService.ResponseJson(request, response, normal_json);

	}

	public void addProductStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object LOGIN_ACCOUNT = LoginControlPublicServlet.getSessionKeyValue(request, response, "EMAIL");
		
		String GOOD_ID = request.getParameter("GOOD_ID");
		// String NOW_STOCK_QUANTITY =
		// request.getParameter("NOW_STOCK_QUANTITY");
		String PLAN_INCOMING_QUANTITY = request.getParameter("PLAN_INCOMING_QUANTITY");
		String SUPPLIER = request.getParameter("SUPPLIER");
		String INCOMING_ESTIM_ARRI_DATE = request.getParameter("INCOMING_ESTIM_ARRI_DATE");
		
		// 获得当前日期和时间
		String timestamp = DateUtil.getTimastampString();

		// 更新进货信息表
		String sql1 = "INSERT INTO WEB_GOODS_INCOMING(GOOD_ID,PLAN_INCOMING_QUANTITY,SUPPLIER,INCOMING_ESTIM_ARRI_TIMESTAMP,LOGIN_ACCOUNT,INCOMING_TIMESTAMP,RECEIVE_FLAG) VALUES(?,?,?,?,?,?,?)";
		Object[] param1 = new Object[7];
		param1[0] = GOOD_ID;
		param1[1] = PLAN_INCOMING_QUANTITY;
		param1[2] = SUPPLIER;
		param1[3] = INCOMING_ESTIM_ARRI_DATE;
		param1[4] = LOGIN_ACCOUNT;
		param1[5] = timestamp;
		param1[6] = 0;

		// 更新库存表中已进货字段
		String sql2 = "UPDATE WEB_GOODS_INVENTORY SET INCOMING_FLAG=? WHERE GOOD_ID=?";
		Object[] param2 = new Object[2];
		param2[0] = 1;
		param2[1] = GOOD_ID;

		int flag1 = BaseDao.exeSql(sql1, param1);
		int flag2 = BaseDao.exeSql(sql2, param2);
		String normal_json = "";
		String msg = "";

		if (flag1 > 0 && flag2 > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getProductStockAvaiListData(request);
			msg = "添加进货单成功！";
			normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);

		} else {
			msg = "添加进货单失败！";
			normal_json = JsonService.getOperateResultNormalJson("false", null, msg);
		}
		JsonService.ResponseJson(request, response, normal_json);

	}

	// add by xiaopan
	public void addSingleProductInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object user_id = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");
		Object LOGIN_ACCOUNT = LoginControlPublicServlet.getSessionKeyValue(request, response, "EMAIL");

		String GOOD_ID = request.getParameter("GOOD_ID");
		String HIDE_MARK = request.getParameter("HIDE_MARK");
		String TITLE = request.getParameter("TITLE");
		String BANNED_COUNTRIES = request.getParameter("BANNED_COUNTRIES");
		String CURRENCY = request.getParameter("CURRENCY");
		String PRODUCT_PRICE = request.getParameter("PRODUCT_PRICE");
		String COMMISSION_MODEL = request.getParameter("COMMISSION_MODEL");

		String SALE_COMMISSION = request.getParameter("SALE_COMMISSION");
		String SS_COMMISSION = request.getParameter("SS_COMMISSION");
		String GL_COMMISSION = request.getParameter("GL_COMMISSION");
		String MA_COMMISSION = request.getParameter("MA_COMMISSION");
		String VP_COMMISSION = request.getParameter("VP_COMMISSION");
		String OWNERSHIP_COMMISSION = request.getParameter("OWNERSHIP_COMMISSION");

		String WHOLESALE_MODEL = request.getParameter("WHOLESALE_MODEL");
		String WHOLESALE_PRICE = request.getParameter("WHOLESALE_PRICE");
		String AGENT_PRICE = request.getParameter("AGENT_PRICE");

		HIDE_MARK = HIDE_MARK == null ? "0" : "1";
		COMMISSION_MODEL = COMMISSION_MODEL == null ? "0" : "1";
		WHOLESALE_MODEL = WHOLESALE_MODEL == null ? "0" : "1";

		String GL_OVERRIDING = request.getParameter("GL_OVERRIDING");
		String MA_OVERRIDING = request.getParameter("MA_OVERRIDING");
		String VP_OVERRIDING = request.getParameter("VP_OVERRIDING");
		
		String PRODUCT_WEIGHT = request.getParameter("PRODUCT_WEIGHT");
		String SHIPPING_PRICE = request.getParameter("SHIPPING_PRICE");

		// insert product info
		String sql1 = "UPDATE WEB_GOODS_BASIC_INFO SET USER_ID=?,HIDE_MARK=?,TITLE=?,BANNED_COUNTRIES=?,CURRENCY=?,PRODUCT_PRICE=?,COMMISSION_MODEL=?,SALE_COMMISSION=?,SS_COMMISSION=?,GL_COMMISSION=?,MA_COMMISSION=?,VP_COMMISSION=?,OWNERSHIP_COMMISSION=?,WHOLESALE_MODEL=?,WHOLESALE_PRICE=?,AGENT_PRICE=?,LOGIN_ACCOUNT=?,GL_OVERRIDING=?,MA_OVERRIDING=?,VP_OVERRIDING=?,PRODUCT_WEIGHT=?,SHIPPING_PRICE=? WHERE GOOD_ID=?";
		Object[] param = new Object[23];
		param[0] = user_id;
		param[1] = HIDE_MARK;
		param[2] = TITLE;
		param[3] = BANNED_COUNTRIES;
		param[4] = CURRENCY;
		param[5] = PRODUCT_PRICE;
		param[6] = COMMISSION_MODEL;
		param[7] = SALE_COMMISSION;
		param[8] = SS_COMMISSION;
		param[9] = GL_COMMISSION;
		param[10] = MA_COMMISSION;
		param[11] = VP_COMMISSION;
		param[12] = OWNERSHIP_COMMISSION;
		param[13] = WHOLESALE_MODEL;
		param[14] = WHOLESALE_PRICE;
		param[15] = AGENT_PRICE;
		param[16] = LOGIN_ACCOUNT;

		param[17] = GL_OVERRIDING;
		param[18] = MA_OVERRIDING;
		param[19] = VP_OVERRIDING;
		param[20] = PRODUCT_WEIGHT;
		param[21] = SHIPPING_PRICE;
		param[22] = GOOD_ID;

		int flag1 = BaseDao.exeSql(sql1, param);

		String sql3 = "INSERT INTO WEB_GOODS_INVENTORY(GOOD_ID,NOW_STOCK_QUANTITY,INCOMING_FLAG,LOGIN_ACCOUNT) VALUES(?,?,?,?)";
		Object[] param3 = new Object[4];
		param3[0] = GOOD_ID;
		param3[1] = 0;// 初始库存为0
		param3[2] = 0;// 当前没有进货
		param3[3] = LOGIN_ACCOUNT;
		int flag2 = BaseDao.exeSql(sql3, param3);

		String normal_json = "";
		String json_data = "";
		String msg = "";
		// 一个产品的基本信息添加成功，初始化库存
		if (flag1 > 0 && flag2 > 0) {
			msg = "产品添加成功！";
			json_data = JsonService.convertKeyValToJsonObject("success", "true");
			normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);

		} else {
			msg = "产品添加失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);

	}

	// add by xiaopan
	public void productUpdateSingle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Object user_id = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");
		Object LOGIN_ACCOUNT = LoginControlPublicServlet.getSessionKeyValue(request, response, "EMAIL");

		String GOOD_ID = request.getParameter("GOOD_ID");
		String HIDE_MARK = request.getParameter("HIDE_MARK");
		String TITLE = request.getParameter("TITLE");
		String BANNED_COUNTRIES = request.getParameter("BANNED_COUNTRIES");
		String CURRENCY = request.getParameter("CURRENCY");
		String PRODUCT_PRICE = request.getParameter("PRODUCT_PRICE");
		String COMMISSION_MODEL = request.getParameter("COMMISSION_MODEL");

		String SALE_COMMISSION = request.getParameter("SALE_COMMISSION");
		String SS_COMMISSION = request.getParameter("SS_COMMISSION");
		String GL_COMMISSION = request.getParameter("GL_COMMISSION");
		String MA_COMMISSION = request.getParameter("MA_COMMISSION");
		String VP_COMMISSION = request.getParameter("VP_COMMISSION");
		String OWNERSHIP_COMMISSION = request.getParameter("OWNERSHIP_COMMISSION");

		String WHOLESALE_MODEL = request.getParameter("WHOLESALE_MODEL");
		String WHOLESALE_PRICE = request.getParameter("WHOLESALE_PRICE");
		String AGENT_PRICE = request.getParameter("AGENT_PRICE");

		HIDE_MARK = HIDE_MARK == null ? "0" : "1";
		COMMISSION_MODEL = COMMISSION_MODEL == null ? "0" : "1";
		WHOLESALE_MODEL = WHOLESALE_MODEL == null ? "0" : "1";

		String GL_OVERRIDING = request.getParameter("GL_OVERRIDING");
		String MA_OVERRIDING = request.getParameter("MA_OVERRIDING");
		String VP_OVERRIDING = request.getParameter("VP_OVERRIDING");
		
		String PRODUCT_WEIGHT = request.getParameter("PRODUCT_WEIGHT");
		String SHIPPING_PRICE = request.getParameter("SHIPPING_PRICE");

		// update product info
		String sql = "UPDATE WEB_GOODS_BASIC_INFO SET USER_ID=?,HIDE_MARK=?,TITLE=?,BANNED_COUNTRIES=?,CURRENCY=?,PRODUCT_PRICE=?,COMMISSION_MODEL=?,SALE_COMMISSION=?,SS_COMMISSION=?,GL_COMMISSION=?,MA_COMMISSION=?,VP_COMMISSION=?,OWNERSHIP_COMMISSION=?,WHOLESALE_MODEL=?,WHOLESALE_PRICE=?,AGENT_PRICE=?,LOGIN_ACCOUNT=?,GL_OVERRIDING=?,MA_OVERRIDING=?,VP_OVERRIDING=?,PRODUCT_WEIGHT=?,SHIPPING_PRICE=? WHERE GOOD_ID=?";
		Object[] param = new Object[23];
		param[0] = user_id;
		param[1] = HIDE_MARK;
		param[2] = TITLE;
		param[3] = BANNED_COUNTRIES;
		param[4] = CURRENCY;
		param[5] = PRODUCT_PRICE;
		param[6] = COMMISSION_MODEL;
		param[7] = SALE_COMMISSION;
		param[8] = SS_COMMISSION;
		param[9] = GL_COMMISSION;
		param[10] = MA_COMMISSION;
		param[11] = VP_COMMISSION;
		param[12] = OWNERSHIP_COMMISSION;
		param[13] = WHOLESALE_MODEL;
		param[14] = WHOLESALE_PRICE;
		param[15] = AGENT_PRICE;
		param[16] = LOGIN_ACCOUNT;

		param[17] = GL_OVERRIDING;
		param[18] = MA_OVERRIDING;
		param[19] = VP_OVERRIDING;
		param[20] = PRODUCT_WEIGHT;
		param[21] = SHIPPING_PRICE;
		param[22] = GOOD_ID;

		int flag1 = BaseDao.exeSql(sql, param);
		String normal_json = "";
		String msg = "";
		// 一个产品的基本信息更新成功
		if (flag1 > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getProductListData(request);
			msg = "产品更新成功！";
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else {
			msg = "产品更新失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);

	}

	public void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int good_id = Integer.parseInt(request.getParameter("good_id"));
		Users oper = (Users) request.getSession().getAttribute("webusers");
		long user_id = oper.getUserId();

		// get product info
		String sql1 = "SELECT  TITLE,BANNED_COUNTRIES,HIDE_MARK,IMAGES_ID,";
		sql1 += "CURRENCY,PRODUCT_PRICE,COMMISSION_MODEL,";
		sql1 += "SALE_COMMISSION,MANAGER_OVERRIDING,VP_OVERRIDING,OWNERSHIP_COMMISSION,";
		sql1 += "WHOLESALE_MODEL,WHOLESALE_PRICE,AGENT_PRICE FROM WEB_GOODS_BASIC_INFO ";
		sql1 += "WHERE USER_ID=? AND GOOD_ID=? ";

		Object[] param1 = new Object[2];
		param1[0] = user_id;
		param1[1] = good_id;

		ProductInfo prodinfo = ProductService.getEditProductInitInfo(sql1, param1);
		prodinfo.setGOOD_ID(good_id);
		String image_path = "";
		if (prodinfo != null) {
			int image_id = prodinfo.getIMAGE_ID();

			String sql2 = "SELECT　IMAGE_PATH FROM WEB_IMAGE WHERE IMAGE_ID=?";
			Object[] param2 = new Object[1];
			param2[0] = image_id;
			image_path = ProductService.getImagePathByImageID(sql2, param2);
		} else {
			// TODO:get image id failed!!!
		}

		if (image_path != null) {
			prodinfo.setIMAGE_PATH(image_path);
		} else {
			// TODO:display a default error image
		}

		request.setAttribute("product", prodinfo);
		request.getRequestDispatcher("/jsp/products/editProduct.jsp").forward(request, response);

	}

	public void addSaleInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Users oper = (Users) request.getSession().getAttribute("webusers");

		// int user_id = oper.getUSER_ID();
		String currency = request.getParameter("currency");
		String product_price = request.getParameter("product_price");
		String commission_model = request.getParameter("commission_model");
		String sale_commission = request.getParameter("sale_commission");
		String manager_overriding = request.getParameter("manager_overriding");
		String vp_overriding = request.getParameter("vp_overriding");
		String ownership_commission = request.getParameter("ownership_commission");
		String wholesale_model = request.getParameter("wholesale_model");
		String whole_price = request.getParameter("whole_price");
		String agent_price = request.getParameter("agent_price");
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		// String login_account = oper.getEMAIL();

		// insert product info
		String sql1 = "UPDATE WEB_GOODS_BASIC_INFO SET CURRENCY=?,PRODUCT_PRICE=?,COMMISSION_MODEL=?,";
		sql1 += "SALE_COMMISSION=?,MANAGER_OVERRIDING=?,VP_OVERRIDING=?,OWNERSHIP_COMMISSION=?,";
		sql1 += "WHOLESALE_MODEL=?,WHOLESALE_PRICE=?,AGENT_PRICE=? ";
		sql1 += "WHERE GOOD_ID=?";
		Object[] param = new Object[11];
		param[0] = currency;
		param[1] = product_price;
		param[2] = commission_model;
		param[3] = sale_commission;
		param[4] = manager_overriding;
		param[5] = vp_overriding;
		param[6] = ownership_commission;
		param[7] = wholesale_model;
		param[8] = whole_price;
		param[9] = agent_price;
		param[10] = product_id;

		int flag1 = ProductService.addSaleInfo(sql1, param);

		// judge sql execute
		String json = "";
		if (flag1 == 1) {
			json = "{msg:[{\"success\":\"Add product sale info success!\"}]}";
			response.getWriter().print(json);
		} else {
			// TODO:rollback sql
		}

	}

	// add by xiaopan
	public void productDeleteRelative(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String GOOD_ID = request.getParameter("GOOD_ID");

		String sql1 = "UPDATE WEB_GOODS_BASIC_INFO SET DELETE_FLAG=? WHERE GOOD_ID=?";
		Object[] param1 = new Object[2];
		param1[0] = 1;
		param1[1] = GOOD_ID;
		int flag1 = BaseDao.exeSql(sql1, param1);

		String sql2 = "UPDATE WEB_IMAGE SET DELETE_FLAG=? WHERE GOOD_ID=?";
		int flag2 = BaseDao.exeSql(sql2, param1);

		String normal_json = "";
		String msg = "";

		if (flag1 > 0 && flag2 > 0) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getProductListData(request);
			msg = "删除成功！";
			normal_json = JsonService.getNormalJson(jsonarr1.toString(), msg);

		} else {
			msg = "删除失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		JsonService.ResponseJson(request, response, normal_json);

	}

}
