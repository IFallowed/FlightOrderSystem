/**
 * 
 */
package com.wbhz.flightordersystem.ui.user;

import java.util.Scanner;

import com.wbhz.flightordersystem.entity.User;
import com.wbhz.flightordersystem.exception.MyException;
import com.wbhz.flightordersystem.service.UserService;
import com.wbhz.flightordersystem.ui.LoginUI;
import com.wbhz.flightordersystem.util.LogUtil;
import com.wbhz.jdbc.util.ObjectFactory;

/**
 * @Description: 修改个人信息
 * @author soft02
 * @version 1.0
 */
public class UpdateUserInfo {
	private static UserService userService = (UserService) ObjectFactory.createObject("userService");
	private static User sessionUser = LoginUI.getSessionUser();
	private static User user = new User();
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
	 * @Description: 导航栏
	 * @Return Type void
	 */
	private static void header(){
		System.out.println("修改个人信息");
		System.out.println("******************************************");
		System.out.println("姓名：" + sessionUser.getUserName() + "\t身份证号:" + sessionUser.getId());
		System.out.println("1.修改帐号\n2.修改密码\n3.修改性别\n"
				+ "4.修改联系电话\n5.修改地址\n6.修改邮箱\n7.回到会员主界面");
		System.out.println("******************************************");
	System.out.println("请选择：");
	Scanner scanner = new Scanner(System.in);
	//选择子菜单
	switch (scanner.nextInt()) {
		case 1:
			//修改帐号
			updateAccount();
			break;
		case 2:
			//修改密码
			updatePassword();
			break;
		case 3:
			//修改性别
			updateSex();
			break;
		case 4:
			//修改联系电话
			updatePhone();
			break;
		case 5:
			//修改地址
			updateAddress();
			break;
		case 6:
			//修改邮箱
			updateMail();
			break;
		case 7://回到会员界面
		default:
			UserUI.init();
		}
	}
	
	/**
	 * @Description: 修改邮箱
	 * @Return Type void
	 */
	private static void updateMail() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入的新的邮箱(不改请输null)：");

		String mail = strRegex("邮箱", "^(\\w)+(\\.\\w)*@(\\w)+((\\.\\w{2,3}){1,3})$");
		user.setMail(mail);
		System.out.println("已保存修改");
		System.out.println("是否继续修改其他信息？(y/n)");
		if("y".equals(scanner.next())){
			header();
		}
		else {
			doUpdate();
		}
		
	}
	/**
	 * @Description: 修改地址
	 * @Return Type void
	 */
	private static void updateAddress() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入的新的地址(不改请输null)：");
		String address = scanner.next();
		if(!"null".equals(address)) {
			user.setAddress(address);
			System.out.println("已保存修改");
			System.out.println("是否继续修改其他信息？(y/n)");
			if("y".equals(scanner.next())){
				header();
			}
			else {
				doUpdate();
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
	/**
	 * @Description: 修改联系电话
	 * @Return Type void
	 */
	private static void updatePhone() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入的新的联系电话(不改请输null)：");
		String phone = strRegex("手机号", "^[1][3,4,5,8][0-9]{9}$");
		user.setPhone(phone);
		System.out.println("已保存修改");
		System.out.println("是否继续修改其他信息？(y/n)");
		if("y".equals(scanner.next())){
			header();
		}
		else {
			doUpdate();
		}
		
	}
	/**
	 * @Description: 修改性别
	 * @Return Type void
	 */
	private static void updateSex() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入的新的性别(不改请输null)：");
		String sex = scanner.next();
		if(!"null".equals(sex)) {
			user.setSex(sex);
			System.out.println("已保存修改");
			System.out.println("是否继续修改其他信息？(y/n)");
			if("y".equals(scanner.next())){
				header();
			}
			else {
				doUpdate();
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
	/**
	 * @Description: 修改密码
	 * @Return Type void
	 */
	private static void updatePassword() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入的新的密码(不改请输null)：");
		String password = scanner.next();
		if(!"null".equals(password)) {
			user.setPassword(password);
			System.out.println("已保存修改");
			System.out.println("是否继续修改其他信息？(y/n)");
			if("y".equals(scanner.next())){
				header();
			}
			else {
				doUpdate();
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
	/**
	 * 
	 * @Description: 修改账户
	 * @Return Type void
	 */
	private static void updateAccount(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入的新的帐号(不改请输null)：");
		String account = scanner.next();
		if(!"null".equals(account)) {
			user.setAcount(account);
			System.out.println("已保存修改");
			System.out.println("是否继续修改其他信息？(y/n)");
			if("y".equals(scanner.next())){
				header();
			}
			else {
				doUpdate();
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
	/**
	 * @Description: 执行修改
	 * @Return Type void
	 */
	private static void doUpdate() {
		user.setId(LoginUI.getSessionUser().getId());
		try {
			userService.updateUser(user);
			System.out.println("修改成功，返回会员界面");
			UserUI.init();
		} catch (MyException e) {
			LogUtil.info(e.getMessage());
		}
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
		if(!"null".equals(data)) {
			while (!data.matches(regex)) {
				System.out.println(str+"格式不正确,请重输：");
				data = scanner.next();
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
		return data;
	}
	
}
