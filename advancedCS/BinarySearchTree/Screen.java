import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.*;
import java.net.MalformedURLException;

public class Screen extends JPanel implements ActionListener{

	public String currentView = "Admin View";
	public String currentAccount = "";
	public String currentAccount2 = "";
	public Account realAccount;
	public Account account;
	public int count = 0;
	public int count2 = 0;
	private TextArea accountDisplay;
	BinarySearchTree<Account> tree; 

	private JButton getAcccount;
	private JButton addAcccount;

	private JButton switchView;
	private JButton logout;
	private JButton signIn;

	private JTextField lastInput;
	private JTextField firstInput;

	private JTextField enterFirst;
	private JTextField enterLast;
	private JTextField enterPin;

	private JTextField lastInput2;
	private JTextField firstInput2;
	private JTextField pinInput;
	private JTextField balanceInput;

	private JTextField lastInput3;
	private JTextField firstInput3;
	private JTextField pinInput2;
	private JTextField balanceInput2;

	private JButton changeLast;
	private JButton changeFirst;
	private JButton changePin;
	private JButton changeBalance;

	private JButton changeLast2;
	private JButton changeFirst2;
	private JButton changePin2;
	private JButton deposit;
	private JButton withdraw;
	private JTextField changeFirst2Input;
	private JTextField changeLast2Input;
	private JTextField changePin2Input;
	private JTextField depositInput;
	private JTextField withdrawInput;

	private JButton deleteAccount;

	public Screen(){
		this.setLayout(null);
		this.setFocusable(true);

		accountDisplay = new TextArea();
		accountDisplay.setBounds(10, 75, 300, 400);
		add(accountDisplay);
		accountDisplay.setVisible(true);

		lastInput = new JTextField(20);
        lastInput.setBounds(600,100,100,30);
        this.add(lastInput);
        lastInput.setVisible(true);

        firstInput = new JTextField(20);
        firstInput.setBounds(480,100,100,30);
        this.add(firstInput);
        firstInput.setVisible(true);

        enterLast = new JTextField(20);
        enterLast.setBounds(50,200,100,30);
        this.add(enterLast);
        enterLast.setVisible(false);

        enterFirst = new JTextField(20);
        enterFirst.setBounds(150,200,100,30);
        this.add(enterFirst);
        enterFirst.setVisible(false);

        enterPin = new JTextField(20);
        enterPin.setBounds(250,200,100,30);
        this.add(enterPin);
        enterPin.setVisible(false);

		getAcccount = new JButton("Get Account");
        getAcccount.setBounds(490,150, 200, 30);
        getAcccount.addActionListener(this);
        this.add(getAcccount);
        getAcccount.setVisible(true);

        addAcccount = new JButton("Add Account");
        addAcccount.setBounds(500,320, 200, 30);
        addAcccount.addActionListener(this);
        this.add(addAcccount);
        addAcccount.setVisible(true);

        switchView = new JButton("Switch View");
        switchView.setBounds(200,20, 200, 30);
        switchView.addActionListener(this);
        this.add(switchView);
        switchView.setVisible(true);

        deleteAccount = new JButton("Delete Account");
        deleteAccount.setBounds(500,550, 200, 30);
        deleteAccount.addActionListener(this);
        this.add(deleteAccount);
        deleteAccount.setVisible(false);

        lastInput2 = new JTextField(20);
        lastInput2.setBounds(350,270,100,30);
        this.add(lastInput2);
        lastInput2.setVisible(true);

        firstInput2 = new JTextField(20);
        firstInput2.setBounds(470,270,100,30);
        this.add(firstInput2);
        firstInput2.setVisible(true);

        lastInput3 = new JTextField(20);
        lastInput3.setBounds(350,420,100,30);
        this.add(lastInput3);
        lastInput3.setVisible(false);

        firstInput3 = new JTextField(20);
        firstInput3.setBounds(470,420,100,30);
        this.add(firstInput3);
        firstInput3.setVisible(false);

        pinInput2 = new JTextField(20);
        pinInput2.setBounds(590,420,100,30);
        this.add(pinInput2);
        pinInput2.setVisible(false);

        balanceInput2 = new JTextField(20);
        balanceInput2.setBounds(710,420,100,30);
        this.add(balanceInput2);
        balanceInput2.setVisible(false);

        changeFirst = new JButton("Change First");
        changeFirst.setBounds(470,470, 100, 30);
        changeFirst.addActionListener(this);
        this.add(changeFirst);
        changeFirst.setVisible(false);

        signIn = new JButton("Log in");
        signIn.setBounds(150,250, 100, 30);
        signIn.addActionListener(this);
        this.add(signIn);
        signIn.setVisible(false);

        logout = new JButton("Log out");
        logout.setBounds(500,250, 100, 30);
        logout.addActionListener(this);
        this.add(logout);
        logout.setVisible(false);

        changeLast = new JButton("Change Last");
        changeLast.setBounds(350,470, 100, 30);
        changeLast.addActionListener(this);
        this.add(changeLast);
        changeLast.setVisible(false);

        changePin = new JButton("Change Pin");
        changePin.setBounds(590,470, 100, 30);
        changePin.addActionListener(this);
        this.add(changePin);
        changePin.setVisible(false);

        changeBalance = new JButton("Change Balance");
        changeBalance.setBounds(710,470, 100, 30);
        changeBalance.addActionListener(this);
        this.add(changeBalance);
        changeBalance.setVisible(false);

        changeFirst2 = new JButton("Change First");
        changeFirst2.setBounds(50,470, 100, 30);
        changeFirst2.addActionListener(this);
        this.add(changeFirst2);
        changeFirst2.setVisible(false);

        changeFirst2Input = new JTextField(20);
        changeFirst2Input.setBounds(50,420,100,30);
        this.add(changeFirst2Input);
        changeFirst2Input.setVisible(false);

        changeLast2 = new JButton("Change Last");
        changeLast2.setBounds(170,470, 100, 30);
        changeLast2.addActionListener(this);
        this.add(changeLast2);
        changeLast2.setVisible(false);

        changeLast2Input = new JTextField(20);
        changeLast2Input.setBounds(170,420,100,30);
        this.add(changeLast2Input);
        changeLast2Input.setVisible(false);

        changePin2 = new JButton("Change Pin");
        changePin2.setBounds(290,470, 100, 30);
        changePin2.addActionListener(this);
        this.add(changePin2);
        changePin2.setVisible(false);

        changePin2Input = new JTextField(20);
        changePin2Input.setBounds(290,420,100,30);
        this.add(changePin2Input);
        changePin2Input.setVisible(false);

        deposit = new JButton("Deposit");
        deposit.setBounds(410,470,100, 30);
        deposit.addActionListener(this);
        this.add(deposit);
        deposit.setVisible(false);

        depositInput = new JTextField(20);
        depositInput.setBounds(410,420,100,30);
        this.add(depositInput);
        depositInput.setVisible(false);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(530,470, 100, 30);
        withdraw.addActionListener(this);
        this.add(withdraw);
        withdraw.setVisible(false);

        withdrawInput = new JTextField(20);
        withdrawInput.setBounds(530,420,100,30);
        this.add(withdrawInput);
        withdrawInput.setVisible(false);

        pinInput = new JTextField(20);
        pinInput.setBounds(590,270,100,30);
        this.add(pinInput);
        pinInput.setVisible(true);

        balanceInput = new JTextField(20);
        balanceInput.setBounds(710,270,100,30);
        this.add(balanceInput);
        balanceInput.setVisible(true);
        tree = new BinarySearchTree<Account>();
        ScanFile();
	}

	public void ScanFile(){
		if ((new File("saveFile.jobj")).exists()) {
            try {
                FileInputStream input = new FileInputStream("saveFile.jobj");
                ObjectInputStream output = new ObjectInputStream(input);
                tree = (BinarySearchTree<Account>) output.readObject();
                output.close();
            } catch (IOException | ClassNotFoundException err) {
                System.out.println(err.toString());
            }
        } else {
			try{
				Scanner scan = new Scanner(new File("names.txt"));
				while(scan.hasNextLine()){
					String input = scan.nextLine();
					String last = input.substring(0,input.indexOf(","));
					String first = input.substring(input.indexOf(",")+1, input.length());
					int ibalance = (int)(Math.random() * 100000);
					double balance = Double.valueOf(ibalance);
					int pin = (int)(Math.random() * 9999 + 1);
					Account newAccount = new Account(first, last, pin, balance);
					tree.add(newAccount);
				}
			} catch (FileNotFoundException err) {
	            System.out.println(err);
	        }
	    }
	}

	private void writeFile() {
        try {
            FileOutputStream foutput = new FileOutputStream("saveFile.jobj");
            ObjectOutputStream ooutput = new ObjectOutputStream(foutput);
            ooutput.writeObject(tree);
            ooutput.close();
        } catch (IOException err) {
            System.out.println(err.toString());
        }
    }

	public void setAccountDisplay(){
		accountDisplay.setText("");
		String text = tree.toString();
		accountDisplay.setText(text);
	}

	public Dimension getPreferredSize(){
		return new Dimension(800,800);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString("Current View: " + currentView, 20, 20);
		if(currentView.equals("Admin View")){
			g.drawString("Account List", 10, 50);
			g.drawString("Last Name: ", 600, 80);
			g.drawString("First Name: ", 480, 80);
			g.drawString("First Name: ", 470, 250);
			g.drawString("Last Name: ", 350, 250);
			g.drawString("Pin: ", 590, 250);
			g.drawString("Balance: ", 710, 250);
			if(count2 > 0){
				g.drawString("Iterations: " + count2, 400, 350);
			}
		}
		if(currentView.equals("Customer View") && currentAccount2.equals("")){
			g.drawString("Last Name: ", 50, 180);
			g.drawString("First Name: ", 150, 180);
			g.drawString("Pin: ", 250, 180);
		}
		if(!currentAccount.equals("")){
			g.drawString(currentAccount, 470, 200);
			g.drawString("Iterations: " + count, 470, 220);
			g.drawString("First Name: ", 470, 400);
			g.drawString("Last Name: ", 350,400);
			g.drawString("Pin: ", 590, 400);
			g.drawString("Balance: ", 710,400);
		}
		if(!currentAccount2.equals("")){
			g.drawString(currentAccount2, 50, 100);
			g.drawString("New First: ", 50, 400);
			g.drawString("New Last: ", 170, 400);
			g.drawString("New Pin: ", 290, 400);
			g.drawString("Deposit Amount: ", 410, 400);
			g.drawString("Withdraw Amount: ", 530, 400);
		}
		
		setAccountDisplay();
		writeFile();
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == getAcccount){
			String first = firstInput.getText();
			String last = lastInput.getText();
			Account a = new Account(first, last, 0, 0.0);
			realAccount = tree.get(a);
			if(realAccount == null){
				return;
			} else {
				currentAccount = realAccount.toString2();
				count = tree.getCount();
				firstInput3.setVisible(true);
				lastInput3.setVisible(true);
				changeFirst.setVisible(true);
				changeLast.setVisible(true);
				changePin.setVisible(true);
				changeBalance.setVisible(true);
				pinInput2.setVisible(true);
				balanceInput2.setVisible(true);
				deleteAccount.setVisible(true);
			}
		}
		if(e.getSource() == addAcccount){
			String first = firstInput2.getText();
			String last = lastInput2.getText();
			String balanceText = balanceInput.getText();
			String pinText = pinInput.getText();
			Double balance = Double.parseDouble(balanceText);
			int pin = Integer.parseInt(pinText);
			tree.add(new Account(first, last, pin, balance));
			count2 = tree.getCount();
			setAccountDisplay();
		}
		if(e.getSource() == changeFirst){
			tree.remove(realAccount);
			String newFirst = firstInput3.getText();
			realAccount = new Account(newFirst, realAccount.getLast(), realAccount.getPin(), realAccount.getBalance());
			tree.add(realAccount);
			currentAccount = realAccount.toString2();
		}
		if(e.getSource() == changeLast){
			tree.remove(realAccount);
			String newLast = lastInput3.getText();
			realAccount = new Account(realAccount.getFirst(), newLast, realAccount.getPin(), realAccount.getBalance());
			tree.add(realAccount);
			currentAccount = realAccount.toString2();
		}
		if(e.getSource() == changeFirst2){
			tree.remove(account);
			String newFirst = changeFirst2Input.getText();
			account = new Account(newFirst, account.getLast(), account.getPin(), account.getBalance());
			tree.add(account);
			currentAccount2 = account.toString2();
		}
		if(e.getSource() == changeLast2){
			tree.remove(account);
			String newLast = changeLast2Input.getText();
			account = new Account(account.getFirst(), newLast, account.getPin(), account.getBalance());
			tree.add(account);
			currentAccount2 = account.toString2();
		}
		if(e.getSource() == changeBalance){
			String balanceText = balanceInput2.getText();
			Double balance = Double.parseDouble(balanceText);
			realAccount.setBalance(balance);
			currentAccount = realAccount.toString2();
		}
		if(e.getSource() == changePin){
			tree.remove(realAccount);
			String pinText = pinInput2.getText();
			int pin = Integer.parseInt(pinText);
			realAccount.setPin(pin);
			tree.add(realAccount);
			currentAccount = realAccount.toString2();
		}
		if(e.getSource() == changePin2){
			tree.remove(account);
			String pinText = changePin2Input.getText();
			int pin = Integer.parseInt(pinText);
			account = new Account(account.getFirst(), account.getLast(), pin, account.getBalance());
			tree.add(account);
			currentAccount2 = account.toString2();
		}
		if(e.getSource() == deposit){
			tree.remove(account);
			String depositText = depositInput.getText();
			double amount = Double.parseDouble(depositText);
			account = new Account(account.getFirst(), account.getLast(), account.getPin(), account.deposit(amount));
			tree.add(account);
			currentAccount2 = account.toString2();
		}
		if(e.getSource() == withdraw){
			tree.remove(account);
			String withdrawText = withdrawInput.getText();
			double amount = Double.parseDouble(withdrawText);
			account = new Account(account.getFirst(), account.getLast(), account.getPin(), account.withdraw(amount));
			tree.add(account);
			currentAccount2 = account.toString2();
		}
		if(e.getSource() == deleteAccount){
			tree.remove(realAccount);
			realAccount = null;
			currentAccount = "";
		}
		if(e.getSource() == switchView){
			if(currentView.equals("Admin View")){
				currentView = "Customer View";
				firstInput.setVisible(false);
				firstInput2.setVisible(false);
				firstInput3.setVisible(false);
				lastInput.setVisible(false);
				lastInput2.setVisible(false);
				lastInput3.setVisible(false);
				balanceInput.setVisible(false);
				balanceInput2.setVisible(false);
				pinInput.setVisible(false);
				pinInput2.setVisible(false);
				addAcccount.setVisible(false);
				getAcccount.setVisible(false);
				deleteAccount.setVisible(false);
				accountDisplay.setVisible(false);
				enterFirst.setVisible(true);
				enterLast.setVisible(true);
				enterPin.setVisible(true);
				signIn.setVisible(true);
				changePin.setVisible(false);
				changeBalance.setVisible(false);
				changeLast.setVisible(false);
				changeFirst.setVisible(false);
				currentAccount = "";
			}
			else if(currentView.equals("Customer View")){
				currentView = "Admin View";
				firstInput.setVisible(true);
				firstInput2.setVisible(true);
				firstInput3.setVisible(true);
				lastInput.setVisible(true);
				lastInput2.setVisible(true);
				lastInput3.setVisible(true);
				balanceInput.setVisible(true);
				balanceInput2.setVisible(true);
				pinInput.setVisible(true);
				pinInput2.setVisible(true);
				addAcccount.setVisible(true);
				getAcccount.setVisible(true);
				deleteAccount.setVisible(true);
				accountDisplay.setVisible(true);
				enterFirst.setVisible(false);
				enterLast.setVisible(false);
				enterPin.setVisible(false);
				signIn.setVisible(false);
				changePin.setVisible(false);
				changeBalance.setVisible(false);
				changeLast.setVisible(false);
				changeFirst.setVisible(false);
				currentAccount = "";
			}
		}
		if(e.getSource() == logout){
			account = null;
			currentAccount2 = "";
			changePin2.setVisible(false);
			changeLast2.setVisible(false);
			changeFirst2.setVisible(false);
			deposit.setVisible(false);
			withdraw.setVisible(false);
			withdrawInput.setVisible(false);
			changePin2Input.setVisible(false);
			changeLast2Input.setVisible(false);
			changeFirst2Input.setVisible(false);
			depositInput.setVisible(false);
			logout.setVisible(false);
			enterFirst.setVisible(true);
			enterLast.setVisible(true);
			enterPin.setVisible(true);
			signIn.setVisible(true);
		}
		if(e.getSource() == signIn){
			currentAccount2 = "";
			String first = enterFirst.getText();
			String last = enterLast.getText();
			String pinText = enterPin.getText();
			int pin = Integer.parseInt(pinText);
			account = new Account(first, last, pin, 0);
			if(tree.contains(account)){
				account = tree.get(account);
				currentAccount2 = account.toString2();
				changePin2.setVisible(true);
				changeLast2.setVisible(true);
				changeFirst2.setVisible(true);
				deposit.setVisible(true);
				withdraw.setVisible(true);
				withdrawInput.setVisible(true);
				changePin2Input.setVisible(true);
				changeLast2Input.setVisible(true);
				changeFirst2Input.setVisible(true);
				depositInput.setVisible(true);
				logout.setVisible(true);
				enterFirst.setVisible(false);
				enterLast.setVisible(false);
				enterPin.setVisible(false);
				signIn.setVisible(false);

			}
		}
		repaint();
	}
}