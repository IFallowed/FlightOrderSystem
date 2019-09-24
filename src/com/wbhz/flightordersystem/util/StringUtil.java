package com.wbhz.flightordersystem.util;

public class StringUtil {
	public static boolean isEmpty(String str){
		if(null == str || "".equals(str.trim()) || "null".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
}
