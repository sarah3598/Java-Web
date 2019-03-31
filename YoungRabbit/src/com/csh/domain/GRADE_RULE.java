package com.csh.domain;

import java.io.Serializable;

public class GRADE_RULE implements Serializable {

	private static final long serialVersionUID = 6379109604059863248L;
	private int ROLE;
	private int PERIOD;
	private int ACHIEVE_AVG;
	private int ACHIEVE_SUM;
	private int CREATE_ID;
	private int HAPPEN_DATE;
	private int HAPPEN_TIME;
	public int getROLE() {
		return ROLE;
	}
	public void setROLE(int rOLE) {
		ROLE = rOLE;
	}
	public int getPERIOD() {
		return PERIOD;
	}
	public void setPERIOD(int pERIOD) {
		PERIOD = pERIOD;
	}
	public int getACHIEVE_AVG() {
		return ACHIEVE_AVG;
	}
	public void setACHIEVE_AVG(int aCHIEVE_AVG) {
		ACHIEVE_AVG = aCHIEVE_AVG;
	}
	public int getACHIEVE_SUM() {
		return ACHIEVE_SUM;
	}
	public void setACHIEVE_SUM(int aCHIEVE_SUM) {
		ACHIEVE_SUM = aCHIEVE_SUM;
	}
	public int getCREATE_ID() {
		return CREATE_ID;
	}
	public void setCREATE_ID(int cREATE_ID) {
		CREATE_ID = cREATE_ID;
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
