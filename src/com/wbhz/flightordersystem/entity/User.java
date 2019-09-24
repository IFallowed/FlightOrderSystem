/**
 * 
 */
package com.wbhz.flightordersystem.entity;

import java.util.Date;

import com.wbhz.flightordersystem.util.DateUtil;

/**
 * @Description:会员实体类
 * @author soft01
 * @version: 1.0
 */
public class User {
	/*
	 * 身份证号
	 */
	private String id;
	/*
	 * 用户名
	 */
	private String acount;
	/*
	 * 密码
	 */
	private String password;
	/*
	 * 姓名
	 */
	private String userName;
	/*
	 * 性别
	 */
	private String sex;
	/*
	 * 电话
	 */
	private String phone;
	/*
	 * 邮箱
	 */
	private String mail;
	/*
	 * 地址
	 */
	private String address;
	/*
	 * 注册时间
	 */
	private Date createTime;
	/*
	 * 登录锁
	 */
	private int lock;
	/*
	 * 锁时间
	 */
	private Date lockTime;
	

	
	public User() {
		super();
	}

	public User(String id, String acount, String password, String userName, String sex, String phone, String mail, String address, Date createTime) {
		super();
		this.id = id;
		this.acount = acount;
		this.password = password;
		this.userName = userName;
		this.sex = sex;
		this.phone = phone;
		this.mail = mail;
		this.address = address;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "User [acount=" + acount + ", address=" + address + 
		", createTime=" + DateUtil.parseDateToStr(createTime, "yyyy-MM-dd|HH:mm:ss") + 
		", id=" + id + ", lock=" + lock + ", lockTime=" + 
		DateUtil.parseDateToStr(lockTime, "yyyy-MM-dd|HH:mm:ss") + ", mail=" + mail + 
		", password=" + password + ", phone=" + phone + ", sex=" + sex + ", userName=" + userName + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcount() {
		return acount;
	}

	public void setAcount(String acount) {
		this.acount = acount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		if("女".equals(sex) || "nv".equals(sex)){
			this.sex = "女";
		}
		else{
			this.sex = "男";
		}
		
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
