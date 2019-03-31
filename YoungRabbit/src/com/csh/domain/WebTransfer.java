package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebTransfer implements java.io.Serializable {

	// Fields

	private long transferId;
	private long orderId;
	private String transferCreateTime;
	private String expectArrTime;
	private String supplier;
	private String status;
	private String received;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private String bak;
	private String bak1;

	// Constructors

	/** default constructor */
	public WebTransfer() {
	}

	/** full constructor */
	public WebTransfer(long orderId, String transferCreateTime,
			String expectArrTime, String supplier, String status,
			String received, long happenDate, long happenTime,
			String loginAccount, String bak, String bak1) {
		this.orderId = orderId;
		this.transferCreateTime = transferCreateTime;
		this.expectArrTime = expectArrTime;
		this.supplier = supplier;
		this.status = status;
		this.received = received;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.bak = bak;
		this.bak1 = bak1;
	}

	// Property accessors

	public long getTransferId() {
		return this.transferId;
	}

	public void setTransferId(long transferId) {
		this.transferId = transferId;
	}

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getTransferCreateTime() {
		return this.transferCreateTime;
	}

	public void setTransferCreateTime(String transferCreateTime) {
		this.transferCreateTime = transferCreateTime;
	}

	public String getExpectArrTime() {
		return this.expectArrTime;
	}

	public void setExpectArrTime(String expectArrTime) {
		this.expectArrTime = expectArrTime;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReceived() {
		return this.received;
	}

	public void setReceived(String received) {
		this.received = received;
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