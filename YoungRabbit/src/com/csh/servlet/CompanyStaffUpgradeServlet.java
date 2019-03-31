package com.csh.servlet;

/*********************
 * Author shaohui-chen
 ******************** */
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.service.CompanyGradeService;
import com.csh.service.CompanyProductService;
import com.csh.service.CompanyStaffService;
import com.csh.service.CompanyTeamManageService;
import com.csh.service.JsonService;
import com.csh.service.WebUsersService;
import com.csh.util.PageModel;
import com.csh.util.Pagination;

public class CompanyStaffUpgradeServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		// String op = request.getParameter("OP");

		switch (method) {
		case "staff_list":
			getStaffList(request, response);
			break;	
		case "staff_upgrade":// staff_upgrade
			upgradeStaff(request, response);
			break;
		}

	}

	// add by lizhenyu
	private void upgradeStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("test");
		String ROLE = request.getParameter("ROLE");
		//int TYPE = Integer.parseInt(request.getParameter("TYPE"));
		String NEW_ROLE = request.getParameter("NEW_ROLE");
		String USER_ID = request.getParameter("USER_ID");
		String REMARK = request.getParameter("REMARK");
		Object CREATE_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取				   		
		
		String msg = "";
		String normal_json = "";
		String sql = "INSERT INTO GRADE_RECORD(USER_ID,ROLE,NEW_ROLE,REMARK,CREATE_ID) VALUES(?,?,?,?,?)";
		Object[] param = new Object[5];
		param[0] = USER_ID;
		param[1] = ROLE;
		param[2] = NEW_ROLE;
		param[3] = REMARK;
		param[4] = CREATE_ID;
		
		int result = BaseDao.exeSql(sql, param);
		if (result == 1) {
			
			String sql1="UPDATE USERS SET GROLE=? WHERE USER_ID=?";
			Object[] param1 = new Object[2];
			param1[0] = NEW_ROLE;
			param1[1] = USER_ID;
			
			int result1 = BaseDao.exeSql(sql1, param1);
			if(result1 == 1){
				// 员工评级完成后，取出数据，刷新列表
				JSONArray jsonarr = new JSONArray();
				jsonarr = getStaffListData(request);			
				msg = "员工评级成功！";	
				normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} 
		    else 
		    	normal_json = JsonService.getErrorMsgNormalJson( "员工评级失败！");		
		}
		
		else 
			normal_json = JsonService.getErrorMsgNormalJson( "员工评级失败！");		
		
		JsonService.ResponseJson(request, response, normal_json);
	}
		
		/*int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0, flag6 = 0;

		String sql = "UPDATE USERS SET ROLE=? WHERE USER_ID=?";
		Object[] param = new Object[2];
		param[0] = NEW_ROLE;
		param[1] = USER_ID;
		flag1 = BaseDao.exeSql(sql, param);

		String sql2 = "UPDATE SALES_MEMBER SET ROLE=? WHERE MEMBER_ID=?";
		flag2 = BaseDao.exeSql(sql2, param);

		String sql3 = "INSERT INTO GRADE_RECORD(USER_ID,ROLE,NEW_ROLE,REMARK,CREATE_ID) VALUES(?,?,?,?,?)";
		Object[] param1 = new Object[5];
		param1[0] = USER_ID;
		param1[1] = ROLE;
		param1[2] = NEW_ROLE;
		param1[3] = REMARK;
		param1[4] = CREATE_ID;
		flag3 = BaseDao.exeSql(sql3, param1);

		if (TYPE == 2 || TYPE == 4) {
			String sql4 = "INSERT INTO SALES_TEAM(TEAM_ID,TYPE,STATUS,SEQ,CREATE_ID) VALUES(?,?,?,?,?)";
			Object[] param2 = new Object[5];
			param2[0] = USER_ID;
			param2[1] = TYPE;
			param2[2] = 1;
			param2[3] = 0;
			param2[4] = CREATE_ID;
			flag4 = BaseDao.exeSql(sql4, param2);

			if (TYPE == 2) {
				String sql6 = "SELECT TEAM_ID,PARENT_ID FROM SALES_MEMBER WHERE MEMBER_ID=?";
				Object[] param6 = new Object[1];
				param6[0] = USER_ID;
				JSONArray jsonarr = new JSONArray();
				jsonarr = BaseDao.getRsJSONArray(sql6, param6);
				if (jsonarr.length() > 0) {
					try {
						String TEAM_ID = jsonarr.getJSONObject(0).getString("TEAM_ID");
						String PARENT_ID = jsonarr.getJSONObject(0).getString("PARENT_ID");
						String sql7 = "SELECT TEAM_ID FROM SALES_MEMBER WHERE MEMBER_ID=?";
						Object[] param7 = new Object[1];
						param7[0] = TEAM_ID;
						JSONArray jsonarr1 = new JSONArray();
						jsonarr1 = BaseDao.getRsJSONArray(sql7, param7);
						if (jsonarr1.length() > 0) {
							String VP_ID = jsonarr1.getJSONObject(0).getString("TEAM_ID");
//							String sql8 = "INSERT INTO SALES_MEMBER(TEAM_ID,MEMBER_ID,ROLE,PARENT_ID) VALUES(?,?,?,?)";
//							Object[] param8 = new Object[4];
//							param8[0] = VP_ID;
//							param8[1] = USER_ID;
//							param8[2] = NEW_ROLE;
//							param8[3] = PARENT_ID;
//							flag6 = BaseDao.exeSql(sql8, param8);
							
							String sql9 = "UPDATE SALES_MEMBER SET TEAM_ID=? WHERE MEMBER_ID=?";
							Object[] param9 = new Object[2];
							param9[0] = VP_ID;
							param9[1] = USER_ID;
							BaseDao.exeSql(sql9, param9);
							
							
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		} else if (TYPE == 3) {
			String sql5 = "UPDATE SALES_TEAM SET TYPE=? WHERE TEAM_ID=?";
			Object[] param5 = new Object[2];
			param5[0] = 4;
			param5[1] = USER_ID;
			flag4 = BaseDao.exeSql(sql5, param5);
		}

		JSONArray jsonarr1 = new JSONArray();
		if (flag1 > 0 && flag2 >= 0 && flag3 > 0 && flag4 >= 0) {
			jsonarr1 = getStaffListData(request);
			msg = "升级成功！";
			normal_json = JsonService.getOperateResultNormalJson("true", jsonarr1.toString(), msg);

		} else {
			msg = "升级失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}*/

	


	// add by lizhenyu
	public void getStaffList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getStaffListData(request);
		request.setAttribute("staff_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_staff_upgrade.jsp").forward(request, response);

	}

	public JSONArray getStaffListData(HttpServletRequest request) throws NumberFormatException {
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row


		String current_month="";
		Calendar cal = Calendar.getInstance();
		int YEAR = cal.get(Calendar.YEAR);
		int MONTH= cal.get(Calendar.MONTH);						
		String current_year=Integer.toString(YEAR);
		
		if(MONTH<10)
			current_month="0"+Integer.toString(MONTH);
		else
			current_month=Integer.toString(MONTH);	
		
		String current_year_month= current_year+current_month;

		//查询USERS表、工资表SALARY和基本就业条款表 BASIC_TERMS中的信息：//组长、经理可以升级
		String sql1 = "SELECT * FROM(SELECT A.USER_ID,A.USERNAME,A.ROLE,A.GROLE,B.MONTH,B.PERSONAL_SALE,B.GROUP_SALE FROM USERS A LEFT JOIN SALARY B ON A.USER_ID=B.USER_ID LEFT JOIN BASIC_TERMS C ON C.ROLE=2*(A.ROLE) WHERE B.MONTH="+current_year_month+" AND A.ROLE >=4 AND A.ROLE <= 8 AND B.GROUP_SALE >= C.GROUP_SALE ORDER BY A.ROLE DESC)D";
		//查询独家代理和销售代理
		String sql2 = "SELECT * FROM(SELECT A.USER_ID,A.USERNAME,A.ROLE,A.GROLE,B.MONTH,B.PERSONAL_SALE,B.GROUP_SALE FROM USERS A LEFT JOIN SALARY  B ON A.USER_ID=B.USER_ID WHERE B.MONTH="+current_year_month+" AND A.ROLE>=1 AND A.ROLE<=2 ORDER BY A.ROLE DESC)E";
		String sql =  "SELECT ROWNUM AS RN,F.* FROM("+ sql1 +" UNION ALL "+ sql2 + ")F";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;
		
		String rowSql1 = "SELECT count(1) FROM USERS A LEFT JOIN SALARY B ON A.USER_ID=B.USER_ID LEFT JOIN BASIC_TERMS C ON C.ROLE=2*(A.ROLE) WHERE B.MONTH="+current_year_month+" AND A.ROLE >=4 AND A.ROLE <= 8 AND B.GROUP_SALE >= C.GROUP_SALE";
		String rowSql2 = "SELECT count(1) FROM USERS A LEFT JOIN SALARY B ON A.USER_ID=B.USER_ID WHERE B.MONTH="+current_year_month+" AND A.ROLE>=1 AND A.ROLE<=2";
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows1 = BaseDao.getRowCount(rowSql1, null);
		int rows2 = BaseDao.getRowCount(rowSql2, null);
		int rows=rows1+rows2;
			

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);

		return jsonarr;
	}
}
