package com.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DBHelper;
import com.model.teacher.Teacher;
import com.model.teacher.TeacherManager;
import com.util.CommonUtil;

/**
 * 教师登录控制器
 * @author 飘仙人
 *
 */
public class TeacherLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TeacherManager tm = new TeacherManager();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//连接数据库(仅连一次)
		if(!CommonUtil.isConn){
			try {
				CommonUtil.isConn = DBHelper.connectDB("com.mysql.jdbc.Driver", "mysql",
					 "localhost", "3306", "edushare", "root", "123456");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		String opFlag = request.getParameter("op");
		String phone = request.getParameter("teacherPhone");
		String pwd = request.getParameter("teacherPwd");
		
		Teacher lTeacher = new Teacher();
		lTeacher.setMobile(phone);;
		lTeacher.setPwd(pwd);;
		
		if("quit".equals(opFlag)){
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("teacherlogin.jsp");
			return;
		}
		
		if(!tm.login(lTeacher)){
			String errMsg = "您输入的账户名不存在，请核对后重新输入";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("teacherlogin.jsp").forward(request, response);
			return;
		}
		CommonUtil.currTeacher = lTeacher;
		request.getSession().setAttribute("loginFlag","OK");
		request.getSession().setMaxInactiveInterval(3600);
		HttpSession hs = request.getSession();
 		hs.setAttribute("teacher",lTeacher);
		
		//Cookie记住密码
		String ck[] = request.getParameterValues("rememberPwd");
 		if(ck != null && ck.length > 0){
 			//把用户和密码存储到Cookie里面
 			
 			//使用URLEncoder解决无法在Cookie当中保存中文的问题
 			String phone1 = URLEncoder.encode(phone,"utf-8") ;
 			String pwd1 = URLEncoder.encode(pwd,"utf-8");
 			
 			Cookie adminCookie = new Cookie("phone",phone1);
 			Cookie pwdCookie = new Cookie("pwd",pwd1);
 			adminCookie.setMaxAge(10*24*3600);	//设置生存时间为10天
 			pwdCookie.setMaxAge(10*24*3600);
 			response.addCookie(adminCookie);
 			response.addCookie(pwdCookie);	
 		}else{
			Cookie[] cookie = request.getCookies();
			if(cookie != null && cookie.length > 0){
				for(Cookie c: cookie){
					if(c.getName().equals("phone1") || c.getName().equals("pwd1")){
						c.setMaxAge(0);		//设置Cookie失效
						response.addCookie(c);	//重新保存
					}						
				}						
			}			
 		}	
 		request.getRequestDispatcher("teacherCenter.jsp").forward(request, response);
	}
}