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
import javax.imageio.ImageIO;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ClientServer extends JPanel implements ActionListener, KeyListener{
	
	Map<Location, Item> hashMap;
    Game grid;
	Location currentLoction = null;
    Player player1;
	int player1Total = 0;
    private BufferedImage image;
    ObjectInputStream inObj;
    ObjectOutputStream out;
    boolean winner = false;


	public ClientServer(){
        this.setLayout(null);
        this.setFocusable(true);
        addKeyListener(this);
    }
    public Dimension getPreferredSize() {
 
        return new Dimension(800,600);
    }

    public void playCoinSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("coins.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
    public void playObstacleSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("obstacle.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        if(grid != null){
            g.setColor(Color.BLUE);
            g.drawString("Player 1 Diamonds: " + grid.getPlayer1Diamonds(), 20, 20);
            g.setColor(Color.RED);
            g.drawString("Player 2 Diamonds: " + grid.getPlayer2Diamonds(), 150, 20);
        }

        g.setColor(Color.BLACK);
        int x1 = 30;
        int y1 = 100;
        for(int i = 0; i < 10; i++){
        	g.drawLine(x1, y1, x1+450, y1);
        	y1 = y1 + 50;
        }
        y1 = 100;
         for(int i = 0; i < 10; i++){
        	g.drawLine(x1, y1, x1, y1+450);
        	x1 = x1 + 50;
        }
        if(grid != null){
             Iterator<Location> it = grid.getGameItems().keySet().iterator();
            while(it.hasNext()){
                Location l = it.next();
                Item value = grid.getGameItems().get(l);
                if(value.getName().equals("Coin")){
                    drawPhoto(g, (l.getX()*50) +30, (l.getY()*50)+102, value.getFile());
                }
                if(value.getName().equals("Fire")){
                    drawPhoto(g, (l.getX()*50)+30, (l.getY()*50)+102, value.getFile());
                } 
                if(value.getName().equals("Landscape")){
                    drawPhoto(g, (l.getX()*50)+30, (l.getY()*50)+102, value.getFile());
                } 

            }

            drawPhoto(g, grid.getPlayer1XLocation()*50+30, grid.getPlayer1YLocation()*50+102, "player1.png");
            drawPhoto(g, grid.getPlayer2XLocation()*50+30, grid.getPlayer2YLocation()*50+102, "player2.png");

            g.setColor(Color.BLUE);
            int health = grid.getPlayer1Stack().size();
            int health2 = grid.getPlayer2Stack().size();
            if(grid.getDiamond() == 0){
                if(grid.getPlayer1Diamonds() > grid.getPlayer2Diamonds()){
                    g.drawString("Player 1 Wins!", 20,50);
                    winner = true;
                } else{
                    g.drawString("Player 2 Wins!", 20,50);
                    winner = true;
                }
            } else{
                if(health == 0){
                    g.drawString("Player 2 Wins!", 20,50);
                    winner = true;
                }
                else if(health2 == 0){
                    g.drawString("Player 1 Wins!", 20,50);
                    winner = true;
                } else {
                        g.drawString("Player 1 Health: " + health, 20,50);
                        int count = 0;
                        int x = 20;
                        while(count < health){
                            g.fillRect(x, 60, 55,15);
                            x=x+60;
                            count++;
                        } 
                        g.setColor(Color.RED);
                        g.drawString("Player 2 Health: " + health2, 250,50);
                        x = 250;
                        count = 0;
                        while(count < health2){
                            g.fillRect(x, 60, 55,15);
                            x=x+60;
                            count++;
                        }
                    }
                }
            }
            
       
    }

    public void drawPhoto(Graphics g, int x, int y, String photoFile){
        try{
            image = ImageIO.read(new File(photoFile));
            g.drawImage(image, x, y, 50, 50, null);
        } catch (IOException ex){
            //exception
        }
    }

    public void poll() throws IOException {
        int portNumber = 1024;
        String hostName = "localhost";

         
        Socket serverSocket = new Socket(hostName, portNumber);   
        inObj = new ObjectInputStream(serverSocket.getInputStream());
        out = new ObjectOutputStream(serverSocket.getOutputStream());
        
        try {
            while(true){
                grid = (Game)inObj.readObject(); 
                player1 = grid.getPlayer1();
                repaint();
            }
        } 
        catch (UnknownHostException e) {
            System.err.println("Host unkown: ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not exist" + e);
            System.exit(1);
        }
    }    

    public void actionPerformed(ActionEvent e) {
    }

    public void keyPressed(KeyEvent e){
    	//System.out.println(e.getKeyCode());
        Location right = new Location(grid.getPlayer1XLocation()+1, grid.getPlayer1YLocation());
        Location down = new Location(grid.getPlayer1XLocation(), grid.getPlayer1YLocation()+1);
        Location left = new Location(grid.getPlayer1XLocation()-1, grid.getPlayer1YLocation());
        Location up = new Location(grid.getPlayer1XLocation(), grid.getPlayer1YLocation()-1);
        if( e.getKeyCode() == 39 && grid.getPlayer1XLocation() <= 7 && winner == false){
            if(grid.getGameItems().containsKey(right)){
                Item item = grid.getGameItems().get(right);
                if(item.getName().equals("Landscape")){
                } else{
                    player1.setLocation(right);
                    if(item.getName().equals("Coin")){
                        grid.setPlayer1Diamonds();
                        grid.removeDiamond();
                        grid.getGameItems().remove(right);
                        playCoinSound();
                    }
                    if(item.getName().equals("Fire")){
                        player1.setLocation(new Location(0,0));
                        grid.removeHealth1();
                        playObstacleSound();
                    }
                }
            } else {
                player1.setLocation(right);
            }
        }
        if( e.getKeyCode() == 40 && grid.getPlayer1YLocation() <= 7 && winner == false ){
            if(grid.getGameItems().containsKey(down)){
                Item item = grid.getGameItems().get(down);
                if(item.getName().equals("Landscape")){
                } else{
                    player1.setLocation(down);
                    if(item.getName().equals("Coin")){
                        grid.setPlayer1Diamonds();
                        grid.removeDiamond();
                        grid.getGameItems().remove(down);
                        playCoinSound();
                    }
                    if(item.getName().equals("Fire")){
                        player1.setLocation(new Location(0,0));
                        grid.removeHealth1();
                        playObstacleSound();
                    }
                }
            } else {
                player1.setLocation(down);
            }
        }
        if( e.getKeyCode() == 37 && grid.getPlayer1XLocation() >= 1 && winner == false){
            if(grid.getGameItems().containsKey(left)){
                Item item = grid.getGameItems().get(left);
                if(item.getName().equals("Landscape")){
                } else{
                    player1.setLocation(left);
                    if(item.getName().equals("Coin")){
                        grid.setPlayer1Diamonds();
                        grid.removeDiamond();
                        grid.getGameItems().remove(left);
                        playCoinSound();
                    }
                    if(item.getName().equals("Fire")){
                        player1.setLocation(new Location(0,0));
                        grid.removeHealth1();
                        playObstacleSound();
                    }
                }
            } else {
                player1.setLocation(left);
            }
        }
        if( e.getKeyCode() == 38 && grid.getPlayer1YLocation() >= 1 && winner == false){
            if(grid.getGameItems().containsKey(up)){
                Item item = grid.getGameItems().get(up);
                if(item.getName().equals("Landscape")){
                } else{
                    player1.setLocation(up);
                    if(item.getName().equals("Coin")){
                        grid.setPlayer1Diamonds();
                        grid.removeDiamond();
                        grid.getGameItems().remove(up);
                        playCoinSound();
                    }
                    if(item.getName().equals("Fire")){
                        player1.setLocation(new Location(0,0));
                        grid.removeHealth1();
                        playObstacleSound();
                    }
                }
            } else {
                player1.setLocation(up);
            }
        }
        if(out != null){
            try{
                out.reset();
                out.writeObject(grid);
                repaint();
            }
            catch (IOException f) {
                System.err.println("Couldn't get I/O for the connection to ");
                System.exit(1);
            }
        }
        
    }
    public void keyReleased(KeyEvent e){
	}
	public void keyTyped(KeyEvent e){	 
	}
}