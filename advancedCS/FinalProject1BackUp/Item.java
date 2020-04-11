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

	public void drawPhoto(Graphics g, int x, int y){
		try{
			image = ImageIO.read(new File(photoFile));
			g.drawImage(image, x, y, 50, 50, null);
		} catch (IOException ex){
			//exception
		}
	}
	public String getName(){
		return name;
	}
}