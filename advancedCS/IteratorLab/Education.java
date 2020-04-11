public class Education{
	private String school;
	private int year;
	public Education(String school, int year){
		this.school = school;
		this.year = year;
	}
	public String getString(String slicedyear){
		return school + " " + slicedyear;
	}
	public int getYear(){
		return year;
	}
}