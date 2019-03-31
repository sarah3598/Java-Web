package com.csh.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.junit.Test;

import com.csh.dao.BaseDao;
import com.csh.domain.Inventory;
import com.csh.domain.ProductInfo;
import com.csh.domain.Users;
import com.csh.service.InventoryService;
import com.csh.service.ProductService;
import com.csh.util.DateUtil;
import com.csh.util.PageModel;

import net.sf.json.JSONArray;

/**
 * @author Xp Lyu
 *
 */
public class ProductsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		String method = request.getParameter("method");

		switch (method) {
		case "addProductInfo":
			addProductInfo(request, response);
			break;
		case "addSaleInfo":
			addSaleInfo(request, response);
			break;
		case "list":
			findProductList(request, response);
			break;
		case "listedit":
			productListEdit(request, response);
			break;
		case "editProduct":
			editProduct(request, response);
			break;
		case "detail":
			findProductDetail(request, response);
			break;
		case "delete":
			deleteProduct(request, response);
			break;
		case "update":
			deleteProduct(request, response);
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
	public void getInventoryNo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	public void showInventoryDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String GOOD_ID = request.getParameter("GOOD_ID");
		String detailSql = "SELECT B.*, " + "  WEB_GOODS_INVENTORY.INVENTORY_ID, " + "  WEB_GOODS_INVENTORY.SKU, "
				+ "  WEB_GOODS_INVENTORY.BARCODE, " + "  WEB_GOODS_INVENTORY.INVENTORY_POLICY, "
				+ "  WEB_GOODS_INVENTORY.QUANTITY, " + "  WEB_GOODS_INVENTORY.WEIGHT_SKU, "
				+ "  WEB_GOODS_INVENTORY.SERVICE_WAYS, " + "  WEB_GOODS_INVENTORY.SHIPPING_MARK " + "FROM "
				+ "  (SELECT A.*, " + "    WEB_GOODS_KINDS.ORGA_NAME " + "  FROM " + "    (SELECT GOOD_ID, "
				+ "      TITLE, " + "      PRICING, " + "      DESCRIPTION, " + "      TAX_MARK, "
				+ "      COMPARE_AT_PRICE, " + "      ONLINE_STORE, " + "      ORGA_ID "
				+ "    FROM WEB_GOODS_BASIC_INFO " + "    WHERE GOOD_ID=? " + "    )A "
				+ "  INNER JOIN WEB_GOODS_KINDS ON　A.ORGA_ID = WEB_GOODS_KINDS.ORGA_ID " + "  )B "
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
	public void findInventoryList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users ac = (Users)request.getSession().getAttribute("account");
		if (ac == null) {
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			return;
		}

		int pageSize = 20;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize - 1;// include the end row

		String listSql = "SELECT * " + "FROM " + "  (SELECT ROWNUM AS RN, " + "    B.* " + "  FROM "
				+ "    (SELECT A.*, " + "      WEB_GOODS_INVENTORY.INVENTORY_ID, " + "      WEB_GOODS_INVENTORY.SKU, "
				+ "      WEB_GOODS_INVENTORY.OUT_STOCK_MARK, " + "      WEB_GOODS_INVENTORY.INCOMING, "
				+ "      WEB_GOODS_INVENTORY.QUANTITY " + "    FROM " + "      (SELECT WEB_GOODS_BASIC_INFO.USER_ID, "
				+ "        WEB_GOODS_BASIC_INFO.GOOD_ID, " + "        WEB_GOODS_BASIC_INFO.TITLE, "
				+ "        WEB_GOODS_BASIC_INFO.DESCRIPTION, " + "        WEB_GOODS_BASIC_INFO.IMAGES_ID "
				+ "      FROM WEB_GOODS_BASIC_INFO " + "      WHERE WEB_GOODS_BASIC_INFO.USER_ID=? " + "      ) A "
				+ "    INNER JOIN WEB_GOODS_INVENTORY " + "    ON A.GOOD_ID=WEB_GOODS_INVENTORY.GOOD_ID "
				+ "    ORDER BY ? DESC " + "    ) B " + "  ) " + "WHERE RN BETWEEN ? AND ? ";

		Object[] param = new Object[4];
		param[0] = ac.getUserId();
		param[1] = "A.GOOD_ID";
		param[2] = startRow;
		param[3] = endRow;

		String rowSql = "SELECT COUNT(*) AS NUM " + "FROM " + "  (SELECT WEB_GOODS_BASIC_INFO.GOOD_ID "
				+ "  FROM WEB_GOODS_BASIC_INFO " + "  WHERE WEB_GOODS_BASIC_INFO.USER_ID= " + ac.getUserId() + "  ) A "
				+ "INNER JOIN WEB_GOODS_INVENTORY " + "ON A.GOOD_ID=WEB_GOODS_INVENTORY.GOOD_ID";

		PageModel<Inventory> page = ProductService.findInventoryPageModel(currentPage, pageSize, listSql, rowSql,
				param);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/jsp/products/inventoryList.jsp").forward(request, response);

	}

	// 鍒犻櫎鎶ュ悕淇℃伅
	public void deleteInventory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	public void getGoodId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	public void findProductDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users oper = (Users) request.getSession().getAttribute("webusers");
		long user_id = oper.getUserId();
		String goodId = request.getParameter("goodid");
		Object[] param = new Object[2];
		param[0] = user_id;
		param[1] = goodId;
		String sql = "SELECT WEB_GOODS_BASIC_INFO.*,WEB_IMAGE.IMAGE_PATH FROM WEB_GOODS_BASIC_INFO LEFT JOIN WEB_IMAGE ON WEB_GOODS_BASIC_INFO.IMAGES_ID=WEB_IMAGE.IMAGE_ID WHERE WEB_GOODS_BASIC_INFO.USER_ID=? AND WEB_GOODS_BASIC_INFO.GOOD_ID=?";

		ProductInfo product = ProductService.findProductDetail(sql, param);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/jsp/products/productDetail.jsp").forward(request, response);
	}

	/**
	 * function:get product list author:pc date:2017年4月13日
	 */
	public void findProductList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		int pageSize = 4;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String listSql = "SELECT * FROM (SELECT ROWNUM AS RN, WEB_GOODS_BASIC_INFO.GOOD_ID,WEB_GOODS_BASIC_INFO.TITLE,WEB_IMAGE.IMAGE_ID,WEB_IMAGE.IMAGE_PATH FROM WEB_GOODS_BASIC_INFO LEFT JOIN WEB_IMAGE ON WEB_GOODS_BASIC_INFO.IMAGES_ID=WEB_IMAGE.IMAGE_ID WHERE WEB_GOODS_BASIC_INFO.USER_ID=? AND HIDE_MARK='0') WHERE RN BETWEEN ? AND ? ";
		Object[] param = new Object[3];
		param[0] = oper.getUserId();
		param[1] = startRow;
		param[2] = endRow;

		String rowSql = "SELECT COUNT(*) FROM WEB_GOODS_BASIC_INFO WHERE HIDE_MARK=0 AND USER_ID=" + oper.getUserId();

		PageModel<ProductInfo> page = ProductService.findProductsPageModel(currentPage, pageSize, listSql, rowSql,
				param);
		request.setAttribute("product", page);
		request.getRequestDispatcher("/jsp/products/productList.jsp").forward(request, response);

	}

	/**
	 * function:edit product list author:pc date:2017年4月13日
	 */
	public void productListEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		int pageSize = 6;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String listSql = "SELECT * FROM (SELECT ROWNUM AS RN, WEB_GOODS_BASIC_INFO.GOOD_ID,WEB_GOODS_BASIC_INFO.TITLE,WEB_IMAGE.IMAGE_ID,WEB_IMAGE.IMAGE_PATH FROM WEB_GOODS_BASIC_INFO LEFT JOIN WEB_IMAGE ON WEB_GOODS_BASIC_INFO.IMAGES_ID=WEB_IMAGE.IMAGE_ID WHERE WEB_GOODS_BASIC_INFO.USER_ID=?) WHERE RN BETWEEN ? AND ? ";
		Object[] param = new Object[3];
		param[0] = oper.getUserId();
		param[1] = startRow;
		param[2] = endRow;

		String rowSql = "SELECT COUNT(*) FROM WEB_GOODS_BASIC_INFO WHERE USER_ID=" + oper.getUserId();

		PageModel<ProductInfo> page = ProductService.findProductsPageModel(currentPage, pageSize, listSql, rowSql,
				param);
		request.setAttribute("product", page);
		request.getRequestDispatcher("/jsp/products/productListEdit.jsp").forward(request, response);

	}

	public void addProductInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users oper = (Users) request.getSession().getAttribute("webusers");

		long user_id = oper.getUserId();
		String product_title = request.getParameter("product_title");
		String banned_countries = request.getParameter("banned_countries");
		String hide_mark = request.getParameter("hide_mark");
		int imageid = Integer.parseInt(request.getParameter("imageid"));
		String login_account = oper.getEmail();

		if (hide_mark == null) {
			hide_mark = "0";
		}

		// insert product info
		String sql1 = "INSERT INTO WEB_GOODS_BASIC_INFO(USER_ID,TITLE,BANNED_COUNTRIES,HIDE_MARK,IMAGES_ID,LOGIN_ACCOUNT) VALUES(?,?,?,?,?,?)";
		Object[] param = new Object[6];
		param[0] = user_id;
		param[1] = product_title;
		param[2] = banned_countries;
		param[3] = hide_mark;
		param[4] = imageid;
		param[5] = login_account;
		int flag1 = ProductService.addProductInfo(sql1, param);
		int flag2 = -1;

		// get the product id
		int good_id = -1;
		if (flag1 == 1) {
			Object[] param2 = new Object[4];
			param2[0] = user_id;
			param2[1] = product_title;
			param2[2] = banned_countries;
			param2[3] = imageid;
			String sql2 = "SELECT　GOOD_ID FROM WEB_GOODS_BASIC_INFO WHERE USER_ID=? AND TITLE=? AND BANNED_COUNTRIES=? AND IMAGES_ID=?";
			good_id = ProductService.getGoodIDFormInsertProductInfo(sql2, param);
		}

		// insert into inventory
		if (good_id >= 0) {
			String sql3 = "INSERT INTO WEB_GOODS_INVENTORY(GOOD_ID,QUANTITY,LOGIN_ACCOUNT) VALUES(?,?,?)";
			Object[] param3 = new Object[3];
			param3[0] = good_id;
			param3[1] = "0";// init quantity is zero
			param3[2] = login_account;
			flag2 = BaseDao.exeSql(sql3, param3);
		}

		// judge all sql execute
		String json = "{product:[";
		if (flag1 == 1 && flag2 == 1) {
			json = "{product:[{\"product_id\":" + good_id + "}]}";
			response.getWriter().print(json);
		} else {
			// TODO:rollback sql
		}

		// response.getWriter().print(json);

	}

	public void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	public void addSaleInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	// 鍒犻櫎鍟嗗搧
	public void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String goodId = request.getParameter("GOOD_ID");
		int i = ProductService.deleteProduct(goodId);
		if (i == -1) {
			String message = "鍒犻櫎澶辫触";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		} else {
			findProductList(request, response);
		}

	}



	

}
