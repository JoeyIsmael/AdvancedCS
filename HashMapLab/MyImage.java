import java.io.Serializable;
public class MyImage implements Serializable{
	private String url;
	private String landmark;
	public MyImage(String url, String landmark){
		this.url = url;
		this.landmark = landmark;
	}

	public String toString(){
		return url + " " + landmark;
	}

	public String getUrl(){
		return url;
	}

	public String getLandmark(){
		return landmark;
	}

	public boolean equals(Object o) {
        MyImage i = (MyImage)o;
        if(i.getUrl().equals(url) && i.getLandmark().equals(landmark)){
        	return true;
        } else {
        	return false;
        }
    }
}