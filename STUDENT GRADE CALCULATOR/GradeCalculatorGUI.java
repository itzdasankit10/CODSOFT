package company.Javass.CodeSoft;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GradeCalculatorGUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));


        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Number of subjects:"));
        JTextField countField = new JTextField(5);
        topPanel.add(countField);
        JButton generateBtn = new JButton("Generate Fields");
        topPanel.add(generateBtn);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(centerPanel);


        JPanel bottomPanel = new JPanel(new GridLayout(3,1,5,5));
        JButton calcBtn = new JButton("Calculate");
        JLabel totalLabel = new JLabel("Total: ");
        JLabel avgLabel   = new JLabel("Average: ");
        JLabel gradeLabel = new JLabel("Grade: ");
        bottomPanel.add(calcBtn);
        bottomPanel.add(totalLabel);
        bottomPanel.add(avgLabel);
        bottomPanel.add(gradeLabel);

        frame.add(topPanel,   BorderLayout.NORTH);
        frame.add(scrollPane,  BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setSize(400, 500);
        frame.setVisible(true);


        generateBtn.addActionListener(e -> {
            centerPanel.removeAll();
            try {
                int n = Integer.parseInt(countField.getText());
                for (int i = 1; i <= n; i++) {
                    JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    p.add(new JLabel("Subject " + i + ":"));
                    p.add(new JTextField(5));
                    centerPanel.add(p);
                }
                centerPanel.revalidate();
                centerPanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Enter a valid integer for number of subjects.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        calcBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Component[] comps = centerPanel.getComponents();
                int total = 0, n = comps.length;
                for (Component comp : comps) {
                    JPanel p = (JPanel) comp;
                    JTextField tf = (JTextField) p.getComponent(1);
                    try {
                        int marks = Integer.parseInt(tf.getText());
                        if (marks < 0 || marks > 100) throw new NumberFormatException();
                        total += marks;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame,
                                "Please enter valid marks (0–100) for all subjects.",
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                double avg = (double) total / n;
                totalLabel.setText("Total: " + total + " / " + (n * 100));
                avgLabel.setText(String.format("Average: %.2f%%", avg));
                gradeLabel.setText("Grade: " + calculateGrade(avg));
            }
        });
    }

    private static String calculateGrade(double avg) {
        if (avg >= 90) return "A";
        else if (avg >= 80) return "B";
        else if (avg >= 70) return "C";
        else if (avg >= 60) return "D";
        else return "F";
    }
}

