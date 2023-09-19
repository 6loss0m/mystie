package com.poscodx.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.GuestbookRepository;
import com.poscodx.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;
	// singletone을 위해, 따로 new 를 하지 않고, 해당 container 내에서 사용할 수 있는 repository 객체를 생성

	public List<GuestbookVo> getContentsList() {
		return guestbookRepository.findAll();
	}
	
	public Boolean deleteContents(Long no, String password) {
		return guestbookRepository.deleteByNoAndPassword(no, password);
	}
	
	public Boolean addContents(GuestbookVo vo) {
		return guestbookRepository.insert(vo);
	}
	
}
