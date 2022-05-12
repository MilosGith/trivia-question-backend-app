package com.milostrivia.trivia;

public class Response {
    int id;
    String response;

    public Response(int id, boolean correct) {
        this.id = id;
        if (correct) this.response = "The answer is correct";
        else this.response = "The answer is not correct";
    }

    public int getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }
}
