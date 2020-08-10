import javax.imageio.ImageIO;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Item implements Serializable{
	private String photoFile;
	private String name;
	private BufferedImage image;
	public Item(String photoFile, String name){
		this.photoFile = photoFile;
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public String getFile(){
		return photoFile;
	}
}