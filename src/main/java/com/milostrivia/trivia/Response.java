package com.milostrivia.trivia;

public class Response {
    int id;
    String response;
    boolean is_correct;

    public Response(int id, String response, boolean correct) {
        this.id = id;
        this.response = response;
        this.is_correct = correct;
    }

    public boolean getIs_correct() {
        return is_correct;
    }

    public int getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }
}
