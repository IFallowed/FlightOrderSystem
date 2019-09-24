/**
 * 
 */
package com.wbhz.flightordersystem.test.dao;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

import com.wbhz.flightordersystem.dao.OrderDao;
import com.wbhz.flightordersystem.dao.impl.OrderDaoImpl;
import com.wbhz.flightordersystem.entity.Order;
import com.wbhz.flightordersystem.util.DateUtil;
import com.wbhz.flightordersystem.util.LogUtil;

/**
 * @Description: OrderDao测试类
 * @author soft02
 * @version 1.0
 */
public class TestOrderDao {
	private OrderDao orderDao = new OrderDaoImpl();

	/**
	 * 
	 * @Description: 测试添加订单
	 * @Return Type void
	 */
	@Test
	public void testAdd() {
		Order order = new Order(DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmss"), "q1", "12341234123412", "1","已预订");
		try {
			int row = orderDao.add(order);
			if(row == 0) {
				System.out.println("机票添加失败");
			}
			else if (row == 1){
				System.out.println("机票添加成功");
			}
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Description:测试根据主键获取订单 
	 * @Return Type void
	 */
	@Test
	public void testGetByKey() {
		Order order = null;
		try {
			order = orderDao.getByKey("20190919010501");
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		System.out.println(order);
	}
}
