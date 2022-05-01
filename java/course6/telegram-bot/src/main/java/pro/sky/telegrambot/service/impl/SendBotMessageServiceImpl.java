package pro.sky.telegrambot.service.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Notification;
import pro.sky.telegrambot.service.NotificationService;
import pro.sky.telegrambot.service.SendBotMessageService;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@RequiredArgsConstructor
public class SendBotMessageServiceImpl implements SendBotMessageService {
    private final TelegramBot telegramBot;
    private final NotificationService notificationService;

    @Override
    public void sendMessage(Long chatId, String message) {
        if (isBlank(message)) return;
        SendMessage sendMessage = new SendMessage(chatId, message).parseMode(ParseMode.HTML);
        telegramBot.execute(sendMessage);
    }

    @Override
    public void sendMessages(List<Notification> notifications) {
        notifications.forEach(notification -> {
            Long chatId = notification.getUser().getChatId();
            String message = notification.getText();
            SendMessage sendMessage = new SendMessage(chatId, message).parseMode(ParseMode.HTML);
            telegramBot.execute(sendMessage);
        });
    }

    @Scheduled(cron = "0 0/1 * * * *")
    @Override
    public void scheduledSendMessages() {
        sendMessages(notificationService.getCurrentNotifications());
    }

}
