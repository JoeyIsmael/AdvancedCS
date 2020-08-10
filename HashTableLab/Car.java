public class Car{
	private String make;
	private String modelName;
	private double price;
	private int year;

	public Car(String make, String modelName, int year, double price){
		this.make = make;
		this.modelName = modelName;
		this.price = price;
		this.year = year;
	}

	public Car(String make) {
		this.make = make;
	}

	public int hashCode(){
		int code = 0;
		int val = 0;
		char[] splitName = make.toLowerCase().toCharArray();
		for(int i = 0; i < 3; i++) {
			val = splitName[i] - 97;
			code += (int)(Math.pow(26,i)) * val;
		}
		return code;
	}
	public String toString(){
		return make + ", " + modelName + ", " + year + ", $" + price;
	}
	public String getMake(){
		return make;
	}

	public void setPrice(double newPrice){
		price = newPrice;
	}
}