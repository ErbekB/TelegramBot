package telegramBot.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private final List<Question> questionsList = this.initQuestions();
    private List<String> rightAnswers;
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

        setRightAnswer(questionsList.get(index).getSolution(), questionsList.get(index).getSolutionAlphabetical());                 // Sets the "rightAnswer" attribute
        return this.questionsList.get(index).toString();
    }

    public void setRightAnswer(String rightAnswer, String alphabetical) {
        this.rightAnswers = List.of(rightAnswer, alphabetical);
    }

    public String checkAnswer(String answer) {
        if (answer.equalsIgnoreCase(rightAnswers.get(0))) {
            points += 5;
            return "✅ That's correct!";
        } else if (answer.equalsIgnoreCase(rightAnswers.get(1))) {
            points += 5;
            return "✅ That's correct!";
        } else {
            return "❌ That's incorrect. The answer is <b>" + rightAnswers.get(0) + "</b>.";
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
                        List.of("Sushi", "Ramen", "Miso", "Tempura"), "Miso", "b"),
                new Question("Shakespeare", "In which famous Shakespearean play does the character Othello appear?",
                        List.of("Macbeth", "Romeo and Juliet", "Othello", "Hamlet"), "Othello", "c"),
                new Question("Human Body", "What is the largest organ in the human body?",
                        List.of("Brain", "Liver", "Heart", "Skin"), "Skin", "d"),
                new Question("Paintings", "Who painted the famous artwork \"Mona Lisa\"?",
                        List.of("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"), "Leonardo da Vinci", "c"),
                new Question("Japan", "What is the name of the popular Japanese dish made from fermented soybeans?",
                        List.of("Sushi", "Ramen", "Miso", "Tempura"), "Miso", "c"),
                new Question("Shakespeare", "In which famous Shakespearean play does the character Othello appear?",
                        List.of("Macbeth", "Romeo and Juliet", "Othello", "Hamlet"), "Othello", "c"),
                new Question("Geography", "What is the largest continent in the world?",
                        List.of("Asia", "Africa", "North America", "Australia"), "Asia", "a"),
                new Question("Science", "What is the chemical symbol for gold?",
                        List.of("Go", "Gl", "Au", "Ag"), "Au", "c"),
                new Question("Movies", "Who directed the 1994 film 'Pulp Fiction'?",
                        List.of("Quentin Tarantino", "Martin Scorsese", "Steven Spielberg", "Spike Lee"), "Quentin Tarantino", "a"),
                new Question("History", "Who was the first President of the United States?",
                        List.of("Thomas Jefferson", "Benjamin Franklin", "George Washington", "John Adams"), "George Washington", "c"),
                new Question("Art", "Who painted the 'Mona Lisa'?",
                        List.of("Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"), "Leonardo da Vinci", "b"),
                new Question("Music", "Which Beatles album is also known as 'The White Album'?",
                        List.of("Abbey Road", "Revolver", "Sgt. Pepper's Lonely Hearts Club Band", "The Beatles"), "The Beatles", "d"),
                new Question("Sports", "In which sport would you perform a slam dunk?",
                        List.of("Soccer", "Tennis", "Basketball", "Golf"), "Basketball", "c"),
                new Question("Mathematics", "What is the value of pi (π) to two decimal places?",
                        List.of("3.14", "3.16", "3.18", "3.20"), "3.14", "a"),
                new Question("Literature", "Who wrote the novel 'To Kill a Mockingbird'?",
                        List.of("George Orwell", "Harper Lee", "J.K. Rowling", "Jane Austen"), "Harper Lee", "b"),
                new Question("Technology", "What company is known for its iPhone and Mac products?",
                        List.of("Microsoft", "Google", "Apple", "Samsung"), "Apple", "c"),
                new Question("Animals", "Which mammal can fly?",
                        List.of("Kangaroo", "Elephant", "Dolphin", "Bat"), "Bat", "d"),
                new Question("Space", "What is the largest planet in our solar system?",
                        List.of("Venus", "Mars", "Jupiter", "Saturn"), "Jupiter", "c"),
                new Question("Food", "Which fruit is known as the 'king of fruits'?",
                        List.of("Banana", "Pineapple", "Durian", "Apple"), "Durian", "c"),
                new Question("Politics", "Who was the first woman to become the Prime Minister of the United Kingdom?",
                        List.of("Margaret Thatcher", "Angela Merkel", "Hillary Clinton", "Theresa May"), "Margaret Thatcher", "a"),
                new Question("Sports", "Which country hosted the 2016 Summer Olympics?",
                        List.of("United States", "Brazil", "China", "Russia"), "Brazil", "b"),
                new Question("Geography", "What is the capital of France?",
                        List.of("Madrid", "Berlin", "Rome", "Paris"), "Paris", "d"),
                new Question("History", "In which year did Christopher Columbus first reach the Americas?",
                        List.of("1492", "1607", "1776", "1849"), "1492", "a"),
                new Question("Music", "Who is known as the 'King of Pop'?",
                        List.of("Elvis Presley", "Michael Jackson", "Prince", "David Bowie"), "Michael Jackson", "b"),
                new Question("Science", "What is the chemical symbol for oxygen?",
                        List.of("Ox", "O2", "Oi", "O"), "O", "d"),
                new Question("Geography", "Which country is known as the Land of the Rising Sun?",
                        List.of("South Korea", "China", "Vietnam", "Japan"), "Japan", "d"),
                new Question("Movies", "Who played the character of Tony Stark in the Marvel Cinematic Universe?",
                        List.of("Chris Hemsworth", "Chris Evans", "Robert Downey Jr.", "Mark Ruffalo"), "Robert Downey Jr.", "c"),
                new Question("History", "What year did the Titanic sink?",
                        List.of("1912", "1907", "1921", "1915"), "1912", "a"),
                new Question("Art", "Which artist is famous for his 'Starry Night' painting?",
                        List.of("Pablo Picasso", "Vincent van Gogh", "Leonardo da Vinci", "Claude Monet"), "Vincent van Gogh", "b"),
                new Question("Literature", "Who wrote 'War and Peace'?",
                        List.of("Charles Dickens", "Fyodor Dostoevsky", "Leo Tolstoy", "Jane Austen"), "Leo Tolstoy", "c"),
                new Question("Space", "What is the name of the brightest star in the night sky?",
                        List.of("Mars", "Venus", "Sirius", "Betelgeuse"), "Sirius", "c"),
                new Question("Sports", "Which country has won the most FIFA World Cup championships?",
                        List.of("Germany", "Brazil", "Argentina", "Italy"), "Brazil", "b"),
                new Question("Food", "What is the main ingredient in traditional Japanese miso soup?",
                        List.of("Seaweed", "Tofu", "Rice", "Noodles"), "Tofu", "b"),
                new Question("Music", "Who released the album 'Thriller,' which is the best-selling album of all time?",
                        List.of("Elton John", "Madonna", "The Rolling Stones", "Michael Jackson"), "Michael Jackson", "d")
        );
    }
}
