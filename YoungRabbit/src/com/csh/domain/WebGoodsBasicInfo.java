package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebGoodsBasicInfo implements java.io.Serializable {

	// Fields

	private long goodId;
	private long userId;
	private String collection;
	private String remark;
	private long orgaId;
	private long storeId;
	private String title;
	private String description;
	private long imagesId;
	private double pricing;
	private double compareAtPrice;
	private String taxMark;
	private String buyButtonMark;
	private String onlineStore;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private String status;
	private String bak;
	private String bak1;

	// Constructors

	/** default constructor */
	public WebGoodsBasicInfo() {
	}

	/** full constructor */
	public WebGoodsBasicInfo(long userId, String collection, String remark,
			long orgaId, long storeId, String title, String description,
			long imagesId, double pricing, double compareAtPrice,
			String taxMark, String buyButtonMark, String onlineStore,
			long happenDate, long happenTime, String loginAccount,
			String status, String bak, String bak1) {
		this.userId = userId;
		this.collection = collection;
		this.remark = remark;
		this.orgaId = orgaId;
		this.storeId = storeId;
		this.title = title;
		this.description = description;
		this.imagesId = imagesId;
		this.pricing = pricing;
		this.compareAtPrice = compareAtPrice;
		this.taxMark = taxMark;
		this.buyButtonMark = buyButtonMark;
		this.onlineStore = onlineStore;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.status = status;
		this.bak = bak;
		this.bak1 = bak1;
	}

	// Property accessors

	public long getGoodId() {
		return this.goodId;
	}

	public void setGoodId(long goodId) {
		this.goodId = goodId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCollection() {
		return this.collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getOrgaId() {
		return this.orgaId;
	}

	public void setOrgaId(long orgaId) {
		this.orgaId = orgaId;
	}

	public long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getImagesId() {
		return this.imagesId;
	}

	public void setImagesId(long imagesId) {
		this.imagesId = imagesId;
	}

	public double getPricing() {
		return this.pricing;
	}

	public void setPricing(double pricing) {
		this.pricing = pricing;
	}

	public double getCompareAtPrice() {
		return this.compareAtPrice;
	}

	public void setCompareAtPrice(double compareAtPrice) {
		this.compareAtPrice = compareAtPrice;
	}

	public String getTaxMark() {
		return this.taxMark;
	}

	public void setTaxMark(String taxMark) {
		this.taxMark = taxMark;
	}

	public String getBuyButtonMark() {
		return this.buyButtonMark;
	}

	public void setBuyButtonMark(String buyButtonMark) {
		this.buyButtonMark = buyButtonMark;
	}

	public String getOnlineStore() {
		return this.onlineStore;
	}

	public void setOnlineStore(String onlineStore) {
		this.onlineStore = onlineStore;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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