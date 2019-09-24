/**
 * 
 */
package com.wbhz.flightordersystem.entity;

import java.util.Date;

import com.wbhz.flightordersystem.util.DateUtil;
import com.wbhz.flightordersystem.util.SeatsUtil;

/**
 * @Description:航班实体类
 * @author soft01
 * @version: 1.0
 */
public class Flight {
	/*
	 * 航班号
	 */
	private String flightId;
	/*
	 * 出发时间
	 */
	private Date takeOffTime;
	/*
	 * 飞行时间
	 */
	private String flyingTime;
	/*
	 * 出发地
	 */
	private String startPlace;
	/*
	 * 目的地
	 */
	private String endPlace;
	/*
	 * 余票情况
	 */
	private String tickets;
	/*
	 * 票价
	 */
	private float price;
	
	public Flight() {
		super();
	}

	public Flight(String flightId, Date takeOffTime, String flyingTime, String startPlace, String endPlace, String tickets, float price) {
		super();
		this.flightId = flightId;
		this.takeOffTime = takeOffTime;
		this.flyingTime = flyingTime;
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.tickets = tickets;
		this.price = price;
	}
	
	

	@Override
	public String toString() {
		return "航班号： " + flightId + 
				"\n出发时间： " + DateUtil.parseDateToStr(takeOffTime, "yyyy-MM-dd|HH:mm:ss") + 
				"\n飞行时间： " + flyingTime + 
				"\n出发地： " + startPlace + 
				"\n目的地： " + endPlace + 
				"\n票价： " + price + 
				"\n余票情况:\n" + remainTickets();
	}
	
	/**
	 * 
	 * @Description: 统计剩余多少张票
	 * @Return Type int
	 * @return
	 */
	private int remainTickets(){
		String str = tickets;
		String s = "-0-";
		//总座位数
		int total = Integer.parseInt(str.substring(0, str.indexOf("-")));
		int count = 0;
		//计算已预订的座位数
		while(str.indexOf(s) != -1){
			str = str.substring(str.indexOf(s)+1,str.length());       
			count++;
      }
		return total - count;
	}
	/**
	 * 
	 * @Description: 打印航班的所有的座位（_表示未订票，*表示已订票）
	 * @Return Type:String
	 * @return
	 */
	public String printSeats(){
		/*解析数据库中的余票记录，例如“10-1-2-3-4-5-6-7-8-9-10”以-拆分字符串
		 *第一个字符串为总座位数，后面若字符串为“0”则为已订票，否则为未订票
		 */
		String[] seats = SeatsUtil.string2Array(tickets);
		StringBuffer sb = new StringBuffer();
		int totalSeats = Integer.parseInt(seats[0]);
		int rows = totalSeats % 10 == 0 ? totalSeats / 10 : totalSeats / 10 + 1;
		sb.append("  1 2 3   4 5 6 7   8 9 10" +"\n");
		for(int i = 0; i < rows; i++){
			sb.append((i + 1) + " ");
			for(int j = 1; j <= 10; j++){
				if(j == 4 || j == 8){
					sb.append("  ");
				}
				if(!"0".equals(seats[10 * i + j])){
					sb.append("☐ ");
				}
				else {
					sb.append("☒ ");
				}
			}
			sb.replace(sb.length() - 1, sb.length(), "\n");
		}
		return sb.toString();
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public Date getTakeOffTime() {
		return takeOffTime;
	}

	public void setTakeOffTime(Date takeOffTime) {
		this.takeOffTime = takeOffTime;
	}

	public String getFlyingTime() {
		return flyingTime;
	}

	public void setFlyingTime(String flyingTime) {
		this.flyingTime = flyingTime;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}

	public String getTickets() {
		return tickets;
	}

	public void setTickets(String tickets) {
		this.tickets = tickets;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
