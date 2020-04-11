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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.Scanner;

public class Screen extends JPanel implements ActionListener{

	private final int fontHeight;
	private final int screenDPI;
	private final int width;
	private final int height;

	private String text="";
	ArrayList<Pair<Item,Integer>> shoppingCart = new ArrayList<Pair<Item,Integer>>();
	Set<Item> treeItemSet = new TreeSet<Item>();
	Set<Item> hashItemSet = new HashSet<Item>();
	private JScrollPane scrollPane;
	private JTextArea outputArea;
	private TextArea shoppingCartArea = new TextArea();
	private JTextField productName;
	private JTextField productPrice;
	private JTextField productQuantity;
	private JButton AddButton;
	private double totalPrice=0;
	private boolean display=false;
	private JTextField removeInput;
	private JButton removeButton;
	private JTextField newName;
	private JTextField newPrice;
	private JTextField newQuantity;
	private JButton newItem;

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

		shoppingCartArea.setBounds(950,50,300,600);  
		add(shoppingCartArea);
		shoppingCartArea.setVisible(true);

		outputArea=new JTextArea(300,900);
		outputArea.setText(text);
		outputArea.setEditable(false);
		scrollPane = new JScrollPane(outputArea); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50,50,300,600);
		this.add(scrollPane);
		
		productName = new JTextField(20);
        productName.setBounds(500,230,100,30);
        this.add(productName);
		
		productPrice = new JTextField(20);
        productPrice.setBounds(600,230,100,30);
        this.add(productPrice);
		
		productQuantity = new JTextField(20);
        productQuantity.setBounds(700,230,100,30);
        this.add(productQuantity);
		
		AddButton = new JButton("Add");
        AddButton.setBounds(600,270, 100, 30);
        AddButton.addActionListener(this);
        this.add(AddButton);
		
		newName = new JTextField(20);
        newName.setBounds(50,720,150,30);
        this.add(newName);
		
		newPrice = new JTextField(20);
        newPrice.setBounds(250,720,150,30);
        this.add(newPrice);
		
		removeButton = new JButton("Remove Item");
        removeButton.setBounds(250,770, 150, 30);
        removeButton.addActionListener(this);
        this.add(removeButton);
		
		newItem = new JButton("New Item");
        newItem.setBounds(50,770, 150, 30);
        newItem.addActionListener(this);
        this.add(newItem);
		
		fontHeight = (int) (0.1 * screenDPI);
		setFont(new Font("Verdana", Font.BOLD, fontHeight));
		scanFile();
	}
	public Dimension getPreferredSize(){
		return new Dimension(800,800);
	}
	public void scanFile(){
		try {
			Scanner scan = new Scanner(new File("StoreA.txt"));
			while(scan.hasNextLine()){
				boolean done = false;	
				String input = scan.nextLine();
				String name = input.substring(0,input.indexOf(","));
				double price = Double.parseDouble(input.substring(input.indexOf(",") + 1, input.length()));
				treeItemSet.add(new Item(name,price));
				hashItemSet.add(new Item(name,price));
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		text="";
		
		for(Item each : treeItemSet){
			text +=each.toString()+"\n";
		}
		outputArea.setText(text);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		outputArea.setText(text);
		g.drawString("Shopping Items", 25, 35);
		g.drawString("Name:",500,220);
		g.drawString("Price:",600,220);
		g.drawString("Quantity:",700,220);
		g.drawString("Input Item Name:",400,170);
		g.drawString("Shopping Cart", 925, 35);
		g.drawString("Total Price: " + totalPrice, 950, 700);
		g.drawString("Name:",50,700);
		g.drawString("Price:",250,700);
	}
	public void editTextArea(){
		shoppingCartArea.setText("");
		totalPrice = 0;
		for(int i = 0; i < shoppingCart.size(); i++){
			shoppingCartArea.append(shoppingCart.get(i).getT().toString() + " : " + shoppingCart.get(i).getE() + "\n");
			totalPrice = (totalPrice + (shoppingCart.get(i).getT().getPrice() * shoppingCart.get(i).getE()));
		}
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==AddButton){
			String nameText = productName.getText();
			String priceText = productPrice.getText();
			double price = Double.parseDouble(priceText);
			String quantityText = productQuantity.getText();
			int quantity = Integer.parseInt(quantityText);
			Item tempItem = new Item(nameText,price);
			Pair<Item,Integer> myPair = new Pair<Item,Integer>(tempItem,quantity);
			System.out.println(tempItem.toString());
			boolean yes=false;
			Item item = new Item(nameText, price);
			if(hashItemSet.contains(item)){
				System.out.println("true");
					shoppingCart.add(myPair);
					yes=true;
			}
			/*for(Item each : hashItemSet){
				if((each.getName().compareTo(nameText)==0)&&(each.getPrice()==price)){
					System.out.println("true");
					shoppingCart.add(myPair);
					yes=true;
					break;
				}
			}*/
			editTextArea();
			System.out.println(yes);
			if(yes==false){
				System.out.println("false");
			}
		}
		if(e.getSource()==removeButton){
			String nameText = newName.getText();
			String priceText = newPrice.getText();
			double price = Double.parseDouble(priceText);
			for(Item each : treeItemSet){
				if(each.getName().compareTo(nameText)==0 && (each.getPrice() == price)){
					treeItemSet.remove(each);
					hashItemSet.remove(each);
					break;
				}
			}
			text="";
			for(Item each : treeItemSet){
				text +=each.toString()+"\n";
			}
		}
		if(e.getSource()==newItem){
			String nameText = newName.getText();
			String priceText = newPrice.getText();
			double price = Double.parseDouble(priceText);
			treeItemSet.add(new Item(nameText,price));
			hashItemSet.add(new Item(nameText,price));
			text="";
		
			for(Item each : treeItemSet){
				text +=each.toString()+"\n";
			}
		}
		repaint();
	}
}