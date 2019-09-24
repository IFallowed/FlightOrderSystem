package com.wbhz.flightordersystem.exception;

/**
 * 
 * @Description:自定义异常类
 * @author soft01
 * @version: 1.0
 */
@SuppressWarnings("serial")
public class MyException extends Exception {

	public MyException(String msg){
		super(msg);
	}
}
