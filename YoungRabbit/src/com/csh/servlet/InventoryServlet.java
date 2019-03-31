package com.csh.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csh.dao.BaseDao;
//import com.csh.domain.Account;
import com.csh.domain.Inventory;
import com.csh.domain.Users;
import com.csh.service.InventoryService;
import com.csh.util.DateUtil;
import com.csh.util.PageModel;

/**
 * @author Xp Lyu
 *
 */
public class InventoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		String method = request.getParameter("method");
		// for inventory
		if (method.equals("showProductDetail")) {
			showInventoryDetail(request, response);
		} else if (method.equals("listInventory")) {
			findInventoryList(request, response);
		} else if (method.equals("showInventoryDetail")) {
			showInventoryDetail(request, response);
		} else if (method.equals("deleteInventory")) {
			deleteInventory(request, response);
		} else if (method.equals("addStock")) {
			addStock(request, response);
		} else if (method.equals("showStock")) {
			showStock(request, response);
		} else if (method.equals("listIncoming")) {
			findIncomingList(request, response);
		} else if (method.equals("confirmIncomingStock")) {
			confirmIncomingStock(request, response);
		} else if (method.equals("updateIncomingStock")) {
			updateIncomingStock(request, response);
		} else if (method == null) {// default
			findInventoryList(request, response);
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
		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		String GOOD_ID = request.getParameter("GOOD_ID");
		String detailSql = "SELECT B.*, " + "  WEB_GOODS_INVENTORY.INVENTORY_ID, " + "  WEB_GOODS_INVENTORY.SKU, "
				+ "  WEB_GOODS_INVENTORY.BARCODE, " + "  WEB_GOODS_INVENTORY.INCOMING, "
				+ "  WEB_GOODS_INVENTORY.INVENTORY_POLICY, " + "  WEB_GOODS_INVENTORY.QUANTITY, "
				+ "  WEB_GOODS_INVENTORY.SUPPLIER, " + "  WEB_GOODS_INVENTORY.INCOMING_ESTIM_ARRI_DATE, "
				+ "  WEB_GOODS_INVENTORY.WEIGHT_SKU, " + "  WEB_GOODS_INVENTORY.SERVICE_WAYS, "
				+ "  WEB_GOODS_INVENTORY.SHIPPING_MARK " + "FROM " + "  (SELECT A.*, "
				+ "    WEB_GOODS_KINDS.ORGA_NAME " + "  FROM " + "    (SELECT GOOD_ID, " + "      TITLE, "
				+ "      DESCRIPTION, " + "      TAX_MARK, " + "      ONLINE_STORE, " + "      ORGA_ID "
				+ "    FROM WEB_GOODS_BASIC_INFO " + "    WHERE GOOD_ID=? " + "    )A "
				+ "  INNER JOIN WEB_GOODS_KINDS ON　A.ORGA_ID = WEB_GOODS_KINDS.ORGA_ID " + "  )B "
				+ "INNER JOIN WEB_GOODS_INVENTORY " + "ON B.GOOD_ID = WEB_GOODS_INVENTORY.GOOD_ID";

		Object[] param = new Object[1];
		param[0] = GOOD_ID;

		// String orderSql = "SELECT * FROM WEB_ORDERS WHERE GOOD_ID=? ORDER BY
		// HAPPEN_DATE DESC";
		// List<InventoryOrders> invOrdlist =
		// InventoryService.findOrdersByGoodId(orderSql, param);
		// request.setAttribute("invOrdlist", invOrdlist);

		Inventory product = InventoryService.findProductDeatil(detailSql, param);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/jsp/products/inventoryDetail.jsp").forward(request, response);
	}

	/**
	 * @fun:generate inventory list page
	 * @author:Xp Lyu
	 * @date:Nov 19, 2016
	 */
	public void findInventoryList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
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
				+ "      FROM WEB_GOODS_BASIC_INFO " /* + "      WHERE WEB_GOODS_BASIC_INFO.USER_ID=? " */
				+ "      ) A " + "    INNER JOIN WEB_GOODS_INVENTORY " + "    ON A.GOOD_ID=WEB_GOODS_INVENTORY.GOOD_ID "
				+ "    ORDER BY ? DESC " + "    ) B " + "  ) " + "WHERE RN BETWEEN ? AND ? ";

		Object[] param = new Object[3];
		/* param[0] = ac.getUSER_ID(); */
		param[0] = "A.GOOD_ID";
		param[1] = startRow;
		param[2] = endRow;

		String rowSql = "SELECT COUNT(*) AS NUM " + "FROM " + "  (SELECT WEB_GOODS_BASIC_INFO.GOOD_ID "
				+ "  FROM WEB_GOODS_BASIC_INFO " /*
													 * +
													 * "  WHERE WEB_GOODS_BASIC_INFO.USER_ID= "
													 * + ac.getUSER_ID()
													 */ + "  ) A " + "INNER JOIN WEB_GOODS_INVENTORY "
				+ "ON A.GOOD_ID=WEB_GOODS_INVENTORY.GOOD_ID";

		PageModel<Inventory> avaiStock = InventoryService.findInventoryPageModel(currentPage, pageSize, listSql, rowSql,
				param);

		request.setAttribute("avaiStock", avaiStock);
		request.getRequestDispatcher("/jsp/products/inventoryList.jsp").forward(request, response);

	}

	public void findIncomingList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		int pageSize = 20;
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		int startRow = (currentPage - 1) * pageSize;
		int endRow = startRow + pageSize - 1;// include the end row

		String incomingListSql = "SELECT * " + "FROM " + "  (SELECT ROWNUM AS RN, " + "    B.* " + "  FROM "
				+ "    (SELECT A.*, " + "      WEB_GOODS_INVENTORY.INVENTORY_ID, " + "      WEB_GOODS_INVENTORY.SKU, "
				+ "      WEB_GOODS_INVENTORY.OUT_STOCK_MARK, " + "      WEB_GOODS_INVENTORY.INCOMING, "
				+ "      WEB_GOODS_INVENTORY.QUANTITY " + "    FROM " + "      (SELECT WEB_GOODS_BASIC_INFO.USER_ID, "
				+ "        WEB_GOODS_BASIC_INFO.GOOD_ID, " + "        WEB_GOODS_BASIC_INFO.TITLE, "
				+ "        WEB_GOODS_BASIC_INFO.DESCRIPTION, " + "        WEB_GOODS_BASIC_INFO.IMAGES_ID "
				+ "      FROM WEB_GOODS_BASIC_INFO "/*
													 * +
													 * "      WHERE WEB_GOODS_BASIC_INFO.USER_ID=? "
													 */ + "      ) A " + "    INNER JOIN WEB_GOODS_INVENTORY "
				+ "    ON A.GOOD_ID=WEB_GOODS_INVENTORY.GOOD_ID WHERE WEB_GOODS_INVENTORY.INCOMING_FLAG=1 "
				+ "    ORDER BY ? DESC " + "    ) B " + "  ) " + "WHERE RN BETWEEN ? AND ? ";

		Object[] param1 = new Object[3];
		/* param1[0] = ac.getUSER_ID(); */
		param1[0] = "A.GOOD_ID";
		param1[1] = startRow;
		param1[2] = endRow;

		String incomingRowSql = "SELECT COUNT(*) AS NUM " + "FROM " + "  (SELECT WEB_GOODS_BASIC_INFO.GOOD_ID "
				+ "  FROM WEB_GOODS_BASIC_INFO " /*
													 * +
													 * "  WHERE WEB_GOODS_BASIC_INFO.USER_ID= "
													 * + ac.getUSER_ID()
													 */ + "  ) A " + "INNER JOIN WEB_GOODS_INVENTORY "
				+ "ON A.GOOD_ID=WEB_GOODS_INVENTORY.GOOD_ID WHERE WEB_GOODS_INVENTORY.INCOMING_FLAG=1";

		PageModel<Inventory> inStock = InventoryService.findInventoryPageModel(currentPage, pageSize, incomingListSql,
				incomingRowSql, param1);

		request.setAttribute("inStock", inStock);
		request.getRequestDispatcher("/jsp/products/incomingList.jsp").forward(request, response);

	}

	public void confirmIncomingStock(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String GOOD_ID = request.getParameter("GOOD_ID");
		String detailSql = "SELECT B.*, " + "  WEB_GOODS_INVENTORY.INVENTORY_ID, " + "  WEB_GOODS_INVENTORY.SKU, "
				+ "  WEB_GOODS_INVENTORY.BARCODE, " + "  WEB_GOODS_INVENTORY.INCOMING, "
				+ "  WEB_GOODS_INVENTORY.INVENTORY_POLICY, " + "  WEB_GOODS_INVENTORY.QUANTITY, "
				+ "  WEB_GOODS_INVENTORY.SUPPLIER, " + "  WEB_GOODS_INVENTORY.INCOMING_ESTIM_ARRI_DATE, "
				+ "  WEB_GOODS_INVENTORY.WEIGHT_SKU, " + "  WEB_GOODS_INVENTORY.SERVICE_WAYS, "
				+ "  WEB_GOODS_INVENTORY.SHIPPING_MARK " + "FROM " + "  (SELECT A.*, "
				+ "    WEB_GOODS_KINDS.ORGA_NAME " + "  FROM " + "    (SELECT GOOD_ID, " + "      TITLE, "
				+ "      DESCRIPTION, " + "      TAX_MARK, " + "      ONLINE_STORE, " + "      ORGA_ID "
				+ "    FROM WEB_GOODS_BASIC_INFO " + "    WHERE GOOD_ID=? " + "    )A "
				+ "  INNER JOIN WEB_GOODS_KINDS ON　A.ORGA_ID = WEB_GOODS_KINDS.ORGA_ID " + "  )B "
				+ "INNER JOIN WEB_GOODS_INVENTORY " + "ON B.GOOD_ID = WEB_GOODS_INVENTORY.GOOD_ID";

		Object[] param = new Object[1];
		param[0] = GOOD_ID;

		Inventory product = InventoryService.findProductDeatil(detailSql, param);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/jsp/products/confirmIncomingStock.jsp").forward(request, response);
	}

	public void deleteInventory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		String INVENTORY_ID = request.getParameter("INVENTORY_ID");
		int flag = InventoryService.deleteInventory(INVENTORY_ID);
		if (flag == -1) {
			String message = "Faile to delete this inventory!!!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		} else {
			findInventoryList(request, response);
		}

	}

	public void addStock(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		String INVENTORY_ID = request.getParameter("invtoryID");
		String INCOMING = request.getParameter("inputIncomingStock");
		String SUPPLIER = request.getParameter("inputSupplier");
		String INCOMING_ESTIM_ARRI_DATE = request.getParameter("inputEstimatedDateOfArrival");

		String sql = "UPDATE WEB_GOODS_INVENTORY SET INCOMING=?,SUPPLIER=?,INCOMING_ESTIM_ARRI_DATE=?,INCOMING_FLAG=? WHERE INVENTORY_ID=?";
		Object[] param = new Object[5];
		param[0] = INCOMING;
		param[1] = SUPPLIER;
		param[2] = INCOMING_ESTIM_ARRI_DATE;
		param[3] = 1;
		param[4] = INVENTORY_ID;

		/* int result = */BaseDao.exeSql(sql, param);

		/*
		 * String message = ""; if (result == 1) { message =
		 * "Add stock success!"; } else { message = "Add stock failed!"; }
		 */
		/*
		 * // request.setAttribute("message", message);
		 */
		request.getRequestDispatcher("/products/inventory?method=listIncoming").forward(request, response);

	}

	public void updateIncomingStock(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		String INVENTORY_ID = request.getParameter("invtoryID");
		String QUANTITY = request.getParameter("inputWarehouseStock");
		String INCOMING = request.getParameter("inputIncomingStock");
		String SUPPLIER = request.getParameter("inputSupplier");
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String INCOMING_ARRI_DATE = format.format(date);

		String sql = "UPDATE WEB_GOODS_INVENTORY SET QUANTITY=?,INCOMING=?,SUPPLIER=?,INCOMING_ARRI_DATE=?,INCOMING_FLAG=? WHERE INVENTORY_ID=?";
		Object[] param = new Object[6];
		param[0] = Integer.parseInt(QUANTITY) + Integer.parseInt(INCOMING);
		param[1] = 0;
		param[2] = SUPPLIER;
		param[3] = INCOMING_ARRI_DATE;
		param[4] = 0;
		param[5] = INVENTORY_ID;

		/* int result = */BaseDao.exeSql(sql, param);

		/*
		 * String message = ""; if (result == 1) { message =
		 * "Add stock success!"; } else { message = "Add stock failed!"; }
		 */
		/*
		 * // request.setAttribute("message", message);
		 */
		request.getRequestDispatcher("/products/inventory?method=listInventory").forward(request, response);

	}

	public void showStock(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		String GOOD_ID = request.getParameter("GOOD_ID");
		String detailSql = "SELECT B.*, " + "  WEB_GOODS_INVENTORY.INVENTORY_ID, " + "  WEB_GOODS_INVENTORY.SKU, "
				+ "  WEB_GOODS_INVENTORY.BARCODE, " + "  WEB_GOODS_INVENTORY.INCOMING, "
				+ "  WEB_GOODS_INVENTORY.INVENTORY_POLICY, " + "  WEB_GOODS_INVENTORY.QUANTITY, "
				+ "  WEB_GOODS_INVENTORY.SUPPLIER, " + "  WEB_GOODS_INVENTORY.INCOMING_ESTIM_ARRI_DATE, "
				+ "  WEB_GOODS_INVENTORY.WEIGHT_SKU, " + "  WEB_GOODS_INVENTORY.SERVICE_WAYS, "
				+ "  WEB_GOODS_INVENTORY.SHIPPING_MARK " + "FROM " + "  (SELECT A.*, "
				+ "    WEB_GOODS_KINDS.ORGA_NAME " + "  FROM " + "    (SELECT GOOD_ID, " + "      TITLE, "
				+ "      DESCRIPTION, " + "      TAX_MARK, " + "      ONLINE_STORE, " + "      ORGA_ID "
				+ "    FROM WEB_GOODS_BASIC_INFO " + "    WHERE GOOD_ID=? " + "    )A "
				+ "  INNER JOIN WEB_GOODS_KINDS ON　A.ORGA_ID = WEB_GOODS_KINDS.ORGA_ID " + "  )B "
				+ "INNER JOIN WEB_GOODS_INVENTORY " + "ON B.GOOD_ID = WEB_GOODS_INVENTORY.GOOD_ID";

		Object[] param = new Object[1];
		param[0] = GOOD_ID;

		Inventory product = InventoryService.findProductDeatil(detailSql, param);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/jsp/products/addStock.jsp").forward(request, response);

	}

}
