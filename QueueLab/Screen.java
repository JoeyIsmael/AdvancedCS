import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ListIterator;
import java.util.*;
import java.awt.*;  
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JComboBox;

public class Screen extends JPanel implements ActionListener{

	boolean nurseView = true;

	private final int fontHeight;
	private final int screenDPI;
	private final int width;
	private final int height;
	int time = 1;


	private JButton addButton;
	private JTextField nameInput;
	private JTextField illnessInput;
	private JTextField nameInput2;
	private JTextField illnessInput2;
	private JTextField noteInput;

	private JButton b1;
	private JButton getPatient;
	private JButton changeIllness;
	private JButton changePriority;
	private JButton changeView;
	private JButton changeView2;
	private JButton discharge;
	private JComboBox<String> choiceList;
	private JComboBox<String> choiceList2;
	private JComboBox<String> choiceList3;
	Patient currentPatient;
	Patient currentPatient2;

	PriorityQueue<Patient> pq = new PriorityQueue<Patient>();
	Queue<Patient> q = new LinkedList<Patient>();

	TextArea patients = new TextArea();  

	private int toScreenDots(double dimInches) {
		double temp = dimInches * screenDPI;
		System.out.println("dimInches = " + screenDPI);
		return (int) (dimInches * screenDPI);
	}

	public Screen(int screenDPI, int width, int height){
		this.setLayout(null);
		
		this.screenDPI = screenDPI;
		this.width = width;
		this.height = height;
		//focuses on button
		
		patients.setBounds(50,50,500,500);  
		add(patients);
		patients.setVisible(true);

		addButton = new JButton("Add Patient");
		addButton.setBounds(720,120,100,30);
		addButton.addActionListener(this);
		this.add(addButton);
		addButton.setVisible(true);


		fontHeight = (int) (0.1 * screenDPI);
		setFont(new Font("Verdana", Font.BOLD, fontHeight));
		
		nameInput = new JTextField();
		nameInput.setBounds(590,70,150,30);
		nameInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(nameInput);
		nameInput.setVisible(true);

		illnessInput = new JTextField();
		illnessInput.setBounds(760,70,150,30);
		illnessInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(illnessInput);
		illnessInput.setVisible(true);

        String[] choices = { "low", "medium", "high" };
		choiceList = new JComboBox<String>(choices);
		choiceList.setBounds(590,110,95,30);
		add(choiceList);
		choiceList.setVisible(true);

		String[] choices2 = { "child", "adult"};
		choiceList2 = new JComboBox<String>(choices2);
		choiceList2.setBounds(590,150,95,30);
		add(choiceList2);
		choiceList2.setVisible(true);

		nameInput2 = new JTextField();
		nameInput2.setBounds(590,230,150,30);
		nameInput2.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(nameInput2);
		nameInput2.setVisible(true);

		getPatient = new JButton("Get Patient");
		getPatient.setBounds(760,230,100,30);
		getPatient.addActionListener(this);
		this.add(getPatient);
		getPatient.setVisible(true);

		illnessInput2 = new JTextField();
		illnessInput2.setBounds(590,330,150,30);
		illnessInput2.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(illnessInput2);
		illnessInput2.setVisible(true);

		changeIllness = new JButton("Change Illness");
		changeIllness.setBounds(760,330,120,30);
		changeIllness.addActionListener(this);
		this.add(changeIllness);
		changeIllness.setVisible(true);

		choiceList3 = new JComboBox<String>(choices);
		choiceList3.setBounds(590,400,150,30);
		add(choiceList3);
		choiceList3.setVisible(true);

		changePriority = new JButton("Change Priority");
		changePriority.setBounds(760,400,150,30);
		changePriority.addActionListener(this);
		this.add(changePriority);
		changePriority.setVisible(true);

		changeView = new JButton("Go to Doctor");
		changeView.setBounds(660,450,150,30);
		changeView.addActionListener(this);
		this.add(changeView);
		changeView.setVisible(true);

		changeView2 = new JButton("Go to Nurse");
		changeView2.setBounds(660,450,150,30);
		changeView2.addActionListener(this);
		this.add(changeView2);
		changeView2.setVisible(false);

		discharge = new JButton("Discharge");
		discharge.setBounds(760,90,150,30);
		discharge.addActionListener(this);
		this.add(discharge);
		discharge.setVisible(false);

		noteInput = new JTextField();
		noteInput.setBounds(590,90,150,30);
		noteInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(noteInput);
		noteInput.setVisible(false);

		createInitialList();
		setFocusable(true);
	
	}

	public void createInitialList(){
		Patient init1 = new Patient("John", "sick", 1, 2, "", time);
		time++;	
		Patient init2 = new Patient("Jennifer", "pregnant", 3, 1, "", time);
		time++;	
		Patient init3 = new Patient("Liam", "flu", 2, 2, "", time);
		time++;
		pq.add(init1);
		pq.add(init2);
		pq.add(init3);
	}
	public void createList(){
		patients.setText("");
		Iterator<Patient> it = pq.iterator();
		String patientString = "";
		while(it.hasNext()){
			patientString = patientString + it.next().toString() + "\n";
		}
		patients.append(patientString);

	}
	public void createDischargeList(){
		patients.setText("");
		Iterator<Patient> it = q.iterator();
		String patientString = "";
		while(it.hasNext()){
			patientString = patientString + it.next().dischargeString() + "\n";
		}
		patients.append(patientString);
	}

	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(800,600);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(nurseView){
			createList();
			g.drawString("Patient List:", 20,30);
			g.drawString("Nurse View", 670, 30);
			g.drawString("Name:", 590, 60);
			g.drawString("Illness:", 760, 60);
			g.drawString("Search Name:", 590, 220);
			g.drawString("Current Patient: " + currentPatient, 590, 290);
			g.drawString("New Illness: ",590, 320);
			g.drawString("New Priority: ",590, 390);
		} else {
			g.drawString("Discharged Patients List:", 20,30);
			g.drawString("Doctor View", 670, 30);
			if(currentPatient2 == null){
				g.drawString("No more patients", 590, 60);
			} else {
				g.drawString("Current Patient: " + currentPatient2, 590, 60);
			}
			
			g.drawString("Doctors Note:", 590, 80);
		}
	}	

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == addButton){
			String name = nameInput.getText();
			String illness = illnessInput.getText();
			String priority = (String)choiceList.getSelectedItem();
			String age = (String)choiceList2.getSelectedItem();

			int currentP;
			int currentA;
			if(priority.equals("high")){
				currentP = 3;
			} else if(priority.equals("medium")){
				currentP = 2;
			} else {
				currentP = 1;
			}
			if(age.equals("adult")){
				currentA = 1;
			} else{
				currentA = 2;
			}
			pq.add(new Patient(name, illness, currentP, currentA, "", time));
			time++;
			createList();
		} 
		if(e.getSource() == getPatient){
			String name = nameInput2.getText();
			Iterator<Patient> it = pq.iterator();
			while(it.hasNext()){
				Patient temp = it.next();
				if(name.equals(temp.getName())){
					currentPatient = temp;
					break;
				}
			}
		}
		if(e.getSource() == changeIllness){
			String newIllness = illnessInput2.getText();
			currentPatient.setIllnessDescription(newIllness);
		}
		if(e.getSource() == changePriority){
			String priority = (String)choiceList3.getSelectedItem();
			int currentP;
			if(priority.equals("high")){
				currentP = 3;
			} else if(priority.equals("medium")){
				currentP = 2;
			} else {
				currentP = 1;
			}
			currentPatient.setPriority(currentP);

			Iterator<Patient> it = pq.iterator();
			while(it.hasNext()){
				Patient temp = it.next();
				if(currentPatient.equals(temp)){
					Patient newPatient = new Patient(temp.getName(), temp.getIllness(), currentP, temp.ageGroupNum(), "", temp.getTimeStamp());
					pq.remove(temp);
					pq.add(newPatient);
					break;
				}
			}
		}
		if(e.getSource() == changeView){
			nurseView = false;
			createDischargeList();
			addButton.setVisible(false);
			nameInput.setVisible(false);
			choiceList.setVisible(false);
			illnessInput.setVisible(false);
			choiceList2.setVisible(false);
			nameInput2.setVisible(false);
			getPatient.setVisible(false);
			choiceList3.setVisible(false);
			illnessInput2.setVisible(false);
			choiceList3.setVisible(false);
			changeIllness.setVisible(false);
			changePriority.setVisible(false);
			changeView.setVisible(false);
			changeView2.setVisible(true);
			if(currentPatient2 == null){
				currentPatient2 = pq.poll();
			}
			discharge.setVisible(true);
			noteInput.setVisible(true);

		}
		if(e.getSource() == discharge){
			String note = noteInput.getText();
			currentPatient2.setNote(note);
			q.add(currentPatient2);
			createDischargeList();
			currentPatient2 = pq.poll();
		}
		if(e.getSource() == changeView2){
			nurseView = true;
			createList();
			addButton.setVisible(true);
			nameInput.setVisible(true);
			choiceList.setVisible(true);
			illnessInput.setVisible(true);
			choiceList2.setVisible(true);
			nameInput2.setVisible(true);
			getPatient.setVisible(true);
			choiceList3.setVisible(true);
			illnessInput2.setVisible(true);
			choiceList3.setVisible(true);
			changeIllness.setVisible(true);
			changePriority.setVisible(true);
			changeView.setVisible(true);
			changeView2.setVisible(false);
			discharge.setVisible(false);
			noteInput.setVisible(false);
		}	
		repaint();
	}
}