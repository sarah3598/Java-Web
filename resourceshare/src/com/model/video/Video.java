package com.model.video;

import java.sql.Date;

/**
 * 
 * @author Administrator
 * @time 2017/09/24
 *
 */

public class Video {
	private Integer id;             //��Ƶ���
	private String videourl;     //��Ƶ��ַ
	private String videopic;     //��ƵͼƬ
	private String grade;		 //�����꼶
	private Date uptime;         //�ϴ�ʱ��
	private String subject;      //����ѧ��
	private String description;  //����
	private Integer teacherid;      //��ʦ���
	private String teachername;  //��ʦ����
	private Integer viewnum;     //�����
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
