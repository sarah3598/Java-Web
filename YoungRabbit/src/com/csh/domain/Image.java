package com.csh.domain;

import java.io.Serializable;

public class Image implements Serializable {

	private static final long serialVersionUID = 6379109604059863248L;
	private int IMAGE_ID;
	private String IMAGE_PATH;
	private String IMAGE_KINDS;
	private int HAPPEN_DATE;
	private int HAPPEN_TIME;
	private String LOGIN_ACCOUNT;
	private String BAK;
	private String BAK1;
	/**
	 * @return the iMAGE_ID
	 */
	public int getIMAGE_ID() {
		return IMAGE_ID;
	}
	/**
	 * @param iMAGE_ID the iMAGE_ID to set
	 */
	public void setIMAGE_ID(int iMAGE_ID) {
		IMAGE_ID = iMAGE_ID;
	}
	/**
	 * @return the iMAGE_PATH
	 */
	public String getIMAGE_PATH() {
		return IMAGE_PATH;
	}
	/**
	 * @param iMAGE_PATH the iMAGE_PATH to set
	 */
	public void setIMAGE_PATH(String iMAGE_PATH) {
		IMAGE_PATH = iMAGE_PATH;
	}
	/**
	 * @return the iMAGE_KINDS
	 */
	public String getIMAGE_KINDS() {
		return IMAGE_KINDS;
	}
	/**
	 * @param iMAGE_KINDS the iMAGE_KINDS to set
	 */
	public void setIMAGE_KINDS(String iMAGE_KINDS) {
		IMAGE_KINDS = iMAGE_KINDS;
	}
	/**
	 * @return the hAPPEN_DATE
	 */
	public int getHAPPEN_DATE() {
		return HAPPEN_DATE;
	}
	/**
	 * @param hAPPEN_DATE the hAPPEN_DATE to set
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
	 * @param hAPPEN_TIME the hAPPEN_TIME to set
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
	 * @param lOGIN_ACCOUNT the lOGIN_ACCOUNT to set
	 */
	public void setLOGIN_ACCOUNT(String lOGIN_ACCOUNT) {
		LOGIN_ACCOUNT = lOGIN_ACCOUNT;
	}
	/**
	 * @return the bAK
	 */
	public String getBAK() {
		return BAK;
	}
	/**
	 * @param bAK the bAK to set
	 */
	public void setBAK(String bAK) {
		BAK = bAK;
	}
	/**
	 * @return the bAK1
	 */
	public String getBAK1() {
		return BAK1;
	}
	/**
	 * @param bAK1 the bAK1 to set
	 */
	public void setBAK1(String bAK1) {
		BAK1 = bAK1;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
