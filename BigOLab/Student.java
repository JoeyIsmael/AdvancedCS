public class Student{
	private String firstname;
	private String lastname;
	private int age;
	public Student(String firstname, String lastname, int age){
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}
	public String getLast(){
		return lastname;
	}
	public String getFirst(){
		return firstname;
	}
	public String toString(int i){
		return i + "." + lastname + ", " + firstname + " - " + age;
	}
}