import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
public class Screen extends JPanel implements ActionListener, MouseListener{

	DLList<Card> deck = new DLList<Card>();
	DLList<Card> playerDeck = new DLList<Card>();
	private JButton newGame;
	private JButton draw;
	private boolean canDraw = true;
	private int points = 50;
	private String pointsWon = "";
	public Screen(){
		this.setLayout(null);
		this.setFocusable(true);
		addMouseListener(this);
		deck.add(new Card(2, "2", "hearts"));
		deck.add(new Card(3, "3", "hearts"));
		deck.add(new Card(4, "4", "hearts"));
		deck.add(new Card(11, "J", "hearts"));
		deck.add(new Card(14, "A", "hearts"));
		deck.add(new Card(2, "2", "diamonds"));
		deck.add(new Card(2, "2", "spades"));
		deck.add(new Card(2, "2", "clubs"));
		deck.add(new Card(14, "A", "diamonds"));
		deck.add(new Card(14, "A", "spades"));
		deck.add(new Card(14, "A", "clubs"));
		deck.add(new Card(3, "3", "diamonds"));
		deck.add(new Card(3, "3", "spades"));
		deck.add(new Card(3, "3", "clubs"));
		deck.add(new Card(4, "4", "diamonds"));
		deck.add(new Card(4, "4", "spades"));
		deck.add(new Card(4, "4", "clubs"));
		deck.add(new Card(5, "5", "diamonds"));
		deck.add(new Card(5, "5", "spades"));
		deck.add(new Card(5, "5", "clubs"));
		deck.add(new Card(5, "5", "hearts"));
		deck.add(new Card(6, "6", "diamonds"));
		deck.add(new Card(6, "6", "spades"));
		deck.add(new Card(6, "6", "clubs"));
		deck.add(new Card(6, "6", "hearts"));
		deck.add(new Card(7, "7", "diamonds"));
		deck.add(new Card(7, "7", "spades"));
		deck.add(new Card(7, "7", "clubs"));
		deck.add(new Card(7, "7", "hearts"));
		deck.add(new Card(8, "8", "diamonds"));
		deck.add(new Card(8, "8", "spades"));
		deck.add(new Card(8, "8", "clubs"));
		deck.add(new Card(8, "8", "hearts"));
		deck.add(new Card(9, "9", "diamonds"));
		deck.add(new Card(9, "9", "spades"));
		deck.add(new Card(9, "9", "clubs"));
		deck.add(new Card(9, "9", "hearts"));
		deck.add(new Card(10, "10", "spades"));
		deck.add(new Card(10, "10", "clubs"));
		deck.add(new Card(10, "10", "diamonds"));
		deck.add(new Card(10, "10", "hearts"));
		deck.add(new Card(11, "J", "diamonds"));
		deck.add(new Card(11, "J", "spades"));
		deck.add(new Card(11, "J", "clubs"));
		deck.add(new Card(12, "Q", "diamonds"));
		deck.add(new Card(12, "Q", "spades"));
		deck.add(new Card(12, "Q", "clubs"));
		deck.add(new Card(12, "Q", "hearts"));
		deck.add(new Card(13, "K", "diamonds"));
		deck.add(new Card(13, "K", "spades"));
		deck.add(new Card(13, "K", "clubs"));
		deck.add(new Card(13, "K", "hearts"));

		newGame = new JButton("New Game");
		newGame.setBounds(20,30,100,30);
		newGame.addActionListener(this);
		this.add(newGame);

		draw = new JButton("Draw");
		draw.setBounds(150,30,100,30);
		draw.addActionListener(this);
		this.add(draw);
	}

	public void youWin() {
        try {
            URL url = this.getClass().getClassLoader().getResource("win.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
    public void youLose() {
        try {
            URL url = this.getClass().getClassLoader().getResource("lose.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(1000,600);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Color green = new Color(54, 130, 55);
		g.setColor(green);
		g.fillRect(0,0,1000,600);
		Color black = new Color(0, 0, 0);
		Color yellow = new Color(255,255,0);
		g.setColor(black);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
		g.drawString("Player Points: " + points, 280, 35);
		g.drawString(pointsWon, 280, 65);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		g.drawString("Royal Flush - 250pts", 30, 270);
		g.drawString("Straight Flush - 50pts", 30, 300);
		g.drawString("Four of a Kind - 25pts", 30, 330);
		g.drawString("Full House - 9pts", 30, 360);
		g.drawString("Flush - 6pts", 30, 390);
		g.drawString("Straight - 4pts", 30, 420);
		g.drawString("3 of a Kind - 3pts", 30, 450);
		g.drawString("2 Pairs - 2pts", 30, 480);
		g.drawString("Pair of Jacks or Higher - 1pt", 30, 510);

		
		
		g.setColor(black);
		Font font = new Font("Arial", Font.PLAIN, 50);
		g.setFont(font);

		int x = 20;
		int y = 100;
		for(int i = 0; i<playerDeck.size(); i++){
			if(playerDeck.get(i).getHold() == true){
				g.setColor(yellow);
				g.fillRect(x, y-25, 100, 140);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
				g.setColor(black);
				g.drawString("Held", x+10, y-7);
			}
			playerDeck.get(i).drawMe(g, x, y);
			x+=120;
		}
	}

	public boolean pair(){
		for(int i = 0; i < playerDeck.size()-1; i++){
			if(playerDeck.get(i).getValue() == playerDeck.get(i+1).getValue() && playerDeck.get(i).getValue() > 10 && playerDeck.get(i+1).getValue() > 10){
				return true;
			}
		}
		return false;
	}

	public boolean twoPairs(){
		boolean temp = false;
		for(int i = 0; i < playerDeck.size()-1; i++){
			if(playerDeck.get(i).getValue()  == playerDeck.get(i+1).getValue()){
				if(temp){
					return true;
				}
				temp = true;
			}
		}
		return false;
	}

	public boolean triple(){
		for(int i = 0; i < playerDeck.size()-2; i++){
			if(playerDeck.get(i).getValue() == playerDeck.get(i+1).getValue()){
				if(playerDeck.get(i).getValue() == playerDeck.get(i+2).getValue()){
					return true;
				}
			}
		}
		return false;
	}

	public boolean straight(){
		if(playerDeck.get(0).getValue()+1 == playerDeck.get(1).getValue()){
			if(playerDeck.get(1).getValue()+1 == playerDeck.get(2).getValue()){
				if(playerDeck.get(2).getValue()+1 == playerDeck.get(3).getValue()){
					if(playerDeck.get(3).getValue()+1 == playerDeck.get(4).getValue()){
						return true;
					}
				}
			}
		}
		else if(playerDeck.get(4).getValue()-12 == playerDeck.get(0).getValue()){
			if(playerDeck.get(0).getValue()+1 == playerDeck.get(1).getValue()){
				if(playerDeck.get(1).getValue()+1 == playerDeck.get(2).getValue()){
					if(playerDeck.get(2).getValue()+1 == playerDeck.get(3).getValue()){
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean flush(){
		String suit = playerDeck.get(0).getSuit();
		for(int i = 1; i < playerDeck.size(); i++){
			if(playerDeck.get(i).getSuit().equals(suit) == false){
				return false;
			}
		}
		return true;
	}

	public boolean straightFlush(){
		if(flush() && straight()){
			return true;
		}
		return false;
	}

	public boolean fourofKind(){
		for(int i = 0; i < playerDeck.size()-3; i++){
			if(playerDeck.get(i).getValue() == playerDeck.get(i+1).getValue()){
				if(playerDeck.get(i).getValue() == playerDeck.get(i+2).getValue()){
					if(playerDeck.get(i).getValue() == playerDeck.get(i+3).getValue()){
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean royalFlush(){
		if(flush() && playerDeck.get(0).getValue() == 10 && playerDeck.get(1).getValue() == 11 && playerDeck.get(2).getValue() == 12 && playerDeck.get(3).getValue() == 13 && playerDeck.get(4).getValue() == 14){
			return true;
		}
		return false;
	}

	public boolean fullHouse(){
		if(playerDeck.get(0).getValue() == playerDeck.get(1).getValue() && playerDeck.get(0).getValue() == playerDeck.get(2).getValue() && playerDeck.get(3).getValue() == playerDeck.get(4).getValue()){
			return true;
		} else if(playerDeck.get(2).getValue() == playerDeck.get(3).getValue() && playerDeck.get(4).getValue() == playerDeck.get(0).getValue() && playerDeck.get(1).getValue() == playerDeck.get(4).getValue()){
			return true;
		}
		return false;
	}

	public void shuffle(){
		for(int i = 0; i < deck.size(); i++){
			int swap = (int)(Math.random()*(deck.size()));
			Card bk = deck.get(i);
			deck.set(i,deck.get(swap));
			deck.set(swap,bk);
		}
	}

	public void Sort(){
		for(int i = 0; i < playerDeck.size()-1; i++){
			for(int j = i+1; j < playerDeck.size(); j++){
				if(playerDeck.get(i).getValue() > playerDeck.get(j).getValue()){
					Card temp1 = playerDeck.get(i);
					Card temp2 = playerDeck.get(j);
					playerDeck.set(i, temp2);
					playerDeck.set(j, temp1);
				}
			}
		}
	}

	public void mousePressed(MouseEvent e){
		for(int i = 0; i<playerDeck.size(); i++){
			if(playerDeck.get(i).hit(e.getX(), e.getY()) && canDraw == true && playerDeck.get(i).getHold() == false){
				playerDeck.get(i).hold();
				repaint();
				return;
			} else if(playerDeck.get(i).hit(e.getX(), e.getY()) && canDraw == true && playerDeck.get(i).getHold() == true){
				playerDeck.get(i).unhold();
				repaint();
				return;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	public void mouseEntered(MouseEvent e){
		
	}
	public void mouseExited(MouseEvent e){
		
	}
	public void mouseClicked(MouseEvent e){
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newGame && points > 0){
			canDraw = true;
			pointsWon = "";
			points--;
			while(playerDeck.size() > 0){
				deck.add(playerDeck.get(0));
				playerDeck.remove();
			}
			shuffle();
			playerDeck.add(deck.get(0));
			deck.remove();
			playerDeck.add(deck.get(0));
			deck.remove();
			playerDeck.add(deck.get(0));
			deck.remove();
			playerDeck.add(deck.get(0));
			deck.remove();
			playerDeck.add(deck.get(0));
			deck.remove();
		}
		if(e.getSource() == draw && canDraw == true){
			for(int i = 0; i < playerDeck.size(); i++){
				if(playerDeck.get(i).getHold() == false){
					Card c = playerDeck.get(i);
					playerDeck.set(i, deck.get(0));
					deck.remove();
					deck.add(c);
				} else{
					playerDeck.get(i).unhold();
				}
			}
			canDraw = false;
			Sort();
			if(royalFlush()){
				points = points + 250;
				pointsWon = "Royal Flush! + 250pts";
				youWin();
			}
			else if(straightFlush()){
				points = points + 50;
				pointsWon = "Straight Flush! + 50pts";
				youWin();
			}
			else if(fourofKind()){
				points = points + 25;
				pointsWon = "Four of a Kind! +25pts";
				youWin();
			}
			else if(fullHouse()){
				points = points + 9;
				pointsWon = "Full House! + 9pts";
				youWin();
			}
			else if(flush()){
				points = points + 6;
				pointsWon = "Flush! +6pts";
				youWin();
			}
			else if(straight()){
				points = points + 4;
				pointsWon = "Straight! +4pts";
				youWin();
			}
			else if(triple()){
				points = points + 3;
				pointsWon = "Triple! +3pts";
				youWin();
			}
			else if(twoPairs()){
				points = points + 2;
				pointsWon = "Two Pairs! +2pts";
				youWin();
			}
			else if(pair()){
				pointsWon = "Pair! +1pt";
				points++;
				youWin();
			}
			else{
				pointsWon = "You Got Nothing.";
				youLose();
			}
		}
		repaint();
	}
}