package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import pro.sky.telegrambot.entity.Notification;

import java.util.List;

public interface NotificationService {
    String createNotification(Update update);

    String editNotification(Update update);

    String deleteNotification(Update update);

    String findNotificationsByPart(Update update);

    String findAllNotifications(Update update);

    List<Notification> getCurrentNotifications();
}
