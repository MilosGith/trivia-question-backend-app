package com.milostrivia.trivia.controller;

import com.milostrivia.trivia.*;
import com.milostrivia.trivia.service.TriviaQuestionService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class TriviaQuestionController {

    @Autowired
    TriviaQuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<List<TriviaQuestion>> questions(@RequestParam(value = "amount", defaultValue = "5") int amount) throws ParseException {
        return ResponseEntity.ok(questionService.getTriviaQuestions(amount));
    }

    @PostMapping("/checkanswer")
    public Response checkanswer(@RequestBody Answer answer) {
        return TriviaQuestionStorage.getInstance().checkAnswer(answer);
    }
}

