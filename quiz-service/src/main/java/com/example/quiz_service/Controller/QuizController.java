package com.example.quiz_service.Controller;


import com.example.quiz_service.Model.QuestionWrapper;
import com.example.quiz_service.Model.QuizDto;
import com.example.quiz_service.Model.Response;
import com.example.quiz_service.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizservice;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizdto){
        return quizservice.createQuiz(quizdto.getCategoryName(),quizdto.getNumQuestions(),quizdto.getTitle());
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        return quizservice.getQuizQuestions(id);
    }
    @GetMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses ){
        return quizservice.calculateResult(id, responses);
    }
}
