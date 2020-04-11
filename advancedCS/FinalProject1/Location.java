import java.util.*;
import java.awt.*;
import java.io.Serializable;
public class Location implements Serializable{

	private int x;
	private int y;

	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}


	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public boolean equals(Object o){
		Location l = (Location)o;
		if (x == l.getX() && y == l.getY()){
			return true;
		} else {
			return false;
		}
	}

	public int hashCode(){
		int code = x*10+y;
		return code;
	}

	
}