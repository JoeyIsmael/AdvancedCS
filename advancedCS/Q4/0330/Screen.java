import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class Screen extends JPanel{
    private BinarySearchTree<Integer> tree;
    public Screen(){
		this.setLayout(null);
        this.setFocusable(true);
        tree = new BinarySearchTree<>();
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(1);
        tree.add(9);
        tree.add(11);
        tree.add(19);
    }

    public Dimension getPreferredSize(){
		return new Dimension(800,800);
	}
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        tree.drawTree(g, new Point(400, 25));
    }
}