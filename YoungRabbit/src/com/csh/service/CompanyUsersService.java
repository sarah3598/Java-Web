package com.csh.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.util.PageModel;

/*********************
 * Author shaohui-chen
 */
public class CompanyUsersService {

	// by xiaopan
	public static ResultSet getUsersListRs(int startRow, int endRow, String sql, Object[] param) {

		sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RN FROM (" + sql + ") A " + ") WHERE RN BETWEEN " + startRow
				+ " AND " + endRow;
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		return rs;
	}

	// by xiaopan
	public static int getUsersListRows(String rowContSql, Object[] param) {
		int rows = BaseDao.getRowCount(rowContSql, param);
		return rows;
	}

	// by xiaopan
	public static List<Users> convertUsersRsToList(ResultSet rs) {

		List<Users> list = new ArrayList<Users>();
		try {
			while (rs.next()) {
				Users ac = new Users();
				// int userId = rs.getInt("userId");
				Long userId = rs.getLong("USER_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				//String pwd = rs.getString("PWD");//页面缓存中排除pwd
				String status = rs.getString("STATUS");
				int role = rs.getInt("ROLE");

				ac.setUserId(userId);
				ac.setFirstName(firstName);
				ac.setLastName(lastName);
				ac.setEmail(email);
				//ac.setPwd(pwd);
				ac.setStatus(status);
				ac.setRole(role);

				list.add(ac);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// by li zhenyu verify email
	public static Boolean verify_Email(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		try {
			while (rs.next()) {
				return true;
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
		return false;
	}

	// by li zhenyu login
	public static ResultSet verify_Login(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		return rs;
	}

	// ��ѯUsers

	public static List<Users> findWebUsers(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		System.out.println("findWebUsers-1-->");
		ResultSet rs = BaseDao.getRs(con, sql, param);
		System.out.println("findWebUsers-2-->");
		List<Users> list = new ArrayList<Users>();
		try {
			while (rs.next()) {
				Users ac = new Users();

				// int userId = rs.getInt("userId");
				Long userId = rs.getLong("USER_ID");
				String userName = rs.getString("USER_NAME");
				String email = rs.getString("EMAIL");
				String pwd = rs.getString("PWD");
				String status = rs.getString("STATUS");

				ac.setUserId(userId);
				ac.setUserName(userName);
				ac.setEmail(email);
				ac.setPwd(pwd);
				ac.setStatus(status);

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

	// ��ѯ�û���ҳ����
	public static PageModel<Users> findPageModel(int currentPage, int pageSize, String sql, String rowContSql,
			Object[] param) {
		System.out.println("enter--PageModel--findPageModel-->");
		PageModel<Users> pageModel = new PageModel<Users>();
		pageModel.setCurrentPage(currentPage);

		sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (" + sql + ") A WHERE ROWNUM <= " + (currentPage) * pageSize
				+ ") WHERE RN >= " + ((currentPage - 1) * pageSize);
		System.out.println("enter--PageModel--findPageModel--sql-->" + sql);
		// sql = sql + " order by userId desc limit "+ (currentPage-1)*pageSize
		// +","+pageSize + " ";
		System.out.println("PageModel--findPageModel-->" + sql);
		pageModel.setList(findWebUsers(sql, param));
		System.out.println("PageModel--findWebUsers-->" + sql + "---" + param);
		pageModel.setPageSize(pageSize);
		System.out.println("PageModel--findWebUsers--pageSize->" + pageSize);

		System.out.println("PageModel--findWebUsers--BaseDao.getRowCount(rowContSql, param)->"
				+ BaseDao.getRowCount(rowContSql, param));
		pageModel.setTotalRecord(BaseDao.getRowCount(rowContSql, param));

		return pageModel;
	}

	// delete Company Users
	public static int deleteCompanyUsers(String userId) {
		String sql = "delete from USERS  where USER_ID=" + userId;
		int i = BaseDao.exeSql(sql, null);
		if (i == -1) {
			return -1;
		}
		return i;

	}

	// ����û�
	public static int auditWebUsers(String userId) {

		String sql = "update users set bak = 1 where user_id=" + userId;
		int i = BaseDao.exeSql(sql, null);
		if (i == -1) {
			return -1;
		}
		return i;

	}

	// do user register
	public static Boolean doUserRegister(String sql, Object[] param) {
		int result = BaseDao.exeSql(sql, param);
		if (result == -1) {
			return false;
		}
		return true;
	}

	// get USER_ID
	public static int getUserID(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		int USER_ID = -1;
		try {
			while (rs.next()) {
				USER_ID = rs.getInt("USER_ID");
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
		return USER_ID;
	}

}
