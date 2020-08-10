import java.util.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
public class Game implements Serializable{

	private HashMap<Location, Item> gameItems;
	Player player1 = new Player(new Location(0,0),"player1.png", "Player1");
	Player player2 = new Player(new Location(0,0),"player2.png", "Player2");
	int player1Diamonds = 0;
	int player2Diamonds = 0;
	int numDiamonds = 11;
	Stack<Integer> player1Stack = new Stack<Integer>();
	Stack<Integer> player2Stack = new Stack<Integer>();
	public Game(){
		player1Stack.push(1);
		player1Stack.push(2);
		player1Stack.push(3);
		player2Stack.push(1);
		player2Stack.push(2);
		player2Stack.push(3);
		gameItems = new HashMap<Location, Item>();
		int count = 0;
    	int fCount = 0;
    	int tCount = 0;
    	int rCount = 0;
    	int pCount = 0;
    	while(fCount<=9){
    		Location l = new Location((int)(Math.random() * 9), (int)(Math.random() * 9));
    		if(gameItems.get(l) == null && !l.equals(new Location(0,0))){
    			gameItems.put(l, new Item("fire.png", "Fire"));
    			fCount++;
    		}
    	}
    	while(count<=10){
    		Location l = new Location((int)(Math.random() * 9), (int)(Math.random() * 9));
    		if(gameItems.get(l) == null && !l.equals(new Location(0,0))){
    			gameItems.put(l, new Item("diamond.png", "Coin"));
    			count++;
    		}
    	}
    	while(tCount <= 1){
    		Location l = new Location((int)(Math.random() * 9), (int)(Math.random() * 9));
    		if(gameItems.get(l) == null && !l.equals(new Location(0,0))){
    			gameItems.put(l, new Item("tree.png", "Landscape"));
    			tCount++;
    		}
    	}
    	while(rCount <= 1){
    		Location l = new Location((int)(Math.random() * 9), (int)(Math.random() * 9));
    		if(gameItems.get(l) == null && !l.equals(new Location(0,0))){
    			gameItems.put(l, new Item("rock.png", "Landscape"));
    			rCount++;
    		}
    	}
    	while(pCount <= 1){
    		Location l = new Location((int)(Math.random() * 9), (int)(Math.random() * 9));
    		if(gameItems.get(l) == null && !l.equals(new Location(0,0))){
    			gameItems.put(l, new Item("pond.png", "Landscape"));
    			pCount++;
    		}
    	}
	}
	public HashMap<Location, Item> getGameItems(){
		return gameItems;
	}
	public int getPlayer1XLocation(){
		return player1.getXLocation();
	}
	public int getPlayer1YLocation(){
		return player1.getYLocation();
	}
	public int getPlayer2XLocation(){
		return player2.getXLocation();
	}
	public Location getPlayer2Location(){
		return player2.getLocation();
	}
	public Location getPlayer1Location(){
		return player1.getLocation();
	}
	public int getPlayer2YLocation(){
		return player2.getYLocation();
	}
	public Player getPlayer1(){
		return player1;
	}
	public Player getPlayer2(){
		return player2;
	}
	public void setPlayer1Diamonds(){
		player1Diamonds++;
	}
	public void setPlayer2Diamonds(){
		player2Diamonds++;
	}
	public int getPlayer1Diamonds(){
		return player1Diamonds;
	}
	public int getPlayer2Diamonds(){
		return player2Diamonds;
	}
	public void removeHealth1(){
		player1Stack.pop();
	}
	public void removeHealth2(){
		player2Stack.pop();
	}
	public Stack getPlayer1Stack(){
		return player1Stack;
	}
	public Stack getPlayer2Stack(){
		return player2Stack;
	}
	public void removeDiamond(){
		numDiamonds--;
	}
	public int getDiamond(){
		return numDiamonds;
	}
}