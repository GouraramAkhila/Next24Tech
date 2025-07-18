import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ResumeBuilderApp extends JFrame implements ActionListener {
    private JTextField nameField, emailField, phoneField;
    private JTextArea experienceArea, educationArea, skillsArea;
    private JComboBox<String> templateSelector;
    private JButton exportButton;

    public ResumeBuilderApp() {
        setTitle("Resume Builder");
        setSize(600, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(230, 240, 255));

        // Top Panel
        JPanel topPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Enter Your Details"));
        topPanel.setBackground(new Color(255, 255, 240));

        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        experienceArea = new JTextArea(5, 20);
        educationArea = new JTextArea(5, 20);
        skillsArea = new JTextArea(5, 20);

        experienceArea.setLineWrap(true);
        educationArea.setLineWrap(true);
        skillsArea.setLineWrap(true);

        JScrollPane expScroll = new JScrollPane(experienceArea);
        JScrollPane eduScroll = new JScrollPane(educationArea);
        JScrollPane skillsScroll = new JScrollPane(skillsArea);

        topPanel.add(new JLabel("Full Name:"));
        topPanel.add(nameField);
        topPanel.add(new JLabel("Email:"));
        topPanel.add(emailField);
        topPanel.add(new JLabel("Phone:"));
        topPanel.add(phoneField);
        topPanel.add(new JLabel("Experience:"));
        topPanel.add(expScroll);
        topPanel.add(new JLabel("Education:"));
        topPanel.add(eduScroll);
        topPanel.add(new JLabel("Skills:"));
        topPanel.add(skillsScroll);

        // Template Selector
        JPanel templatePanel = new JPanel();
        templatePanel.setBackground(new Color(220, 230, 250));
        templateSelector = new JComboBox<>(new String[]{"Classic", "Modern", "Creative"});
        templateSelector.setBackground(new Color(240, 248, 255));
        templateSelector.setForeground(new Color(25, 25, 112));
        templatePanel.setBorder(BorderFactory.createTitledBorder("Choose Template"));
        templatePanel.add(templateSelector);

        // Export Button
        exportButton = new JButton("Export Resume");
        exportButton.setBackground(new Color(100, 149, 237));
        exportButton.setForeground(Color.WHITE);
        exportButton.setFocusPainted(false);
        exportButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        exportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exportButton.setBackground(new Color(65, 105, 225));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exportButton.setBackground(new Color(100, 149, 237));
            }
        });
        exportButton.addActionListener(this);

        add(templatePanel, BorderLayout.NORTH);
        add(topPanel, BorderLayout.CENTER);
        add(exportButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exportButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String experience = experienceArea.getText();
            String education = educationArea.getText();
            String skills = skillsArea.getText();
            String template = (String) templateSelector.getSelectedItem();

            StringBuilder resume = new StringBuilder();
            resume.append("=== " + name + " ===\n");
            resume.append("Email: " + email + "\n");
            resume.append("Phone: " + phone + "\n\n");
            resume.append("Experience:\n" + experience + "\n\n");
            resume.append("Education:\n" + education + "\n\n");
            resume.append("Skills:\n" + skills + "\n\n");
            resume.append("[Template: " + template + "]");

            try {
                File file = new File("resume.txt");
                FileWriter writer = new FileWriter(file);
                writer.write(resume.toString());
                writer.close();
                JOptionPane.showMessageDialog(this, "Resume exported to " + file.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error exporting resume.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ResumeBuilderApp app = new ResumeBuilderApp();
            app.setVisible(true);
            app.setLocationRelativeTo(null);
        });
    }
}
