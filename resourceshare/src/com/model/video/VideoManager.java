package com.model.video;

import java.util.List;

import com.dao.VideoDao;

/**
 * 视频业务层
 * @author Administrator
 *
 */
public class VideoManager {
	VideoDao videoDao=new VideoDao();
	
	
	/**
	 * 添加视频
	 */
	public boolean addVideo(Video nvideo){
		boolean bFlag = false;
		try {
			int iFlag=videoDao.addVideo(nvideo);
			bFlag = iFlag>0?true:false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bFlag;
	}

	/**
	 * 删除视频
	 */
	public boolean delVideo(Video nvideo){
		boolean bFlag=false;
		
		
		return bFlag;
	}
	
	/**
	 * 查询所有视频
	 */
	public List<Video> findAll(){
		List<Video> videolist=null;
		try {
			videolist=videoDao.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return videolist;
		
	}
}
