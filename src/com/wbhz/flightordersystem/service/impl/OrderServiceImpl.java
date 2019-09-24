/**
 * 
 */
package com.wbhz.flightordersystem.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.wbhz.flightordersystem.dao.FlightDao;
import com.wbhz.flightordersystem.dao.OrderDao;
import com.wbhz.flightordersystem.dao.UserDao;
import com.wbhz.flightordersystem.entity.Flight;
import com.wbhz.flightordersystem.entity.Order;
import com.wbhz.flightordersystem.entity.User;
import com.wbhz.flightordersystem.exception.MyException;
import com.wbhz.flightordersystem.service.OrderService;
import com.wbhz.flightordersystem.ui.LoginUI;
import com.wbhz.flightordersystem.util.DateUtil;
import com.wbhz.flightordersystem.util.LogUtil;
import com.wbhz.flightordersystem.util.SeatsUtil;

/**
 * @Description: 订单业务逻辑实现
 * @author soft02
 * @version 1.0
 */
public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	private FlightDao flightDao;
//	private UserDao userDao;
	public void setFlightDao(FlightDao flightDao) {
		this.flightDao = flightDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
	/**
	 * @see com.wbhz.flightordersystem.service.OrderService#insertOrder(com.wbhz.flightordersystem.entity.Order)
	 * @param order
	 * @return 
	 */
	@Override
	public boolean insertOrder(Order order,Flight flight) {
		boolean flag = false;
		if(null == order.getOrderId()){
			order.setOrderId(DateUtil.parseDateToStr(new Date(), "yyyyMMddHHmmss"));
		}
		try {
			orderDao.add(order);
			flightDao.updateTicketsByKey(flight);
			flag = true;
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
			flag = false;
		}
		return flag; 
	}
	/**
	 * @see com.wbhz.flightordersystem.service.OrderService#printTickets(java.lang.String)
	 * @param id
	 */
	@Override
	public void printTickets(String id) {
		List<Order> orders = null;
		//先更新所有订单的状态
		try {
			orders = orderDao.getByUserId(id);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		Iterator<Order> iterator = orders.iterator();
		while (iterator.hasNext()) {
			Order order = iterator.next();
			boolean flag = updateState(order);
			if(!flag) {
				LogUtil.error(order + "更新状态失败");
			}
		}
		//再输出需要的订单
		try {
			orders = orderDao.getByUserId(id);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		if(orders.size() > 0) {
			iterator = orders.iterator();
			while (iterator.hasNext()) {
				Order order = iterator.next();
				Flight flight = null;
				User user = LoginUI.getSessionUser();
				try {
					flight = flightDao.getByKey(order.getFlightId());
				} catch (SQLException e) {
					LogUtil.error(e.getMessage());
				}
				if(!order.getState().contains("已过期") && !order.getState().contains("已退订")){
					System.out.println("******************************************");
					System.out.println("订单号：" + order.getOrderId());
					System.out.println("会员姓名：" + user.getUserName() + "\t身份证号：" + order.getUserId());
					System.out.println("航班号:"+ order.getFlightId() +"\t出发时间：" + flight.getTakeOffTime() + "\n出发地：" + flight.getStartPlace()+ "\t目的地：" +flight.getEndPlace());
					System.out.println("座位号：" + order.getSeat() + "\t订单状态：" + order.getState());
					System.out.println("******************************************");
				}
			}
		}
		else {
			System.out.println("您没有订购的机票");
		}
	}
	/**
	 * @see com.wbhz.flightordersystem.service.OrderService#updateStateByKey(java.lang.String)
	 * @param id
	 * @return 
	 */
	@Override
	public boolean updateState(Order order) {
		boolean flag = false;
		Flight flight = null;
		try {
			flight = flightDao.getByKey(order.getFlightId());
			//计算当前时间与飞机出发时间的差值
			long time = (flight.getTakeOffTime().getTime() - System.currentTimeMillis());
			if(time > 0 && time < 7200000){
				//如果差值在两小时之内，订单不可改签
				if(!order.getState().contains("不可改签")){
					order.setState("已预定但不可改签");
					orderDao.updateStateByKey(order);
				}
			}
			else if (time < 0) {
				//如果差值小于0，则飞机已起飞
				if(!order.getState().contains("已过期")){
					order.setState("已过期");					
					orderDao.updateStateByKey(order);
				}
			}
			flag = true;
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
			flag = false;
		}
		return flag;
	}
	/**
	 * @see com.wbhz.flightordersystem.service.OrderService#printFlights(java.lang.String)
	 * @param orderId
	 */
	@Override
	public void printFlights(String orderId) {
		Flight flight = null;
		String startPlace = null;
		String endPlace = null;
		try {
			//获取原来订单的航班的出发地与目的地
			String flightId = orderDao.getByKey(orderId).getFlightId();
			flight = flightDao.getByKey(flightId);
			startPlace = flight.getStartPlace();
			endPlace = flight.getEndPlace();
			//查询符合此出发地与目的地的航班
			List<Flight> flights = flightDao.getByCondition(startPlace, endPlace, null);
			//打印可供选择的航班
			System.out.println("从"+startPlace+"出发，到"+endPlace+"的可供选择的航班如下：");
			Iterator<Flight> iterator = flights.iterator();
			while (iterator.hasNext()) {
				Flight flight2 = iterator.next();
				// 航班的出发时间必须晚于当前时间
				if((flight2.getTakeOffTime().getTime()) - System.currentTimeMillis() > 1800000){
					System.out.println(flight2);					
				}
			}
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
	}

	/**
	 * @see com.wbhz.flightordersystem.service.OrderService#checkFlight(com.wbhz.flightordersystem.entity.Flight, com.wbhz.flightordersystem.entity.Order)
	 * @param flight
	 * @param order
	 * @return 
	 */
	@Override
	public Flight checkFlight(Flight flight, Order order) {
		Scanner scanner = new Scanner(System.in);
		String flightId =  scanner.next();
		try {
			flight = flightDao.getByKey(flightId);
			//保存查到的航班的航班号
			if(null != flight) {
				order.setFlightId(flight.getFlightId());
			}
			else {
				System.out.println("没有此航班");
			}
		} catch (SQLException e) {
			System.out.println("无效航班号");
			LogUtil.error(e.getMessage());
		}
		return flight;
	}
	/**
	 * @see com.wbhz.flightordersystem.service.OrderService#orderSeat(com.wbhz.flightordersystem.entity.Flight, com.wbhz.flightordersystem.entity.Order)
	 * @param flight
	 * @param order
	 * @throws MyException 
	 */
	@Override
	public void orderSeat(Flight flight, Order order) {
		Scanner scanner = new Scanner(System.in);
		//显示需要预订的航班的座位预订
		System.out.println(flight.getFlightId() + "号航班座位预订情况：");
		System.out.println(flight.printSeats());
		//预订座位
		System.out.println("请输入需要预订的座位号(行-列：1-10)");
		String seat = scanner.next();
		try {
			seat = parseSeat(seat);
			//获取本航班的座位预订情况
			String[] seats = flight.getTickets().split("-");
			if("0".equals(seats[Integer.parseInt(seat)])){
				//如果要预订的座位值为0
				System.out.println("此座位已被预订");
				order.setSeat(null);
			}
			else {
				//座位存在，且无人预订则保存座位到订单中
				order.setSeat(seat);
				seats[Integer.parseInt(seat)] = "0";
				//修改此航班的座位预订
				flight.setTickets(SeatsUtil.array2String(seats));
			}
			//捕获可能是无效座位号导致的数组下标越界异常
		} catch (Exception e) {
			order.setSeat(null);
			System.out.println("座位订购无效，请重新订购");
		}
	}

	/**
	 * @see com.wbhz.flightordersystem.service.OrderService#queryByOrderId(java.lang.String)
	 * @param orderId
	 * @return
	 */
	@Override
	public Order queryByOrderId(String orderId) {
		Order order = null;
		try {
			order = orderDao.getByKey(orderId);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		return order;
	}
	/**
	 * @see com.wbhz.flightordersystem.service.OrderService#updateOrder(com.wbhz.flightordersystem.entity.Order, com.wbhz.flightordersystem.entity.Flight)
	 * @param order
	 * @param flight
	 * @return 
	 */
	@Override
	public boolean updateOrder(Order order, Flight flight) {
		boolean flag = false;
		try {
			orderDao.updateByKey(order);
			flightDao.updateTicketsByKey(flight);
			flag = true;
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
			flag = false;
		}
		return flag;
	}
	/**
	 * @see com.wbhz.flightordersystem.service.OrderService#deleteOrder(java.lang.String)
	 * @param orderId
	 * @return 
	 */
	@Override
	public boolean deleteOrder(String orderId) {
		boolean flag = false;
		Order order = null;
		try {
			order = orderDao.getByKey(orderId);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
			flag = false;
		}
		if(null == order){
			System.out.println("输入的订单号错误");
			flag = false;
		}
		else {
			if(!order.getState().contains("已退订")){
				order.setState("已退订");
				try {
					orderDao.updateStateByKey(order);
					flag = true;
				} catch (SQLException e) {
					LogUtil.error(e.getMessage());
					flag = false;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @Description: 解析座位字符串：例如： 2-8 -->  18
	 * @Return Type String
	 * @param seat
	 * @return
	 * @throws Exception 
	 */
	private String parseSeat(String seat) throws Exception {
		int row = Integer.parseInt(seat.substring(0,seat.indexOf("-")));
		int column = Integer.parseInt(seat.substring(seat.indexOf("-") + 1,seat.length()));
		if(column > 10) {
			throw new Exception();
		}
		String seatNum = String.valueOf(10*(row - 1) + column);
		return seatNum;
	}
	/**
	 * @see com.wbhz.flightordersystem.service.OrderService#queryAll(java.lang.String)
	 * @param userId
	 * @return
	 */
	@Override
	public List<Order> queryAll(String userId) {
		List<Order> orders = null;
		try {
			orders = orderDao.getByUserId(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
}
