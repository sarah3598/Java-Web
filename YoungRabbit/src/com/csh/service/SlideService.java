package com.csh.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.util.PageModel;

public class SlideService {
	public static String getSlideListRs(int startRow, int endRow, String sql, Object[] param) {

		sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RN FROM (" + sql + ") A " + ") WHERE RN BETWEEN " + startRow
				+ " AND " + endRow;
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		JSONArray json_data=new JSONArray();
	
		try {
			if(rs.isBeforeFirst()){
				json_data =JsonService.ConvertResultSetToJsonArray(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return json_data.toString();
	}
	
	public static int getSlideListRows(String rowContSql, Object[] param) {
		int rows = BaseDao.getRowCount(rowContSql, param);
		return rows;
	}
	
	public static int getSlideID(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		int slide_id = -1;
		try {
			while (rs.next()) {
				slide_id = rs.getInt("SLIDE_ID");

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
		return slide_id;

	}

}
