package com.milostrivia.trivia;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.milostrivia.trivia.Util.decodeBase64;

@Component
public class TriviaQuestionStorage {

    private static TriviaQuestionStorage instance = null;
    private final AtomicInteger id = new AtomicInteger(0);

    private final ArrayList<TriviaQuestion> questionStorage;
    private final HashMap<Integer, String> correctAnswers;

    private TriviaQuestionStorage() {
        questionStorage  = new ArrayList<>();
        correctAnswers = new HashMap<>();
    }

    public static TriviaQuestionStorage getInstance() {
        if (instance == null) instance = new TriviaQuestionStorage();
        return instance;
    }

    public void addQuestion(TriviaQuestion question, String correctAnswer) {
        questionStorage.add(question);
        correctAnswers.put(question.id, correctAnswer);
    }

    public Response checkAnswer(Answer answer) {
        int id = answer.getId();
        if (correctAnswers.containsKey(id)) {
            return new Response(id, answer.getAnswer(), correctAnswers.get(id).equalsIgnoreCase(answer.getAnswer()));
        } else {
            return new Response(id, answer.getAnswer(), false);
        }
    }

    public String getAnswerForQuestion(int id) {
        if (correctAnswers.containsKey(id)) return correctAnswers.get(id);
        return null;
    }

    public TriviaQuestion getQuestionByID(int id) {
        for (TriviaQuestion question : questionStorage) {
            if (question.getId() == id) return question;
        }
        return null;
    }

    public List<TriviaQuestion> getAllQuestions() {
        return questionStorage;
    }

    public void clearStorage() {
        questionStorage.clear();
        correctAnswers.clear();
        id.getAndSet(0);
    }

    public int getNewID() {
        return id.getAndIncrement();
    }

}
