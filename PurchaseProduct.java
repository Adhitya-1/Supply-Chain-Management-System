import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseProduct {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel id,pid,quantity;
    private static int count = 0;
    GridLayout experimentLayout = new GridLayout(0,2);
    ResultSet rs;

    PurchaseProduct(){
        prepareGUI();
    }

    public static void main(String[] args){
        PurchaseProduct swingControlDemo = new PurchaseProduct();
        swingControlDemo.showButtonDemo();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Purchase Product");
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

        pid = new JLabel("Enter Product Id");
        JTextField tf2=new JTextField();
        tf2.setSize(100,30);

        quantity = new JLabel("Enter Quantity");
        JTextField tf3=new JTextField();
        tf3.setSize(100,30);
        JButton okButton = new JButton("Place Order");

        id = new JLabel("Enter Product Name");
        JTextField tf1=new JTextField();
        tf1.setSize(100,30);


        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement pst;
                DBConnection con = new DBConnection();
                try{
                    pst = con.mkDataBase().prepareStatement("UPDATE product SET quantity = ?, price = ? WHERE product_name = ?");
                    pst.setInt(1, Integer.parseInt(tf2.getText())); // Set the new quantity
                    pst.setDouble(2, Double.parseDouble(tf3.getText())); // Set the new price
                    pst.setString(3, tf1.getText()); // Set the product_name for updating

                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Product Ordered! Quantity: " + tf2.getText());
                    mainFrame.setVisible(false);


                    //pst = con.mkDataBase().prepareStatement("UPDATE product SET quantity= ?, price=? where product_name = ?");
                   // pst.setString(3, tf2.getText());
                   // pst.setDouble(2, Double.parseDouble(tf3.getText()));
                    //pst.execute();

                    //JOptionPane.showMessageDialog(null, "Product Ordered! Amount: " + tf2.getText());
                    //mainFrame.setVisible(false);

                }catch(Exception ex){
                    System.out.println(ex);
                    //System.out.println("Error");
                    JOptionPane.showMessageDialog(null, "Error");
                }finally{

                }
            }
        });

        JPanel jp = new JPanel();
        jp.add(pid);
        jp.add(tf2);
        jp.add(quantity);
        jp.add(tf3);
        jp.add(id);
        jp.add(tf1);


        jp.setSize(200,200);
        jp.setLayout(experimentLayout);
        controlPanel.add(jp);
        jp.add(okButton);



        mainFrame.setVisible(true);

        mainFrame.setLocationRelativeTo(null);
    }
}

