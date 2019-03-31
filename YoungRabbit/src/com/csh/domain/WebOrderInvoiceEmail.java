package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebOrderInvoiceEmail implements java.io.Serializable {

	// Fields

	private long orderInvoiceEId;
	private long orderId;
	private String toEmail;
	private String fromEmail;
	private String invoiceContent;
	private String orderSummery;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private String bak;
	private String bak1;

	// Constructors

	/** default constructor */
	public WebOrderInvoiceEmail() {
	}

	/** full constructor */
	public WebOrderInvoiceEmail(long orderId, String toEmail, String fromEmail,
			String invoiceContent, String orderSummery, long happenDate,
			long happenTime, String loginAccount, String bak, String bak1) {
		this.orderId = orderId;
		this.toEmail = toEmail;
		this.fromEmail = fromEmail;
		this.invoiceContent = invoiceContent;
		this.orderSummery = orderSummery;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.bak = bak;
		this.bak1 = bak1;
	}

	// Property accessors

	public long getOrderInvoiceEId() {
		return this.orderInvoiceEId;
	}

	public void setOrderInvoiceEId(long orderInvoiceEId) {
		this.orderInvoiceEId = orderInvoiceEId;
	}

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getToEmail() {
		return this.toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getFromEmail() {
		return this.fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getInvoiceContent() {
		return this.invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public String getOrderSummery() {
		return this.orderSummery;
	}

	public void setOrderSummery(String orderSummery) {
		this.orderSummery = orderSummery;
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