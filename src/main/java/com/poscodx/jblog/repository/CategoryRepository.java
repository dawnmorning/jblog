package com.poscodx.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> getCategoriesById(String blogId) {
		return sqlSession.selectList("category.getCategoriesById", blogId);
	}

	public boolean add(CategoryVo categoryVo) {
		int count = sqlSession.insert("category.add", categoryVo);
		return count == 1;
	}
	
	
}
