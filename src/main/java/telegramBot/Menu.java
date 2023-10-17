package telegramBot;

public class Menu {

    public String menu(String message){
        // start to evaluate the messages you received
        // 1. Main menu
        if (message.toLowerCase().startsWith("hello") ||message.toLowerCase().startsWith("/hello")) {
            String hello = """
                    Hello there! 👋 Welcome to the Quiz Bot. How can I assist you today? 
                    If you'd like to start a quiz, simply type /start or send 'Start.' 
                    If you have any questions or need help, feel free to ask. Have a great time quizzing!
                    """;
            return hello;
        }

        // 2. quiz mode
        if (message.toLowerCase().startsWith("quiz")||message.toLowerCase().startsWith("/quiz")
                || message.toLowerCase().startsWith("start") || message.toLowerCase().startsWith("/start")) {
            String quiz = """
                    Here are some options to get started:
                    
                    🚀 /startquiz: If you're ready to jump into a quiz.
                    🏆 /startpvp: Challenge yourself against other players and see how you stack up.
                    👫 /startfriend: Invite a friend to a friendly competition.
                    📊 /bestlist: Check out the leaderboard to see where you stand among the top quizzers.
                    
                    Great time quizzing! 😄💡
                    """;
            return quiz;
        }

        // 3. help menu
        if (message.toLowerCase().startsWith("help") || message.toLowerCase().startsWith("/help")) {
            String help = """
                    Here are some options to get started:
                    
                    🎮 /start: For the Game options.
                    👋 /hello: To welcome you again.
                    📊 /bestlist: Check out the leaderboard to see where you stand among the top quizzers.
                    """;
            return help;
        }
        // 4. Leaderboard
        if (message.toLowerCase().startsWith("bestlist") || message.toLowerCase().startsWith("/bestlist")) {
            String help = """
                    Not rdy yet :(
                    """;
            return help;
        }
        return null;
    }
    public String welcome() {
        String welcome = "Welcome to the Quiz Bot! \uD83E\uDD16\uD83E\uDDE0\n" +
                "I'll ask you questions on a variety of topics, and you can earn" +
                " points for each correct answer. If you're not sure about something," +
                " don't worry, it's all about learning!" +
                " To start, simply type /start or just send me 'Start' to begin a quiz." +
                " If you need any assistance or have questions, feel free to type /help." +
                " Good luck, and may the best quizzers win! \uD83C\uDFC6\uD83C\uDF1F";

        return welcome;
    }
}
