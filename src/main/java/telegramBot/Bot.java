package telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.quiz.Bestlist;
import telegramBot.quiz.Game;
import telegramBot.quiz.MainMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    private Map<Long, Game> userGames = new HashMap<>();
    private Map<Long, MainMenu> userMenu = new HashMap<>();
    private boolean firstEntry = true;
    Bestlist list = new Bestlist();

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        String messageReceived = update.getMessage().getText();
        String playerName = update.getMessage().getFrom().getFirstName();
        System.out.println(messageReceived);

        Game game = getGame(chatId);                              // New Game for new User
        MainMenu mainMenu = getMainMenu(chatId);                  // New Menu for new User

        if (firstEntry){
            sendResponse(chatId, mainMenu.welcome());
            firstEntry = false;
            try {
                loadFiles();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if(game.isOn()){                                          // Checks if the User is in a Game
            sendResponse(chatId, game.checkAnswer(messageReceived));
            game.setInGame(game.state());
            if (game.isOn()){                                     // Checks if the last Question is asked
                sendResponse(chatId, game.getQuestion());
            }
            else {                                                 // After the last Question we send the Score
                sendResponse(chatId, game.playerPoints(playerName, list));
                try {
                    saveFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if(messageReceived.equalsIgnoreCase("startquiz") || messageReceived.equalsIgnoreCase("/startquiz") ||
                messageReceived.equalsIgnoreCase("startgame") || messageReceived.equalsIgnoreCase("/startgame")){           // Beginns a Game
            sendResponse(chatId, game.start());
            sendResponse(chatId, game.getQuestion());
            game.setInGame(true);
        }
        else {                                                    // Displays the menu option if the User asked for it
            sendResponse(chatId, mainMenu.menu(messageReceived, list));
        }
    }

    private Game getGame(long chatId) {
        Game game = userGames.get(chatId);
        if (game == null) {
            game = new Game();
            userGames.put(chatId, game);
        }
        return game;
    }

    private MainMenu getMainMenu(long chatId) {
        MainMenu mainMenu = userMenu.get(chatId);
        if (mainMenu == null) {
            mainMenu = new MainMenu();
            userMenu.put(chatId, mainMenu);
        }
        return mainMenu;
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

    private void saveFile() throws IOException {
        File file = new File("Bestlist.ser");

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

        outputStream.writeObject(list.getList());
        outputStream.close();
    }

    private void loadFiles() throws IOException, ClassNotFoundException {
        File file = new File("Bestlist.ser");

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

        list.setList((Map<String, Integer>) inputStream.readObject());
        inputStream.close();
    }

}
