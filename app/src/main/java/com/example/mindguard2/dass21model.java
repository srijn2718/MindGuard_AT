package com.example.mindguard2;

public class dass21model {
    private String question;
    private int selectedOption; // Add this variable to hold the selected state

    public dass21model(String question, int selectedOption) {
        this.question = question;
        this.selectedOption = selectedOption;
    }

    public String getQuestion() {
        return question;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }
}

