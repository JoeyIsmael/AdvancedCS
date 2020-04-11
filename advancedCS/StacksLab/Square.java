import java.awt.Graphics;
import java.awt.Color;

public class Square{
	
	private Color color;
	private int x;
	private int y;
	private int w = 40;
	
	public Square(Color color, int x, int y){
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	public void drawMe( Graphics g){	
		g.setColor(color);	
		g.fillRect(x, y, w, w);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, w, w);
		
	}
	
	public void changeColor(Color color){
		this.color = color;
	}
	public Color getColor(){
		return color;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean hit(int mX, int mY){
		if((mX > x) && (mX < x + w) && (mY > y) && (mY < y + w)){
			return true;
		}
		return false;
	}
	
}