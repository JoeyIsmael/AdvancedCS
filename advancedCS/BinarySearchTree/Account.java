import java.io.Serializable;
public class Account implements Comparable<Account>, Serializable{
	private String first;
	private String last;
	private int pin;
	private double balance;
	public Account(String first, String last, int pin, double balance){
		this.first = first;
		this.last = last;
		this.pin = pin;
		this.balance = balance;
	}
	
	public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public double deposit(double num){
    	System.out.println(num);
    	balance = balance + num;
    	return balance;
    }

    public double withdraw(double num){
    	if(balance >= num){
    		balance = balance - num;
    	}
    	return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString(){
    	return last + "," + first;
    }

    public String toString2(){
    	return ("First: " + first + ", " + "Last: " + last + ", " + "Balance: " + balance + ", " + "Pin: " + pin);
    }


	public boolean equals(Object o){
		Account a = (Account)o;
		if(a.getFirst().equals(first) && a.getLast().equals(last) && a.getPin() == pin){
			return true;
		} 
		return false;
	}

	public int compareTo(Account a){
		if(last.equals(a.getLast())){
			return first.compareTo(a.getFirst());
		} else {
			return last.compareTo(a.getLast());
		}
	}
}