package com.servlet;

import java.io.IOException;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DBConnection;
import com.dao.DBHelper;
//import com.model.good.Good;
//import com.model.good.GoodManager;
import com.model.user.User;
import com.model.user.UserManager;
import com.util.CommonUtil;


/**
 * 前台首页控制器
 * @author 飘仙人
 *
 */
public class UserIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//GoodManager gm = new GoodManager();
	UserManager um = new UserManager();

	public UserIndexServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//连接数据库(仅连一次)
		if(!CommonUtil.isConn){
			try {
				CommonUtil.isConn = DBHelper.connectDB("com.mysql.jdbc.Driver", "mysql",
					 "localhost", "3306", "edushare", "root", "123456");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html");
		//List<Good> goodList = null;
		
		String opFlag = request.getParameter("op");
		//goodList = gm.findTwelve();
		
		if("phone".equals(opFlag)){ //手机注册
			User nUser = new User();
			nUser.setUsername(request.getParameter("phone"));
			nUser.setMobile(request.getParameter("phone"));
			String test=request.getParameter("pwd2");
			System.out.println(test);
			nUser.setPwd(test);
			if(um.queryUser(nUser)){
				request.setAttribute("errmsg","账号已存在");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			
			if(!um.register(nUser)){
				request.setAttribute("msg","注册失败！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			request.setAttribute("msg","注册成功！");
			request.getRequestDispatcher("regok.jsp").forward(request, response);
			return;
		}else if("email".equals(opFlag)){	//邮箱注册
			User nUser = new User();
			nUser.setUsername(request.getParameter("email"));
			nUser.setUseremail(request.getParameter("email"));
			nUser.setPwd(request.getParameter("pwd2"));
			
			if(um.queryUser(nUser)){
				request.setAttribute("errmsg","账号已存在");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			
			if(!um.register(nUser)){
				request.setAttribute("msg","注册失败！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			request.setAttribute("msg","注册成功！");
			request.getRequestDispatcher("regok.jsp").forward(request, response);
			return;
		}else if("checkusername".equals(opFlag)){	//确认用户名
			User nUser = new User();
			nUser.setUsername(request.getParameter("userName"));
			
			if(!um.queryUser(nUser)){
				request.setAttribute("errmsg","您输入的账户名不存在，请核对后重新输入。");
				request.getRequestDispatcher("up_pwd_username.jsp").forward(request, response);
				return;
			}
			CommonUtil.currUserName = request.getParameter("userName");
			request.getRequestDispatcher("up_pwd_email.jsp").forward(request, response);
			return;	
		}else if("resetpwd".equals(opFlag)){	//重置密码
		
			String cUser = CommonUtil.currUserName;
			String newPwd = request.getParameter("repwd");
			
			if(um.upUserPwd(cUser, newPwd)){
				request.getRequestDispatcher("up_ok.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}else if(opFlag != null && opFlag.equals("quit")){	 	//清空session		
			HttpSession session = request.getSession();
			session.invalidate();
		}
		
		//request.setAttribute("goodlist", goodList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public void init() throws ServletException {

	}
}