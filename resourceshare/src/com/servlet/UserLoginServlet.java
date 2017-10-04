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
 * ǰ̨��¼������
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
		
		//�������ݿ�(����һ��)
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
			String errMsg = "��������˺Ż����벻���ڣ���˶Ժ���������";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		CommonUtil.currUser = lUser;
		request.getSession().setMaxInactiveInterval(3600);
		HttpSession hs = request.getSession();
		
		//Cookie��ס����
		String ck[] = request.getParameterValues("checkboxID");
 		if(ck != null && ck.length > 0){
 			//���û�������洢��Cookie����
 			
 			//ʹ��URLEncoder����޷���Cookie���б������ĵ�����
 			String userName = URLEncoder.encode(username,"utf-8") ;
 			String userPwd = URLEncoder.encode(pwd,"utf-8");
 			
 			Cookie userCookie = new Cookie("userName",userName);
 			Cookie pwdCookie = new Cookie("userPwd",userPwd);
 			userCookie.setMaxAge(10*24*3600);	//��������ʱ��Ϊ10��
 			pwdCookie.setMaxAge(10*24*3600);
 			response.addCookie(userCookie);
 			response.addCookie(pwdCookie);	
 		}else{
			Cookie[] cookie = request.getCookies();
			if(cookie != null && cookie.length > 0){
				for(Cookie c: cookie){
					if(c.getName().equals("userName") || c.getName().equals("userPwd")){
						c.setMaxAge(0);		//����CookieʧЧ
						response.addCookie(c);	//���±���
					}						
				}						
			}			
 		}
 		
 		hs.setAttribute("user",lUser);
 		request.getRequestDispatcher("UserIndexServlet.do").forward(request, response);
	}
}