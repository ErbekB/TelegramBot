package telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.quiz.Game;
import telegramBot.quiz.MainMenu;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private boolean firstTime = true;
    private boolean inGame = false;
    private long chatId;
    MainMenu mainMenu = new MainMenu();
    Game game = new Game();

    @Override
    public void onUpdateReceived(Update update) {
        chatId = update.getMessage().getChatId();
        String messageReceived = update.getMessage().getText();
        String playerName = update.getMessage().getFrom().getFirstName();
        System.out.println(messageReceived);

        if (firstTime){                             // Shows the welcome-text
            mainMenu = new MainMenu();
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
                sendResponse(chatId, game.playerPoints(playerName));
            }
        }
        else if(messageReceived.equalsIgnoreCase("startquiz") || messageReceived.equalsIgnoreCase("/startquiz")){ //Beginns a Game
            sendResponse(chatId, game.start());
            sendResponse(chatId, game.getQuestion());
            inGame = true;
        }
        else {                                       // Displays the menu option if the User asked for it
            sendResponse(chatId, mainMenu.menu(messageReceived));
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
