package telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private boolean firstTime = true;
    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        String messageReceived = update.getMessage().getText();
        System.out.println(messageReceived);

        Menu menu = new Menu();
        if (firstTime){                             //Shows the welcome-text
            sendResponse(chatId, menu.welcome());
            firstTime = false;
        }
        else{
            sendResponse(chatId,menu.menu(messageReceived));
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
