package com.sdust.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sdust.entity.User;
import com.sdust.service.ILoginService;
import com.sdust.vo.JsonBean;

@Controller
public class LoginController {
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody JsonBean login(@RequestParam("userName") String username, @RequestParam("password") String pwd,
			HttpSession session, HttpServletResponse response){
		JsonBean bean=new JsonBean(); 
		try {
			loginService.login(username, pwd);
			//登录成功，将用户名存放到session对象中
			session.setAttribute("loginname", username);
			String sessionId=session.getId();
			Cookie cookie=new Cookie("JSESSIONID", sessionId);
			cookie.setMaxAge(1800);
			response.addCookie(cookie);
			
			bean.setCode(1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;	
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public @ResponseBody JsonBean register(User user){
		JsonBean bean=new JsonBean();
		try {
			loginService.register(user);
			bean.setCode(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping("/check")
	public @ResponseBody JsonBean checkUser(String userName){
		JsonBean bean=new JsonBean();
		try {
			boolean ret=loginService.userIsExist(userName);
			if(ret==true){
				bean.setCode(-1);
			}else{
				bean.setCode(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bean.setCode(0);
		}
		return bean;
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public @ResponseBody JsonBean logout(HttpSession session, HttpServletResponse response){
	    JsonBean bean=new JsonBean();

        //获取session对象中存放的用户名
        String name=(String)session.getAttribute("loginname");
        try {
            boolean login=loginService.userIsLogin(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }
	
}
