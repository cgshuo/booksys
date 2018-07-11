package com.sdust.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sdust.entity.Book;
import com.sdust.service.IBookService;
import com.sdust.vo.JsonBean;
import com.sdust.vo.PageBean;

@Controller
public class BookController {
	@Autowired
	private IBookService bookService;
	
	@RequestMapping(value="/books/page/{page}", method=RequestMethod.GET)
	//@PathVariable 表示从路径中取对应变量的值
	public @ResponseBody JsonBean findByPage(@PathVariable("page")Integer page){
		JsonBean bean=new JsonBean();
		try {
			PageBean<Book> infos=bookService.findByPage(page);
			bean.setCode(1);
			bean.setMsg(infos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping(value="/upload.do", method=RequestMethod.POST)
	public @ResponseBody JsonBean add(@RequestParam MultipartFile imgfile, Book book){
		JsonBean bean=new JsonBean();
		
		String fileName=imgfile.getOriginalFilename();		//获取文件名
		String path="D:/software production/upload";		//存储上传文件的位置
		File d=new File(path);
		if(!d.exists()){
			d.mkdir();
		}
		File file=new File(path,fileName);
		try {
			imgfile.transferTo(file);
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		book.setImg("images/"+fileName);					//设置文件路径
		
		try {
			bookService.add(book);
			bean.setCode(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
}
