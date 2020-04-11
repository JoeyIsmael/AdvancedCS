import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class Screen extends JPanel implements ActionListener, ListSelectionListener {

    private JButton dealerView;
    private JButton consumerView;
    private HashTable<Car> table;
    private JList<String> carList;
    private String text = " ";
    private String currentView = "Comsumer View";
    private JButton c1;
    private JButton c2;
    private JButton c3;
    private JButton c4;
    private JButton c5; 
    private JButton r1;
    private JButton r2;
    private JButton r3;
    private JButton r4;
    private JButton r5; 
    private JButton u1;
    private JButton u2;
    private JButton u3;
    private JButton u4;
    private JButton u5; 
    private JButton a1;
    private JTextField yearInput;
    private JTextField modelNameInput;
    private JTextField priceInput;
    private JTextField makeInput;
    private JTextField f1;
    private JTextField f2;
    private JTextField f3;
    private JTextField f4;
    private JTextField f5;
    String make = "";
    private JButton[] buttons = new JButton[5];
    private JButton[] rButtons = new JButton[5];
    private JButton[] uButtons = new JButton[5];
    private JTextField[] fields = new JTextField[5];
    boolean isdealerView = false;
    boolean isConsumerView = true;

  public Screen(){
	this.setLayout(null);
	this.setFocusable(true);
    table = new HashTable<Car>();
    table.add(new Car("Toyota", "Corolla", 2020, 30000));
    table.add(new Car("Toyota", "Prius", 2027, 80000));
    table.add(new Car("Jeep", "Compass", 1968, 15000));
    table.add(new Car("Jeep", "Gladiator", 1998, 50000));
    table.add(new Car("Jeep", "Wrangler", 2016, 1000000));
    table.add(new Car("Volkswagon", "Golf", 2020, 87000));
    table.add(new Car("Volkswagon", "Jetta", 2018, 56000));
    table.add(new Car("BMW", "3 Series", 2019, 99000));
    table.add(new Car("Range Rover", "Sport", 2017, 78000));
    table.add(new Car("Range Rover", "Discovery", 2003, 18000));

   
    dealerView = new JButton("Change to Dealer View");
    dealerView.setBounds(10,25,200,30);
    dealerView.addActionListener(this);
    this.add(dealerView);
    dealerView.setVisible(true);

    consumerView = new JButton("Change to Consumer View");
    consumerView.setBounds(10,25,200,30);
    consumerView.addActionListener(this);
    this.add(consumerView);
    consumerView.setVisible(false);

    c1 = new JButton("Buy");
    c1.setBounds(250,200,100,30);
    c1.addActionListener(this);
    this.add(c1);
    c1.setVisible(false);

    c2 = new JButton("Buy");
    c2.setBounds(350,200,100,30);
    c2.addActionListener(this);
    this.add(c2);
    c2.setVisible(false);

    c3 = new JButton("Buy");
    c3.setBounds(450,200,100,30);
    c3.addActionListener(this);
    this.add(c3);
    c3.setVisible(false);

    c4 = new JButton("Buy");
    c4.setBounds(550,200,100,30);
    c4.addActionListener(this);
    this.add(c4);
    c4.setVisible(false);

    c5 = new JButton("Buy");
    c5.setBounds(650,200,100,30);
    c5.addActionListener(this);
    this.add(c5);
    c5.setVisible(false);

    a1 = new JButton("Add");
    a1.setBounds(10,550,100,30);
    a1.addActionListener(this);
    this.add(a1);
    a1.setVisible(false);

    r1 = new JButton("Remove");
    r1.setBounds(250,250,100,30);
    r1.addActionListener(this);
    this.add(r1);
    r1.setVisible(false);

    r2 = new JButton("Remove");
    r2.setBounds(350,250,100,30);
    r2.addActionListener(this);
    this.add(r2);
    r2.setVisible(false);

    r3 = new JButton("Remove");
    r3.setBounds(450,250,100,30);
    r3.addActionListener(this);
    this.add(r3);
    r3.setVisible(false);

    r4 = new JButton("Remove");
    r4.setBounds(550,250,100,30);
    r4.addActionListener(this);
    this.add(r4);
    r4.setVisible(false);

    r5 = new JButton("Remove");
    r5.setBounds(650,250,100,30);
    r5.addActionListener(this);
    this.add(r5);
    r5.setVisible(false);

    u1 = new JButton("Update P");
    u1.setBounds(250,220,100,20);
    u1.addActionListener(this);
    this.add(u1);
    u1.setVisible(false);

    u2 = new JButton("Update P");
    u2.setBounds(350,220,100,20);
    u2.addActionListener(this);
    this.add(u2);
    u2.setVisible(false);

    u3 = new JButton("Update P");
    u3.setBounds(450,220,100,20);
    u3.addActionListener(this);
    this.add(u3);
    u3.setVisible(false);

    u4 = new JButton("Update P");
    u4.setBounds(550,220,100,20);
    u4.addActionListener(this);
    this.add(u4);
    u4.setVisible(false);

    u5 = new JButton("Update P");
    u5.setBounds(650,220,100,20);
    u5.addActionListener(this);
    this.add(u5);
    u5.setVisible(false);

    yearInput = new JTextField(20);
    yearInput.setBounds(10,350,100,30);
    this.add(yearInput);
    yearInput.setVisible(false);

    modelNameInput = new JTextField(20);
    modelNameInput.setBounds(10,400,100,30);
    this.add(modelNameInput);
    modelNameInput.setVisible(false);

    priceInput = new JTextField(20);
    priceInput.setBounds(10,450,100,30);
    this.add(priceInput);
    priceInput.setVisible(false);

    makeInput = new JTextField(20);
    makeInput.setBounds(10,500,100,30);
    this.add(makeInput);
    makeInput.setVisible(false);

    f1 = new JTextField(20);
    f1.setBounds(250,190,100,30);
    this.add(f1);
    f1.setVisible(false);

    f2 = new JTextField(20);
    f2.setBounds(350,190,100,30);
    this.add(f2);
    f2.setVisible(false);

    f3 = new JTextField(20);
    f3.setBounds(450,190,100,30);
    this.add(f3);
    f3.setVisible(false);

    f4 = new JTextField(20);
    f4.setBounds(550,190,100,30);
    this.add(f4);
    f4.setVisible(false);

    f5 = new JTextField(20);
    f5.setBounds(650,190,100,30);
    this.add(f5);
    f5.setVisible(false);

    buttons[0] = c1;
    buttons[1] = c2;
    buttons[2] = c3;
    buttons[3] = c4;
    buttons[4] = c5;
    rButtons[0] = r1;
    rButtons[1] = r2;
    rButtons[2] = r3;
    rButtons[3] = r4;
    rButtons[4] = r5;
    uButtons[0] = u1;
    uButtons[1] = u2;
    uButtons[2] = u3;
    uButtons[3] = u4;
    uButtons[4] = u5;
    fields[0] = f1;
    fields[1] = f2;
    fields[2] = f3;
    fields[3] = f4;
    fields[4] = f5;

    String[] choices = {"Toyota", "Jeep", "Volkswagon", "BMW", "Range Rover"};
    carList = new JList<String>(choices);
    carList.setBounds(200,150,80,100);
    this.add(carList);
    carList.addListSelectionListener(this);
     
    JScrollPane scrollPane = new JScrollPane(carList);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setBounds(10,75,200,250);

    this.add(scrollPane);
  }
     
    public Dimension getPreferredSize(){
        return new Dimension(800,600);  
    }
     
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(isdealerView){
        	g.drawString("Year:", 10, 345);
        	g.drawString("Model Name:", 10, 395);
        	g.drawString("Price:", 10, 445);
        	g.drawString("Make:", 10, 495);
        }
        g.drawString("Current View: " + currentView, 10, 20);

        if(make.equals("") == false){
        	int increment = 0;
    		Car newCar = new Car(make);
			String carsinBucket = table.getBucket(newCar.hashCode());
	        String[] strArray = carsinBucket.split(",");
	        int y = 100;
			int x = 250;		
	        for (int i = 0; i < strArray.length; i++) {
				g.drawString(strArray[i].toString(), x, y);
				if(x == 250){
					if(isConsumerView){
						c1.setVisible(true);
					}
					if(isdealerView){
						r1.setVisible(true);
						f1.setVisible(true);
						u1.setVisible(true);
					}
				} else if(x == 350){
					if(isConsumerView){
						c2.setVisible(true);
					}
					if(isdealerView){
						r2.setVisible(true);
						f2.setVisible(true);
						u2.setVisible(true);
					}
				} else if(x == 450){
					if(isConsumerView){
						c3.setVisible(true);
					}
					if(isdealerView){
						r3.setVisible(true);
						f3.setVisible(true);
						u3.setVisible(true);
					}
				} else if(x == 550){
					if(isConsumerView){
						c4.setVisible(true);
					}
					if(isdealerView){
						r4.setVisible(true);
						f4.setVisible(true);
						u4.setVisible(true);
					}
				} else if(x == 650){
					if(isConsumerView){
						c5.setVisible(true);
					}
					if(isdealerView){
						r5.setVisible(true);
						f5.setVisible(true);
						u5.setVisible(true);
					}
				}
				y += 25;
				increment++;
				if(y == 200){
					y = 100;
					x = x + 100;
				}
			}
    	}
    }

    public void updateJList(){
    	String[] choices = {"Toyota", "Jeep", "Volkswagon", "BMW", "Range Rover"};
    	carList = new JList<String>(choices);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == dealerView){
        	if(buttons.length == 0){
        		buttons[0].setVisible(false);
        	} else if(buttons.length > 0){
        		for(int i = 0; i < buttons.length; i++){
		    		buttons[i].setVisible(false);
		    	}
        	}
        	if(rButtons.length == 0){
        		rButtons[0].setVisible(false);
        	} else if(rButtons.length > 0){
        		for(int i = 0; i < rButtons.length; i++){
		    		rButtons[i].setVisible(false);
		    	}
        	}

        	if(fields.length == 0){
        		fields[0].setVisible(false);
        	} else if(fields.length > 0){
        		for(int i = 0; i < fields.length; i++){
		    		fields[i].setVisible(false);
		    	}
        	}

        	if(uButtons.length == 0){
        		uButtons[0].setVisible(false);
        	} else if(uButtons.length > 0){
        		for(int i = 0; i < uButtons.length; i++){
		    		uButtons[i].setVisible(false);
		    	}
        	}

        	dealerView.setVisible(false);
        	consumerView.setVisible(true);
        	currentView = "Dealer View";
        	isdealerView = true;
        	isConsumerView = false;
        	modelNameInput.setVisible(true);
        	priceInput.setVisible(true);
        	yearInput.setVisible(true);
        	makeInput.setVisible(true);
        	a1.setVisible(true);
        }

        if(e.getSource() == consumerView){
        	if(buttons.length == 0){
        		buttons[0].setVisible(false);
        	} else if(buttons.length > 0){
        		for(int i = 0; i < buttons.length; i++){
		    		buttons[i].setVisible(false);
		    	}
        	}
        	if(rButtons.length == 0){
        		rButtons[0].setVisible(false);
        	} else if(rButtons.length > 0){
        		for(int i = 0; i < rButtons.length; i++){
		    		rButtons[i].setVisible(false);
		    	}
        	}

        	if(fields.length == 0){
        		fields[0].setVisible(false);
        	} else if(fields.length > 0){
        		for(int i = 0; i < fields.length; i++){
		    		fields[i].setVisible(false);
		    	}
        	}

        	if(uButtons.length == 0){
        		uButtons[0].setVisible(false);
        	} else if(uButtons.length > 0){
        		for(int i = 0; i < uButtons.length; i++){
		    		uButtons[i].setVisible(false);
		    	}
        	}

        	isdealerView = false;
        	isConsumerView = true;
        	currentView = "Consumer View";
        	dealerView.setVisible(true);
        	consumerView.setVisible(false);
        	modelNameInput.setVisible(false);
        	priceInput.setVisible(false);
        	yearInput.setVisible(false);
        	makeInput.setVisible(false);
        	a1.setVisible(false);
        }

        if(e.getSource() == c1 || e.getSource() == r1){
        	if(buttons.length == 1){
        		buttons[0].setVisible(false);
        	} else if(buttons.length > 0){
        		for(int i = 0; i < buttons.length; i++){
		    		buttons[i].setVisible(false);
		    	}
        	}
        	if(rButtons.length == 1){
        		rButtons[0].setVisible(false);
        	} else if(rButtons.length > 0){
        		for(int i = 0; i < rButtons.length; i++){
		    		rButtons[i].setVisible(false);
		    	}
        	}
        	make = carList.getSelectedValue();
        	Car newCar = new Car(make);
        	table.removeBucket(newCar.hashCode(), 0);

        }

        if(e.getSource() == c2 || e.getSource() == r2){
        	if(buttons.length == 1){
        		buttons[0].setVisible(false);
        	} else if(buttons.length > 0){
        		for(int i = 0; i < buttons.length; i++){
		    		buttons[i].setVisible(false);
		    	}
        	}
        	if(rButtons.length == 1){
        		rButtons[0].setVisible(false);
        	} else if(buttons.length > 0){
        		for(int i = 0; i < rButtons.length; i++){
		    		rButtons[i].setVisible(false);
		    	}
        	}
        	make = carList.getSelectedValue();
        	Car newCar = new Car(make);
        	table.removeBucket(newCar.hashCode(), 1);

        }

        if(e.getSource() == c3 || e.getSource() == r3){
        	if(buttons.length == 1){
        		buttons[0].setVisible(false);
        	} else if(buttons.length > 0){
        		for(int i = 0; i < buttons.length; i++){
		    		buttons[i].setVisible(false);
		    	}
        	}
        	if(rButtons.length == 1){
        		rButtons[0].setVisible(false);
        	} else if(rButtons.length > 0){
        		for(int i = 0; i < rButtons.length; i++){
		    		rButtons[i].setVisible(false);
		    	}
        	}
        	make = carList.getSelectedValue();
        	Car newCar = new Car(make);
        	table.removeBucket(newCar.hashCode(), 2);

        }

        if(e.getSource() == c4 || e.getSource() == r4){
        	if(buttons.length == 1){
        		buttons[0].setVisible(false);
        	} else if(buttons.length > 0){
        		for(int i = 0; i < buttons.length; i++){
		    		buttons[i].setVisible(false);
		    	}
        	}
        	if(rButtons.length == 1){
        		rButtons[0].setVisible(false);
        	} else if(rButtons.length > 0){
        		for(int i = 0; i < rButtons.length; i++){
		    		rButtons[i].setVisible(false);
		    	}
        	}
        	make = carList.getSelectedValue();
        	Car newCar = new Car(make);
        	table.removeBucket(newCar.hashCode(), 3);

        }

        if(e.getSource() == c5 || e.getSource() == r5){
        	if(buttons.length == 1){
        		buttons[0].setVisible(false);
        	} else if(buttons.length > 0){
        		for(int i = 0; i < buttons.length; i++){
		    		buttons[i].setVisible(false);
		    	}
        	}
        	if(rButtons.length == 1){
        		rButtons[0].setVisible(false);
        	} else if(rButtons.length > 0){
        		for(int i = 0; i < rButtons.length; i++){
		    		rButtons[i].setVisible(false);
		    	}
        	}
        	make = carList.getSelectedValue();
        	Car newCar = new Car(make);
        	table.removeBucket(newCar.hashCode(), 4);

        }
        if(e.getSource() == a1){
        	String newMake = makeInput.getText();
        	String newModel = modelNameInput.getText();
        	String yearI = yearInput.getText();
        	String priceI = priceInput.getText();
        	int newYear = Integer.parseInt(yearI);
        	double newPrice = Double.parseDouble(priceI);
        	Car newCar = new Car(newMake, newModel, newYear, newPrice);
        	table.add(newCar);
        	table.toString();
        }
        if(e.getSource() == u1){
        	String priceI = f1.getText();
        	double newPrice = Double.parseDouble(priceI);

        	make = carList.getSelectedValue();
        	Car newCar = new Car(make);
        	table.getCar(newCar.hashCode(), 0).setPrice(newPrice);

        }
        if(e.getSource() == u2){
        	String priceI = f2.getText();
        	double newPrice = Double.parseDouble(priceI);

        	make = carList.getSelectedValue();
        	Car newCar = new Car(make);
        	table.getCar(newCar.hashCode(), 1).setPrice(newPrice);

        }
        if(e.getSource() == u3){
        	String priceI = f3.getText();
        	double newPrice = Double.parseDouble(priceI);

        	make = carList.getSelectedValue();
        	Car newCar = new Car(make);
        	table.getCar(newCar.hashCode(), 2).setPrice(newPrice);

        }
        if(e.getSource() == u4){
        	String priceI = f4.getText();
        	double newPrice = Double.parseDouble(priceI);

        	make = carList.getSelectedValue();
        	Car newCar = new Car(make);
        	table.getCar(newCar.hashCode(), 3).setPrice(newPrice);

        }
        if(e.getSource() == u5){
        	String priceI = f5.getText();
        	double newPrice = Double.parseDouble(priceI);

        	make = carList.getSelectedValue();
        	Car newCar = new Car(make);
        	table.getCar(newCar.hashCode(), 4).setPrice(newPrice);

        }


        repaint();
    }

    public void valueChanged(ListSelectionEvent e){
        //set the text of the label to the selected value of lists
        //text = carList.get();
        for(int i = 0; i < buttons.length; i++){
        	buttons[i].setVisible(false);
        }

        for(int i = 0; i < rButtons.length; i++){
        	rButtons[i].setVisible(false);
        }

        for(int i = 0; i < fields.length; i++){
        	fields[i].setVisible(false);
        }

        for(int i = 0; i < uButtons.length; i++){
        	uButtons[i].setVisible(false);
        }

        make = carList.getSelectedValue();

        repaint();
    }



}