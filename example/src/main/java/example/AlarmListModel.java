package example;

import javax.swing.*;

public class AlarmListModel extends AbstractListModel<Alarm> {
    private final AlarmManager alarmManager;

    public AlarmListModel(AlarmManager alarmManager) {
        this.alarmManager = alarmManager;
    }

    @Override
    public int getSize() {
        return alarmManager.getAlarms().size();
    }

    @Override
    public Alarm getElementAt(int index) {
        return alarmManager.getAlarms().get(index);
    }

    public void update() {
        fireContentsChanged(this, 0, getSize());
    }
}