package com.sdust.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdust.entity.OrderItem;
import com.sdust.service.impl.OrderService;
import com.sdust.vo.JsonBean;
import com.sdust.vo.PageBean;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/orderinfo", method=RequestMethod.POST)
	public @ResponseBody JsonBean addOrder(String[] ids, String[] nums, Double totalPrice,
			HttpSession session, HttpServletResponse response){
		JsonBean bean=new JsonBean();
		//获取session对象中存放的用户名
		String name=(String)session.getAttribute("loginname");
		try {
			//添加订单
			orderService.addOrderInfo(ids, nums, totalPrice, name);
			//orderService.addOrderItems(ids, nums, order);
			
			//添加订单成功，清空购物车
			Cookie cookie=new Cookie("cartids","");
			cookie.setMaxAge(0);
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
	
	@RequestMapping(value="/orders/page/{page}", method=RequestMethod.GET)
	public @ResponseBody JsonBean findOrderInfo(@PathVariable("page") int page, HttpSession session){
		JsonBean bean=new JsonBean();
		
		String name=(String) session.getAttribute("loginname");
		try {
			PageBean<OrderItem> pageBean=orderService.findItemByIndex(name, page);
			bean.setCode(1);
			bean.setMsg(pageBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
	
}
