
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

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
        setLocationRelativeTo(owner); // Centraliza o diálogo em relação ao frame pai

        // Criar e configurar o SpinnerDateModel para data
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        // Configurar centralização para o editor de data
        JTextField dateField = ((JSpinner.DateEditor) dateSpinner.getEditor()).getTextField();
        dateField.setHorizontalAlignment(JTextField.CENTER);

        // Criar e configurar os spinners para hora e minuto
        hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));

        // Configurar centralização para os editores de hora e minuto
        JTextField hourField = ((JSpinner.NumberEditor) hourSpinner.getEditor()).getTextField();
        hourField.setHorizontalAlignment(JTextField.CENTER);
        JTextField minuteField = ((JSpinner.NumberEditor) minuteSpinner.getEditor()).getTextField();
        minuteField.setHorizontalAlignment(JTextField.CENTER);

        noteField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addAlarm());

        add(new JLabel("Data:"));
        add(dateSpinner);
        add(new JLabel("Hora:"));
        add(hourSpinner);
        add(new JLabel("Minuto:"));
        add(minuteSpinner);
        add(new JLabel("Título do Alarme:"));
        add(noteField);
        add(addButton);
    }

    private void addAlarm() {
        // Obtendo a data selecionada no dateSpinner
        LocalDate date = ((SpinnerDateModel) dateSpinner.getModel()).getDate().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();

        // Obtendo a hora e minutos do hourSpinner e minuteSpinner
        int hour = (int) hourSpinner.getValue();
        int minute = (int) minuteSpinner.getValue();

        // Criando LocalTime com a hora e minuto
        LocalTime time = LocalTime.of(hour, minute);

        // Combinando LocalDate e LocalTime em LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        // Obtendo o texto da nota
        String note = noteField.getText();

        // Adicionando o alarme
        alarmManager.addAlarm(new Alarm(dateTime, note));

        // Atualizando o modelo de lista
        alarmListModel.update();

        // Fechando o diálogo
        setVisible(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Importante para fechar a aplicação

        // Criar uma instância de AlarmManager
        AlarmManager alarmManager = new AlarmManager(); // Suponha que você tenha uma implementação

        // Criar uma instância de AlarmListModel com o AlarmManager
        AlarmListModel alarmListModel = new AlarmListModel(alarmManager);

        // Criar e mostrar o diálogo
        AddAlarmDialog dialog = new AddAlarmDialog(frame, alarmManager, alarmListModel);
        dialog.setVisible(true);
    }
}
