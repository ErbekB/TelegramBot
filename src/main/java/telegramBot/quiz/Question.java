package telegramBot.quiz;

import java.util.List;

public class Question {
    private String title;
    private String text;
    private List<String> options;
    private String solution;

    public Question(String title, String text, List<String> options, String solution) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.solution = solution;
    }
}