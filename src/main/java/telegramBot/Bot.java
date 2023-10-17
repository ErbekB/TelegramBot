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
    private long chatId;
    MainMenu mainMenu;
    Game game;

    @Override
    public void onUpdateReceived(Update update) {
        chatId = update.getMessage().getChatId();
        String messageReceived = update.getMessage().getText();
        System.out.println(messageReceived);

        
        if (firstTime){                             //Shows the welcome-text
            mainMenu = new MainMenu();
            sendResponse(chatId, mainMenu.welcome());
            firstTime = false;
        }
        else if(messageReceived.equalsIgnoreCase("startquiz") || messageReceived.equalsIgnoreCase("/startquiz")){
            game = new Game();
            game.start();
        }
        else {
            sendResponse(chatId, mainMenu.menu(messageReceived));
        }
    }


    private void sendResponse(long chatId, String s) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(s);

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
