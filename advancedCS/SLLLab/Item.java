public class Item{
	private String name;
	private double price;
	private int quantity;
	private int time;
	public Item(String name, double price, int quantity, int time){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.time = time;
	}

	public String getName(){
		return name;
	}

	public double getPrice(){
		return price;
	}

	public int getQuantity(){
		return quantity;
	}

	public void updateQuantity(){
		quantity++;
	}

	public int getTime(){
		return time;
	}

	public void setTime(int newTime){
		time = newTime;
	}

	public String toString(){
		return name + " : " + price + " : " + quantity;
	}

	public boolean equals(Object o){
		Item i = (Item)o;
		if(name.equals(i.getName()) && price == i.getPrice()){
			return true;
		} else {
			return false;
		}
	}
}