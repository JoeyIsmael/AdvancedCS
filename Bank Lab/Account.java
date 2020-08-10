public class Account{
	
	public String name;
	public double balance;
	public int pin;
	public boolean access = false;
	
	public Account(String name, double balance, int pin){
		this.name = name;
		this.balance = balance;
		this.pin = pin;
	}
	public String getName(){
		return name;
	}
	public double getBalance(){
		if(access){
			return balance;
		}
		return 0;
	}
	public boolean getAccess(){
		return access;
	}
	public void setAccess(int pinEntered, String nameEntered){
		if(pinEntered == pin && nameEntered.equals(name)){
			access = true;
		}
	}
	public void withdraw(double amount){
		if(access){
			if(balance >= amount){
				balance = balance - amount;
			}
		}
	}	
	public void deposit(double amount){
		if(access){
			balance = balance + amount;
		}
	}
	public void logout(){
		access = false;
	}
	public void changeName(String newName){
		if(access){
			name = newName;
		}
	}
	public void changePin(int newPin){
		if(access){
			pin = newPin;
		}
	}
}