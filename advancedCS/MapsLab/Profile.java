public class Profile implements Comparable<Profile>{
	private String firstName;
	private String lastName;
	private int birthYear;
	public Profile(String firstName, String lastName, int birthYear){
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getYear(){
		return birthYear;
	}
	public String toString() {
		return lastName + " " + firstName + " " + birthYear;
		}
	
	public int hashCode() {
		int firstHash = firstName.charAt(0) * 37;
		int lastHash = lastName.charAt(0) * 13;
		int code = (firstHash + lastHash);
		
        return code;
    }
	public boolean equals(Object o) {
        Profile p = (Profile)o;
        if( lastName.equals( p.getLastName() ) && firstName.equals(p.getFirstName()) && birthYear == p.getYear() ){
            return true;
        }
        else
            return false;
    }

    public int compareTo(Profile p){
		if(lastName.equals( p.getLastName() ) && firstName.equals(p.getFirstName()) && birthYear == p.getYear()){
			return 0;
		}else if (lastName.compareTo(p.getLastName()) == 0 && firstName.compareTo(p.getFirstName()) == 0){
			return ((birthYear-p.getYear()));
		}else if (lastName.compareTo(p.getLastName()) == 0) {
			return 	firstName.compareTo(p.getFirstName());
		} else {
			return lastName.compareTo(p.getLastName());	
		}
	}
}