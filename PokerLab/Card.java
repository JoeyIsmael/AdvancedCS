import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Card {
	private int value;
	private String name;
	private String suit;
	private BufferedImage suitImage;
	private boolean isHeld;
	private int xPos;
	private int yPos;
	
	public Card(int value, String name, String suit){
		this.value = value;
		this.name = name;
		this.suit = suit;
		this.isHeld = false;
		
		if( suit.equals("hearts") ){
			try{
				suitImage = ImageIO.read(new File("Suits/hearts.jpg"));
			} catch (IOException e){}
		}
		else if( suit.equals("diamonds") ){
			try{
				suitImage = ImageIO.read(new File("Suits/diamonds.jpg"));
			} catch (IOException e){}
		}
		else if( suit.equals("spades") ){
			try{
				suitImage = ImageIO.read(new File("Suits/spades.jpg"));
			} catch (IOException e){}
		}
		else if( suit.equals("clubs") ){
			try{
				suitImage = ImageIO.read(new File("Suits/Clubs.png"));
			} catch (IOException e){}
		}
	}
	
	public void drawMe(Graphics g, int x, int y){
		xPos = x;
		yPos = y;
		g.setColor(Color.WHITE);
		g.fillRect(x,y,100,120);
		g.setColor(Color.BLACK);
		g.drawRect(x,y,100,120);
		Font font = new Font("Arial", Font.PLAIN, 50);
		g.setFont(font);
		if( suit.equals("hearts") ){
			g.setColor(Color.RED);
			g.drawString(name, x+25, y+80);
			g.drawImage(suitImage, x+2, y, null);
		}
		else if( suit.equals("diamonds") ){
			g.setColor(Color.RED);
			g.drawString(name, x+25, y+80);
			g.drawImage(suitImage, x+2, y, null);
		}
		else if( suit.equals("clubs") ){
			g.setColor(Color.BLACK);
			g.drawString(name, x+25, y+80);
			g.drawImage(suitImage, x+2, y, null);
		}
		else if( suit.equals("spades") ){
			g.setColor(Color.BLACK);
			g.drawString(name, x+25, y+80);
			g.drawImage(suitImage, x+2, y, null);
		}
	}
	
	public int getValue(){
		return value;
	}

	public String getSuit(){
		return suit;
	}

	public boolean hit(int mX, int mY){
		if((mX > xPos) && (mX < xPos + 100) && (mY > 120) && (mY < 120 + 80)){
			return true;
		}
		return false;
	}

	public void hold(){
		isHeld = true;
	}

	public void unhold(){
		isHeld = false;
	}

	public boolean getHold(){
		return isHeld;
	}
}
