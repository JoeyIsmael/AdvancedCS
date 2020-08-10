import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.*;  
import java.awt.event.*;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Screen extends JPanel implements ActionListener{

	private final int fontHeight;
	private final int screenDPI;
	private final int width;
	private final int height;
	private String name;
	private String address;
	private String email;
	private String objectives;
	private String skills;
	private int section = 1;

	private JButton nameButton;
	private JButton addressButton;
	private JButton skillsButton;
	private JButton objectivesButton;
	private JButton emailButton;
	private JButton moveOn1;
	private JButton addSchool;
	private JButton moveOn2;
	private JButton moveOn3;
	private JButton addJob;

	private JTextField nameInput;
	private JTextField addressInput;
	private JTextField skillsInput;
	private JTextField objectivesInput;
	private JTextField emailInput;
	private JTextField schoolInput;
	private JTextField schoolYearInput;
	private JTextField jobTitleInput;
	private JTextField jobYearInput;
	private JTextField companyInput;

	TextArea resume = new TextArea();  

	ArrayList<Education> educationList = new ArrayList<Education>();
	ArrayList<Job> jobList = new ArrayList<Job>();

	ListIterator<Education> it1; 
	ListIterator<Job> it2;  

	private int toScreenDots(double dimInches) {
		double temp = dimInches * screenDPI;
		System.out.println("dimInches = " + screenDPI);
		return (int) (dimInches * screenDPI);
	}

	public Screen(int screenDPI, int width, int height){
		this.setLayout(null);
		this.setFocusable(true);
		this.screenDPI = screenDPI;
		this.width = width;
		this.height = height;
		
		fontHeight = (int) (0.1 * screenDPI);
		setFont(new Font("Verdana", Font.BOLD, fontHeight));

		nameButton = new JButton("Submit Name");
		nameButton.setBounds(10,150,400,38);
		nameButton.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		nameButton.addActionListener(this);
		this.add(nameButton);
		this.setVisible(true);

		nameInput = new JTextField();
		nameInput.setBounds(10,100,400,38);
		nameInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(nameInput);
		this.setVisible(true);

		addressButton = new JButton("Submit Address");
		addressButton.setBounds(10,290,400,38);
		addressButton.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		addressButton.addActionListener(this);
		this.add(addressButton);
		this.setVisible(true);

		addressInput = new JTextField();
		addressInput.setBounds(10,240,400,38);
		addressInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(addressInput);
		this.setVisible(true);

		objectivesButton = new JButton("Submit Objectives");
		objectivesButton.setBounds(10,430,400,38);
		objectivesButton.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		objectivesButton.addActionListener(this);
		this.add(objectivesButton);
		this.setVisible(true);

		objectivesInput = new JTextField();
		objectivesInput.setBounds(10,380,400,38);
		objectivesInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(objectivesInput);
		this.setVisible(true);

		skillsButton = new JButton("Submit Skills");
		skillsButton.setBounds(10,570,400,38);
		skillsButton.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		skillsButton.addActionListener(this);
		this.add(skillsButton);
		this.setVisible(true);

		skillsInput = new JTextField();
		skillsInput.setBounds(10,520,400,38);
		skillsInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(skillsInput);
		this.setVisible(true);

		emailButton = new JButton("Submit Email");
		emailButton.setBounds(10,710,400,38);
		emailButton.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		emailButton.addActionListener(this);
		this.add(emailButton);
		this.setVisible(true);

		emailInput = new JTextField();
		emailInput.setBounds(10,660,400,38);
		emailInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(emailInput);
		this.setVisible(true);

		moveOn1 = new JButton("Done");
		moveOn1.setBounds(800,380,400,38);
		moveOn1.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		moveOn1.addActionListener(this);
		this.add(moveOn1);
		this.setVisible(true);

		addSchool = new JButton("Add School");
		addSchool.setBounds(10,220,400,38);
		addSchool.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		addSchool.addActionListener(this);
		add(addSchool);
		addSchool.setVisible(false);

		schoolInput = new JTextField();
		schoolInput.setBounds(10,100,400,38);
		schoolInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		add(schoolInput);
		schoolInput.setVisible(false);

		schoolYearInput = new JTextField();
		schoolYearInput.setBounds(10,170,400,38);
		schoolYearInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		add(schoolYearInput);
		schoolYearInput.setVisible(false);

		moveOn2 = new JButton("Done");
		moveOn2.setBounds(600,150,400,38);
		moveOn2.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		moveOn2.addActionListener(this);
		add(moveOn2);
		moveOn2.setVisible(false);

		moveOn3 = new JButton("Done");
		moveOn3.setBounds(600,150,400,38);
		moveOn3.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		moveOn3.addActionListener(this);
		add(moveOn3);
		moveOn3.setVisible(false);

		jobTitleInput = new JTextField();
		jobTitleInput.setBounds(10,100,400,38);
		jobTitleInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		add(jobTitleInput);
		jobTitleInput.setVisible(false);

		companyInput = new JTextField();
		companyInput.setBounds(10,170,400,38);
		companyInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		add(companyInput);
		companyInput.setVisible(false);

		jobYearInput = new JTextField();
		jobYearInput.setBounds(10,240,400,38);
		jobYearInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		add(jobYearInput);
		jobYearInput.setVisible(false);

		addJob = new JButton("Add Job");
		addJob.setBounds(10,280,400,38);
		addJob.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		addJob.addActionListener(this);
		add(addJob);
		addJob.setVisible(false);



		resume.setBounds(50,50,700,500);  
		add(resume);
		resume.setVisible(false);
	}
		
	public Dimension getPreferredSize(){
		return new Dimension(800,800);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(section == 1){
			g.drawString("Enter in the following information about yourself:", 10, 30);
			g.drawString("Enter in your name:", 10, 90);
			g.drawString("Enter in your address:", 10, 230);
			g.drawString("List your objectives using commas:", 10, 370);
			g.drawString("List your skills using commas:", 10, 510);
			g.drawString("Enter in your email:", 10, 650);
		}
		if(section == 2){
			g.drawString("Enter in an education to add your resume:", 10, 30);
			g.drawString("Enter in the name of the school:", 10, 90);
			g.drawString("Enter in your graduation date YYYYMM:", 10, 160);
		}
		if(section == 3){
			g.drawString("Enter in a job to add your resume:", 10, 30);
			g.drawString("Enter in the job title for the job:", 10, 90);
			g.drawString("Enter in the company name for the job:", 10, 160);
			g.drawString("Enter in your start date YYYYMM:", 10, 230);
		}
		if(section == 4){
			g.drawString("Your Resume:", 10, 30);
		}

	}

	public void createTextArea(){
		String aboutUser = "Name: " + name + "\n" + "Address: " + address + "\n" + "Skills: " + skills + "\n" + "Objectives: " + objectives + "\n" + "Email: " + email;
		System.out.println(name);
		System.out.println(address);
		System.out.println(objectives);
		System.out.println(skills);
		System.out.println(email);

		resume.append(aboutUser);
		
		String space = "\n";

		resume.append(space);
		resume.append(space);
		resume.append("Education:" + "\n");

		String educationString = "";
		for(int i = 0; i < educationList.size(); i++){
			int year = educationList.get(i).getYear();
			String updatedYear = sliceYear(year);
			educationString = educationString + educationList.get(i).getString(updatedYear) + "\n";
		}
		resume.append(educationString);

		resume.append(space);
		resume.append(space);
		resume.append("Jobs:" + "\n");

		String jobString = "";
		for(int i = 0; i < jobList.size(); i++){
			int newJobYear = jobList.get(i).getYear();
			String updatedJobYear = sliceYear(newJobYear);
			jobString = jobString + jobList.get(i).getString(updatedJobYear) + "\n";
		}
		resume.append(jobString);
	}

	public String sliceYear(int year){
		String newYear =String.valueOf(year);
		String yearPart = newYear.substring(0, 4);
		String monthPart = newYear.substring(4);
		return yearPart + "-" + monthPart;
	}

	public void actionPerformed(ActionEvent e){
		repaint();
		if( e.getSource() == nameButton ){
			name = nameInput.getText();
			nameInput.setText("");
		}
		if( e.getSource() == addressButton ){
			address = addressInput.getText();
			addressInput.setText("");
		}
		if( e.getSource() == skillsButton ){
			skills = skillsInput.getText();
			skillsInput.setText("");
		}
		if( e.getSource() == objectivesButton ){
			objectives = objectivesInput.getText();
			objectivesInput.setText("");
		}
		if( e.getSource() == emailButton ){
			email = emailInput.getText();
			emailInput.setText("");
		}
		if( e.getSource() == moveOn1 ){
			//TODO Change to 2
			section = 2;
			nameButton.setVisible(false);
			nameInput.setVisible(false);
			addressButton.setVisible(false);
			addressInput.setVisible(false);
			skillsButton.setVisible(false);
			skillsInput.setVisible(false);
			objectivesButton.setVisible(false);
			objectivesInput.setVisible(false);
			emailButton.setVisible(false);
			emailInput.setVisible(false);
			moveOn1.setVisible(false);
			moveOn2.setVisible(true);
			schoolInput.setVisible(true);
			addSchool.setVisible(true);
			schoolYearInput.setVisible(true);

		}
		if( e.getSource() == addSchool){
			String schoolName = schoolInput.getText();
			String enteredSchoolYear = schoolYearInput.getText();
			int schoolYear = Integer.parseInt(enteredSchoolYear);
			it1 = educationList.listIterator(); 
			boolean added = false;
				while( it1.hasNext() ){
					if( it1.next().getYear() <= schoolYear ){
						it1.previous();
						it1.add(new Education(schoolName, schoolYear));
						added = true;
						break;
					}
				}
				if( added == false ){
					it1.add(new Education(schoolName, schoolYear));
				}
			schoolInput.setText("");
			schoolYearInput.setText("");
		}
		if( e.getSource() == addJob){
			String company = companyInput.getText();
			String jobTitle = jobTitleInput.getText();
			String jobYear = jobYearInput.getText();
			int startDate = Integer.parseInt(jobYear);
			it2 = jobList.listIterator(); 
			boolean added = false;
				while( it2.hasNext() ){
					if( it2.next().getYear() <= startDate ){
						it2.previous();
						it2.add(new Job(jobTitle, startDate, company));
						added = true;
						break;
					}
				}
				if( added == false ){
					it2.add(new Job(jobTitle, startDate, company));
				}
			jobTitleInput.setText("");
			companyInput.setText("");
			jobYearInput.setText("");
		}
		if(e.getSource() == moveOn2){
			moveOn2.setVisible(false);
			schoolInput.setVisible(false);
			addSchool.setVisible(false);
			schoolYearInput.setVisible(false);
			section = 3;
			addJob.setVisible(true);
			jobYearInput.setVisible(true);
			jobTitleInput.setVisible(true);
			moveOn3.setVisible(true);
			companyInput.setVisible(true);
		}
		if(e.getSource() == moveOn3){
			addJob.setVisible(false);
			jobYearInput.setVisible(false);
			jobTitleInput.setVisible(false);
			moveOn3.setVisible(false);
			companyInput.setVisible(false);
			section = 4;
			createTextArea();
			resume.setVisible(true);
		}
	}
}