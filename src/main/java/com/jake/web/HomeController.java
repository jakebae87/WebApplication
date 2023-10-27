package com.jake.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		// Logger Message Test
		/*
		// 1) {} 활용
		String name = "홍길동";
		int age = 20;
		logger.info("Test 1 안녕하세요.");
		logger.info("Test 2 안녕하세요.{} 님", name);

		// 2) 로깅 레벨 조정
		logger.error("로깅레벨 테스트 error: name={}, age={}", name, age);
		logger.warn("로깅레벨 테스트 warn: name={}, age={}", name, age);
		logger.info("로깅레벨 테스트 info: name={}, age={}", name, age);
		logger.debug("로깅레벨 테스트 debug: name={}, age={}", name, age);
		logger.trace("로깅레벨 테스트 trace: name={}, age={}", name, age);
		*/
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

}
