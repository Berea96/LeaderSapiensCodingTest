package com.leaderSapiens;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TestTelegram extends TelegramLongPollingBot {
    @Override
    public String getBotToken() {
        return "716083493:AAF9chXmDAo_x_RL6k_9EOZHwi1v2HVDyZs";
    }

    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        System.out.println(text);

        if(text.equals("hi")) {
            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("hello");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "kimkj96Bot";
    }
}
