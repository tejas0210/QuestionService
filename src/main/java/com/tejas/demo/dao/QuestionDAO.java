package com.tejas.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tejas.demo.model.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Integer>{
	
	List<Question> findByCategory(String category);

	
	@Query(value="SELECT q.id FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ",nativeQuery=true)
	List<Integer> findRandomQuestionByCategory(String category, int numQ);

}
