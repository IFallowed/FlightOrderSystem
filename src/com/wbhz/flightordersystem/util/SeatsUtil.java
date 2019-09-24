/**
 * 
 */
package com.wbhz.flightordersystem.util;

/**
 * @Description: 座位解析类
 * @author soft02
 * @version 1.0
 */
public class SeatsUtil {
	
	public static String[] string2Array(String str){
		String[] seats = str.split("-");
		return seats;
	}
	
	public static String array2String(String[] strs){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			sb.append(strs[i]).append("-");
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}
	
	public static String parseSeats(String tickets) {
		StringBuffer seats = new StringBuffer();
		seats.append(tickets).append("-");
		for (int i = 1; i <= Integer.parseInt(tickets); i++) {
			seats.append(i).append("-");
		}
		seats.delete(seats.length() - 1, seats.length());
		return seats.toString();
	}
}
