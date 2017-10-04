package com.model.user;

import java.util.List;

import com.dao.UserDao;

/**
 * 用户业务层
 * @author 飘仙人
 *
 */
public class UserManager {
	UserDao userDao = new UserDao();
	
	/**
	 * 用户登录
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
	 * 添加用户
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
	 * 用户注册
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
	 * 修改用户
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
	 * 删除用户
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
	 *  修改密码
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
	 * 修改用户密码
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
	 * 查询所有的用户
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
	 * 查询一个用户
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
	 * 查询用户存在
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