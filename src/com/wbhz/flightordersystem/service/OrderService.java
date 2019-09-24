/**
 * 
 */
package com.wbhz.flightordersystem.service;

import java.util.List;

import com.wbhz.flightordersystem.entity.Flight;
import com.wbhz.flightordersystem.entity.Order;
import com.wbhz.jdbc.util.BaseService;

/**
 * @Description: 订单业务逻辑接口
 * @author soft02
 * @version 1.0
 */
public interface OrderService extends BaseService {

	/**
	 * 
	 * @Description: 添加订单
	 * @Return Type void
	 * @param order
	 * @param flight
	 */
	boolean insertOrder(Order order,Flight flight);

	/**
	 * @Description: 打印此id的用户的预订单
	 * @Return Type void
	 * @param id 
	 */
	void printTickets(String id);
	
	/**
	 * 
	 * @Description: 更新订单状态
	 * @Return Type void
	 * @param order
	 */
	boolean updateState(Order order);

	/**
	 * @Description: 打印可改签的航班
	 * @Return Type void
	 * @param orderId
	 */
	void printFlights(String orderId);

	
	/**
	 * 
	 * @Description:检测航班号的有效性
	 * @Return Type Flight
	 * @param flight
	 * @param order
	 */
	Flight checkFlight(Flight flight,Order order);

	/**
	 * @Description: 预订座位
	 * @Return Type void
	 * @param flight
	 * @param order
	 */
	void orderSeat(Flight flight, Order order);

	/**
	 * 
	 * @Description: 根据订单号查询订单
	 * @Return Type Order
	 * @param orderId
	 * @return
	 */
	Order queryByOrderId(String orderId);

	/**
	 * @Description: 修改订单
	 * @Return Type void
	 * @param order
	 * @param flight
	 */
	boolean updateOrder(Order order, Flight flight);

	/**
	 * @Description: 删除订单
	 * @Return Type void
	 * @param orderId
	 */
	boolean deleteOrder(String orderId);

	/**
	 * @Description: 
	 * @Return Type:List<Order>
	 * @return 
	 * 
	 */
	List<Order> queryAll(String userId);
}
