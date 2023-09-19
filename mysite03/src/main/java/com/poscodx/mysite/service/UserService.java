package com.poscodx.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.UserRepository;
import com.poscodx.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private MailSender mailSender;

	public boolean addUser(UserVo vo) {
		/* 비즈니스 로직, sql 작성
		public void erpProcess01(UserVo vo) {
		erpEmpoyeeRepository.insert(vo);
		erpJob1Repository.insert(vo);
		erpSalaryRepository.insert(vo);
		}
		*/
		
		return userRepository.insert(vo);
		// java mail 시스템
//		mailSender.send(vo.getEmail(), "", "");
		// @Component
		// class MailSender (email, title, ... )
	}

	public UserVo getUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public UserVo getUser(Long no) {
		return userRepository.findByNo(no);
	}

	public boolean updateUser(UserVo userVo) {
		return userRepository.update(userVo);
	}

}
