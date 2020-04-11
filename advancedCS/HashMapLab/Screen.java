import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.*;
import java.net.MalformedURLException;

public class Screen extends JPanel implements ActionListener{
//Change the map2 to String, Country maybe
	private TextArea countryDisplay;
	HashMap<Country, MyImage> map;
	HashMap<String, String> map2;
	private JButton getButton;
	private JButton forward;
	private JButton back;
	private JButton delete;
	private JTextField abreviation;
	private JButton addPhoto;
	private JTextField abrevationName;
	private JTextField landmarkName;
	private JTextField urlName;
	DLList<MyImage> sImages;
	String url = null;
	String currentCountry = "";
	String landmark = null;
	int location = 0;
	int currentSize;

	public Screen(){
		this.setLayout(null);
		this.setFocusable(true);
		map = new HashMap<Country, MyImage>();
		map2 = new HashMap<String, String>();
		countryDisplay = new TextArea();
		countryDisplay.setBounds(10, 50, 300, 400);
		add(countryDisplay);
		countryDisplay.setVisible(true);

		abreviation = new JTextField(20);
        abreviation.setBounds(350,60,100,30);
        this.add(abreviation);

        abrevationName = new JTextField(20);
        abrevationName.setBounds(50,500,100,30);
        this.add(abrevationName);

        landmarkName = new JTextField(20);
        landmarkName.setBounds(150,500,100,30);
        this.add(landmarkName);

        urlName = new JTextField(20);
        urlName.setBounds(250,500,100,30);
        this.add(urlName);

		getButton = new JButton("Get Country");
        getButton.setBounds(350,100, 100, 30);
        getButton.addActionListener(this);
        this.add(getButton);

        addPhoto = new JButton("Add Photo");
        addPhoto.setBounds(50,550, 100, 30);
        addPhoto.addActionListener(this);
        this.add(addPhoto);

        forward = new JButton("Next");
        forward.setBounds(600,250,80,20);
        forward.addActionListener(this);
        this.add(forward);
        forward.setVisible(false);

        back = new JButton("Back");
        back.setBounds(600,280,80,20);
        back.addActionListener(this);
        this.add(back);
        back.setVisible(false);

        delete = new JButton("Delete");
        delete.setBounds(600,310, 80, 20);
        delete.addActionListener(this);
        this.add(delete);
        delete.setVisible(false);
        ScanFile();
	}

	public void writeToFile() {
        try {
            FileOutputStream fstream = new FileOutputStream("HashMapSave.jobj");
            ObjectOutputStream oStream = new ObjectOutputStream(fstream);
            oStream.writeObject(map);
            oStream.close();
        } catch (IOException err) {
            System.out.println(err.toString());
        }
    }

	public void ScanFile(){
		try{
			Scanner scan = new Scanner(new File("countries.txt"));
			while(scan.hasNextLine()){
				String input = scan.nextLine();
				String abr = input.substring(0,input.indexOf(","));
				String name = input.substring(input.indexOf(",")+1, input.length());
				map.put(new Country(abr, name), new MyImage(null, null));
				map2.put(abr, name);
			}
		} catch (FileNotFoundException err) {
            System.out.println(err);
        }
        /*if(1 == 2){
        	System.out.println();
        }*/	
		if ((new File("HashMapSave.jobj")).exists()) {
        	try {
                FileInputStream fin = new FileInputStream("HashMapSave.jobj");
                ObjectInputStream ois = new ObjectInputStream(fin);
                map = (HashMap<Country, MyImage>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException err) {
                System.out.println(err.toString());
            }
    	} 	
    	else {
			map.put(new Country("es", "Spain"), new MyImage("https://lp-cms-production.imgix.net/2019-06/73acceaafa620817a58081310e91e479-spain.jpeg?fit=crop&q=40&sharp=10&vib=20&auto=format&ixlib=react-8.6.4", "Alhambra"));
			map.put(new Country("es", "Spain"), new MyImage("https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/%CE%A3%CE%B1%CE%B3%CF%81%CE%AC%CE%B4%CE%B1_%CE%A6%CE%B1%CE%BC%CE%AF%CE%BB%CE%B9%CE%B1_2941.jpg/1200px-%CE%A3%CE%B1%CE%B3%CF%81%CE%AC%CE%B4%CE%B1_%CE%A6%CE%B1%CE%BC%CE%AF%CE%BB%CE%B9%CE%B1_2941.jpg", "La Sagrada Familia"));
			//map.put(new Country("es", "Spain"), new MyImage("https://lh3.googleusercontent.com/proxy/3fIxq7JBM9osWulKb-ah-m7jApPI7BdbGPJGTTK5L7hKtmbDMcoyA36C4o3dOIFbIgbVdSNgvNnb28WkaoykbSUykMOwzsFMBhW-P07nnGJtk_zzC3gkj_fvHoj-6suSlRn9sqvFkOwo_Lu40s29qvMkSHKsPca8S1Bggm3Mzy40=w592-h404-n-k-no", "Casa Mila"));
			map.put(new Country("es", "Spain"), new MyImage("https://live.staticflickr.com/6216/6279935694_0df921ec27_b.jpg", "Casa Mila"));
			map.put(new Country("uk", "United Kingdom"), new MyImage("https://cdn7.dissolve.com/p/D246_47_011/D246_47_011_1200.jpg","Tower of London"));
			map.put(new Country("uk", "United Kingdom"), new MyImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSurS8JElJfLPzk-jLGHbTpYKixW0JUFYf-Rm8rjZabMfAi-I-Z&s","Stonehedge"));
			map.put(new Country("us", "United States"), new MyImage("https://cdn.vox-cdn.com/thumbor/_5wCc2Fit8apEioStEg5s4MjUyE=/0x0:2000x1333/1200x800/filters:focal(840x507:1160x827)/cdn.vox-cdn.com/uploads/chorus_image/image/62336589/120907_12_20_05_IMG_3042.0.jpg","Statue of Liberty"));
			map.put(new Country("us", "United States"), new MyImage("https://www.knowablemagazine.org/sites/default/files/styles/1600_600/public/articles/212/grand-canyon-1600x600.jpg?itok=ypnuF9fR","Grand Canyon"));
			map.put(new Country("us", "United States"), new MyImage("https://sgl-assets.imgix.net/files/article_hero/yosemite-glacier-point-sunset-national-park-summer-activities-things-to-do-via-magazine-shutterstock_552174034.jpg?w=1440&h=720&crop=faces,edges","Yosemite National Park"));
			map.put(new Country("uk", "United Kingdom"), new MyImage("https://www.thetimes.co.uk/imageserver/image/%2Fmethode%2Ftimes%2Fprod%2Fweb%2Fbin%2F7023ff3c-a356-11e9-a6f9-8847b0c7b91c.jpg?crop=2931%2C4396%2C416%2C467", "Big Ben"));
        }
	}

	public void setCountryDisplay(){
		countryDisplay.setText("");
		DLList<Country> textList = new DLList<>();
        DLList<Country> keys = map.getKeys();
        for (int i = 0; i < keys.size(); i++) {
            if ((map.get(keys.get(i)).size() - 1) > 0){
            	textList.add(keys.get(i));
            } 
        }
        String text = "";
        for (int i = 0; i < textList.size(); i++)
            text = text + textList.get(i).toString() + " - " + (map.get(textList.get(i)).size() - 1) + "\n";
		
		countryDisplay.append(text);

	}

	public Dimension getPreferredSize(){
		return new Dimension(800,800);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString("Abreviation: ", 50, 490);
		g.drawString("Landmark: ", 150, 490);
		g.drawString("Url: ", 250, 490);

		g.drawString("Enter in country abreviation:", 350, 50);
		g.drawString("Country List", 10, 40);
		g.drawString("Current Country: " + currentCountry, 350, 200);
		setCountryDisplay();

		if(url != null){
			try {
	        	URL link = new URL(url);
	            Image image = ImageIO.read(link);
	            g.drawImage( image , 350, 220, 200, 200, null);
        	} catch (IOException e) {
            	System.out.println(e);
        	}
		}
		if(landmark != null){
			g.drawString(landmark, 400, 450);
		}

		writeToFile();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == getButton){
			location = 1;
			String abr = abreviation.getText();
			DLList<String> countries = map2.get(abr);
			Country c = new Country(abr, countries.get(0));
			currentCountry = c.toString();
			back.setVisible(true);
			forward.setVisible(true);
			delete.setVisible(true);
			if(map.get(c) != null){
				sImages = map.get(c);
				currentSize = sImages.size();
			}
			if(currentSize > 1){
				url = sImages.get(location).getUrl();
				landmark = sImages.get(location).getLandmark();
				//System.out.println(list.toString());
			}
		}
		if(e.getSource() == forward && location < (currentSize - 1)){
			location++;
			url = sImages.get(location).getUrl();
			landmark = sImages.get(location).getLandmark();
		}
		if(e.getSource() == back && location > 1){
			location--;
			url = sImages.get(location).getUrl();
			landmark = sImages.get(location).getLandmark();
		}
		if(e.getSource() == delete){
			url = null;
			landmark = null;
			if(sImages.size() - 1 > 0){
				sImages.remove(location);
				if(location > 0){
					location--;
				}
				if(location == 0){
					location++;
				}
				if(location < (currentSize - 1) && location > 0){
					url = sImages.get(location).getUrl();
					landmark = sImages.get(location).getLandmark();
					currentSize = sImages.size();
				}
			}
			
		}
		if(e.getSource() == addPhoto){
			String urlText = urlName.getText();
			String landmarkText = landmarkName.getText();
			String abrevationText = abrevationName.getText();
			String countryText = map2.get(abrevationText).get(0);
			map.put(new Country(abrevationText, countryText), new MyImage(urlText, landmarkText));
			urlName.setText("");
			landmarkName.setText("");
			abrevationName.setText("");
		}
		repaint();
	}
}