/**
 * 
 */
package com.wbhz.flightordersystem.entity;

/**
 * @Description: 订单实体类
 * @author soft02
 * @version 1.0
 */
public class Order {
	/*
	 * 订单号
	 */
	private String orderId;
	/*
	 * 航班号
	 */
	private String flightId;
	/*
	 * 订单用户身份证号
	 */
	private String UserId;
	/*
	 * 预订座位号
	 */
	private String seat;
	/*
	 * 订单状态
	 */
	private String state;

	public Order() {
		super();
	}

	public Order(String orderId, String flightId, String userId, String seat, String state) {
		super();
		this.orderId = orderId;
		this.flightId = flightId;
		UserId = userId;
		this.seat = seat;
		this.state = state;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		return "\norderId:" + orderId + "\nUserId:" + UserId + "\nflightId:" + flightId + ", seat:" + seat + "号\nstate:" + state;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
