package com.poscodx.mysite.event;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class ApplicationContextEventListener {
	/* Event Bean인지 Controller Bean인지 어떻게 알 수 있나?
	 * -> 각 bean등록 되어있는 애들은 scan함. (메소드 스캔)
	 * @EventListener가 모두 method 스캔을 해서 아무 곳에 있어도 됨.
	 * 
	 */
	@EventListener({ContextRefreshedEvent.class})
	public void handlerContextRefreshedEvent() {
		System.out.println("--- Context Refreshed Event Received ---");
		// refresh 될때( bean 바꾸거나, 
	}
}
