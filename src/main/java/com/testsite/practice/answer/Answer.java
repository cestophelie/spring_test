package com.testsite.practice.answer;

import java.time.LocalDateTime;

import com.testsite.practice.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	 
	private LocalDateTime createDate;
		
	@ManyToOne // 1:N = question:answers
	private Question question; // 'question' field added to refer to the 'question entity'

	
}
