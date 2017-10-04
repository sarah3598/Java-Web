package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBHelper;
import com.model.teacher.Teacher;
import com.model.teacher.TeacherManager;
import com.util.CommonUtil;

/**
 * 教师控制器
 */
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    TeacherManager tm=new TeacherManager();   
  
    public TeacherServlet() {
        super();
   
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		response.setContentType("text/html");
		String opFlag=request.getParameter("op");
		String phone=request.getParameter("phone");
		
		if("addteacher".equals(opFlag)){
			Teacher nTeacher=new Teacher();
			nTeacher.setMobile(request.getParameter("phone"));
			nTeacher.setEducation(request.getParameter("education"));
			nTeacher.setEmail(request.getParameter("email"));
			nTeacher.setSex(request.getParameter("sex"));
			nTeacher.setPwd(request.getParameter("pwd2"));
			nTeacher.setJobtitle(request.getParameter("jobtitle"));
			nTeacher.setRelname(request.getParameter("relname"));
			nTeacher.setIdno(request.getParameter("idno"));
			nTeacher.setTeachage(Integer.parseInt(request.getParameter("teachage")));
			nTeacher.setSubject(request.getParameter("subject"));
			nTeacher.setAddress(request.getParameter("address"));
	
			if(!tm.addTeacher(nTeacher)){
				request.setAttribute("errmsg","添加失败！");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return;
			}
			request.setAttribute("succeedmsg","添加成功！");
			request.getRequestDispatcher("teacherlogin.jsp").forward(request, response);
			return;
			
		}else if("teacherUpdate".equals(opFlag)){	//修改信息
			Teacher nTeacher=new Teacher();
			nTeacher.setMobile(request.getParameter("phone"));
			nTeacher.setEducation(request.getParameter("education"));
			nTeacher.setEmail(request.getParameter("email"));
			nTeacher.setSex(request.getParameter("sex"));
			nTeacher.setJobtitle(request.getParameter("jobtitle"));
			nTeacher.setRelname(request.getParameter("relname"));
			//nTeacher.setIdno(request.getParameter("idno"));
			String teachage=request.getParameter("teachage");
			nTeacher.setTeachage(Integer.parseInt(teachage));
			nTeacher.setSubject(request.getParameter("subject"));
			String age=request.getParameter("age");
			nTeacher.setAge(Integer.parseInt(age));
			nTeacher.setAddress(request.getParameter("address"));
			
			if(!tm.updateTeacher(nTeacher)){
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return;
			}
			//request.setAttribute("msg", "修改成功！");
			Teacher teacher=tm.findByMobile(phone);
			request.setAttribute("teacher", teacher);
			request.getRequestDispatcher("teacherCenter.jsp").forward(request, response);
		}
		
	}

}
