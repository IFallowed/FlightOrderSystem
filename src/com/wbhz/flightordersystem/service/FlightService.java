/**
 * 
 */
package com.wbhz.flightordersystem.service;

import java.util.List;

import com.wbhz.flightordersystem.entity.Flight;
import com.wbhz.jdbc.util.BaseService;

/**
 * @Description:航班业务逻辑接口
 * @author soft01
 * @version: 1.0
 */
public interface FlightService extends BaseService{

	/**
	 * 
	 * @Description: 根据航班号查询航班
	 * @Return Type:Flight
	 * @param flight
	 * @return 
	 */
	Flight queryByFlightId(String flightId);

	/**
	 * 
	 * @Description: 多条件查询航班
	 * @Return Type:List<Flight>
	 * @param startPlace 出发地
	 * @param endPlace 目的地
	 * @param takeOffTime 出发日期
	 * @return
	 */
	List<Flight> queryByCondition(String startPlace,String endPlace,String takeOffTime);

	/**
	 * 
	 * @Description: 根据航班号删除航班
	 * @Return Type:void
	 * @param flightId 
	 */
	boolean delByFlightId(String flightId);


	/**
	 * @Description: 根据航班号修改航班
	 * @Return Type:void
	 * @param flightId
	 * @param takeOffTime 
	 * 
	 */
	boolean updateByFlightId(String flightId, String takeOffTime);

	/**
	 * @Description: 根据航班号添加航班
	 * @Return Type:void
	 * @param flight  
	 * 
	 */
	boolean insertByFlightId(Flight flight);
	
	/**
	 * 
	 * @Description: 查找所有航班
	 * @Return Type:List<Flight>
	 * @return 
	 *
	 */
	List<Flight> queryAll();
}
