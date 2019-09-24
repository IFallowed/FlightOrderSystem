/**
 * 
 */
package com.wbhz.flightordersystem.util;

import org.apache.log4j.Logger;

/**
 * @Description:日志工具类
 * @author soft01
 * @version: 1.0
 */
public class LogUtil {
	private static Logger logger = Logger.getLogger(LogUtil.class);
	
	/**
	 * 
	 * @Description: 获取日志输出对象
	 * @Return Type:Logger
	 * @return 
	 *
	 */
	private static Logger getLogger() {
//		return Logger.getLogger(findCaller().getClassName());
		return logger;
    }
	
//	/**
//	 * 
//	 * @Description: 返回当前类的信息
//	 * @Return Type:StackTraceElement
//	 * @return 
//	 *
//	 */
//    private static StackTraceElement findCaller() {
//        // 获取堆栈信息
//        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
// 
//        // 最原始被调用的堆栈信息
//        StackTraceElement caller = null;
// 
//        // 日志类名称
// 
//        String logClassName = LogUtil.class.getName();
//        // 循环遍历到日志类标识
// 
//        int i = 0;
//        for (int len = callStack.length; i < len; i++) {
//        	System.out.println(callStack[i]);
//        	// 从栈的最上开始 往下找 找到第一个不为Log和线程的类
//            if (logClassName.equals(callStack[i].getClassName())) {
//                break;
//            }
//        }
//        caller = callStack[i + 3];
//        return caller;
//    }
	
    public static void trace(String msg) {
        getLogger().trace(msg);
    }
 
    public static void debug(String msg) {
        getLogger().debug(msg);
    }
 
    public static void info(String msg) {
        getLogger().info(msg);
    }
 
    public static void warn(String msg) {
        getLogger().warn(msg);
    }
 
    public static void error(String msg) {
        getLogger().error(msg);
    }
 
    public static void error(String msg,Throwable t) {
        getLogger().error(msg,t);
    }
}
