package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.CommonUtil;

public class DBConnection {
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String dbmsName="mysql";
	public static final String serverName="localhost";
	public static final String port="3306";
	public static final String dbName="edushare";
	public static final String username = "root";
	public static final String password = "123456";

	static {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ȡ���ݿ����Ӷ���
	public Connection connect() throws ClassNotFoundException, SQLException{
    	Connection conn=null; 
	    Class.forName("com.mysql.jdbc.Driver");
	    String url = "jdbc:mysql://localhost:3306/t_user?"
                + "user=root&password=19900908&useUnicode=true&characterEncoding=UTF8";
		conn=DriverManager.getConnection(url); 
		return conn;
	}
	//�ر����ݿ���Դ
	public void close(Statement stat,Connection conn) throws SQLException{
		if(stat!=null){
	    	   stat.close();
	    }
	    if(conn!=null){
	    	   conn.close();
	    }
	}

	// ��ȡStatment����
	public static Statement getStatement(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			return stmt;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	// ��ȡ�����
	public static ResultSet getResultSet(Connection conn, String sql) {
		ResultSet rs = null;
		try {
			rs = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void execute(Connection conn, String sql){
		try{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).execute(sql);
		} catch(Exception e){
			rollback(conn);
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn){
		if(conn!=null){
			try{
				conn.rollback();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ر�����
	 * 
	 * @param conn
	 */
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ر�Statement����
	 * 
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �رս����
	 * 
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection conn, ResultSet rs) {
		close(rs);
		close(conn);
	}

	public static void close(Connection conn, Statement stmt) {

		close(stmt);
		close(conn);
	}

	/**
	 * ͬʱ�ر����ݿ����ӡ�Statement���󡢽����
	 * 
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(conn);
	}
}