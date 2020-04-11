public class Government extends Employee{
	private String cityName;
	public Government(String name, String photoFile, String jobTitle, String cityName){
		super(name, photoFile, jobTitle);
		this.cityName = cityName;
	}
	public double getSalary(){
		return 100000;
	}
	public String toString(){
		return super.toString() + " City Name: " + cityName;
	}
	public String getName(){
		return super.getName();
	}
}