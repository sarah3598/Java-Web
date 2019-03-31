package com.csh.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	// Date and Time Pattern Result
	// "yyyy.MM.dd G 'at' HH:mm:ss z" 2001.07.04 AD at 12:08:56 PDT
	// "EEE, MMM d, ''yy" Wed, Jul 4, '01
	// "h:mm a" 12:08 PM
	// "hh 'o''clock' a, zzzz" 12 o'clock PM, Pacific Daylight Time
	// "K:mm a, z" 0:08 PM, PDT
	// "yyyyy.MMMMM.dd GGG hh:mm aaa" 02001.July.04 AD 12:08 PM
	// "EEE, d MMM yyyy HH:mm:ss Z" Wed, 4 Jul 2001 12:08:56 -0700
	// "yyMMddHHmmssZ" 010704120856-0700
	// "yyyy-MM-dd'T'HH:mm:ss.SSSZ" 2001-07-04T12:08:56.235-0700
	// "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" 2001-07-04T12:08:56.235-07:00
	// "YYYY-'W'ww-u" 2001-W27-3

	public static String getFullDateTimeString() {
		Date date = new Date();
		String pattern = "yyyyMMddHHmmssSSSXXX";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String getDateString() {
		Date date = new Date();
		String pattern = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String getYearMonthString() {
		Date date = new Date();
		String pattern = "yyyyMM";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String getYearLastMonthString() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		String time = format.format(c.getTime());

		return time;
//		Date date = new Date();
//		String year_pattern = "yyyy";
//		String year_pattern = "yyyy";
//		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//		int temp = Integer.parseInt(sdf.format(date));
//		temp = temp - 1;
	//	return Integer.toString(temp);
	}

	public static String getTimeString() {
		Date date = new Date();
		String pattern = "HHmmss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String getDateString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String getTimastampString() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return Long.toString(timestamp.getTime());
	}

	public static String parseTimastampToDate(String timestamp) {
		String date_pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(date_pattern);
		return simpleDateFormat.format(timestamp);
	}

	public static String parseTimastampToTime(String timestamp) {
		String date_pattern = "HHmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(date_pattern);
		return simpleDateFormat.format(timestamp);
	}

	public static String parseTimastampToStandarDateTime(String timestamp) {
		String date_pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(date_pattern);
		return simpleDateFormat.format(timestamp);
	}

}
