/**
 * 
 */
package com.wbhz.flightordersystem.service;

import com.wbhz.flightordersystem.entity.Admin;
import com.wbhz.flightordersystem.exception.MyException;
import com.wbhz.jdbc.util.BaseService;

/**
 * @Description:管理员业务逻辑接口
 * @author soft01
 * @version: 1.0
 */
public interface AdminService extends BaseService {

	/**
	 * @Description:  管理员登录操作
	 * @Return Type:boolean
	 * @param account
	 * @param password
	 * @param sessionAdmin 
	 * @return
	 * @throws MyException 
	 */
	boolean login(String account, String password, ThreadLocal<Admin> sessionAdmin);

}
