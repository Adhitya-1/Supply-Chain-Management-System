import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class AddProduct {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel controlPanel;
    private JLabel id,name,price,quantity;
    private static int count = 0;
    GridLayout experimentLayout = new GridLayout(0,2);
    ResultSet rs;

    AddProduct(){
        prepareGUI();
    }

    public static void main(String[] args){
        AddProduct swingControlDemo = new AddProduct();
        swingControlDemo.showButtonDemo();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Add Product Details");
        mainFrame.setSize(700,500);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.getContentPane().setBackground(Color.green);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                mainFrame.setVisible(false);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }


    public void showButtonDemo(){

        headerLabel.setText("Supply Chain Management System");
        headerLabel.setFont(new Font(null, Font.BOLD, 27));

       // id = new JLabel("Enter Product Id");
        //JTextField tf0=new JTextField();
        //tf0.setSize(100,40);

        name = new JLabel("Enter Product Name");
        JTextField tf1=new JTextField();
        tf1.setSize(100,40);

        quantity = new JLabel("Enter Quantity");
        JTextField tf3=new JTextField();
        tf3.setSize(100,40);

        price = new JLabel("Enter Price");
        JTextField tf2=new JTextField();
        tf2.setSize(100,40);

        JButton okButton = new JButton("Add");


        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement pst;
                DBConnection con = new DBConnection();
                try{
                    pst = con.mkDataBase().prepareStatement("insert into product(product_name,price,quantity) values (?,?,?)");
                    //pst.setInt(1, tf0.getX());
                    pst.setString(1, tf1.getText());
                    pst.setInt(3, tf2.getX());
                    pst.setInt(2, Integer.parseInt(tf3.getText()));
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Product Added!" + tf1.getText());
                    mainFrame.setVisible(false);

                }catch(Exception ex){
                    System.out.println(ex);
                    System.out.println("EEEE");
                    JOptionPane.showMessageDialog(null, "Error");
                }finally{

                }
            }
        });



        JPanel jp = new JPanel(null);
        //jp.add(id);
        //jp.add(tf0);
        jp.add(name);
        jp.add(tf1);
        jp.add(quantity);
        jp.add(tf3);
        jp.add(price);
        jp.add(tf2);

        jp.setSize(700,700);
        jp.setLayout(experimentLayout);
        controlPanel.add(jp);
        jp.add(okButton);


        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}

