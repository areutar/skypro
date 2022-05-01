package pro.sky.telegrambot.service;

import pro.sky.telegrambot.entity.Notification;

import java.util.List;

public interface SendBotMessageService {
    void sendMessage(Long chatId, String message);

    void sendMessages(List<Notification> notifications);

    void scheduledSendMessages();
}
