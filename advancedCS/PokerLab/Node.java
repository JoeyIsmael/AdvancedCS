public class Node<E>{
	private E data;
	private Node<E> next;
	private Node<E> previous;
	
	public Node(E data){
		this.data = data;
		next = null;
		previous = null;
	}
	
	public E get(){
		return data;
	}
	
	public E getData(){
		return data;
	}
	
	public void setData(E newData){
		data = newData;
	}
	
	public Node<E> next(){
		return next;
	}
	
	public Node<E> previous(){
		return previous;
	}
	
	public void setNext(Node<E> newNode){
		next = newNode;
	}
	
	public void setPrevious(Node<E> newNode){
		previous = newNode;
	}
}