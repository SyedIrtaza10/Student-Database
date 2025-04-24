import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class StudentTableView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public StudentTableView () {
        setTitle("Student Directory");
        setSize(1000, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(40, 40, 40));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title label
        JLabel titleLabel = new JLabel("Student Directory", JLabel.LEFT);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Search Student by ID
        JLabel label = new JLabel("Search Student by Roll no:");
        label.setBounds(540, 20, 200, 25);
        label.setForeground(Color.WHITE);
        mainPanel.add(label);

        // Create a text field for input
        JTextField textField = new JTextField();
        textField.setBounds(740, 20, 120, 25);
        mainPanel.add(textField);

        // Create a button
        JButton button = new JButton("Search");
        button.setBounds(860, 20, 100, 25);
        mainPanel.add(button);

        JButton hiddenButton = new JButton("Show All Students");
        hiddenButton.setBounds(400, 350, 200, 25);
        hiddenButton.setVisible(false);
        mainPanel.add(hiddenButton);

        // Button action listener
        button.addActionListener(e -> {
            loadStudentByID(textField.getText());
            textField.setText("");
            hiddenButton.setVisible(true);
        });
        hiddenButton.addActionListener(e -> {
            loadStudentData();
            hiddenButton.setVisible(false);
        });

        // Table setup
        String[] columns = {"Roll no", "Name", "Father Name", "Last Name", "Domicile", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.setRowHeight(32);
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(30, 30, 30));
        table.setGridColor(new Color(60, 60, 60));

        // Table header styling
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
        header.setBackground(new Color(50, 50, 50));
        header.setForeground(Color.WHITE);

        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Set content pane
        setContentPane(mainPanel);

        loadStudentData();
    }


    private void loadStudentData () {
        StudentDAO studentDAO = new StudentDAO();
        Student[] students = studentDAO.getAllStudents();

        tableModel.setRowCount(0); // Clear previous data

        for (Student student : students) {
            String[] row = {
                student.getRollNo(),
                student.getFirstName(),
                student.getFatherName(),
                student.getLastName(),
                student.getDomicile(),
                student.getEmail()
            };
            this.tableModel.addRow(row);
        }
    }

    private void loadStudentByID (String id) {
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a roll number");
            return;
        }
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudent(id);

        tableModel.setRowCount(0); // Clear previous data

        if (student == null) {
            JOptionPane.showMessageDialog(this, "Student not found");
            return;
        }

        String[] row = {
            student.getRollNo(),
            student.getFirstName(),
            student.getFatherName(),
            student.getLastName(),
            student.getDomicile(),
            student.getEmail()
        };

        this.tableModel.addRow(row);
    }
}