package com.csh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import com.csh.service.JsonService;

/*********************
 * Author shaohui-chen
 */

public class BaseDao {

	/**
	 * 连接对象
	 */
	public static Connection getConn() {
		Connection conn = null;

		try {
			// Class.forName("com.mysql.jdbc.Driver");//加载指定驱动
			Class.forName("oracle.jdbc.OracleDriver");
			// 根据用户名，密码，连接到指定的数据库地址。来得到连接对象
			// jdbc:oracle:thin:@192.168.71.12:1521:orcl
			//http://59.110.236.41
			//conn = DriverManager.getConnection("jdbc:oracle:thin:WEB_USER@192.168.71.3:1521/orcl", "WEB_USER", "WEB_USER");
			conn = DriverManager.getConnection("jdbc:oracle:thin:WEB_USER@59.110.236.41:1521/orcl", "WEB_USER", "WEB_USER");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	// by xiaopan
	public static JSONArray getRsJSONArray(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		JSONArray json_data = new JSONArray();
		try {
			if (rs.isBeforeFirst()) {
				json_data = JsonService.ConvertResultSetToJsonArray(rs);
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
		return json_data;
	}

	public static ResultSet getRs(Connection con, String sql, Object[] param) {
		Connection conn = con;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// 这是不同于exeSql中的
		// 1
		conn = getConn();
		// 2
		try {
			pstmt = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			// 3
			rs = pstmt.executeQuery();// 这里注意。。是Query
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;// 返回这个查询后的结果集
	}

	public static int exeSql(String sql, Object[] param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 1
		conn = getConn();
		// 2
		try {
			pstmt = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			// 2-2
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 取得行数
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public static int getRowCount(String sql, Object[] param) {
		Connection con = getConn();
		ResultSet rs = getRs(con, sql, param);
		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return 0;
	}

}
