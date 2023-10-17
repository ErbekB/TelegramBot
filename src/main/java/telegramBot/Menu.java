package telegramBot;

public class Menu {

    public String menu(String message){
        // start to evaluate the messages you received
        // 1. Main menu
        if (message.toLowerCase().startsWith("hello")) {
            String hello = """
                    Hello there! 👋 Welcome to the Quiz Bot. How can I assist you today? 
                    If you'd like to start a quiz, simply type /startquiz or send 'Start.' 
                    If you have any questions or need help, feel free to ask. Have a great time quizzing!
                    """;
            return hello;
        }

        // 2. quiz mode
        if (message.toLowerCase().startsWith("quiz")) {
            String menu = "\n1. Start Game \n2. Play vs Player\n3. Play vs Friend\n4. Show Best-list";
            return menu;
        }

        // 3. create a poll
        if (message.toLowerCase().startsWith("poll")) {

        } else{

        }
        return null;
    }
    public String welcome() {
        String welcome = "Welcome to the Quiz Bot! \uD83E\uDD16\uD83E\uDDE0\n" +
                "I'll ask you questions on a variety of topics, and you can earn" +
                " points for each correct answer. If you're not sure about something," +
                " don't worry, it's all about learning!" +
                " To start, simply type /startquiz or just send me 'Start' to begin a quiz." +
                " If you need any assistance or have questions, feel free to type /help." +
                " Good luck, and may the best quizzers win! \uD83C\uDFC6\uD83C\uDF1F";

        return welcome;
    }
}
