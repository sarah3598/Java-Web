package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebOrderTags implements java.io.Serializable {

	// Fields

	private long orderTagsId;
	private String orderTagsContent;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private String bak;
	private String bak1;

	// Constructors

	/** default constructor */
	public WebOrderTags() {
	}

	/** full constructor */
	public WebOrderTags(String orderTagsContent, long happenDate,
			long happenTime, String loginAccount, String bak, String bak1) {
		this.orderTagsContent = orderTagsContent;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.bak = bak;
		this.bak1 = bak1;
	}

	// Property accessors

	public long getOrderTagsId() {
		return this.orderTagsId;
	}

	public void setOrderTagsId(long orderTagsId) {
		this.orderTagsId = orderTagsId;
	}

	public String getOrderTagsContent() {
		return this.orderTagsContent;
	}

	public void setOrderTagsContent(String orderTagsContent) {
		this.orderTagsContent = orderTagsContent;
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