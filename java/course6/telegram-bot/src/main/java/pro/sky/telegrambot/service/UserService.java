package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import pro.sky.telegrambot.entity.TelegramUser;

public interface UserService {
    TelegramUser create(Long chatId, String name);

    TelegramUser findUserByChatId(Long chatId);

    void updateUserName(Update update);

}
