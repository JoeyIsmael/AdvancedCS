import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel implements ActionListener{

	JTextField pinInput;
	JTextField withdrawInput;
	JTextField nameInput;
	JTextField depositInput;
	JTextField updateNameInput;
	JTextField changeNameInput;
	JTextField changePinInput;
	JButton checkPinButton;
	JButton withdrawButton;
	JButton depositButton;
	JButton updateNameButton;
	JButton logout;
	JButton changeNameButton;
	JButton changePinButton;
	Account activeAccount = null;
	Account[] accounts = new Account[5];
	
	public Screen(){
		this.setLayout(null);
		accounts[0] = new Account("John" , 100.99, 1234);
		accounts[1] = new Account("Jen" , 1000.01,  4321);
		accounts[2] = new Account("Jerry" , 50.50,  1111);
		accounts[3] = new Account("Joseph", 3000, 4444);
		accounts[4] = new Account("Jenna", 2000, 4242);
		
		pinInput = new JTextField();
		pinInput.setBounds(50,250,100,30);
		this.add(pinInput);
			
		checkPinButton = new JButton("Check Pin");
		checkPinButton.setBounds(50,300,100,30);
		checkPinButton.addActionListener(this);
		this.add(checkPinButton);
		
		nameInput = new JTextField();
		nameInput.setBounds(250,250,100,30);
		this.add(nameInput);
		
		withdrawInput = new JTextField();
		withdrawInput.setBounds(50,350,100,30);
		this.add(withdrawInput);
		withdrawInput.setVisible(false);
		
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(50,400,100,30);
		withdrawButton.addActionListener(this);
		this.add(withdrawButton);
		withdrawButton.setVisible(false);
		
		changePinInput = new JTextField();
		changePinInput.setBounds(200,350,100,30);
		this.add(changePinInput);
		changePinInput.setVisible(false);
		
		changePinButton = new JButton("Change Pin");
		changePinButton.setBounds(200,400,200,30);
		changePinButton.addActionListener(this);
		this.add(changePinButton);
		changePinButton.setVisible(false);
		
		depositInput = new JTextField();
		depositInput.setBounds(50,450,100,30);
		this.add(depositInput);
		depositInput.setVisible(false);
		
		depositButton = new JButton("Deposit");
		depositButton.setBounds(50,500,100,30);
		depositButton.addActionListener(this);
		this.add(depositButton);
		depositButton.setVisible(false);
		
		changeNameInput = new JTextField();
		changeNameInput.setBounds(200,450,100,30);
		this.add(changeNameInput);
		changeNameInput.setVisible(false);
		
		changeNameButton = new JButton("Change Name");
		changeNameButton.setBounds(200,500,200,30);
		changeNameButton.addActionListener(this);
		this.add(changeNameButton);
		changeNameButton.setVisible(false);
		
		logout = new JButton("Logout");
		logout.setBounds(50,550,100,30);
		logout.addActionListener(this);
		this.add(logout);
		logout.setVisible(false);
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(800,800);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
		
		Color black = new Color(0,0,0);
		
		g.setColor(black);
		if( activeAccount != null ){
			g.drawString("Name: " + activeAccount.getName(),50,150);
			g.drawString("Balance: " + activeAccount.getBalance(),50,210);
		} else {
			g.drawString("Welcome",50,150);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
			g.drawString("Enter in your account pin:",40,220);
			g.drawString("Enter in your account name:",240,220);

		}
		
	}
	
	public Account lookupAccountByPin(int pinInput, String name)
	{
		for (int i=0; i< accounts.length; i++) {
			accounts[i].setAccess(pinInput, name);
			System.out.println(i + " " + accounts[i].getAccess());
			if(accounts[i].getAccess()) {
				return accounts[i];
			}
		}
		
		return null;
	}
	
	public void actionPerformed(ActionEvent e){
		if( e.getSource() == checkPinButton && activeAccount == null){
			String pinText = pinInput.getText();
			int pinEntered = Integer.parseInt(pinText);
			String name = nameInput.getText();
			this.activeAccount = lookupAccountByPin(pinEntered, name);
			if(this.activeAccount != null){
				depositInput.setVisible(true);
				depositButton.setVisible(true);
				withdrawInput.setVisible(true);
				withdrawButton.setVisible(true);
				logout.setVisible(true);
				nameInput.setVisible(false);
				pinInput.setVisible(false);
				checkPinButton.setVisible(false);
				changeNameButton.setVisible(true);
				changeNameInput.setVisible(true);
				changePinButton.setVisible(true);
				changePinInput.setVisible(true);
				nameInput.setText("");
				pinInput.setText("");
			}
		} else if(activeAccount != null) {		
			if( e.getSource() == withdrawButton){
				String withdrawText = withdrawInput.getText();
				double withdrawEntered = Double.parseDouble(withdrawText);
				
				activeAccount.withdraw(withdrawEntered);
			}
			
			else if( e.getSource() == depositButton){
				String depositText = depositInput.getText();
				double depositEntered = Double.parseDouble(depositText);
				
				activeAccount.deposit(depositEntered);
			}
			
			else if( e.getSource() == changeNameButton){
				String nameChanged = changeNameInput.getText();
				activeAccount.changeName(nameChanged);
			}
			
			else if( e.getSource() == changePinButton){
				String pinEntered = changePinInput.getText();
				int newPin = Integer.parseInt(pinEntered);
				activeAccount.changePin(newPin);
			}
			else if( e.getSource() == logout && activeAccount != null){
				activeAccount.logout();
				activeAccount = null; 
				depositInput.setVisible(false);
				depositButton.setVisible(false);
				withdrawInput.setVisible(false);
				withdrawButton.setVisible(false);
				logout.setVisible(false);
				nameInput.setVisible(true);
				pinInput.setVisible(true);
				checkPinButton.setVisible(true);
				changeNameButton.setVisible(false);
				changeNameInput.setVisible(false);
				changePinButton.setVisible(false);
				changePinInput.setVisible(false);
			}
		}
		repaint();
	}
}
