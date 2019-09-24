/**
 * 
 */
package com.wbhz.flightordersystem.test.dao;


import java.sql.SQLException;

import org.junit.Test;

import com.wbhz.flightordersystem.dao.AdminDao;
import com.wbhz.flightordersystem.dao.impl.AdminDaoImpl;
import com.wbhz.flightordersystem.entity.Admin;
import com.wbhz.flightordersystem.util.LogUtil;

/**
 * @Description:AdminDao测试类
 * @author soft01
 * @version: 1.0
 */
public class TestAdminDao {
	AdminDao adminDao = new AdminDaoImpl();
	/**
	 * 
	 * @Description: 测试添加管理员
	 * @Return Type:void
	 */
	@Test
	public void testadd() {
		Admin admin = new Admin("987987987987987111","admin1","111111","mmmm","12312312312");
		try {
			int row = adminDao.add(admin);
			if(row == 0) {
				System.out.println("管理员添加失败");
			}
			else if (row == 1){
				System.out.println("管理员添加成功");
			}
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Description: 测试根据主键获取管理员
	 * @Return Type:void
	 */
	@Test
	public void testGetByKey() {
		String key = "111222333444555667";
		try {
			Admin admin = adminDao.getByKey(key);
			System.out.println(admin);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
	}
}
