package com.csh.servlet;

/*********************
 * Author shaohui-chen
 ******************** */
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.csh.dao.BaseDao;
import com.csh.domain.Users;
import com.csh.service.JsonService;
import com.csh.util.Encoder;
import com.csh.service.CompanyUsersService;

public class CompanyUserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String EMAIL = request.getParameter("EMAIL");
		String PWD = request.getParameter("PWD");
		if(!(PWD.equals(""))){
			try {
				PWD =Encoder.EncoderByMd5(PWD);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String sql = "SELECT * FROM USERS WHERE EMAIL=? and PWD=?";
		Object[] param = new Object[2];
		param[0] = EMAIL;
		param[1] = PWD;

		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql, param);

		String normal_json = "";
		String msg = "";
		int ROLE = 0;
		int STATUS=0;

		String path = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath();// 项目名称
		String url = "";

		if (jsonarr != null && jsonarr.length() > 0) {
			
			String strBackUrl = request.getParameter("token");
			try {
				ROLE = Integer.parseInt(jsonarr.getJSONObject(0).getString("ROLE"));
				STATUS = Integer.parseInt(jsonarr.getJSONObject(0).getString("STATUS"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
				
			if (ROLE > 0 && ROLE < 9999) {
				request.getSession().setAttribute("users", jsonarr);
				if (strBackUrl != null && strBackUrl != "") {
					request.getSession().setAttribute("users", jsonarr);
					msg = "登录成功！";
					String json = JsonService.convertKeyValToJsonObject("url", strBackUrl);
					normal_json = JsonService.getNormalJson(json, msg);
					// response.sendRedirect(strBackUrl);
					// return;
					// request.getRequestDispatcher(strBackUrl).forward(request,
					// response);
				} else {
					msg = "登录成功！";
					
					//判断个人信息是否完整，不完整则跳转到完善信息页面
					if(STATUS==1)
						url = path + "/zh_CN/company/yb_register_step_2.jsp";
					else if(STATUS==2)
						url = path + "/zh_CN/company/yb_register_step_3.jsp";
					else 
						url = path + "/zh_CN/company/yb_dashboard.jsp";
					
					String json = JsonService.convertKeyValToJsonObject("url", url);
					normal_json = JsonService.getNormalJson(json, msg);
				}
						
			} else {
					msg = "用户名或密码错误！！！";
					normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
			}					
		} else {
			msg = "用户名或密码错误！！！";
			normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
		}

		JsonService.ResponseJson(request, response, normal_json);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
