public class Bank{
	
	public Account[] accounts;
	
	public Bank(Account[] accounts){
		this.accounts = accounts;
	}
	
	public Account lookupAccountByPin(int pinInput, String name)
	{
		for (int i=0; i< accounts.length; i++) {
			if(accounts[i].name == name) {
				return accounts[i];
			}
		}
		
		return null;
	}
	
		
	/*public void withdraw(CustomerAccount customerAcount, double amount){
		if(customerAcount.balance >= amount) {
			customerAcount.balance = customerAcount.balance - amount;
		}		
	}
	
	public void deposit(CustomerAccount customerAcount, double amount){
		customerAcount.balance = customerAcount.balance + amount;
	}
	
	public void updateName(CustomerAccount customerAcount, String newName){
		customerAcount.name = newName;
	}*/
}
