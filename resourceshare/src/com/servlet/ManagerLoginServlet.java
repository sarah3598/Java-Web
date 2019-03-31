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
import com.model.manager.Manager;
import com.model.manager.ManagerManager;
import com.util.CommonUtil;

/**
 * ��̨����Ա��¼������
 * @author Ʈ����
 *
 */
public class ManagerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ManagerManager mm = new ManagerManager();

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
		
		String opFlag = request.getParameter("op");
		String admin = request.getParameter("managerName");
		String pwd = request.getParameter("managerPwd");
		
		Manager lManager = new Manager();
		lManager.setManagername(admin);;
		lManager.setManagerpwd(pwd);;
		
		if("quit".equals(opFlag)){
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("managerlogin.jsp");
			return;
		}
		
		if(!mm.login(lManager)){
			String errMsg = "��������˻��������ڣ���˶Ժ���������";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("managerlogin.jsp").forward(request, response);
			return;
		}
		CommonUtil.currManager = lManager;
		request.getSession().setAttribute("loginFlag","OK");
		request.getSession().setMaxInactiveInterval(3600);
		HttpSession hs = request.getSession();
 		hs.setAttribute("manager",lManager);
		
		//Cookie��ס����
		String ck[] = request.getParameterValues("rememberPwd");
 		if(ck != null && ck.length > 0){
 			//���û�������洢��Cookie����
 			
 			//ʹ��URLEncoder����޷���Cookie���б������ĵ�����
 			String adminName = URLEncoder.encode(admin,"utf-8") ;
 			String adminPwd = URLEncoder.encode(pwd,"utf-8");
 			
 			Cookie adminCookie = new Cookie("adminName",adminName);
 			Cookie pwdCookie = new Cookie("adminPwd",adminPwd);
 			adminCookie.setMaxAge(10*24*3600);	//��������ʱ��Ϊ10��
 			pwdCookie.setMaxAge(10*24*3600);
 			response.addCookie(adminCookie);
 			response.addCookie(pwdCookie);	
 		}else{
			Cookie[] cookie = request.getCookies();
			if(cookie != null && cookie.length > 0){
				for(Cookie c: cookie){
					if(c.getName().equals("adminName") || c.getName().equals("adminPwd")){
						c.setMaxAge(0);		//����CookieʧЧ
						response.addCookie(c);	//���±���
					}						
				}						
			}			
 		}	
 		request.getRequestDispatcher("managerindex.jsp").forward(request, response);
	}
}