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



public class Runner{
	public static void main(String[] args){
		JFrame fr = new JFrame("Generics Lab");
		fr.pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		fr.setSize(width/2, height/2);
		int screenDPI = Toolkit.getDefaultToolkit().getScreenResolution();

		fr.setLocationRelativeTo(null);
		
		
		Screen sc = new Screen(screenDPI, width/2, height/2);
		
		fr.add( sc );
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fr.setVisible(true);
	}
}
