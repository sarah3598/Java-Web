package com.model.manager;

/**
 * ����Աģ��
 * @author Ʈ����
 *
 */
public class Manager {
	private Long managerid;		    //����Ա���
	private String managername;	    //����Ա�û���
	private String managerpwd;		//����Ա����
	private String managertype;	    //����ԱȨ��
	private String manageremail;	//����Ա����
	private String managermobile;    //����Ա����
	
	public Long getManagerid() {
		return managerid;
	}
	public void setManagerid(Long managerid) {
		this.managerid = managerid;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getManagerpwd() {
		return managerpwd;
	}
	public void setManagerpwd(String managerpwd) {
		this.managerpwd = managerpwd;
	}
	public String getManagertype() {
		return managertype;
	}
	public void setManagertype(String managertype) {
		this.managertype = managertype;
	}
	public String getManageremail() {
		return manageremail;
	}
	public void setManageremail(String manageremail) {
		this.manageremail = manageremail;
	}
	public String getManagermobile() {
		return managermobile;
	}
	public void setManagermobile(String managermobile) {
		this.managermobile = managermobile;
	}
	
	
}