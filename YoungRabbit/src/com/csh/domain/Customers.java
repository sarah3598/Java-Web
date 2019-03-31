package com.csh.domain;

/*********************
 * Author shaohui-chen
 */

public class Customers implements java.io.Serializable {

	// Fields

	private long customerId;
	private String userName;
	private String email;
	private String pwd;
	private String firstName;
	private String lastName;
	private String phone;
	private String weChat;
	private String facebook;
	private String status;
	private long happenDate;
	private long happenTime;
	private int role;
	private String nationality;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWeChat() {
		return weChat;
	}
	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getHappenDate() {
		return happenDate;
	}
	public void setHappenDate(long happenDate) {
		this.happenDate = happenDate;
	}
	public long getHappenTime() {
		return happenTime;
	}
	public void setHappenTime(long happenTime) {
		this.happenTime = happenTime;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	

}