/**
 * 
 */
package com.wbhz.flightordersystem.ui.admin;

import java.util.Scanner;

import com.wbhz.flightordersystem.ui.LoginUI;

/**
 * @Description:管理员界面
 * @author soft01
 * @version: 1.0
 */
public class AdminUI {
	
	/**
	 * 
	 * @Description: 初始化所有模块
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
		System.out.println("管理员界面");
		System.out.println("******************************************");
		System.out.println("1.航班查询\n2.航班删除\n3.修改航班\n"
				+ "4.添加航班\n5.回到登录界面\n6.退出系统");
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
				//航班删除
				DeleteFlight.init();
				break;
			case 3:
				//修改航班
				UpdateFlight.init();
				break;
			case 4:
				//添加航班
				InsertFlight.init();
				break;
			case 5:
				//登录页面
				LoginUI.init();
				break;
			case 6:
			default:
				System.out.println("谢谢使用，欢迎下次光临！");
				System.exit(0);
		}
	}
}
