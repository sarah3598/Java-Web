package com.csh.domain;

import java.io.Serializable;

public class Store implements Serializable {

	private static final long serialVersionUID = 6379109604059863248L;
	private int STORE_ID;
	private int USER_ID;
	private String EMAIL;
	private String PASSWORD;
	private String STORE_NAME;
	private int IMAGE_ID;
	private int HAPPEN_DATE;
	private int HAPPEN_TIME;
	private String LOGIN_ACCOUNT;
	private String BAK1;
	private String BAK2;

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
	 * @return the eMAIL
	 */
	public String getEMAIL() {
		return EMAIL;
	}

	/**
	 * @param eMAIL
	 *            the eMAIL to set
	 */
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	/**
	 * @return the pASSWORD
	 */
	public String getPASSWORD() {
		return PASSWORD;
	}

	/**
	 * @param pASSWORD
	 *            the pASSWORD to set
	 */
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	/**
	 * @return the sTORE_NAME
	 */
	public String getSTORE_NAME() {
		return STORE_NAME;
	}

	/**
	 * @param sTORE_NAME
	 *            the sTORE_NAME to set
	 */
	public void setSTORE_NAME(String sTORE_NAME) {
		STORE_NAME = sTORE_NAME;
	}

	/**
	 * @return the iMAGE_ID
	 */
	public int getIMAGE_ID() {
		return IMAGE_ID;
	}

	/**
	 * @param iMAGE_ID
	 *            the iMAGE_ID to set
	 */
	public void setIMAGE_ID(int iMAGE_ID) {
		IMAGE_ID = iMAGE_ID;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
