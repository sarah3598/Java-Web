package com.csh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.csh.dao.BaseDao;
import com.csh.service.JsonService;
import com.csh.service.SlideService;

/**
 * @author wendy
 *
 */
public class HomePageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		getSlideList(request, response);
	}

	private void getSlideList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "select * from SLIDER";
		JSONArray arr = BaseDao.getRsJSONArray(sql, null);
		
		String msg="";
		String normal_json="";

		if (arr.length() > 0) {
			msg="图片加载成功！";
			normal_json=JsonService.getNormalJson(arr.toString(), msg);
		}
		else{
			msg="图片加载失败！";
			normal_json=JsonService.getErrorMsgNormalJson(msg);
		
		}
		JsonService.ResponseJson(request, response, normal_json);
		//request.getRequestDispatcher("/zh_CN/customer/yb_home_page.jsp").forward(request, response);
	}

}
