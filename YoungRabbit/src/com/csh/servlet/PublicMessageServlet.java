package com.csh.servlet;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;

import com.csh.dao.BaseDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PublicMessageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// add by xiaopan
	public static int getMessageCount(HttpServletRequest request) throws IOException {
		Object USER_ID = LoginControlPublicServlet.getSessionKeyValue(request, "USER_ID");
		String sql = "SELECT COUNT(1) FROM MESSAGE WHERE TO_ID=? AND STATUS=0";
		Object[] param = new Object[1];
		param[0] = USER_ID;
		int num = BaseDao.getRowCount(sql, param);
		return num;
	}

}
