package com.milostrivia.trivia.service;

import com.milostrivia.trivia.TriviaQuestion;
import com.milostrivia.trivia.TriviaQuestionStorage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.milostrivia.trivia.Util.decodeBase64;

@Component
public class TriviaQuestionService {

    public List<TriviaQuestion> getTriviaQuestions(int amount) {
        try {
            TriviaQuestionStorage.getInstance().clearStorage();
            URL url = new URL("https://opentdb.com/api.php?amount=" + amount + "&encode=base64");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder body = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    body.append(scanner.nextLine());
                }

                scanner.close();
                processGetResult(String.valueOf(body));
            }

        } catch (Exception e) {

        }
        return TriviaQuestionStorage.getInstance().getAllQuestions();
    }

    private static void processGetResult(String body) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(body);
        JSONArray results = (JSONArray) obj.get("results");
        for (Object result : results) {
            JSONObject question = (JSONObject) result;
            String questionText = decodeBase64((String) question.get("question"));
            String correctAnswer = decodeBase64((String) question.get("correct_answer"));
            JSONArray wrongAnswers = (JSONArray) question.get("incorrect_answers");
            ArrayList<String> wrongAnswerList = new ArrayList<String>();
            if (wrongAnswers != null) {
                for (Object wrongAnswer : wrongAnswers) {
                    wrongAnswerList.add(decodeBase64((String) wrongAnswer));
                }
            }

            TriviaQuestion newQuestion = new TriviaQuestion(TriviaQuestionStorage.getInstance().getNewID(), questionText, correctAnswer, wrongAnswerList);
            TriviaQuestionStorage.getInstance().addQuestion(newQuestion, correctAnswer);
            //System.out.println(newQuestion);

            //System.out.println(TriviaQuestionStorage.getInstance().getQuestion(0).toString());
        }
    }
}
