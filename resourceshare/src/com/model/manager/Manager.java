package com.model.manager;

/**
 * 管理员模型
 * @author 飘仙人
 *
 */
public class Manager {
	private Long managerid;		    //管理员编号
	private String managername;	    //管理员用户名
	private String managerpwd;		//管理员密码
	private String managertype;	    //管理员权限
	private String manageremail;	//管理员邮箱
	private String managermobile;    //管理员邮箱
	
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