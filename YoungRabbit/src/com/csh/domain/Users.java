package com.csh.domain;

/*********************
 * Author shaohui-chen
 */

public class Users implements java.io.Serializable {

	// Fields

	private long userId;
	private String userName;
	private String upperUser;
	private String email;
	private String pwd;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	private String city;
	private String zipCode;
	private String country;
	private String contract;
	private String receiveUpdate;
	private String remark;
	private String status;
	private long bak;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private long upperUserId;
	private int role;

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(String userName, String upperUser, String email, String pwd, String firstName, String lastName,
			String phone, String address, String city, String zipCode, String country, String contract,
			String receiveUpdate, String remark, String status, long bak, long happenDate, long happenTime,
			String loginAccount, long upperUserId, int role) {
		this.userName = userName;
		this.upperUser = upperUser;
		this.email = email;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
		this.contract = contract;
		this.receiveUpdate = receiveUpdate;
		this.remark = remark;
		this.status = status;
		this.bak = bak;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.upperUserId = upperUserId;
		this.role = role;
	}

	// Property accessors

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUpperUser() {
		return this.upperUser;
	}

	public void setUpperUser(String upperUser) {
		this.upperUser = upperUser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContract() {
		return this.contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getReceiveUpdate() {
		return this.receiveUpdate;
	}

	public void setReceiveUpdate(String receiveUpdate) {
		this.receiveUpdate = receiveUpdate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getBak() {
		return this.bak;
	}

	public void setBak(long bak) {
		this.bak = bak;
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

	public long getUpperUserId() {
		return this.upperUserId;
	}

	public void setUpperUserId(long upperUserId) {
		this.upperUserId = upperUserId;
	}
	public int getRole() {
		return this.role;
	}
	public void setRole(int role) {
		this.role = role;
	}

}