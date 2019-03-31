package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebGoodsInventory implements java.io.Serializable {

	// Fields

	private long inventoryId;
	private long goodId;
	private String sku;
	private String barcode;
	private String inventoryPolicy;
	private String quantity;
	private String incoming;
	private String outStockMark;
	private String weightSku;
	private String serviceWays;
	private String shippingMark;
	private String ssize;
	private String color;
	private String material;
	private String remark;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private String bak1;
	private String bak2;

	// Constructors

	/** default constructor */
	public WebGoodsInventory() {
	}

	/** full constructor */
	public WebGoodsInventory(long goodId, String sku, String barcode,
			String inventoryPolicy, String quantity, String incoming,
			String outStockMark, String weightSku, String serviceWays,
			String shippingMark, String ssize, String color, String material,
			String remark, long happenDate, long happenTime,
			String loginAccount, String bak1, String bak2) {
		this.goodId = goodId;
		this.sku = sku;
		this.barcode = barcode;
		this.inventoryPolicy = inventoryPolicy;
		this.quantity = quantity;
		this.incoming = incoming;
		this.outStockMark = outStockMark;
		this.weightSku = weightSku;
		this.serviceWays = serviceWays;
		this.shippingMark = shippingMark;
		this.ssize = ssize;
		this.color = color;
		this.material = material;
		this.remark = remark;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.bak1 = bak1;
		this.bak2 = bak2;
	}

	// Property accessors

	public long getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public long getGoodId() {
		return this.goodId;
	}

	public void setGoodId(long goodId) {
		this.goodId = goodId;
	}

	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getInventoryPolicy() {
		return this.inventoryPolicy;
	}

	public void setInventoryPolicy(String inventoryPolicy) {
		this.inventoryPolicy = inventoryPolicy;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getIncoming() {
		return this.incoming;
	}

	public void setIncoming(String incoming) {
		this.incoming = incoming;
	}

	public String getOutStockMark() {
		return this.outStockMark;
	}

	public void setOutStockMark(String outStockMark) {
		this.outStockMark = outStockMark;
	}

	public String getWeightSku() {
		return this.weightSku;
	}

	public void setWeightSku(String weightSku) {
		this.weightSku = weightSku;
	}

	public String getServiceWays() {
		return this.serviceWays;
	}

	public void setServiceWays(String serviceWays) {
		this.serviceWays = serviceWays;
	}

	public String getShippingMark() {
		return this.shippingMark;
	}

	public void setShippingMark(String shippingMark) {
		this.shippingMark = shippingMark;
	}

	public String getSsize() {
		return this.ssize;
	}

	public void setSsize(String ssize) {
		this.ssize = ssize;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMaterial() {
		return this.material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getBak1() {
		return this.bak1;
	}

	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}

	public String getBak2() {
		return this.bak2;
	}

	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}

}