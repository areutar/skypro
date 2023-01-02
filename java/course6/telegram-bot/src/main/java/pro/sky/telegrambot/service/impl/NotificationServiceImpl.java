package pro.sky.telegrambot.service.impl;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.telegrambot.entity.Notification;
import pro.sky.telegrambot.entity.TelegramUser;
import pro.sky.telegrambot.repository.NotificationRepository;
import pro.sky.telegrambot.service.NotificationService;
import pro.sky.telegrambot.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import static pro.sky.telegrambot.command.CommandNames.*;
import static pro.sky.telegrambot.util.NotificationConst.*;
import static pro.sky.telegrambot.util.NotificationUtils.*;
import static pro.sky.telegrambot.util.ParsingUtil.*;


@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository repository;
    private final UserService userService;

    @Override
    @Transactional
    public String createNotification(Update update) {
        String input = inputCleardOfCommand(CREATE, update);

        String[] strings = input.split(" ");
        if (strings.length < 3) {
            return BAD_REQUST;
        }

        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.of(parseDate(strings[0]), parseTime(strings[1]));
        } catch (DateTimeParseException e) {
            return BAD_REQUST;
        }
        String textNotification = input.substring(patternDateTime.length());


        TelegramUser user = userService.findUserByChatId(getChatId(update));
        if (user == null) {
            user = userService.create(getChatId(update), getUserName(update));
        }
        Long notificationsNumber = user.getNotificationsNumber();

        Notification notification =
                new Notification(++notificationsNumber, localDateTime, textNotification, user);

        try {
            repository.save(notification);
            user.setNotificationsNumber(notificationsNumber);
        } catch (Exception e) {
            return SAVED_FAIL;
        }

        userService.updateUserName(update);
        return SAVED_OK;
    }

    @Override
    public String editNotification(Update update) {
        String input = inputCleardOfCommand(EDIT, update);
        if (input.isEmpty()) {
            return NO_ID;
        }

        String[] strings = input.split(" ");
        if (strings.length < 4) {
            return BAD_REQUST;
        }

        if (!strings[0].matches("[1-9][\\d]*")) {
            return String.format("%s - %s", input, WRONG_NUMBER);
        }

        Long number = Long.parseLong(strings[0]);
        Optional<Notification> notificationOptional = repository.findNotificationByNumber(number);
        if (notificationOptional.isEmpty()) {
            return String.format("%s - %s", input, WRONG_NUMBER);
        }

        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.of(parseDate(strings[1]), parseTime(strings[2]));
        } catch (DateTimeParseException e) {
            return BAD_REQUST;
        }

        String textNotification = input.substring(strings[0].length()).trim()
                .substring(patternDateTime.length());

        Notification notification = notificationOptional.get();
        notification.setTime(localDateTime);
        notification.setText(textNotification);

        try {
            repository.save(notification);
        } catch (Exception e) {
            return EDIT_FAIL;
        }

        return EDIT_OK;
    }

    @Override
    public String deleteNotification(Update update) {
        String input = inputCleardOfCommand(DELETE, update);
        if (input.isEmpty()) {
            return NO_ID;
        }

        if (!input.matches("[1-9][\\d]*")) {
            return String.format("%s - %s", input, WRONG_NUMBER);
        }

        Long number = Long.parseLong(input);
        Optional<Notification> notificationOptional = repository.findNotificationByNumber(number);

        if (notificationOptional.isEmpty()) {
            return String.format("%s - %s", input, WRONG_NUMBER);
        }

        try {
            repository.deleteById(notificationOptional.get().getId());
        } catch (Exception e) {
            return DELETE_FAIL;
        }
        return DELETE_OK;
    }

    @Override
    public String findNotificationsByPart(Update update) {
        String input = inputCleardOfCommand(FIND, update);
        List<Notification> list = repository
                .findNotificationsByPart(
                        getChatId(update), input.toLowerCase());
        return listToString(list);
    }

    @Override
    public String findAllNotifications(Update update) {
        return listToString(repository.findNotificationByUser_ChatId(getChatId(update),
                Sort.by(Sort.Direction.ASC, "number")));
    }

    @Override
    public List<Notification> getCurrentNotifications() {
        return repository.getCurrentNotifications();
    }
}
