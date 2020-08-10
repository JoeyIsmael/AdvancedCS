import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeMap;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.*;  
import java.awt.event.*;
import java.awt.Font;
import java.util.*;

public class Screen extends JPanel implements ActionListener{
	private final int fontHeight;
	private final int screenDPI;
	private final int width;
	private final int height;

	private String text="";

	Map<Profile, Classes> treeMap = new TreeMap<Profile, Classes>();
	Map<Profile, Classes> hashMap = new HashMap<Profile, Classes>();
	Profile currentProfile = null;

	private JTextArea outputArea;
	private JScrollPane scrollPane;
	private TextArea schedule = new TextArea();

	private JButton getButton;
	private JTextField lastButton;
	private JTextField firstButton;
	private JTextField yearButton;

	private JButton removeButton;
	private JTextField last2Button;
	private JTextField first2Button;
	private JTextField year2Button;

	private JTextField last3Button;
	private JTextField first3Button;
	private JTextField year3Button;
	private JTextField schoolButton;
	private JButton changeSchoolButton;
	private JButton addStudent;
	private JButton addClass;
	private JTextField classInput;
	private JButton removeClass;
	private JTextField class2Input;


	String[] schools = {"Stanford", "MIT", "Princeton", "UCLA", "Berkeley", "USC", "Harvard", "Cornell", "Columbia", "UC Santa Cruz"};
	String[] classes1 = {"Calculus", "Algebra", "Trigonometry", "Geometry"};
	String[] classes2 = {"Biology", "Chemistry", "Physics", "Environmental Science"};
	String[] classes3 = {"English", "US History", "Art", "Spanish"};

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

		outputArea=new JTextArea(300,900);
		outputArea.setText(text);
		outputArea.setEditable(false);
		scrollPane = new JScrollPane(outputArea); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50,50,300,600);
		this.add(scrollPane);

		schedule.setBounds(950,50,450,600);  
		add(schedule);
		schedule.setVisible(true);

		getButton = new JButton("Get Student");
        getButton.setBounds(570,170, 150, 30);
        getButton.addActionListener(this);
        this.add(getButton);

        lastButton = new JTextField(20);
        lastButton.setBounds(500,130,100,30);
        this.add(lastButton);

        firstButton = new JTextField(20);
        firstButton.setBounds(600,130,100,30);
        this.add(firstButton);
		
		yearButton = new JTextField(20);
        yearButton.setBounds(700,130,100,30);
        this.add(yearButton);

        removeButton = new JButton("Remove Student");
        removeButton.setBounds(570,340, 150, 30);
        removeButton.addActionListener(this);
        this.add(removeButton);

        last2Button = new JTextField(20);
        last2Button.setBounds(500,300,100,30);
        this.add(last2Button);

        first2Button = new JTextField(20);
        first2Button.setBounds(600,300,100,30);
        this.add(first2Button);
		
		year2Button = new JTextField(20);
        year2Button.setBounds(700,300,100,30);
        this.add(year2Button);

        last3Button = new JTextField(20);
        last3Button.setBounds(450,450,100,30);
        this.add(last3Button);

        first3Button = new JTextField(20);
        first3Button.setBounds(550,450,100,30);
        this.add(first3Button);
		
		year3Button = new JTextField(20);
        year3Button.setBounds(650,450,100,30);
        this.add(year3Button);

        schoolButton = new JTextField(20);
        schoolButton.setBounds(750,450,100,30);
        this.add(schoolButton);

        changeSchoolButton = new JButton("Change School");
        changeSchoolButton.setBounds(500,500, 120, 30);
        changeSchoolButton.addActionListener(this);
        this.add(changeSchoolButton);

        addStudent = new JButton("Add Student");
        addStudent.setBounds(690,500, 120, 30);
        addStudent.addActionListener(this);
        this.add(addStudent);

        addClass = new JButton("Add Class");
        addClass.setBounds(430,650, 120, 30);
        addClass.addActionListener(this);
        this.add(addClass);
		
		classInput = new JTextField(20);
        classInput.setBounds(430,610,120,30);
        this.add(classInput);

        removeClass = new JButton("Remove Class");
        removeClass.setBounds(590,650, 120, 30);
        removeClass.addActionListener(this);
        this.add(removeClass);
		
		class2Input = new JTextField(20);
        class2Input.setBounds(590,610,120,30);
        this.add(class2Input);

		fontHeight = (int) (0.1 * screenDPI);
		setFont(new Font("Verdana", Font.BOLD, fontHeight));
		scanFile();
	}

	public void editSchedule(Profile p){
		schedule.setText("");
		schedule.append("School:" + "\n");
		schedule.append(hashMap.get(p).getSchool() + "\n");
		schedule.append("" + "\n");
		schedule.append("Classes: " + "\n");
		schedule.append(hashMap.get(p).toString() + "\n");
	}

	public Dimension getPreferredSize(){
		return new Dimension(800,800);
	}

	public void scanFile(){
		try {
			Scanner scan = new Scanner(new File("names.txt"));
			while(scan.hasNextLine()){
				boolean done = false;	
				String input = scan.nextLine();
				String first = input.substring(0,input.indexOf(" "));
				String last = input.substring(input.indexOf(" ")+1, input.length());
				int birth = (int)(Math.random() * 6 + 2001);
				int num = (int)(Math.random() * 10);
				int num2 = (int)(Math.random() * 4);
				int num3 = (int)(Math.random() * 4);
				int num4 = (int)(Math.random() * 4);
				ArrayList<String> classes = new ArrayList<String>();
				classes.add(classes1[num2]);
				classes.add(classes2[num3]);
				classes.add(classes3[num4]);
				treeMap.put(new Profile(first, last, birth), new Classes(schools[num], classes));
				hashMap.put(new Profile(first, last, birth), new Classes(schools[num], classes));
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		text="";

		Iterator<Profile> it = treeMap.keySet().iterator();
		while(it.hasNext()){
			Profile profile = it.next();
			text = text + profile.toString() + "\n";
		}
		outputArea.setText(text);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		outputArea.setText(text);
		g.drawString("Student List:", 25, 35);
		g.drawString("Last :",500,120);
		g.drawString("First :",600,120);		
		g.drawString("Birth Year:",700,120);
		g.drawString("Admin Section:", 380, 250);
		g.drawString("Last :",500,290);
		g.drawString("First :",600,290);		
		g.drawString("Birth Year:",700,290);
		g.drawString("Last :",420,440);
		g.drawString("First :",520,440);		
		g.drawString("Birth Year:",610,440);
		g.drawString("School:",760,440);
		g.drawString("Class :",420,600);
		g.drawString("Class :",600,600);


	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==getButton){
			String lastText = lastButton.getText();
			String firstText = firstButton.getText();
			String num = yearButton.getText();
			int year = Integer.parseInt(num);
			Profile p = new Profile(firstText, lastText, year);
			if(hashMap.containsKey(p)){
				editSchedule(p);
				currentProfile = p;
				System.out.println("good");
			} else {
				System.out.println("bad");
			}
		}
		if(e.getSource() == removeButton){
			String lastText = last2Button.getText();
			String firstText = first2Button.getText();
			String num = year2Button.getText();
			int year = Integer.parseInt(num);
			Profile p = new Profile(firstText, lastText, year);
			if(hashMap.containsKey(p)){
				hashMap.remove(p);
				treeMap.remove(p);
				System.out.println("good2");
			}

			text="";
			for(Profile each : treeMap.keySet()){
				text +=each.toString()+"\n";
			}

			if(p.getLastName().compareTo(currentProfile.getLastName()) == 0 && p.getFirstName().compareTo(currentProfile.getFirstName()) == 0 && p.getYear() == currentProfile.getYear()){
				System.out.println("Same");
				schedule.setText("");
			}
		}
		if(e.getSource() == changeSchoolButton){
			String lastText = last3Button.getText();
			String firstText = first3Button.getText();
			String num = year3Button.getText();
			int year = Integer.parseInt(num);
			String school = schoolButton.getText();
			Profile p = new Profile(firstText, lastText, year);
			Classes c = new Classes(school, hashMap.get(p).getSchedule());
			if(hashMap.containsKey(p)){
				hashMap.replace(p, c);
				treeMap.replace(p, c);
			}
			text="";
			for(Profile each : treeMap.keySet()){
				text +=each.toString()+"\n";
			}
			editSchedule(p);
		}
		if(e.getSource() == addStudent){
			outputArea.setText("");
			String lastText = last3Button.getText();
			String firstText = first3Button.getText();
			String num = year3Button.getText();
			int year = Integer.parseInt(num);
			String school = schoolButton.getText();
			int num2 = (int)(Math.random() * 4);
			int num3 = (int)(Math.random() * 4);
			int num4 = (int)(Math.random() * 4);
			ArrayList<String> classes = new ArrayList<String>();
			classes.add(classes1[num2]);
			classes.add(classes2[num3]);
			classes.add(classes3[num4]);
			treeMap.put(new Profile(firstText, lastText, year), new Classes(school, classes));
			hashMap.put(new Profile(firstText, lastText, year), new Classes(school, classes));
			text="";
			for(Profile each : treeMap.keySet()){
				text +=each.toString()+"\n";
			}
		}
		if(e.getSource() == addClass){
			String newClass = classInput.getText();
			Classes current = hashMap.get(currentProfile);
			ArrayList<String> schedule = current.getSchedule();
			schedule.add(newClass);
			current.change(schedule);
			editSchedule(currentProfile);
		}
		if(e.getSource() == removeClass){
			String newClass = class2Input.getText();
			Classes current = hashMap.get(currentProfile);
			ArrayList<String> schedule = current.getSchedule();
			schedule.remove(newClass);
			editSchedule(currentProfile);
		}
		repaint();
	}
}