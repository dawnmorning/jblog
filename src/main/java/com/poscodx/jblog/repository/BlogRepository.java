package com.poscodx.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public Object getInfoById(String blogId) {
		// TODO Auto-generated method stub
		return null;
	}
}
