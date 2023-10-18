package telegramBot.quiz;

import java.util.List;
import java.util.Random;

public class Game {

    private List<Question> questionsList = this.initQuestions();
    private String rightAnswer;
    private int gameStatus;
    private int points = 0;

    public String start() {                                                     // The User starts the Game and receives a Message
        String start = """
                Great choice! 🚀 You've just started an exciting quiz game. 
                Answer questions correctly to earn points and climb the leaderboard.
                """;
        gameStatus = 1;
        return start;
    }

    public boolean state() {
        int gameLength = 3;
        if (gameStatus < gameLength) {
            gameStatus += 1;
            return true;
        } else {
            return false;
        }
    }

    public String getQuestion() {
        Random r = new Random();
        int index = r.nextInt(0, this.questionsList.size());              // Gets a random Question from the List

        setRightAnswer(questionsList.get(index).getSolution());                 // Sets the "rightAnswer" attribute
        return this.questionsList.get(index).toString();
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String checkAnswer(String answer) {
        if (answer.equalsIgnoreCase(rightAnswer)) {
            points += 5;
            return "✅ That's correct!";
        } else {
            return "❌ That's incorrect. The answer is <b>" + rightAnswer + "</b>.";
        }
    }

    public String playerPoints(String playerName) {
        String message = "🎮 Player: " + playerName + "\n";
        message += "🌟 Points: " + points;
        return message;
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
