/**
 * 
 */
package com.wbhz.flightordersystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.wbhz.flightordersystem.entity.Order;

/**
 * @Description: 订单底层数据操作接口
 * @author soft02
 * @version 1.0
 */
public interface OrderDao {

	/**
	 * 
	 * @Description: 添加一个订单记录
	 * @Return Type void
	 * @param order
	 * @throws SQLException  
	 */
	int add(Order order) throws SQLException;
	
	/**
	 * 
	 * @Description: 根据主键获取订单记录
	 * @Return Type Order
	 * @param key
	 * @return
	 * @throws SQLException 
	 */
	Order getByKey(String key) throws SQLException;

	/**
	 * @Description: 根据用户id获得所有订单记录
	 * @Return Type List<Order>
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	List<Order> getByUserId(String id) throws SQLException;

	/**
	 * @Description: 根据主键修改订单状态
	 * @Return Type void
	 * @param order
	 * @throws SQLException  
	 */
	int updateStateByKey(Order order) throws SQLException;

	/**
	 * @Description: 根据主键修改订单
	 * @Return Type void
	 * @param order
	 * @throws SQLException 
	 */
	int updateByKey(Order order) throws SQLException;
}
