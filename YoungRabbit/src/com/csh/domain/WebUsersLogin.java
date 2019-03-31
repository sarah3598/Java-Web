package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebUsersLogin implements java.io.Serializable {

	// Fields

	private long loginId;
	private String email;
	private String pwd;
	private String remark;
	private long happenDate;
	private long happenTime;
	private String loginCount;

	// Constructors

	/** default constructor */
	public WebUsersLogin() {
	}

	/** full constructor */
	public WebUsersLogin(String email, String pwd, String remark,
			long happenDate, long happenTime, String loginCount) {
		this.email = email;
		this.pwd = pwd;
		this.remark = remark;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginCount = loginCount;
	}

	// Property accessors

	public long getLoginId() {
		return this.loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
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

	public String getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}

}