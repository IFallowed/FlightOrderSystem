/**
 * 
 */
package com.wbhz.flightordersystem.service.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.wbhz.flightordersystem.dao.FlightDao;
import com.wbhz.flightordersystem.entity.Flight;
import com.wbhz.flightordersystem.exception.MyException;
import com.wbhz.flightordersystem.service.FlightService;
import com.wbhz.flightordersystem.util.DateUtil;
import com.wbhz.flightordersystem.util.LogUtil;

/**
 * @Description:航班业务逻辑操作实现
 * @author soft01
 * @version: 1.0
 */
public class FlightServiceImpl implements FlightService {

	private FlightDao flightDao;
	
	/**
	 * @see com.wbhz.flightordersystem.service.FlightService#queryByFlightId(java.lang.String)
	 * @param flightId
	 * @return
	 * @throws MyException 
	 */
	@Override
	public Flight queryByFlightId(String flightId) {
		Flight flight = null;
		try {
			flight = flightDao.getByKey(flightId);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		return flight;
	}

	public void setFlightDao(FlightDao flightDao) {
		this.flightDao = flightDao;
	}

	/**
	 * @see com.wbhz.flightordersystem.service.FlightService#queryByCondition(java.lang.String, java.lang.String, java.lang.String)
	 * @param startPlace
	 * @param endPlace
	 * @param takeOffTime
	 * @return
	 */
	@Override
	public List<Flight> queryByCondition(String startPlace, String endPlace, String takeOffTime){
		List<Flight> flights = null;
		try {
			flights = flightDao.getByCondition(startPlace, endPlace, takeOffTime);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		return flights;
	}

	/**
	 * @see com.wbhz.flightordersystem.service.FlightService#delByFlightId(java.lang.String)
	 * @param flightId
	 * @return 
	 * @throws MyException 
	 * @throws MyException 
	 */
	@Override
	public boolean delByFlightId(String flightId) {
		boolean flag = false;
		//先通过航班号找到此航班
		Flight flight = null;
		try {
			flight = flightDao.getByKey(flightId);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		if(null != flight) {
			//如果此航班中有已订票的乘客，则不能删除
			if(!flight.getTickets().contains("-0-")) {
				try {
					flightDao.delByKey(flightId);
					flag = true;
				} catch (SQLException e) {
					LogUtil.error(e.getMessage());
					flag = false;
				} 
			}
			else {
				System.out.println("此航班已有乘客订票，不能删除");
				flag = false;
			}
		}
		else {
			System.out.println("无效航班号");
			flag = false;
		}
		return flag;
	}

	/**
	 * @see com.wbhz.flightordersystem.service.FlightService#updateByFlightId(java.lang.String, java.lang.String)
	 * @param flightId
	 * @param takeOffTime
	 * @return 
	 */
	@Override
	public boolean updateByFlightId(String flightId, String takeOffTime) {
		boolean flag = false;
		Flight flight = new Flight();
		flight.setFlightId(flightId);
		flight.setTakeOffTime(DateUtil.parseStrToDate(takeOffTime, "yyyy-MM-dd|HH:mm:ss"));
		try {
			if(null == flight.getTakeOffTime()) {
				throw new SQLException("日期格式错误");
			}
			flightDao.updateTakeOffTimeByKey(flight); 
			flag = true;
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
			System.out.println("无效航班号或出发时间");
			flag = false;
		}
		return flag;
	}

	/**
	 * @see com.wbhz.flightordersystem.service.FlightService#insertByFlightId(com.wbhz.flightordersystem.entity.Flight)
	 * @param flight
	 * @return 
	 */
	@Override
	public boolean insertByFlightId(Flight flight) {
		boolean flag = false;
		//获取已存储的所有航班
		List<Flight> flights = null;
		try {
			flights = flightDao.getAll();
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
			flag = false;
		}
		//遍历所有航班
		Iterator<Flight> iterator = flights.iterator();
		while (iterator.hasNext()) {
			Flight rsflight = iterator.next();
			//如果新添加的航班的航班号已存在，向外抛出异常
			if(flight.getFlightId().equals(rsflight.getFlightId())) {
				System.out.println("已有此航班号，请添加新的航班");
				flag = false;
			}
			//如果新添加的航班的出发时间已存在，向外抛出异常
			if(DateUtil.parseDateToStr(flight.getTakeOffTime(), "yyyy-MM-dd|HH:mm:ss").equals(DateUtil.parseDateToStr(rsflight.getTakeOffTime(), "yyyy-MM-dd|HH:mm:ss"))) {
				System.out.println("此时间已有航班起飞，请更换出发时间");
				flag = false;
			}
			continue;
		}
		//如果航班号和出发时间都是新数据，就将此航班保存到数据库中
		try {
			flightDao.add(flight);
			flag = true;
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
			flag = false;
		}
		return flag;
	}

	/**
	 * @see com.wbhz.flightordersystem.service.FlightService#queryAll()
	 * @return
	 * @throws MyException 
	 */
	@Override
	public List<Flight> queryAll() {
		List<Flight> flights = null;
		try {
			flights = flightDao.getAll();
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		return flights;
	}
}
