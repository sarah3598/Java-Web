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
import com.csh.service.JsonService;
import com.csh.util.DateUtil;
import com.csh.util.Pagination;

public class CompanyStaffSalaryManageServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if (!LoginControlPublicServlet.LoginSessionValidate(request, response, 1))
			return;
		// String method = request.getParameter("method");

		String op = request.getParameter("OP");
		if (op == null) {
			// staff salary==================================
			getSalaryList(request, response);
		} else {

			// by lizhenyu
			switch (op) {

			// staff salary==================================
			case "1":// search Salary
				searchSalary(request, response);
				break;
			case "2":// adjust Salary
				adjustSalary(request, response);
				break;
			case "998":// pagination
				salaryListPaging(request, response);
				break;
			case "999":// adjust Salary
				doSalaryBatch(request, response);
				break;

			}
		}

	}

	// add by xiaopan
	public void salaryListPaging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getSalaryListPagingData(request);

		request.setAttribute("salary_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_staff_salary.jsp").forward(request, response);

	}

	// add by xiaopan
	public JSONArray getSalaryListPagingData(HttpServletRequest request) throws NumberFormatException {

		Pagination paging = (Pagination) request.getSession().getAttribute("page");
		if (paging == null||paging.equals("")) {
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 = getSalaryListData(request);
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
			String sql02 = "SELECT SALARY_ID,BONUS,SALE_BROKERAGE,COVER_BROKERAGE,SUM FROM SALARY  WHERE USER_ID=? AND　MONTH=?";
			Object[] param02 = new Object[2];
			param02[0] = user_id;
			param02[1] = YM;
			JSONArray jsonarr02 = new JSONArray();
			jsonarr02 = BaseDao.getRsJSONArray(sql02, param02);

			String SALARY_ID = "0", BONUS = "0", SALE_BROKERAGE = "0", COVER_BROKERAGE = "0", SUM = "0";
			if (jsonarr02.length() > 0) {
				try {
					SALARY_ID = jsonarr02.getJSONObject(0).getString("SALARY_ID");
					BONUS = jsonarr02.getJSONObject(0).getString("BONUS");
					SALE_BROKERAGE = jsonarr02.getJSONObject(0).getString("SALE_BROKERAGE");
					COVER_BROKERAGE = jsonarr02.getJSONObject(0).getString("COVER_BROKERAGE");
					SUM = jsonarr02.getJSONObject(0).getString("SUM");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				jsonarr.getJSONObject(i).put("SALARY_ID", SALARY_ID);
				jsonarr.getJSONObject(i).put("BONUS", BONUS);
				jsonarr.getJSONObject(i).put("SALE_BROKERAGE", SALE_BROKERAGE);
				jsonarr.getJSONObject(i).put("COVER_BROKERAGE", COVER_BROKERAGE);
				jsonarr.getJSONObject(i).put("SUM", SUM);
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

	// 按月份搜索
	private void searchSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		String YM = request.getParameter("YM");
		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		String sql = "SELECT ROWNUM AS RN,C.* FROM (SELECT a.USERNAME,a.ROLE,b.* FROM USERS a LEFT JOIN SALARY b ON a.USER_ID=b.USER_ID " + "WHERE b.MONTH =" + YM + " order BY b.SALARY_ID DESC)C";
		String listSql = "SELECT * FROM (" + sql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;

		String rowSql = "select count(1) from SALARY  WHERE  MONTH =" + YM;

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);
		int rows = BaseDao.getRowCount(rowSql, null);

		// paging
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalRecord(rows);
		request.setAttribute("page", page);
		request.setAttribute("salary_list", jsonarr.toString());
		request.setAttribute("YM", YM);
		request.getRequestDispatcher("/zh_CN/company/yb_db_staff_salary.jsp").forward(request, response);

	}

	// add by lizhenyu 调整工资
	public void adjustSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String SALARY_ID = request.getParameter("SALARY_ID");
		String ESPECIAL_REWARD = request.getParameter("ESPECIAL_REWARD");
		String SUM = request.getParameter("SUM");
		Object CREATE_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取

		String normal_json = "";
		String msg = "";

		// 修改工资
		String sql = "UPDATE SALARY SET ESPECIAL_REWARD=?,SUM=?,CREATE_ID=? WHERE SALARY_ID=?";
		Object[] param = new Object[4];
		param[0] = ESPECIAL_REWARD;
		param[1] = SUM;
		param[2] = CREATE_ID;
		param[3] = SALARY_ID;

		int result = BaseDao.exeSql(sql, param);
		if (result == 1) {

			// 修改工资完成后，取出数据，刷新列表
			JSONArray jsonarr = new JSONArray();
			jsonarr = getSalaryListData(request);
			msg = "调整成功！";
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		} else
			normal_json = JsonService.getErrorMsgNormalJson("调整失败！");
		JsonService.ResponseJson(request, response, normal_json);
	}

	// add by lizhenyu
	public void getSalaryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getSalaryListData(request);
		request.setAttribute("salary_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/company/yb_db_staff_salary.jsp").forward(request, response);

	}

	private JSONArray getSalaryListData(HttpServletRequest request) {
		// String YM = request.getParameter("YM");
		String YM = request.getParameter("YM");
		// if()

		String page_size = request.getParameter("page_size");
		String current_page = request.getParameter("current_page");
		Boolean flag1 = (page_size == null || page_size.equals("undefined")) ? true : false;
		Boolean flag2 = (current_page == null || current_page.equals("undefined")) ? true : false;

		int pageSize = flag1 ? 10 : Integer.parseInt(request.getParameter("page_size"));
		int currentPage = flag2 ? 1 : Integer.parseInt(request.getParameter("current_page"));
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;// include the end row

		if (YM == null||YM.equals("")) {
			YM = DateUtil.getYearLastMonthString();
		}

		// 01 取成员信息
		String sql = "SELECT USER_ID,USERNAME,ROLE FROM USERS WHERE ROLE>=1 AND ROLE<=16 ORDER BY ROLE DESC";
		String rsql = "SELECT ROWNUM AS RN,C.* FROM (" + sql + ")C";
		String listSql = "SELECT * FROM (" + rsql + ") WHERE RN >=? AND RN <=? ";
		Object[] param = new Object[2];
		param[0] = startRow;
		param[1] = endRow;
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(listSql, param);

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
			String sql02 = "SELECT SALARY_ID,BONUS,SALE_BROKERAGE,COVER_BROKERAGE,SUM FROM SALARY  WHERE USER_ID=? AND　MONTH=?";
			Object[] param02 = new Object[2];
			param02[0] = user_id;
			param02[1] = YM;
			JSONArray jsonarr02 = new JSONArray();
			jsonarr02 = BaseDao.getRsJSONArray(sql02, param02);

			String SALARY_ID = "0", BONUS = "0", SALE_BROKERAGE = "0", COVER_BROKERAGE = "0", SUM = "0";
			if (jsonarr02.length() > 0) {
				try {
					SALARY_ID = jsonarr02.getJSONObject(0).getString("SALARY_ID");
					BONUS = jsonarr02.getJSONObject(0).getString("BONUS");
					SALE_BROKERAGE = jsonarr02.getJSONObject(0).getString("SALE_BROKERAGE");
					COVER_BROKERAGE = jsonarr02.getJSONObject(0).getString("COVER_BROKERAGE");
					SUM = jsonarr02.getJSONObject(0).getString("SUM");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				jsonarr.getJSONObject(i).put("SALARY_ID", SALARY_ID);
				jsonarr.getJSONObject(i).put("BONUS", BONUS);
				jsonarr.getJSONObject(i).put("SALE_BROKERAGE", SALE_BROKERAGE);
				jsonarr.getJSONObject(i).put("COVER_BROKERAGE", COVER_BROKERAGE);
				jsonarr.getJSONObject(i).put("SUM", SUM);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String rowSql = "select count(1) from (" + sql + ")";
		int rows = BaseDao.getRowCount(rowSql, null);

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
	public void doSalaryBatch(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		// JSONArray jsonarr1 = new JSONArray();
		Boolean flag = false;
		try {
			flag = doSalaryBatchData(request, response);
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String msg = "";
		String normal_json = "";
		if (flag) {
			msg = "工资批计算成功！";
			normal_json = JsonService.getOperateResultNormalJson("true", null, msg);
		} else {
			msg = "工资批计算失败！";
			normal_json = JsonService.getErrorMsgNormalJson(msg);
		}
		try {
			JsonService.ResponseJson(request, response, normal_json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// add by xiaopan
	private Boolean doSalaryBatchData(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

		Object CREATE_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");
		// 1）自动获取上个月的年份和月份，

		String YM = DateUtil.getYearLastMonthString();
		String s = YM + "01";
		String e = YM + "31";

		// 设置工资计算时间段
		int START = Integer.parseInt(s);
		int END = Integer.parseInt(e);

		// 2）查询USERS表和基本就业条款表BASIC_TERMS中用户信息和基本工资信息：
		String sql0 = "SELECT A.USER_ID,A.ROLE,A.GROLE,B.SALE_BROKERAGE,B.COVER_BROKERAGE,B.PERSONAL_SALE,B.GROUP_SALE FROM USERS A LEFT JOIN BASIC_TERMS B ON A.ROLE=B.ROLE WHERE A.ROLE >= 0 AND A.ROLE <= 16";
		JSONArray arr0 = BaseDao.getRsJSONArray(sql0, null);
		Object USER_ID, ROLE, GROLE, SALE_BROKERAGE, COVER_BROKERAGE, PERSONAL_SALE, GROUP_SALE;
		for (int i0 = 0; i0 < arr0.length(); i0++) {

			USER_ID = arr0.getJSONObject(i0).get("USER_ID");
			ROLE = arr0.getJSONObject(i0).get("ROLE");
			GROLE = arr0.getJSONObject(i0).get("GROLE");

			SALE_BROKERAGE = arr0.getJSONObject(i0).get("SALE_BROKERAGE");
			COVER_BROKERAGE = arr0.getJSONObject(i0).get("COVER_BROKERAGE");
			PERSONAL_SALE = arr0.getJSONObject(i0).get("PERSONAL_SALE");
			GROUP_SALE = arr0.getJSONObject(i0).get("GROUP_SALE");

			int role = Integer.parseInt((String) ROLE);
			int grole = Integer.parseInt((String) GROLE);

			// 3）计算分销佣金： 使用上面取出的参数START，END，USER_ID，SALE_BROKERAGE（0：无 1：有）
			int S_B = 0;
			if (SALE_BROKERAGE.equals("1")) {
				// 有分销佣金，到佣金表BROKERAGE_2017中计算分销佣金
				// TYPE = 1：销售代理的分销佣金；TYPE = 2：独家代理的分销佣金；TYPE = 3: 组长的分销佣金；TYPE
				// = 4: 经理的分销佣金；TYPE = 5：副总裁的分销佣金
				String sql1 = "SELECT BROKERAGE FROM BROKERAGE_2017 WHERE MEMBER_ID=? AND (TYPE>=1 AND TYPE<=5) AND HAPPEN_DATE>=? AND HAPPEN_DATE<=?";
				Object[] param1 = new Object[3];
				param1[0] = USER_ID;
				param1[1] = START;
				param1[2] = END;
				JSONArray arr1 = BaseDao.getRsJSONArray(sql1, param1);
				// 循环计算分销佣金的月总额：
				float BROKERAGE;
				for (int i1 = 0; i1 < arr1.length(); i1++) {
					BROKERAGE = Integer.parseInt((String) arr1.getJSONObject(i1).get("BROKERAGE"));
					S_B += BROKERAGE;
				}
			}

			// 4）计算覆盖佣金： 使用上面取出的参数START，END，USER_ID，COVER_BROKERAGE（0：无 1：有）
			int C_B = 0;
			if (COVER_BROKERAGE.equals("1")) {
				// 有覆盖佣金，到佣金表BROKERAGE_2017中计算覆盖佣金
				// TYPE = 6：组长的覆盖佣金；TYPE = 7 ; 经理的覆盖佣金；TYPE = 8：副总裁的覆盖佣金
				String sql2 = "SELECT BROKERAGE FROM BROKERAGE_2017 WHERE MEMBER_ID=? AND (TYPE>=6 AND TYPE<=8) AND HAPPEN_DATE>=? AND HAPPEN_DATE<=?";
				Object[] param2 = new Object[3];
				param2[0] = USER_ID;
				param2[1] = START;
				param2[2] = END;
				JSONArray arr2 = BaseDao.getRsJSONArray(sql2, param2);
				// 循环计算分销佣金的月总额：
				// For(int i = 0; i < 长度; i ++){
				// C_B += BROKERAGE;
				float BROKERAGE = 0;
				for (int i2 = 0; i2 < arr2.length(); i2++) {
					BROKERAGE = Integer.parseInt((String) arr2.getJSONObject(i2).get("BROKERAGE"));
					C_B += BROKERAGE;
				}
			}

			// 5）计算个人月份销售额： 使用上面取出的参数START，END，USER_ID
			int P_S = 0;
			// 直接计算该人员的月销售额，到销售表SALES_2017中计算个人月销售额
			String sql3 = "SELECT GOOD_MONEY FROM SALES_2017 WHERE (SALES_ID=? AND TYPE=1) OR (SALES_ID=? AND TYPE=2) OR (MA_ID=? AND TYPE=4) OR (MA_ID=? AND TYPE=8) OR (VP_ID=? AND TYPE=16) AND HAPPEN_DATE>=? AND HAPPEN_DATE<=?";
			Object[] param3 = new Object[7];
			param3[0] = USER_ID;
			param3[1] = USER_ID;
			param3[2] = USER_ID;
			param3[3] = USER_ID;
			param3[4] = USER_ID;
			param3[5] = START;
			param3[6] = END;
			JSONArray arr3 = BaseDao.getRsJSONArray(sql3, param3);
			// 循环计算个人月份销售额：
			float GOOD_MONEY;
			for (int i3 = 0; i3 < arr3.length(); i3++) {
				GOOD_MONEY = Integer.parseInt((String) arr3.getJSONObject(i3).get("GOOD_MONEY"));
				P_S += GOOD_MONEY;
			}

			// 6）计算群体月份销售额： 使用上面取出的参数START，END，USER_ID
			int G_S = 0;
			// 直接计算该人员的群体月销售额，到销售表SALES_2017中计算群体月销售额
			String sql4 = "SELECT GOOD_MONEY FROM SALES_2017 WHERE (MA_ID=? OR VP_ID=?) AND HAPPEN_DATE>=? AND HAPPEN_DATE<=?";
			Object[] param4 = new Object[4];
			param4[0] = USER_ID;
			param4[1] = USER_ID;
			param4[2] = START;
			param4[3] = END;
			JSONArray arr4 = BaseDao.getRsJSONArray(sql4, param4);
			// 循环计算个人月份销售额：
			// for(int i = 0; i < 长度; i ++){
			// G_S += GOOD_MONEY;
			// }
			// Object GOOD_MONEY;
			for (int i4 = 0; i4 < arr4.length(); i4++) {
				GOOD_MONEY = Integer.parseInt((String) arr4.getJSONObject(i4).get("GOOD_MONEY"));
				G_S += GOOD_MONEY;
			}

			// 7）计算额外奖金
			int BONUS = 0;
			// 获取某个人员的角色ROLE，到奖金表中查询该人员的奖金区间
			String sql6 = "SELECT * FROM BONUS WHERE ROLE=? ORDER BY VALID ASC";
			Object[] param6 = new Object[1];
			param6[0] = ROLE;
			JSONArray arr6 = BaseDao.getRsJSONArray(sql6, param6);

			if (role >= 1 && role <= 4) {// 该人员是销售员、组长，只对比个人月销量
				for (int i = 0; i < arr6.length(); i++) {
					int VALID = arr6.getJSONObject(i).getInt("VALID");
					if (P_S >= VALID) { // P_S：个人月份销售额，VALID最低销量
						BONUS = arr6.getJSONObject(i).getInt("BONUS");
					}
				}
			} else if (role >= 8 && role <= 16) { // 该人员是经理或副总裁，只对比群体月销量
				for (int i = 0; i < arr6.length(); i++) {
					int VALID = arr6.getJSONObject(i).getInt("VALID");
					if (G_S >= VALID) // G_S：群体月份销售额，VALID最低销量
						BONUS = arr6.getJSONObject(i).getInt("BONUS");
				}
			}

			// 8）计算基本工资小结
			int SUM = BONUS + S_B + C_B;

			// 插入一条工资记录到SALARY表：
			String sql5 = "INSERT INTO " + "SALARY(USER_ID,GROLE,MONTH,SUM,BONUS,SALE_BROKERAGE,COVER_BROKERAGE,PERSONAL_SALE,GROUP_SALE,ESPECIAL_REWARD) " + "VALUES(?,?,?,?,?,?,?,?,?,?)";
			// USER_ID=USER_ID,GROLE=GROLE,MONTH=YM,SUM=SUM,SALARY=SALARY,VARIABLE_BONUS=VARIABLE_BONUS,SALE_BROKERAGE=S_B,COVER_BROKERAGE=C_B,EPFSOCSO=0,PERSONAL_SALE=P_S,GROUP_SALE=G_S,MEDICAL_ALLOWANCE=0,JOURNEY_ALLOWANCE=0,ESPECIAL_REWARD=0
			Object[] param5 = new Object[10];
			param5[0] = USER_ID;
			param5[1] = GROLE;
			param5[2] = YM;
			param5[3] = SUM;
			param5[4] = BONUS;
			param5[5] = S_B;
			param5[6] = C_B;
			param5[7] = P_S;
			param5[8] = G_S;
			param5[9] = 0;

			// param5[14] = CREATE_ID;
			int flag5 = BaseDao.exeSql(sql5, param5);

			// 9）上月评级批处理
			if (role == 1) { // ROLE==1该人员是销售代理，只能升级为独家代理
				if (grole == 2) { // (1)更新该人员为独家代理; (2)修改自己在团队成员中的角色
					String sql7 = "UPDATE USERS SET ROLE=? WHERE USER_ID=?";
					Object[] param7 = new Object[2];
					param7[0] = GROLE;
					param7[1] = USER_ID;
					int flag7 = BaseDao.exeSql(sql7, param7);

					String sql8 = "UPDATE SALES_MEMBER SET ROLE=? WHERE MEMBER_ID=?";
					Object[] param8 = new Object[2];
					param8[0] = GROLE;
					param8[1] = USER_ID;
					int flag8 = BaseDao.exeSql(sql8, param8);

				}
			} else if (role == 2) { // ROLE==2该人员是独家代理，只能降级为销售代理或者升级为组长
				if (grole == 1) { // (1)更新该人员为独家代理; (2)修改自己在团队成员中的角色
					String sql9 = "UPDATE USERS SET ROLE=? WHERE USER_ID=?";
					Object[] param9 = new Object[2];
					param9[0] = GROLE;
					param9[1] = USER_ID;
					int flag9 = BaseDao.exeSql(sql9, param9);

					String sql10 = "UPDATE SALES_MEMBER SET ROLE=? WHERE MEMBER_ID=?";
					Object[] param10 = new Object[2];
					param10[0] = GROLE;
					param10[1] = USER_ID;
					int flag10 = BaseDao.exeSql(sql10, param10);

				} else if (grole == 4) { // (1)更新该人员为组长
											// (2)创建销售团队；(3)修改自己在团队成员中的角色
					String sql11 = "UPDATE USERS SET ROLE=? WHERE USER_ID=?";
					Object[] param11 = new Object[2];
					param11[0] = GROLE;
					param11[1] = USER_ID;
					int flag11 = BaseDao.exeSql(sql11, param11);

					String sql12 = "INSERT INTO SALES_TEAM(TEAM_ID,TYPE,STATUS) VALUES(?,?,?)";
					Object[] param12 = new Object[3];
					param12[0] = USER_ID;
					param12[1] = 1;
					param12[2] = 1;
					int flag12 = BaseDao.exeSql(sql12, param12);

					String sql10 = "UPDATE SALES_MEMBER SET ROLE=? WHERE MEMBER_ID=?";
					Object[] param10 = new Object[2];
					param10[0] = GROLE;
					param10[1] = USER_ID;
					int flag10 = BaseDao.exeSql(sql10, param10);

				}
			} else if (role == 4 || role <= 8) { // 该人员是组长或者经理
				if (grole >= 4 && grole <= 8) {
					// (1)更新该人员的角色 (2)修改自己团队的类型 (3)修改自己在团队成员中的角色
					String sql13 = "UPDATE USERS SET ROLE=? WHERE USER_ID=?";
					Object[] param13 = new Object[2];
					param13[0] = GROLE;
					param13[1] = USER_ID;
					int flag11 = BaseDao.exeSql(sql13, param13);

					// 判断团队类型
					int type = 0;
					if (grole == 4)
						type = 1;
					else
						type = 2;

					String sql14 = "UPDATE SALES_TEAM SET TYPE=? WHERE TEAM_ID=?";
					Object[] param14 = new Object[2];
					param14[0] = type;
					param14[1] = USER_ID;
					int flag14 = BaseDao.exeSql(sql14, param14);

					String sql15 = "UPDATE SALES_MEMBER SET ROLE=? WHERE MEMBER_ID=?";
					Object[] param15 = new Object[2];
					param15[0] = GROLE;
					param15[1] = USER_ID;
					int flag15 = BaseDao.exeSql(sql15, param15);

				} else if (grole == 16) { // 升级成为副总裁
					// (1)更新该人员的角色 (2)修改自己团队的类型 (3)删除自己在其他团队成员中的记录
					String sql16 = "UPDATE USERS SET ROLE=? WHERE USER_ID=?";
					Object[] param16 = new Object[2];
					param16[0] = GROLE;
					param16[1] = USER_ID;
					int flag16 = BaseDao.exeSql(sql16, param16);

					String sql17 = "UPDATE SALES_TEAM SET TYPE=? WHERE TEAM_ID=?";
					Object[] param17 = new Object[2];
					param17[0] = 3;
					param17[1] = USER_ID;
					int flag17 = BaseDao.exeSql(sql17, param17);

					String sql18 = "DELETE FROM SALES_MEMBER WHERE MEMBER_ID=?";
					Object[] param18 = new Object[1];
					param18[0] = USER_ID;
					int flag18 = BaseDao.exeSql(sql18, param18);

				}
			} else if (role == 16) { // 该人员是副总裁，被降级
				if (grole >= 4 && grole <= 8) {
					// (1)更新该人员的角色 (2)修改自己团队的类型

					String sql19 = "UPDATE USERS SET ROLE=? WHERE USER_ID=?";
					Object[] param19 = new Object[2];
					param19[0] = GROLE;
					param19[1] = USER_ID;
					int flag19 = BaseDao.exeSql(sql19, param19);

					// 判断团队类型
					int type = 0;
					if (grole == 4)
						type = 1;
					else
						type = 2;

					String sql20 = "UPDATE SALES_TEAM SET TYPE=? WHERE TEAM_ID=?";
					Object[] param20 = new Object[2];
					param20[0] = type;
					param20[1] = USER_ID;
					int flag20 = BaseDao.exeSql(sql20, param20);
				}
			}
		}
		// return jsonarr;
		return true;
	}
}
