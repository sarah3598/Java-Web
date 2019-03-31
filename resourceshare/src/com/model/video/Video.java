package com.model.video;

import java.sql.Date;

/**
 * 
 * @author Administrator
 * @time 2017/09/24
 *
 */

public class Video {
	private Integer id;             //视频编号
	private String videourl;     //视频地址
	private String videopic;     //视频图片
	private String grade;		 //所属年级
	private Date uptime;         //上传时间
	private String subject;      //所属学科
	private String description;  //描述
	private Integer teacherid;      //教师编号
	private String teachername;  //教师姓名
	private Integer viewnum;     //浏览量
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVideourl() {
		return videourl;
	}
	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
	public String getVideopic() {
		return videopic;
	}
	public void setVideopic(String videopic) {
		this.videopic = videopic;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Date getUptime() {
		return uptime;
	}
	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public Integer getViewnum() {
		return viewnum;
	}
	public void setViewnum(Integer viewnum) {
		this.viewnum = viewnum;
	}

}
