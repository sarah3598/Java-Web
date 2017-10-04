package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.manager.Manager;

/**
 * ����Աdao��
 * @author Ʈ����
 *
 */
public class ManagerDao {
	private StringBuffer sql = null;

	/**
	 * ����Ա��¼
	 * @param lManager
	 * @return
	 * @throws Exception
	 */
	public boolean login(Manager lManager) throws Exception {
		boolean bFlag = false;
		sql = new StringBuffer("");
		sql.append("select * from manager_info where managername='").append(
				lManager.getManagername()).append("'");
		ResultSet rs = DBHelper.queryData(sql.toString());
		if (!rs.next()) {
			return bFlag;
		}

		if (lManager.getManagerpwd().equals(rs.getString("managerpwd"))) {
			bFlag = true;
			lManager.setManagerid(rs.getLong("managerid"));
			lManager.setManagertype(rs.getString("managertype"));
			lManager.setManageremail(rs.getString("manageremail"));
		}
		return bFlag;
	}
	
	/**
	 * ���ӹ���Ա
	 * @param nManager
	 * @return
	 * @throws Exception
	 */
	public int addManager(Manager nManager) throws Exception{
		sql = new StringBuffer("");
		sql.append("insert into manager_info(managername,managerpwd,managertype,manageremail,managermobile)values('").append(nManager.getManagername())
			.append("','").append(nManager.getManagerpwd()).append("','").append(nManager.getManagertype()).append("','")
			.append(nManager.getManageremail()).append(nManager.getManagermobile()).append("','").append("')");
		
		int iFlag = DBHelper.updateData(sql.toString());
		return iFlag;
	}
	
	/**
	 * ���ù���Ա
	 * @param uManager
	 * @return
	 * @throws Exception
	 */
	public int updateManager(Manager uManager) throws Exception{
		sql = new StringBuffer("");
		sql.append("update manager_info set managertype='").append(uManager.getManagertype())
			.append("' where managername='").append(uManager.getManagername()).append("'");
		
		int iFlag = DBHelper.updateData(sql.toString());
		return iFlag;
	}
	
	/**
	 * ɾ������Ա
	 * @param dManager
	 * @return
	 * @throws Exception
	 */
	public int deleteManager(Manager dManager) throws Exception{
		sql = new StringBuffer("");
		sql.append("delete from manager_info").append(" where managername='").append(dManager.getManagername()).append("'");
		
		int iFlag = DBHelper.updateData(sql.toString());
		return iFlag;
	}
	
	/**
	 * �޸�����
	 * @param cManager
	 * @param newPwd
	 * @return
	 * @throws Exception
	 */
	public int modifyPwd(Manager cManager,String newPwd) throws Exception{
		sql = new StringBuffer("");
		sql.append("update manager_info set managerpwd='").append(newPwd)
			.append("' where managername='").append(cManager.getManagername()).append("';");
		
		//sql.append("update t_user_info set userpwd=? where username=?");
		//PreparedStatement pstmt=DBHelper.getConn().prepareStatement(sql.toString());
		
		int iFlag=DBHelper.updateData(sql.toString());	
		return iFlag;
	}
	
	/**
	 * ��ѯ���й���Ա
	 * @return
	 * @throws Exception
	 */
	public List<Manager> findAll() throws Exception{
		sql = new StringBuffer("");
		sql.append("select * from manager_info");
		ResultSet rs = DBHelper.queryData(sql.toString());

		List<Manager> managerList = new ArrayList<Manager>();
		while(rs.next()){
			Manager m = new Manager();
			m.setManagerid(rs.getLong("managerid"));
			m.setManagername(rs.getString("managername"));
			m.setManagerpwd(rs.getString("managerpwd"));
			m.setManagertype(rs.getString("managertype"));
			m.setManageremail(rs.getString("manageremail"));
			m.setManagermobile(rs.getString("managermobile"));

			managerList.add(m);
		}	
		return managerList;
	}
	
	/**
	 * ��ѯһ������Ա
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	public Manager findByAdmin(String admin) throws Exception{
		sql = new StringBuffer("");
		sql.append("select * from manager_info where managername='").append(admin).append("'");
		ResultSet rs = DBHelper.queryData(sql.toString());

		if(!rs.next()){
			return null;
		}
		
		Manager m = new Manager();
		m.setManagerid(rs.getLong("managerid"));
		m.setManagername(rs.getString("managername"));
		m.setManagerpwd(rs.getString("managerpwd"));
		m.setManagertype(rs.getString("managertype"));
		m.setManageremail(rs.getString("manageremail"));
		m.setManagermobile(rs.getString("managermobile"));
		return m;
	}
}