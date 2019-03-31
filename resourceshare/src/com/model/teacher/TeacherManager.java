package com.model.teacher;

import com.dao.TeacherDao;

public class TeacherManager {
	TeacherDao teacherDao=new TeacherDao();
	
	
	/**
	 * ��ӽ�ʦ
	 * @param nTeacher
	 * @return
	 */
	public boolean addTeacher(Teacher nTeacher){
		boolean bFlag = false;
		try{
			int iFlag = teacherDao.addTeacher(nTeacher);
			bFlag = iFlag>0?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}

	/**
	 * ��ʦ��¼
	 * @param lTeacher
	 * @return
	 */
	public boolean login(Teacher lTeacher){
		boolean bFlag = false;
		try{
			bFlag = teacherDao.login(lTeacher);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	/**
	 * �޸���Ϣ
	 * @param nTeacher
	 * @return
	 */
	public boolean updateTeacher(Teacher nTeacher){
		boolean bFlag=false;
		try{
			int iFlag = teacherDao.updateTeacher(nTeacher);
			bFlag = iFlag>0?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	
	public Teacher findByMobile(String mobile){
		Teacher teacher=null;
		try {
			teacher=teacherDao.findByMobile(mobile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}
}
