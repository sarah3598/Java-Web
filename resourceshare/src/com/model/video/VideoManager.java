package com.model.video;

import java.util.List;

import com.dao.VideoDao;

/**
 * ��Ƶҵ���
 * @author Administrator
 *
 */
public class VideoManager {
	VideoDao videoDao=new VideoDao();
	
	
	/**
	 * �����Ƶ
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
	 * ɾ����Ƶ
	 */
	public boolean delVideo(Video nvideo){
		boolean bFlag=false;
		
		
		return bFlag;
	}
	
	/**
	 * ��ѯ������Ƶ
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
