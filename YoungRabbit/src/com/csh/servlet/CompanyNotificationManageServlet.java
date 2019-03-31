package com.csh.servlet;

/*********************
 * Author shaohui-chen
 ******************** */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.csh.dao.BaseDao;
import com.csh.service.JsonService;
import com.csh.util.Pagination;

public class CompanyNotificationManageServlet extends HttpServlet {

	// ------------------------------
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if (!LoginControlPublicServlet.LoginSessionValidate(request, response, 1))
			return;
		String method = request.getParameter("method");
		if (method == null) {
			try {
				getNotificationList(request, response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}	
		// by lizhenyu
		switch (method) {
		
		// add Notification====================================
		case "addNotification":// add_Notification
			try {
				addNotification(request, response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;				
		}
	}

	

	private void addNotification(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, JSONException {
		Object FROM_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");	   
		String TIDS = request.getParameter("TIDS");
		String MESSAGE = request.getParameter("MESSAGE");
		String[] TEAM_ID=TIDS.split(",");
		String normal_json = "";
		String msg ="";
		int result =0;
		JSONArray jsonarr = new JSONArray();
		
	    for(int i=0;i<TEAM_ID.length;i=i+2)
	    {		   
				//ROLE=3副总裁团队，取出的成员都为经理角色
				if(Integer.parseInt(TEAM_ID[i+1])==3)
		    	{
					String sql1 = "SELECT MEMBER_ID FROM SALES_MEMBER WHERE TEAM_ID=?  AND (ROLE=4 OR ROLE=8)";
					Object[] param1 = new Object[1];
					param1[0] = TEAM_ID[i];				
					jsonarr = BaseDao.getRsJSONArray(sql1, param1);
					if (jsonarr != null && jsonarr.length() > 0)
					{
						 for(int j = 0; j < jsonarr.length(); j++ )
						 {
							 JSONObject ob  = (JSONObject) jsonarr.get(j);
							 boolean flag1 = true;
			    		     for(int k = 0; k < TEAM_ID.length; k=k+2)
						     {	
								if(Integer.parseInt(TEAM_ID[k]) == Integer.parseInt((String) ob.get("MEMBER_ID")))
										flag1 =false;
						     }	
			    		     if(flag1){
							
							 String sql2 = "INSERT INTO MESSAGE(MESSAGE,TYPE,STATUS,FROM_ID,TO_ID) VALUES(?,?,?,?,?)";
							 Object[] param2 = new Object[5];
							 param2[0] = MESSAGE;
							 param2[1] = 1;
							 param2[2] = 0;
							 param2[3] = FROM_ID;			
							 param2[4] = ob.get("MEMBER_ID") ;
							 result = BaseDao.exeSql(sql2, param2);
							 if(result == 1)
							 {
								normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
							 }
							 else 
								normal_json = JsonService.getErrorMsgNormalJson( "添加失败！");						
			    		     }
						 }
					  }
						normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
					}
				//ROLE=2||ROLE=1经理团队，取出的成员都为销售员角色
				else if(Integer.parseInt(TEAM_ID[i+1])==2 || Integer.parseInt(TEAM_ID[i+1])==1)
				{
					String sql1 = "SELECT MEMBER_ID FROM SALES_MEMBER WHERE TEAM_ID=?  AND (ROLE=1 OR ROLE=2)";
					Object[] param1 = new Object[1];
					param1[0] = TEAM_ID[i];				
					jsonarr = BaseDao.getRsJSONArray(sql1, param1);
					if (jsonarr != null && jsonarr.length() > 0)
					{
						 for(int j = 0; j < jsonarr.length(); j++ )
						 {
							 JSONObject ob  = (JSONObject) jsonarr.get(j);												
							 String sql2 = "INSERT INTO MESSAGE(MESSAGE,TYPE,STATUS,FROM_ID,TO_ID) VALUES(?,?,?,?,?)";
							 Object[] param2 = new Object[5];
							 param2[0] = MESSAGE;
							 param2[1] = 1;
							 param2[2] = 0;
							 param2[3] = FROM_ID;			
							 param2[4] = ob.get("MEMBER_ID") ;
							 result = BaseDao.exeSql(sql2, param2);
							 if(result == 1)
							 {
								normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
							 }
							 else 
								normal_json = JsonService.getErrorMsgNormalJson( "添加失败！");						
			    		 }						 
					  }
						normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
					}
				
				/*else if(Integer.parseInt(TEAM_ID[i+1])==1)
				{
					String sql1 = "SELECT MEMBER_ID FROM SALES_MEMBER WHERE TEAM_ID=?  AND ROLE=0";
					Object[] param1 = new Object[1];
					param1[0] = TEAM_ID[i];					
					jsonarr = BaseDao.getRsJSONArray(sql1, param1);
					if (jsonarr != null && jsonarr.length() > 0)
					{
						 for(int j = 0; j < jsonarr.length(); j++ )
						 {
							 JSONObject ob  = (JSONObject) jsonarr.get(j);												
							 String sql2 = "INSERT INTO MESSAGE(MESSAGE,TYPE,STATUS,FROM_ID,TO_ID) VALUES(?,?,?,?,?)";
							 Object[] param2 = new Object[5];
							 param2[0] = MESSAGE;
							 param2[1] = 1;
							 param2[2] = 0;
							 param2[3] = FROM_ID;			
							 param2[4] = ob.get("MEMBER_ID") ;
							 result = BaseDao.exeSql(sql2, param2);
							 if(result == 1)
							 {
								normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
							 }
							 else 
								normal_json = JsonService.getErrorMsgNormalJson( "添加失败！");						
			    		 }						 
					  }
						normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
					}*/
				
			    boolean flag = true;
			   	for(int j=i+2; j<TEAM_ID.length; j=j+2)
			    {	    		 	 	    
			    	 if(Integer.parseInt(TEAM_ID[i]) == Integer.parseInt(TEAM_ID[j]))		    	 
			    		 flag = false;	    		    	    		     		    	   		    	
			    }
		   	    if(flag)
		   	    {
					//将消息存入团队负责人的消息表中
				   	String sql = "INSERT INTO MESSAGE(MESSAGE,TYPE,STATUS,FROM_ID,TO_ID) VALUES(?,?,?,?,?)";
					Object[] param = new Object[5];
					param[0] = MESSAGE;
					param[1] = 1;
					param[2] = 0;
					param[3] = FROM_ID;			
					param[4] = TEAM_ID[i];
					result = BaseDao.exeSql(sql, param);
					if (result == 1) 
					{
						normal_json = JsonService.getNormalJson(jsonarr.toString(), msg);
					}
					else
						normal_json = JsonService.getErrorMsgNormalJson( "添加失败！");		
			   	    }
			}	    			
	    JsonService.ResponseJson(request, response, normal_json);
	}

	

	// add by lizhenyu
	public void getNotificationList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, JSONException {
		Object USER_ID =LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID"); // 从session中获取				   
		Object ROLE =LoginControlPublicServlet.getSessionKeyValue(request, response, "ROLE"); // 从session中获取				   
		Object USERNAME =LoginControlPublicServlet.getSessionKeyValue(request, response, "USERNAME");
		int role =  Integer.parseInt(ROLE.toString());
		if(role >= 4 && role <= 8)
		{
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 =  getNotificationListData(request,USER_ID,USERNAME);
			request.setAttribute("notification_list", jsonarr1.toString());
			request.getRequestDispatcher("/zh_CN/company/yb_db_notification.jsp").forward(request, response);				
		}
		
		else if(role ==16)
		{
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 =  getNotificationListData1(request,USER_ID,USERNAME);
			request.setAttribute("notification_list", jsonarr1.toString());
			request.getRequestDispatcher("/zh_CN/company/yb_db_notification.jsp").forward(request, response);				
		}
		else if(role ==1024 || role == 2048)
		{
			JSONArray jsonarr1 = new JSONArray();
			jsonarr1 =  getNotificationListData2(request);
			request.setAttribute("notification_list", jsonarr1.toString());
			request.getRequestDispatcher("/zh_CN/company/yb_db_notification.jsp").forward(request, response);
		}
	}
				
	//查询经理团队及培训团队
	private JSONArray getNotificationListData(HttpServletRequest request, Object USER_ID , Object USERNAME) throws JSONException{
		Object user_id=USER_ID;	
		Object user_name=USERNAME;		

		JSONArray jsonarr = new JSONArray();	
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("TEAM_ID", user_id);
		jsonObj.put("TEAM_NAME", user_name);
		jsonObj.put("OP", 1);
		jsonObj.put("ROLE",2);
		jsonarr.put(jsonObj);
		
		/*String sql = "SELECT A.TEAM_ID,A.TYPE AS ROLE,B.USERNAME AS TEAM_NAME FROM SALES_TEAM A LEFT JOIN USERS  B ON A.TEAM_ID=B.USER_ID WHERE A.TEAM_ID=? AND (A.TYPE=1 OR A.TYPE=2) ORDER BY  A.TYPE DESC";		
		Object[] param = new Object[1];
		param[0] = user_id;
	
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql, param);
		try {
			if(jsonarr.length()>0)
				jsonarr.getJSONObject(0).put("OP", 1);						
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonarr;*/
		
		
		return jsonarr; 
	}		
	
	//查询副总裁团队下的经理团队
	private JSONArray getNotificationListData1(HttpServletRequest request, Object USER_ID, Object USERNAME) throws JSONException {
		Object user_id=USER_ID;		
		Object user_name=USERNAME;
		
		String sql = "SELECT A.MEMBER_ID AS TEAM_ID,B.USERNAME AS TEAM_NAME FROM SALES_MEMBER A LEFT JOIN USERS B ON A.MEMBER_ID=B.USER_ID WHERE A.TEAM_ID=? AND (A.ROLE=4 OR A.ROLE=8)";		
		Object[] param = new Object[1];
		param[0] = user_id;
		
		JSONArray jsonarr = new JSONArray();	
		jsonarr = BaseDao.getRsJSONArray(sql, param);
		try {						
			for(int i = 0;i<jsonarr.length();i++){
				jsonarr.getJSONObject(i).put("ROLE", 2);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("TEAM_ID", user_id);
		jsonObj.put("TEAM_NAME", user_name);
		jsonObj.put("OP", 1);
		jsonObj.put("ROLE", 3);
		jsonarr.put(jsonObj);
		
		return jsonarr; 
	}		
	
	//查询销售团队中的所有团队信息
	private JSONArray getNotificationListData2(HttpServletRequest request) {
		
		String sql = "SELECT A.TEAM_ID,A.TYPE AS ROLE,B.USERNAME AS TEAM_NAME FROM SALES_TEAM A LEFT JOIN USERS  B ON A.TEAM_ID=B.USER_ID  ORDER BY  A.TYPE DESC";		
		
		JSONArray jsonarr = new JSONArray();
		jsonarr = BaseDao.getRsJSONArray(sql,null);
		try {
			jsonarr.getJSONObject(0).put("OP", 1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonarr;
	}		
}
