import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Screen extends JPanel implements ActionListener{
	
	private JScrollPane peoplePane;
	private List<Employee> displayedPeople;
	private JList peopleList;
	private JTextField getEmployee;
	private JButton getEmployeeButton;
	private JButton displayTeachers;
	private JButton displayPolice;
	private JButton displayEngineers;
	private JButton displayBankers;
	private JButton displayAll;
	private JButton remove;
		
	private ArrayList<Employee> people = new ArrayList<Employee>();
	
	public Screen(){
		this.setLayout(null);
		this.setFocusable(true);
		
		people.add(new Teacher("Mr Smith", "teacher1.jpg", "Math Teacher" , "Los Altos"));
		people.add(new Teacher("Mrs Jessica", "teacher2.jpg", "English Teacher" , "Mountain View"));
		people.add(new PoliceOfficer("Tom", "police1.jpg", "Police Officer", "Mountain View"));
		people.add(new PoliceOfficer("Frank", "police2.jpg", "Police Officer", "Santa Clara"));
		people.add(new PrivateEngineer("Holland", "plumber.jpg", "Plumber", "Roto-Rooter"));
		people.add(new PrivateEngineer("Jenna", "electrician.jpg", "Electrician", "CityWide Electric"));
		people.add(new PrivateBanker("Susan", "banker1.jpg", "Banker", "Wells Fargo"));
		people.add(new PrivateBanker("Jason", "banker2.jpg", "Banker", "Chase"));
		
		
		displayedPeople = (ArrayList<Employee>)people.clone();
		
		
		peopleList = new JList(displayedPeople.toArray());
		peopleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		peopleList.setLayoutOrientation(JList.VERTICAL);
		
		peoplePane = new JScrollPane(peopleList);
		peoplePane.setPreferredSize(new Dimension(400, 400));
		peoplePane.setBounds(25, 25, 400, 500);
		add(peoplePane);	
		
		displayTeachers = new JButton("Display Teachers");
		displayTeachers.setBounds(500,50,175,30);
		displayTeachers.addActionListener(this);
		this.add(displayTeachers);
		
		displayPolice = new JButton("Display Police");
		displayPolice.setBounds(500,100,175,30);
		displayPolice.addActionListener(this);
		this.add(displayPolice);
		
		displayEngineers = new JButton("Display Engineers");
		displayEngineers.setBounds(500,150,175,30);
		displayEngineers.addActionListener(this);
		this.add(displayEngineers);
		
		displayBankers = new JButton("Display Bankers");
		displayBankers.setBounds(500,200,175,30);
		displayBankers.addActionListener(this);
		this.add(displayBankers);
		
		displayAll = new JButton("Display All");
		displayAll.setBounds(500,250,175,30);
		displayAll.addActionListener(this);
		this.add(displayAll);
		
		getEmployee = new JTextField();
		getEmployee.setBounds(500,300,175,30);
		this.add(getEmployee);
		
		getEmployeeButton = new JButton("Get Employee");
		getEmployeeButton.setBounds(500,350,175,30);
		getEmployeeButton.addActionListener(this);
		this.add(getEmployeeButton);
		
		remove = new JButton("Remove Employee");
		remove.setBounds(500,400,175,30);
		remove.addActionListener(this);
		this.add(remove);
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(800,800);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int x = 10;
		int y = 550;
		for(int i = 0; i < displayedPeople.size(); i++){
			displayedPeople.get(i).drawPhoto(g, x, y);
			x = x + 100;
		}
		
	}
	
	
	public void actionPerformed(ActionEvent e){
		if( e.getSource() == displayTeachers ){
			displayedPeople.clear();
			for(int i = 0; i < people.size(); i++){
				if(people.get(i) instanceof Teacher){
					displayedPeople.add(people.get(i));
				}
			}
		}
		if( e.getSource() == displayPolice ){
			displayedPeople.clear();
			for(int i = 0; i < people.size(); i++){
				if(people.get(i) instanceof PoliceOfficer){
					displayedPeople.add(people.get(i));
				}
			}
		}
		if( e.getSource() == displayEngineers ){
			displayedPeople.clear();
			for(int i = 0; i < people.size(); i++){
				if(people.get(i) instanceof PrivateEngineer){
					displayedPeople.add(people.get(i));
				}
			}
		}
		if( e.getSource() == displayBankers ){
			displayedPeople.clear();
			for(int i = 0; i < people.size(); i++){
				if(people.get(i) instanceof PrivateBanker){
					displayedPeople.add(people.get(i));
				}
			}
		}
		if( e.getSource() == displayAll ){
			displayedPeople.clear();
			for(int i = 0; i < people.size(); i++){
				displayedPeople.add(people.get(i));
			}
		}
		if( e.getSource() == getEmployeeButton ){
			displayedPeople.clear();
			String name = getEmployee.getText();
			getEmployee.setText("");
			for(int i = 0; i < people.size(); i++){
				if(people.get(i).getName().equals(name)){
					displayedPeople.add(people.get(i));
				}
			}
		}
		if( e.getSource() == remove ){
			displayedPeople.clear();
			int index = peopleList.getSelectedIndex();
			people.remove(index);
			for(int i = 0; i < people.size(); i++){
				displayedPeople.add(people.get(i));
			}
		}
		
		
		peopleList.setListData(displayedPeople.toArray());
		repaint();
	}
}