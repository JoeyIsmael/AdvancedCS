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

	TextArea list = new TextArea();  
	private final int fontHeight;
	private final int screenDPI;
	private final int width;
	private final int height;
	Database data = new Database();

	private JButton binarySearch;
	private JButton linearSearch;
	private JButton shuffle;
	private JButton bubbleSort;
	private JButton mergeSort;

	private JTextField binaryOutput;
	private JTextField mergeOutput;
	private JTextField binaryInput;
	private JTextField linearOutput;
	private JTextField bubbleOutput;

	boolean shuffled = false;
	boolean began = true;


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
		list.setBounds(50,50,700,700);  
		add(list);
		list.setVisible(true);

		binarySearch = new JButton("Binary Search");
		binarySearch.setBounds(900,100,400,38);
		binarySearch.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		binarySearch.addActionListener(this);
		this.add(binarySearch);
		this.setVisible(true);

		binaryOutput = new JTextField();
		binaryOutput.setBounds(900,190,600,38);
		binaryOutput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(binaryOutput);
		this.setVisible(true);

		binaryInput = new JTextField();
		binaryInput.setBounds(900,40,400,38);
		binaryInput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(binaryInput);
		this.setVisible(true);

		linearSearch = new JButton("Linear Search");
		linearSearch.setBounds(900,250,400,38);
		linearSearch.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		linearSearch.addActionListener(this);
		this.add(linearSearch);
		this.setVisible(true);

		linearOutput = new JTextField();
		linearOutput.setBounds(900,340,600,38);
		linearOutput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(linearOutput);
		this.setVisible(true);

		shuffle = new JButton("Shuffle");
		shuffle.setBounds(900, 700,400,38);
		shuffle.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		shuffle.addActionListener(this);
		this.add(shuffle);
		this.setVisible(true);

		bubbleSort = new JButton("Bubble Sort");
		bubbleSort.setBounds(900, 550,400,38);
		bubbleSort.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		bubbleSort.addActionListener(this);
		this.add(bubbleSort);
		this.setVisible(true);

		mergeSort = new JButton("Merge Sort");
		mergeSort.setBounds(900, 400,400,38);
		mergeSort.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		mergeSort.addActionListener(this);
		this.add(mergeSort);
		this.setVisible(true);

		bubbleOutput = new JTextField();
		bubbleOutput.setBounds(900,640,600,38);
		bubbleOutput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(bubbleOutput);
		this.setVisible(true);

		mergeOutput = new JTextField();
		mergeOutput.setBounds(900,490,600,38);
		mergeOutput.setFont(new Font("Verdana", Font.BOLD, fontHeight));
		this.add(mergeOutput);
		this.setVisible(true);
	}
	public Dimension getPreferredSize(){
		return new Dimension(800,800);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(began){
			createInitialList();
			began = false;
		}
		if(shuffled){
			shuffledList();
		} else if(!shuffled){
			createList();
		}
		g.drawString("Student List:", 20, 30);
		g.drawString("Result of Binary Search: ", 900, 170);
		g.drawString("Enter in a student last name to search:", 900, 25);
		g.drawString("Result of Linear Search", 900, 210);
		g.drawString("Result of Linear Search: ", 900, 320);
		g.drawString("Result of Bubble Sort: ", 900, 620);
		g.drawString("Result of Merge Sort: ", 900, 470);


	}
	public void createInitialList(){
		list.setText("");
		data.ReadFile();
		list.append(data.getArray());
	}
	public void createList(){
		list.setText("");
		list.append(data.getArray());
	}
	public void shuffledList(){
		list.setText("");
		data.shuffleArray();
		list.append(data.getArray());
	}
	public void binarySearch(ArrayList<Student> array, String value, int startpos, int endpos, int passes){
		while(startpos <= endpos){
			int midpos = (startpos+endpos)/2;
			int res = value.compareTo(array.get(midpos).getLast().substring(1)); 
			if(res == 0){
				binaryOutput.setText(array.get(midpos).toString(midpos+1) + " Passes: " + passes);
				return;
			} else if (res > 0) {
				passes++;
				startpos = midpos+1;
			} else {
				passes++;
				endpos = midpos-1;
			}
		}
		binaryOutput.setText("Not in the List");
	}
	public void linearSearch(ArrayList<Student> array, String value, int passes){
		for(int i = 0; i < array.size(); i++){
			passes++;
			if(array.get(i).getLast().substring(1).equals(value)){
				linearOutput.setText(array.get(i).toString(i+1) + " Passes: " + passes);
				return;
			}
		}
		linearOutput.setText("Not in the List");
	}
	public void bubbleSort(){
		int passes = data.swapElements();
		bubbleOutput.setText("Passes: " + passes);
	}
	public void mergeSort(){
		data.callMerge();
		mergeOutput.setText("Passes: " + data.getPasses());
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == binarySearch){
			if(shuffled == true){
				binaryOutput.setText("List is not shuffled"); 
			} else {
				String student = binaryInput.getText();
				binarySearch(data.returnArray(), student, 0, data.returnArray().size()-1, 0);
			}
		}
		if(e.getSource() == linearSearch){
			if(shuffled == true){
				linearOutput.setText("List is not shuffled");
			} else {
				String student = binaryInput.getText();
				linearSearch(data.returnArray(), student, 0);
			}
		}
		if(e.getSource() == shuffle){
			shuffled = true;
			list.setText("");
			repaint();
		}
		if(e.getSource() == bubbleSort){
			bubbleSort();
			shuffled = false;
			repaint();
		}
		if(e.getSource() == mergeSort){
			mergeSort();
			shuffled = false;
			repaint();
		}
		
	}
}