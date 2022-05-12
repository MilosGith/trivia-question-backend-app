package com.milostrivia.trivia;


public class Answer {
    int id;
    String answer;

    public Answer(int id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public Response checkAnswer(int id, String response) {
        System.out.println(response);
        if (TriviaQuestionStorage.getInstance().checkAnswer(id, response)) return new Response(id, true);
        else return new Response(id, false);
    }

    public int getId() {
        return id;
    }

    public String getString() {
        return answer;
    }
}
