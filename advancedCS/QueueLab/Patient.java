public class Patient implements Comparable<Patient>{
	private String name;
	private String illnessDescription;
	private int priority;
	private int ageGroup;
	private String doctorsNote;
	private int timeStamp;

	public Patient(String name, String illnessDescription, int priority, int ageGroup, String doctorsNote, int timeStamp){
		this.name = name;
		this.illnessDescription = illnessDescription;
		this.priority = priority;
		this.ageGroup = ageGroup;
		this.doctorsNote = doctorsNote;
		this.timeStamp = timeStamp;
	}

	public String getPriority(){
		if(priority == 3){
			return "high";
		} else if(priority == 2){
			return "medium";
		} else{
			return "low";
		}
	}
	public int getPriorityNum(){
		return priority;
	}
	public String getName(){
		return name;
	}
	public String ageGroup(){
		if(ageGroup == 1){
			return "adult";
		} else{
			return "child";
		}
	}
	public int ageGroupNum(){
		return ageGroup;
	}
	public int getTimeStamp(){
		return timeStamp;
	}
	public String getIllness(){
		return illnessDescription;
	}

	public String toString(){
		return name + " " + illnessDescription + " " + getPriority() + " " + ageGroup() + " " + timeStamp;
	}

	public void setPriority(int newPriority){
		priority = newPriority;
	}
	public void setIllnessDescription(String newDescription){
		illnessDescription = newDescription;
	}

	public String dischargeString(){
		return name + " " + doctorsNote;
	}

	public void setNote(String note){
		doctorsNote = note;
	}


	public int compareTo(Patient p){
		if(ageGroup == p.ageGroupNum() && priority == p.getPriorityNum()){
			return timeStamp - p.getTimeStamp();
		} else if(priority == p.getPriorityNum()){
			return p.ageGroupNum() - ageGroup;
		} else {
			return p.getPriorityNum() - priority;
		}
	}

	public boolean equals(Patient p){
		if(name.equals(p.getName())){
			return true;
		} else {
			return false;
		}

	}

}