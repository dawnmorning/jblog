package com.poscodx.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;
	public boolean addPost(PostVo postVo) {
		int count = sqlSession.insert("post.addPost", postVo);
		return count == 1;
	}
	public boolean delete(Long postNo) {
		int count = sqlSession.delete("post.delete", postNo);
		return count == 1;
	}

}