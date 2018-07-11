package com.sdust.dao;

import com.sdust.base.IBaseDao;
import com.sdust.entity.User;

public interface IUserDao extends IBaseDao<User>{
	
	//根据用户名查询用户信息
	public User findByName(String name);
	
}
