package com.jay.FlashcardApp;

public class Flashcard {

    //instance variables
    private String question;
    private String answer;

    //constructor
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    //getters & setters
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

}
