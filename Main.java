package customerCharge;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String [] args){
        //Get user information
        UserInfo frame = new UserInfo();
        frame.setVisible(true);

        DisplayUserInfo processedInfo = new DisplayUserInfo(frame);
        processedInfo.setVisible(false);
    }

    public static double calculateDiscountOrServiceCharge(UserInfo frame){
        String code = frame.custCode.getText();
        double discount;
        switch(code){
            case "Over 60 days":
                discount = 0.015;
                break;
            case "Current":
                discount = 0.03;
                break;
            case "30-60 days":
                discount = 0;
                break;
            default:
                discount = -1;
        }

        return discount;
    }

    public static double calculateBalanceDue(double discountOrServiceCharge , UserInfo frame){
        double balanceDue;
        double balance = Double.parseDouble(frame.custBalance.getText());

        if(discountOrServiceCharge == 0.03)
        {
            balanceDue = balance - (balance * discountOrServiceCharge);
        }else if(discountOrServiceCharge == 0.015)
        {
            balanceDue = balance + (balance * discountOrServiceCharge);
        }else{
            balanceDue = balance;
        }

        return balanceDue;
    }

    public static void addUserToProcessInfo(UserInfo frame, DisplayUserInfo processedInfo){
        if(frame.custNumber.getText().equals("0") == false)
        {
            //calculate discount Or ServiceCharge
            double discountOrServiceCharge = calculateDiscountOrServiceCharge(frame);
            //calculate balance due
            double balancedue  = calculateBalanceDue(discountOrServiceCharge, frame);
            String discount;
            String serviceCharge;
            if(discountOrServiceCharge == 0.03)
            {
                discount = Double.toString(discountOrServiceCharge);
                serviceCharge =  Double.toString(Double.valueOf(0));
            }else if(discountOrServiceCharge == 0.015)
            {
                serviceCharge = Double.toString(discountOrServiceCharge);
                discount = Double.toString(Double.valueOf(0));
            }else if(discountOrServiceCharge == -1)
            {
                discount = Double.toString(Double.valueOf(0));
                serviceCharge = Double.toString(Double.valueOf(0));
                JOptionPane.showMessageDialog(frame, "Please enter correct code either 'Current' or '30-60 days' or 'Over 60 days'",
                "Error", JOptionPane.ERROR_MESSAGE);
            }else
            {
                discount = Double.toString(Double.valueOf(0));
                serviceCharge = Double.toString(Double.valueOf(0));
            }

            if(discountOrServiceCharge != -1)
            {
                processedInfo.add(new JLabel(frame.custNumber.getText()));
                processedInfo.add(new JLabel(frame.custName.getText()));
                processedInfo.add(new JLabel(frame.custBalance.getText()));
                processedInfo.add(new JLabel(discount));
                processedInfo.add(new JLabel(serviceCharge));
                processedInfo.add(new JLabel(Double.toString(balancedue)));
                processedInfo.add(new JLabel(frame.custCode.getText()));
            }

            if(processedInfo.isVisible())
            {
                processInfo(frame,processedInfo);
            }
        }
    }

    public static void processInfo(UserInfo frame, DisplayUserInfo processedInfo){
        //print out processed information
        processedInfo.setVisible(true);
    }

}

class UserInfo extends JFrame implements ActionListener{
     final int FRAME_WIDTH = 600;
     final int FRAME_HEIGHT = 350;
     JLabel numberlabel;
     JTextField custNumber;
     JLabel namelabel;
     JTextField custName;
     JLabel balancelabel;
     JTextField custBalance;
     JLabel codelabel;
     JTextField custCode;
     JButton continueButton;
     JButton addButton;
    public UserInfo()
    {
        super("Users Basic Information");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        numberlabel = new JLabel("Customer Number:");
        custNumber = new JTextField(10);
        namelabel = new JLabel("Customer Name:");
        custName = new JTextField(20);
        balancelabel = new JLabel("Customer Balance:");
        custBalance = new JTextField(30);
        codelabel = new JLabel("Customer Code:");
        custCode = new JTextField(30);
        addButton = new JButton("Add Customer");
        continueButton = new JButton("Click to continue");
        setLayout(new GridLayout(0,2,0,3));
        add(numberlabel);
        add(custNumber);
        add(namelabel);
        add(custName);
        add(balancelabel);
        add(custBalance);
        add(codelabel);
        add(custCode);
        add(addButton);
        add(continueButton);
        addButton.addActionListener(this);
        continueButton.addActionListener(this);
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton)
        {
            Main.addUserToProcessInfo(this,DisplayUserInfo.processedInfo);
        }else{
            Main.processInfo(this,DisplayUserInfo.processedInfo);
        }
    }
}

class DisplayUserInfo extends JFrame{
    static DisplayUserInfo processedInfo;
    public DisplayUserInfo(UserInfo frame)
    {

        super("Calculated Information");
        final int FRAME_WIDTH = 1100;
        final int FRAME_HEIGHT = 200;
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel custNumberLabel = new JLabel("Customer Number");
        JLabel custNameLabel = new JLabel("Customer Name");
        JLabel custBalanceLabel = new JLabel("Customer Balance");
        JLabel custDiscountLabel = new JLabel("Customer Discount");
        JLabel custServiceLabel = new JLabel("Customer Service Charge");
        JLabel custBalancedueLabel = new JLabel("Customer Balancedue");
        JLabel categoryLabel = new JLabel("Category"); 
        setLayout(new GridLayout(0,7,0,0));
        add(custNumberLabel);
        add(custNameLabel);
        add(custBalanceLabel);
        add(custDiscountLabel);
        add(custServiceLabel);
        add(custBalancedueLabel);
        add(categoryLabel);
        processedInfo = this;

    }
}

