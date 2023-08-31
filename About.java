import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel controlPanel;
    private GridLayout experimentLayout;

    public About() {
        prepareGUI();
    }

    public static void main(String[] args) {
        About about = new About();
        about.showButtonDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("About");
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new GridLayout(2, 1));

        controlPanel = new JPanel();
        mainFrame.add(controlPanel);

        experimentLayout = new GridLayout(0, 2);

        headerLabel = new JLabel("", JLabel.CENTER);
        controlPanel.add(headerLabel);

        mainFrame.setVisible(true);
    }

    public void showButtonDemo() {
        headerLabel.setText("Supply Chain Management System");
        headerLabel.setFont(new Font(null, Font.BOLD, 27));
        headerLabel.setForeground(Color.black);

        JButton welcomeButton = new JButton("Welcome");
        welcomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Welcome to Supply Chain Management System");
            }
        });

        JPanel jp = new JPanel(null);
        jp.add(welcomeButton);
        jp.setSize(700, 700);
        jp.setLayout(experimentLayout);
        controlPanel.add(jp);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
