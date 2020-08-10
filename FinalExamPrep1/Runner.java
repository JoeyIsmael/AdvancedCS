import java.util.*;
public class Runner{
	public static void main(String[] args){
		Practice p1 = new Practice();

		p1.addStudent(new Student("Jose", 1234), "MVHS");
		p1.addStudent(new Student("Zelda", 4321), "MVHS");
		p1.addStudent(new Student("Arthur", 1111), "LAHS");
		p1.addStudent(new Student("Jose", 1234), "LAHS");
		p1.addStudent(new Student("Manuel", 9876), "MVHS");

		p1.printHashandTreeLists();
		p1.removeStudent(new Student("Jose", 1234));
		p1.printHashandTreeLists();
		p1.getSchool(new Student("Jose", 1234));
		p1.getSchool(new Student("Jose", 1233));
	}
}