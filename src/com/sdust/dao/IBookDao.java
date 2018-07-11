package com.sdust.dao;

import java.util.List;
import java.util.Map;

import com.sdust.base.IBaseDao;
import com.sdust.entity.Book;

public interface IBookDao extends IBaseDao<Book>{
	
	//进行分页查询，索引、几条记录
	//public List<Book> findByIndex(Integer index, Integer size);
	public List<Book> findByIndex(Map<String, Object> pageInfo);
	
	// 根据相关的书籍id进行查询
	public List<Book> findByIds(List<String> ids);
	
	//根据id查询库存
	public Integer findStockById(Integer id);
}
