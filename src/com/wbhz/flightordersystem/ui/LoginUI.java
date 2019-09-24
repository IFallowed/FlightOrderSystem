/**
 * 
 */
package com.wbhz.flightordersystem.ui;

import java.util.Scanner;

import com.wbhz.flightordersystem.entity.Admin;
import com.wbhz.flightordersystem.entity.User;
import com.wbhz.flightordersystem.service.AdminService;
import com.wbhz.flightordersystem.service.UserService;
import com.wbhz.flightordersystem.ui.admin.AdminUI;
import com.wbhz.flightordersystem.ui.user.UserUI;
import com.wbhz.jdbc.util.ObjectFactory;

/**
 * @Description:所有用户登录界面
 * @author soft01
 * @version: 1.0
 */
public class LoginUI {
	private static UserService userService = (UserService) ObjectFactory.createObject("userService");
	private static AdminService adminService = (AdminService) ObjectFactory.createObject("adminService");
	/*
	 * 线程绑定对象，记录登录的用户
	 */
	private static ThreadLocal<Admin> sessionAdmin = new ThreadLocal<Admin>();
	private static ThreadLocal<User> sessionUser = new ThreadLocal<User>();

	/**
	 * 
	 * @Description: 界面初始化
	 * @Return Type:void
	 */
	public static void init(){
		body();
//		userLogin();
	}
	
	private static void body(){
		System.out.println("登录界面");
		System.out.println("******************************************");
		System.out.println("1.会员登录\n2.管理员登录\n3.会员注册界面\n4.退出系统");
		System.out.println("******************************************");
		Scanner scanner = new Scanner(System.in);
		System.out.println("请选择：");
		switch (scanner.nextInt()) {
		case 1:
			System.out.println("会员登录");
			userLogin();
			break;
		case 2:
			System.out.println("管理员登录");
			adminLogin();
			break;
		case 3:
			//会员注册界面
			RegisterUI.init();
			break;
		case 4:
		default:
			System.out.println("谢谢使用，欢迎下次光临！");
			System.exit(0);
		}
	}
	
	/**
	 * 
	 * @Description: 会员登录
	 * @Return Type:void
	 */
	private static void userLogin(){
		Scanner scanner = new Scanner(System.in);
		//输入登录参数
		System.out.println("请输入用户名：");
		String account = scanner.next();
		System.out.println("请输入密码：");
		String password = scanner.next();
		//如果登录验证不成功会返回false
		boolean flag = userService.login(account, password,sessionUser);
		if(flag){
			System.out.println("进入会员主界面");
			UserUI.init();
		}
		else {
			System.out.println("是否重新登录？（y/n）");
			if("y".equals(scanner.next())){
				//重新登录
				userLogin();	
			}
			else {
				System.out.println("返回初始界面");
				SystemUI.init();
			}
		}
	}
		
	
	/**
	 * 
	 * @Description: 管理员登录
	 * @Return Type:void
	 */
	private static void adminLogin(){
		Scanner scanner = new Scanner(System.in);
		//输入登录参数
		System.out.println("请输入用户名：");
		String account = scanner.next();
		System.out.println("请输入密码：");
		String password = scanner.next();
		//如果登录验证不成功会返回false
		boolean flag = adminService.login(account, password,sessionAdmin);
		if(flag){
			System.out.println("进入管理员主界面");
			AdminUI.init();
		}
		else {
			System.out.println("是否重新登录？（y/n）");
			if("y".equals(scanner.next())){
				//重新登录
				adminLogin();	
			}
			else {
				System.out.println("返回初始界面");
				SystemUI.init();
			}
		}
	}
	
	public static User getSessionUser(){
		return sessionUser.get();
	}
	
	public static Admin getSessionAdmin(){
		return sessionAdmin.get();
	}
}
