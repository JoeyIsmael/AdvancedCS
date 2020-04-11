public class Item implements Comparable<Item>{
	private String name;
	private double price;
	public Item(String name, double price){
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public String toString() {
		return name + " : " + price;
	}
	
	public int hashCode() {
		int nameHash = (int)(price*100);
		for(int i = 0; i < name.length(); i++){
			char character = name.charAt(i);
			int ascii = (int) character;
			nameHash = (nameHash*26) + ascii;
		}
        return nameHash;
    }
	public boolean equals(Object o) {
        Item t = (Item)o;
        if( name.equals( t.getName() ) && price == t.getPrice() )
            return true;
        else
            return false;
    }

    public int compareTo(Item s){
		if(name.equals(s.getName()) && price == s.getPrice()){
			return 0;
		}else if(name.compareTo(s.getName()) == 0){
			return ((int)(price-s.getPrice()));
		}else{
			return name.compareTo(s.getName());	
		}
	}
}