import javax.swing.JFrame;
import java.awt.*;

public class Runner{
	public static void main(String[] args){
		JFrame fr = new JFrame("Maps Lab");
		fr.pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		fr.setSize(width/2, height/2);
		int screenDPI = Toolkit.getDefaultToolkit().getScreenResolution();

		fr.setLocationRelativeTo(null);
		
		
		
		Screen sc = new Screen(screenDPI, width/2, height/2);
		
		fr.add( sc );
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fr.setVisible(true);
	}
}
