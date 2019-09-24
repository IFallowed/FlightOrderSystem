/**
 * 
 */
package com.wbhz.flightordersystem.dao.impl;

import java.sql.SQLException;

import com.wbhz.flightordersystem.dao.AdminDao;
import com.wbhz.flightordersystem.dao.mapper.AdminRowMapper;
import com.wbhz.flightordersystem.entity.Admin;
import com.wbhz.jdbc.util.JdbcTemplate;

/**
 * @Description:管理员底层数据操作实现
 * @author soft01
 * @version: 1.0
 */
public class AdminDaoImpl implements AdminDao{

	/**
	 * @see cn.wbhz.flightorder.dao.AdminDao#add(com.wbhz.flightordersystem.entity.Admin)
	 * @param admin
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int add(Admin admin) throws SQLException {
		String sql = "insert into t_admin (a_id,a_account,a_password,a_adminname,a_phone) values (?,?,?,?,?)";
		int rowAffected = JdbcTemplate.insert(sql,admin.getId(),admin.getAccount(),admin.getPassword(),admin.getAdminName(),admin.getPhone());
		return rowAffected;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.AdminDao#getByKey(java.lang.String)
	 * @param key
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Admin getByKey(String key) throws SQLException {
		String sql = "select a_id,a_account,a_password,a_adminname,a_phone,a_lock,a_locktime from t_admin where a_id = ?";
		Admin admin = JdbcTemplate.selectOne(sql, new AdminRowMapper(),key);
		return admin;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.AdminDao#getByAccount(java.lang.String)
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Admin getByAccount(String account) throws SQLException {
		String sql = "select a_id,a_account,a_password,a_adminname,a_phone,a_lock,a_locktime from t_admin where a_account = ?";
		Admin admin = JdbcTemplate.selectOne(sql, new AdminRowMapper(),account);
		return admin;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.AdminDao#updateLockAndLockTimeByKey(com.wbhz.flightordersystem.entity.Admin)
	 * @param admin
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int updateLockAndLockTimeByKey(Admin admin) throws SQLException {
		String sql = "update t_admin set a_lock = ?,a_locktime = ? where a_id = ?";
		int rowAffected = JdbcTemplate.update(sql, admin.getLock(),admin.getLockTime(),admin.getId());
		return rowAffected;
	}
}
