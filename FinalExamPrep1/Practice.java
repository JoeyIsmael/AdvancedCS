import java.util.*;
public class Practice{
	private Set<Student> hashSet; 
	private Set<Student> treeSet; 
	private Map<Student, String> hashMap; 
	private Map<Student, String> treeMap;
	private PriorityQueue<Student> priorityqueue; 
	private Stack<Student> stack; 

	public Practice(){
		hashSet = new HashSet<Student>();
		treeSet = new TreeSet<Student>();
		hashMap = new HashMap<Student, String>();
		treeMap = new TreeMap<Student, String>();
		priorityqueue = new PriorityQueue<Student>();
		stack = new Stack<Student>();
	}
	public void addStudent(Student s, String school){
		hashSet.add(s);
		treeSet.add(s);
		hashMap.put(s, school);
		treeMap.put(s, school);
		priorityqueue.add(s);
		stack.push(s);
	}
	public void printHashandTreeLists(){
		Iterator<Student> it = hashSet.iterator();
		Iterator<Student> it2 = treeSet.iterator();
		Iterator<Student> it3 = hashMap.keySet().iterator();
		Iterator<Student> it4 = treeMap.keySet().iterator();

		System.out.println("HashSet:");
		while(it.hasNext()){
			Student s = it.next();
			System.out.println(s.toString());
		}
		System.out.println();
		System.out.println("TreeSet:");
		while(it2.hasNext()){
			Student s = it2.next();
			System.out.println(s.toString());
		}
		System.out.println();
		System.out.println("HashMap");
		while(it3.hasNext()){
			Student s = it3.next();
			System.out.println(s.toString() + " " + hashMap.get(s));
		}
		System.out.println();
		System.out.println("TreeSet");
		while(it4.hasNext()){
			Student s = it4.next();
			System.out.println(s.toString() + " " + treeMap.get(s));
		}
	}
	public void removeStudent(Student s){
		hashSet.remove(s);
		treeSet.remove(s);
		hashMap.remove(s);
		treeMap.remove(s);
	}
	public String getSchool(Student s){
		return hashMap.get(s);
	}
	public void printStacksandPQueues(){
		System.out.println("Stack:");
		while(stack.isEmpty() == false){
			Student s = stack.pop();
			System.out.println(s.toString());
		}

		System.out.println("Priority Queue:");
		while(priorityqueue.isEmpty() == false){
			Student s = priorityqueue.poll();
			System.out.println(s.toString());
		}
		
	}

}