/**
 * 
 */
package com.wbhz.flightordersystem.test.dao;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

import com.wbhz.flightordersystem.dao.FlightDao;
import com.wbhz.flightordersystem.dao.impl.FlightDaoImpl;
import com.wbhz.flightordersystem.entity.Flight;
import com.wbhz.flightordersystem.util.LogUtil;

/**
 * @Description:FlightDao测试类
 * @author soft01
 * @version: 1.0
 */
public class TestFlightDao {
	FlightDao flightDao = new FlightDaoImpl();
	
	/**
	 * 
	 * @Description: 测试添加一个航班
	 * @Return Type:void
	 */
	@Test
	public void testadd() {
		Flight flight = new Flight("q10",new Date(),"2","nj","bj","10-1-2-3-4-5-6-7-8-9-10",100);
//		Flight flight = new Flight("q2",new Date(),"2","bj","nj","20-1-0-3-4-0-0-7-8-9-0-11-12-13-14-15-16-17-0-19-20",100);
		try {
			int row = flightDao.add(flight);
			if(row == 0) {
				System.out.println("航班添加失败");
			}
			else if (row == 1){
				System.out.println("航班添加成功");
			}
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Description: 测试根据主键获取航班
	 * @Return Type:void
	 */
	@Test
	public void testGetByKey() {
		String key = "q1";
		Flight flight = null;
		try {
			flight = flightDao.getByKey(key);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		System.out.println(flight);
	}
}
