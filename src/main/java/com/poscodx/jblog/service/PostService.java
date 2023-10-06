package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.PostRepository;
import com.poscodx.jblog.vo.PostVo;
@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<PostVo> getPostsByCategory(Long long1) {
		// TODO Auto-generated method stub
		return null;
	}

	public PostVo getPostByNo(Long long1) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addPost(PostVo postVo) {
		return postRepository.addPost(postVo);
		
	}

}
