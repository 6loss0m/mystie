package com.poscodx.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.SiteVo;

@Repository
public class SiteRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public SiteVo findByNo(Long no) {
		return sqlSession.selectOne("site.findByNo", no);
	}
	
	public void update(SiteVo vo) {
		sqlSession.update("site.update", vo);
	}
}