/**
 * 
 */
package com.wbhz.flightordersystem.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.wbhz.flightordersystem.dao.OrderDao;
import com.wbhz.flightordersystem.dao.mapper.OrderRowMapper;
import com.wbhz.flightordersystem.entity.Order;
import com.wbhz.jdbc.util.JdbcTemplate;

/**
 * @Description: 订单底层数据操作实现
 * @author soft02
 * @version 1.0
 */
public class OrderDaoImpl implements OrderDao {

	/**
	 * @see cn.wbhz.flightorder.dao.OrderDao#add(cn.wbhz.flightorder.entity.Order)
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int add(Order order) throws SQLException {
		String sql = "insert into t_order (o_orderid,o_flightid,o_userid,o_seat,o_state) values (?,?,?,?,?)";
		int rowAffected = JdbcTemplate.insert(sql, order.getOrderId(),order.getFlightId(),order.getUserId(),order.getSeat(),order.getState());
		return rowAffected;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.OrderDao#getByKey(java.lang.String)
	 * @param key
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Order getByKey(String key) throws SQLException {
		String sql = "select o_orderid,o_flightid,o_userid,o_seat,o_state from t_order where o_orderid = ?";
		Order order = JdbcTemplate.selectOne(sql, new OrderRowMapper(), key);
		return order;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.OrderDao#getByUserId(java.lang.String)
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Order> getByUserId(String id) throws SQLException {
		String sql = "select o_orderid,o_flightid,o_userid,o_seat,o_state from t_order where o_userid = ?";
		List<Order> orders = JdbcTemplate.selectList(sql, new OrderRowMapper(), id);
		return orders;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.OrderDao#updateStateByKey(cn.wbhz.flightorder.entity.Order)
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int updateStateByKey(Order order) throws SQLException {
		String sql ="update t_order set o_state = ? where o_orderid = ?";
		int rowAffected = JdbcTemplate.update(sql, order.getState(),order.getOrderId());
		return rowAffected;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.OrderDao#updateByKey(cn.wbhz.flightorder.entity.Order)
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int updateByKey(Order order) throws SQLException {
		String sql ="update t_order set o_flightid = ?,o_userid = ?,o_seat = ?,o_state = ? where o_orderid = ?";
		int rowAffected = JdbcTemplate.update(sql, order.getFlightId(),order.getUserId(),order.getSeat(),order.getState(),order.getOrderId());
		return rowAffected;
	}

}
