public class Node<E>{
	private E data;
	private Node<E> next;

	public Node(E data){
		this.data = data;
		next = null;
	}

	public void setNext(Node<E> newNode){
		next = newNode;
	}

	public E getData(){
		return data;
	}

	public Node<E> next(){
		return next;
	}

	public void setData(E newData){
		data = newData;
	}
}