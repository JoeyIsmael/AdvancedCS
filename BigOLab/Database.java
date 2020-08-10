import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ListIterator;
import java.util.Collections;
import java.util.*;

public class Database{
	ArrayList<Student> students;
	ListIterator<Student> it1; 
	int passes = 0;
	public Database(){
		students = new ArrayList<Student>();
	}

	public void ReadFile(){
		 try {
            Scanner scan = new Scanner(new File("names.txt"));
             
            while (scan.hasNext()){
               String line = scan.nextLine();
               int index = line.indexOf(" ");
               String last = line.substring(index);
               it1 = students.listIterator(); 
               boolean added = false;
				while( it1.hasNext() ){
					if( it1.next().getLast().compareTo(last) > 0 ){
						it1.previous();
						it1.add(new Student(line.substring(0, index), last, ((int)(Math.random()*(18-14)+14))));
						added = true;
						break;
					}
				}
				if( added == false ){
					it1.add(new Student(line.substring(0, index), line.substring(index), ((int)(Math.random()*(18-14)+15))));
				}
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	public String getArray(){
		String allStudents = "";
		for(int i = 0; i < students.size(); i++){
			allStudents = allStudents + students.get(i).toString(i+1) + "\n";
		}
		return allStudents;
	}
	public ArrayList<Student> returnArray(){
		return students;
	}
	public void shuffleArray(){
		Collections.shuffle(students);
	}
	public int swapElements(){
		int passes = 0;
		for(int i = 0; i < students.size()-1; i++){
			for(int j = i; j < students.size(); j++){
				if(students.get(i).getLast().compareTo(students.get(j).getLast()) > 0){
					Student temp = students.get(i);
					students.set(i, students.get(j));
					students.set(j, temp);
					passes++;
				}
			}
		}
		return passes;
	}
	public void callMerge(){
		passes = 0;
		divide(0, students.size()-1);
	}

	 public void divide(int startIndex,int endIndex){
        
        //Divide till you breakdown your list to single element
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            passes = passes + 2;
            divide(startIndex, mid);
            divide(mid+1, endIndex);        
            
            //merging Sorted array produce above into one sorted array
            merger(startIndex,mid,endIndex);       
        } 
    }   
    
    public void merger(int startIndex,int midIndex,int endIndex){
        
        //Below is the mergedarray that will be sorted array Array[i-midIndex] , Array[(midIndex+1)-endIndex]
        ArrayList<Student> mergedSortedArray = new ArrayList<Student>();
        
        int leftIndex = startIndex;
        int rightIndex = midIndex+1;
        
        while(leftIndex<=midIndex && rightIndex<=endIndex){
            if(students.get(leftIndex).getLast().compareTo(students.get(rightIndex).getLast()) < 0){
                mergedSortedArray.add(students.get(leftIndex));
                leftIndex++;
            }else{
                mergedSortedArray.add(students.get(rightIndex));
                rightIndex++;
            }
        }       
        
        //Either of below while loop will execute
        while(leftIndex<=midIndex){
        	passes++;
            mergedSortedArray.add(students.get(leftIndex));
            leftIndex++;
        }
        
        while(rightIndex<=endIndex){
        	passes++;
            mergedSortedArray.add(students.get(rightIndex));
            rightIndex++;
        }
        
        int i = 0;
        int j = startIndex;
        //Setting sorted array to original one
        while(i<mergedSortedArray.size()){
            students.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }

    public int getPasses(){
    	return passes;
    }
}