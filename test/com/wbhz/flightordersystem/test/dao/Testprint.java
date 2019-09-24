/**
 * 
 */
package com.wbhz.flightordersystem.test.dao;

import org.junit.Test;

import com.wbhz.flightordersystem.dao.FlightDao;
import com.wbhz.flightordersystem.dao.impl.FlightDaoImpl;
import com.wbhz.flightordersystem.entity.Flight;

/**
 * @Description:
 * @author soft01
 * @version: 1.0
 */
public class Testprint {
	/**
	 * 
	 * @Description: 测试某一航班座位的打印
	 * @Return Type:void
	 * @throws Exception
	 */
	@Test
	public void testprint() throws Exception {
		FlightDao flightDao = new FlightDaoImpl();
		Flight flight = flightDao.getByKey("q1");
		System.out.println(flight);
		System.out.println(flight.printSeats());
	}
}
