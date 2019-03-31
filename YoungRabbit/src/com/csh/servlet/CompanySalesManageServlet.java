package com.csh.servlet;

/*********************
 * Author shaohui-chen
 ******************** */
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.service.CompanyUsersService;
import com.csh.service.JsonService;
import com.csh.util.Encoder;
import com.csh.util.PageModel;
import com.csh.util.Pagination;

public class CompanySalesManageServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String op = request.getParameter("OP");

		if (op == null) {
			getSalesList(request, response);
			return;
		}

		switch (op) {
		// manage Sales ====================================
		case "1":// add_Sales
			try {
				addSales(request, response);
			} catch (NumberFormatException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "2":// update_Sales
			updateSales(request, response);
			break;
		case "3":// delete_Sales
			try {
				deleteSales(request, response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "4":// query_Sales
			querySales(request, response);
			break;

		case "5":// query_Sales
			querySales2(request, response);
			break;
		case "999":// pagination
			paging(request, response);
			break;
		}
	}

	// by lizhenyu query Company Sales
	private void querySales2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ROLE = request.getParameter("ROLE");
		if(ROLE==null||ROLE.equals("")){//ROLE 为空时，默认显示所有销售
			ROLE=" ROLE=1 OR ROLE=2 OR ROLE=4 OR ROLE=8 OR ROLE=16 ";
		}
		String sql = "select * from USERS t where " + ROLE + " ORDER BY ROLE DESC";

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String pSql = "SELECT ROWNUM AS RN,B.* FROM(" + sql + ")B";

		String listSql = "SELECT * FROM (" + pSql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM (" + sql + ")";

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		page.setCurrentSql(sql);
		page.setPageParams(ROLE.replace("ROLE=", "").replace("OR", ",").replace(" ", ""));
		request.setAttribute("page", page);
		request.getSession().setAttribute("page", page);

		request.setAttribute("sales_list", jsonarr.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_sales_manage.jsp").forward(request, response);

	}

		// add by xiaopan
		public void paging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getPagingData(request);
			request.setAttribute("sales_list", jsonarr1.toString());
			request.getRequestDispatcher("/zh_CN/company/yb_db_sales_manage.jsp").forward(request, response);
		}
	
		public JSONArray getPagingData(HttpServletRequest request) throws NumberFormatException {
		
		Pagination paging = (Pagination) request.getSession().getAttribute("page");
		
		
		if (paging == null) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getSalesListData(request);
			return jsonarr1;
		}

		
		String sql = paging.getCurrentSql();
		
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String pSql = "SELECT ROWNUM AS RN,B.* FROM(" + sql + ")B";

		String listSql = "SELECT * FROM (" + pSql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM (" + sql + ")";

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// paging
		Pagination page = (Pagination)request.getSession().getAttribute("page");
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		
		request.setAttribute("page", page);
		request.getSession().setAttribute("page", page);
		return jsonarr;
	}

	// by lizhenyu query Company Sales
	private void querySales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String users = request.getParameter("users");
		// System.out.println(users);
		String searchUsers = request.getParameter("searchUsers");
		// System.out.println(searchUsers);
		String rs_json = "";
		String msg = "";
		if (searchUsers == "") {
			// 输入为空
			getSalesList(request, response);
		} else {
			switch (users) {
			case "USER_ID":
				String sqlwhere = "";
				sqlwhere = "AND USER_ID LIKE \'%" + searchUsers + "%\'";

				int pageSize = 100;
				int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
				int startRow = (currentPage - 1) * pageSize;
				int endRow = startRow + pageSize;// include the end row

				String listSql = "select * from USERS t where ROLE>0 AND ROLE<=512 ";
				String rowSql = "select count(1) from USERS t where ROLE>0 AND ROLE<=512 ";

				listSql = listSql + sqlwhere;
				rowSql = rowSql + sqlwhere;

				ResultSet rs = CompanyUsersService.getUsersListRs(startRow, endRow, listSql, null);
				int rows = CompanyUsersService.getUsersListRows(rowSql, null);

				try {
					if (rs.isBeforeFirst()) {
						msg = "";
						rs_json = JsonService.getNormalJson(rs, msg);

					} else {
						msg = "没有相关搜索结果！";
						rs_json = JsonService.getNormalJson(rs, msg);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg = "搜索成功！数据加载失败！";
					rs_json = JsonService.getOperateResultNormalJson("true", null, msg);
				}
				break;

			case "EMAIL":
				sqlwhere = "AND EMAIL LIKE \'%" + searchUsers + "%\'";
				pageSize = 100;
				currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
				startRow = (currentPage - 1) * pageSize;
				endRow = startRow + pageSize;// include the end row

				listSql = "select * from USERS t where ROLE>0 AND ROLE<=512 ";
				rowSql = "select count(1) from USERS t where ROLE>0 AND ROLE<=512 ";

				listSql = listSql + sqlwhere;
				rowSql = rowSql + sqlwhere;

				rs = CompanyUsersService.getUsersListRs(startRow, endRow, listSql, null);
				rows = CompanyUsersService.getUsersListRows(rowSql, null);

				try {
					if (rs.isBeforeFirst()) {
						msg = "";
						rs_json = JsonService.getNormalJson(rs, msg);

					} else {
						msg = "没有相关搜索结果！";
						rs_json = JsonService.getNormalJson(rs, msg);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg = "搜索成功！数据加载失败！";
					rs_json = JsonService.getOperateResultNormalJson("true", null, msg);
				}
				break;

			case "USERNAME":
				sqlwhere = "AND USERNAME LIKE \'%" + searchUsers + "%\'";

				pageSize = 100;
				currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
				startRow = (currentPage - 1) * pageSize;
				endRow = startRow + pageSize;// include the end row

				listSql = "select * from USERS t where ROLE>0 AND ROLE<=512 ";
				rowSql = "select count(1) from USERS t where ROLE>0 AND ROLE<=512 ";

				listSql = listSql + sqlwhere;
				rowSql = rowSql + sqlwhere;

				rs = CompanyUsersService.getUsersListRs(startRow, endRow, listSql, null);
				rows = CompanyUsersService.getUsersListRows(rowSql, null);

				try {
					if (rs.isBeforeFirst()) {
						msg = "";
						rs_json = JsonService.getNormalJson(rs, msg);

					} else {
						msg = "没有相关搜索结果！";
						rs_json = JsonService.getNormalJson(rs, msg);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg = "搜索成功！数据加载失败！";
					rs_json = JsonService.getOperateResultNormalJson("true", null, msg);
				}
				break;
			}

		}
		JsonService.ResponseJson(request, response, rs_json);

	}

	// by lizhenyu 删除用户，返回新列表
	public void deleteSales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JSONException {
		String USER_ID = request.getParameter("USER_ID");
		String ROLE = request.getParameter("ROLE");

		String normal_json = "";
		String msg = "";

		// 删除销售代理和独家销售代理
		if (Integer.parseInt(ROLE) >= 1 && Integer.parseInt(ROLE) <= 2) {
			String sql = "DELETE FROM SALES_MEMBER WHERE MEMBER_ID=? AND ROLE=?";
			Object[] param = new Object[2];
			param[0] = USER_ID;
			param[1] = ROLE;
			int result = BaseDao.exeSql(sql, param);
			if (result >= 0) {
				String sql1 = "DELETE FROM USERS WHERE USER_ID=?";
				Object[] param1 = new Object[1];
				param1[0] = USER_ID;
				int result1 = BaseDao.exeSql(sql1, param1);
				if (result1 == 1) {
					// 删除完成后，取出数据，刷新列表
					JSONArray jsonarr = new JSONArray();
					jsonarr = getSalesListData(request);
					msg = "删除成功！";
					normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
				} else
					normal_json = JsonService.getErrorMsgNormalJson("删除失败！");

			}
		}

		// 删除组长和经理
		if (Integer.parseInt(ROLE) >= 4 && Integer.parseInt(ROLE) <= 8) {
			JSONArray jsonarr1 = new JSONArray();

			// 查询SALES_MEMBER表中该经理团队团队中是否还有成员。
			String sql9 = "SELECT COUNT(1) FROM SALES_MEMBER WHERE TEAM_ID=? AND ROLE>=1 AND ROLE<=2";
			Object[] param9 = new Object[1];
			param9[0] = USER_ID;
			int rows1 = BaseDao.getRowCount(sql9, param9);

			if (rows1 == 0) {
				// 删除表SALES_TEAM中该经理团队信息（销售经理和培训经理）
				String sql0 = "DELETE FROM SALES_TEAM WHERE TEAM_ID=?";
				Object[] param0 = new Object[1];
				param0[0] = USER_ID;
				int flag0 = BaseDao.exeSql(sql0, param0);

				// 删除用户表USERS的成员信息
				String sql3 = "DELETE FROM USERS WHERE USER_ID=?";
				Object[] param3 = new Object[1];
				param3[0] = USER_ID;
				int flag3 = BaseDao.exeSql(sql3, param3);

				int flag1 = 0;
				int flag2 = 0;

				// 查询原副总裁的ID
				String sql_team_id = "SELECT TEAM_ID FROM SALES_MEMBER WHERE MEMBER_ID=" + USER_ID + "AND ROLE=" + ROLE;
				JSONArray rs_team_id = BaseDao.getRsJSONArray(sql_team_id, null);

				if (rs_team_id.length() > 0) {

					String VP_PARENT_ID = rs_team_id.getJSONObject(0).getString("TEAM_ID");// 原副总裁的ID
					// 修改SALES_MEMBER表中的PARENT_ID：（处理自己培养的销售员升级为经理：将上级的ID替换被培养人的PARENT_ID）
					String sql1 = "UPDATE SALES_MEMBER SET PARENT_ID=? WHERE PARENT_ID=? AND ROLE>=4 AND ROLE<=8";
					Object[] param1 = new Object[2];
					param1[0] = VP_PARENT_ID;
					param1[1] = USER_ID;
					flag1 = BaseDao.exeSql(sql1, param1);

					// 删除副总裁团队成员表SALES_MEMBER的成员信息(该经理所在的副总裁团队)
					String sql2 = "DELETE FROM SALES_MEMBER WHERE TEAM_ID=? AND MEMBER_ID=?";
					Object[] param2 = new Object[2];
					param2[0] = VP_PARENT_ID;
					param2[1] = USER_ID;
					flag2 = BaseDao.exeSql(sql2, param2);
				}

				if (flag0 >= 0 && (flag1 == 0 || flag1 == 1) && (flag2 == 0 || flag1 == 1) && flag3 > 0) {
					msg = "删除成功！";
					jsonarr1 = getSalesListData(request);
					normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);

				} else {
					msg = "删除失败！";
					normal_json = JsonService.getErrorMsgNormalJson(msg);
				}
			} else {
				msg = "该团队还有成员，不能删除！";
				jsonarr1 = getSalesListData(request);
				normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);
			}
		}

		JsonService.ResponseJson(request, response, normal_json);
	}

	// by lizhenyu 添加用户,并返回新列表
	public void addSales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, JSONException {
		String FIRST_NAME = request.getParameter("FIRST_NAME");
		String LAST_NAME = request.getParameter("LAST_NAME");
		String EMAIL = request.getParameter("EMAIL");
		String USERNAME = LAST_NAME + FIRST_NAME;
		String PWD = request.getParameter("PWD");
		if(!(PWD.equals(""))){
			try {
				PWD =Encoder.EncoderByMd5(PWD);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String ROLE = request.getParameter("ROLE");
		String TYPE = request.getParameter("TYPE");
		//String PHONE = request.getParameter("PHONE");
		//String WECHAT = request.getParameter("WECHAT");
		//String FACEBOOK = request.getParameter("FACEBOOK");

		Object CREATE_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");

		String normal_json = "";
		String msg = "";

		// 先判断该用户是否存在，若存在不允许添加
		String select_sql = "SELECT COUNT(1) FROM USERS WHERE USERNAME='" + USERNAME + "' OR EMAIL='" + EMAIL + "'";
		int rows = BaseDao.getRowCount(select_sql, null);
		if (rows > 0) {
			// 取出数据，刷新列表
			msg = "该用户名或邮箱已存在，请勿重复添加！";
			normal_json = JsonService.getOperateResultNormalJson("true",null,msg);
			JsonService.ResponseJson(request, response, normal_json);
			return;
		}

		// 该用户不存在，允许添加
		String sql = "INSERT INTO USERS(FIRST_NAME,LAST_NAME,EMAIL,PWD,ROLE,GROLE,USERNAME) VALUES(?,?,?,?,?,?,?)";
		Object[] param = new Object[7];
		param[0] = FIRST_NAME;
		param[1] = LAST_NAME;
		param[2] = EMAIL;
		param[3] = PWD;
		param[4] = ROLE;
		param[5] = ROLE;
		//param[6] = PHONE;
		//param[7] = WECHAT;
		//param[8] = FACEBOOK;
		param[6] = USERNAME;

		int result = BaseDao.exeSql(sql, param);
		if (result == 1) {
			if (Integer.parseInt(ROLE) >= 4 && Integer.parseInt(ROLE) <= 16) {
				String sql_user_id = "SELECT USER_ID FROM USERS WHERE EMAIL='" + EMAIL + "'";
				JSONArray rs_user_id = BaseDao.getRsJSONArray(sql_user_id, null);
				// 查询用户id
				String USER_ID = rs_user_id.getJSONObject(0).getString("USER_ID");
				String sql1 = "INSERT INTO SALES_TEAM(TEAM_ID,TYPE,CREATE_ID,STATUS) VALUES(?,?,?,?)";
				Object[] param1 = new Object[4];
				param1[0] = USER_ID;
				param1[1] = TYPE;
				param1[2] = CREATE_ID;
				param1[3] = 1;

				int result1 = BaseDao.exeSql(sql1, param1);
				if (result1 == 1) {
					// 添加完成后，取出数据，刷新列表
					
					msg = "添加成功！";
					normal_json = JsonService.getOperateResultNormalJson("true",null,msg);
				} else
					normal_json = JsonService.getErrorMsgNormalJson("添加失败！");
			} else {
				msg = "添加成功！";
				// 取出数据，刷新列表
				normal_json = JsonService.getOperateResultNormalJson("true",null,msg);
			}
		} else
			normal_json = JsonService.getErrorMsgNormalJson("数据异常！");
		JsonService.ResponseJson(request, response, normal_json);
	}

	// by lizhenyu 修改用户信息
	public void updateSales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String FIRST_NAME = request.getParameter("FIRST_NAME");
		String LAST_NAME = request.getParameter("LAST_NAME");
		// String EMAIL = request.getParameter("EMAIL");
		String USERNAME = LAST_NAME + FIRST_NAME;
		String PWD = request.getParameter("PWD");
		if (!(PWD.equals(""))) {
			try {
				PWD = Encoder.EncoderByMd5(PWD);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//String ROLE = request.getParameter("ROLE");
		//String PHONE = request.getParameter("PHONE");
		//String WECHAT = request.getParameter("WECHAT");
		//String FACEBOOK = request.getParameter("FACEBOOK");
		String USER_ID = request.getParameter("USER_ID");
		// String CREATE_ID = "10011";// 应该从session中获取当前用户名
		
		String normal_json = "";
		String msg = "";
		if (!(PWD.equals(""))) {

			String sql = "UPDATE USERS SET FIRST_NAME=?,LAST_NAME=?,PWD=?,USERNAME=? WHERE USER_ID=?";
			Object[] param = new Object[5];
			param[0] = FIRST_NAME;
			param[1] = LAST_NAME;
			// param[2] = EMAIL;
			param[2] = PWD;
			//param[4] = ROLE;
			//param[3] = PHONE;
			//param[4] = WECHAT;
			//param[5] = FACEBOOK;
			param[3] = USERNAME;
			param[4] = USER_ID;
	
			
	
			int result = BaseDao.exeSql(sql, param);
			if (result == 1) {
				msg = "修改成功！";
				normal_json = JsonService.getOperateResultNormalJson("true", null, msg);
			} else
				normal_json = JsonService.getErrorMsgNormalJson("修改失败！");
		}else{
			String sql = "UPDATE USERS SET FIRST_NAME=?,LAST_NAME=?,USERNAME=? WHERE USER_ID=?";
			Object[] param = new Object[4];
			param[0] = FIRST_NAME;
			param[1] = LAST_NAME;
			param[2] = USERNAME;
			param[3] = USER_ID;
	
			
	
			int result = BaseDao.exeSql(sql, param);
			if (result == 1) {
				msg = "修改成功！";
				normal_json = JsonService.getOperateResultNormalJson("true", null, msg);
			} else
				normal_json = JsonService.getErrorMsgNormalJson("修改失败！");
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	public JSONArray getSalesListData(HttpServletRequest request) throws NumberFormatException {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row
		
		String ROLE = request.getParameter("ROLE");
		if(ROLE==null||ROLE.equals("")){//ROLE 为空时，默认显示所有销售
			ROLE=" ROLE=1 OR ROLE=2 OR ROLE=4 OR ROLE=8 OR ROLE=16 ";
		}else{
			ROLE="ROLE="+ROLE;
		}
		String sql = "select * from USERS t where " + ROLE + " ORDER BY ROLE DESC";
		String rsql = "SELECT ROWNUM AS RN,B.* FROM("+sql+")B";
		String listSql = "SELECT * FROM (" + rsql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "select count(1) from ("+sql+")";

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		page.setCurrentSql(sql);
		
		request.setAttribute("page", page);
		request.getSession().setAttribute("page", page);
		
		return jsonarr;
	}

	// add by xiaopan
	public void getSalesList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getSalesListData(request);
		request.setAttribute("sales_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_sales_manage.jsp").forward(request, response);
	}

	public void listFormalUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		String sql = "select * from USERS t where 1=1 ";
		String rowSql = "select count(1) from USERS t where 1=1 ";
		int pageSize = 100;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		PageModel<Users> page = CompanyUsersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.getSession().setAttribute("page", page);
		request.getSession().setAttribute("webusers", oper);
		try {
			request.getRequestDispatcher("/jsp/webuserlist.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// ��δ����û�?
	public void listNoAuditWebUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users user = (Users) request.getSession().getAttribute("webusers");// ��¼����Ա
		if (user == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		String sql = "select * from USERS t where 1=1 and t.bak=0";
		String rowSql = "select count(1) from USERS t where 1=1 and t.bak=0 ";
		int pageSize = 100;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		PageModel<Users> page = CompanyUsersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		System.out.println("page.getTotalRecord-->" + page.getTotalRecord());
		request.setAttribute("page", page);
		request.getSession().setAttribute("webusers", user);
		System.out.println("login-dopost--enter listWebUsers-end->");
		try {
			request.getRequestDispatcher("/jsp/webuseraudit.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// ����û�?
	public void auditWebUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("enter servlet audituser->");

		String user_Id = request.getParameter("userId");
		System.out.println("-auditWebUsers-userId->" + user_Id);

		int uid = CompanyUsersService.auditWebUsers(user_Id);
		if (uid == -1) {
			String message = "ʧ��";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		} else {

			listNoAuditWebUsers(request, response);
		}

	}

	public void listSearchWebUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reportLevelSe = request.getParameter("reportLevelSe");
		String reportStatusSe = request.getParameter("reportDistrictSe");
		String reportEmailSe = request.getParameter("reportDaySe");
		Users user = (Users) request.getSession().getAttribute("webusers");
		if (user == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		String sqlwhere = "";
		if (!"0".equals(reportLevelSe))
			sqlwhere = "and STATUS = " + reportLevelSe;
		if (!"9".equals(reportStatusSe))
			sqlwhere = sqlwhere + " and BAK = " + reportStatusSe;
		if (!"".equals(reportEmailSe))
			sqlwhere = sqlwhere + " and EMAIL like \'" + reportEmailSe + "\'";

		String sql = "select * from USERS t where 1=1 ";
		String rowSql = "select count(1) from USERS t where 1=1 ";
		sql = sql + sqlwhere;
		rowSql = rowSql + sqlwhere;

		int pageSize = 100;
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		PageModel<Users> page = CompanyUsersService.findPageModel(currentPage, pageSize, sql, rowSql, null);
		request.setAttribute("page", page);
		request.getSession().setAttribute("webusers", user);
		try {
			// ��Ҫ�����쳣�Ĵ���
			request.getRequestDispatcher("/jsp/webuserlist.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// �༭�û�
	public void editWebUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}
		String userId = request.getParameter("userId");

		String sql = "select * from USERS t where t.USER_Id=" + userId;
		Users userinfo = CompanyUsersService.findWebUsers(sql, null).get(0);
		request.getSession().setAttribute("userinfo", userinfo);// ǰ̨��ʾ��Ϣ�õ�,�鵽����Ϣ��;
		request.getSession().setAttribute("userId", userId);
		request.getRequestDispatcher("/jsp/webUseredit.jsp").forward(request, response);

	}

	// �༭�޸��û�
	public void editaddUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users oper = (Users) request.getSession().getAttribute("webusers");
		if (oper == null) {
			request.getRequestDispatcher("/jsp/webUserlogin.jsp").forward(request, response);
			return;
		}

		String email = request.getParameter("userEmail");
		String firstname = request.getParameter("userFirstname");
		String lastname = request.getParameter("userLastname");
		String username = firstname + lastname;
		String address = request.getParameter("userAddress");
		String city = request.getParameter("userCity");
		String zipcode = request.getParameter("userZipcode");
		String country = request.getParameter("userCountry");
		String phone = request.getParameter("userPhone");
		String password = request.getParameter("userPassword");
		String status = request.getParameter("Status");
		String contract = request.getParameter("userContract");

		String sql = "INSERT INTO USERS(Email,FIRST_NAME,LAST_NAME,USER_NAME,ADDRESS,CITY,ZIP_CODE,COUNTRY,PHONE,PWD,STATUS,CONTRACT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] param = new Object[12];
		param[0] = email;
		param[1] = firstname;
		param[2] = lastname;
		param[3] = username;
		param[4] = address;
		param[5] = city;
		param[6] = zipcode;
		param[7] = country;
		param[8] = phone;
		param[9] = password;
		param[10] = status;
		param[11] = contract;

		int result = BaseDao.exeSql(sql, param);
		String message = "";
		if (result == 1) {
			message = "editAddUser Success!";
		} else {
			message = "Failure to editAddUser !";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);

	}

}
