package com.csh.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.csh.dao.BaseDao;
import com.csh.domain.Inventory;
import com.csh.domain.ProductInfo;
import com.csh.domain.Users;
import com.csh.service.CompanyProductService;
import com.csh.service.CompanyTeamManageService;
import com.csh.service.CompanyUsersService;
import com.csh.service.InventoryService;
import com.csh.service.JsonService;
import com.csh.service.ProductService;
import com.csh.util.DateUtil;
import com.csh.util.PageModel;
import com.csh.util.Pagination;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Xp Lyu
 *
 */
public class CompanyPersonalTeamManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if (!LoginControlPublicServlet.LoginSessionValidate(request, response, 1))
			return;
		String method = request.getParameter("method");

		switch (method) {
		case "train_team_add":
			addTrainTeam(request, response);
			break;
		// case "train_team_close":
		// closeTrainTeam(request, response);
		// break;
		case "train_team_list":
			findTrainTeamList(request, response);
			break;
		case "train_team_delete":
			deleteTrainTeam(request, response);
			break;
		case "do_train":
			doTrain(request, response);
			break;

		case "team_list":
			findTeamList(request, response);
			break;

		case "vp_team_list":
			findVPTeamList(request, response);
			break;
		case "vp_team_add":
			addVPTeam(request, response);
			break;
		case "vp_team_detail":
			findVPTeamDetail(request, response);
			break;
		case "vp_team_close":
			closeVPTeam(request, response);
			break;
		case "vp_team_delete":
			deleteVPTeam(request, response);
			break;
		case "vp_adjust_list":
			findVPAdjustList(request, response);
			break;

		case "manager_team_add":
			addManagerTeam(request, response);
			break;
		case "manager_team_sales_add":
			addManagerTeamSales(request, response);
			break;
		case "get_no_m_team_sales":
			findNoManagerTeamSalesList(request, response);
			break;
		case "manager_team_move":
			moveManagerTeam(request, response);
			break;

		case "manager_team_move_in":
			moveManagerTeamIn(request, response);
			break;
		case "manager_team_close":
			closeManagerTeam(request, response);
			break;
		case "manager_team_delete":
			deleteManagerTeam(request, response);
			break;

		case "sales_member_delete":
			deleteSalesMember(request, response);
			break;
		case "sales_member_dimission":
			salesMemberDimission(request, response);
			break;
		case "sales_member_move":
			moveSalesMember(request, response);
			break;

		case "sales_team_add":
			addSalesTeam(request, response);
			break;

		case "999":// pagination
			teamListPaging(request, response);
			break;

		default:
			findTrainTeamList(request, response);
			break;
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// add by xiaopan
	public void findTrainTeamList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getTrainTeamListData(request);
		String sel_json = getSalesAndManagerListData();

		request.setAttribute("train_team_list", jsonarr1.toString());
		request.setAttribute("sales_and_manager_list", sel_json);
		request.getRequestDispatcher("/zh_CN/company/yb_db_train_team.jsp").forward(request, response);

	}

	// add by xiaopan
	public void findTeamList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getTeamListData(request);
		String sel_json1 = getVPListDataForAdd();
		String sel_json2 = getManagerListDataForAdd();
		String sel_json3 = getVPListDataForMove();
		String sel_json4 = getManagerListDataForMove();

		request.setAttribute("vp_team_list", jsonarr1.toString());
		request.setAttribute("vp_list", sel_json1);
		request.setAttribute("manager_list", sel_json2);
		request.setAttribute("vp_move_list", sel_json3);
		request.setAttribute("manager_move_list", sel_json4);
		request.getRequestDispatcher("/zh_CN/company/yb_db_personal_sales_team.jsp").forward(request, response);

	}

	// add by xiaopan
	public void teamListPaging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getTeamListPagingData(request);
		String sel_json1 = getVPListDataForAdd();
		String sel_json2 = getManagerListDataForAdd();
		String sel_json3 = getVPListDataForMove();
		String sel_json4 = getManagerListDataForMove();

		request.setAttribute("vp_team_list", jsonarr1.toString());
		request.setAttribute("vp_list", sel_json1);
		request.setAttribute("manager_list", sel_json2);
		request.setAttribute("vp_move_list", sel_json3);
		request.setAttribute("manager_move_list", sel_json4);
		request.getRequestDispatcher("/zh_CN/company/yb_db_personal_sales_team.jsp").forward(request, response);

	}

	// add by xiaopan
	public void findVPTeamList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getVPTeamListData(request);
		String sel_json1 = getVPListDataForAdd();
		String sel_json2 = getManagerListDataForAdd();
		String sel_json3 = getVPListDataForMove();
		String sel_json4 = getManagerListDataForMove();

		request.setAttribute("vp_team_list", jsonarr1.toString());
		request.setAttribute("vp_list", sel_json1);
		request.setAttribute("manager_list", sel_json2);
		request.setAttribute("vp_move_list", sel_json3);
		request.setAttribute("manager_move_list", sel_json4);
		request.getRequestDispatcher("/zh_CN/company/yb_db_personal_sales_team.jsp").forward(request, response);

	}

	// add by xiaopan
	public void findNoManagerTeamSalesList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 取出是销售，但没有团队的人
		String sql = "SELECT A.USER_ID,A.ROLE,A.USERNAME FROM USERS A WHERE A.USER_ID NOT IN(SELECT MEMBER_ID FROM SALES_MEMBER) AND A.ROLE>=1 AND A.ROLE<=2";
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql, null);
		int rows = jsonarr.length();
		String select_json = "[";
		// each column
		try {
			for (int i = 0; i < rows; i++) {
				String role = jsonarr.getJSONObject(i).getString("ROLE");
				String user_id = jsonarr.getJSONObject(i).getString("USER_ID");
				String user_name = jsonarr.getJSONObject(i).getString("USERNAME");
				String user_id_role = user_id + "_" + role;
				select_json += "[\"" + user_id_role + "\",\"" + "销售 " + user_name + "\"]";
				if (i < rows - 1) {
					select_json += ",";
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		select_json += "]";

		String normal_json = "";
		String msg = "";

		normal_json = JsonService.getNormalJson(select_json, msg);
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by xiaopan
	public void findVPTeamDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getVPTeamDetailData(request);
		String normal_json = JsonService.getNormalJson(jsonarr1.toString(), null);
		JsonService.ResponseJson(request, response, normal_json);

	}

	// add by xiaopan
	public void findVPAdjustList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getVPAdjustListData(request);
		String normal_json = JsonService.getNormalJson(jsonarr1.toString(), null);
		JsonService.ResponseJson(request, response, normal_json);

	}

	// add by xiaopan
	public JSONArray getTrainTeamListData(HttpServletRequest request) throws NumberFormatException {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT a.FLAG,a.USER_ID,a.USERNAME,a.ROLE,b.TRAIN_ID,b.TEAM_ID,b.TYPE,b.COUNT FROM USERS a LEFT JOIN TRAIN_TEAM b ON a.USER_ID=b.TEAM_ID  WHERE (b.TYPE=1 OR b.TYPE=4) AND b.STATUS=1 ";
		String ssql = "SELECT ROWNUM AS RN,R.* FROM (" + sql + ")R ORDER BY RN ASC";

		String listSql = "SELECT * FROM (" + ssql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM (" + sql + ")";

		JSONArray jsonarr = new JSONArray();
		jsonarr = CompanyTeamManageService.getRsJSONArray(listSql, param);
		int rows = CompanyTeamManageService.getRows(rowSql, null);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getTeamListData(HttpServletRequest request) throws NumberFormatException {
		String YM = request.getParameter("YM");
		Boolean flag0 = (YM == null || YM.equals("undefined")) ? true : false;
		YM = flag0 ? DateUtil.getYearLastMonthString() : YM;

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		Object USER_ID = LoginControlPublicServlet.getSessionKeyValue(request, "USER_ID");
		
		
		// 01先取出所有团队
		String sql = "SELECT  a.USER_ID AS TEAM_ID,a.USER_ID,a.USERNAME,a.LAST_NAME,a.ROLE,b.STATUS,b.TYPE,(SELECT COUNT(*) FROM SALES_MEMBER WHERE TEAM_ID=b.TEAM_ID) AS SUM FROM USERS a LEFT JOIN SALES_TEAM b ON a.USER_ID=b.TEAM_ID WHERE b.STATUS=1 AND a.USER_ID="+USER_ID;
		String rsql = "SELECT ROWNUM AS RN, R.* FROM (" + sql + ")R ORDER BY RN ASC";
		String listSql = "SELECT * FROM (" + rsql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM (" + sql + ")";

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// 02关联团队的群销量，因为有可能在salary表中没有记录
		for (int i = 0; i < jsonarr.length(); i++) {
			String user_id = "";
			try {
				user_id = jsonarr.getJSONObject(i).getString("USER_ID");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 02在salary表中查询团队及成员的销量
			String sql02 = "SELECT GROUP_SALE FROM SALARY  WHERE USER_ID=? AND　MONTH=?";
			Object[] param02 = new Object[2];
			param02[0] = user_id;
			param02[1] = YM;
			JSONArray jsonarr02 = new JSONArray();
			jsonarr02 = BaseDao.getRsJSONArray(sql02, param02);

			String g_sale = "0";
			if (jsonarr02.length() > 0) {
				try {
					g_sale = jsonarr02.getJSONObject(0).getString("GROUP_SALE");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				jsonarr.getJSONObject(i).put("MONTH", YM);
				jsonarr.getJSONObject(i).put("GROUP_SALE", g_sale);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		page.setCurrentSql(sql);
		page.setPageParams(YM);
		request.setAttribute("page", page);
		request.getSession().setAttribute("page", page);

		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getTeamListPagingData(HttpServletRequest request) throws NumberFormatException {

		Pagination paging = (Pagination) request.getSession().getAttribute("page");
		if (paging == null) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getTeamListData(request);
			return jsonarr1;
		}

		String sql = paging.getCurrentSql();

		String YM = paging.getPageParams();
		Boolean flag0 = (YM == null || YM.equals("undefined")) ? true : false;
		YM = flag0 ? DateUtil.getYearMonthString() : YM;

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String rsql = "SELECT ROWNUM AS RN, R.* FROM (" + sql + ")R ORDER BY RN ASC";
		String listSql = "SELECT * FROM (" + rsql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM (" + rsql + ")";

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// 02关联团队的群销量，因为有可能在salary表中没有记录
		for (int i = 0; i < jsonarr.length(); i++) {
			String user_id = "";
			try {
				user_id = jsonarr.getJSONObject(i).getString("USER_ID");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 02在salary表中查询团队及成员的销量
			String sql02 = "SELECT GROUP_SALE FROM SALARY  WHERE USER_ID=? AND　MONTH=?";
			Object[] param02 = new Object[2];
			param02[0] = user_id;
			param02[1] = YM;
			JSONArray jsonarr02 = new JSONArray();
			jsonarr02 = BaseDao.getRsJSONArray(sql02, param02);

			String g_sale = "0";
			if (jsonarr02.length() > 0) {
				try {
					g_sale = jsonarr02.getJSONObject(0).getString("GROUP_SALE");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				jsonarr.getJSONObject(i).put("MONTH", YM);
				jsonarr.getJSONObject(i).put("GROUP_SALE", g_sale);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// paging
		Pagination page = (Pagination) request.getSession().getAttribute("page");
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		page.setPageParams(YM);

		request.setAttribute("page", page);
		request.getSession().setAttribute("page", page);

		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getVPTeamListData(HttpServletRequest request) throws NumberFormatException {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN, a.USER_ID AS TEAM_ID,a.USERNAME,a.HAPPEN_DATE,a.ROLE FROM USERS a LEFT JOIN SALES_TEAM b ON a.USER_ID=b.TEAM_ID WHERE b.TYPE=4 AND b.STATUS=1 ORDER BY RN ASC";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "SELECT COUNT(1) FROM USERS a LEFT JOIN SALES_TEAM b ON a.USER_ID=b.TEAM_ID WHERE b.TYPE=4 AND b.STATUS=1";

		JSONArray jsonarr = new JSONArray();
		jsonarr = CompanyTeamManageService.getRsJSONArray(listSql, param);
		int rows = CompanyTeamManageService.getRows(rowSql, null);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		return jsonarr;
	}

	// add by xiaopan
	public JSONArray getVPTeamDetailData(HttpServletRequest request) throws NumberFormatException {
		String TEAM_ID = request.getParameter("TEAM_ID");
		String TYPE = request.getParameter("TYPE");
		String YM = request.getParameter("YM");

		// String TEAM_ID = request.getParameter("H_V_TEAM_ID");
		// if (TEAM_ID == null) {
		// TEAM_ID = request.getParameter("H_V_TEAM_ID1");
		// }
		// if (TEAM_ID == null) {
		// TEAM_ID = request.getParameter("H_V_TEAM_ID2");
		// }

		JSONArray ret_jsonarr = new JSONArray();

		if (TYPE.equals("1") || TYPE.equals("2")) {
			// 查询SALES_MEMBER表中组长或者经理的数据：以及该组长或者经理的上级（VP）的ID
			String sql0 = "SELECT a.USER_ID,a.USERNAME,a.ROLE,b.TEAM_ID FROM USERS a LEFT JOIN  SALES_MEMBER b ON a.USER_ID=b.MEMBER_ID WHERE a.USER_ID=?";
			Object[] param0 = new Object[1];
			param0[0] = TEAM_ID;
			JSONArray jsonarr0 = new JSONArray();
			jsonarr0 = BaseDao.getRsJSONArray(sql0, param0);

			// 查询SALES_MEMBER表中组长或者经理的销量数据：
			String sql00 = "SELECT b.MONTH,b.GROUP_SALE FROM SALARY　b　WHERE b.USER_ID =? AND　b.MONTH=?";
			Object[] param00 = new Object[2];
			param00[0] = TEAM_ID;
			param00[1] = YM;
			JSONArray jsonarr00 = new JSONArray();
			jsonarr00 = BaseDao.getRsJSONArray(sql00, param00);

			String tg_sale = "0";
			if (jsonarr00.length() > 0) {
				try {
					tg_sale = jsonarr00.getJSONObject(0).getString("GROUP_SALE");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				jsonarr0.getJSONObject(0).put("MONTH", YM);
				jsonarr0.getJSONObject(0).put("GROUP_SALE", tg_sale);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 01查询SALES_MEMBER表中该组长或者经理团队中的成员信息：
			String sql01 = "SELECT a.USER_ID,a.USERNAME,a.ROLE,b.TEAM_ID FROM USERS a LEFT JOIN SALES_MEMBER b ON a.USER_ID=b.MEMBER_ID WHERE b.TEAM_ID=?  AND b.ROLE >=1 AND b.ROLE <=2";
			Object[] param01 = new Object[1];
			param01[0] = TEAM_ID;
			JSONArray jsonarr01 = new JSONArray();
			jsonarr01 = BaseDao.getRsJSONArray(sql01, param01);

			for (int i = 0; i < jsonarr01.length(); i++) {
				String user_id = "";
				try {
					user_id = jsonarr01.getJSONObject(i).getString("USER_ID");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 02在salary表中查询团队及成员的销量
				String sql02 = "SELECT PERSONAL_SALE FROM SALARY  WHERE USER_ID=? AND　MONTH=?";
				Object[] param02 = new Object[2];
				param02[0] = user_id;
				param02[1] = YM;
				JSONArray jsonarr02 = new JSONArray();
				jsonarr02 = BaseDao.getRsJSONArray(sql02, param02);

				String p_sale = "0";
				if (jsonarr02.length() > 0) {
					try {
						p_sale = jsonarr02.getJSONObject(0).getString("PERSONAL_SALE");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				try {
					jsonarr01.getJSONObject(i).put("MONTH", YM);
					jsonarr01.getJSONObject(i).put("PERSONAL_SALE", p_sale);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				jsonarr0.getJSONObject(0).put("members", jsonarr01);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 查询到的用户信息，存放在arr中。
			ret_jsonarr = jsonarr0;

		} else if (TYPE.equals("3")) {

			// 查询该VP下的所有经理团队
			String sql1 = "SELECT a.USER_ID,b.TEAM_ID,a.USERNAME,a.ROLE FROM USERS a LEFT JOIN SALES_MEMBER b ON b.MEMBER_ID=a.USER_ID　WHERE b.TEAM_ID=? AND a.ROLE >=4 AND a.ROLE <=8";
			Object[] param1 = new Object[1];
			param1[0] = TEAM_ID;
			JSONArray jsonarr = new JSONArray();
			jsonarr = BaseDao.getRsJSONArray(sql1, param1);

			// each column sales memebers detail
			try {
				for (int i = 0; i < jsonarr.length(); i++) {

					String M_TEAM_ID = jsonarr.getJSONObject(i).getString("USER_ID");

					// 查询该VP下的经理团队的销量数据
					String sql10 = "SELECT c.MONTH,c.GROUP_SALE FROM SALARY　c WHERE c.USER_ID=? AND　c.MONTH=? ";
					Object[] param10 = new Object[2];
					param10[0] = M_TEAM_ID;
					param10[1] = YM;
					JSONArray jsonarr10 = new JSONArray();
					jsonarr10 = BaseDao.getRsJSONArray(sql10, param10);

					String mg_sale = "0";
					if (jsonarr10.length() > 0) {
						mg_sale = jsonarr10.getJSONObject(0).getString("GROUP_SALE");

					}
					jsonarr.getJSONObject(i).put("MONTH", YM);
					jsonarr.getJSONObject(i).put("GROUP_SALE", mg_sale);

					// 01查询该经理团队中的成员信息：
					String sql01 = "SELECT a.USER_ID,a.USERNAME,a.ROLE,b.TEAM_ID FROM USERS a LEFT JOIN SALES_MEMBER b ON a.USER_ID=b.MEMBER_ID WHERE b.TEAM_ID=?  AND b.ROLE >=1 AND b.ROLE <=2";
					Object[] param01 = new Object[1];
					param01[0] = M_TEAM_ID;
					JSONArray jsonarr01 = new JSONArray();
					jsonarr01 = BaseDao.getRsJSONArray(sql01, param01);

					for (int j = 0; j < jsonarr01.length(); j++) {
						String user_id = "";
						try {
							user_id = jsonarr01.getJSONObject(j).getString("USER_ID");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// 02在salary表中查询团队及成员的销量
						String sql02 = "SELECT PERSONAL_SALE FROM SALARY  WHERE USER_ID=? AND　MONTH=?";
						Object[] param02 = new Object[2];
						param02[0] = user_id;
						param02[1] = YM;
						JSONArray jsonarr02 = new JSONArray();
						jsonarr02 = BaseDao.getRsJSONArray(sql02, param02);

						String p_sale = "0";
						if (jsonarr02.length() > 0) {
							try {
								p_sale = jsonarr02.getJSONObject(0).getString("PERSONAL_SALE");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						try {
							jsonarr01.getJSONObject(j).put("MONTH", YM);
							jsonarr01.getJSONObject(j).put("PERSONAL_SALE", p_sale);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					try {
						jsonarr.getJSONObject(i).put("members", jsonarr01);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ret_jsonarr = jsonarr;

		}
		return ret_jsonarr;
	}

	// add by xiaopan
	public JSONArray getVPAdjustListData(HttpServletRequest request) throws NumberFormatException {

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		String TEAM_ID = request.getParameter("TEAM_ID");

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		// 查询SALES_MEMBER表中归属于某个副总裁、经理、或者组长的人员
		String sql = "SELECT ROWNUM AS RN,T.* FROM(SELECT A.USER_ID,A.USERNAME,A.HAPPEN_DATE,A.ROLE,B.TEAM_ID FROM USERS A LEFT JOIN SALES_MEMBER B ON A.USER_ID=B.MEMBER_ID WHERE B.PARENT_ID=? AND B.ROLE >=1 AND B.ROLE <=8 ORDER BY A.ROLE DESC)T";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param1 = new Object[3];
		param1[0] = TEAM_ID;
		param1[1] = startRow;
		param1[2] = endRow;

		String rowSql = "SELECT COUNT(1) FROM USERS A LEFT JOIN SALES_MEMBER B ON A.USER_ID=B.MEMBER_ID WHERE B.PARENT_ID=? AND B.ROLE >=1 AND B.ROLE <=16";
		Object[] param2 = new Object[1];
		param2[0] = TEAM_ID;

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param1);
		int rows = BaseDao.getRowCount(rowSql, param2);

		

		// 在USERS中循环取出b.TEAM_ID对应的USERNAME（成员的上级名称）
		try {
			for (int i = 0; i < rows; i++) {
				String B_TEAM_ID = jsonarr.getJSONObject(i).getString("TEAM_ID");
				// 在USERS中循环取出b.TEAM_ID对应的USERNAME（成员的上级名称）
				String sql3 = "SELECT USER_ID AS UPPER_ID, USERNAME AS UPPER_USERNAME FROM USERS WHERE USER_ID=? ";
				Object[] param3 = new Object[1];
				param3[0] = B_TEAM_ID;

				JSONArray jsonarr1 = new JSONArray();
				jsonarr1 = BaseDao.getRsJSONArray(sql3, param3);
				String UPPER_USERNAME = jsonarr1.getJSONObject(0).getString("UPPER_USERNAME");
				String UPPER_ID = jsonarr1.getJSONObject(0).getString("UPPER_ID");
				jsonarr.getJSONObject(i).put("UPPER_USERNAME", UPPER_USERNAME);
				jsonarr.getJSONObject(i).put("UPPER_ID", UPPER_ID);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonarr;
	}

	// add by xiaopan
	public String getSalesAndManagerListData() throws NumberFormatException {
		String sql = "SELECT USER_ID,USERNAME,ROLE FROM USERS WHERE ROLE=4 OR (ROLE=2 AND FLAG=4)";
		JSONArray jsonarr = new JSONArray();
		jsonarr = CompanyTeamManageService.getRsJSONArray(sql, null);
		int rows = jsonarr.length();
		String select_json = "[";
		// each column
		try {
			for (int i = 0; i < rows; i++) {
				String user_id = jsonarr.getJSONObject(i).getString("USER_ID");
				String user_name = jsonarr.getJSONObject(i).getString("USERNAME");
				int role = jsonarr.getJSONObject(i).getInt("ROLE");
				String role_name = "";
				if (role == 4)
					role_name = "组长";
				else
					role_name = "独家销售代理";
				select_json += "[\"" + user_id + "\",\"" + role_name + " " + user_name + "\"]";
				if (i < rows - 1) {
					select_json += ",";
				}
			}
			select_json += "]";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return select_json;
	}

	// add by xiaopan
	public String getVPListDataForMove() throws NumberFormatException {
		// SELECT a.USER_ID,a.USERNAME FROM USERS a LEFT JOIN SALES_TEAM b ON
		// a.USER_ID=b.TEAM_ID WHERE b.TYPE=3 AND a.ROLE=16;
		String sql = "SELECT a.USER_ID,a.USERNAME FROM USERS a LEFT JOIN SALES_TEAM b ON a.USER_ID=b.TEAM_ID WHERE b.TYPE=3 AND a.ROLE=16";
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql, null);
		int rows = jsonarr.length();
		String select_json = "[";
		// each column
		try {
			for (int i = 0; i < rows; i++) {
				String user_id = jsonarr.getJSONObject(i).getString("USER_ID");
				String user_name = jsonarr.getJSONObject(i).getString("USERNAME");

				select_json += "[\"" + user_id + "\",\"" + "副总裁 " + user_name + "\"]";
				if (i < rows - 1) {
					select_json += ",";
				}
			}
			select_json += "]";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return select_json;
	}

	// add by xiaopan
	public String getVPListDataForAdd() throws NumberFormatException {
		String sql = "SELECT a.USER_ID,a.FIRST_NAME,a.LAST_NAME,a.ROLE,a.USERNAME FROM USERS a WHERE a.USER_ID NOT IN(SELECT TEAM_ID FROM SALES_TEAM WHERE TYPE=4) AND a.ROLE>=32 AND a.ROLE<=128";
		JSONArray jsonarr = new JSONArray();
		jsonarr = CompanyTeamManageService.getRsJSONArray(sql, null);
		int rows = jsonarr.length();
		String select_json = "[";
		// each column
		try {
			for (int i = 0; i < rows; i++) {
				String user_id = jsonarr.getJSONObject(i).getString("USER_ID");
				String user_name = jsonarr.getJSONObject(i).getString("USERNAME");
				select_json += "[\"" + user_id + "\",\"" + "副总裁 " + user_name + "\"]";
				if (i < rows - 1) {
					select_json += ",";
				}
			}
			select_json += "]";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return select_json;
	}

	// add by xiaopan
	public String getNoManagerTeamSalesListData() throws NumberFormatException {
		String sql = "SELECT a.USER_ID,a.FIRST_NAME,a.LAST_NAME,a.ROLE,a.USERNAME FROM USERS a WHERE a.USER_ID NOT IN(SELECT TEAM_ID FROM SALES_TEAM WHERE TYPE=4) AND a.ROLE>=32 AND a.ROLE<=128";
		JSONArray jsonarr = new JSONArray();
		jsonarr = CompanyTeamManageService.getRsJSONArray(sql, null);
		int rows = jsonarr.length();
		String select_json = "[";
		// each column
		try {
			for (int i = 0; i < rows; i++) {
				String user_id = jsonarr.getJSONObject(i).getString("USER_ID");
				String user_name = jsonarr.getJSONObject(i).getString("USERNAME");
				select_json += "[\"" + user_id + "\",\"" + "副总裁 " + user_name + "\"]";
				if (i < rows - 1) {
					select_json += ",";
				}
			}
			select_json += "]";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return select_json;
	}

	// add by xiaopan
	public String getManagerListDataForAdd() throws NumberFormatException {
		String sql = "SELECT a.USER_ID,a.FIRST_NAME,a.LAST_NAME,a.ROLE,a.USERNAME FROM USERS a WHERE a.USER_ID NOT IN(SELECT TEAM_ID FROM SALES_TEAM WHERE TYPE=2) AND a.ROLE>=4 AND a.ROLE<=16";
		JSONArray jsonarr = new JSONArray();
		jsonarr = CompanyTeamManageService.getRsJSONArray(sql, null);
		int rows = jsonarr.length();
		String select_json = "[";
		// each column
		try {
			for (int i = 0; i < rows; i++) {
				String user_id = jsonarr.getJSONObject(i).getString("USER_ID");
				String user_name = jsonarr.getJSONObject(i).getString("USERNAME");
				// String role = jsonarr.getJSONObject(i).getString("ROLE");
				select_json += "[\"" + user_id /* + "_" + role */ + "\",\"" + "经理 " + user_name + "\"]";
				if (i < rows - 1) {
					select_json += ",";
				}
			}
			select_json += "]";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return select_json;
	}

	// add by xiaopan
	public String getManagerListDataForMove() throws NumberFormatException {
		// SELECT a.USER_ID,a.USERNAME FROM USERS a LEFT JOIN SALES_TEAM b ON
		// a.USER_ID=b.TEAM_ID WHERE b.TYPE>=1 AND b.TYPE<=2 AND a.ROLE>=4 AND
		// a.ROLE<=8;
		// 打印var rows5 =
		// [["100037","组长A"],["100038","组长B"],["100039","经理C"],["100040","经理D"]];

		String sql = "SELECT a.USER_ID,a.USERNAME,a.ROLE FROM USERS a LEFT JOIN SALES_TEAM b ON a.USER_ID=b.TEAM_ID WHERE b.TYPE>=1 AND b.TYPE<=2 AND a.ROLE>=4 AND a.ROLE<=8";
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql, null);
		int rows = jsonarr.length();
		String select_json = "[";
		// each column
		try {
			for (int i = 0; i < rows; i++) {
				String user_id = jsonarr.getJSONObject(i).getString("USER_ID");
				String user_name = jsonarr.getJSONObject(i).getString("USERNAME");
				int role = jsonarr.getJSONObject(i).getInt("ROLE");
				String user_role = "";
				if (role == 4) {
					user_role = " 组长 ";
				} else {
					user_role = " 经理 ";
				}
				select_json += "[\"" + user_id /* + "_" + role */ + "\",\"" + user_role + user_name + "\"]";
				if (i < rows - 1) {
					select_json += ",";
				}
			}
			select_json += "]";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return select_json;
	}

	public void doTrain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// （3）独家代理开始培训：do_train.jsp?TAG=1&TRAIN_ID=TRAIN_ID&TEAM_ID=TEAM_ID&ROLE=ROLE
		// //如果TAG=1，在培训成员表(TRAIN_MEMBER)中添加一条记录
		// INSERT INTO
		// TRAIN_MEMBER(TRAIN_ID,MEMBER_ID,ROLE,FLAG,CREATE_ID,HAPPEN_DATE,HAPPEN_TIME)
		// VALUES(?,?,?,?,?,?,?);
		// //TRAIN_ID=TRAIN_ID;MEMBER_ID=TEAM_ID;ROLE=ROLE;FLAG=0;

		String TAG = request.getParameter("TAG");
		String TRAIN_ID = request.getParameter("TRAIN_ID");
		String TEAM_ID = request.getParameter("TEAM_ID");
		String ROLE = request.getParameter("ROLE");

		Object CREATE_ID = LoginControlPublicServlet.getSessionKeyValue(request, "USER_ID");

		String date = DateUtil.getDateString();
		String time = DateUtil.getTimeString();

		String msg = "";
		String normal_json = "";
		JSONArray jsonarr1 = new JSONArray();

		if (TAG.equals("1")) {

			String sql = "INSERT INTO TRAIN_MEMBER(TRAIN_ID,MEMBER_ID,ROLE,FLAG,CREATE_ID,HAPPEN_DATE,HAPPEN_TIME) VALUES(?,?,?,?,?,?,?)";
			Object[] param = new Object[7];
			param[0] = TRAIN_ID;
			param[1] = TEAM_ID;
			param[2] = ROLE;
			param[3] = 0;
			param[4] = CREATE_ID;
			param[5] = date;
			param[6] = time;

			int flag = BaseDao.exeSql(sql, param);

			if (flag > 0) {
				msg = "成功！";
				jsonarr1 = getTrainTeamListData(request);
				normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);

			} else {
				msg = "失败！";
				normal_json = JsonService.getErrorMsgNormalJson(msg);
			}
		} else if (TAG.equals("2")) {

			// 在完成培训之前，要检测是否开始培训了
			String sql0 = "";

			// 如果TAG=2，修改培训成员表(TRAIN_MEMBER)中记录，修改培训团队的状态为关闭
			// UPDATE TRAIN_MEMBER SET FLAG=1,END_DATE=?,END_TIME=? WHERE
			// TRAIN_ID=TRAIN_ID;
			// END_DATE,END_TIME为当前的时间

			// UPDATE TRAIN_TEAM SET STATUS=2 WHERE TRAIN_ID=TRAIN_ID;

			// 修改表USERS字段FLAG：
			// UPDATE USERS SET FLAG=0 WHERE USER_ID=TEAM_ID;
			String sql1 = "UPDATE TRAIN_MEMBER SET FLAG=1,END_DATE=?,END_TIME=? WHERE TRAIN_ID=?";
			Object[] param1 = new Object[3];
			param1[0] = date;
			param1[1] = time;
			param1[2] = TRAIN_ID;
			int flag1 = BaseDao.exeSql(sql1, param1);

			String sql2 = "UPDATE TRAIN_TEAM SET STATUS=2 WHERE TRAIN_ID=?";
			Object[] param2 = new Object[1];
			param2[0] = TRAIN_ID;
			int flag2 = BaseDao.exeSql(sql2, param2);

			String sql3 = "UPDATE USERS SET FLAG=0,GROLE=4 WHERE USER_ID=?";
			Object[] param3 = new Object[1];
			param3[0] = TEAM_ID;
			int flag3 = BaseDao.exeSql(sql3, param3);

			// USER_ID=TEAM_ID,ROLE=2,NEW_ROLE=4

			String sql4 = "INSERT INTO GRADE_RECORD(USER_ID,ROLE,NEW_ROLE,REMARK,CREATE_ID,HAPPEN_DATE,HAPPEN_TIME) VALUES(?,?,?,?,?,?,?)";
			Object[] param4 = new Object[7];
			param4[0] = TEAM_ID;
			param4[1] = 2;
			param4[2] = 4;
			param4[3] = "完成独家代理培训";
			param4[4] = CREATE_ID;
			param4[5] = date;
			param4[6] = time;
			int flag4 = BaseDao.exeSql(sql4, param4);

			if (flag1 > 0 && flag2 > 0 && flag3 > 0 && flag4 > 0) {
				msg = "成功！";
				jsonarr1 = getTrainTeamListData(request);
				normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);

			} else {
				msg = "失败！";
				normal_json = JsonService.getErrorMsgNormalJson(msg);
			}
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void closeVPTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String STATUS = request.getParameter("STATUS");
		String TEAM_ID = request.getParameter("TEAM_ID");

		String msg = "";
		String normal_json = "";
		JSONArray jsonarr1 = new JSONArray();

		String sql = "UPDATE SALES_TEAM SET STATUS=? WHERE TEAM_ID=?";
		Object[] param = new Object[2];
		param[0] = STATUS;
		param[1] = TEAM_ID;

		int flag = BaseDao.exeSql(sql, param);

		if (flag > 0) {
			msg = "关闭成功！";
			//jsonarr1 = getVPTeamListData(request);
			normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

		} else {
			msg = "关闭失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void closeManagerTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String TEAM_ID = request.getParameter("TEAM_ID");

		String msg = "";
		String normal_json = "";
		JSONArray jsonarr1 = new JSONArray();

		String sql = "UPDATE SALES_TEAM SET STATUS=? WHERE TEAM_ID=?";
		Object[] param = new Object[2];
		param[0] = 2;
		param[1] = TEAM_ID;

		int flag = BaseDao.exeSql(sql, param);

		if (flag > 0) {
			msg = "关闭成功！";
			//jsonarr1 = getVPTeamDetailData(request);
			normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

		} else {
			msg = "关闭失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void deleteTrainTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// String STATUS = request.getParameter("STATUS");
		String TRAIN_ID = request.getParameter("TRAIN_ID");

		String msg = "";
		String normal_json = "";

		// 查询TRAIN_MEMBER表中该团队中是否还有成员正在培训，FLAG==0。
		String sql0 = "SELECT COUNT(1) FROM TRAIN_MEMBER WHERE TRAIN_ID=? AND FLAG=0";
		Object[] param0 = new Object[1];
		param0[0] = TRAIN_ID;
		int rows = BaseDao.getRowCount(sql0, param0);
		JSONArray jsonarr1 = new JSONArray();
		if (rows > 0) {
			msg = "该团队还有成员，不能关闭！";
			jsonarr1 = getTrainTeamListData(request);
			normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);
		} else {
			String sql = "UPDATE TRAIN_TEAM SET STATUS=2 WHERE TRAIN_ID=?";
			Object[] param = new Object[1];
			param[0] = TRAIN_ID;

			int flag = BaseDao.exeSql(sql, param);
			if (flag > 0) {
				msg = "删除成功！";
				jsonarr1 = getTrainTeamListData(request);
				normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);

			} else {
				msg = "删除失败！";
				normal_json = JsonService.getErrorMsgNormalJson(msg);
			}
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	// TODO:need to check
	public void moveManagerTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String M_USER_ID = request.getParameter("M_USER_ID");
		String VP_PARENT_ID = request.getParameter("VP_PARENT_ID");
		String VP_TEAM_ID = request.getParameter("N_VP_TEAM_ID");

		String msg = "";
		String normal_json = "";
		JSONArray jsonarr1 = new JSONArray();

		// 1)
		// 修改SALES_MEMBER表中的PARENT_ID：（处理自己培养的销售员升级为经理：将上级的ID替换被培养人的PARENT_ID）
		String sql = "UPDATE SALES_MEMBER SET PARENT_ID=? WHERE PARENT_ID=? AND ROLE>=4 AND ROLE<=8";
		Object[] param = new Object[2];
		param[0] = VP_PARENT_ID;
		param[1] = M_USER_ID;
		int flag = BaseDao.exeSql(sql, param);

		// 2) 修改自己在SALES_MEMBER表中的记录：(成为新副总裁团队的一员)
		String sql1 = "UPDATE SALES_MEMBER SET TEAM_ID=?, PARENT_ID=? WHERE TEAM_ID=? AND  MEMBER_ID=?";
		Object[] param1 = new Object[4];
		param1[0] = VP_TEAM_ID;
		param1[1] = VP_TEAM_ID;
		param1[2] = VP_PARENT_ID;
		param1[3] = M_USER_ID;
		int flag1 = BaseDao.exeSql(sql1, param1);

		if (flag >= 0 && flag1 > 0) {
			msg = "迁出成功！";
			//jsonarr1 = getVPTeamDetailData(request);
			normal_json = JsonService.getOperateResultNormalJson("true",null, msg);

		} else {
			msg = "迁出失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	// TODO:need to check
	public void moveManagerTeamIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String M_USER_ID = request.getParameter("M_USER_ID");
		String VP_PARENT_ID = request.getParameter("VP_PARENT_ID");
		String VP_TEAM_ID = request.getParameter("VP_TEAM_ID");

		String msg = "";
		String normal_json = "";
		JSONArray jsonarr1 = new JSONArray();

		// 1)
		// 修改SALES_MEMBER表中的PARENT_ID：（处理自己培养的销售员升级为经理：将上级的ID替换被培养人的PARENT_ID）
		String sql = "UPDATE SALES_MEMBER SET PARENT_ID=? WHERE PARENT_ID=? AND ROLE>=4 AND ROLE<=16";
		Object[] param = new Object[2];
		param[0] = VP_PARENT_ID;
		param[1] = M_USER_ID;
		int flag = BaseDao.exeSql(sql, param);

		// 2) 修改自己在SALES_MEMBER表中的记录：(成为新副总裁团队的一员)
		String sql1 = "UPDATE SALES_MEMBER SET TEAM_ID=?, PARENT_ID=? WHERE TEAM_ID=? AND  MEMBER_ID=?";
		Object[] param1 = new Object[4];
		param1[0] = VP_TEAM_ID;
		param1[1] = VP_TEAM_ID;
		param1[2] = VP_PARENT_ID;
		param1[3] = M_USER_ID;
		int flag1 = BaseDao.exeSql(sql1, param1);

		if (flag >= 0 && flag1 > 0) {
			msg = "迁入成功！";
			//jsonarr1 = getVPAdjustListData(request);
			normal_json = JsonService.getOperateResultNormalJson("true",null, msg);

		} else {
			msg = "迁入失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void deleteManagerTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String USER_ID = request.getParameter("USER_ID");// 自己的ID
		String VP_PARENT_ID = request.getParameter("TEAM_ID");// 原副总裁的ID

		String msg = "";
		String normal_json = "";
		JSONArray jsonarr1 = new JSONArray();

		// 查询SALES_MEMBER表中该经理团队团队中是否还有成员。
		String sql9 = "SELECT COUNT(1) FROM SALES_MEMBER WHERE TEAM_ID=? AND ROLE>=1 AND ROLE<=2";
		Object[] param9 = new Object[1];
		param9[0] = USER_ID;
		int rows1 = BaseDao.getRowCount(sql9, param9);

		if (rows1 == 0) {

			// 修改SALES_MEMBER表中的PARENT_ID：（处理自己培养的销售员升级为经理：将上级的ID替换被培养人的PARENT_ID）
			String sql1 = "UPDATE SALES_MEMBER SET PARENT_ID=? WHERE PARENT_ID=? AND ROLE>=4 AND ROLE<=8";
			Object[] param1 = new Object[2];
			param1[0] = VP_PARENT_ID;
			param1[1] = USER_ID;
			int flag1 = BaseDao.exeSql(sql1, param1);

			// 删除表SALES_TEAM中该经理团队信息（销售经理和培训经理）
			String sql0 = "DELETE FROM SALES_TEAM WHERE TEAM_ID=?";
			Object[] param0 = new Object[1];
			param0[0] = USER_ID;
			int flag0 = BaseDao.exeSql(sql0, param0);

			// 删除副总裁团队成员表SALES_MEMBER的成员信息(该经理所在的副总裁团队)
			String sql2 = "DELETE FROM SALES_MEMBER WHERE TEAM_ID=? AND MEMBER_ID=?";
			Object[] param2 = new Object[2];
			param2[0] = VP_PARENT_ID;
			param2[1] = USER_ID;
			int flag2 = BaseDao.exeSql(sql2, param2);

			// 删除用户表USERS的成员信息
			String sql3 = "DELETE FROM USERS WHERE USER_ID=?";
			Object[] param3 = new Object[1];
			param3[0] = USER_ID;
			int flag3 = BaseDao.exeSql(sql3, param3);

			if (flag0 >= 0 && flag1 >= 0 && flag2 >= 0 && flag3 > 0) {
				msg = "删除成功！";
				//jsonarr1 = getVPTeamDetailData(request);
				normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

			} else {
				msg = "删除失败！";
				normal_json = JsonService.getErrorMsgNormalJson(msg);
			}
		} else {
			msg = "该团队还有成员，不能删除！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void moveSalesMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String S_USER_ID = request.getParameter("S_USER_ID");
		String S_PARENT_ID = request.getParameter("S_PARENT_ID");
		String S_TEAM_ID = request.getParameter("S_TEAM_ID");

		String msg = "";
		String normal_json = "";
		JSONArray jsonarr1 = new JSONArray();

		// 修改SALES_MEMBER表中的PARENT_ID：（以前培训的成员保留在原经理团队：已经培训的销售员PARENT_ID换成原来的经理团队的ID；不含正在培训的成员：ROLE>=1
		// ，ROLE<=16）
		String sql0 = "UPDATE SALES_MEMBER SET PARENT_ID=? WHERE TEAM_ID=? AND  PARENT_ID=? AND ROLE>=1 AND ROLE<=16";
		Object[] param0 = new Object[3];
		param0[0] = S_PARENT_ID;
		param0[1] = S_PARENT_ID;
		param0[2] = S_USER_ID;
		int flag0 = BaseDao.exeSql(sql0, param0);

		// 修改自己在SALES_MEMBER表中的记录：(成为新经理团队的一员)
		String sql1 = "UPDATE SALES_MEMBER SET TEAM_ID=?, PARENT_ID=? WHERE TEAM_ID=? AND  MEMBER_ID=?";
		Object[] param1 = new Object[4];
		param1[0] = S_TEAM_ID;
		param1[1] = S_TEAM_ID;
		param1[2] = S_PARENT_ID;
		param1[3] = S_USER_ID;
		int flag1 = BaseDao.exeSql(sql1, param1);

		if (flag0 >= 0 && flag1 > 0) {
			msg = "调岗成功！";
			// jsonarr1 = getVPTeamDetailData(request);
			normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

		} else {
			msg = "调岗失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void salesMemberDimission(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String USER_ID = request.getParameter("USER_ID");
		String TEAM_ID = request.getParameter("TEAM_ID");

		String msg = "";
		String normal_json = "";
		JSONArray jsonarr1 = new JSONArray();

		// 1）修改USERS中用户的角色ROLE = 10000
		String sql0 = "UPDATE USERS SET ROLE = 10000 WHERE USER_ID=?";
		Object[] param0 = new Object[1];
		param0[0] = USER_ID;
		int flag0 = BaseDao.exeSql(sql0, param0);

		// 2）修改SALES_MEMBER中用户的角色ROLE = 10000
		String sql1 = "UPDATE SALES_MEMBER SET ROLE = 10000 WHERE TEAM_ID=? AND MEMBER_ID=? ";
		Object[] param1 = new Object[2];
		param1[0] = TEAM_ID;
		param1[1] = USER_ID;
		int flag1 = BaseDao.exeSql(sql1, param1);

		// 修改SALES_MEMBER中的PARENT_ID（将销售员培训的成员划归给其经理）
		String sql2 = "UPDATE SALES_MEMBER SET PARENT_ID=? WHERE PARENT_ID=?";
		Object[] param2 = new Object[2];
		param2[0] = TEAM_ID;
		param2[1] = USER_ID;
		int flag2 = BaseDao.exeSql(sql2, param2);

		if (flag0 > 0 && flag1 > 0 && flag2 >= 0) {
			msg = "离职成功！";
			//jsonarr1 = getVPTeamDetailData(request);
			normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

		} else {
			msg = "离职失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void deleteSalesMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String USER_ID = request.getParameter("USER_ID");
		String TEAM_ID = request.getParameter("TEAM_ID");

		String msg = "";
		String normal_json = "";
		JSONArray jsonarr1 = new JSONArray();

		// 2）修改SALES_MEMBER中的PARENT_ID
		String sql1 = "UPDATE SALES_MEMBER SET PARENT_ID=? WHERE PARENT_ID=?";
		Object[] param1 = new Object[2];
		param1[0] = TEAM_ID;
		param1[1] = USER_ID;
		int flag1 = BaseDao.exeSql(sql1, param1);

		// 3）删除团队成员表SALES_MEMBER的成员信息(该销售员所在的团队)
		String sql2 = "DELETE FROM SALES_MEMBER WHERE TEAM_ID=? AND MEMBER_ID=?";
		Object[] param2 = new Object[2];
		param2[0] = TEAM_ID;
		param2[1] = USER_ID;
		int flag2 = BaseDao.exeSql(sql2, param2);

		// 4）删除用户表USERS的成员信息
		String sql3 = "DELETE FROM USERS WHERE USER_ID=?";
		Object[] param3 = new Object[1];
		param3[0] = USER_ID;
		int flag3 = BaseDao.exeSql(sql3, param3);

		if (flag1 >= 0 && flag2 > 0 && flag3 > 0) {
			msg = "删除成功！";
			//jsonarr1 = getVPTeamDetailData(request);
			normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

		} else {
			msg = "删除失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void deleteVPTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String STATUS = request.getParameter("STATUS");
		String TEAM_ID = request.getParameter("TEAM_ID");

		String msg = "";
		String normal_json = "";

		// 删除之前判断该团队是否有成员，若有，不让删除
		String sql0 = "SELECT COUNT(1) FROM SALES_MEMBER WHERE TEAM_ID=?";
		Object[] param0 = new Object[1];
		param0[0] = TEAM_ID;
		int rows = BaseDao.getRowCount(sql0, param0);
		if (rows > 0) {
			msg = "该团队还有成员，不能删除！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		} else {
			JSONArray jsonarr1 = new JSONArray();
			String sql = "UPDATE SALES_TEAM SET STATUS=? WHERE TEAM_ID=?";
			Object[] param = new Object[2];
			param[0] = STATUS;
			param[1] = TEAM_ID;

			int flag = BaseDao.exeSql(sql, param);

			if (flag > 0) {
				msg = "删除成功！";
				//jsonarr1 = getVPTeamListData(request);
				normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

			} else {
				msg = "删除失败！";
				normal_json = JsonService.getErrorMsgNormalJson(msg);
			}
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void addVPTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Object user_id = LoginControlPublicServlet.getSessionKeyValue(request, "USER_ID");

		String TYPE = request.getParameter("TYPE");
		String TEAM_ID = request.getParameter("V_TEAM_ID");

		String msg = "";
		String normal_json = "";

		String sql1 = "SELECT COUNT(1) FROM　SALES_TEAM WHERE TYPE=? AND TEAM_ID=?";
		Object[] param1 = new Object[2];
		param1[0] = TYPE;
		param1[1] = TEAM_ID;

		int flag1 = BaseDao.getRowCount(sql1, param1);
		if (flag1 > 0) {
			msg = "该团队已存在，请勿重复添加！";
			normal_json = JsonService.getOperateResultNormalJson("true", null, msg);
		} else {

			// TEAM_ID = TEAM_ID; TYPE = TYPE ; STATUS = 1; SEQ = 0;

			JSONArray jsonarr1 = new JSONArray();

			String sql2 = "INSERT INTO　SALES_TEAM(TEAM_ID,TYPE,STATUS,SEQ,CREATE_ID) VALUES(?,?,?,?,?)";
			Object[] param2 = new Object[5];
			param2[0] = TEAM_ID;
			param2[1] = TYPE;
			param2[2] = 1;
			param2[3] = 0;
			param2[4] = user_id;

			int flag2 = BaseDao.exeSql(sql2, param2);
			if (flag2 > 0) {
				//jsonarr1 = getVPTeamListData(request);
				msg = "添加成功！";
				normal_json = JsonService.getOperateResultNormalJson("true",null, msg);

			} else {
				msg = "添加失败！";
				normal_json = JsonService.getErrorMsgNormalJson(msg);
			}
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void addManagerTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Users oper = (Users) request.getSession().getAttribute("users");
		//
		// long user_id = oper.getUserId();

		Object user_id = LoginControlPublicServlet.getSessionKeyValue(request, "USER_ID");

		String TYPE = request.getParameter("M_TYPE");
		String VP_TEAM_ID = request.getParameter("H_V_TEAM_ID");
		String M_TEAM_ID = request.getParameter("M_TEAM_ID");

		String msg = "";
		String normal_json = "";

		String sql0 = "SELECT COUNT(1) FROM　SALES_TEAM WHERE TYPE=? AND TEAM_ID=?";
		Object[] param0 = new Object[2];
		param0[0] = TYPE;
		param0[1] = M_TEAM_ID;

		int flag0 = BaseDao.getRowCount(sql0, param0);
		if (flag0 > 0) {
			msg = "该团队已存在，请勿重复添加！";
			normal_json = JsonService.getOperateResultNormalJson("true", null, msg);
		} else {

			// TEAM_ID = TEAM_ID; TYPE = TYPE ; STATUS = 1; SEQ = 0;
			JSONArray jsonarr1 = new JSONArray();

			// new team
			String sql1 = "INSERT INTO　SALES_TEAM(TEAM_ID,TYPE,STATUS,SEQ,CREATE_ID) VALUES(?,?,?,?,?)";
			Object[] param1 = new Object[5];
			param1[0] = M_TEAM_ID;
			param1[1] = TYPE;
			param1[2] = 1;
			param1[3] = 0;
			param1[4] = user_id;
			int flag1 = BaseDao.exeSql(sql1, param1);

			// new team member
			String sql3 = "SELECT ROLE FROM USERS WHERE USER_ID=?";
			Object[] param3 = new Object[1];
			param3[0] = M_TEAM_ID;

			JSONArray rolearr = new JSONArray();
			rolearr = CompanyTeamManageService.getRsJSONArray(sql3, param3);
			String role = "";
			try {
				role = rolearr.getJSONObject(0).getString("ROLE");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String sql2 = "INSERT INTO　SALES_MEMBER(TEAM_ID,MEMBER_ID,ROLE,PARENT_ID) VALUES(?,?,?,?)";
			Object[] param2 = new Object[4];
			param2[0] = VP_TEAM_ID;
			param2[1] = M_TEAM_ID;
			param2[2] = role;
			param2[3] = VP_TEAM_ID;

			int flag2 = BaseDao.exeSql(sql2, param2);
			if (flag1 > 0 && flag2 > 0) {
				//jsonarr1 = getVPTeamDetailData(request);
				msg = "添加成功！";
				normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

			} else {
				msg = "添加失败！";
				normal_json = JsonService.getErrorMsgNormalJson(msg);
			}
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void addManagerTeamSales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ROLE = request.getParameter("ROLE5");
		String TEAM_ID = request.getParameter("TEAM_ID5");
		String USER_ID = request.getParameter("USER_ID5");

		String msg = "";
		String normal_json = "";

		String sql0 = "INSERT INTO SALES_MEMBER(TEAM_ID,MEMBER_ID,ROLE,PARENT_ID) VALUES(?,?,?,?)";
		Object[] param0 = new Object[4];
		param0[0] = TEAM_ID;
		param0[1] = USER_ID;
		param0[2] = ROLE;
		param0[3] = TEAM_ID;

		int flag0 = BaseDao.exeSql(sql0, param0);

		if (flag0 > 0) {
			// JSONArray jsonarr1 = new JSONArray();
			// jsonarr1 = getVPTeamDetailData(request);
			msg = "添加成功！";
			normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

		} else {
			msg = "添加失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void addSalesTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("OP");
		Object user_id = LoginControlPublicServlet.getSessionKeyValue(request, "USER_ID");

		String TYPE = request.getParameter("TYPE");
		String TEAM_ID = request.getParameter("TEAM_ID");
		String P_TEAM_ID = request.getParameter("P_TEAM_ID");

		String msg = "";
		String normal_json = "";
		JSONArray jsonarr1 = new JSONArray();
		if (op.equals("1")) {// 添加
			String sql1 = "SELECT COUNT(1) FROM　SALES_TEAM WHERE TYPE=? AND TEAM_ID=?";
			Object[] param1 = new Object[2];
			param1[0] = TYPE;
			param1[1] = TEAM_ID;

			int flag1 = BaseDao.getRowCount(sql1, param1);
			if (flag1 > 0) {
				msg = "该培训团队已存在，请勿重复添加！";
				normal_json = JsonService.getOperateResultNormalJson("true", null, msg);
			} else {
				String sql2 = "INSERT INTO　SALES_TEAM(TEAM_ID,TYPE,STATUS,SEQ,CREATE_ID) VALUES(?,?,?,?,?)";
				Object[] param2 = new Object[5];
				param2[0] = TEAM_ID;
				param2[1] = TYPE;
				param2[2] = 1;
				param2[3] = 0;
				param2[4] = user_id;

				int flag2 = BaseDao.exeSql(sql2, param2);
				if (flag2 > 0) {
					//jsonarr1 = getTrainTeamListData(request);
					msg = "添加成功！";
					normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

				} else {
					msg = "添加失败！";
					normal_json = JsonService.getOperateResultNormalJson("false", null, msg);
				}
			}
		} else if (op.equals("2")) {
			String sql3 = "UPDATE SALES_TEAM SET TEAM_ID=?,TYPE=? WHERE TEAM_ID=?";
			Object[] param3 = new Object[3];
			param3[0] = TEAM_ID;
			param3[1] = TYPE;
			param3[2] = P_TEAM_ID;

			// 此处应同步更新SALES_MEMBER表的TEAM_ID,表示团队换了领导
			String sql4 = "UPDATE SALES_MEMBER SET TEAM_ID=? WHERE TEAM_ID=?";
			Object[] param4 = new Object[2];
			param4[0] = TEAM_ID;
			param4[1] = P_TEAM_ID;

			int flag3 = BaseDao.exeSql(sql3, param3);
			int flag4 = BaseDao.exeSql(sql4, param4);
			if (flag3 > 0 && flag4 > 0) {
				msg = "更新成功！";
				//jsonarr1 = getTrainTeamListData(request);
				normal_json = JsonService.getOperateResultNormalJson("true", null, msg);

			} else {
				msg = "更新失败！";
				normal_json = JsonService.getOperateResultNormalJson("false", null, msg);
			}
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

	public void addTrainTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("OP");

		Object CREATE_ID = LoginControlPublicServlet.getSessionKeyValue(request, "USER_ID");

		String date = DateUtil.getDateString();
		String time = DateUtil.getTimeString();

		String TYPE = request.getParameter("TYPE");
		String TEAM_ID = request.getParameter("TEAM_ID");
		String TRAIN_ID = request.getParameter("TRAIN_ID");
		String COUNT = request.getParameter("COUNT");

		String msg = "";
		String normal_json = "";
		JSONArray jsonarr1 = new JSONArray();
		if (op.equals("1")) {// 添加
			String sql1 = "SELECT COUNT(1) FROM　TRAIN_TEAM WHERE TEAM_ID=? and TYPE=? and STATUS=1";
			Object[] param1 = new Object[2];
			param1[0] = TEAM_ID;
			param1[1] = TYPE;

			int flag1 = BaseDao.getRowCount(sql1, param1);
			if (flag1 > 0) {
				msg = "该培训团队已存在，请勿重复添加！";
				normal_json = JsonService.getOperateResultNormalJson("true", null, msg);
			} else {
				String sql2 = "INSERT INTO TRAIN_TEAM(TEAM_ID,TYPE,STATUS,COUNT,CREATE_ID) VALUES(?,?,?,?,?)";
				Object[] param2 = new Object[5];
				param2[0] = TEAM_ID;
				param2[1] = TYPE;
				param2[2] = 1;
				param2[3] = COUNT;
				param2[4] = CREATE_ID;
				// param2[5] = date;
				// param2[6] = time;

				int flag2 = BaseDao.exeSql(sql2, param2);
				if (flag2 > 0) {
					jsonarr1 = getTrainTeamListData(request);
					msg = "添加成功！";
					normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);

				} else {
					msg = "添加失败！";
					normal_json = JsonService.getOperateResultNormalJson("false", null, msg);
				}
			}
		} else if (op.equals("2")) {
			// UPDATE TRAIN_TEAM SET TEAM_ID=?,TYPE=?,COUNT=? WHERE
			// TRAIN_ID=TRAIN_ID;
			// TEAM_ID = TEAM_ID; TYPE = TYPE ;COUNT=COUNT;TRAIN_ID=TRAIN_ID;

			String sql3 = "UPDATE TRAIN_TEAM SET TEAM_ID=?,TYPE=?,COUNT=? WHERE TRAIN_ID=?";
			Object[] param3 = new Object[4];
			param3[0] = TEAM_ID;
			param3[1] = TYPE;
			param3[2] = COUNT;
			param3[3] = TRAIN_ID;

			// // 此处应同步更新SALES_MEMBER表的TEAM_ID,表示团队换了领导
			// String sql4 = "UPDATE TRAIN_MEMBER SET TEAM_ID=? WHERE
			// TEAM_ID=?";
			// Object[] param4 = new Object[2];
			// param4[0] = TEAM_ID;
			// param4[1] = P_TEAM_ID;

			int flag3 = BaseDao.exeSql(sql3, param3);
			// int flag4 = BaseDao.exeSql(sql4, param4);
			if (flag3 > 0) {
				msg = "更新成功！";
				jsonarr1 = getTrainTeamListData(request);
				normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);

			} else {
				msg = "更新失败！";
				normal_json = JsonService.getOperateResultNormalJson("false", null, msg);
			}
		}

		JsonService.ResponseJson(request, response, normal_json);

	}

}
