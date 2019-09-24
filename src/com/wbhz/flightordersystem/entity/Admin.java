/**
 * 
 */
package com.wbhz.flightordersystem.entity;

import java.util.Date;

import com.wbhz.flightordersystem.util.DateUtil;

/**
 * @Description: 管理员实体类
 * @author soft01
 * @version: 1.0
 */
public class Admin {
	/*
	 * 身份证号
	 */
	private String id;
	/*
	 * 管理员姓名
	 */
	private String adminName;
	/*
	 * 管理员帐号
	 */
	private String account;
	/*
	 * 管理员密码
	 */
	private String password;
	/*
	 * 管理员手机号
	 */
	private String phone;
	/*
	 * 登录锁 三次输入机会
	 */
	private int lock;
	/*
	 * 锁定时间
	 */
	private Date lockTime;
	
	
	public Admin() {
		super();
	}

	public Admin(String id, String adminName, String account, String password, String phone, int lock, Date lockTime) {
		super();
		this.id = id;
		this.adminName = adminName;
		this.account = account;
		this.password = password;
		this.phone = phone;
		this.lock = lock;
		this.lockTime = lockTime;
	}

	public Admin(String id, String adminName, String account, String password, String phone) {
		super();
		this.id = id;
		this.adminName = adminName;
		this.account = account;
		this.password = password;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Admin [account=" + account + ", adminName=" + adminName + ", id=" + id + ", lock=" + lock + ", lockTime=" + DateUtil.parseDateToStr(lockTime, "yyyy-MM-dd|HH:mm:ss") + ", password=" + password + ", phone=" + phone + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getLock() {
		return lock;
	}
	public void setLock(int lock) {
		this.lock = lock;
	}
	public Date getLockTime() {
		return lockTime;
	}
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	
	
}
