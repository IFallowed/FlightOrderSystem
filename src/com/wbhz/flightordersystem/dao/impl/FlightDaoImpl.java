/**
 * 
 */
package com.wbhz.flightordersystem.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.wbhz.flightordersystem.dao.FlightDao;
import com.wbhz.flightordersystem.dao.mapper.FlightRowMapper;
import com.wbhz.flightordersystem.entity.Flight;
import com.wbhz.flightordersystem.util.StringUtil;
import com.wbhz.jdbc.util.JdbcTemplate;

/**
 * @Description:航班底层数据操作实现
 * @author soft01
 * @version: 1.0
 */
public class FlightDaoImpl implements FlightDao {

	/**
	 * @see cn.wbhz.flightorder.dao.FlightDao#add(cn.wbhz.flightorder.entity.Flight)
	 * @param flight
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int add(Flight flight) throws SQLException {
		String sql = "insert into t_flight (f_flightid,f_takeofftime,f_flyingtime,f_startplace,f_endplace,f_tickets,f_price) values (?,?,?,?,?,?,?)";
		int rowAffected = JdbcTemplate.insert(sql, flight.getFlightId(),flight.getTakeOffTime(),flight.getFlyingTime(),flight.getStartPlace(),flight.getEndPlace(),flight.getTickets(),flight.getPrice());
		return rowAffected;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.FlightDao#getByKey(java.lang.String)
	 * @param key
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Flight getByKey(String key) throws SQLException {
		String sql = "select f_flightid,f_takeofftime,f_flyingtime,f_startplace,f_endplace,f_tickets,f_price from t_flight where f_flightid = ?";
		Flight flight = JdbcTemplate.selectOne(sql, new FlightRowMapper(),key);
		return flight;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.FlightDao#getAll()
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Flight> getAll() throws SQLException {
		String sql = "select f_flightid,f_takeofftime,f_flyingtime,f_startplace,f_endplace,f_tickets,f_price from t_flight";
		List<Flight> flights = JdbcTemplate.selectList(sql, new FlightRowMapper());
		return flights;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.FlightDao#getByCondition(java.lang.String, java.lang.String, java.lang.String)
	 * @param startPlace
	 * @param endPlace
	 * @param takeOffTime
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Flight> getByCondition(String startPlace, String endPlace, String takeOffTime) throws SQLException {
		StringBuffer sql =new StringBuffer();
		sql.append("select f_flightid,f_takeofftime,f_flyingtime,f_startplace,f_endplace,f_tickets,f_price from t_flight where 1=1");
		//如果出发地不为空，则在sql字符串后加上出发地条件约束
		if(!StringUtil.isEmpty(startPlace)){
			sql.append(" and f_startplace = '")
			   .append(startPlace).append("'");
		}
		//如果目的地不为空，则在sql字符串后加上目的地条件约束
		if(!StringUtil.isEmpty(endPlace)){
			sql.append(" and f_endplace = '")
			   .append(endPlace).append("'");
		}
		//如果出发日期不为空，则在sql字符串后加上出发日期条件约束
		if(!StringUtil.isEmpty(takeOffTime)){
			takeOffTime = takeOffTime.replace("|", " ");
			sql.append(" and f_takeofftime like '%")
			   .append(takeOffTime)
			   .append("%'");
		}
		List<Flight> flights = JdbcTemplate.selectList(sql.toString(), new FlightRowMapper());
		return flights;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.FlightDao#delByKey(java.lang.String)
	 * @param key
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int delByKey(String key) throws SQLException {
		String sql = "delete from t_flight where f_flightid = ?";
		int rowAffected = JdbcTemplate.delete(sql, key);
		return rowAffected;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.FlightDao#updateTakeOffTimeByKey(cn.wbhz.flightorder.entity.Flight)
	 * @param flight
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int updateTakeOffTimeByKey(Flight flight) throws SQLException {
		String sql = "update t_flight set f_takeofftime = ? where f_flightid = ?";
		int rowAffected = JdbcTemplate.update(sql, flight.getTakeOffTime(),flight.getFlightId());
		return rowAffected;
	}

	/**
	 * @see cn.wbhz.flightorder.dao.FlightDao#updateTicketsByKey(cn.wbhz.flightorder.entity.Flight)
	 * @param flight
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int updateTicketsByKey(Flight flight) throws SQLException {
		String sql = "update t_flight set f_tickets = ? where f_flightid = ?";
		int rowAffected = JdbcTemplate.update(sql, flight.getTickets(),flight.getFlightId());
		return rowAffected;
	}
}
