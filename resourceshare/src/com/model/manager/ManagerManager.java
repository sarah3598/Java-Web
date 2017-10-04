package com.model.manager;

import java.util.List;

import com.dao.ManagerDao;

/**
 * ����Աҵ���
 * @author Ʈ����
 *
 */
public class ManagerManager {
	ManagerDao managerDao = new ManagerDao();
	
	/**
	 * ����Ա��¼
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
	 * ���ӹ���Ա
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
	 * ���ù���Ա
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
	 * ɾ������Ա
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
	 * �޸�����
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
	 * ��ѯ���й���Ա
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
	 * ��ѯһ������Ա
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