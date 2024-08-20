package example;


import java.time.LocalDateTime;

public class Alarm {
    private LocalDateTime dateTime;
    private String note;

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

    @Override
    public String toString() {
        return dateTime.toString() + " - " + note;
    }
}