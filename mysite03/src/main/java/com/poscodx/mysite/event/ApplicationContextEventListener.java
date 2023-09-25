package com.poscodx.mysite.event;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;

public class ApplicationContextEventListener {
	/* Event Bean인지 Controller Bean인지 어떻게 알 수 있나?
	 * -> 각 bean등록 되어있는 애들은 scan함. (메소드 스캔)
	 * @EventListener가 모두 method 스캔을 해서 아무 곳에 있어도 됨.
	 */
	// 1. 주입 해서 사용
	@Autowired
	private SiteService siteService;
	
	// 2. container 주입
	@Autowired
	private ApplicationContext applicationContext;
	
	// event 될 때, 초기화 될 때 사용
	@EventListener({ContextRefreshedEvent.class})
	public void handlerContextRefreshedEvent() {
		System.out.println("--- Context Refreshed Event Received ---");
		
		// 주입 받아서 SiteService 주입 받음
		SiteService siteService = applicationContext.getBean(SiteService.class);
		SiteVo site = siteService.getSite(1L);
		
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("title", site.getTitle());
		propertyValues.add("profile", site.getProfile());
		propertyValues.add("welcome", site.getWelcome());
		propertyValues.add("description", site.getDescription());

		// container 안에 코드 등록
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(SiteVo.class);
		beanDefinition.setPropertyValues(propertyValues);

		AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry)factory;
		registry.registerBeanDefinition("site", beanDefinition);	// 만들어 놓은 bean을 등록 할 수 없고, 정보를 만든 뒤 써야함.
		
	}
}
