/**
 * 
 */
package com.wbhz.flightordersystem.dao;

import java.sql.SQLException;

import com.wbhz.flightordersystem.entity.Admin;
/**
 * @Description:管理员底层数据操作接口
 * @author soft01
 * @version: 1.0
 */
public interface AdminDao {
	/**
	 * 
	 * @Description: 添加一个管理员
	 * @Return Type:void
	 * @param admin
	 * @throws SQLException 
	 */
	int add(Admin admin) throws SQLException;
	
	/**
	 * 
	 * @Description: 根据主键获取一条管理员记录
	 * @Return Type:Admin
	 * @param key
	 * @return
	 * @throws SQLException
	 */
	Admin getByKey(String key) throws SQLException;
	
	/**
	 * @Description: 根据帐号获取一条管理员记录
	 * @Return Type:User
	 * @param account
	 * @return
	 * @throws SQLException 
	 */
	Admin getByAccount(String account) throws SQLException;
	
	/**
	 * @Description: 根据主键修改锁与锁时间
	 * @Return Type:void
	 * @param user 
	 * @throws SQLException 
	 */
	int updateLockAndLockTimeByKey(Admin admin) throws SQLException ;
}
