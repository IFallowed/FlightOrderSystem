/**
 * 
 */
package com.wbhz.flightordersystem.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wbhz.flightordersystem.entity.Flight;
import com.wbhz.flightordersystem.util.DateUtil;
import com.wbhz.jdbc.util.RowMapper;

/**
 * @Description: 航班实体的行映射器
 * @author soft01
 * @version: 1.0
 */
public class FlightRowMapper implements RowMapper<Flight> {

	/**
	 * @see com.wbhz.jdbc.util.RowMapper#mapperObject(java.sql.ResultSet)
	 * @param arg0
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Flight mapperObject(ResultSet arg0) throws SQLException {
		Flight flight = new Flight();
		flight.setFlightId(arg0.getString("f_flightid"));
		flight.setTakeOffTime(DateUtil.parseStrToDate(arg0.getString("f_takeofftime"), "yyyy-MM-dd HH:mm:ss"));
		flight.setFlyingTime(arg0.getString("f_flyingtime"));
		flight.setStartPlace(arg0.getString("f_startplace"));
		flight.setEndPlace(arg0.getString("f_endplace"));
		flight.setTickets(arg0.getString("f_tickets"));
		flight.setPrice(arg0.getFloat("f_price"));
		return flight;
	}

}
