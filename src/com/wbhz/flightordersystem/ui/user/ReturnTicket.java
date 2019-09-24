/**
 * 
 */
package com.wbhz.flightordersystem.ui.user;

import java.util.Scanner;

import com.wbhz.flightordersystem.service.OrderService;
import com.wbhz.flightordersystem.ui.LoginUI;
import com.wbhz.flightordersystem.util.LogUtil;
import com.wbhz.jdbc.util.ObjectFactory;

/**
 * @Description: 退票
 * @author soft02
 * @version 1.0
 */
public class ReturnTicket {
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
		System.out.println("退订机票");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LogUtil.error(e.getMessage());
		}
		//打印此会员所有订单
		System.out.println("您已预订的机票如下：");
		orderService.printTickets(LoginUI.getSessionUser().getId());
		//退票
		System.out.println("请输入你想要退的机票号 (不改请输null)：");
		String orderId = scanner.next();
		if(!"null".equals(orderId)){
			boolean isDel = orderService.deleteOrder(orderId);
			if(isDel) {
				System.out.println("机票退订成功");
			}
			else {
				System.out.println("机票退订失败");
			}
			//页面跳转
			System.out.println("机票退订成功,是否返回上一界面？(y/n)");
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
