package com.csh.domain;

import java.io.Serializable;

public class GRADE_RECORD implements Serializable {

	private static final long serialVersionUID = 6379109604059863248L;
	private int USER_ID;
	private int ROLE;
	private int NEW_ROLE;
	private int REMARK;
	private int HAPPEN_DATE;
	private int HAPPEN_TIME;
	public int getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(int uSER_ID) {
		USER_ID = uSER_ID;
	}
	public int getROLE() {
		return ROLE;
	}
	public void setROLE(int rOLE) {
		ROLE = rOLE;
	}
	public int getNEW_ROLE() {
		return NEW_ROLE;
	}
	public void setNEW_ROLE(int nEW_ROLE) {
		NEW_ROLE = nEW_ROLE;
	}
	public int getREMARK() {
		return REMARK;
	}
	public void setREMARK(int rEMARK) {
		REMARK = rEMARK;
	}
	public int getHAPPEN_DATE() {
		return HAPPEN_DATE;
	}
	public void setHAPPEN_DATE(int hAPPEN_DATE) {
		HAPPEN_DATE = hAPPEN_DATE;
	}
	public int getHAPPEN_TIME() {
		return HAPPEN_TIME;
	}
	public void setHAPPEN_TIME(int hAPPEN_TIME) {
		HAPPEN_TIME = hAPPEN_TIME;
	}

}
