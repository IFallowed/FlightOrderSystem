/**
 * 
 */
package com.wbhz.flightordersystem.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wbhz.flightordersystem.entity.Admin;
import com.wbhz.flightordersystem.util.DateUtil;
import com.wbhz.jdbc.util.RowMapper;

/**
 * @Description: 管理员实体的行映射器
 * @author soft01
 * @version: 1.0
 */
public class AdminRowMapper implements RowMapper<Admin> {

	/**
	 * @see com.wbhz.jdbc.util.RowMapper#mapperObject(java.sql.ResultSet)
	 * @param arg0
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Admin mapperObject(ResultSet arg0) throws SQLException {
		Admin admin = new Admin();
		admin.setId(arg0.getString("a_id"));
		admin.setAdminName(arg0.getString("a_adminname"));
		admin.setAccount(arg0.getString("a_account"));
		admin.setPassword(arg0.getString("a_password"));
		admin.setPhone(arg0.getString("a_phone"));
		admin.setLock(arg0.getInt("a_lock"));
		if(null != arg0.getString("a_locktime")){
			admin.setLockTime(DateUtil.parseStrToDate(arg0.getString("a_locktime"), "yyyy-MM-dd HH:mm:ss"));
		}
		else {
			admin.setLockTime(null);
		}
		return admin;
	}

}
