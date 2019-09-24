/**
 * 
 */
package com.wbhz.flightordersystem.ui.admin;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.wbhz.flightordersystem.entity.Flight;
import com.wbhz.flightordersystem.service.FlightService;
import com.wbhz.flightordersystem.util.LogUtil;
import com.wbhz.jdbc.util.ObjectFactory;

/**
 * @Description:航班查询界面
 * @author soft01
 * @version: 1.0
 */
public class QueryFlight {
	private static FlightService flightService = (FlightService) ObjectFactory.createObject("flightService");
	
	/**
	 * 
	 * @Description: 界面初始化
	 * @Return Type:void
	 */
	public static void init(){
		header();
	}
	
	/**
	 * 
	 * @Description: 打印头部导航栏
	 * @Return Type:void
	 */
	private static void header(){
		System.out.println("航班查询");
		System.out.println("******************************************");
		System.out.println("1.按航班号查询\n2.按出发地、目的地和日期查询\n"
				+ "3.回到管理员界面\n4.退出系统");
		System.out.println("******************************************");
	System.out.println("请选择：");
	Scanner scanner = new Scanner(System.in);
	//选择查询方式
	switch (scanner.nextInt()) {
		case 1:
			queryByFlightId();
			break;
		case 2:
			queryByCondition();
			break;
		case 3:
			AdminUI.init();
			break;
		case 4:
		default:
			System.out.println("谢谢使用，欢迎下次光临！");
			System.exit(0);
		}
	}
	
	/**
	 * 
	 * @Description: 按航班号查询
	 * @Return Type:void
	 */
	private static void queryByFlightId(){
		Scanner scanner = new Scanner(System.in);
		//延时1秒
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LogUtil.error(e.getMessage());
			System.out.println("线程异常退出");
			System.exit(0);
		}
		//管理员输入航班号
		System.out.println("请输入航班号：");
		String flightId = scanner.next();
		//调用service层方法查询航班
		Flight flight = null;
		flight = flightService.queryByFlightId(flightId);
		if(null != flight) {
			//打印查询到的航班信息
			System.out.println(flight);
			System.out.println(flight.printSeats());
		} else {
			System.out.println("没有此航班的信息，请确认航班号是否正确。");
		}
		//界面跳转
		System.out.println("返回上一界面？（y/n）");
		if("y".equals(scanner.next())){
			header();
		}
		else {
			System.out.println("继续查询。");
			queryByFlightId();
		}
	}
	
	/**
	 * 
	 * @Description: 按出发地、目的地和日期查询
	 * @Return Type:void
	 */
	private static void queryByCondition(){
		Scanner scanner = new Scanner(System.in);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LogUtil.error(e.getMessage());
			System.out.println("线程异常退出");
			System.exit(0);
		}
		//输入查询条件
		System.out.println("请输入出发地，无条件请输入null：");
		String startPlace = scanner.next();
		System.out.println("请输入目的地，无条件请输入null：");
		String endPlace = scanner.next();
		System.out.println("请输入出发日期，无条件请输入null：");
		String takeOffTime = scanner.next();
		//调用service层方法查询航班
		List<Flight> flights = null;
		flights = flightService.queryByCondition(startPlace, endPlace, takeOffTime);
		//打印查询到的航班
		if(flights.size() > 0){
			Iterator<Flight> iterator = flights.iterator();
			while (iterator.hasNext()) {
				Flight flight = iterator.next();
				System.out.println("******************************************");
				System.out.println(flight + "\n"+ flight.printSeats());
				System.out.println("******************************************");
			}
		}
		else {
			System.out.println("没有符合条件的航班");
		}
		//界面跳转
		System.out.println("返回上一界面？（y/n）");
		if("y".equals(scanner.next())){
			header();
		}
		else {
			System.out.println("继续查询。");
			queryByCondition();
		}
	}
	
}
