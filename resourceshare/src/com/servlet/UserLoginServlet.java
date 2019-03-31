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
import com.model.user.User;
import com.model.user.UserManager;
import com.util.CommonUtil;

/**
 * 前台登录控制器
 * @author Administrator
 *
 */
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager um = new UserManager();

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
		
		String username = request.getParameter("userName");
		String pwd = request.getParameter("password");
		
		User lUser = new User();
		lUser.setUsername(username);
		lUser.setPwd(pwd);
		
		if(!um.login(lUser)){
			String errMsg = "您输入的账号或密码不存在，请核对后重新输入";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		CommonUtil.currUser = lUser;
		request.getSession().setMaxInactiveInterval(3600);
		HttpSession hs = request.getSession();
		
		//Cookie记住密码
		String ck[] = request.getParameterValues("checkboxID");
 		if(ck != null && ck.length > 0){
 			//把用户和密码存储到Cookie里面
 			
 			//使用URLEncoder解决无法在Cookie当中保存中文的问题
 			String userName = URLEncoder.encode(username,"utf-8") ;
 			String userPwd = URLEncoder.encode(pwd,"utf-8");
 			
 			Cookie userCookie = new Cookie("userName",userName);
 			Cookie pwdCookie = new Cookie("userPwd",userPwd);
 			userCookie.setMaxAge(10*24*3600);	//设置生存时间为10天
 			pwdCookie.setMaxAge(10*24*3600);
 			response.addCookie(userCookie);
 			response.addCookie(pwdCookie);	
 		}else{
			Cookie[] cookie = request.getCookies();
			if(cookie != null && cookie.length > 0){
				for(Cookie c: cookie){
					if(c.getName().equals("userName") || c.getName().equals("userPwd")){
						c.setMaxAge(0);		//设置Cookie失效
						response.addCookie(c);	//重新保存
					}						
				}						
			}			
 		}
 		
 		hs.setAttribute("user",lUser);
 		request.getRequestDispatcher("UserIndexServlet.do").forward(request, response);
	}
}