package com.sdust.service;

import com.sdust.entity.Order;
import com.sdust.entity.OrderItem;
import com.sdust.vo.PageBean;

public interface IOrderService {
	//添加订单信息
	public Order addOrderInfo(String[] ids, String[] nums, Double price, String name);
	//添加订单明细
	public void addOrderItems(String[] ids, String[] nums, Order order);
	//根据页码查询订单明细(按订单数据分页)
	public PageBean<OrderItem> findItemByIndex(String name, int page);
}
