import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.*;
import java.awt.event.KeyEvent;
import java.net.*;
import java.util.*;

public class ServerScreen extends JPanel implements ActionListener, KeyListener{
	
	Map<Location, Item> hashMap = new HashMap<Location, Item>();
	ArrayList<String> files = new ArrayList<String>();
	Location currentLoction = null;
	int player1Total = 0;
	int health = 3;

	public ServerScreen(){
         
        this.setLayout(null);
        createObjects();
        this.setFocusable(true);
        addKeyListener(this);
    }
    public Dimension getPreferredSize() {
 
        return new Dimension(800,600);
    }

    public void createObjects(){
    	int count = 0;
    	int pCount = 0;
    	int fCount = 0;
    	while(pCount < 1){
    		Location l = new Location(0, 0);
    		hashMap.put(l, new Item("player1.png", "Player1"));
    		currentLoction = l;
    		pCount++;
    	}
    	while(fCount<=9){
    		Location l = new Location((int)(Math.random() * 8), (int)(Math.random() * 8));
    		if(hashMap.get(l) == null){
    			hashMap.put(l, new Item("fire.png", "Fire"));
    			fCount++;
    		}
    	}
    	while(count<=10){
    		Location l = new Location((int)(Math.random() * 8), (int)(Math.random() * 8));
    		if(hashMap.get(l) == null){
    			hashMap.put(l, new Item("diamond.png", "Coin"));
    			count++;
    		}
    	}
    }

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("Player 1 Diamonds: " + player1Total, 20, 20);

        int x1 = 30;
        int y1 = 100;
        for(int i = 0; i < 9; i++){
        	g.drawLine(x1, y1, x1+400, y1);
        	y1 = y1 + 50;
        }
        y1 = 100;
         for(int i = 0; i < 9; i++){
        	g.drawLine(x1, y1, x1, y1+400);
        	x1 = x1 + 50;
        }
        
        Iterator<Location> it = hashMap.keySet().iterator();
        while(it.hasNext()){
        	Location l = it.next();
        	Item value = hashMap.get(l);
        	if(value.getName().equals("Coin")){
        		value.drawPhoto(g, (l.getX()*50) +30, (l.getY()*50)+102);
        	}
        	if(value.getName().equals("Player1")){
        		value.drawPhoto(g, (l.getX()*50)+30, (l.getY()*50)+102);
        	} 
        	if(value.getName().equals("Fire")){
        		value.drawPhoto(g, (l.getX()*50)+30, (l.getY()*50)+102);
        	} 

        }
        g.setColor(Color.BLUE);
        if(health==0){
        	g.drawString("Player1 Loses!", 20,50);
        } else {
        	g.drawString("Player1 Health: " + health, 20,50);
        	int count = 0;
    		int x = 20;
	        while(count < health){
	        	g.fillRect(x, 60, 75,20);
	        	x=x+80;
	        	count++;
	        }
        }
        

    }
    public void actionPerformed(ActionEvent e) {
    }

    public void keyPressed(KeyEvent e){
    	//System.out.println(e.getKeyCode());
    	Item value = hashMap.get(currentLoction);
    	if( e.getKeyCode() == 39 && currentLoction.getX() <= 6){
    		hashMap.remove(currentLoction);
    		currentLoction = new Location(currentLoction.getX()+1, currentLoction.getY());
    		if(hashMap.containsKey(currentLoction)){
    			Item item = hashMap.get(currentLoction);
	    		if(item.getName().equals("Coin")){
	    			player1Total++;
	    		}
	    		if(item.getName().equals("Fire")){
	    			currentLoction = new Location(0,0);
	    			health--;
	    		}
    		}
    		hashMap.put(currentLoction, value);
    		repaint();
    	}
    	if( e.getKeyCode() == 40 && currentLoction.getY() <= 6){
    		hashMap.remove(currentLoction);
    		currentLoction = new Location(currentLoction.getX(), currentLoction.getY()+1);
    		if(hashMap.containsKey(currentLoction)){
    			Item item = hashMap.get(currentLoction);
	    		if(item.getName().equals("Coin")){
	    			player1Total++;
	    		}
	    		if(item.getName().equals("Fire")){
	    			currentLoction = new Location(0,0);
	    			health--;
	    		}
    		}
    		hashMap.put(currentLoction, value);
    		repaint();
    	}
    	if( e.getKeyCode() == 37 && currentLoction.getX() >= 1){
    		hashMap.remove(currentLoction);
    		currentLoction = new Location(currentLoction.getX()-1, currentLoction.getY());
    		if(hashMap.containsKey(currentLoction)){
    			Item item = hashMap.get(currentLoction);
	    		if(item.getName().equals("Coin")){
	    			player1Total++;
	    		}
	    		if(item.getName().equals("Fire")){
	    			currentLoction = new Location(0,0);
	    			health--;
	    		}
    		}
    		hashMap.put(currentLoction, value);
    		repaint();
    	}
    	if( e.getKeyCode() == 38 && currentLoction.getY() >= 1){
    		hashMap.remove(currentLoction);
    		currentLoction = new Location(currentLoction.getX(), currentLoction.getY()-1);
    		if(hashMap.containsKey(currentLoction)){
    			Item item = hashMap.get(currentLoction);
	    		if(item.getName().equals("Coin")){
	    			player1Total++;
	    		}
	    		if(item.getName().equals("Fire")){
	    			currentLoction = new Location(0,0);
	    			health--;
	    		}
    		}
    		hashMap.put(currentLoction, value);
    		repaint();
    	}
    }
    public void keyReleased(KeyEvent e){
	}
	public void keyTyped(KeyEvent e){	 
	}
}