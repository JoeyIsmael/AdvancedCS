public class Student implements Comparable<Student>{
	private String name;
	private int id;
	public Student(String name, int id){
		this.name = name;
		this.id = id;
	}
	public String toString(){
		return name + ":" + id;
	}
	public String getName(){
		return name;
	}
	public int getId(){
		return id;
	}
	public int hashCode(){
		int num1 = name.hashCode();
		int code = 31 * (num1 + id);
		return code;
	}
	public boolean equals(Object o){
		Student s = (Student)o;
		if(s.getName().equals(name) && s.getId() == id){
			return true;
		} else {
			return false;
		}
	}
	public int compareTo(Student s){
		if(s.getName().equals(name)){
			return id - s.getId();
		} else {
			return name.compareTo(s.getName());
		}
	}

}