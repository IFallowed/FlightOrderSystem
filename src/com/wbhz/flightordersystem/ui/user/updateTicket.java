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
 * @Description: 机票改签
 * @author soft02
 * @version 1.0
 */
public class updateTicket {
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
		Scanner scanner = new Scanner(System.in);
		System.out.println("机票改签");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LogUtil.error(e.getMessage());
		}
		//打印此会员所有订单
		System.out.println("您已预订的机票如下：");
		orderService.printTickets(LoginUI.getSessionUser().getId());
		System.out.println("请输入你要改签的机票号 (不改请输null):");
		String orderId = scanner.next();
		//有效性接检测
		if(!"null".equals(orderId)){	
			Order order = orderService.queryByOrderId(orderId);;
			while (null == order) {
				System.out.println("无效机票号，请重输：");
				orderId = scanner.next();
				order = orderService.queryByOrderId(orderId);
			}
			//检测此订单是否可以改签
			while(order.getState().contains("不可改签")){
				System.out.println("飞机将于两小时内出发，不可改签");
				System.out.println("请重新输入你要改签的机票号:");
				orderId = scanner.next();
				order = orderService.queryByOrderId(orderId);
			}
			//打印可供选择的航班
			orderService.printFlights(orderId);
			
			System.out.println("请输入新的航班号：");
			Flight flight = null;
			//选择新的航班
			while(null == flight) {
				flight = orderService.checkFlight(flight, order);
				System.out.println("请重新输入航班号：");
			}
			//选择新的座位号
			do {
				orderService.orderSeat(flight, order);
			} while (null == order.getSeat());
			//保存订单
			boolean isUpdate = orderService.updateOrder(order, flight);
			if(isUpdate) {
				System.out.println("机票改签成功");
			}
			else {
				System.out.println("机票改签失败");
			}
			//页面跳转
			System.out.println("是否返回上一界面？(y/n)");
			if("y".equals(scanner.next())){
				UserUI.init();
			}
			else {
				System.out.print("继续");
				body();
			}
		}
		else {
			//放弃修改
			System.out.println("放弃修改，退出，返回会员界面");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LogUtil.error(e.getMessage());
			}
			UserUI.init();
		}
		
	}
}
