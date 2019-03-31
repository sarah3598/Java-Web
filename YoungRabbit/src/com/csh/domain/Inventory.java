package com.csh.domain;

import java.io.Serializable;
import java.util.List;

public class Inventory implements Serializable {

	private static final long serialVersionUID = 6379109604059863248L;
	private int GOOD_ID;
	private String SKU;
	private String BARCODE;
	private String INVENTORY_POLICY;
	private String QUANTITY;
	private String INCOMING;
	private String OUT_STOCK_MARK;
	private String WEIGHT_SKU;
	private String SERVICE_WAYS;
	private String SHIPPING_MARK;
	private String SSIZE;
	private String COLOR;
	private String MATERIAL;
	private String REMARK;
	private int HAPPEN_DATE;
	private int HAPPEN_TIME;
	private String LOGIN_ACCOUNT;
	private String BAK1;
	private String BAK2;
	private int INVENTORY_ID;

	private int USER_ID;
	private String TITLE;
	private String DESCRIPTION;
	private String IMAGES_ID;

	// product info
	private int goodId;
	private int userId;
	private int price;
	private String title;
	// private List<ProductInfo> products;
	private List<Inventory> INVENTORY;
	private List<Image> IMAGE;
	private List<GoodsKinds> GOODSKINDS;
	private List<Store> STORE;

	/* WEB_GOODS_BASIC_INFO */

	private String COLLECTION;

	private int ORGA_ID;
	private int STORE_ID;

	private int PRICING;
	private int COMPARE_AT_PRICE;
	private String TAX_MARK;
	private String BUY_BUTTON_MARK;
	private String ONLINE_STORE;
	private String STATUS;

	/* WEB_GOODS_KINDS */
	// private int ORGA_ID ;
	private String ORGA_NAME;
	private String ORGA_DISCRIPTION;

	/**
	 * @return the gOOD_ID
	 */
	public int getGOOD_ID() {
		return GOOD_ID;
	}

	/**
	 * @param gOOD_ID
	 *            the gOOD_ID to set
	 */
	public void setGOOD_ID(int gOOD_ID) {
		GOOD_ID = gOOD_ID;
	}

	/**
	 * @return the sKU
	 */
	public String getSKU() {
		return SKU;
	}

	/**
	 * @param sKU
	 *            the sKU to set
	 */
	public void setSKU(String sKU) {
		SKU = sKU;
	}

	/**
	 * @return the bARCODE
	 */
	public String getBARCODE() {
		return BARCODE;
	}

	/**
	 * @param bARCODE
	 *            the bARCODE to set
	 */
	public void setBARCODE(String bARCODE) {
		BARCODE = bARCODE;
	}

	/**
	 * @return the iNVENTORY_POLICY
	 */
	public String getINVENTORY_POLICY() {
		return INVENTORY_POLICY;
	}

	/**
	 * @param iNVENTORY_POLICY
	 *            the iNVENTORY_POLICY to set
	 */
	public void setINVENTORY_POLICY(String iNVENTORY_POLICY) {
		INVENTORY_POLICY = iNVENTORY_POLICY;
	}

	/**
	 * @return the qUANTITY
	 */
	public String getQUANTITY() {
		return QUANTITY;
	}

	/**
	 * @param qUANTITY
	 *            the qUANTITY to set
	 */
	public void setQUANTITY(String qUANTITY) {
		QUANTITY = qUANTITY;
	}

	/**
	 * @return the iNCOMING
	 */
	public String getINCOMING() {
		return INCOMING;
	}

	/**
	 * @param iNCOMING
	 *            the iNCOMING to set
	 */
	public void setINCOMING(String iNCOMING) {
		INCOMING = iNCOMING;
	}

	/**
	 * @return the oUT_STOCK_MARK
	 */
	public String getOUT_STOCK_MARK() {
		return OUT_STOCK_MARK;
	}

	/**
	 * @param oUT_STOCK_MARK
	 *            the oUT_STOCK_MARK to set
	 */
	public void setOUT_STOCK_MARK(String oUT_STOCK_MARK) {
		OUT_STOCK_MARK = oUT_STOCK_MARK;
	}

	/**
	 * @return the wEIGHT_SKU
	 */
	public String getWEIGHT_SKU() {
		return WEIGHT_SKU;
	}

	/**
	 * @param wEIGHT_SKU
	 *            the wEIGHT_SKU to set
	 */
	public void setWEIGHT_SKU(String wEIGHT_SKU) {
		WEIGHT_SKU = wEIGHT_SKU;
	}

	/**
	 * @return the sERVICE_WAYS
	 */
	public String getSERVICE_WAYS() {
		return SERVICE_WAYS;
	}

	/**
	 * @param sERVICE_WAYS
	 *            the sERVICE_WAYS to set
	 */
	public void setSERVICE_WAYS(String sERVICE_WAYS) {
		SERVICE_WAYS = sERVICE_WAYS;
	}

	/**
	 * @return the sHIPPING_MARK
	 */
	public String getSHIPPING_MARK() {
		return SHIPPING_MARK;
	}

	/**
	 * @param sHIPPING_MARK
	 *            the sHIPPING_MARK to set
	 */
	public void setSHIPPING_MARK(String sHIPPING_MARK) {
		SHIPPING_MARK = sHIPPING_MARK;
	}

	/**
	 * @return the sSIZE
	 */
	public String getSSIZE() {
		return SSIZE;
	}

	/**
	 * @param sSIZE
	 *            the sSIZE to set
	 */
	public void setSSIZE(String sSIZE) {
		SSIZE = sSIZE;
	}

	/**
	 * @return the cOLOR
	 */
	public String getCOLOR() {
		return COLOR;
	}

	/**
	 * @param cOLOR
	 *            the cOLOR to set
	 */
	public void setCOLOR(String cOLOR) {
		COLOR = cOLOR;
	}

	/**
	 * @return the mATERIAL
	 */
	public String getMATERIAL() {
		return MATERIAL;
	}

	/**
	 * @param mATERIAL
	 *            the mATERIAL to set
	 */
	public void setMATERIAL(String mATERIAL) {
		MATERIAL = mATERIAL;
	}

	/**
	 * @return the rEMARK
	 */
	public String getREMARK() {
		return REMARK;
	}

	/**
	 * @param rEMARK
	 *            the rEMARK to set
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	/**
	 * @return the hAPPEN_DATE
	 */
	public int getHAPPEN_DATE() {
		return HAPPEN_DATE;
	}

	/**
	 * @param hAPPEN_DATE
	 *            the hAPPEN_DATE to set
	 */
	public void setHAPPEN_DATE(int hAPPEN_DATE) {
		HAPPEN_DATE = hAPPEN_DATE;
	}

	/**
	 * @return the hAPPEN_TIME
	 */
	public int getHAPPEN_TIME() {
		return HAPPEN_TIME;
	}

	/**
	 * @param hAPPEN_TIME
	 *            the hAPPEN_TIME to set
	 */
	public void setHAPPEN_TIME(int hAPPEN_TIME) {
		HAPPEN_TIME = hAPPEN_TIME;
	}

	/**
	 * @return the lOGIN_ACCOUNT
	 */
	public String getLOGIN_ACCOUNT() {
		return LOGIN_ACCOUNT;
	}

	/**
	 * @param lOGIN_ACCOUNT
	 *            the lOGIN_ACCOUNT to set
	 */
	public void setLOGIN_ACCOUNT(String lOGIN_ACCOUNT) {
		LOGIN_ACCOUNT = lOGIN_ACCOUNT;
	}

	/**
	 * @return the bAK1
	 */
	public String getBAK1() {
		return BAK1;
	}

	/**
	 * @param bAK1
	 *            the bAK1 to set
	 */
	public void setBAK1(String bAK1) {
		BAK1 = bAK1;
	}

	/**
	 * @return the bAK2
	 */
	public String getBAK2() {
		return BAK2;
	}

	/**
	 * @param bAK2
	 *            the bAK2 to set
	 */
	public void setBAK2(String bAK2) {
		BAK2 = bAK2;
	}

	/**
	 * @return the iNVENTORY_ID
	 */
	public int getINVENTORY_ID() {
		return INVENTORY_ID;
	}

	/**
	 * @param iNVENTORY_ID
	 *            the iNVENTORY_ID to set
	 */
	public void setINVENTORY_ID(int iNVENTORY_ID) {
		INVENTORY_ID = iNVENTORY_ID;
	}

	/**
	 * @return the uSER_ID
	 */
	public int getUSER_ID() {
		return USER_ID;
	}

	/**
	 * @param uSER_ID
	 *            the uSER_ID to set
	 */
	public void setUSER_ID(int uSER_ID) {
		USER_ID = uSER_ID;
	}

	/**
	 * @return the tITLE
	 */
	public String getTITLE() {
		return TITLE;
	}

	/**
	 * @param tITLE
	 *            the tITLE to set
	 */
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	/**
	 * @return the dESCRIPTION
	 */
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	/**
	 * @param dESCRIPTION
	 *            the dESCRIPTION to set
	 */
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	/**
	 * @return the iMAGES_ID
	 */
	public String getIMAGES_ID() {
		return IMAGES_ID;
	}

	/**
	 * @param iMAGES_ID
	 *            the iMAGES_ID to set
	 */
	public void setIMAGES_ID(String iMAGES_ID) {
		IMAGES_ID = iMAGES_ID;
	}

	/**
	 * @return the goodId
	 */
	public int getGoodId() {
		return goodId;
	}

	/**
	 * @param goodId
	 *            the goodId to set
	 */
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the iNVENTORY
	 */
	public List<Inventory> getINVENTORY() {
		return INVENTORY;
	}

	/**
	 * @param iNVENTORY
	 *            the iNVENTORY to set
	 */
	public void setINVENTORY(List<Inventory> iNVENTORY) {
		INVENTORY = iNVENTORY;
	}

	/**
	 * @return the iMAGE
	 */
	public List<Image> getIMAGE() {
		return IMAGE;
	}

	/**
	 * @param iMAGE
	 *            the iMAGE to set
	 */
	public void setIMAGE(List<Image> iMAGE) {
		IMAGE = iMAGE;
	}

	/**
	 * @return the gOODSKINDS
	 */
	public List<GoodsKinds> getGOODSKINDS() {
		return GOODSKINDS;
	}

	/**
	 * @param gOODSKINDS
	 *            the gOODSKINDS to set
	 */
	public void setGOODSKINDS(List<GoodsKinds> gOODSKINDS) {
		GOODSKINDS = gOODSKINDS;
	}

	/**
	 * @return the sTORE
	 */
	public List<Store> getSTORE() {
		return STORE;
	}

	/**
	 * @param sTORE
	 *            the sTORE to set
	 */
	public void setSTORE(List<Store> sTORE) {
		STORE = sTORE;
	}

	/**
	 * @return the cOLLECTION
	 */
	public String getCOLLECTION() {
		return COLLECTION;
	}

	/**
	 * @param cOLLECTION
	 *            the cOLLECTION to set
	 */
	public void setCOLLECTION(String cOLLECTION) {
		COLLECTION = cOLLECTION;
	}

	/**
	 * @return the oRGA_ID
	 */
	public int getORGA_ID() {
		return ORGA_ID;
	}

	/**
	 * @param oRGA_ID
	 *            the oRGA_ID to set
	 */
	public void setORGA_ID(int oRGA_ID) {
		ORGA_ID = oRGA_ID;
	}

	/**
	 * @return the sTORE_ID
	 */
	public int getSTORE_ID() {
		return STORE_ID;
	}

	/**
	 * @param sTORE_ID
	 *            the sTORE_ID to set
	 */
	public void setSTORE_ID(int sTORE_ID) {
		STORE_ID = sTORE_ID;
	}

	/**
	 * @return the pRICING
	 */
	public int getPRICING() {
		return PRICING;
	}

	/**
	 * @param pRICING
	 *            the pRICING to set
	 */
	public void setPRICING(int pRICING) {
		PRICING = pRICING;
	}

	/**
	 * @return the cOMPARE_AT_PRICE
	 */
	public int getCOMPARE_AT_PRICE() {
		return COMPARE_AT_PRICE;
	}

	/**
	 * @param cOMPARE_AT_PRICE
	 *            the cOMPARE_AT_PRICE to set
	 */
	public void setCOMPARE_AT_PRICE(int cOMPARE_AT_PRICE) {
		COMPARE_AT_PRICE = cOMPARE_AT_PRICE;
	}

	/**
	 * @return the tAX_MARK
	 */
	public String getTAX_MARK() {
		return TAX_MARK;
	}

	/**
	 * @param tAX_MARK
	 *            the tAX_MARK to set
	 */
	public void setTAX_MARK(String tAX_MARK) {
		TAX_MARK = tAX_MARK;
	}

	/**
	 * @return the bUY_BUTTON_MARK
	 */
	public String getBUY_BUTTON_MARK() {
		return BUY_BUTTON_MARK;
	}

	/**
	 * @param bUY_BUTTON_MARK
	 *            the bUY_BUTTON_MARK to set
	 */
	public void setBUY_BUTTON_MARK(String bUY_BUTTON_MARK) {
		BUY_BUTTON_MARK = bUY_BUTTON_MARK;
	}

	/**
	 * @return the oNLINE_STORE
	 */
	public String getONLINE_STORE() {
		return ONLINE_STORE;
	}

	/**
	 * @param oNLINE_STORE
	 *            the oNLINE_STORE to set
	 */
	public void setONLINE_STORE(String oNLINE_STORE) {
		ONLINE_STORE = oNLINE_STORE;
	}

	/**
	 * @return the sTATUS
	 */
	public String getSTATUS() {
		return STATUS;
	}

	/**
	 * @param sTATUS
	 *            the sTATUS to set
	 */
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	/**
	 * @return the oRGA_NAME
	 */
	public String getORGA_NAME() {
		return ORGA_NAME;
	}

	/**
	 * @param oRGA_NAME
	 *            the oRGA_NAME to set
	 */
	public void setORGA_NAME(String oRGA_NAME) {
		ORGA_NAME = oRGA_NAME;
	}

	/**
	 * @return the oRGA_DISCRIPTION
	 */
	public String getORGA_DISCRIPTION() {
		return ORGA_DISCRIPTION;
	}

	/**
	 * @param oRGA_DISCRIPTION
	 *            the oRGA_DISCRIPTION to set
	 */
	public void setORGA_DISCRIPTION(String oRGA_DISCRIPTION) {
		ORGA_DISCRIPTION = oRGA_DISCRIPTION;
	}

	/**
	 * @return the bAK
	 */
	public String getBAK() {
		return BAK;
	}

	/**
	 * @param bAK
	 *            the bAK to set
	 */
	public void setBAK(String bAK) {
		BAK = bAK;
	}

	/**
	 * @return the sUPPLIER
	 */
	public String getSUPPLIER() {
		return SUPPLIER;
	}

	/**
	 * @param sUPPLIER
	 *            the sUPPLIER to set
	 */
	public void setSUPPLIER(String sUPPLIER) {
		SUPPLIER = sUPPLIER;
	}

	/**
	 * @return the iNCOMING_ESTIM_ARRI_DATE
	 */
	public String getINCOMING_ESTIM_ARRI_DATE() {
		return INCOMING_ESTIM_ARRI_DATE;
	}

	/**
	 * @param iNCOMING_ESTIM_ARRI_DATE
	 *            the iNCOMING_ESTIM_ARRI_DATE to set
	 */
	public void setINCOMING_ESTIM_ARRI_DATE(String iNCOMING_ESTIM_ARRI_DATE) {
		INCOMING_ESTIM_ARRI_DATE = iNCOMING_ESTIM_ARRI_DATE;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// private int HAPPEN_DATE ;
	// private int HAPPEN_TIME ;
	// private String LOGIN_ACCOUNT ;
	private String BAK;
	// private String REMARK ;
	private String SUPPLIER;
	private String INCOMING_ESTIM_ARRI_DATE;

}
