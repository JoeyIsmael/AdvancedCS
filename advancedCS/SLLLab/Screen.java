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
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.*;

public class Screen extends JPanel implements ActionListener{

	SLList<Item> myList = new SLList<Item>();
	int count = 1;
	double totalprice = 0;
	String sortingMethod;

	private TextArea shoppingCartArea = new TextArea();
	private JTextField newName;
	private JTextField newPrice;
	private JButton addButton;
	private JButton removeButton;
	private JButton timeSortButton;
	private JButton nameSortButton;
	private JButton priceSortButton;
	
	public Screen(){
		this.setLayout(null);
		this.setFocusable(true);
		myList.add(new Item("Apple", 2.99, 1, count), count);
		count++;
		myList.add(new Item("Keyboard", 12.99, 1, count), count);
		count++;
		myList.add(new Item("Laptop", 1300.99, 1, count), count);
		count++;

		totalprice = myList.getPrice();

		sortingMethod = "Timestamp";

		shoppingCartArea.setBounds(20,75,200,200);  
		add(shoppingCartArea);
		shoppingCartArea.setVisible(true);

		newName = new JTextField(20);
        newName.setBounds(20,300,100,30);
        this.add(newName);

        newPrice = new JTextField(20);
        newPrice.setBounds(20,350,100,30);
        this.add(newPrice);

        addButton = new JButton("Add Item");
        addButton.setBounds(20,400, 100, 30);
        addButton.addActionListener(this);
        this.add(addButton);

        removeButton = new JButton("Remove Item");
        removeButton.setBounds(20,450, 100, 30);
        removeButton.addActionListener(this);
        this.add(removeButton);

        timeSortButton = new JButton("Time Sort");
        timeSortButton.setBounds(200,300, 100, 30);
        timeSortButton.addActionListener(this);
        this.add(timeSortButton);

        priceSortButton = new JButton("Price Sort");
        priceSortButton.setBounds(200,350, 100, 30);
        priceSortButton.addActionListener(this);
        this.add(priceSortButton);

        nameSortButton = new JButton("Name Sort");
        nameSortButton.setBounds(200,400, 100, 30);
        nameSortButton.addActionListener(this);
        this.add(nameSortButton);

        updateTextArea();

	}

	public Dimension getPreferredSize(){
		return new Dimension(800,800);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString("Shopping List: ", 20, 60);
		g.drawString("Current Price: " + totalprice, 20, 35);
		g.drawString("Current Method of Sorting: " + sortingMethod, 20, 20);
		g.drawString("Name: ", 20, 290);
		g.drawString("Price: ", 20, 345);

	}	

	public void updateTextArea(){
		shoppingCartArea.setText(myList.toString());
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==addButton){
			String nameText = newName.getText();
			String priceText = newPrice.getText();
			double price = Double.parseDouble(priceText);
			Item newItem = new Item(nameText, price, 1, count);
			totalprice = totalprice + newItem.getPrice();
			myList.add(newItem, count);
			count++;
			if(sortingMethod.equals("Price")){
				myList.priceSort();
				updateTextArea();
			} else if(sortingMethod.equals("Name")){
				myList.nameSort();
				updateTextArea();
			} else if(sortingMethod.equals("Timestamp")){
				myList.timeSort();
				updateTextArea();
			}

			updateTextArea();
			repaint();

		}
		if(e.getSource() == removeButton){
			String nameText = newName.getText();
			String priceText = newPrice.getText();
			double price = Double.parseDouble(priceText);
			Item newItem = new Item(nameText, price, 1, count);
			totalprice = totalprice - myList.getQuantityPrice(nameText, price);
			myList.remove(newItem);
			updateTextArea();

		}

		if(e.getSource() == priceSortButton){
			myList.priceSort();
			sortingMethod = "Price";
			updateTextArea();
		}

		if(e.getSource() == nameSortButton){
			myList.nameSort();
			sortingMethod = "Name";
			updateTextArea();
		}
		if(e.getSource() == timeSortButton){
			myList.timeSort();
			sortingMethod = "Timestamp";
			updateTextArea();
		}
		repaint();
	}
}

