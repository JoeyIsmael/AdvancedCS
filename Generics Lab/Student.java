import javax.imageio.ImageIO;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Student{
	private String name;
	private String imageFile;
	private BufferedImage image;
	public Student(String name, String imageFile){
		this.name = name;
		this.imageFile = imageFile;
	}
	public void drawPhoto(Graphics g, int x, int y){
		try{
			image = ImageIO.read(new File(imageFile));
			g.drawImage(image, x, y, 120, 120, null);
			g.drawString(name, x, y + 140);
		} catch (IOException ex){
			//exception
		}
	}
	
	public String getName() {
		return name;
	}
	
	
	public String toString(){
		return name;
	}
}