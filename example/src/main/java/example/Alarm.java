import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alarm {
    private final LocalDateTime dateTime;
    private final String note;

    public Alarm(LocalDateTime dateTime, String note) {
        this.dateTime = dateTime;
        this.note = note;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getNote() {
        return note;
    }

    public String formatAlarm() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedTime = dateTime.format(timeFormatter);
        String formattedDate = dateTime.format(dateFormatter);
        return formattedTime + "   " + formattedDate + "   - " + note;
    }
}
