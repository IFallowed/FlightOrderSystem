/**
 * 
 */
package com.wbhz.flightordersystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.wbhz.flightordersystem.entity.User;
import com.wbhz.flightordersystem.exception.MyException;

/**
 * @Description:会员底层数据操作接口
 * @author soft01
 * @version: 1.0
 */
public interface UserDao {

	/**
	 * @Description: 获取所有会员记录
	 * @Return Type:List<User>
	 * @return
	 * @throws SQLException 
	 */
	List<User> getAll() throws SQLException;

	/**
	 * @Description: 添加一条会员记录
	 * @Return Type:void
	 * @param user
	 * @throws SQLException  
	 */
	int add(User user) throws SQLException;

	/**
	 * @Description: 根据用户名查找用户
	 * @Return Type:User
	 * @param account
	 * @return
	 * @throws SQLException 
	 */
	User getByAccount(String account) throws SQLException;


	/**
	 * @Description: 根据主键修改锁与锁时间
	 * @Return Type:void
	 * @param user
	 * @throws SQLException 
	 * @throws MyException 
	 */
	int updateLockAndLockTimeByKey(User user) throws SQLException;

	/**
	 * @Description: 根据主键修改用用户信息
	 * @Return Type void
	 * @param user
	 * @throws SQLException 
	 * @throws MyException 
	 */
	int updateByKey(User user) throws SQLException;


}
