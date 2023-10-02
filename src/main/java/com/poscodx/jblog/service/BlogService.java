package com.poscodx.jblog.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public BlogVo getBlog(String blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CategoryVo> getCategoriesById(String blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PostVo> getPostsByCategory(Long no) {
		// TODO Auto-generated method stub
		return null;
	}

	public com.poscodx.jblog.vo.PostVo getPostByNo(Long long1) {
		// TODO Auto-generated method stub
		return null;
	}

}
