package pro.sky.telegrambot.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.service.NotificationService;
import pro.sky.telegrambot.service.SendBotMessageService;

import javax.annotation.PostConstruct;
import java.util.EnumMap;

import static pro.sky.telegrambot.command.CommandConst.*;
import static pro.sky.telegrambot.util.NotificationUtils.getChatId;
import static pro.sky.telegrambot.util.NotificationUtils.getUserName;

@Component
@RequiredArgsConstructor
public class CommandsBox {
    private final EnumMap<CommandNames, Command> commands = new EnumMap<>(CommandNames.class);
    private final NotificationService notificationService;
    private final SendBotMessageService sendBotMessageService;

    @PostConstruct
    private void initCommands() {
        commands.put(CommandNames.START, update -> {
            String greetings = String.format("%s %s !", GREETINGS_MESSAGE, getUserName(update));
            sendBotMessageService.sendMessage(getChatId(update), greetings);
        });

        commands.put(CommandNames.STOP, update -> sendBotMessageService.sendMessage(getChatId(update), UNKNOWN_MESSAGE));

        commands.put(CommandNames.HELP, update -> sendBotMessageService.sendMessage(getChatId(update), HELP_MESSAGE));

        commands.put(CommandNames.UNKNOWN, update -> sendBotMessageService.sendMessage(getChatId(update), UNKNOWN_MESSAGE));

        commands.put(CommandNames.CREATE, update -> sendBotMessageService.sendMessage(getChatId(update),
                notificationService.createNotification(update)));

        commands.put(CommandNames.EDIT, update -> sendBotMessageService.sendMessage(getChatId(update),
                notificationService.editNotification(update)));

        commands.put(CommandNames.FIND, update -> sendBotMessageService.sendMessage(getChatId(update),
                notificationService.findNotificationsByPart(update)));

        commands.put(CommandNames.DELETE, update -> sendBotMessageService.sendMessage(getChatId(update),
                notificationService.deleteNotification(update)));

        commands.put(CommandNames.ALL, update -> sendBotMessageService.sendMessage(getChatId(update),
                notificationService.findAllNotifications(update)));
    }

    public Command findCommand(String commandName) {
        return commands.getOrDefault(CommandNames.valueOfCommandName(commandName),
                commands.get(CommandNames.UNKNOWN));
    }

}
