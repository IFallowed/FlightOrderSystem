/**
 * 
 */
package com.wbhz.flightordersystem.ui;

import java.util.Date;
import java.util.Scanner;

import com.wbhz.flightordersystem.entity.User;
import com.wbhz.flightordersystem.service.UserService;
import com.wbhz.jdbc.util.ObjectFactory;

/**
 * @Description:会员注册界面
 * @author soft01
 * @version: 1.0
 */
public class RegisterUI {
	private static UserService userService = (UserService) ObjectFactory.createObject("userService");
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
	 * @Description: 界面主体
	 * @Return Type:void
	 */
	private static void body(){
		Scanner scanner = new Scanner(System.in);
		//获取参数
		User user = paramsInput();
		//执行注册
		boolean isregister = userService.register(user);
		if(isregister) {
			//页面跳转
			System.out.println("注册成功，转向登录界面");
			LoginUI.init();
		}
		else {
			System.out.println("是否重新注册，否则返回主界面 (y/n)");
			if("y".equals(scanner.next())){
				//重新注册
				body();
			}
			else {
				//返回主界面
				SystemUI.init();
			}
		}
		
	}
	/**
	 * 
	 * @Description: 注册参数输入
	 * @Return Type:User
	 * @return
	 */
	private static User paramsInput(){
		Scanner scanner = new Scanner(System.in);
		//输入注册参数
		User user = new User();
		//输入用户名
		System.out.println("请输入用户名：");
		user.setAcount(scanner.next());
		//输入并验证密码
		System.out.println("请输入密码：");
		user.setPassword(scanner.next());
		System.out.println("请确认密码：");
		String confPwd = scanner.next();
		while (!user.getPassword().equals(confPwd)) {
			System.out.println("两次输入的密码不匹配，请重新输入密码：");
			user.setPassword(scanner.next());
			System.out.println("请再次确认密码：");
			confPwd = scanner.next();
		}
		//输入姓名
		System.out.println("请输入姓名：");
		user.setUserName(scanner.next());
		//输入身份证号
		String id = strRegex("身份证号", "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
		user.setId(id);
		//输入性别
		System.out.println("请输入性别：");
		user.setSex(scanner.next());
		//输入手机号
		String phone = strRegex("手机号", "^[1][3,4,5,8][0-9]{9}$");
		user.setPhone(phone);
		//输入邮箱
		String mail = strRegex("邮箱", "^(\\w)+(\\.\\w)*@(\\w)+((\\.\\w{2,3}){1,3})$");
		user.setMail(mail);
		//保存注册时间
		user.setCreateTime(new Date());
		//输入地址
		System.out.println("请输入地址：");
		user.setAddress(scanner.next());
		
		return user;
	}
	
	/**
	 * 
	 * @Description: 对输入的数据使用特定的正则表达式验证
	 * @Return Type:String
	 * @param str
	 * @param regex
	 * @return
	 */
	private static String strRegex(String str,String regex){
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入" + str + "：");
		String data = scanner.next();
		while (!data.matches(regex)) {
			System.out.println(str+"格式不正确,请重输：");
			data = scanner.next();
		}
		return data;
	}
}
