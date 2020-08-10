import java.util.*;
import java.awt.*;
import java.io.Serializable;
public class Game implements Serializable{
	private ArrayList<String[][]> large;
	private int player=1;
	private int lastX;
	private int lastY;
	private boolean normal;
	private boolean firstTurn;
	public Game(){
		large=new ArrayList<String[][]>();
		for (int i=0;i<9 ;i++ ) {
			large.add(new String[3][3]);
		}
		for (int i=0;i<large.size() ;i++ ){
			for(int j=0;j<large.get(i).length;j++){
				for(int k=0;k<large.get(i).length;k++){
					large.get(i)[j][k]="blank";
				}
			}
		}
		normal=true;
		firstTurn=true;
	}
	public boolean moveTrue(int x,int y,int z){
		return large.get(z)[x][y].equals("blank");
	}
	public boolean yourTurn(int x){
		if(player==x){
			return true;
		}
		else
			return false;
	}
	public int playerData(){
		return player;
	}
	public void makeMove(int x,int y,int z){
		lastX=x;
		lastY=y;
		if (player==1) {
			player=2;
			large.get(z)[x][y]="X";
		}
		else{
			player=1;
			large.get(z)[x][y]="O";
		}
		
	}
	public int playSquare(){
		//System.out.println(normal);
		//System.out.println(firstTurn);
		int temp=playSquareData();
		if(temp==-2){}
		else if(temp==-1){
			return -1;
		}
		if(firstTurn){
			firstTurn=false;
			normal=true;
			return -1;
		}
		if(normal){
			int answer=0;
			answer+=lastX;
			answer+=lastY*3;
			return answer;
		}
		
		if(!normal){
			normal=true;
			return -1;
		}
		return -2;
	}
	public void refresh(){
		for(int i=0;i<9;i++){
			for(int r=0;r<3;r++){
				for(int c=0;c<3;c++){
					large.get(i)[r][c]="blank";
				}
			}
		}
		firstTurn=true;
	}
	public int playSquareData(){
		if(normal){
			int answer=0;
			answer+=lastX;
			answer+=lastY*3;
			if(checkFull(answer)){
				//System.out.println("jksjkn");
				return -1;
			}
			return answer;
		}
		if(firstTurn){
			firstTurn=false;
			//normal=true;
			//System.out.println(normal);
			//System.out.println("jksjkn");
			return -1;
		}
		if(!normal){
			//normal=true;
			System.out.println("jksjkn");
			return -1;
		}
		return -2;
	}
	public String checkSquare(int x,int y,int z){
		return large.get(z)[x][y];
	}
	public boolean checkFull(int z){
		boolean check=true;
		for(int r=0;r<3;r++){
			for(int c=0;c<3;c++){
				if(large.get(z)[r][c].equals("blank")){
					check=false;
				}
			}
		}
		return check;
	}
	public boolean checkBigFull(){
		boolean answer=true;
		for(int i=0;i<9;i++){
			if(!checkFull(i)){
				answer=false;
			}
		}
		return answer;
	}
	public boolean checkFullData(String x,int z){
		boolean check=true;
		for(int r=0;r<3;r++){
			for(int c=0;c<3;c++){
				if(!large.get(z)[r][c].equals(x)){
					check=false;
				}
			}
		}
		return check;
	}
	public void fillSquare(int p,int z){
		for(int r=0;r<3;r++){
			for(int c=0;c<3;c++){
				if(p==1){
					large.get(z)[r][c]="X";
				}
				if(p==2){
					large.get(z)[r][c]="O";
				}
			}
		}
	}
	public int checkBigWin(){
		if(checkFullData("X",0)){
			if(checkFullData("X",1)){
				if(checkFullData("X",2)){
					return 1;
				}
			}
			if(checkFullData("X",4)){
				if(checkFullData("X",8)){
					return 1;
				}
			}
			if(checkFullData("X",3)){
				if(checkFullData("X",6)){
					return 1;
				}
			}
		}
		if(checkFullData("X",1)){
			if(checkFullData("X",4)){
				if(checkFullData("X",7)){
					return 1;
				}
			}
		}
		if(checkFullData("X",2)){
			if(checkFullData("X",5)){
				if(checkFullData("X",8)){
					return 1;
				}
			}
			if(checkFullData("X",4)){
				if(checkFullData("X",6)){
					return 1;
				}
			}
		}
		if(checkFullData("X",3)){
			if(checkFullData("X",4)){
				if(checkFullData("X",5)){
					return 1;
				}
			}
		}
		if(checkFullData("X",6)){
			if(checkFullData("X",7)){
				if(checkFullData("X",8)){
					return 1;
				}
			}
		}
		//CHECKPOINT
		if(checkFullData("O",0)){
			if(checkFullData("O",1)){
				if(checkFullData("O",2)){
					return 2;
				}
			}
			if(checkFullData("O",4)){
				if(checkFullData("O",8)){
					return 2;
				}
			}
			if(checkFullData("O",3)){
				if(checkFullData("O",6)){
					return 2;
				}
			}
		}
		if(checkFullData("O",1)){
			if(checkFullData("O",4)){
				if(checkFullData("O",7)){
					return 2;
				}
			}
		}
		if(checkFullData("O",2)){
			if(checkFullData("O",5)){
				if(checkFullData("O",8)){
					return 2;
				}
			}
			if(checkFullData("O",4)){
				if(checkFullData("O",6)){
					return 2;
				}
			}
		}
		if(checkFullData("O",3)){
			if(checkFullData("O",4)){
				if(checkFullData("O",5)){
					return 2;
				}
			}
		}
		if(checkFullData("O",6)){
			if(checkFullData("O",7)){
				if(checkFullData("O",8)){
					return 2;
				}
			}
		}
		return 0;
	}

	public int checkWin(int z){
		if(large.get(z)[0][0].equals("X")){
			if(large.get(z)[1][1].equals("X")){
				if (large.get(z)[2][2].equals("X")) {
					return 1;
				}
			}
			if(large.get(z)[0][1].equals("X")){
				if(large.get(z)[0][2].equals("X")){
					return 1;
				}
			}
			if(large.get(z)[1][0].equals("X")){
				if(large.get(z)[2][0].equals("X")){
					return 1;
				}
			}
		}
		if(large.get(z)[0][1].equals("X")){
			if(large.get(z)[1][1].equals("X")){
				if(large.get(z)[2][1].equals("X")){
					return 1;
				}
			}
		}
		if(large.get(z)[0][2].equals("X")){
			if(large.get(z)[1][1].equals("X")){
				if(large.get(z)[2][0].equals("X")){
					return 1;
				}
			}
			if(large.get(z)[1][2].equals("X")){
				if(large.get(z)[2][2].equals("X")){
					return 1;
				}
			}
		}
		if(large.get(z)[1][0].equals("X")){
			if(large.get(z)[1][1].equals("X")){
				if(large.get(z)[1][2].equals("X")){
					return 1;
				}
			}
		}
		if(large.get(z)[2][0].equals("X")){
			if(large.get(z)[2][1].equals("X")){
				if(large.get(z)[2][2].equals("X")){
					return 1;
				}
			}
		}
		//CHECKPOINT
		if(large.get(z)[0][0].equals("O")){
			if(large.get(z)[1][1].equals("O")){
				if (large.get(z)[2][2].equals("O")) {
					return 2;
				}
			}
			if(large.get(z)[0][1].equals("O")){
				if(large.get(z)[0][2].equals("O")){
					return 2;
				}
			}
			if(large.get(z)[1][0].equals("O")){
				if(large.get(z)[2][0].equals("O")){
					return 2;
				}
			}
		}
		if(large.get(z)[0][1].equals("O")){
			if(large.get(z)[1][1].equals("O")){
				if(large.get(z)[2][1].equals("O")){
					return 2;
				}
			}
		}
		if(large.get(z)[0][2].equals("O")){
			if(large.get(z)[1][1].equals("O")){
				if(large.get(z)[2][0].equals("O")){
					return 2;
				}
			}
			if(large.get(z)[1][2].equals("O")){
				if(large.get(z)[2][2].equals("O")){
					return 2;
				}
			}
		}
		if(large.get(z)[1][0].equals("O")){
			if(large.get(z)[1][1].equals("O")){
				if(large.get(z)[1][2].equals("O")){
					return 2;
				}
			}
		}
		if(large.get(z)[2][0].equals("O")){
			if(large.get(z)[2][1].equals("O")){
				if(large.get(z)[2][2].equals("O")){
					return 2;
				}
			}
		}
		return 0;
	}
}