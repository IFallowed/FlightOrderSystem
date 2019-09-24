/**
 * 
 */
package com.wbhz.flightordersystem.ui;

import java.util.Scanner;

/**
 * @Description: 主界面入口
 * @author soft01
 * @version: 1.0
 */
public class SystemUI {
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
	 * @Description 主题界面
	 * @Return Type void
	 */
	private static void body(){
		System.out.println("欢迎使用飞机订票系统");
		System.out.println("******************************************");
		System.out.println("1.用户登录\n2.用户注册\n3.退出系统");
		System.out.println("******************************************");
		Scanner scanner = new Scanner(System.in);
		System.out.println("请选择：");
		switch (scanner.nextInt()) {
		case 1:
			LoginUI.init();
			break;
		case 2:
			RegisterUI.init();
			break;
		case 3:
		default:
			System.out.println("谢谢使用，欢迎下次光临！");
			System.exit(0);
		}
	}
}
