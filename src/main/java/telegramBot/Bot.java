package telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.quiz.Bestlist;
import telegramBot.quiz.Game;
import telegramBot.quiz.MainMenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    private boolean firstTime = true;
    private boolean inGame = false;
    private Map<Long, Long> userChatIds = new HashMap<>();
    MainMenu mainMenu = new MainMenu();
    Game game = new Game();
    Bestlist list = new Bestlist();

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        String messageReceived = update.getMessage().getText();
        String playerName = update.getMessage().getFrom().getFirstName();
        System.out.println(messageReceived);

//        if (!userChatIds.containsKey(chatId)) {
//            userChatIds.put(chatId, chatId); // Jeder Benutzer erhält seine eigene chatId
//        }

        if (firstTime){                             // Shows the welcome-text
            sendResponse(chatId, mainMenu.welcome());
            firstTime = false;
        }
        else if(inGame){                            // Checks if the User is in a Game
            sendResponse(chatId, game.checkAnswer(messageReceived));
            inGame = game.state();
            if (inGame){                            // Checks if we the last Question is asked
                sendResponse(chatId, game.getQuestion());
            }
            else{                                   // After the last Question we send the Score
                sendResponse(chatId, game.playerPoints(playerName, list));
            }
        }
        else if(messageReceived.equalsIgnoreCase("startquiz") || messageReceived.equalsIgnoreCase("/startquiz") ||
                messageReceived.equalsIgnoreCase("startgame") || messageReceived.equalsIgnoreCase("/startgame")){ //Beginns a Game
            sendResponse(chatId, game.start());
            sendResponse(chatId, game.getQuestion());
            inGame = true;
        }
        else {                                       // Displays the menu option if the User asked for it
            sendResponse(chatId, mainMenu.menu(messageReceived, list));
        }
    }


    private void sendResponse(long chatId, String s) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(s);
        msg.setParseMode("HTML");

        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return "6098688794:AAF85okcNjTqj2quUWsaYXNaja73P3Tb0R0";  // TODO: insert your bot token here!
    }

    @Override
    public String getBotUsername() {
        return "Airbekbot";  // TODO: insert your bots username here
    }
}
