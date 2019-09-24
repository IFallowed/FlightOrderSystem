/**
 * 
 */
package com.wbhz.flightordersystem.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wbhz.flightordersystem.entity.Order;
import com.wbhz.jdbc.util.RowMapper;

/**
 * @Description: 订单的行映射器
 * @author soft02
 * @version 1.0
 */
public class OrderRowMapper implements RowMapper<Order> {

	/**
	 * @see com.wbhz.jdbc.util.RowMapper#mapperObject(java.sql.ResultSet)
	 * @param arg0
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Order mapperObject(ResultSet arg0) throws SQLException {
		Order order = new Order();
		order.setOrderId(arg0.getString("o_orderid"));
		order.setFlightId(arg0.getString("o_flightid"));
		order.setUserId(arg0.getString("o_userid"));
		order.setSeat(arg0.getString("o_seat"));
		order.setState(arg0.getString("o_state"));
		return order;
	}
}
