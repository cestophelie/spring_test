package com.testsite.practice;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.testsite.practice.answer.Answer;
import com.testsite.practice.answer.AnswerRepository;
import com.testsite.practice.question.Question;
import com.testsite.practice.question.QuestionRepository;


@SpringBootTest // referring the following class as the 'Test' class
class PracticeApplicationTests {
	@Autowired // Spring's 'DI(Data Injection) function. Automatically creates the 'questionRepository' object
	// The annotation preferably used in 'test' condition, because of the 'circular dependencies' issue 
	private QuestionRepository questionRepository;
	
	
	@Autowired
	private AnswerRepository answerRepository;

	
	@Transactional // DB Session maintaining in test environment
	@Test
	void testJpa() {
		// 1)
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
		
		
		// 2) SELECT
		 List<Question> all = this.questionRepository.findAll();
		assertEquals(22, all.size()); // checking the size of the db table
		
		Question q =  all.get(0); // getting the 0 row to check if the val is equal
		assertEquals("1. What is this project about?", q.getSubject());
		
		// Optional class for the management of null value
		 Optional <Question> oq = this.questionRepository.findById(1);
		 if (oq.isPresent()) { // when not null
			 q = oq.get();
			 assertEquals("1. What is this project about?", q.getSubject()); 
		 }
		
		// 3) The name of the repository used for the where conditions columns
		q = this.questionRepository.findBySubject("1. What is this project about?");
		assertEquals(1, q.getId());
		
		q = this.questionRepository.findBySubjectAndContent("2. What is this project about?", "2. I want to know about the purpose of this project.");
		assertEquals(2, q.getId());
		
		
		// 4) Update the previous value using SAVE METHOD. If the value is not null, update the q2 subject value
		assertTrue(oq.isPresent()); // boolean value check
		q = oq.get();
		q.setSubject("changed version2");
		this.questionRepository.save(q);
		
		// 5. Delete the previous value using the DELETE METHOD
		Optional<Question> del = this.questionRepository.findById(1);
		assertTrue(del.isPresent());
		q = del.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count()); // Checking if two params are the same value
		
		
		// 6. Adding the answer to a question
		Optional<Question> oq2 = this.questionRepository.findById(2);
		assertTrue(oq2.isPresent());
		Question q3 = oq2.get();
		
		Answer a = new Answer();
		a.setContent("This project is about practicing springboot.");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q3);
		this.answerRepository.save(a);
		
		
		// 7. Checking the insert data
		List<Answer> answers = this.answerRepository.findAll();
		assertEquals(2, answers.size()); // checking the size of the db table
		
		a =  answers.get(0); // getting the 0 row to check if the val is equal
		assertEquals("This project is about practicing springboot.", a.getContent());
		System.out.println(a.getContent());
		
		
	}
}