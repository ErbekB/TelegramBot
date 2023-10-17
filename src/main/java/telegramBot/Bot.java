package telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.quiz.MainMenu;

public class Bot extends TelegramLongPollingBot {
    private boolean firstTime = true;
    MainMenu mainMenu = new MainMenu();
    private long chatId;
    @Override
    public void onUpdateReceived(Update update) {
        chatId = update.getMessage().getChatId();
        String messageReceived = update.getMessage().getText();
        System.out.println(messageReceived);

        if (firstTime){                             //Shows the welcome-text
            sendResponse(chatId, mainMenu.welcome());
            firstTime = false;
        }
        else if (messageReceived.toLowerCase().startsWith("startquiz") || messageReceived.toLowerCase().startsWith("/startquiz")) {
            chatId = update.getMessage().getChatId();
            String messageReceived2 = update.getMessage().getText();
            sendResponse(chatId, "wie lange");
            System.out.println(messageReceived2);
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
