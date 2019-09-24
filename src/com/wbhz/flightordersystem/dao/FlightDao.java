/**
 * 
 */
package com.wbhz.flightordersystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.wbhz.flightordersystem.entity.Flight;
import com.wbhz.flightordersystem.exception.MyException;

/**
 * @Description: 航班底层数据操作接口
 * @author soft01
 * @version: 1.0
 */
public interface FlightDao {
	/**
	 * 
	 * @Description: 添加一个航班记录
	 * @Return Type:void
	 * @param flight
	 * @throws SQLException
	 */
	int add(Flight flight) throws SQLException;
	/**
	 * 
	 * @Description: 根据主键获取一个航班记录
	 * @Return Type:Flight
	 * @param key
	 * @return
	 * @throws SQLException
	 */
	Flight getByKey(String key) throws SQLException;

	/**
	 * 
	 * @Description: 获取所有航班记录
	 * @Return Type:List<Flight>
	 * @return 
	 * @throws SQLException 
	 *
	 */
	List<Flight> getAll() throws SQLException;
	
	/**
	 * 
	 * @Description: 多条件查询航班
	 * @Return Type:List<Flight>
	  * @param startPlace 出发地
	 * @param endPlace 目的地
	 * @param takeOffTime 出发日期
	 * @return
	 * @throws SQLException
	 */
	List<Flight> getByCondition(String startPlace, String endPlace, String takeOffTime) throws SQLException;


	/**
	 * 
	 * @Description: 根据主键删除航班
	 * @Return Type:void
	 * @param key
	 * @throws SQLException 
	 *
	 */
	int delByKey(String key) throws SQLException;
	
	/**
	 * @Description: 根据主键修改航班出发时间
	 * @Return Type:void
	 * @param flight 
	 * @throws SQLException 
	 */
	int updateTakeOffTimeByKey(Flight flight) throws SQLException;
	/**
	 * @Description: 根据主键修改航班余票
	 * @Return Type void
	 * @param flight
	 * @throws SQLException 
	 * @throws MyException 
	 */
	int updateTicketsByKey(Flight flight) throws SQLException;
}
