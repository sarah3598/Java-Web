package com.csh.test;

import org.junit.Test;

import com.csh.dao.BaseDao;

public class TestConn {
	
	@Test
	public void testConn(){
		BaseDao dao = new BaseDao();
		System.out.println(dao.getConn());
		
	}

}
