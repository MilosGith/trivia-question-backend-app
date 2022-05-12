package com.milostrivia.trivia;

import java.util.ArrayList;
import java.util.Collections;

public class TriviaQuestion {
    int id;
    String questionText;
    ArrayList<String> possibleAnswers;

    public TriviaQuestion(int id, String question, String answer, ArrayList<String> possibleAnswers) {
        this.id = id;
        this.questionText = question;
        this.possibleAnswers = possibleAnswers;
        possibleAnswers.add(answer);
        Collections.shuffle(possibleAnswers);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("id: " + id + "\n" +
                      "Question: " + questionText + "\n" +
                      "Wrong answers: ");

        for (String obj : possibleAnswers) {
            string.append(obj + " | ");
        }
        string.append("\n");
        return string.toString();
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public String getQuestionText() {
        return questionText;
    }

}
