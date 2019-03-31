package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.user.User;
import com.model.user.UserManager;

/**
 * 后台用户控制器
 * @author 飘仙人
 *
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager um = new UserManager();

	public UserServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html");
		List<User> userList = null;
		
		String opFlag = request.getParameter("op");
		
		if("findAll".equals(opFlag)){		//查所有用户
			userList = um.findAll();
			
			request.setAttribute("userlist", userList);
			request.getRequestDispatcher("manager_user_list.jsp").forward(request, response);
			return;
		}else if("detail".equals(opFlag)){	//查看用户详情
			String userName = request.getParameter("username");
			User user = um.findByUserName(userName);
			
			request.setAttribute("user", user);
			request.getRequestDispatcher("manager_user_details.jsp").forward(request, response);
			return;
		}else if("adduser".equals(opFlag)){	//添加用户
			User nUser = new User();
			nUser.setUsername(request.getParameter("username"));
			nUser.setPwd(request.getParameter("rpwd"));
			
			if(um.queryUser(nUser)){
				request.setAttribute("errmsg","账号已存在");
				request.getRequestDispatcher("manager_user_add.jsp").forward(request, response);
				return;
			}
			
			if(!um.addUser(nUser)){
				request.setAttribute("errmsg","添加失败！");
				request.getRequestDispatcher("manager_user_add.jsp").forward(request, response);
				return;
			}
			request.setAttribute("succeedmsg","添加成功！");
			request.getRequestDispatcher("manager_user_add.jsp").forward(request, response);
			return;
		}else if("deluser".equals(opFlag)){	//删除用户
			User dUser = new User();
			dUser.setUsername(request.getParameter("username"));
			
			if(!um.deleteUser(dUser)){
				request.setAttribute("errmsg","删除用户失败!");
				request.getRequestDispatcher("manager_user_del.jsp").forward(request, response);
				return;
			}
			request.setAttribute("succeedmsg","删除成功！");
			request.getRequestDispatcher("manager_user_del.jsp").forward(request, response);
			return;	
		}

		request.getRequestDispatcher("error.jsp").forward(request, response);
	}

	public void init() throws ServletException {
		
	}
}
