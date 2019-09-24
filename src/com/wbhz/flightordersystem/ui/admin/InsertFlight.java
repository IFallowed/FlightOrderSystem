/**
 * 
 */
package com.wbhz.flightordersystem.ui.admin;

import java.util.Scanner;

import com.wbhz.flightordersystem.entity.Flight;
import com.wbhz.flightordersystem.service.FlightService;
import com.wbhz.flightordersystem.util.DateUtil;
import com.wbhz.flightordersystem.util.LogUtil;
import com.wbhz.flightordersystem.util.SeatsUtil;
import com.wbhz.jdbc.util.ObjectFactory;

/**
 * 
 * @Description: 添加航班界面
 * @author soft01
 * @version: 1.0
 */
public class InsertFlight {
	private static FlightService flightService = (FlightService) ObjectFactory.createObject("flightService");
	/**
	 * 
	 * @Description:界面初始化 
	 * @Return Type:void 
	 *
	 */
	public static void init() {
		body();
	}
	
	/**
	 * 
	 * @Description:主体界面
	 * @Return Type:void 
	 *
	 */
	private static void body() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("添加航班");
		//延时1秒
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LogUtil.error(e.getMessage());
			System.out.println("线程异常退出");
			System.exit(0);
		}
		//输入查询条件
		Flight flight = new Flight();
		System.out.println("请输入航班号(不改请输null)：");
		String flightId = scanner.next(); 
		System.out.println("请输入出发时间(不改请输null)：");
		String takeOffTime = scanner.next(); 
		if(!"null".equals(flightId) && !"null".equals(takeOffTime)) {
			flight.setFlightId(flightId);
			flight.setTakeOffTime(DateUtil.parseStrToDate(scanner.next(), "yyyy-MM-dd|HH:mm:ss"));
			System.out.println("请输入飞行时间：");
			flight.setFlyingTime(scanner.next());
			System.out.println("请输入出发地：");
			flight.setStartPlace(scanner.next());
			System.out.println("请输入目的地：");
			flight.setEndPlace(scanner.next());
			System.out.println("请输入座位数：");
			//将余票数变成座位字符串存储
			//将输入的余票数（即座位数）转换成座位连接字符串 如“10-1-2-3-4-5-6-7-8-9-10”
			flight.setTickets(SeatsUtil.parseSeats(scanner.next()));
			System.out.println("请输入票价：");
			flight.setPrice(scanner.nextFloat());

			//调用service层方法添加航班
			boolean isInsert = flightService.insertByFlightId(flight);
			if(isInsert) {
				System.out.println("添加航班成功");		
			}
			else {
				System.out.println("添加航班失败");
			}
			//界面跳转
			System.out.println("返回管理员界面？（y/n）");
			if("y".equals(scanner.next())){
				AdminUI.init();
			}
			else {
				System.out.print("继续");
				body();
			}
		}
		else {
			//放弃修改
			System.out.println("放弃修改，返回上一层界面");
			AdminUI.init();
		}
	}
}
