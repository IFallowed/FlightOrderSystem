/**
 * 
 */
package com.wbhz.flightordersystem.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.wbhz.flightordersystem.dao.UserDao;
import com.wbhz.flightordersystem.dao.mapper.UserRowMapper;
import com.wbhz.flightordersystem.entity.User;
import com.wbhz.flightordersystem.util.StringUtil;
import com.wbhz.jdbc.util.JdbcTemplate;

/**
 * @Description:会员底层数据操作实现
 * @author soft01
 * @version: 1.0
 */
public class UserDaoImpl implements UserDao {

	/**
	 * @see cn.wbhz.flightorder.dao.UserDao#getAll()
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<User> getAll() throws SQLException {
		String sql = "select u_id,u_username,u_account,u_password,u_sex,u_phone,u_mail,u_address,u_createtime,u_lock,u_locktime from t_user";
		List<User> users = JdbcTemplate.selectList(sql, new UserRowMapper());
		return users;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.UserDao#add(cn.wbhz.flightorder.entity.User)
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int add(User user) throws SQLException {
		String sql = "insert into t_user (u_id,u_username,u_account,u_password,u_sex,u_phone,u_mail,u_address,u_createtime) values (?,?,?,?,?,?,?,?,?)";
		int rowAffected = JdbcTemplate.insert(sql, user.getId(),user.getUserName(),user.getAcount(),user.getPassword(),user.getSex(),user.getPhone(),user.getMail(),user.getAddress(),user.getCreateTime());
		return rowAffected;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.UserDao#getByAccount(java.lang.String)
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	@Override
	public User getByAccount(String account) throws SQLException {
		String sql = "select u_id,u_username,u_account,u_password,u_sex,u_phone,u_mail,u_address,u_createtime,u_lock,u_locktime from t_user where u_account = ?";
		User user = JdbcTemplate.selectOne(sql, new UserRowMapper(), account);
		return user;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.UserDao#updateLockAndLockTimeByKey(cn.wbhz.flightorder.entity.User)
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int updateLockAndLockTimeByKey(User user) throws SQLException {
		String sql = "update t_user set u_lock = ?,u_locktime = ? where u_id = ?";
		int rowAffected = JdbcTemplate.update(sql, user.getLock(),user.getLockTime(),user.getId());
		return rowAffected;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.UserDao#updateByKey(cn.wbhz.flightorder.entity.User)
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int updateByKey(User user) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("update t_user set ");
		if(!StringUtil.isEmpty(user.getAcount())){
			sql.append("u_account = ").append("'")
			   .append(user.getAcount())
			   .append("',");
		}
		if(!StringUtil.isEmpty(user.getPassword())){
			sql.append("u_password = ").append("'")
			   .append(user.getPassword())
			   .append("',");
		}
		if(!StringUtil.isEmpty(user.getSex())){
			sql.append("u_sex = ").append("'")
			   .append(user.getSex())
			   .append("',");
		}
		if(!StringUtil.isEmpty(user.getPhone())){
			sql.append("u_phone = ").append("'")
			   .append(user.getPhone())
			   .append("',");
		}
		if(!StringUtil.isEmpty(user.getMail())){
			sql.append("u_mail = ").append("'")
			   .append(user.getMail())
			   .append("',");
		}
		if(!StringUtil.isEmpty(user.getAddress())){
			sql.append("u_address = ").append("'")
			   .append(user.getAddress())
			   .append("',");
		}
		sql.delete(sql.length() - 1, sql.length());
		sql.append("where u_id = ").append("'")
		   .append(user.getId())
		   .append("'");
		int rowAffected = JdbcTemplate.update(sql.toString());
		return rowAffected;
	}
}
