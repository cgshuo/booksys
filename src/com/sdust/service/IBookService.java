package com.sdust.service;

import com.sdust.entity.Book;
import com.sdust.vo.PageBean;

public interface IBookService {
	//根据页码进行分页查询
	public PageBean<Book> findByPage(Integer page);
	
	public void add(Book book);
}
