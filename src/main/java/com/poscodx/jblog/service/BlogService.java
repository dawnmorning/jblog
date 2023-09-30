package com.poscodx.jblog.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.repository.PostRepository;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostRepository postRepository;

	public Map<String, Object> getBlogInfo(String blogId, Optional<Long> pathCategory, Optional<Long> pathPost) {
Map<String,Object> blogInfoMap = new HashMap<String, Object>();
		
		blogInfoMap.put("categoryList", categoryRepository.getCategoryList(blogId));
		blogInfoMap.put("blogVo", blogRepository.getInfoById(blogId));
		
		Long categoryNo = 1L;
		Long postNo = 1L;
		if( pathPost.isPresent() ) {
			postNo = pathPost.get();
			categoryNo = pathCategory.get();
			
			blogInfoMap.put("postTitleList", postRepository.getVoListByCategoryNo(categoryNo)); 
			blogInfoMap.put("postVo",postRepository.getVoByPostNo(postNo));
			
		} else if( pathCategory.isPresent() ){
			categoryNo = pathCategory.get();
			
			blogInfoMap.put("postTitleList", postRepository.getVoListByCategoryNo(categoryNo)); 
			blogInfoMap.put("postVo",postRepository.getVoByPostNo(postRepository.getDefaultPostNoByCategoryNo(categoryNo)));
			
		}
		else {
			blogInfoMap.put("postTitleList", postRepository.getVoListByCategoryNo(postRepository.getDefaultCategoryNoById(blogId))); //path가 아이디만 들어왔을때 첫번째 카테고리의 첫번째 글
			blogInfoMap.put("postVo",postRepository.getVoByPostNo(postRepository.getDefaultPostNoByCategoryNo(categoryRepository.getDefaultCategoryNoById(blogId))));
		}
		return blogInfoMap;
	}
}
