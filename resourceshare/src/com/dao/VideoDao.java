package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.video.Video;



/**
 * 视频Dao层
 * @author Administrator
 *
 */
public class VideoDao {
	private StringBuffer sql = null;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 增加视频
	 */
	public int addVideo(Video nVideo) throws Exception{
		sql = new StringBuffer("");
		sql.append("insert into video(videourl,videopic,grade,uptime,subject,description,teacherid,teachername)values('").append(nVideo.getVideourl())
			.append("','").append(nVideo.getVideopic())
			.append("','").append(nVideo.getGrade())
			.append("','").append(df.format(new Date()))
			.append("','").append(nVideo.getSubject())
			.append("','").append(nVideo.getDescription())
			.append("','").append(nVideo.getTeacherid())
			.append("','").append(nVideo.getTeachername()).append("');");
		System.out.println(sql);
		int iFlag = DBHelper.updateData(sql.toString());
		
		return iFlag;
	}
	
	/**
	 * 查询视频是否存在
	 */
	public boolean queryVideo(Video nVideo) throws Exception{
		boolean bFlag = false;
		sql = new StringBuffer("");
		sql.append("select * from video where id='").append(nVideo.getId()).append("'");
		ResultSet rs = DBHelper.queryData(sql.toString());
		if(rs.next()){
			bFlag = true;
		}
		return bFlag;
	}
	
	/**
	 * 查询一个视频
	 */
	public Video findByVideoId(String videoId) throws Exception{
		sql = new StringBuffer("");
		sql.append("select * from video where id='").append(videoId).append("'");
		ResultSet rs = DBHelper.queryData(sql.toString());

		if(!rs.next()){
			return null;
		}
		Video nvideo=new Video();
		nvideo.setGrade(rs.getString("grade"));
		nvideo.setSubject(rs.getString("subject"));
		nvideo.setTeachername(rs.getString("teachername"));
		nvideo.setDescription(rs.getString("description"));
		return nvideo;
	}
	/**
	 * 删除视频
	 */
	public int deleteVideo(Video nVideo) throws Exception {
		sql = new StringBuffer("");
		sql.append("delete from video").append(" where id='")
				.append(nVideo.getId()).append("'");

		int iFlag = DBHelper.updateData(sql.toString());
		return iFlag;
	}
	
	/**
	 * 查询所有视频
	 */
	public List<Video> findAll() throws Exception{
		sql = new StringBuffer("");
		sql.append("select * from video");
		ResultSet rs = DBHelper.queryData(sql.toString());
		System.out.println(sql);
		List<Video> videolist=new ArrayList<Video>();
		
		while(rs.next()){
			Video v=new Video();
			v.setId(rs.getInt("id"));
			v.setGrade(rs.getString("grade"));
			v.setSubject(rs.getString("subject"));
			v.setTeachername(rs.getString("teachername"));
			v.setDescription(rs.getString("description"));
			v.setUptime(rs.getDate("uptime"));
		}
		
		return videolist;
	}
	
	public Integer MaxQueryID() {
		Integer maxID = Integer.valueOf(0);
		String sql = "select max(id) as id from video";
		try {
			ResultSet rs = DBHelper.queryData(sql.toString());
			while (rs.next()) {
				maxID = Integer.valueOf(rs.getInt("id"));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxID;
	}
	
}

