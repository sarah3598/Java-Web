package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebOrders implements java.io.Serializable {

	// Fields

	private long orderId;
	private long goodId;
	private long customerId;
	private String marketPrice;
	private String compareAtPrice;
	private String chargeTaxes;
	private String costPrice;
	private String discountMoney;
	private String shippingPrice;
	private String totalCharge;
	private String emailInvoice;
	private String acceptPayment;
	private String paymentWays;
	private String status;
	private String tags;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private String bak;
	private String bak1;
	private String statisticalCurr;
	private String soldTo;
	private String exchgRate;
	private String altTaxClass;
	private String masterContr;
	private String taxDepartCty;
	private String smenr;
	private String materialId;
	private String salesUnit;
	private String salesQtyNum;
	private String salesQtyDenom;
	private String grossWeight;
	private String netWeight;
	private String unitOfWeight;
	private String volume;
	private String volumeUnit;
	private String stgeLoc;
	private String knumaAg;

	// Constructors

	/** default constructor */
	public WebOrders() {
	}

	/** full constructor */
	public WebOrders(long goodId, long customerId, String marketPrice,
			String compareAtPrice, String chargeTaxes, String costPrice,
			String discountMoney, String shippingPrice, String totalCharge,
			String emailInvoice, String acceptPayment, String paymentWays,
			String status, String tags, long happenDate, long happenTime,
			String loginAccount, String bak, String bak1,
			String statisticalCurr, String soldTo, String exchgRate,
			String altTaxClass, String masterContr, String taxDepartCty,
			String smenr, String materialId, String salesUnit,
			String salesQtyNum, String salesQtyDenom, String grossWeight,
			String netWeight, String unitOfWeight, String volume,
			String volumeUnit, String stgeLoc, String knumaAg) {
		this.goodId = goodId;
		this.customerId = customerId;
		this.marketPrice = marketPrice;
		this.compareAtPrice = compareAtPrice;
		this.chargeTaxes = chargeTaxes;
		this.costPrice = costPrice;
		this.discountMoney = discountMoney;
		this.shippingPrice = shippingPrice;
		this.totalCharge = totalCharge;
		this.emailInvoice = emailInvoice;
		this.acceptPayment = acceptPayment;
		this.paymentWays = paymentWays;
		this.status = status;
		this.tags = tags;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.bak = bak;
		this.bak1 = bak1;
		this.statisticalCurr = statisticalCurr;
		this.soldTo = soldTo;
		this.exchgRate = exchgRate;
		this.altTaxClass = altTaxClass;
		this.masterContr = masterContr;
		this.taxDepartCty = taxDepartCty;
		this.smenr = smenr;
		this.materialId = materialId;
		this.salesUnit = salesUnit;
		this.salesQtyNum = salesQtyNum;
		this.salesQtyDenom = salesQtyDenom;
		this.grossWeight = grossWeight;
		this.netWeight = netWeight;
		this.unitOfWeight = unitOfWeight;
		this.volume = volume;
		this.volumeUnit = volumeUnit;
		this.stgeLoc = stgeLoc;
		this.knumaAg = knumaAg;
	}

	// Property accessors

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getGoodId() {
		return this.goodId;
	}

	public void setGoodId(long goodId) {
		this.goodId = goodId;
	}

	public long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getCompareAtPrice() {
		return this.compareAtPrice;
	}

	public void setCompareAtPrice(String compareAtPrice) {
		this.compareAtPrice = compareAtPrice;
	}

	public String getChargeTaxes() {
		return this.chargeTaxes;
	}

	public void setChargeTaxes(String chargeTaxes) {
		this.chargeTaxes = chargeTaxes;
	}

	public String getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	public String getDiscountMoney() {
		return this.discountMoney;
	}

	public void setDiscountMoney(String discountMoney) {
		this.discountMoney = discountMoney;
	}

	public String getShippingPrice() {
		return this.shippingPrice;
	}

	public void setShippingPrice(String shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public String getTotalCharge() {
		return this.totalCharge;
	}

	public void setTotalCharge(String totalCharge) {
		this.totalCharge = totalCharge;
	}

	public String getEmailInvoice() {
		return this.emailInvoice;
	}

	public void setEmailInvoice(String emailInvoice) {
		this.emailInvoice = emailInvoice;
	}

	public String getAcceptPayment() {
		return this.acceptPayment;
	}

	public void setAcceptPayment(String acceptPayment) {
		this.acceptPayment = acceptPayment;
	}

	public String getPaymentWays() {
		return this.paymentWays;
	}

	public void setPaymentWays(String paymentWays) {
		this.paymentWays = paymentWays;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

	public String getStatisticalCurr() {
		return this.statisticalCurr;
	}

	public void setStatisticalCurr(String statisticalCurr) {
		this.statisticalCurr = statisticalCurr;
	}

	public String getSoldTo() {
		return this.soldTo;
	}

	public void setSoldTo(String soldTo) {
		this.soldTo = soldTo;
	}

	public String getExchgRate() {
		return this.exchgRate;
	}

	public void setExchgRate(String exchgRate) {
		this.exchgRate = exchgRate;
	}

	public String getAltTaxClass() {
		return this.altTaxClass;
	}

	public void setAltTaxClass(String altTaxClass) {
		this.altTaxClass = altTaxClass;
	}

	public String getMasterContr() {
		return this.masterContr;
	}

	public void setMasterContr(String masterContr) {
		this.masterContr = masterContr;
	}

	public String getTaxDepartCty() {
		return this.taxDepartCty;
	}

	public void setTaxDepartCty(String taxDepartCty) {
		this.taxDepartCty = taxDepartCty;
	}

	public String getSmenr() {
		return this.smenr;
	}

	public void setSmenr(String smenr) {
		this.smenr = smenr;
	}

	public String getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getSalesUnit() {
		return this.salesUnit;
	}

	public void setSalesUnit(String salesUnit) {
		this.salesUnit = salesUnit;
	}

	public String getSalesQtyNum() {
		return this.salesQtyNum;
	}

	public void setSalesQtyNum(String salesQtyNum) {
		this.salesQtyNum = salesQtyNum;
	}

	public String getSalesQtyDenom() {
		return this.salesQtyDenom;
	}

	public void setSalesQtyDenom(String salesQtyDenom) {
		this.salesQtyDenom = salesQtyDenom;
	}

	public String getGrossWeight() {
		return this.grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getNetWeight() {
		return this.netWeight;
	}

	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	public String getUnitOfWeight() {
		return this.unitOfWeight;
	}

	public void setUnitOfWeight(String unitOfWeight) {
		this.unitOfWeight = unitOfWeight;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getVolumeUnit() {
		return this.volumeUnit;
	}

	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}

	public String getStgeLoc() {
		return this.stgeLoc;
	}

	public void setStgeLoc(String stgeLoc) {
		this.stgeLoc = stgeLoc;
	}

	public String getKnumaAg() {
		return this.knumaAg;
	}

	public void setKnumaAg(String knumaAg) {
		this.knumaAg = knumaAg;
	}

}