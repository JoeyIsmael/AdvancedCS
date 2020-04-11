import java.util.*;
public class Classes{
	private String school;
	private ArrayList<String> schedule;
	public Classes(String school, ArrayList<String> schedule){
		this.school = school;
		this.schedule = schedule;
	}
	public String getSchool(){
		return school;
	}
	public ArrayList<String> getSchedule(){
		return schedule;
	}
	public void change(ArrayList<String> newschedule){
		schedule = newschedule;
	}
	public String toString(){
		String text = "";
		for(int i = 0; i < schedule.size(); i++){
			text = text + schedule.get(i) + "\n";
		}
		return text;
	}
}