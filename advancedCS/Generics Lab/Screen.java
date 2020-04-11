import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Screen extends JPanel implements ActionListener{
	
	private JScrollPane studentsPane;
	private JScrollPane studentsAndSchedulePane;
	
	Boolean isTrue = true;
	int count;
	String empty = "";
	int currentStudentIndex;
	private ArrayList<Pair<Student, Schedule>> foundStudentDetails = new ArrayList<Pair<Student, Schedule>>(); //new Pair<Student, Schedule>();

	//private Pair<Student, Schedule> foundStudentDetails = new Pair<>(null, null);
	
	private ArrayList<Student> studentList = new ArrayList<>();
	
	//private Schedule[] scheduleArrayHolder = new Schedule[1];
	
	private JList studentJList;
	private JList<String> studentScheduleJList;
	
	private JButton getStudentButton;
	private JButton displayAll;
	private JButton addClass;
	private JButton removePeriod;
	private JTextField getStudent;
	private JTextField periodInput;
	private JTextField classInput;
	private JTextField removePeriodInput;
	
	private final int fontHeight;
	private final int screenDPI;
	private final int width;
	private final int height;
	
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
		
		Student student1 = new Student("John", "John.png");
		Student student2 = new Student("Jenna", "Jenna.jpg");
		Student student3 = new Student("Jack", "Jack.jpg");
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		
		Schedule student1Schedule = new Schedule();
		student1Schedule.addClass(1, "Calculus");
		student1Schedule.addClass(5, "Biology");
		student1Schedule.addClass(7, "Spanish");
		
		Schedule student2Schedule = new Schedule();
		student2Schedule.addClass(1, "English");
		student2Schedule.addClass(3, "Art");
		student2Schedule.addClass(4, "Music");
		
		Schedule student3Schedule = new Schedule();
		student3Schedule.addClass(1, "Trigonometry");
		student3Schedule.addClass(2, "French");
		student3Schedule.addClass(3, "History");
		
		
		Pair<Student, Schedule> s1 = new Pair<Student, Schedule>(student1, student1Schedule);
		Pair<Student, Schedule> s2 = new Pair<Student, Schedule>(student2, student2Schedule);
		Pair<Student, Schedule> s3 = new Pair<Student, Schedule>(student3, student3Schedule);
		
		foundStudentDetails.add(s1);
		foundStudentDetails.add(s2);
		foundStudentDetails.add(s3);

		setFont(new Font("Verdana", Font.BOLD, fontHeight));
		
		
		studentJList = new JList(studentList.toArray());
		studentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentJList.setLayoutOrientation(JList.VERTICAL);
		studentJList.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		
		studentsPane = new JScrollPane(studentJList);
		studentsPane.setPreferredSize(new Dimension(300, 300));
		studentsPane.setBounds(25, 25, 300, 300);
		add(studentsPane);
		
		studentScheduleJList = new JList<>();
		studentScheduleJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentScheduleJList.setLayoutOrientation(JList.VERTICAL);
		studentScheduleJList.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		studentScheduleJList.setFixedCellHeight((int)(fontHeight * 1.5));
		studentScheduleJList.setFixedCellWidth(280);
		
		studentsAndSchedulePane = new JScrollPane(studentScheduleJList);
		studentsAndSchedulePane.setPreferredSize(new Dimension(300, 300));
		studentsAndSchedulePane.setBounds(350, 20, 300, 300);
		add(studentsAndSchedulePane);
		
		getStudent = new JTextField();
		getStudent.setBounds(450,400,400,38);
		getStudent.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(getStudent);
		
		getStudentButton = new JButton("See Student Schedule");
		getStudentButton.setBounds(450,450,400,38);
		getStudentButton.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		getStudentButton.addActionListener(this);
		this.add(getStudentButton);
		
		displayAll = new JButton("Display All");
		displayAll.setBounds(50,450,300,38);
		displayAll.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		displayAll.addActionListener(this);
		this.add(displayAll);
		
		addClass = new JButton("Add Class");
		addClass.setBounds(950,450,400,38);
		addClass.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		addClass.addActionListener(this);
		this.add(addClass);
		
		removePeriod = new JButton("Remove Class");
		removePeriod.setBounds(950,200,400,38);
		removePeriod.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		removePeriod.addActionListener(this);
		this.add(removePeriod);
		
		periodInput = new JTextField();
		periodInput.setBounds(950,300,400,38);
		periodInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(periodInput);
		
		classInput = new JTextField();
		classInput.setBounds(950,400,400,38);
		classInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(classInput);
		
		removePeriodInput = new JTextField();
		removePeriodInput.setBounds(950,150,400,38);
		removePeriodInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(removePeriodInput);
		
		
	}
	public Dimension getPreferredSize(){
		return new Dimension(1200,800);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawString("Enter period:",950,280);
		g.drawString("Enter class:",950,380);
		g.drawString("Enter period to delete:", 950, 130);
	
		
		int x = 10;
		int y = 550;
		if(isTrue){
			for(int i = 0; i < studentList.size(); i++){
				studentList.get(i).drawPhoto(g, x, y);
				x = x + 300;
			}
		} else {
			studentList.get(count).drawPhoto(g,x,y);
		}
	}
	
	

	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == getStudentButton){
			System.out.println("screen DPI = " + screenDPI);
			System.out.println("font height Dots = " + fontHeight);
			studentScheduleJList.setListData(new String[0]);
			String name = getStudent.getText();
			System.out.println("entered name: " + name);
			System.out.println(foundStudentDetails.get(0).getT());
			
			for(int i = 0; i < foundStudentDetails.size(); i++){
				if(foundStudentDetails.get(i).getT().getName().equals(name)){
					isTrue = false;
					count = i;
					currentStudentIndex = i;
					System.out.println("Hello");
					Schedule currentSchedule = foundStudentDetails.get(i).getE();
					studentScheduleJList.setListData(currentSchedule.toStringArray());
				}
			}
			
			/*for (Student aStudent: studentList) {
				if( aStudent.getName().equals(name) ) { 
					isTrue = true;
					System.out.println("Hello");
					
					
					Schedule currentSchedule = aStudent.getE();
					
					studentScheduleJList.setListData(currentSchedule.toStringArray());
					//studentScheduleJList.setListData(student1Schedule.toStringArray());
					break;
					//foundStudentDetails.add(foundStudentDetails.get(i));
				} else {
					System.out.println("oops....");
				}
			}*/
		}
		if( e.getSource() == displayAll ){
			isTrue = true;
			Schedule currentSchedule = new Schedule();
			studentScheduleJList.setListData(currentSchedule.toStringArray());
		}
		if( e.getSource() == addClass ){
			String newClass = classInput.getText();
			String periodEntered = periodInput.getText();
			int period = Integer.parseInt(periodEntered);
			foundStudentDetails.get(currentStudentIndex).getE().addClass(period, newClass);
			studentScheduleJList.setListData(foundStudentDetails.get(currentStudentIndex).getE().toStringArray());
			
		}
		if( e.getSource() == removePeriod ){
			String enteredPeriodDelete = removePeriodInput.getText();
			int periodDelete = Integer.parseInt(enteredPeriodDelete);
			foundStudentDetails.get(currentStudentIndex).getE().removeClass(periodDelete);
			studentScheduleJList.setListData(foundStudentDetails.get(currentStudentIndex).getE().toStringArray());
		}
		repaint();
	}
}