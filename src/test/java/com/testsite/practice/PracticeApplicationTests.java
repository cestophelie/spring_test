package com.testsite.practice;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest // referring the following class as the 'Test' class
class PracticeApplicationTests {
	@Autowired // Spring's 'DI(Data Injection) function. Automatically creates the 'questionRepository' object
	// @Autowired annotation preferably used in 'test' condition, because of the 'circular dependencies' issue 
	private QuestionRepository questionRepository;

	/*
	 * @Test void contextLoads() { }
	 */
	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("1. What is this project about?");
		q1.setContent("1. I want to know about the purpose of this project.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("2. What is this project about?");
		q2.setContent("2. I want to know about the purpose of this project.");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}
 
}
