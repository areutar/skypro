package pro.sky.telegrambot.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public final class ParsingUtil {
    private static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
    public static final String patternDateTime = "dd.MM.yyyy HH:mm";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternDateTime);

    // 01.01.2022 20:00
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, formatterDate);
    }

    public static LocalTime parseTime(String time) {
        return LocalTime.parse(time, formatterTime);
    }

    public static String formatTime(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }

}
