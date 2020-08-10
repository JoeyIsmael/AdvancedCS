import javax.imageio.ImageIO;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public abstract class Employee{
	private String name;
	private String photoFile;
	private String jobTitle;
	private BufferedImage image;
	public Employee(String name, String photoFile, String jobTitle){
		this.name = name;
		this.photoFile = photoFile;
		this.jobTitle = jobTitle;
	}
	
	public abstract double getSalary();
	public void drawPhoto(Graphics g, int x, int y){
		try{
			image = ImageIO.read(new File(photoFile));
			g.drawImage(image, x, y, 75, 75, null);
			g.drawString(name, x, y + 100);
		} catch (IOException ex){
			//exception
		}
	}
	public String toString(){
		return "Name: " + name + " Job Title: " + jobTitle + " Salary: " + getSalary();
	}
	public String getName(){
		return name;
	}
}