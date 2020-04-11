import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
 
public class ServerScreen extends JPanel implements ActionListener,MouseListener {
   private int player=2;
   private boolean output=false;
   private boolean win=false;
   private boolean lose=false;
   private boolean draw=false;
   private JButton refresh;
   private int amountLoss=0;
   private int amountWin=0;
    Game grid=new Game();
    ObjectOutputStream out; 
    ObjectInputStream in;
    public ServerScreen(){
         
        this.setLayout(null);
        refresh=new JButton("Replay");
        refresh.setBounds(600,300,70,30);
        refresh.addActionListener(this);
        this.add(refresh);
        addMouseListener(this);
        
        this.setFocusable(true);
    }
    public void playSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("Win.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
    public void playSound2() {
        try {
            URL url = this.getClass().getClassLoader().getResource("Lose.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
    public Dimension getPreferredSize() {
 
        return new Dimension(800,600);
    }
     
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(140,20,140,380);
        g.drawLine(260,20,260,380);
        g.drawLine(20,140,380,140);
        g.drawLine(20,260,380,260);
        int num1=60;
        int num2=28;
        int num3=100;
        int num4=132;
        if(win){
            g.drawString("Player 2 Won",600,100);
        }
        if(lose){
            g.drawString("Player 1 Won",600,100);
        }
        g.drawString("Play Square is:"+grid.playSquareData(),600,200);
        g.drawString("If play square is -1 then you can play anywhere",450,225);
        g.drawString("You are Player 2",600,50);
        g.drawString("It is Player "+grid.playerData()+"'s turn",600,75);
        g.drawString("You have won:"+amountWin,600,350);
        g.drawString("You have lost:"+amountLoss,600,375);
        for(int i=0;i<9;i++){
            g.drawLine(num1,num2,num1,num4);
            g.drawLine(num3,num2,num3,num4);
            
            if(i<2){
                num3+=120;
                num1+=120;
                
            }
            else if (i==2){
                num1=60;
                num3=100;
                num2=148;
                num4=252;
            }
            else if(i<5){
                num3+=120;
                num1+=120;
                //System.out.println(num1);
            }
            else if(i==5){
                num1=60;
                num3=100;
                num2=268;
                num4=372;
            }
            else if(i<10){
                num3+=120;
                num1+=120;

            }
        }
        num1=28;
        num2=60;
        num3=132;
        num4=100;
        for(int i=0;i<9;i++){
            g.drawLine(num1,num2,num3,num2);
            g.drawLine(num1,num4,num3,num4);
            
            if(i<2){
                num2+=120;
                num4+=120;
                
            }
            else if (i==2){
                num2=60;
                num4=100;
                num1=148;
                num3=252;
            }
            else if(i<5){
                num2+=120;
                num4+=120;
                //System.out.println(num1);
            }
            else if(i==5){
                num2=60;
                num4=100;
                num1=268;
                num3=372;
            }
            else if(i<10){
                num4+=120;
                num2+=120;

            }
        }
        for (int i=0;i<9 ;i++ ) {
            for (int r=0;r<3;r++) {
                for(int c=0;c<3;c++){
                    if(grid.checkSquare(r,c,i).equals("O")){
                        g.setColor(Color.RED);
                        int x=30;
                        int y=30;
                        if(i>2){
                            y+=120;
                        }
                        if(i>5){
                            y+=120;
                        }
                        if(i==1||i==4||i==7){
                            x+=120;
                        }
                        if(i==2||i==5||i==8){
                            x+=240;
                        }
                        x+=r*40;
                        y+=c*40;
                        g.fillRect(x,y,20,20);
                    }
                    else if(grid.checkSquare(r,c,i).equals("X")){
                        g.setColor(Color.BLUE);
                        int x=30;
                        int y=30;
                        if(i>2){
                            y+=120;
                        }
                        if(i>5){
                            y+=120;
                        }
                        if(i==1||i==4||i==7){
                            x+=120;
                        }
                        if(i==2||i==5||i==8){
                            x+=240;
                        }
                        x+=r*40;
                        y+=c*40;
                        g.fillRect(x,y,20,20);
                    }
                }
            }
        }
    } 
 
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==refresh){
            if(win||lose){
                grid.refresh();
                win=false;
                lose=false;
                draw=false;
                repaint();
            }

        }
         
    }
    public void mousePressed(MouseEvent e){
        int x=e.getX();
        int y=e.getY();
        int z=0;
        if(x>20&&y>20&&x<380&&y<380){
            int c=(x-20)/40;
            int r=(y-20)/40;
            //System.out.println(c+","+r);
            if(r>=3&&r<6){
                z+=3;
            }
            else if(r>5){
                z+=6;
            }
            if(c>=6){
                z+=2;
            }
            else if(c>=3){
                z+=1;
            }
            //System.out.println(z+"("+c%3+","+r%3+")");
            if(grid.yourTurn(2)&&!win&&!lose){
                if(grid.moveTrue(c%3,r%3,z)){
                    //System.out.println(grid.playSquare());
                    if(-1==grid.playSquare()||z==grid.playSquare()){
                        try{
                            grid.makeMove(c%3,r%3,z);
                            if(grid.checkWin(z)==2){
                                grid.fillSquare(2,z);
                            }
                            if(grid.checkBigWin()==2){
                                win=true;
                                amountWin++;
                                playSound();
                            }
                            if(grid.checkBigFull()){
                                draw=true;
                            }
                            out.reset();
                            out.writeObject(grid);
                            repaint();
                        }
                        catch (IOException f) {
                            System.err.println("Couldn't get I/O for the connection to ");
                            System.exit(1);
                        }
                    }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {}
 
    public void mouseEntered(MouseEvent e) {}
 
    public void mouseExited(MouseEvent e) {}
 
    public void mouseClicked(MouseEvent e) {}
 
    public void poll() throws IOException {
        int portNumber = 1024;
         
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket clientSocket = serverSocket.accept();
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());

		
		try {
 
            while (true) {
                grid=(Game)in.readObject();
                if(grid.checkBigWin()==1){
                    lose=true;
                    amountLoss++;
                    playSound2();
                }
                if(grid.checkBigFull()){
                    draw=true;
                }
                /*
                chatMessage = in.readLine();
				total+="Other:"+chatMessage+"\n";
				output.setText(total);
                repaint();
                */
                repaint();
            }
        } catch (UnknownHostException e) {
            System.err.println("Host unkown: ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Class does not exist" + e);
            System.exit(1);
        }
    }
}
