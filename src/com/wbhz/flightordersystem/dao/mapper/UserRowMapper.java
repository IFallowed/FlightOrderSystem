/**
 * 
 */
package com.wbhz.flightordersystem.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wbhz.flightordersystem.entity.User;
import com.wbhz.flightordersystem.util.DateUtil;
import com.wbhz.jdbc.util.RowMapper;

/**
 * @Description:
 * @author soft01
 * @version: 1.0
 */
public class UserRowMapper implements RowMapper<User> {

	/**
	 * @see com.wbhz.jdbc.util.RowMapper#mapperObject(java.sql.ResultSet)
	 * @param arg0
	 * @return
	 * @throws SQLException
	 */
	@Override
	public User mapperObject(ResultSet arg0) throws SQLException {
		User user = new User();
		user.setId(arg0.getString("u_id"));
		user.setUserName(arg0.getString("u_username"));
		user.setAcount(arg0.getString("u_account"));
		user.setPassword(arg0.getString("u_password"));
		user.setSex(arg0.getString("u_sex"));
		user.setPhone(arg0.getString("u_phone"));
		user.setMail(arg0.getString("u_mail"));
		user.setAddress(arg0.getString("u_address"));
		user.setCreateTime(DateUtil.parseStrToDate(arg0.getString("u_createtime"), "yyyy-MM-dd HH:mm:ss"));
		user.setLock(arg0.getInt("u_lock"));
		if(null != arg0.getString("u_locktime")){
			user.setLockTime(DateUtil.parseStrToDate(arg0.getString("u_locktime"), "yyyy-MM-dd HH:mm:ss"));
		}
		else {
			user.setLockTime(null);
		}
		return user;
	}

}
