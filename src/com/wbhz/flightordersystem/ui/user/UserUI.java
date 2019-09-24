/**
 * 
 */
package com.wbhz.flightordersystem.ui.user;

import java.util.Scanner;

import com.wbhz.flightordersystem.ui.LoginUI;

/**
 * @Description: 会员界面
 * @author soft02
 * @version 1.0
 */
public class UserUI {

	/**
	 * 
	 * @Description: 初始化所有模块
	 * @Return Type:void
	 */
	public static void init(){
		body();
	}
	
	/**
	 * 
	 * @Description: 主体界面
	 * @Return Type:void
	 */
	private static void body(){
		System.out.println("会员界面");
		System.out.println("******************************************");
		System.out.println("1.航班查询\n2.预订机票\n3.订单改签\n"
				+ "4.退票\n5.修改个人信息\n6.回到登录界面\n7.退出系统");
		System.out.println("******************************************");
	System.out.println("请选择：");
	Scanner scanner = new Scanner(System.in);
	//选择子菜单
	switch (scanner.nextInt()) {
		case 1:
			//航班查询
			QueryFlight.init();
			break;
		case 2:
			//预订机票
			ReserveTicket.init();
			break;
		case 3:
			//订单改签
			updateTicket.init();
			break;
		case 4:
			//退票
			ReturnTicket.init();
			break;
		case 5:
			//修改个人信息
			UpdateUserInfo.init();
			break;
		case 6:
			//登录页面
			LoginUI.init();
			break;
		case 7:
		default:
			System.out.println("谢谢使用，欢迎下次光临！");
			System.exit(0);
		}
	}
}
