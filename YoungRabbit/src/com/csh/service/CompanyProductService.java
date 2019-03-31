package com.csh.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.csh.dao.BaseDao;
import com.csh.domain.Inventory;
import com.csh.domain.ProductInfo;
import com.csh.util.DateUtil;
import com.csh.util.PageModel;

public class CompanyProductService {

	// by xiaopan
	public static int productDeleteRelative(String good_id) {
		String sql1 = "UPDATE WEB_GOODS_BASIC_INFO SET DELETE_FLAG=? WHERE GOOD_ID=?";
		Object[] param1 = new Object[2];
		param1[0] = 1;
		param1[1] = good_id;
		int flag = BaseDao.exeSql(sql1, param1);
		String sql2 = "UPDATE WEB_IMAGE SET DELETE_FLAG=? WHERE GOOD_ID=?";
		BaseDao.exeSql(sql2, param1);

		if (flag == -1) {
			return -1;
		}
		// i = BaseDao.exeSql(sql1, null);
		return flag;

	}

	// by xiaopan
	public static ResultSet getProductListRs(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		return rs;
	}

	// by xiaopan
	public static int getProductListRows(String rowContSql, Object[] param) {
		int rows = BaseDao.getRowCount(rowContSql, param);
		return rows;
	}

	public static List<ProductInfo> findProductList(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		List<ProductInfo> list = new ArrayList<ProductInfo>();
		try {
			while (rs.next()) {
				ProductInfo product = new ProductInfo();

				int GOOD_ID = rs.getInt("GOOD_ID");
				String TITLE = rs.getString("TITLE");
				// int PRODUCT_PRICE = rs.getInt("PRODUCT_PRICE");
				int IMAGE_ID = rs.getInt("IMAGE_ID");
				String IMAGE_PATH = rs.getString("IMAGE_PATH");

				product.setGOOD_ID(GOOD_ID);
				product.setTITLE(TITLE);
				// product.setPRODUCT_PRICE(PRODUCT_PRICE);
				product.setIMAGE_ID(IMAGE_ID);
				product.setIMAGE_PATH(IMAGE_PATH);

				list.add(product);
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

	public static ProductInfo findProductDetail(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		ProductInfo product = new ProductInfo();
		try {
			while (rs.next()) {
				int GOOD_ID = rs.getInt("GOOD_ID");
				String TITLE = rs.getString("TITLE");
				int PRODUCT_PRICE = rs.getInt("PRODUCT_PRICE");
				// int IMAGES_ID = rs.getInt("IMAGE_ID");
				String IMAGE_PATH = rs.getString("IMAGE_PATH");
				String POINTS = rs.getString("POINTS");
				String SALE_COMMISSION = rs.getString("SALE_COMMISSION");
				String VP_OVERRIDING = rs.getString("VP_OVERRIDING");
				String MANAGER_OVERRIDING = rs.getString("MANAGER_OVERRIDING");

				product.setGOOD_ID(GOOD_ID);
				product.setTITLE(TITLE);
				product.setPRODUCT_PRICE(PRODUCT_PRICE);
				// product.setIMAGES_ID(IMAGES_ID);
				product.setIMAGE_PATH(IMAGE_PATH);
				product.setPOINTS(POINTS);
				product.setSALE_COMMISSION(SALE_COMMISSION);
				product.setVP_OVERRIDING(VP_OVERRIDING);
				product.setMANAGER_OVERRIDING(MANAGER_OVERRIDING);

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
		return product;

	}

	public static ProductInfo getEditProductInitInfo(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		ProductInfo product = new ProductInfo();
		try {
			while (rs.next()) {
				String TITLE = rs.getString("TITLE");
				String BANNED_COUNTRIES = rs.getString("BANNED_COUNTRIES");
				String HIDE_MARK = rs.getString("HIDE_MARK");
				int IMAGE_ID = rs.getInt("IMAGE_ID");
				String CURRENCY = rs.getString("CURRENCY");
				int PRODUCT_PRICE = rs.getInt("PRODUCT_PRICE");
				String COMMISSION_MODEL = rs.getString("COMMISSION_MODEL");
				String SALE_COMMISSION = rs.getString("SALE_COMMISSION");
				String MANAGER_OVERRIDING = rs.getString("MANAGER_OVERRIDING");
				String VP_OVERRIDING = rs.getString("VP_OVERRIDING");
				String OWNERSHIP_COMMISSION = rs.getString("OWNERSHIP_COMMISSION");
				String WHOLESALE_MODEL = rs.getString("WHOLESALE_MODEL");
				int WHOLESALE_PRICE = rs.getInt("WHOLESALE_PRICE");
				int AGENT_PRICE = rs.getInt("AGENT_PRICE");

				product.setTITLE(TITLE);
				product.setBANNED_COUNTRIES(BANNED_COUNTRIES);
				product.setHIDE_MARK(HIDE_MARK);
				product.setIMAGE_ID(IMAGE_ID);
				product.setCURRENCY(CURRENCY);
				product.setPRODUCT_PRICE(PRODUCT_PRICE);
				product.setCOMMISSION_MODEL(COMMISSION_MODEL);
				product.setSALE_COMMISSION(SALE_COMMISSION);
				product.setMANAGER_OVERRIDING(MANAGER_OVERRIDING);
				product.setVP_OVERRIDING(VP_OVERRIDING);
				product.setOWNERSHIP_COMMISSION(OWNERSHIP_COMMISSION);
				product.setWHOLESALE_MODEL(WHOLESALE_MODEL);
				product.setWHOLESALE_PRICE(WHOLESALE_PRICE);
				product.setAGENT_PRICE(AGENT_PRICE);
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
		return product;
	}

	public static int getProducImageID(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		int image_id = -1;
		try {
			while (rs.next()) {
				image_id = rs.getInt("IMAGE_ID");

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
		return image_id;

	}

	public static int getGoodID(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		int good_id = -1;
		try {
			while (rs.next()) {
				good_id = rs.getInt("GOOD_ID");

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
		return good_id;

	}

	public static ResultSet getImageUrl(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		return rs;

	}

	// 查询商品列表分页对象
	public static PageModel<ProductInfo> findProductsPageModel(int currentPage, int pageSize, String sql, String rowContSql, Object[] param) {
		List<ProductInfo> list = findProductList(sql, param);
		int rowCount = BaseDao.getRowCount(rowContSql, null);

		PageModel<ProductInfo> pageModel = new PageModel<ProductInfo>();
		pageModel.setCurrentPage(currentPage);
		pageModel.setList(list);
		pageModel.setPageSize(pageSize);
		pageModel.setTotalRecord(rowCount);
		return pageModel;
	}

	// 删除商品条目
	public static int deleteProduct(String goodId) {
		String sql = "delete from WEB_GOODS_BASIC_INFO  where GOOD_ID=" + goodId;
		// String sql1= "delete from family_member WHERE Person_id="+goodId;
		int i = BaseDao.exeSql(sql, null);
		if (i == -1) {
			return -1;
		}
		// i = BaseDao.exeSql(sql1, null);
		return i;

	}

	// 获取下一条产品记录的good id
	public static int getNextGoodID() {
		// Date date = new Date(); // The current Date/time
		// String pattern = "yyyyMMddHHmmssSSS";
		// String dateStr = DateUtil.getDateString(date, pattern);
		String insert_seq = UUID.randomUUID().toString();// 插入安全码

		String sql = "INSERT INTO WEB_GOODS_BASIC_INFO(INSERT_SEQ)  VALUES(?) ";
		Object param[] = new Object[1];
		param[0] = insert_seq;

		int flag = BaseDao.exeSql(sql, param);
		int good_id = -1;
		if (flag == 1) {
			String sql1 = "SELECT GOOD_ID FROM WEB_GOODS_BASIC_INFO WHERE INSERT_SEQ=?";
			Connection con = BaseDao.getConn();
			ResultSet rs = BaseDao.getRs(con, sql1, param);
			try {
				while (rs.next()) {

					good_id = rs.getInt("GOOD_ID");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// i = BaseDao.exeSql(sql1, null);
		return good_id;

	}

	// 插入学生关系

	// 获取最大的person_id
	public static int maxGoodId() {
		String sql = "select max(person_id) from enroll_person ";
		return BaseDao.getRowCount(sql, null);
	}

	public static List<Inventory> getInventoryList(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		List<Inventory> list = new ArrayList<Inventory>();

		try {

			while (rs.next()) {
				Inventory inv = new Inventory();

				int USER_ID = rs.getInt("USER_ID");
				int GOOD_ID = rs.getInt("GOOD_ID");
				String TITLE = rs.getString("TITLE");
				String DESCRIPTION = rs.getString("DESCRIPTION");
				String IMAGES_ID = rs.getString("IMAGES_ID");
				int INVENTORY_ID = rs.getInt("INVENTORY_ID");
				String SKU = rs.getString("SKU");
				String OUT_STOCK_MARK = rs.getString("OUT_STOCK_MARK");
				String INCOMING = rs.getString("INCOMING");
				String QUANTITY = rs.getString("QUANTITY");

				inv.setUSER_ID(USER_ID);
				inv.setGOOD_ID(GOOD_ID);
				inv.setTITLE(TITLE);
				inv.setDESCRIPTION(DESCRIPTION);
				inv.setIMAGES_ID(IMAGES_ID);
				inv.setINVENTORY_ID(INVENTORY_ID);
				inv.setSKU(SKU);
				inv.setOUT_STOCK_MARK(OUT_STOCK_MARK);
				inv.setINCOMING(INCOMING);
				inv.setQUANTITY(QUANTITY);

				list.add(inv);
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

	public static int getInventoryRows(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		int rows = 0;
		try {
			if (rs.next()) {
				rows = rs.getInt("NUM");
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

	public static int addSingleProductInfo(String sql, Object[] param) {
		return BaseDao.exeSql(sql, param);
	}

	public static int addSaleInfo(String sql, Object[] param) {
		return BaseDao.exeSql(sql, param);
	}

	public static List<Inventory> findInventoryDetail(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs;
		rs = BaseDao.getRs(con, sql, param);
		List<Inventory> list = new ArrayList<Inventory>();

		try {
			while (rs.next()) {

				int GOOD_ID = rs.getInt("GOOD_ID");
				String SKU = rs.getString("SKU");
				String BARCODE = rs.getString("BARCODE");
				String INVENTORY_POLICY = rs.getString("INVENTORY_POLICY");
				String QUANTITY = rs.getString("QUANTITY");
				String INCOMING = rs.getString("INCOMING");
				String OUT_STOCK_MARK = rs.getString("OUT_STOCK_MARK");
				String WEIGHT_SKU = rs.getString("WEIGHT_SKU");
				String SERVICE_WAYS = rs.getString("SERVICE_WAYS");
				String SHIPPING_MARK = rs.getString("SHIPPING_MARK");
				String SSIZE = rs.getString("SSIZE");
				String COLOR = rs.getString("COLOR");
				String MATERIAL = rs.getString("MATERIAL");
				String REMARK = rs.getString("REMARK");
				int HAPPEN_DATE = rs.getInt("HAPPEN_DATE");
				int HAPPEN_TIME = rs.getInt("HAPPEN_TIME");
				String LOGIN_ACCOUNT = rs.getString("LOGIN_ACCOUNT");
				String BAK1 = rs.getString("BAK1");
				String BAK2 = rs.getString("BAK2");
				int INVENTORY_ID = rs.getInt("INVENTORY_ID");

				Inventory inv = new Inventory();
				inv.setGOOD_ID(GOOD_ID);
				inv.setSKU(SKU);
				inv.setBARCODE(BARCODE);
				inv.setINVENTORY_POLICY(INVENTORY_POLICY);
				inv.setQUANTITY(QUANTITY);
				inv.setINCOMING(INCOMING);
				inv.setOUT_STOCK_MARK(OUT_STOCK_MARK);
				inv.setWEIGHT_SKU(WEIGHT_SKU);
				inv.setSERVICE_WAYS(SERVICE_WAYS);
				inv.setSHIPPING_MARK(SHIPPING_MARK);
				inv.setSSIZE(SSIZE);
				inv.setCOLOR(COLOR);
				inv.setMATERIAL(MATERIAL);
				inv.setREMARK(REMARK);
				inv.setHAPPEN_DATE(HAPPEN_DATE);
				inv.setHAPPEN_TIME(HAPPEN_TIME);
				inv.setLOGIN_ACCOUNT(LOGIN_ACCOUNT);
				inv.setBAK1(BAK1);
				inv.setBAK2(BAK2);
				inv.setINVENTORY_ID(INVENTORY_ID);

				list.add(inv);

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

	public static int getGoodIDFormInsertProductInfo(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		int good_id = -1;
		try {
			while (rs.next()) {
				good_id = rs.getInt("GOOD_ID");
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

		return good_id;
	}

	/**
	 * @fun:page model
	 * @author:Xp Lyu
	 * @date:Nov 19, 2016
	 */
	public static PageModel<Inventory> findInventoryPageModel(int currentPage, int pageSize, String listSql, String rowSql, Object[] param) {

		int rowCount = getInventoryRows(rowSql, null);
		List<Inventory> invList = getInventoryList(listSql, param);

		PageModel<Inventory> pageModel = new PageModel<Inventory>();
		pageModel.setCurrentPage(currentPage);
		pageModel.setList(invList);
		pageModel.setPageSize(pageSize);
		pageModel.setTotalRecord(rowCount);
		return pageModel;
	}

	/**
	 * @fun:product detail page model
	 * @author:Xp Lyu
	 * @throws SQLException
	 * @date:Nov 20, 2016
	 */
	public static ProductInfo findProductDeatil(String detailSql, Object[] param) {

		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, detailSql, param);
		ProductInfo product = new ProductInfo();
		try {
			if (rs.next()) {
				String TITLE = rs.getString("TITLE");
				int PRODUCT_PRICE = rs.getInt("PRODUCT_PRICE");
				String DESCRIPTION = rs.getString("DESCRIPTION");
				String TAX_MARK = rs.getString("TAX_MARK");
				String ORGA_NAME = rs.getString("ORGA_NAME");
				String ONLINE_STORE = rs.getString("ONLINE_STORE");
				String SKU = rs.getString("SKU");
				String BARCODE = rs.getString("BARCODE");
				String INVENTORY_POLICY = rs.getString("INVENTORY_POLICY");
				String QUANTITY = rs.getString("QUANTITY");
				String WEIGHT_SKU = rs.getString("WEIGHT_SKU");
				String SERVICE_WAYS = rs.getString("SERVICE_WAYS");
				String SHIPPING_MARK = rs.getString("SHIPPING_MARK");

				product.setTITLE(TITLE);
				product.setPRODUCT_PRICE(PRODUCT_PRICE);
				product.setDESCRIPTION(DESCRIPTION);
				product.setTAX_MARK(TAX_MARK);
				product.setORGA_NAME(ORGA_NAME);
				product.setONLINE_STORE(ONLINE_STORE);
				product.setSKU(SKU);
				product.setBARCODE(BARCODE);
				product.setINVENTORY_POLICY(INVENTORY_POLICY);
				product.setQUANTITY(QUANTITY);
				product.setWEIGHT_SKU(WEIGHT_SKU);
				product.setSERVICE_WAYS(SERVICE_WAYS);
				product.setSHIPPING_MARK(SHIPPING_MARK);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}

	// 鍒犻櫎鎶ュ悕瀛︾敓
	public static int deleteInventory(String INVENTORY_ID) {
		String sql = "delete from WEB_GOODS_INVENTORY  where INVENTORY_ID=" + INVENTORY_ID;
		String sql1 = "delete  from family_member  WHERE Person_id=" + INVENTORY_ID;
		int i = BaseDao.exeSql(sql, null);
		if (i == -1) {
			return -1;
		}
		i = BaseDao.exeSql(sql1, null);
		return i;

	}

	// 鎻掑叆瀛︾敓鍏崇郴
	public static void insertInventory(List<Inventory> list) {
		int personId = maxInvertoryId();
		for (int i = 0; i < list.size(); i++) {
			String sql = "insert into family_member (telephone,position,unit,family_name,Person_id,relation) values (?,?,?,?,?,?)";
			Object[] param = new Object[6];
			// param[0] = list.get(i).getTelephone();
			// param[1] = list.get(i).getPosition();
			// param[2] = list.get(i).getUnit();
			// param[3] = list.get(i).getFamilyName();
			param[0] = personId;
			// param[5] = list.get(i).getRelation();
			BaseDao.exeSql(sql, param);
		}
	}

	// 鑾峰彇鏈�澶х殑person_id
	public static int maxInvertoryId() {
		String sql = "select max(person_id) from enroll_person ";
		return BaseDao.getRowCount(sql, null);
	}

}
