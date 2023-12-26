package com.tejas.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.demo.model.Question;
import com.tejas.demo.model.QuestionWrapper;
import com.tejas.demo.model.Response;
import com.tejas.demo.service.QuestionService;


@RestController
@RequestMapping("questions")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion(){
		return (ResponseEntity<List<Question>>) questionService.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getAllQuestionByCategory(@PathVariable String category){
		return (ResponseEntity<List<Question>>) questionService.getAllQuestionsByCategory(category);
	}
	
	@PostMapping("addQuestion")
	public String addQuestion(@RequestBody Question question) {
		questionService.addQuestion(question);
		return "Success";
	}
	
	@DeleteMapping("delQuestion/{id}")
	public String delQuestion(@PathVariable int id) {
		questionService.delQuestion(id);
		return "Deleted Successfully";
	}
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam int numberOfQuestions){
		return questionService.getQuestionsForQuiz(category,numberOfQuestions);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
		return questionService.getQuestionsFromId(questionIds);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.getScore(responses);
	}
	
	

}
