package com.testsite.practice.answer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{
	Answer findByContent(String subject); // The name of the column has to be the same as the variable name
}
