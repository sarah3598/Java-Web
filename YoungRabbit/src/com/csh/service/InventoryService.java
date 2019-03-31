package com.csh.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.csh.dao.BaseDao;
import com.csh.domain.Inventory;

import com.csh.util.PageModel;

public class InventoryService {

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

	public static PageModel<Inventory> findInventoryPageModel(int currentPage, int pageSize, String listSql,
			String rowSql, Object[] param) {

		int rowCount = getInventoryRows(rowSql, null);
		List<Inventory> invList = getInventoryList(listSql, param);

		PageModel<Inventory> pageModel = new PageModel<Inventory>();
		pageModel.setCurrentPage(currentPage);
		pageModel.setList(invList);
		pageModel.setPageSize(pageSize);
		pageModel.setTotalRecord(rowCount);
		return pageModel;
	}

	public static Inventory findProductDeatil(String detailSql, Object[] param) {

		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, detailSql, param);
		Inventory product = new Inventory();
		try {
			if (rs.next()) {
				int INVENTORY_ID = rs.getInt("INVENTORY_ID");
				String TITLE = rs.getString("TITLE");
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
				String INCOMING = rs.getString("INCOMING");
				String SUPPLIER = rs.getString("SUPPLIER");
				String INCOMING_ESTIM_ARRI_DATE = rs.getString("INCOMING_ESTIM_ARRI_DATE");

				product.setINVENTORY_ID(INVENTORY_ID);
				product.setTITLE(TITLE);
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
				product.setINCOMING(INCOMING);
				product.setSUPPLIER(SUPPLIER);
				product.setINCOMING_ESTIM_ARRI_DATE(INCOMING_ESTIM_ARRI_DATE);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}

	/*//public static List<InventoryOrders> findOrdersByGoodId(String ordersSql, Object[] param) {

		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, ordersSql, param);
		List<InventoryOrders> invOrdList = new ArrayList<InventoryOrders>();
		try {
			if (rs.next()) {
				int ORDER_ID = rs.getInt("ORDER_ID");
				int GOOD_ID = rs.getInt("GOOD_ID");
				int CUSTOMER_ID = rs.getInt(" CUSTOMER_ID");
				String MARKET_PRICE = rs.getString(" MARKET_PRICE");
				String COMPARE_AT_PRICE = rs.getString("COMPARE_AT_PRICE");
				String CHARGE_TAXES = rs.getString("CHARGE_TAXES");
				String COST_PRICE = rs.getString("COST_PRICE");
				String DISCOUNT_MONEY = rs.getString("DISCOUNT_MONEY");
				String SHIPPING_PRICE = rs.getString("SHIPPING_PRICE");
				String TOTAL_CHARGE = rs.getString("TOTAL_CHARGE");
				String EMAIL_INVOICE = rs.getString("EMAIL_INVOICE");
				String ACCEPT_PAYMENT = rs.getString("ACCEPT_PAYMENT");
				String PAYMENT_WAYS = rs.getString("PAYMENT_WAYS");
				String STATUS = rs.getString("STATUS");
				String TAGS = rs.getString("TAGS");
				int HAPPEN_DATE = rs.getInt("HAPPEN_DATE");
				int HAPPEN_TIME = rs.getInt("HAPPEN_TIME");
				String LOGIN_ACCOUNT = rs.getString("LOGIN_ACCOUNT");
				String BAK = rs.getString(" BAK");
				String BAK1 = rs.getString(" BAK1");
				String STATISTICAL_CURR = rs.getString("STATISTICAL_CURR");
				String SOLD_TO = rs.getString("SOLD_TO");
				String EXCHG_RATE = rs.getString("EXCHG_RATE");
				String ALT_TAX_CLASS = rs.getString("ALT_TAX_CLASS");
				String MASTER_CONTR = rs.getString("MASTER_CONTR");
				String TAX_DEPART_CTY = rs.getString("TAX_DEPART_CTY");
				String SMENR = rs.getString("SMENR");
				String MATERIAL_ID = rs.getString("MATERIAL_ID");
				String SALES_UNIT = rs.getString("SALES_UNIT");
				String SALES_QTY_NUM = rs.getString(" SALES_QTY_NUM");
				String SALES_QTY_DENOM = rs.getString("SALES_QTY_DENOM");
				String GROSS_WEIGHT = rs.getString("GROSS_WEIGHT");
				String NET_WEIGHT = rs.getString(" NET_WEIGHT");
				String UNIT_OF_WEIGHT = rs.getString(" UNIT_OF_WEIGHT");
				String VOLUME = rs.getString(" VOLUME");
				String VOLUME_UNIT = rs.getString("VOLUME_UNIT");
				String STGE_LOC = rs.getString(" STGE_LOC");
				String KNUMA_AG = rs.getString(" KNUMA_AG");

				InventoryOrders invOrd = new InventoryOrders();
				invOrd.setORDER_ID(ORDER_ID);
				invOrd.setGOOD_ID(GOOD_ID);
				invOrd.setCUSTOMER_ID(CUSTOMER_ID);
				invOrd.setMARKET_PRICE(MARKET_PRICE);
				invOrd.setCOMPARE_AT_PRICE(COMPARE_AT_PRICE);
				invOrd.setCHARGE_TAXES(CHARGE_TAXES);
				invOrd.setCOST_PRICE(COST_PRICE);
				invOrd.setDISCOUNT_MONEY(DISCOUNT_MONEY);
				invOrd.setSHIPPING_PRICE(SHIPPING_PRICE);
				invOrd.setTOTAL_CHARGE(TOTAL_CHARGE);
				invOrd.setEMAIL_INVOICE(EMAIL_INVOICE);
				invOrd.setACCEPT_PAYMENT(ACCEPT_PAYMENT);
				invOrd.setPAYMENT_WAYS(PAYMENT_WAYS);
				invOrd.setSTATUS(STATUS);
				invOrd.setTAGS(TAGS);
				invOrd.setHAPPEN_DATE(HAPPEN_DATE);
				invOrd.setHAPPEN_TIME(HAPPEN_TIME);
				invOrd.setLOGIN_ACCOUNT(LOGIN_ACCOUNT);
				invOrd.setBAK(BAK);
				invOrd.setBAK1(BAK1);
				invOrd.setSTATISTICAL_CURR(STATISTICAL_CURR);
				invOrd.setSOLD_TO(SOLD_TO);
				invOrd.setEXCHG_RATE(EXCHG_RATE);
				invOrd.setALT_TAX_CLASS(ALT_TAX_CLASS);
				invOrd.setMASTER_CONTR(MASTER_CONTR);
				invOrd.setTAX_DEPART_CTY(TAX_DEPART_CTY);
				invOrd.setSMENR(SMENR);
				invOrd.setMATERIAL_ID(MATERIAL_ID);
				invOrd.setSALES_UNIT(SALES_UNIT);
				invOrd.setSALES_QTY_NUM(SALES_QTY_NUM);
				invOrd.setSALES_QTY_DENOM(SALES_QTY_DENOM);
				invOrd.setGROSS_WEIGHT(GROSS_WEIGHT);
				invOrd.setNET_WEIGHT(NET_WEIGHT);
				invOrd.setUNIT_OF_WEIGHT(UNIT_OF_WEIGHT);
				invOrd.setVOLUME(VOLUME);
				invOrd.setVOLUME_UNIT(VOLUME_UNIT);
				invOrd.setSTGE_LOC(STGE_LOC);
				invOrd.setKNUMA_AG(KNUMA_AG);
				invOrdList.add(invOrd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return invOrdList;
	}*/

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

	public static int maxInvertoryId() {
		String sql = "select max(person_id) from enroll_person ";
		return BaseDao.getRowCount(sql, null);
	}

}
