package com.example.anticovid.Model;

public class FAQ {
    private int resourceImg;
    private String question;
    private String answer;
    private boolean expandable;

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public int getResourceImg() {
        return resourceImg;
    }

    public FAQ(int resourceImg , String question, String answer) {
        this.resourceImg =resourceImg;
        this.question = question;
        this.answer = answer;
        this.expandable=false;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

