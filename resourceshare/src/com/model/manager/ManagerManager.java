package com.model.manager;

import java.util.List;

import com.dao.ManagerDao;

/**
 * 管理员业务层
 * @author 飘仙人
 *
 */
public class ManagerManager {
	ManagerDao managerDao = new ManagerDao();
	
	/**
	 * 管理员登录
	 * @param lManager
	 * @return
	 */
	public boolean login(Manager lManager){
		boolean bFlag = false;
		try{
			bFlag = managerDao.login(lManager);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 * 增加管理员
	 * @param nManager
	 * @return
	 */
	public boolean addManager(Manager nManager){
		boolean bFlag = false;
		
		try{
			int iFlag = managerDao.addManager(nManager);
			bFlag = iFlag>0?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 * 设置管理员
	 * @param uManager
	 * @return
	 */
	public boolean updateManager(Manager uManager){
		boolean bFlag = false;
		try{
			int iFlag = managerDao.updateManager(uManager);	
			bFlag = iFlag>0?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 * 删除管理员
	 * @param dManager
	 * @return
	 */
	public boolean deleteManager(Manager dManager){
		boolean bFlag = false;
		try{
			int iFlag = managerDao.deleteManager(dManager);
			bFlag = iFlag>0?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 * 修改密码
	 * @param cManager
	 * @param newPwd
	 * @return
	 */
	public boolean modifyPwd(Manager cManager,String newPwd){
		boolean bFlag = false;
		try{
			int iFlag = managerDao.modifyPwd(cManager,newPwd);
			bFlag = iFlag>0?true:false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bFlag;
	}
	
	/**
	 * 查询所有管理员
	 * @return
	 */
	public List<Manager> findAll(){
		List<Manager> managerList = null;
		try{
			managerList = managerDao.findAll();	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return managerList;
	}
	
	/**
	 * 查询一个管理员
	 * @param admin
	 * @return
	 */
	public Manager findByAdmin(String admin){
		Manager m = null;
		try{
			m = managerDao.findByAdmin(admin);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return m;
	}
}