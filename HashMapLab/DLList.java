import java.io.Serializable;
public class DLList<E> implements Serializable{
	private Node<E> head;
	private Node<E> current;
	private Node<E> tail;
	private int size;
	
	public DLList(){
		head = new Node<E>(null);
		tail = new Node<E>(null);
		size = 0;
	}
	
	private Node<E> getNode(int location){
		current = head.next();
		for(int i = 0; i < location; i++){
			current = current.next();
		}
		return current;
	}

	public void add(E data){
		Node<E> newNode = new Node<E>(data);
		if(size == 0){
			head.setNext(newNode);
			tail.setPrevious(newNode);
			newNode.setNext(tail);
			newNode.setPrevious(head);
			size++;
		} else {
			Node<E> previous = tail.previous();
			newNode.setPrevious(previous);
			newNode.setNext(tail);
			previous.setNext(newNode);
			tail.setPrevious(newNode);
			size++;
		}
		
	}
	
	public void add(int location, E data){
		Node<E> newNode = new Node<E>(data);
		if(size == 0){
			head.setNext(newNode);
			tail.setPrevious(newNode);
			newNode.setNext(tail);
			newNode.setPrevious(head);
			size++;
		} else {
			Node<E> node = getNode(location);
			Node<E> previous = node.previous();
			newNode.setPrevious(previous);
			newNode.setNext(node);
			node.setPrevious(newNode);
			previous.setNext(newNode);
			size++;
		}
	}
	
	public E get(int location){
		Node<E> node = getNode(location);
		return node.get();
	}
	
	public int size(){
		return size;
	}
	
	public void remove(E data){
		current = head.next();
		if(current.get().equals(data)){
			System.out.println("Hello");
			head = current;
			size--;
			return;
		}
		while(current != null){
			if(current.get().equals(data)){
				Node<E> before = current.previous();
				Node<E> after = current.next();
				before.setNext(after);
				after.setPrevious(before);
				size--;
				return;
			}
			current = current.next();
		}
	}

	public void remove(int location){
		Node<E> node = getNode(location);
		remove(node.get());
	}
  
	public String toString(){
		String text = "";
		Node<E> current = head.next();
		while(current.get() != null){
			text = text + current.get() + "\n";
			current = current.next();
		}
		return text;
	}
}