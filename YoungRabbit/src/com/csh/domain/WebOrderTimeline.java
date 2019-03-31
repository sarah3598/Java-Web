package com.csh.domain;

/*********************
 * Author shaohui-chen
 ******************** */

public class WebOrderTimeline implements java.io.Serializable {

	// Fields

	private long orderTimelineId;
	private String time;
	private String comments;
	private String happensThings;
	private long happenDate;
	private long happenTime;
	private String loginAccount;
	private String bak;
	private String bak1;

	// Constructors

	/** default constructor */
	public WebOrderTimeline() {
	}

	/** full constructor */
	public WebOrderTimeline(String time, String comments, String happensThings,
			long happenDate, long happenTime, String loginAccount, String bak,
			String bak1) {
		this.time = time;
		this.comments = comments;
		this.happensThings = happensThings;
		this.happenDate = happenDate;
		this.happenTime = happenTime;
		this.loginAccount = loginAccount;
		this.bak = bak;
		this.bak1 = bak1;
	}

	// Property accessors

	public long getOrderTimelineId() {
		return this.orderTimelineId;
	}

	public void setOrderTimelineId(long orderTimelineId) {
		this.orderTimelineId = orderTimelineId;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getHappensThings() {
		return this.happensThings;
	}

	public void setHappensThings(String happensThings) {
		this.happensThings = happensThings;
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