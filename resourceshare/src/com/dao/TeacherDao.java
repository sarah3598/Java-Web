package com.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.model.teacher.Teacher;

public class TeacherDao {
	private StringBuffer sql = null;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public int addTeacher(Teacher nTeacher) throws Exception{
		sql = new StringBuffer("");
		sql.append("insert into teacher_info(mobile,pwd,email,relname,jobtitle,education,teachage,subject,address,idno,redate)values('").append(nTeacher.getMobile())
			.append("','").append(nTeacher.getPwd()).append("','").append(nTeacher.getEmail()).append("','").append(nTeacher.getRelname())
			.append("','").append(nTeacher.getJobtitle()).append("','").append(nTeacher.getEducation()).append("','").append(nTeacher.getTeachage())
			.append("','").append(nTeacher.getSubject()).append("','").append(nTeacher.getAddress()).append("','").append(nTeacher.getIdno())
			.append("','").append(df.format(new Date())).append("');");
		int iFlag = DBHelper.updateData(sql.toString());
		
		return iFlag;
	}
	
	
	public boolean login(Teacher lTeacher) throws Exception{
		boolean bFlag = false;
		sql = new StringBuffer("");
		sql.append("select * from teacher_info where mobile='").append(lTeacher.getMobile()).append("'");
		ResultSet rs = DBHelper.queryData(sql.toString());
		if(!rs.next()){
			return bFlag;
		}
		
		if(lTeacher.getPwd().equals(rs.getString("pwd"))){
			bFlag = true;
			lTeacher.setIdno(rs.getString("idno"));
			lTeacher.setRelname(rs.getString("relname"));
			lTeacher.setEmail(rs.getString("email"));
			lTeacher.setMobile(rs.getString("mobile"));
			lTeacher.setTeachage(rs.getInt("teachage"));
			lTeacher.setJobtitle(rs.getString("jobtitle"));
			lTeacher.setSex(rs.getString("sex"));
			lTeacher.setAge(rs.getInt("age"));
			lTeacher.setPicurl(rs.getString("picurl"));
			lTeacher.setEducation(rs.getString("education"));
			lTeacher.setAddress(rs.getString("address"));
			lTeacher.setSubject(rs.getString("subject"));
		}	
		return bFlag;
	}
	
	public int updateTeacher(Teacher lTeacher) throws Exception{
		sql = new StringBuffer("");
		sql.append("update teacher_info set relname='").append(lTeacher.getRelname())
		.append("',email='").append(lTeacher.getEmail())
		.append("',education='").append(lTeacher.getEducation())
		.append("',teachage='").append(lTeacher.getTeachage())
		.append("',age='").append(lTeacher.getAge())
		.append("',sex='").append(lTeacher.getSex())
		.append("',subject='").append(lTeacher.getSubject())
		.append("',jobtitle='").append(lTeacher.getJobtitle())
		.append("',address='").append(lTeacher.getAddress())
		.append("',picurl='").append(lTeacher.getPicurl())
		.append("' where mobile='").append(lTeacher.getMobile()).append("'");
		
		int iFlag = DBHelper.updateData(sql.toString());
		return iFlag;	
	}
	
	
	public Teacher findByMobile(String mobile) throws Exception{
		sql = new StringBuffer("");
		sql.append("select * from teacher_info where mobile='").append(mobile).append("'");
		ResultSet rs = DBHelper.queryData(sql.toString());
		if(!rs.next()){
			return null;
		}
		Teacher lTeacher=new Teacher();
		lTeacher.setIdno(rs.getString("idno"));
		lTeacher.setRelname(rs.getString("relname"));
		lTeacher.setPicurl(rs.getString("picurl"));
		lTeacher.setEmail(rs.getString("email"));
		lTeacher.setMobile(rs.getString("mobile"));
		lTeacher.setTeachage(rs.getInt("teachage"));
		lTeacher.setJobtitle(rs.getString("jobtitle"));
		lTeacher.setSex(rs.getString("sex"));
		lTeacher.setAge(rs.getInt("age"));
		lTeacher.setTeacherid(rs.getInt("teacherid"));
		lTeacher.setEducation(rs.getString("education"));
		lTeacher.setAddress(rs.getString("address"));
		lTeacher.setSubject(rs.getString("subject"));
		
		return lTeacher;
	}
}
