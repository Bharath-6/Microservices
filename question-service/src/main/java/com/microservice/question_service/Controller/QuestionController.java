package com.microservice.question_service.Controller;

import com.microservice.question_service.Model.Question;
import com.microservice.question_service.Model.QuestionWrapper;
import com.microservice.question_service.Model.Response;
import com.microservice.question_service.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionservice;
    @GetMapping("allquestions")
    public ResponseEntity<List<Question>>  getAllQuestion(){
        return questionservice.getAllQuestion();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category){
        return questionservice.getByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String> postAQuestion(@RequestBody Question q){
        return questionservice.postAQuestion(q);
    }
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions){
        return questionservice.getQuestionsForQuiz(categoryName,numQuestions);
    }
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestonsFromId(@RequestBody List<Integer> questionIds){
        return questionservice.getQuestionFromId(questionIds);
    }
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionservice.getScore(responses);
    }
}

//these are the things also we need to implement in question
// generate
// getQuestion (questionid)
// getScore



