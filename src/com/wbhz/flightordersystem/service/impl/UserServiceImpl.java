/**
 * 
 */
package com.wbhz.flightordersystem.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.wbhz.flightordersystem.dao.UserDao;
import com.wbhz.flightordersystem.entity.User;
import com.wbhz.flightordersystem.exception.MyException;
import com.wbhz.flightordersystem.service.UserService;
import com.wbhz.flightordersystem.util.LogUtil;

/**
 * @Description:会员业务逻辑实现
 * @author soft01
 * @version: 1.0
 */
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * @see cn.wbhz.flightorder.service.UserService#register(cn.wbhz.flightorder.entity.User)
	 * @param user
	 * @return
	 */
	@Override
	public boolean register(User user) {
		boolean flag = false;
		//获取所有的会员记录
		List<User> users = null;
		try {
			users = userDao.getAll();
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		//遍历会员集合
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User rsUser = iterator.next();
			//如果用户名有相同记录,向外抛出异常
			if(user.getUserName().equals(rsUser.getUserName())){
				System.out.println("此用户名已被注册，请重新注册：");
				flag = false;
			}
			//如果身份证有相同记录，向外抛出异常
			if(user.getId().equals(rsUser.getId())){
				System.out.println("此身份证号已被注册，请重新注册：");
				flag = false;
			}
			continue;
		}
		//如果没有相同的记录，执行会员添加
		try {
			userDao.add(user);
			flag = true;
		} catch (SQLException e) {
			flag = false;
			System.out.println("注册失败，数据异常");
			LogUtil.error(e.getMessage());
		}
		return flag;
	}
	/**
	 * @see cn.wbhz.flightorder.service.UserService#login(java.lang.String, java.lang.String, java.lang.ThreadLocal)
	 * @param account
	 * @param password
	 * @param sessionUser
	 * @return
	 */
	@Override
	public boolean login(String account, String password, ThreadLocal<User> sessionUser) {
		boolean flag = false;
		//查询数据库中是否有此用户名
		User user = null;
		try {
			user = userDao.getByAccount(account);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
		//如果找不到对象，则为此用户名不存在
		if(null == user){
			flag = false;
			System.out.println("用户名不存在，请重新输入：");
		}
		//如果用户存在检查锁，是否允许登录
		else {
			//获取锁状态与锁时间
			int lock = user.getLock();
			Date lockTime = user.getLockTime();
			//如果时间锁为空,则尚未上锁
			if(null == lockTime) {
				if(lock != 0){
					//还有输入机会，验证密码
					if(password.equals(user.getPassword())) {
						//密码输入正确，重置锁
						lock = 3;
						System.out.println("登录成功");
						sessionUser.set(user);
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
					if(password.equals(user.getPassword())) {
						//密码输入正确
						System.out.println("登录成功");
						sessionUser.set(user);
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
			user.setLock(lock);
			user.setLockTime(lockTime);
			try {
				userDao.updateLockAndLockTimeByKey(user);
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

	/**
	 * @see com.wbhz.flightordersystem.service.UserService#updateUser(com.wbhz.flightordersystem.entity.User)
	 * @param user
	 * @throws MyException 
	 */
	@Override
	public void updateUser(User user) throws MyException {
		try {
			userDao.updateByKey(user);
		} catch (SQLException e) {
			LogUtil.error(e.getMessage());
		}
	}
	

}
