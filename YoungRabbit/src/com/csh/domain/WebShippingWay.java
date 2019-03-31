package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebShippingWay implements java.io.Serializable {

	// Fields

	private long shippingMethodId;
	private String shippingCompany;
	private String shippingPrice;
	private String shippingUnit;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private String bak;
	private String bak1;

	// Constructors

	/** default constructor */
	public WebShippingWay() {
	}

	/** full constructor */
	public WebShippingWay(String shippingCompany, String shippingPrice,
			String shippingUnit, long happenDate, long happenTime,
			String loginAccount, String bak, String bak1) {
		this.shippingCompany = shippingCompany;
		this.shippingPrice = shippingPrice;
		this.shippingUnit = shippingUnit;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.bak = bak;
		this.bak1 = bak1;
	}

	// Property accessors

	public long getShippingMethodId() {
		return this.shippingMethodId;
	}

	public void setShippingMethodId(long shippingMethodId) {
		this.shippingMethodId = shippingMethodId;
	}

	public String getShippingCompany() {
		return this.shippingCompany;
	}

	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	public String getShippingPrice() {
		return this.shippingPrice;
	}

	public void setShippingPrice(String shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public String getShippingUnit() {
		return this.shippingUnit;
	}

	public void setShippingUnit(String shippingUnit) {
		this.shippingUnit = shippingUnit;
	}

	public long getHappenDate() {
		return this.happenDate;
	}

	public void setHappenDate(long happenDate) {
		this.happenDate = happenDate;
	}

	public long getHappenTime() {
		return this.happenTime;
	}

	public void setHappenTime(long happenTime) {
		this.happenTime = happenTime;
	}

	public String getLoginAccount() {
		return this.loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getBak() {
		return this.bak;
	}

	public void setBak(String bak) {
		this.bak = bak;
	}

	public String getBak1() {
		return this.bak1;
	}

	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}

}