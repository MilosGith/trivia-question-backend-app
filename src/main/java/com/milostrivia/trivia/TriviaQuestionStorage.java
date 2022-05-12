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

    public Boolean checkAnswer(int id, String answer) {
        System.out.println("from post request: " + answer);
        System.out.println("From db: " + (correctAnswers.get(id)));
        System.out.println(getQuestionByID(id).questionText);
        if (correctAnswers.containsKey(id)) return correctAnswers.get(id).equalsIgnoreCase(answer);
        else return false;
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

    public TriviaQuestion getQuestion(int index) {
        if (index < questionStorage.size()) return questionStorage.get(index);
        else return null;
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
