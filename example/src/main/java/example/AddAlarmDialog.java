package example;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddAlarmDialog extends JDialog {
    private final AlarmManager alarmManager;
    private final AlarmListModel alarmListModel;
    private JSpinner dateSpinner;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JTextField noteField;

    public AddAlarmDialog(Frame owner, AlarmManager alarmManager, AlarmListModel alarmListModel) {
        super(owner, "Add Alarm", true);
        this.alarmManager = alarmManager;
        this.alarmListModel = alarmListModel;
        setLayout(new GridLayout(5, 2));
        setSize(300, 200);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        noteField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addAlarm());

        add(new JLabel("Date:"));
        add(dateSpinner);
        add(new JLabel("Hour:"));
        add(hourSpinner);
        add(new JLabel("Minute:"));
        add(minuteSpinner);
        add(new JLabel("Note:"));
        add(noteField);
        add(addButton);
    }

    private void addAlarm() {
        LocalDateTime dateTime = ((SpinnerDateModel) dateSpinner.getModel()).getDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
        int hour = (int) hourSpinner.getValue();
        int minute = (int) minuteSpinner.getValue();
        LocalTime time = LocalTime.of(hour, minute);
        dateTime = dateTime.withHour(time.getHour()).withMinute(time.getMinute());

        String note = noteField.getText();
        alarmManager.addAlarm(new Alarm(dateTime, note));
        alarmListModel.update();
        setVisible(false);
    }
}