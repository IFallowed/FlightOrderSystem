/**
 * 
 */
package com.wbhz.flightordersystem.service;

import com.wbhz.flightordersystem.entity.User;
import com.wbhz.flightordersystem.exception.MyException;
import com.wbhz.jdbc.util.BaseService;

/**
 * @Description:会员业务逻辑接口
 * @author soft01
 * @version: 1.0
 */
public interface UserService extends BaseService {

	/**
	 * @Description: 会员注册
	 * @Return Type:boolean
	 * @param user
	 * @return 
	 * 
	 */
	boolean register(User user);

	/**
	 * @Description: 
	 * @Return Type:boolean
	 * @param account
	 * @param password
	 * @param sessionUser
	 * @return 
	 * 
	 */
	boolean login(String account, String password, ThreadLocal<User> sessionUser);

	/**
	 * @Description: 修改用户信息
	 * @Return Type void
	 * @param user
	 * @throws MyException 
	 */
	void updateUser(User user) throws MyException;
	

}
