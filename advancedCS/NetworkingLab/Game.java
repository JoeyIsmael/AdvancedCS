import java.io.Serializable;

public class Game implements Serializable{
	public int[][] gameArray;
	boolean winner1 = false;
	boolean winner2 = false;
	boolean tie = false;
	public Game(){
		gameArray = new int[3][3];
	}
	public void print(){
		System.out.println();
		for(int r = 0; r<gameArray.length; r++){
			for(int c = 0; c<gameArray[r].length; c++){
				System.out.print(gameArray[r][c] + "\t");
			}
			System.out.println();
		}
	}
	public void clear(){
		winner1 = false;
		winner2 = false;
		tie = false;
		for(int r = 0; r<gameArray.length; r++){
			for(int c = 0; c<gameArray[r].length; c++){
				gameArray[r][c] = 0;
			}
		}	
	}
	public boolean checkTie(){
		for(int r = 0; r<gameArray.length; r++){
			for(int c = 0; c<gameArray[r].length; c++){
				if(gameArray[r][c] == 1 || gameArray[r][c] == 2){
					tie = true;
				}
				else{
					return false;
				}
			}
		}
		return tie;
	}
	public void insertX(int num1, int num2){
		if( gameArray[num1][num2] == 0){
			gameArray[num1][num2] = 1;
		}
		print();
	}
	public void insertO(int num1, int num2){
		if( gameArray[num1][num2] == 0){
			gameArray[num1][num2] = 2;
		}
		print();
	}
	public void checkTicTacToe(int turn){
		if(turn%2 == 0){
			if(gameArray[0][0] == 1 && gameArray[0][1] == 1 && gameArray[0][2] == 1){
				System.out.println("X Wins");
				whenXWon();
				winner1 = true;
			}
			if(gameArray[0][0] == 1 && gameArray[1][0] == 1 && gameArray[2][0] == 1){
				System.out.println("X Wins");
				whenXWon();
				winner1 = true;
			}
			if(gameArray[0][0] == 1 && gameArray[1][1] == 1 && gameArray[2][2] == 1){
				System.out.println("X Wins");
				whenXWon();
				winner1 = true;
			}
			if(gameArray[0][1] == 1 && gameArray[1][1] == 1 && gameArray[2][1] == 1){
				System.out.println("X Wins");
				whenXWon();
				winner1 = true;
			}
			if(gameArray[0][2] == 1 && gameArray[1][2] == 1 && gameArray[2][2] == 1){
				System.out.println("X Wins");
				whenXWon();
				winner1 = true;
			}
			if(gameArray[1][0] == 1 && gameArray[1][1] == 1 && gameArray[1][2] == 1){
				System.out.println("X Wins");
				whenXWon();
				winner1 = true;
			}
			if(gameArray[2][0] == 1 && gameArray[2][1] == 1 && gameArray[2][2] == 1){
				System.out.println("X Wins");
				whenXWon();
				winner1 = true;
			}
			if(gameArray[2][0] == 1 && gameArray[1][1] == 1 && gameArray[0][2] == 1){
				System.out.println("X Wins");
				whenXWon();
				winner1 = true;
			}
		}
		if(turn%2 == 1){
			if(gameArray[0][0] == 2 && gameArray[0][1] == 2 && gameArray[0][2] == 2){
				System.out.println("O Wins");
				whenOWon();
				winner2 = true;
			}
			if(gameArray[0][0] == 2 && gameArray[1][0] == 2 && gameArray[2][0] == 2){
				System.out.println("O Wins");
				whenOWon();
				winner2 = true;
			}
			if(gameArray[0][0] == 2 && gameArray[1][1] == 2 && gameArray[2][2] == 2){
				System.out.println("O Wins");
				whenOWon();
				winner2 = true;
			}
			if(gameArray[0][1] == 2 && gameArray[1][1] == 2 && gameArray[2][1] == 2){
				System.out.println("O Wins");
				whenOWon();
				winner2 = true;
			}
			if(gameArray[0][2] == 2 && gameArray[1][2] == 2 && gameArray[2][2] == 2){
				System.out.println("O Wins");
				whenOWon();
				winner2 = true;
			}
			if(gameArray[1][0] == 2 && gameArray[1][1] == 2 && gameArray[1][2] == 2){
				System.out.println("O Wins");
				whenOWon();
				winner2 = true;
			}
			if(gameArray[2][0] == 2 && gameArray[2][1] == 2 && gameArray[2][2] == 2){
				System.out.println("O Wins");
				whenOWon();
				winner2 = true;
			}
			if(gameArray[2][0] == 2 && gameArray[1][1] == 2 && gameArray[0][2] == 2){
				System.out.println("O Wins");
				whenOWon();
				winner2 = true;
			}
		}
	}
	public boolean whenXWon(){
		return winner1;
	}
	public boolean whenOWon(){
		return winner2;
	}
}
