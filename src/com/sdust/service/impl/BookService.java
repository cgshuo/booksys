package com.sdust.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdust.dao.IBookDao;
import com.sdust.entity.Book;
import com.sdust.service.IBookService;
import com.sdust.vo.PageBean;

@Service
public class BookService implements IBookService{
	
	@Autowired
	private IBookDao bookDao;
	
	@Override
	public PageBean<Book> findByPage(Integer page) {
		// TODO Auto-generated method stub
		
		if(page==null||page<1)
			throw new RuntimeException("页码数据有误");
		PageBean<Book> pageBean=new PageBean<>();
		pageBean.setCurrentPage(page);
		
		//获取所有的记录
		int count=0;
		try {
			count=bookDao.count();
			pageBean.setCount(count);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw e1;
		}
		
		//计算总页数
		if(count%pageBean.getSize()==0){
			pageBean.setTotalPage(count/pageBean.getSize());
		}else{
			pageBean.setTotalPage(count/pageBean.getSize()+1);
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("size", pageBean.getSize());	//每页显示的记录数
		int index=(page-1)*pageBean.getSize();	//根据页码计算分页
		map.put("index", index);				//设置分页时的开始索引
		
		try {
			List<Book> books=bookDao.findByIndex(map);
			pageBean.setPageInfos(books);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pageBean;
	}

	@Override
	public void add(Book book) {
		// TODO Auto-generated method stub
		if(book==null)
			throw new RuntimeException("图书信息不能为空");
		if(book.getStock()<1)
			throw new RuntimeException("请输入正确的库存");
		if(book.getPrice()<0)
			throw new RuntimeException("请输入正确的价格");
		try {
			bookDao.add(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
}
