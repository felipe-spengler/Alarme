package example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AlarmApp {
    private JFrame frame;
    private AlarmManager alarmManager;
    private JList<Alarm> alarmList;
    private AlarmListModel alarmListModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AlarmApp::new);
    }

    public AlarmApp() {
        alarmManager = new AlarmManager();
        frame = new JFrame("Alarm App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        alarmListModel = new AlarmListModel(alarmManager);
        alarmList = new JList<>(alarmListModel);

        JPanel panel = new JPanel();
        JButton addButton = new JButton("Add Alarm");
        JButton deleteButton = new JButton("Delete Alarm");

        addButton.addActionListener(this::addAlarm);
        deleteButton.addActionListener(this::deleteAlarm);

        panel.add(addButton);
        panel.add(deleteButton);

        frame.add(new JScrollPane(alarmList), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addAlarm(ActionEvent e) {
        AddAlarmDialog dialog = new AddAlarmDialog(frame, alarmManager, alarmListModel);
        dialog.setVisible(true);
    }

    private void deleteAlarm(ActionEvent e) {
        int selectedIndex = alarmList.getSelectedIndex();
        if (selectedIndex != -1) {
            alarmManager.removeAlarm(selectedIndex);
            alarmListModel.update();
        }
    }
}
