/**
 * 
 */
package com.wbhz.flightordersystem.test.ui;

import org.junit.Test;

import com.wbhz.flightordersystem.ui.admin.AdminUI;
import com.wbhz.flightordersystem.ui.admin.DeleteFlight;
import com.wbhz.flightordersystem.ui.admin.InsertFlight;
import com.wbhz.flightordersystem.ui.admin.QueryFlight;
import com.wbhz.flightordersystem.ui.admin.UpdateFlight;

/**
 * @Description:管理员界面测试
 * @author soft01类
 * @version: 1.0
 */
public class TestAdminUI {
	

	/**
	 * 
	 * @Description: 测试管理员界面
	 * @Return Type:void
	 */
	@Test
	public void testAdminUI() {
		AdminUI.init();
	}
	
	/**
	 * 
	 * @Description: 测试航班查询
	 * @Return Type:void
	 */
	@Test
	public void testQueryFlight() {
		QueryFlight.init();
	}
	
	/**
	 * 
	 * @Description: 测试航班删除
	 * @Return Type:void 
	 *
	 */
	@Test
	public void testDelFlight()  {
		DeleteFlight.init();
	}
	
	/**
	 * 
	 * @Description: 测试航班修改
	 * @Return Type:void
	 * @throws Exception 
	 *
	 */
	@Test
	public void testUpdateFlight(){
		UpdateFlight.init();
	}
	
	/**
	 * 
	 * @Description: 测试航班添加
	 * @Return Type:void
	 * @throws Exception 
	 *
	 */
	@Test
	public void testInsertFlight() {
		InsertFlight.init();
	}
}
