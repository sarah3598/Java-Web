package com.model.user;

import java.util.List;

import com.dao.UserDao;

/**
 * �û�ҵ���
 * @author Ʈ����
 *
 */
public class UserManager {
	UserDao userDao = new UserDao();
	
	/**
	 * �û���¼
	 * @param lUser
	 * @return
	 */
	public boolean login(User lUser){
		boolean bFlag = false;
		try{
			bFlag = userDao.login(lUser);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 * ����û�
	 * @param nUser
	 * @return
	 */
	public boolean addUser(User nUser){
		boolean bFlag = false;
		try{
			int iFlag = userDao.addUser(nUser);
			bFlag = iFlag>0?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 * �û�ע��
	 * @param nUser
	 * @return
	 */
	public boolean register(User nUser){
		boolean bFlag = false;
		try{
			int iFlag = userDao.register(nUser);
			bFlag = iFlag>0?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 * �޸��û�
	 * @param uUser
	 * @return
	 */
	public boolean updateUser(User uUser){
		boolean bFlag = false;
		try{
			int iFlag = userDao.updateUser(uUser);
			bFlag = iFlag>0?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;	
	}
	
	/**
	 * ɾ���û�
	 * @param dUser
	 * @return
	 */
	public boolean deleteUser(User dUser) {
		boolean bFlag = false;
		try {
			int iFlag = userDao.deleteUser(dUser);
			bFlag = iFlag > 0 ? true : false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 *  �޸�����
	 * @param cUser
	 * @param newPwd
	 * @return
	 */
	public boolean modifyPwd(User cUser,String newPwd){
		boolean bFlag = false;
		try{
			int iFlag = userDao.modifyPwd(cUser, newPwd);
			bFlag = iFlag>0?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 * �޸��û�����
	 * @param cUser
	 * @param newPwd
	 * @return
	 */
	public boolean upUserPwd(String cUser,String newPwd){
		boolean bFlag = false;
		try{
			int iFlag = userDao.upUserPwd(cUser, newPwd);
			bFlag = iFlag>0?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 * ��ѯ���е��û�
	 * @return
	 */
	public List<User> findAll() {
		List<User> userList = null;
		try {
			userList = userDao.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return userList;
	}
	
	/**
	 * ��ѯһ���û�
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName) {
		User user = null;
		try {
			user = userDao.findByUserName(userName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
	/**
	 * ��ѯ�û�����
	 * @param user
	 * @return
	 */
	public boolean queryUser(User user){
		boolean bFlag = false;
		try{
			bFlag = userDao.queryUser(user);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
}