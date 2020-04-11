import java.io.Serializable;
public class Country implements Serializable{
	private String abr;
	private String name;
	public Country(String abr, String name){
		this.abr = abr;
		this.name = name;
	}
	public String toString(){
		return abr + " - " + name;
	}

	public String getAbr(){
		return abr;
	}

	public String getName(){
		return name;
	}

	public boolean equals(Object o) {
        Country c = (Country)o;
        if(c.getName().equals(name) && c.getAbr().equals(abr)){
        	return true;
        } else {
        	return false;
        }
    }

	public int hashCode(){
		int code = 0;
		int val = 0;
		char[] splitName = abr.toLowerCase().toCharArray();
		for(int i = 0; i < abr.length(); i++) {
			val = splitName[i] - 97;
			code += (int)(Math.pow(26,i)) * val;
		}
		return code;
	}
}