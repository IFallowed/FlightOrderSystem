/**
 * 
 */
package com.wbhz.flightordersystem.test.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.wbhz.flightordersystem.dao.UserDao;
import com.wbhz.flightordersystem.dao.impl.UserDaoImpl;
import com.wbhz.flightordersystem.entity.User;

/**
 * @Description: UserDao测试类
 * @author soft02
 * @version 1.0
 */
public class TestUserDao {
	private UserDao userDao = new UserDaoImpl();
	/**
	 * 
	 * @Description 测试添加会员
	 * @Return Type void
	 * @throws Exception
	 */
	 @Test
	public void testAdd() {
		User user  = new User("12341234123412","test","test01","test01","男",
				"13645291296","zrgsfew@qq.com","mmmm",new Date());
		try {
			int row = userDao.add(user);
			if(row == 0) {
				System.out.println("会员添加失败");
			}
			else if (row == 1){
				System.out.println("会员添加成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	 /**
	  * 
	  * @Description 测试获取所有会员
	  * @Return Type void
	  * @throws Exception
	  */
	 @Test
	public void testGetAll() throws Exception {
		List<User> users = userDao.getAll();
		for (User user : users) {
			System.out.println(user);
		}
	}
}
