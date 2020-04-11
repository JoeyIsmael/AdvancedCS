import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javafx.embed.swing.JFXPanel;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;
import java.awt.*;  
import java.util.*;
 
public class ServerScreen extends JPanel implements ActionListener, MouseListener{
	boolean mark1 = false;
	boolean mark2 = false;
	boolean mark3 = false;
	boolean mark4 = false;
	boolean mark5 = false;
	boolean mark6 = false;
	boolean mark7 = false;
	boolean mark8 = false;
	boolean mark9 = false;
	boolean mark10 = false;
	boolean mark11 = false;
	boolean mark12 = false;
	boolean mark13 = false;
	boolean mark14 = false;
	boolean mark15 = false;
	boolean mark16 = false;
	boolean mark17 = false;
	boolean mark18 = false;
	boolean incrementTurn;
	ObjectOutputStream outObj;
	ObjectInputStream inObj;
	boolean box1Filled = false;
	boolean box2Filled = false;
	boolean box3Filled = false;
	boolean box4Filled = false;
	boolean box5Filled = false;
	boolean box6Filled = false;
	boolean box7Filled = false;
	boolean box8Filled = false;
	boolean box9Filled = false;
	boolean player1Won = false;
	boolean player2Won = false;
	boolean canMove = true;
	int player1Wins = 0;
	int player2Wins = 0;
	private JButton newButton;
	private JButton AIButton;
	int turn = 0;
	Game g1 = new Game();
	private PrintWriter out;
	private BufferedReader in;

	
    public ServerScreen() {
		setLayout(null);
        addMouseListener(this);
		newButton = new JButton("New Game");
		newButton.setBounds(325,375,100,30);
		newButton.addActionListener(this);
		this.add(newButton);
		newButton.setVisible(false);
    }
	 public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800, 600);
	 }
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
 
        //draw background
        Color lightBlue = new Color(191, 255, 226);
		g.setColor(lightBlue);
        g.fillRect(0, 0, 800, 600);
		
		//draw table
		g.setColor(Color.black);
		g.drawRect(225,50,300,300);
		g.drawLine(225, 150, 525, 150);
		g.drawLine(225, 250, 525, 250);
		g.drawLine(325,50, 325, 350);
		g.drawLine(425,50, 425, 350);
		g.drawString("Player 1 Wins: " + player1Wins, 20, 100);
		g.drawString("Player 2 Wins: " + player2Wins, 20, 120);
 
       if(mark1 == true){
			g.drawLine(225, 50, 325, 150);
			g.drawLine(325, 50, 225, 150);
			box1Filled = true;
	   }
	   if(mark2 == true){
			g.drawLine(225, 150, 325, 250);
			g.drawLine(325, 150, 225, 250);
			box2Filled = true;
	   }
	   if(mark3 == true){
			g.drawLine(225, 250, 325, 350);
			g.drawLine(325, 250, 225, 350);
			box3Filled = true;
	   }
	   if(mark4 == true){
			g.drawLine(325, 50, 425, 150);
			g.drawLine(425, 50, 325, 150);
			box4Filled = true;
	   }
	   if(mark5 == true){
			g.drawLine(325, 150, 425, 250);
			g.drawLine(425, 150, 325, 250);
			box5Filled = true;
	   }
	   if(mark6 == true){
			g.drawLine(325, 250, 425, 350);
			g.drawLine(425, 250, 325, 350);
			box6Filled = true;
	   }
	   if(mark7 == true){
			g.drawLine(425, 50, 525, 150);
			g.drawLine(525, 50, 425, 150);
			box7Filled = true;
	   }
	   if(mark8 == true){
			g.drawLine(425, 150, 525, 250);
			g.drawLine(525, 150, 425, 250);
			box8Filled = true;
	   }
	   if(mark9 == true){
			g.drawLine(425, 250, 525, 350);
			g.drawLine(525, 250, 425, 350);
			box9Filled = true;
	   }
	   if(mark10 == true){
			g.drawOval(240, 65, 75, 75);
			box1Filled = true;
	   }
	   if(mark11 == true){
			g.drawOval(240, 165, 75, 75);
			box2Filled = true;
	   }
	   if(mark12 == true){
			g.drawOval(240, 265, 75, 75);
			box3Filled = true;
	   }
	   if(mark13 == true){
			g.drawOval(340, 65, 75, 75);
			box4Filled = true;
	   }
	   if(mark14 == true){
			g.drawOval(340, 165, 75, 75);
			box5Filled = true;
	   }
	   if(mark15 == true){
			g.drawOval(340, 265, 75, 75);
			box6Filled = true;
	   }
	   if(mark16 == true){
			g.drawOval(440, 65, 75, 75);
			box7Filled = true;
	   }
	   if(mark17 == true){
			g.drawOval(440, 165, 75, 75);
			box8Filled = true;
	   }
	   if(mark18 == true){
			g.drawOval(440, 265, 75, 75);
			box9Filled = true;
	   }
	   if(g1.whenXWon() == true){
		   player1Won = true;
		   player1Wins++;
		   newButton.setVisible(true);
	   }
	   if(g1.whenOWon() == true){
		   player2Won = true;
		   player2Wins++;
		   newButton.setVisible(true);
	   }
	   if(player1Won == true){
		   playWinSound();
		   canMove = false;
		   g.drawString("Player 1 Won!!", 20, 20);
	   }
	   if(player2Won == true){
		   playWinSound();
		   canMove = false;
		   g.drawString("Player 2 Won!!", 20, 20);
	   }
	   if(g1.checkTie() == true && player1Won == false && player2Won == false){
		   g.drawString("Tie!!", 20, 20);
		   canMove = false;
		   playTieSound();
		   newButton.setVisible(true);
	   }
	}
	public void playButtonSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("sounds/click.wav");
			if(url == null){
				System.out.println("Danger");
			}
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
	public void playTieSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("sounds/lose.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
	public void playWinSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("sounds/win.wav");
			if(url == null){
				System.out.println("Danger");
			}
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public void poll() throws IOException {
        int portNumber = 1024;
         
		try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
	        Socket clientSocket = serverSocket.accept();
	        outObj = new ObjectOutputStream(clientSocket.getOutputStream());
			inObj = new ObjectInputStream(clientSocket.getInputStream());

			outObj.writeObject("Connection Successful!");


			while(true){
				g1 = (Game) inObj.readObject();

				if(g1.whenXWon() == true){
				   player1Won = true;
				   player1Wins++;
				   newButton.setVisible(true);
	   			}
				repaint();
			}


        } catch (UnknownHostException e) {
            System.err.println("Host unkown: " + portNumber);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not exist" + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                portNumber);
            System.exit(1);
        }
     
    }

	public void mousePressed(MouseEvent e) 
	{
		
        //Check if mouse pressed position is in the brown box
        if (e.getX() >= 225 && e.getX() <= 325 && e.getY() >= 50 && e.getY() <= 150 && turn%2 == 0 && box1Filled == false && canMove == true){
			mark1 = true;
			incrementTurn = true;
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			g1.insertX(0,0);
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 225 && e.getX() <= 325 && e.getY() >= 150 && e.getY() <= 250 && turn%2 == 0 && box2Filled == false && canMove == true){
			mark2 = true;
			incrementTurn = true;
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			g1.insertX(1,0);
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 225 && e.getX() <= 325 && e.getY() >= 250 && e.getY() <= 350 && turn%2 == 0 && box3Filled == false && canMove == true){
			mark3 = true;
			incrementTurn = true;
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			g1.insertX(2,0);
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 325 && e.getX() <= 425 && e.getY() >= 50 && e.getY() <= 150 && turn%2 == 0 && box4Filled == false && canMove == true){
			mark4 = true;
			incrementTurn = true;
			g1.insertX(0,1);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 325 && e.getX() <= 425 && e.getY() >= 150 && e.getY() <= 250 && turn%2 == 0 && box5Filled == false && canMove == true){
			mark5 = true;
			incrementTurn = true;
			g1.insertX(1,1);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
			
        }
		else if (e.getX() >= 325 && e.getX() <= 425 && e.getY() >= 250 && e.getY() <= 350 && turn%2 == 0 && box6Filled == false && canMove == true){
			mark6 = true;
			incrementTurn = true;
			g1.insertX(2,1);
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 425 && e.getX() <= 525 && e.getY() >= 50 && e.getY() <= 150 && turn%2 == 0 && box7Filled == false && canMove == true){
			mark7 = true;
			incrementTurn = true;
			g1.insertX(0,2);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 425 && e.getX() <= 525 && e.getY() >= 150 && e.getY() <= 250 && turn%2 == 0 && box8Filled == false && canMove == true){
			mark8 = true;
			incrementTurn = true;
			g1.insertX(1,2);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 425 && e.getX() <= 525 && e.getY() >= 250 && e.getY() <= 350 && turn%2 == 0 && box9Filled == false && canMove == true){
			mark9 = true;

			incrementTurn = true;
			g1.insertX(2,2);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 225 && e.getX() <= 325 && e.getY() >= 50 && e.getY() <= 150 && turn%2 == 1 && box1Filled == false && canMove == true){
			mark10 = true;
			incrementTurn = true;
			g1.insertO(0,0);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 225 && e.getX() <= 325 && e.getY() >= 150 && e.getY() <= 250 && turn%2 == 1 && box2Filled == false && canMove == true){
			mark11 = true;
			incrementTurn = true;
			g1.insertO(1,0);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 225 && e.getX() <= 325 && e.getY() >= 250 && e.getY() <= 350 && turn%2 == 1 && box3Filled == false && canMove == true){
			mark12 = true;
			incrementTurn = true;
			g1.insertO(2,0);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 325 && e.getX() <= 425 && e.getY() >= 50 && e.getY() <= 150 && turn%2 == 1 && box4Filled == false && canMove == true){
			mark13 = true;
			incrementTurn = true;
			g1.insertO(0,1);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 325 && e.getX() <= 425 && e.getY() >= 150 && e.getY() <= 250 && turn%2 == 1 && box5Filled == false && canMove == true){
			mark14 = true;
			incrementTurn = true;
			g1.insertO(1,1);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 325 && e.getX() <= 425 && e.getY() >= 250 && e.getY() <= 350 && turn%2 == 1 && box6Filled == false && canMove == true){
			mark15 = true;
			incrementTurn = true;
			g1.insertO(2,1);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 425 && e.getX() <= 525 && e.getY() >= 50 && e.getY() <= 150 && turn%2 == 1 && box7Filled == false && canMove == true){
			mark16 = true;
			incrementTurn = true;
			g1.insertO(0,2);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 425 && e.getX() <= 525 && e.getY() >= 150 && e.getY() <= 250 && turn%2 == 1 && box8Filled == false && canMove == true){
			mark17 = true;
			incrementTurn = true;
			g1.insertO(1,2);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		else if (e.getX() >= 425 && e.getX() <= 525 && e.getY() >= 250 && e.getY() <= 350 && turn%2 == 1 && box9Filled == false && canMove == true){
			mark18 = true;
			incrementTurn = true;
			g1.insertO(2,2);
			try{

			outObj.reset();
		    outObj.writeObject(g1);
		 
		    repaint();
		} catch (IOException f) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
			playButtonSound();
			g1.checkTicTacToe(turn);
        }
		
		else {
			System.out.println("No turn: " + turn);
        }
		
		 if(incrementTurn){
		   incrementTurn = false;
		   turn++;
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
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == newButton){
		   mark1 = false;
		   mark2 = false;
		   mark3 = false;
		   mark4 = false;
		   mark5 = false;
		   mark6 = false;
		   mark7 = false;
		   mark8 = false;
		   mark9 = false;
		   mark10 = false;
		   mark11 = false; 
		   mark12 = false;
		   mark13 = false;
		   mark14 = false;
		   mark15 = false;
		   mark16 = false;
		   mark17 = false;
		   mark18 = false;
		   box1Filled = false;
		   box2Filled = false;
		   box3Filled = false;
		   box4Filled = false;
		   box5Filled = false;
		   box6Filled = false;
		   box7Filled = false;
		   box8Filled = false;
		   box9Filled = false;
		   g1.clear();
		   player1Won = false;
		   player2Won = false;
		   canMove = true;
		   turn = 0;
		   newButton.setVisible(false);
		}
	}
	
}

