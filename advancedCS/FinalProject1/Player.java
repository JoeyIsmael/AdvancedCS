import java.io.Serializable;
import java.util.*;
import java.awt.*;

public class Player implements Serializable{
	Location location;
	private String photoFile;
	private String name;

	public Player(Location location, String photoFile, String name){
		this.location = location;
		this.photoFile = photoFile;
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public String getFile(){
		return photoFile;
	}
	public int getXLocation(){
		return location.getX();
	}
	public int getYLocation(){
		return location.getY();
	}
	public Location getLocation(){
		return location;
	}
	public void setLocation(Location location){
		this.location = location;
	}
}