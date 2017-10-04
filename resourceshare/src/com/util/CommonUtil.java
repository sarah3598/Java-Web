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
	//ϵͳ��������
	
		/** ϵͳ���� */
		public static final String sysName = "��Դ������";
		
		/** ϵͳ��̨���� */
		public static final String backStageManager = "��Դ������";
		
		// ��֤��������ʽ
		/** �������� */
		private static final String REG_NUMBER = "^\\d+$";

		/** �������� */
		private static final String REG_INTEGER = "^[-+]?[0-9]\\d*$|^0$/";

		/** С������ */
		private static final String REG_FLOAT = "[-\\+]?\\d+(\\.\\d+)?$";
		
		/**�ϴ�ͼƬ��ַ*/
		public static final String UPLOAD_PATH = "/upload/photos/";

		
		//ϵͳȫ�ֱ���
		
		/** �˳�ϵͳ*/
		public static boolean isExit=false;
		
		/**��ǰ�û�*/
		public static Manager currManager = null;//new User();
		public static User currUser = null;//new User();
		public static Teacher currTeacher=null; //new Teacher();
		public static String currUserName = "";
		
		/** ��������*/
		public static boolean isConn = false;
		
		//ϵͳ���÷�������
		
		/**
		 * ��ȡ��ǰ����
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
		 * ��ȡ��ǰʱ��
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
		 * �������ַ��л�ȡ����
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
		 * �������ַ��л�ȡ����ʱ��
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
		 * ��ȡ�����ַ���
		 * @param date
		 * @return
		 */
		public static String getDateStr(Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		
		/**
		 * ��ȡʱ���ַ���
		 * @param date
		 * @return
		 */
		public static String getDateTimeStr(Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.format(date);
		}
		
		
		/**
		 * �ж��ַ�����ֵ
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
		 * �ж��ַ����Ƿ�����ֵ
		 * @param value
		 * @return
		 */
		public static boolean isNumber(String value) {
			return Pattern.matches(REG_NUMBER, value);
		}

		/**
		 * �ж��ַ����Ƿ�������
		 * @param value
		 * @return
		 */
		public static boolean isInteger(String value) {
			return Pattern.matches(REG_INTEGER, value);
		}

		/**
		 * �ж��ַ����Ƿ���С��
		 * @param value
		 * @return
		 */
		public static boolean isFloat(String value) {
			return Pattern.matches(REG_FLOAT, value);
		}
		
		/**
		 * ���ˮӡ
		 * @param slrFileName
		 * @param sText
		 * @throws IOException
		 */
		public static void addWater(String slrFileName, String sText) throws IOException{
			//��ˮӡ(�ػ��ϴ�����ļ�)
			Image img = new ImageIcon(slrFileName).getImage();
			int iH = img.getHeight(null);
			int iW = img.getWidth(null);
			
			BufferedImage bImg = new BufferedImage(iW,iH,BufferedImage.TYPE_3BYTE_BGR);
			
			//����
			Graphics2D g2d = bImg.createGraphics();
			
			//��ͼ��
			g2d.drawImage(img,0,0,null);
			
			//����ˮӡ����ɫ������
			g2d.setColor(Color.orange);
			g2d.setFont(new Font("���Ĳ���",
					Font.BOLD|Font.ITALIC,50));
			
			//����ˮӡ����
			//String sText="www.xxx.com";
			g2d.drawString(sText, 100, 100);
			g2d.drawString(sText, 100, 200);
			g2d.drawString(sText, 100, 300);
			
			
			//�ͷŻ���(����ͼƬ�༭״̬)
			g2d.dispose();
			
			//�����µ�ͼƬ
			ImageIO.write(bImg, "jpg", new File(slrFileName));
		}
}
