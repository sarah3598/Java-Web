package com.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.model.manager.Manager;
import com.model.teacher.Teacher;
import com.model.user.User;

public class CommonUtil {
	//系统常量定义
	
		/** 系统名称 */
		public static final String sysName = "资源共享网";
		
		/** 系统后台名称 */
		public static final String backStageManager = "资源共享网";
		
		// 验证的正则表达式
		/** 数字正则 */
		private static final String REG_NUMBER = "^\\d+$";

		/** 整数正则 */
		private static final String REG_INTEGER = "^[-+]?[0-9]\\d*$|^0$/";

		/** 小数正则 */
		private static final String REG_FLOAT = "[-\\+]?\\d+(\\.\\d+)?$";
		
		/**上传图片地址*/
		public static final String UPLOAD_PATH = "/upload/photos/";

		
		//系统全局变量
		
		/** 退出系统*/
		public static boolean isExit=false;
		
		/**当前用户*/
		public static Manager currManager = null;//new User();
		public static User currUser = null;//new User();
		public static Teacher currTeacher=null; //new Teacher();
		public static String currUserName = "";
		
		/** 数据连接*/
		public static boolean isConn = false;
		
		//系统公用方法定义
		
		/**
		 * 获取当前日期
		 * @return
		 * @throws Exception
		 */
		public static Date getCurrentDate() throws Exception{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			Date d = new Date();
			d = sdf.parse(sdf.format(d));
			return d;		
		}
		
		/**
		 * 获取当前时间
		 * @return
		 * @throws Exception
		 */
		public static Date getCurrentDateTime(String dateStr) throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date d = new Date();
			d = sdf.parse(sdf.format(d));
			return d;
		}
		
		
		/**
		 * 从日期字符中获取日期
		 * @param dateStr
		 * @return
		 * @throws Exception
		 */
		public static Date getDateFromStr(String dateStr) throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			Date d = sdf.parse(dateStr);
			return d;
		}
		
		/**
		 * 从日期字符中获取日期时间
		 * @param dateTimeStr
		 * @return
		 * @throws Exception
		 */
		public static Date getDateTimeFromStr(String dateTimeStr) throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date d = sdf.parse(dateTimeStr);
			return d;
		}
		
		/**
		 * 获取日期字符串
		 * @param date
		 * @return
		 */
		public static String getDateStr(Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		
		/**
		 * 获取时间字符串
		 * @param date
		 * @return
		 */
		public static String getDateTimeStr(Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.format(date);
		}
		
		
		/**
		 * 判断字符串空值
		 * 
		 * @param str
		 * @return
		 */
		public static boolean isNullStr(String str) {
			if (str == null || str.trim().equals("")) {
				return true;
			}
			return false;
		}

		/**
		 * 判断字符串是否是数值
		 * @param value
		 * @return
		 */
		public static boolean isNumber(String value) {
			return Pattern.matches(REG_NUMBER, value);
		}

		/**
		 * 判断字符串是否是整数
		 * @param value
		 * @return
		 */
		public static boolean isInteger(String value) {
			return Pattern.matches(REG_INTEGER, value);
		}

		/**
		 * 判断字符串是否是小数
		 * @param value
		 * @return
		 */
		public static boolean isFloat(String value) {
			return Pattern.matches(REG_FLOAT, value);
		}
		
		/**
		 * 添加水印
		 * @param slrFileName
		 * @param sText
		 * @throws IOException
		 */
		public static void addWater(String slrFileName, String sText) throws IOException{
			//加水印(重画上传后的文件)
			Image img = new ImageIcon(slrFileName).getImage();
			int iH = img.getHeight(null);
			int iW = img.getWidth(null);
			
			BufferedImage bImg = new BufferedImage(iW,iH,BufferedImage.TYPE_3BYTE_BGR);
			
			//画板
			Graphics2D g2d = bImg.createGraphics();
			
			//画图像
			g2d.drawImage(img,0,0,null);
			
			//设置水印的颜色和字体
			g2d.setColor(Color.orange);
			g2d.setFont(new Font("华文彩云",
					Font.BOLD|Font.ITALIC,50));
			
			//加上水印文字
			//String sText="www.xxx.com";
			g2d.drawString(sText, 100, 100);
			g2d.drawString(sText, 100, 200);
			g2d.drawString(sText, 100, 300);
			
			
			//释放画板(结束图片编辑状态)
			g2d.dispose();
			
			//保存新的图片
			ImageIO.write(bImg, "jpg", new File(slrFileName));
		}
}
