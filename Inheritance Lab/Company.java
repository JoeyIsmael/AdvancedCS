public class Company extends Employee{
	private String companyName;
	public Company(String name, String photoFile, String jobTitle, String companyName){
		super(name, photoFile, jobTitle);
		this.companyName = companyName;
	}
	public double getSalary(){
		return 200000;
	}
	public String toString(){
		return super.toString() + " Company Name: " + companyName;
	}
}
