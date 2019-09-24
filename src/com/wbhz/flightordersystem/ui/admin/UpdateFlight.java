/**
 * 
 */
package com.wbhz.flightordersystem.ui.admin;

import java.util.Scanner;

import com.wbhz.flightordersystem.service.FlightService;
import com.wbhz.flightordersystem.util.LogUtil;
import com.wbhz.jdbc.util.ObjectFactory;

/**
 * 
 * @Description: 航班修改界面
 * @author soft01
 * @version: 1.0
 */
public class UpdateFlight {
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
		System.out.println("修改航班");
		//延时1秒
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LogUtil.error(e.getMessage());
			System.out.println("线程异常退出");
			System.exit(0);
		}
		//输入查询条件
		System.out.println("请输入航班号 (不改请输null)：");
		String flightId = scanner.next();
		//输入修改参数
		System.out.println("请输入新的起飞时间 (不改请输null): ");
		String takeOffTime = scanner.next();
		if(!"null".equals(flightId) && !"null".equals(takeOffTime)) {
			//调用service层方法修改航班
			boolean  isUpdate = flightService.updateByFlightId(flightId,takeOffTime);
			if(isUpdate) {
				System.out.println("修改航班成功");
			}
			else {
				System.out.println("修改航班失败");
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
