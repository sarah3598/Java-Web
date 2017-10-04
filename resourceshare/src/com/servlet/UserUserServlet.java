package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.model.user.User;
import com.model.user.UserManager;

/**
 * 前台用户控制器
 * @author Administrator
 *
 */
public class UserUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserManager um = new UserManager();

	public UserUserServlet() {
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

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html");
		String opFlag = request.getParameter("op");
		String userName = request.getParameter("username");

		if("upDate".equals(opFlag)){
			User user = um.findByUserName(userName);
			request.setAttribute("user", user);
			request.getRequestDispatcher("selfupdate.jsp").forward(request, response);
			return;
		}else if("userUpdate".equals(opFlag)){	//修改用户
			User uUser = new User();
			SmartUpload su = new SmartUpload();
			String result = "上传的图片格式和大小有问题,上传头像失败!";
			String type = null;
			String[] imgType = { "png", "jpg" };
			String filedir = "E:/images/";
			long maxsize = 104857600L;
			su.initialize(getServletConfig(), request, response);
			su.setMaxFileSize(maxsize);
			try {
				su.upload();
				Files files = su.getFiles();
				for (int i = 0; i < files.getCount(); i++){
					com.jspsmart.upload.File singlefile = files.getFile(i);
					type = singlefile.getFileExt();
					for (int j = 0; j < imgType.length; j++){
						if ((imgType[j].equals(type))&&(!singlefile.isMissing())) {
							String username = su.getRequest().getParameter("userName");
							String userEmail=su.getRequest().getParameter("userEmail");
							String mobile = su.getRequest().getParameter("mobile");
							String relName = su.getRequest().getParameter("relName");
							String sex = su.getRequest().getParameter("sex");
							String city=su.getRequest().getParameter("city");
							String nickName=su.getRequest().getParameter("nickName");
							String picurl=filedir + singlefile.getFileName();
							
							uUser.setUsername(username);
							uUser.setUseremail(userEmail);
							uUser.setMobile(mobile);
							uUser.setRelname(relName);
							uUser.setSex(sex);
							uUser.setCity(city);
							uUser.setNickname(nickName);
							uUser.setPicurl(picurl);
							
							if(um.updateUser(uUser)){
								singlefile.saveAs(picurl, su.SAVE_PHYSICAL);
								request.setAttribute("msg", "修改成功！");
								
							}
						}
					}
				}
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!um.updateUser(uUser)){
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return;
			}
			
		}
		User user = um.findByUserName(userName);
		request.setAttribute("user", user);
		request.getRequestDispatcher("selfupdate.jsp").forward(request, response);
	}

	public void init() throws ServletException {
		
	}
}