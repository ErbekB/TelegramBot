package telegramBot.quiz;

import java.util.List;
import java.util.Random;

public class Game {
    private List<Question> questionsList = this.initQuestions();

    private int score = 0;

    public String start() {
        String start = """
                Great choice! 🚀 You've just started an exciting quiz game. 
                Answer questions correctly to earn points and climb the leaderboard.
                """;
        return start;
    }

    public Question getQuestion(){
        Random r = new Random();
        int index = r.nextInt(0, this.questionsList.size());
        return (Question) this.questionsList.get(index);
    }
    private List<Question> initQuestions() {
        return List.of(
                new Question("Japan", "What is the name of the popular Japanese dish made from fermented soybeans?",
                        List.of("Sushi", "Ramen", "Miso", "Tempura"), "Miso"),
                new Question("Shakespeare", "In which famous Shakespearean play does the character Othello appear?",
                        List.of("Macbeth", "Romeo and Juliet", "Othello", "Hamlet"), "Othello"),
                new Question("Human Body", "What is the largest organ in the human body?",
                        List.of("Brain", "Liver", "Heart", "Skin"), "Skin"),
                new Question("Paintings", "Who painted the famous artwork \"Mona Lisa\"?",
                        List.of("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"), "Leonardo da Vinci"));
    }
}
