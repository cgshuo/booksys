package com.sdust.dao;

import java.util.List;
import java.util.Map;

import com.sdust.base.IBaseDao;
import com.sdust.entity.OrderItem;

public interface IOrderItemDao extends IBaseDao<OrderItem> {
	//²éÑ¯¶©µ¥Ã÷Ï¸
	public List<OrderItem> findByIndex(Map<String, Object> info);
}
