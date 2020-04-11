import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class Screen extends JPanel implements ActionListener, MouseListener{

	private final int fontHeight;
	private final int screenDPI;
	private final int width;
	private final int height;
	
	private Stack<Square[][]> s = new Stack<Square[][]>();
	private Stack<Square[][]> s2 = new Stack<Square[][]>();
	private Square[][] grid;
	private JButton clearButton;
	private JButton undoButton;
	private JButton redo;
	private Square[][] palette;
	private Color currentColor = Color.WHITE;
	private Color[] colors = {new Color(255,0,0),
		new Color(0,255,0),
		new Color(0, 0, 255),
		new Color(255, 0, 255),
		new Color(255, 255, 0),
		new Color(0, 255, 255),
		new Color(255, 255, 255),
		new Color(128, 0, 128),
		new Color(0, 128, 0),
		new Color(128, 128, 128),
		new Color(128, 128, 0),
		new Color(0, 128, 128),
		new Color(128, 0, 0),
		new Color(0, 0, 128),
		new Color(131, 209, 194),
		new Color(252, 75, 175),
		new Color(0, 0, 0),
		new Color(183, 119, 113),
		new Color(237, 11, 109),
		new Color(239, 180, 247),
		new Color(61, 34, 94),
		new Color(0, 128, 0),
		new Color(157, 115, 209),
		new Color(114, 0, 255),
		new Color(14, 58, 114),
		new Color(182, 249, 212),
		new Color(106, 242, 167),
		new Color(79, 56, 3),
		new Color(157, 255, 0),
		new Color(224, 26, 92),
		new Color(204, 81, 65),
		new Color(242, 244, 193),
		new Color(127, 132, 26),
		new Color(22, 61, 124),
		new Color(66, 134, 244),
		new Color(255, 131, 0),
		};

	private int toScreenDots(double dimInches) {
		double temp = dimInches * screenDPI;
		System.out.println("dimInches = " + screenDPI);
		return (int) (dimInches * screenDPI);
	}

	
	public Screen(int screenDPI, int width, int height){
		this.setLayout(null);
		this.setFocusable(true);
		this.screenDPI = screenDPI;
		this.width = width;
		this.height = height;
		setLayout(null);
		//focuses on button
		setFocusable(true);
		grid = new Square[16][16];
		for(int r = 0; r<grid.length; r++){
			for(int c = 0; c<grid[r].length; c++){
				int x = c * 40 + 275;
				int y = r * 40 + 50;
				grid[r][c] = new Square(Color.WHITE, x, y);
			}
		}
		palette = new Square[18][2];
		for(int i = 0; i<palette.length; i++){
			for(int j = 0; j<palette[i].length; j++){
				int x = 50 + 40 * j;
				int y = 25 + 40 * i;
				palette[i][j] = new Square(colors[i*palette[i].length+j], x, y);
			}
		}
		addMouseListener(this);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(1100,100,250,60);
		clearButton.addActionListener(this);
		this.add(clearButton);

		undoButton = new JButton("Undo");
		undoButton.setBounds(1100,200,250,60);
		undoButton.addActionListener(this);
		this.add(undoButton);

		redo = new JButton("Redo");
		redo.setBounds(1100,300,250,60);
		redo.addActionListener(this);
		this.add(redo);

		fontHeight = (int) (0.1 * screenDPI);
		setFont(new Font("Verdana", Font.BOLD, fontHeight));
		
	}
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(2700,2500);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(int i = 0; i<palette.length; i++){
			for(int j = 0; j<palette[i].length; j++){
				palette[i][j].drawMe(g);
			}
		}
		
		for(int r = 0; r<grid.length; r++){
			for(int c = 0; c<grid[r].length; c++){
				grid[r][c].drawMe(g);
			}
		}
	}	
	public void mousePressed(MouseEvent e){
		for(int i = 0; i<palette.length; i++){
			for(int j = 0; j<palette[i].length; j++){
				if(palette[i][j].hit(e.getX(), e.getY())){
					currentColor = palette[i][j].getColor();
					repaint();
					return;
				}
			}
		}
		for(int r = 0; r<grid.length; r++){
			for(int c = 0; c<grid[r].length; c++){
				if(grid[r][c].hit(e.getX(), e.getY())){
					Square[][] newGrid = new Square[16][16];
					for(int x = 0; x<newGrid.length; x++){
						for(int y = 0; y<newGrid[x].length; y++){
							newGrid[x][y] = new Square(grid[x][y].getColor(), grid[x][y].getX(), grid[x][y].getY());
						}
					}
					s.push(newGrid);
					grid[r][c].changeColor(currentColor);
					repaint();
					return;
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e){
	}
	public void mouseEntered(MouseEvent e){
	}
	public void mouseExited(MouseEvent e){
		
	}
	public void mouseClicked(MouseEvent e){
		
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == clearButton){
			currentColor = Color.WHITE;
			Square[][] newGrid = new Square[16][16];
			for(int x = 0; x<newGrid.length; x++){
				for(int y = 0; y<newGrid[x].length; y++){
					newGrid[x][y] = new Square(grid[x][y].getColor(), grid[x][y].getX(), grid[x][y].getY());
				}
			}
			s.push(newGrid);
			for(int r = 0; r<grid.length; r++){
				for(int c = 0; c<grid[r].length; c++){
					grid[r][c].changeColor(currentColor);
				}
			}
		}
		if(e.getSource() == undoButton){
			if(s.size() == 0){
				return;
			}
			Square[][] newGrid = new Square[16][16];
			for(int x = 0; x<newGrid.length; x++){
				for(int y = 0; y<newGrid[x].length; y++){
					newGrid[x][y] = new Square(grid[x][y].getColor(), grid[x][y].getX(), grid[x][y].getY());
				}
			}
			s2.push(newGrid);
			Square[][] poppedGrid = s.pop();
			System.out.println(poppedGrid[0][0].getColor());
			for(int r = 0; r<poppedGrid.length; r++){
				for(int c = 0; c<poppedGrid[r].length; c++){
					grid[r][c] = new Square(poppedGrid[r][c].getColor(), poppedGrid[r][c].getX(), poppedGrid[r][c].getY());
				}
			}
			System.out.println(s2.size());
		}
		if(e.getSource() == redo){
			if(s2.size() == 0){
				return;
			}
			Square[][] newGrid = new Square[16][16];
			for(int x = 0; x<newGrid.length; x++){
				for(int y = 0; y<newGrid[x].length; y++){
					newGrid[x][y] = new Square(grid[x][y].getColor(), grid[x][y].getX(), grid[x][y].getY());
				}
			}
			s.push(newGrid);
			System.out.println("Hello");
			Square[][] poppedGrid = s2.pop();
			for(int r = 0; r<poppedGrid.length; r++){
				for(int c = 0; c<poppedGrid[r].length; c++){
					grid[r][c] = new Square(poppedGrid[r][c].getColor(), poppedGrid[r][c].getX(), poppedGrid[r][c].getY());
				}
			}
		}
		repaint();
	}
}