import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SearchProduct {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel pid;
    private static int count = 0;
    GridLayout experimentLayout = new GridLayout(0,2);
    ResultSet rs;

    SearchProduct(){
        prepareGUI();
    }

    public static void main(String[] args){
        SearchProduct  swingControlDemo = new SearchProduct();
        swingControlDemo.showButtonDemo();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Search Product");
        mainFrame.setSize(700,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.getContentPane().setBackground(Color.green);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                mainFrame.setVisible(false);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);

        statusLabel.setSize(350,400);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }


    public void showButtonDemo(){

        headerLabel.setText("Supply Chain Management System");
        headerLabel.setFont(new Font(null, Font.BOLD, 27));
        headerLabel.setForeground(Color.black);

        pid = new JLabel("Enter Product Name");
        JTextField tf2=new JTextField();
        tf2.setSize(100,30);


        JButton okButton = new JButton("Search Product");


        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement pst;
                DBConnection con = new DBConnection();
                try{
                    pst = con.mkDataBase().prepareStatement("SELECT * FROM product WHERE product_name = ?");
                    pst.setString(1, tf2.getText()); // Set the product_id value from tf2

                    ResultSet resultSet = pst.executeQuery();
                    String message = ""; // Initialize an empty string to accumulate data

                    while (resultSet.next()) {
                        int productId = resultSet.getInt("product_id");
                        String productName = resultSet.getString("product_name");
                        int quantity = resultSet.getInt("quantity");
                        double price = resultSet.getDouble("price");

                        // Append retrieved data to the message string
                        message += "Product ID: " + productId + "\n";
                        message += "Product Name: " + productName + "\n";
                        message += "Quantity: " + quantity + "\n";
                        message += "Price: " + price + "\n\n";
                    }

                    if (!message.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Product Details Available:\n" + message);
                    } else {
                        JOptionPane.showMessageDialog(null, "No Product Details Found");
                    }



                }catch(Exception ex){
                    System.out.println(ex);
                    System.out.println("Error");
                    JOptionPane.showMessageDialog(null, "Error");
                }finally{

                }
            }
        });

        JPanel jp = new JPanel();
        jp.add(pid);
        jp.add(tf2);

        jp.setSize(200,200);
        jp.setLayout(experimentLayout);
        controlPanel.add(jp);
        jp.add(okButton);



        mainFrame.setVisible(true);

        mainFrame.setLocationRelativeTo(null);
    }
}
