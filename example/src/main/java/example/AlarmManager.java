package example;

import java.util.ArrayList;
import java.util.List;

public class AlarmManager {
    private List<Alarm> alarms;

    public AlarmManager() {
        alarms = new ArrayList<>();
    }

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }

    public void removeAlarm(int index) {
        if (index >= 0 && index < alarms.size()) {
            alarms.remove(index);
        }
    }

    public List<Alarm> getAlarms() {
        return alarms;
    }
}