package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebDiscount implements java.io.Serializable {

	// Fields

	private long discountId;
	private long goodId;
	private String discountName;
	private String details;
	private String startDate;
	private String endDate;
	private String status;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private String bak;
	private String bak1;

	// Constructors

	/** default constructor */
	public WebDiscount() {
	}

	/** full constructor */
	public WebDiscount(long goodId, String discountName, String details,
			String startDate, String endDate, String status, long happenDate,
			long happenTime, String loginAccount, String bak, String bak1) {
		this.goodId = goodId;
		this.discountName = discountName;
		this.details = details;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.bak = bak;
		this.bak1 = bak1;
	}

	// Property accessors

	public long getDiscountId() {
		return this.discountId;
	}

	public void setDiscountId(long discountId) {
		this.discountId = discountId;
	}

	public long getGoodId() {
		return this.goodId;
	}

	public void setGoodId(long goodId) {
		this.goodId = goodId;
	}

	public String getDiscountName() {
		return this.discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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