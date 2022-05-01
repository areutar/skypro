package pro.sky.telegrambot.util;

import com.pengrad.telegrambot.model.Update;
import lombok.experimental.UtilityClass;
import pro.sky.telegrambot.command.CommandConst;
import pro.sky.telegrambot.command.CommandNames;
import pro.sky.telegrambot.entity.Notification;

import java.util.List;
import java.util.stream.Collectors;

import static pro.sky.telegrambot.util.NotificationConst.NOT_FOUND;

@UtilityClass
public class NotificationUtils {
    public static Long getChatId(Update update) {
        return update.message().chat().id();
    }

    public static String getMessage(Update update) {
        return update.message().text();
    }

    public static String getUserName(Update update) {
        return update.message().from().firstName();
    }

    public static String listToString(List<Notification> list) {
        if (list.isEmpty()) {
            return NOT_FOUND;
        }
        return list.stream()
                .map(Notification::toString)
                .collect(Collectors.joining(CommandConst.NEW_LINE));
    }

    public static String inputCleardOfCommand(CommandNames command, Update update) {
        return getMessage(update).substring(command.getLength()).trim();
    }


}
