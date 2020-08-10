import java.util.ArrayList;
public class Schedule{
	private ArrayList<Pair<Integer,String>> mySchedule;
	private ArrayList<Pair<Integer,String>> temp;
	public Schedule(){
		mySchedule = new ArrayList<Pair<Integer,String>>();
		temp = new ArrayList<Pair<Integer, String>>();
	}
	public void addClass(int period, String course){
		for(int i = 0; i < mySchedule.size(); i++){
			if(mySchedule.get(i).getE().equals(course)){
				return;
			}
		}
		for(int i = 0; i < mySchedule.size(); i++){
			if(mySchedule.get(i).getT() == period){
				System.out.println("That period is filled already");
				return;
			}
		}
		
		
		 
		Pair<Integer, String> newCourse = new Pair<Integer, String>(period, course);
		mySchedule.add(newCourse);
		
		
		for(int i = 0; i < mySchedule.size(); i++){
			temp.add(mySchedule.get(i));
		}
		mySchedule.clear();
		
		while(temp.size() > 0){
			int lowNum = Integer.MAX_VALUE;
			int index = 0;
			String tempClass = "";
			for(int i = 0; i < temp.size(); i++){
				if(temp.get(i).getT() < lowNum){
					lowNum = temp.get(i).getT();
					tempClass = temp.get(i).getE();
					index = i;
				}
			}
			
			Pair<Integer, String> newClass = new Pair<Integer, String>(lowNum, tempClass);
			mySchedule.add(newClass);
			temp.remove(index);
			lowNum = Integer.MAX_VALUE;
		}
		
		
		
		
		
		/*for(int i = 0; i < mySchedule.size()-1; i++){
			for(int j = i+1; j<mySchedule.size(); j++){
				if(mySchedule.get(i).getT() > mySchedule.get(j).getT()){
					String temp1 = mySchedule.get(i).getE();
					String temp2 = mySchedule.get(j).getE();
					mySchedule.get(i) = new Pair<Integer,String>(mySchedule.get(i).getT(), temp2);
					mySchedule.get(j) = new Pair<Integer,String>(mySchedule.get(j).getT(), temp1);
					
				}
			}
		}	*/
	}
	public void removeClass(int period){
		for(int i = 0; i < mySchedule.size(); i++){
			if(mySchedule.get(i).getT() == period){
				mySchedule.remove(i);
			}
		}
	}
	public String[] toStringArray(){
		
		String[] returnVal = new String[mySchedule.size() + 1];
		
		for(int i = 0; i < mySchedule.size(); i++){
			
			returnVal[i] =  "" + mySchedule.get(i).getT() + "         " + mySchedule.get(i).getE();
		}
		return returnVal;
	}
}