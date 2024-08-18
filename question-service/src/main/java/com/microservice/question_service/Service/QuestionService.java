package com.microservice.question_service.Service;

import com.microservice.question_service.Dao.QuestionDao;
import com.microservice.question_service.Model.Question;
import com.microservice.question_service.Model.QuestionWrapper;
import com.microservice.question_service.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questiondao;
    public ResponseEntity<List<Question>> getAllQuestion() {
        try{
            return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity< List<Question>> getByCategory(String s) {
        try{
            return new ResponseEntity<>(questiondao.findByCategory(s),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> postAQuestion(Question q) {
        questiondao.save(q);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions){
        List<Integer> questions = questiondao.findRandomQuestionsByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id:questionIds){
            questions.add(questiondao.findById(id).get());
        }
        for(Question question:questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right = 0;
        for(Response response:responses){
            Question question = questiondao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())) {
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
