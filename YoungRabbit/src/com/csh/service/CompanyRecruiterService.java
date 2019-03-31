package com.csh.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.util.PageModel;

/*********************
 * Author shaohui-chen
 */
public class CompanyRecruiterService {

	// by xiaopan
	public static String getRecruiterListRs(int startRow, int endRow, String sql, Object[] param) {
		sql = "SELECT * FROM (SELECT A.*, ROWNUM AS RN FROM (" + sql + ") A " + ") WHERE RN BETWEEN " + startRow + " AND " + endRow;
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		JSONArray json_arr = new JSONArray();
		try {
			while (rs.next()) {
				JSONObject jsonObj = new JSONObject();
				Long TEAM_ID = rs.getLong("TEAM_ID");
				Long MEMBER_ID = rs.getLong("MEMBER_ID");
				String sql_1 = "select USER_ID as PARENT_ID,USERNAME as PARENT_NAME, ROLE as PARENT_ROLE from USERS where USER_ID= " + TEAM_ID;
				String sql_2 = "select USER_ID,USERNAME,FIRST_NAME,LAST_NAME,EMAIL,PWD, ROLE,HAPPEN_DATE from USERS where USER_ID= " + MEMBER_ID;
				Connection con1 = BaseDao.getConn();
				ResultSet rs1 = BaseDao.getRs(con1, sql_1, null);
				Connection con2 = BaseDao.getConn();
				ResultSet rs2 = BaseDao.getRs(con2, sql_2, null);

				// get raw data
				ResultSetMetaData metaData1 = rs1.getMetaData();
				int columnCount1 = metaData1.getColumnCount();
				try {
					while (rs1.next()) {
						// each column
						for (int i = 1; i <= columnCount1; i++) {
							String columnName1 = metaData1.getColumnLabel(i);
							String value1 = rs1.getString(columnName1);
							jsonObj.put(columnName1, value1);
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						con1.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// get raw data
				ResultSetMetaData metaData2 = rs2.getMetaData();
				int columnCount2 = metaData2.getColumnCount();
				try {
					while (rs2.next()) {
						// each column
						for (int i = 1; i <= columnCount2; i++) {
							String columnName2 = metaData2.getColumnLabel(i);
							String value2 = rs2.getString(columnName2);
							jsonObj.put(columnName2, value2);
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						con2.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				json_arr.put(jsonObj);
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
		return json_arr.toString();
	}

	// by xiaopan
	public static int getRecruiterListRows(String rowContSql, Object[] param) {
		int rows = BaseDao.getRowCount(rowContSql, param);
		return rows;
	}

	// by lizhenyu
	public static String getRs(String sql, Object object) {
		// TODO Auto-generated method stub
		return null;
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
				// String pwd = rs.getString("PWD");//页面缓存中排除pwd
				String status = rs.getString("STATUS");
				int role = rs.getInt("ROLE");

				ac.setUserId(userId);
				ac.setFirstName(firstName);
				ac.setLastName(lastName);
				ac.setEmail(email);
				// ac.setPwd(pwd);
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
	public static PageModel<Users> findPageModel(int currentPage, int pageSize, String sql, String rowContSql, Object[] param) {
		System.out.println("enter--PageModel--findPageModel-->");
		PageModel<Users> pageModel = new PageModel<Users>();
		pageModel.setCurrentPage(currentPage);

		sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (" + sql + ") A WHERE ROWNUM <= " + (currentPage) * pageSize + ") WHERE RN >= " + ((currentPage - 1) * pageSize);
		System.out.println("enter--PageModel--findPageModel--sql-->" + sql);
		// sql = sql + " order by userId desc limit "+ (currentPage-1)*pageSize
		// +","+pageSize + " ";
		System.out.println("PageModel--findPageModel-->" + sql);
		pageModel.setList(findWebUsers(sql, param));
		System.out.println("PageModel--findWebUsers-->" + sql + "---" + param);
		pageModel.setPageSize(pageSize);
		System.out.println("PageModel--findWebUsers--pageSize->" + pageSize);

		System.out.println("PageModel--findWebUsers--BaseDao.getRowCount(rowContSql, param)->" + BaseDao.getRowCount(rowContSql, param));
		pageModel.setTotalRecord(BaseDao.getRowCount(rowContSql, param));

		return pageModel;
	}

	// delete Company Recruiter
	public static int deleteRecruiterAdmin(String userId, String parentId) {
		String sql1 = "delete from SALES_MEMBER where MEMBER_ID=" + userId + "and TEAM_ID=" + parentId;
		String sql = "delete from USERS  where USER_ID=" + userId;
		int i = BaseDao.exeSql(sql, null);
		int j = BaseDao.exeSql(sql1, null);
		if (i == -1 && j == -1) {
			return -1;
		}
		return i;

	}

	// delete Company Recruiter
	public static int deleteRecruiterAdmin_1(String userId) {
		String sql1 = "delete from SALES_MEMBER where MEMBER_ID=" + userId;
		String sql = "delete from USERS  where USER_ID=" + userId;
		int i = BaseDao.exeSql(sql, null);
		int j = BaseDao.exeSql(sql1, null);
		if (i == -1 && j == -1) {
			return -1;
		}
		return i;

	}

	// delete sales member
	public static int deleteSalesMember(String userId, String parentId) {
		String sql = "DELETE FROM SALES_MEMBER WHERE TEAM_ID=" + parentId + " AND MEMBER_ID=" + userId + " AND ROLE=0";
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

	// get ROLE
	public static long getROLE(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		long ROLE = 0;
		try {
			while (rs.next()) {
				ROLE = rs.getLong("ROLE");
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
		return ROLE;
	}

	// get TEAM_ID
	public static long getTEAMID(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		long TEAM_ID = -1;
		try {
			while (rs.next()) {
				TEAM_ID = rs.getLong("TEAM_ID");
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
		return TEAM_ID;
	}

	// get USER_ID
	public static long getUserID(String sql, Object[] param) {
		Connection con = BaseDao.getConn();
		ResultSet rs = BaseDao.getRs(con, sql, param);
		long USER_ID = -1;
		try {
			while (rs.next()) {
				USER_ID = rs.getLong("USER_ID");
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
