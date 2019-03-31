package com.csh.servlet;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;

import com.csh.dao.BaseDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerMessageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		if (!LoginControlPublicServlet.LoginSessionValidate(request, response, 2))
			return;
		getMessageList(request,response);

	}

	// add by xiaopan
	public void getMessageList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonarr1 = new JSONArray();
		jsonarr1 = getMessageListData(request);
		request.setAttribute("notice_list", jsonarr1.toString());
		request.getRequestDispatcher("/zh_CN/customer/yb_notice_list.jsp").forward(request, response);

	}

	// add by xiaopan
	public static JSONArray getMessageListData(HttpServletRequest request) throws IOException {

		Object USER_ID = LoginControlPublicServlet.getSessionKeyValue(request, "USER_ID");
		String sql = "SELECT A.M_ID,A.MESSAGE,A.STATUS,A.HAPPEN_DATE,A.HAPPEN_TIME,B.USERNAME FROM MESSAGE A LEFT JOIN USERS B ON A.FROM_ID=B.USER_ID WHERE A.TO_ID=? ORDER BY A.HAPPEN_DATE DESC, A.HAPPEN_TIME DESC";
		Object[] param = new Object[1];
		param[0] = USER_ID;
		JSONArray jsonarr = BaseDao.getRsJSONArray(sql, param);
		return jsonarr;
	}
}
