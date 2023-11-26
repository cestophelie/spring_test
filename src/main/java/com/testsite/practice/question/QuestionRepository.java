package com.testsite.practice.question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
	// Following 'Subject', 'Content' are used as where clause conditions
	// 'and','or', 'between', 'lessthan' etc are possible
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);

}