package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebDiscountInfo implements java.io.Serializable {

	// Fields

	private long discountInfoId;
	private long discountId;
	private String discountCode;
	private String discountUnit;
	private String discountRatio;
	private String discountKind;
	private String discountLimit;
	private String discountLimitCustomer;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private String bak;
	private String bak1;

	// Constructors

	/** default constructor */
	public WebDiscountInfo() {
	}

	/** full constructor */
	public WebDiscountInfo(long discountId, String discountCode,
			String discountUnit, String discountRatio, String discountKind,
			String discountLimit, String discountLimitCustomer,
			long happenDate, long happenTime, String loginAccount, String bak,
			String bak1) {
		this.discountId = discountId;
		this.discountCode = discountCode;
		this.discountUnit = discountUnit;
		this.discountRatio = discountRatio;
		this.discountKind = discountKind;
		this.discountLimit = discountLimit;
		this.discountLimitCustomer = discountLimitCustomer;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.bak = bak;
		this.bak1 = bak1;
	}

	// Property accessors

	public long getDiscountInfoId() {
		return this.discountInfoId;
	}

	public void setDiscountInfoId(long discountInfoId) {
		this.discountInfoId = discountInfoId;
	}

	public long getDiscountId() {
		return this.discountId;
	}

	public void setDiscountId(long discountId) {
		this.discountId = discountId;
	}

	public String getDiscountCode() {
		return this.discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getDiscountUnit() {
		return this.discountUnit;
	}

	public void setDiscountUnit(String discountUnit) {
		this.discountUnit = discountUnit;
	}

	public String getDiscountRatio() {
		return this.discountRatio;
	}

	public void setDiscountRatio(String discountRatio) {
		this.discountRatio = discountRatio;
	}

	public String getDiscountKind() {
		return this.discountKind;
	}

	public void setDiscountKind(String discountKind) {
		this.discountKind = discountKind;
	}

	public String getDiscountLimit() {
		return this.discountLimit;
	}

	public void setDiscountLimit(String discountLimit) {
		this.discountLimit = discountLimit;
	}

	public String getDiscountLimitCustomer() {
		return this.discountLimitCustomer;
	}

	public void setDiscountLimitCustomer(String discountLimitCustomer) {
		this.discountLimitCustomer = discountLimitCustomer;
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