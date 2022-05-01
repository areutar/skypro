package pro.sky.telegrambot.command;

import lombok.experimental.UtilityClass;

import static pro.sky.telegrambot.command.CommandNames.*;

@UtilityClass
public class CommandConst {
    public static final String GREETINGS_MESSAGE = "Здравствуйте,";
    public static final String HELP_MESSAGE = String.format("<b>❇ Доступные команды ❇</b>\n\n"
                    + "<i><u>Основные команды бота:</u></i>\n"
                    + "%s - начать работу\n"
                    + "%s - приостановить\n"
                    + "%s - помощь\n\n"

                    + "<i><u>Создание напоминаний:</u></i>\n"
                    + "%s (дата время) (текст напоминания) - создать напоминание.\n"
                    + "Дата и время вводятся в формате dd.mm.yyyy hh:mm.\n"
                    + "Пример: %s 01.01.2022 10:30 полить цветы.\n\n"

                    + "<i><u>Редактирование напоминаний:</u></i>\n"
                    + "%s (номер напоминания) (дата время) текст напоминания"
                    + " - изменить напоминание, зная его номер.\n"
                    + "Пример: %s 3 01.01.2022 13:30 Полить кактусы.\n"
                    + "(Номер напоминания можно узнать с помощью поиска напоминаний.)\n\n"

                    + "<i><u>Поиск напоминаний:</u></i>\n"
                    + "%s (отрывок) - найти напоминание по содержащемуся в его тексте отрывку.\n"
                    + "%s - найти все напоминания.\n\n"

                    + "<i><u>Удаление напоминаний:</u></i>\n"
                    + "%s (номер напоминания) - удалить напоминание по его номеру. Пример: %s 5\n",

            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName(),
            CREATE.getCommandName(), CREATE.getCommandName(),
            EDIT.getCommandName(), EDIT.getCommandName(),
            FIND.getCommandName(), ALL.getCommandName(),
            DELETE.getCommandName(), DELETE.getCommandName());

    public static final String UNKNOWN_MESSAGE =
            "<b>Нет такой команды!</b> Используйте /help, чтобы получить список доступных команд.";
    public static final String NEW_LINE = System.lineSeparator();
}
