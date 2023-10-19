package telegramBot.quiz;

public class MainMenu {
    public String menu(String message, Bestlist list){
        // start to evaluate the messages you received
        // 1. Main menu
        if (message.toLowerCase().startsWith("hello") ||message.toLowerCase().startsWith("/hello")) {
            String hello = """
                    Hello there! 👋 Welcome to the Quiz Bot. How can I assist you today? 
                    If you'd like to start a quiz, simply type /start or send 'Start.' 
                    If you have any questions or need /help, feel free to ask. Have a great time quizzing!
                    """;
            return hello;
        }

        // 2. quiz mode
        if (message.equalsIgnoreCase("quiz")|| message.equalsIgnoreCase("/quiz")
                || message.equalsIgnoreCase("start") || message.equalsIgnoreCase("/start")) {
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
            return list.toString();
        }

        // 5. PVP
        if (message.toLowerCase().startsWith("startpvp") || message.toLowerCase().startsWith("/startpvp")) {
            return "still in process  \uD83D\uDD04";
        }

        // 6. Play vs friend
        if (message.toLowerCase().startsWith("startfriend") || message.toLowerCase().startsWith("/startfriend")) {
            return "still in process  \uD83D\uDD04";
        }

        return null;
    }
    //Welcome text for every new chat
    public String welcome() {
        String welcome = """
                    Hello there! 👋 Welcome to the Quiz Bot. How can I assist you today? 
                    If you'd like to start a quiz, simply type /start or send 'Start.' 
                    If you have any questions or need /help, feel free to ask. Have a great time quizzing!
                    """;
        return welcome;
    }

}
