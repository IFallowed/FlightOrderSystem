/**
 * 
 */
package com.wbhz.flightordersystem.service.impl;

import java.sql.SQLException;
import java.util.Date;

import com.wbhz.flightordersystem.dao.AdminDao;
import com.wbhz.flightordersystem.entity.Admin;
import com.wbhz.flightordersystem.exception.MyException;
import com.wbhz.flightordersystem.service.AdminService;
import com.wbhz.flightordersystem.util.LogUtil;

/**
 * @Description:管理员业务逻辑实现
 * @author soft01
 * @version: 1.0
 */
public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao;
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	/**
	 * @see com.wbhz.flightordersystem.service.AdminService#login(java.lang.String, java.lang.String)
	 * @param account
	 * @param password
	 * @return
	 * @throws MyException 
	 */
	@Override
	public boolean login(String account, String password,ThreadLocal<Admin> sessionAdmin){
		Boolean flag = false;
		//查询数据库中是否有此用户名
		Admin admin = null;
		try {
			admin = adminDao.getByAccount(account);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		//如果找不到对象，则为此用户名不存在
		if(null == admin){
			flag = false;
			System.out.println("用户名不存在，请重新输入：");
		}
		//如果用户存在检查锁，是否允许登录
		else {
			//获取锁状态与锁时间
			int lock = admin.getLock();
			Date lockTime = admin.getLockTime();
			//如果时间锁为空,则尚未上锁
			if(null == lockTime) {
				if(lock != 0){
					//还有输入机会，验证密码
					if(password.equals(admin.getPassword())) {
						//密码输入正确，重置锁
						lock = 3;
						System.out.println("登录成功");
						sessionAdmin.set(admin);
						flag = true;
					}
					else {
						//密码输入错误，登录次数减一
						lock--;
						System.out.println("密码错误，还剩" + lock + "机会，请重新输入：");
						flag = false;
					}
				}
				else {
					//没有输入机会，即lock=0,设置锁时间
					lockTime = new Date();
					System.out.println("输入机会已用完，账户锁定1小时");
					flag = false;
				}
			}
			//如果时间锁不为空，则已上锁
			else {
				//如果当前时间减去锁时间大于1小时
				if (calcDate(System.currentTimeMillis(), lockTime)) {
					//重置锁
					lock = 3;
					lockTime = null;
					if(password.equals(admin.getPassword())) {
						//密码输入正确
						System.out.println("登录成功");
						sessionAdmin.set(admin);
						flag = true;
					}
					else {
						//密码输入错误，登录次数减一
						lock--;
						System.out.println("密码错误，还剩" + lock + "机会，请重新输入：");
						flag = false;
					}
				} 
				//如果当前时间减去锁时间小于1小时
				else {
					int seconds = (int) (3600000 - ((System.currentTimeMillis() - lockTime.getTime())/1000));
					System.out.println("当前账户已锁定,剩余时间：" + seconds + "秒");
					flag = false;
				}
			}
			//更新用户状态
			admin.setLock(lock);
			admin.setLockTime(lockTime);
			try {
				adminDao.updateLockAndLockTimeByKey(admin);
			} catch (SQLException e) {
				LogUtil.error(e.getMessage());
			}			
		}
		return flag;
	}


	/**
	 * 
	 * @Description: 计算两个时间的毫秒差是否大于等于1小时
	 * @Return Type:long
	 * @param rightNow
	 * @param lockTime
	 * @return
	 */
	private static boolean calcDate(long rightNow,Date lockTime){
		//获取锁时间的毫秒数
		long beginSeconds = lockTime.getTime();
		//当前时间的毫秒数
		long endSeconds = rightNow;
		return (endSeconds - beginSeconds) >= 3600000;
	}
}
