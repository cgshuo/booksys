package com.sdust.service;

import com.sdust.entity.User;

public interface ILoginService {
	public void login(String name,String pwd);		//µÇÂ¼
	public void register(User user);				//×¢²á
	public boolean userIsExist(String name);
	public boolean userIsLogin(String name);
}
