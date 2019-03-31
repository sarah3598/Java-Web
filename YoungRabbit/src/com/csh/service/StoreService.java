package com.csh.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.csh.dao.BaseDao;
import com.csh.domain.Store;

public class StoreService {

	// 查询store
	public static List<Store> findStore(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs;
		rs = BaseDao.getRs(con, sql, param);
		List<Store> list = new ArrayList<Store>();

		try {
			while (rs.next()) {
				Store ac = new Store();

				int STORE_ID = rs.getInt("STORE_ID");
				int USER_ID=rs.getInt("USER_ID");
				String EMAIL = rs.getString("EMAIL");
				String PWD = rs.getString("PASSWORD");
				int HAPPEN_DATE = rs.getInt("HAPPEN_DATE");
				int HAPPEN_TIME = rs.getInt("HAPPEN_TIME");
				String LOGIN_ACCOUNT = rs.getString("LOGIN_ACCOUNT");

				ac.setUSER_ID(USER_ID);
				ac.setSTORE_ID(STORE_ID);
				
				ac.setEMAIL(EMAIL);
				ac.setPASSWORD(PWD);
				
				
				ac.setHAPPEN_DATE(HAPPEN_DATE);
				ac.setHAPPEN_TIME(HAPPEN_TIME);
				ac.setLOGIN_ACCOUNT(LOGIN_ACCOUNT);

				list.add(ac);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}
}
