package pro.sky.telegrambot.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static pro.sky.telegrambot.util.ParsingUtil.formatTime;

@Entity
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "notification_task")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private LocalDateTime time;
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TelegramUser user;

    public Notification(Long number, LocalDateTime time, String text, TelegramUser user) {
        this.number = number;
        this.time = time;
        this.text = text;
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("<b>Напоминание №%d</b> %s %s", number, formatTime(time), text);
    }
}
