public class Pair<T,E>{
	private T object;
	private E object2;

	public Pair(T object, E object2){
		this.object = object;
		this.object2 = object2;
	}

	public String toString(){
		return object + " : " + object2;
	}

	public T getT(){
		return object;
	}

	public E getE(){
		return object2;
	}
}