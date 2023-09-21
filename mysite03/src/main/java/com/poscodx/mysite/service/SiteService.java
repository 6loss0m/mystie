package com.poscodx.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.SiteRepository;
import com.poscodx.mysite.vo.SiteVo;

@Service
public class SiteService {
	
	@Autowired
	private SiteRepository siteRepository;
	
	public SiteVo getSite(Long no) {
		return siteRepository.findByNo(no);
	}
	
	public void updateSite(SiteVo vo) {
		siteRepository.update(vo);
	}
}