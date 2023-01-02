package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.command.CommandNames;
import pro.sky.telegrambot.command.CommandsBox;
import pro.sky.telegrambot.util.NotificationUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final TelegramBot telegramBot;
    private final CommandsBox commandsBox;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            log.info("Processing update: {}", update);
            String message = NotificationUtils.getMessage(update);
            if (message == null) {
                continue;
            }

            String commandName;
            if (message.startsWith("/")) {
                commandName = message.split(" ")[0].toLowerCase();
            } else {
                commandName = CommandNames.UNKNOWN.getCommandName();
            }

            commandsBox.findCommand(commandName).execute(update);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
