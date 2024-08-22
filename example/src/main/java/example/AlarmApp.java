import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AlarmApp {
    private JFrame frame;
    private AlarmManager alarmManager;
    private JList<String> alarmList;  // Alterado para JList<String>
    private AlarmListModel alarmListModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AlarmApp::new);
    }

    public AlarmApp() {
        alarmManager = new AlarmManager();
        frame = new JFrame("Alarm App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Usando AlarmListModel para formatar strings
        alarmListModel = new AlarmListModel(alarmManager);
        alarmList = new JList<>(alarmListModel);

        JPanel panel = new JPanel();
        JButton addButton = new JButton("Novo Alarme");
        JButton deleteButton = new JButton("Excluir Alarme");

        addButton.addActionListener(this::addAlarm);
        deleteButton.addActionListener(this::deleteAlarm);

        panel.add(addButton);
        panel.add(deleteButton);

        frame.add(new JScrollPane(alarmList), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addAlarm(ActionEvent e) {
        // Criar e mostrar o diálogo para adicionar um novo alarme
        AddAlarmDialog dialog = new AddAlarmDialog(frame, alarmManager, alarmListModel);
        dialog.setVisible(true);
    }

    private void deleteAlarm(ActionEvent e) {
        // Excluir o alarme selecionado
        int selectedIndex = alarmList.getSelectedIndex();
        if (selectedIndex != -1) {
            alarmManager.removeAlarm(selectedIndex);
            alarmListModel.update();  // Atualizar o modelo da lista
        }
    }
}
