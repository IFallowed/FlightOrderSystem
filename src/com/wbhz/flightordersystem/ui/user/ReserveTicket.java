/**
 * 
 */
package com.wbhz.flightordersystem.ui.user;

import java.util.Scanner;

import com.wbhz.flightordersystem.entity.Flight;
import com.wbhz.flightordersystem.entity.Order;
import com.wbhz.flightordersystem.service.OrderService;
import com.wbhz.flightordersystem.ui.LoginUI;
import com.wbhz.flightordersystem.util.LogUtil;
import com.wbhz.jdbc.util.ObjectFactory;

/**
 * @Description: 预订机票界面
 * @author soft02
 * @version 1.0
 */
public class ReserveTicket {
	private static OrderService orderService = (OrderService) ObjectFactory.createObject("orderService");
	/**
	 * 
	 * @Description: 界面初始化
	 * @Return Type:void
	 */
	public static void init(){
		body();
	}
	/**
	 * 
	 * @Description: 主体界面
	 * @Return Type void
	 */
	private static void body(){
		System.out.println("预订机票");
		Scanner scanner = new Scanner(System.in);
		//延时1秒
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LogUtil.error(e.getMessage());
		}
		System.out.println("是否需要查询航班列表？（y/n）");
		if("y".equals(scanner.next())){
			QueryFlight.init();
		}
		//输入订单参数
		Order order = new Order();
		//获取当前登陆的会员的id
		order.setUserId(LoginUI.getSessionUser().getId());
		Flight flight = null;
		//输入航班号
		System.out.println("请输入需要预订的航班号：");
		//检测航班
		while(null == flight) {
			flight = orderService.checkFlight(flight, order);
			System.out.println("请重新输入航班号：");
		}
		//获取预订的座位
		do {
			orderService.orderSeat(flight, order);
		} while (null == order.getSeat());
		//设置订单状态
		order.setState("已预订");
		//保存订单
			boolean isInsert = orderService.insertOrder(order, flight);
			if(isInsert) {
				System.out.println("机票预定成功");				
			}
			else {
				System.out.println("机票预定失败");
			}
			//页面跳转
			System.out.println("是否返回上一界面？(y/n)");
			if("y".equals(scanner.next())){
				UserUI.init();
			}
			else {
				System.out.println("继续订票");
				body();
			}
	}

}
