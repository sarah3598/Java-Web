package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.CommonUtil;

/**
 * ���ݿ���������ࣨJDBC��
 * @author Administrator
 *
 */
public class DBHelper {
	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;
	
	/**
	 * �������ݿ�
	 * @param driverStr  ����������ַ���
	 * @param dbmsName   �����ݿ����ϵͳ��(Сд)
	 * @param serverName ����������
	 * @param port       ���˿ں�
	 * @param dbName     �����ݿ���
	 * @param user       ���û���
	 * @param pwd        ������
	 * @return
	 * @throws Exception
	 */
	public static boolean connectDB(String driverStr,String dbmsName,String serverName,String port,
			String dbName,String user,String pwd) throws Exception{
		String url = "";
		
		//���ز�ע������
		Class.forName(driverStr);
		if("sqlserver".equals(dbmsName)){
			url="jdbc:"+dbmsName+"://"+serverName+":"+port+";databaseName="+dbName;
		}else if("mysql".equals(dbmsName)){
			url="jdbc:"+dbmsName+"://"+serverName+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=UTF-8";
			//+"&useUnicode=true&characterEncoding=UTF-8"
		}else if("oracle".equals(dbmsName)){
			url="jdbc:"+dbmsName+":thin:@"+serverName+":"+port+":"+dbName;
		}
		
		//�������ݿ�
		conn=DriverManager.getConnection(url,user,pwd);	
		stmt=conn.createStatement();	
		return true;
	}
	
	/**
	 * ��ѯ
	 * @param sql
	 * @return
	 * @throws Exception 
	 */
	public static ResultSet queryData(String sql) throws Exception{
		rs=stmt.executeQuery(sql);
		System.out.println(rs);
		return rs;
	}
	
	/**
	 * ����(����ɾ����)
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public static int updateData(String sql) throws Exception{
		int iFlag=stmt.executeUpdate(sql);
		return iFlag;
	}
	
	/**
	 *  �ر����ݿ����
	 * @return
	 * @throws Exception
	 */
	public static boolean closeDB() throws Exception{
		if(rs!=null){
			rs.close();
		}
		if(stmt!=null){
			stmt.close();
		}
		if(conn!=null){
			conn.close();
		}
		return true;
	}
	
	public static Connection getConn() {
		return conn;
	}	

}
