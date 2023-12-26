package com.tejas.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tejas.demo.dao.QuestionDAO;
import com.tejas.demo.model.Question;
import com.tejas.demo.model.QuestionWrapper;
import com.tejas.demo.model.Response;

@Service  //Also can use instead of @Component
public class QuestionService {
	
	@Autowired
	QuestionDAO questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}

	public Question addQuestion(Question question) {
		return questionDao.save(question);
		// TODO Auto-generated method stub
		
	}

	public void delQuestion(int id) {
		// TODO Auto-generated method stub
		questionDao.deleteById(id);
		
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int numberOfQuestions) {
		List<Integer> questions = questionDao.findRandomQuestionByCategory(category,numberOfQuestions);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		// TODO Auto-generated method stub
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		
		for(Integer id : questionIds) {
			questions.add(questionDao.findById(id).get());
		}
		
		for(Question q : questions) {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(q.getId());
			wrapper.setQuestionTitle(q.getQuestionTitle());
			wrapper.setOption1(q.getOption1());
			wrapper.setOption2(q.getOption2());
			wrapper.setOption3(q.getOption3());
			wrapper.setOption4(q.getOption4());
			wrappers.add(wrapper);
			
		}
		
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int right=0;
		
		for(Response response:responses) {
			Question q = questionDao.findById(response.getId()).get();
			if(response.getResponse().equals(q.getRightAnswer())) {
				right++;
			}
		
		}
		
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
