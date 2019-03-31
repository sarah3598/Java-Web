package com.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.user.User;



/**
 * 用户dao层
 * @author 飘仙人
 *
 */
public class UserDao {
	private StringBuffer sql = null;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 用户登录
	 * @param lUser
	 * @return
	 * @throws Exception
	 */
	public boolean login(User lUser) throws Exception{
		boolean bFlag = false;
		sql = new StringBuffer("");
		sql.append("select * from user_info where username='").append(lUser.getUsername()).append("'");
		System.out.println(sql);
		ResultSet rs = DBHelper.queryData(sql.toString());
		if(!rs.next()){
			return bFlag;
		}
		
		if(lUser.getPwd().equals(rs.getString("pwd"))){
			bFlag = true;
			lUser.setUserid(rs.getLong("userid"));
			lUser.setNickname(rs.getString("nickname"));
			lUser.setUseremail(rs.getString("useremail"));
			lUser.setMobile(rs.getString("mobile"));
			lUser.setRelname(rs.getString("relname"));
			lUser.setSex(rs.getString("sex"));
			lUser.setCity(rs.getString("city"));
			lUser.setAddress(rs.getString("address"));
			lUser.setScore(rs.getInt("score"));
		}	
		return bFlag;
	}
	
	/**
	 * 添加用户
	 * @param nUser
	 * @return
	 * @throws Exception
	 */
	public int addUser(User nUser) throws Exception{
		sql = new StringBuffer("");
		sql.append("insert into user_info(username,pwd)values('").append(nUser.getUsername())
			.append("','").append(nUser.getPwd())
			.append("');");
		int iFlag = DBHelper.updateData(sql.toString());
		
		return iFlag;
	}
	
	/**
	 * 注册用户
	 * @param nUser
	 * @return
	 * @throws Exception
	 */
	public int register(User nUser) throws Exception{
		sql = new StringBuffer("");
		if(nUser.getMobile()!=null){
			sql.append("insert into user_info(username,pwd,mobile,adddate) values('").append(nUser.getUsername())
			.append("','").append(nUser.getPwd()).append("','").append(nUser.getMobile()).append(df.format(new Date())).append("');");
		}else if(nUser.getUseremail()!=null){
			sql.append("insert into user_info(username,pwd,useremail) values('").append(nUser.getUsername())
			.append("','").append(nUser.getPwd()).append("','").append(nUser.getUseremail()).append("');");
		}
		System.out.println(sql);
		int iFlag = DBHelper.updateData(sql.toString());
		
		return iFlag;
	}
	
	/**
	 * 查询用户存在
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean queryUser(User user) throws Exception{
		boolean bFlag = false;
		sql = new StringBuffer("");
		sql.append("select * from user_info where username='").append(user.getUsername()).append("'");
		ResultSet rs = DBHelper.queryData(sql.toString());
		if(rs.next()){
			bFlag = true;
		}
		return bFlag;
	}
	
	/**
	 * 修改资料
	 * @param uUser
	 * @return
	 * @throws Exception
	 */
	public int updateUser(User uUser) throws Exception{
		sql = new StringBuffer("");
		sql.append("update user_info set NICKNAME='").append(uUser.getNickname())
		.append("',useremail='").append(uUser.getUseremail())
		.append("',mobile='").append(uUser.getMobile())
		.append("',relname='").append(uUser.getRelname())
		.append("',sex='").append(uUser.getSex())
		.append("',city='").append(uUser.getCity())
		.append("',address='").append(uUser.getAddress())
		.append("',picurl='").append(uUser.getPicurl())
		.append("' where username='").append(uUser.getUsername()).append("'");
		
		int iFlag = DBHelper.updateData(sql.toString());
		return iFlag;	
	}
	
	/**
	 * 删除用户
	 * @param dUser
	 * @return
	 * @throws Exception
	 */
	public int deleteUser(User dUser) throws Exception {
		sql = new StringBuffer("");
		sql.append("delete from user_info").append(" where username='")
				.append(dUser.getUsername()).append("'");

		int iFlag = DBHelper.updateData(sql.toString());
		return iFlag;
	}
	
	/**
	 * 修改密码
	 * @param cUser
	 * @param newPwd
	 * @return
	 * @throws Exception
	 */
	public int modifyPwd(User cUser,String newPwd) throws Exception{
		sql = new StringBuffer("");
		sql.append("update user_info set pwd='").append(newPwd)
			.append("' where username='").append(cUser.getUsername()).append("'");
		
		int iFlag = DBHelper.updateData(sql.toString());	
		return iFlag;	
	}
	
	/**
	 * 用户修改密码
	 * @param cUser
	 * @param newPwd
	 * @return
	 * @throws Exception
	 */
	public int upUserPwd(String cUser,String newPwd) throws Exception{
		sql = new StringBuffer("");
		sql.append("update user_info set pwd='").append(newPwd)
			.append("' where username='").append(cUser).append("'");
		
		int iFlag = DBHelper.updateData(sql.toString());	
		return iFlag;	
	}
	
	/**
	 * 查询所有用户
	 * @return
	 * @throws Exception
	 */
	public List<User> findAll() throws Exception{
		sql = new StringBuffer("");
		sql.append("select * from user_info");
		ResultSet rs = DBHelper.queryData(sql.toString());

		List<User> userList = new ArrayList<User>();
		while(rs.next()){
			User u = new User();
			u.setUserid(rs.getLong("userid"));
			u.setUsername(rs.getString("username"));
			u.setPwd(rs.getString("pwd"));
			u.setUseremail(rs.getString("useremail"));
			u.setMobile(rs.getString("mobile"));
			u.setRelname(rs.getString("relname"));
			u.setSex(rs.getString("sex"));
			u.setCity(rs.getString("city"));
			u.setAddress(rs.getString("address"));
			u.setScore(rs.getInt("score"));
			
			userList.add(u);
		}
		return userList;	
	}
	
	/**
	 * 查询一个用户
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public User findByUserName(String userName) throws Exception{
		sql = new StringBuffer("");
		sql.append("select * from user_info where username='").append(userName).append("'");
		ResultSet rs = DBHelper.queryData(sql.toString());

		if(!rs.next()){
			return null;
		}
		
		User user = new User();
		user.setUserid(rs.getLong("userid"));
		user.setNickname(rs.getString("nickname"));
		user.setPwd(rs.getString("pwd"));
		user.setUseremail(rs.getString("useremail"));
		user.setMobile(rs.getString("mobile"));
		user.setRelname(rs.getString("relname"));
		user.setSex(rs.getString("sex"));
		user.setCity(rs.getString("city"));
		user.setAddress(rs.getString("address"));
		user.setScore(rs.getInt("score"));
				
		return user;
	}
}