package com.csh.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonService {

	/**
	 * get normal json:lxp
	 * 
	 * @param data
	 *            :输入由ResultSet转换成的JSONArray的string
	 * 
	 * @param msg
	 *            :捎带msg对象，对于页面需要显示消息时用
	 * @return
	 */
	public static String getNormalJson(String data, String msg) {
		String mormal_json = "{\"result\":";

		if (data != null) {

			mormal_json += "true";// "true" or "false"，表示返回以后，页面是否继续响应
		} else {
			mormal_json += "false";// 此处错误，强行返回失败
		}

		mormal_json += ",\"data\":";
		mormal_json += data;

		if (msg != null) {
			mormal_json += ",\"msg\":{\"show_msg\":true,\"msg_content\":" + "\"" + msg + "\"}";
		} else {
			mormal_json += ",\"msg\":{\"show_msg\":false,\"msg_content\":" + "\"" + msg + "\"}";
		}

		mormal_json += "}";

		return mormal_json;
	}

	/**
	 * get normal json:lxp
	 * 
	 * @param rs
	 *            :输入ResultSet
	 * 
	 * @param msg
	 *            :捎带msg对象，对于页面需要显示消息时用
	 * @return
	 */
	public static String getNormalJson(ResultSet rs, String msg) {
		String mormal_json = "{\"result\":";
		String rs_json = "";

		try {
			rs_json = ConvertResultSetToJson(rs);
			mormal_json += "true";// "true" or "false"，表示返回以后，页面是否继续响应
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rs_json = "";
			mormal_json += "false";// 此处错误，强行返回失败
		}

		mormal_json += ",\"data\":";
		mormal_json += rs_json;

		if (msg != null) {
			mormal_json += ",\"msg\":{\"show_msg\":true,\"msg_content\":" + "\"" + msg + "\"}";
		} else {
			mormal_json += ",\"msg\":{\"show_msg\":false,\"msg_content\":" + "\"" + msg + "\"}";
		}

		mormal_json += "}";

		return mormal_json;
	}

	/**
	 * get normal json:lxp
	 * 
	 * @param rs
	 *            :输入ResultSet
	 * @return :返回标准的json对象 for data list
	 */
	public static String getNormalJson(ResultSet rs) {
		String mormal_json = "{\"result\":";
		String rs_json = "";

		try {
			rs_json = ConvertResultSetToJson(rs);
			mormal_json += "true";// "true" or "false"，表示返回以后，页面是否继续响应
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rs_json = "";
			mormal_json += "false";// 此处错误，强行返回失败
		}

		mormal_json += ",\"data\":";
		mormal_json += rs_json;
		mormal_json += "}";

		return mormal_json;
	}

	/**
	 * get json data:lxp
	 * 
	 * @param rs
	 *            :输入ResultSet
	 * @param is_include
	 *            :是否包含data对象名， 为true包含，否则，不包含。
	 * @return 返回Json data 对象
	 */
	public static String getJsonData(ResultSet rs, Boolean is_include) {
		String json_data = "";
		String rs_json = "";

		try {
			rs_json = ConvertResultSetToJson(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rs_json = "";
		}

		if (is_include) {// 包含data对象名
			json_data = "{\"data\":";
			json_data += rs_json;
			json_data += "}";
		} else {
			json_data += rs_json;
		}

		return json_data;
	}

	/**
	 * get operate result normal json:lxp
	 * 
	 * @param result
	 *            : "true" or "false"，表示返回以后，页面是否继续响应
	 * 
	 * @param json_data
	 *            : json的data数据，format:{"key":"value",...}
	 * @param msg
	 *            : 捎带信息
	 * @return 返回标准json
	 */
	public static String getOperateResultNormalJson(String result, String json_data, String msg) {
		String mormal_json = "{\"result\":";
		mormal_json += result;
		mormal_json += ",\"data\":";
		if (json_data != null) {
			mormal_json += json_data;
		} else {
			mormal_json += "{\"success\":false}";
		}
		if (msg != null) {
			mormal_json += ",\"msg\":{\"show_msg\":true,\"msg_content\":" + "\"" + msg + "\"}";
		} else {
			mormal_json += ",\"msg\":{\"show_msg\":false,\"msg_content\":" + "\"" + msg + "\"}";
		}
		mormal_json += "}";
		return mormal_json;
	}

	/**
	 * @param emsg
	 *            :返回错误消息
	 * @param json_data
	 *            :json数据
	 * @return
	 */
	public static String getErrorMsgNormalJson(String emsg) {
		String mormal_json = "{\"result\":";
		mormal_json += "false";
		
		if (emsg != null) {
			mormal_json += ",\"emsg\":" + "\"" + emsg + "\"";
		} else {
			mormal_json += ",\"emsg\":false";
		}
		mormal_json += "}";
		return mormal_json;
	}

	/**
	 * convert key,value to json obj string:lxp
	 * 
	 * @param key
	 *            :输入字段名称
	 * @param val
	 *            :输入字段的值
	 * @return : 返回转换后的json字符串
	 */
	public static String convertKeyValToJsonObject(String key, String val) {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put(key, val);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObj.toString();
	}

	// execute status json
	public static String StatusJson(int flag) {

		JSONObject jsonObj = new JSONObject();
		String columnName = "status";
		String value = (flag > 0) ? "1" : "0";

		try {
			jsonObj.put(columnName, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj.toString();
	}

	// execute result json
	public static String ResultJson(String bool, String key, String val) {
		JSONObject jsonObj = new JSONObject();

		try {
			jsonObj.put("flag", bool);
			jsonObj.put(key, val);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = "{\"result\":{\"success\":\"" + bool + "\"},\"data\":";
		json += jsonObj.toString();
		json += "}";
		return json;
	}

	// convert resultset to json string
	public static String ConvertResultSetToJson(ResultSet rs) throws SQLException {
		// define a json array
		JSONArray array = new JSONArray();

		// get raw data
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		// each row
		try {
			while (rs.next()) {
				JSONObject jsonObj = new JSONObject();

				// each column
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnLabel(i);
					String value = rs.getString(columnName);
					jsonObj.put(columnName, value);
				}
				array.put(jsonObj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array.toString();
	}

	// convert resultset to json array
	public static JSONArray ConvertResultSetToJsonArray(ResultSet rs) throws SQLException {
		// define a json array
		JSONArray array = new JSONArray();

		// get raw data
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		// each row
		try {
			while (rs.next()) {
				JSONObject jsonObj = new JSONObject();

				// each column
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnLabel(i);
					
					//过滤掉密码字段
					if(columnName.equals("PWD"))
						continue;
					String value = rs.getString(columnName);
					jsonObj.put(columnName, value);
				}
				array.put(jsonObj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}

	// response to ajax request, return json
	public static void ResponseJson(HttpServletRequest request, HttpServletResponse response, String json) throws ServletException, IOException {
		// response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(json);
	}

}
