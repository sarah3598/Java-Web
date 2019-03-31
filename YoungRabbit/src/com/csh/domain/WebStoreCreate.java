package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebStoreCreate implements java.io.Serializable {

	// Fields

	private long storeId;
	private long userId;
	private String email;
	private String password;
	private String storeName;
	private long imageId;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private String bak1;
	private String bak2;

	// Constructors

	/** default constructor */
	public WebStoreCreate() {
	}

	/** full constructor */
	public WebStoreCreate(long userId, String email, String password,
			String storeName, long imageId, long happenDate, long happenTime,
			String loginAccount, String bak1, String bak2) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.storeName = storeName;
		this.imageId = imageId;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.bak1 = bak1;
		this.bak2 = bak2;
	}

	// Property accessors

	public long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public long getImageId() {
		return this.imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
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