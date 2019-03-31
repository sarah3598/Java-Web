package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBHelper;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.model.user.User;
import com.model.video.Video;
import com.model.video.VideoManager;
import com.util.CommonUtil;

/**
 * ��Ƶ������
 * @author Administrator
 */

public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VideoManager vi=new VideoManager();
       
   
    public VideoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		response.setContentType("text/html");
		String opFlag=request.getParameter("op");
		List<Video> videolist=null;
		
		
		if("addVideo".equals(opFlag)){
			Video nVideo=new Video();
			SmartUpload su = new SmartUpload();
			String result = "�ϴ���Ӱ����ʽ�ʹ�С������,�ϴ�Ӱ��ʧ��!";
			String type = null;
			String[] videoType = { "ogg", "mp4" };
			String filedir = "E:/videos/";
			//Long maxID = Long.valueOf(0);
			long maxsize = 104857600L;
			
			try {
				su.initialize(getServletConfig(), request, response);
				su.setMaxFileSize(maxsize);
				su.upload();
				Files files = su.getFiles();
				for (int i = 0; i < files.getCount(); i++){
					com.jspsmart.upload.File singlefile = files.getFile(i);
					type = singlefile.getFileExt();
					for (int j = 0; j < videoType.length; j++){
						if ((videoType[j].equals(type))&&(!singlefile.isMissing())) {
							String grade = su.getRequest().getParameter("grade");
							String subject=su.getRequest().getParameter("subject");
							String description=su.getRequest().getParameter("description");
							String teachername=su.getRequest().getParameter("teachername");
							String id=su.getRequest().getParameter("teacherid");
							Integer teacherid=Integer.parseInt(id);
							String videourl=filedir + singlefile.getFileName();
							nVideo.setVideourl(videourl);
							nVideo.setGrade(grade);
							nVideo.setSubject(subject);
							nVideo.setTeachername(teachername);
							nVideo.setTeacherid(teacherid);
							nVideo.setDescription(description);
							if(vi.addVideo(nVideo)){
								singlefile.saveAs(videourl, su.SAVE_PHYSICAL);
								result = "�ϴ�Ӱ���ɹ�!";
							}
						}
								
					}
						
				}
				request.setAttribute("result", result);
				request.getRequestDispatcher("teacher_video_add.jsp").forward(request, response);
				return;
				
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}else if("delVideo".equals(opFlag)){	//ɾ����Ƶ
			Video nVideo = new Video();
			nVideo.setId(Integer.parseInt(request.getParameter("id")));
			
			if(!vi.delVideo(nVideo)){
				request.setAttribute("errmsg","ɾ���û�ʧ��!");
				request.getRequestDispatcher("manager_user_del.jsp").forward(request, response);
				return;
			}
			request.setAttribute("succeedmsg","ɾ���ɹ���");
			request.getRequestDispatcher("manager_user_del.jsp").forward(request, response);
			return;	
		}else if("findAll".equals(opFlag)){//��ѯ������Ƶ
			videolist=vi.findAll();
			request.setAttribute("videolist", videolist);
			request.getRequestDispatcher("videolist.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("error.jsp").forward(request, response);
	}

}
