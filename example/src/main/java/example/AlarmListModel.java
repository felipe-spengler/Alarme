import javax.swing.*;

public class AlarmListModel extends AbstractListModel<String> {
    private final AlarmManager alarmManager;

    public AlarmListModel(AlarmManager alarmManager) {
        this.alarmManager = alarmManager;
    }

    @Override
    public int getSize() {
        return alarmManager.getAlarms().size();
    }

    @Override
    public String getElementAt(int index) {
        Alarm alarm = alarmManager.getAlarms().get(index);
        return alarm.formatAlarm();  // Usa o método formatAlarm para exibir a string formatada
    }

    public void update() {
        fireContentsChanged(this, 0, getSize() - 1);
    }
}
