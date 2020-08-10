public class Job{
	private String title;
	private String companyName;
	private int year;
	public Job(String title, int year, String companyName){
		this.title = title;
		this.year = year;
		this.companyName = companyName;
	}
	public String getString(String slicedYear){
		return title + " " + companyName + " " + slicedYear;
	}
	public int getYear(){
		return year;
	}
}