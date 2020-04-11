//TODO: Implement Comparable
public class DLList<E>{
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
	
	public void remove(){
		current = head.next();
		Node<E> prevNode = current.previous();
		Node<E> nextNode = current.next();
		prevNode.setNext(nextNode);
		nextNode.setPrevious(prevNode);
		size--;
	}
/*
	public void remove(E data){
		current = head.next();
		while(current.get() != null){
			if(current.get().equals(data)){
				Node<E> prevNode = current.previous();
				Node<E> nextNode = current.next();
				prevNode.setNext(nextNode);
				nextNode.setPrevious(prevNode);
				size--;
				return;
			}
			current = current.next();
		}
	}
*/
	/*public void removebyString(String input){
		current = head.next();
		while(current.get() != null){
			if(current.get().getArtist().equals(input)){
				Node<E> prevNode = current.previous();
				Node<E> nextNode = current.next();
				prevNode.setNext(nextNode);
				nextNode.setPrevious(prevNode);
				size--;
			}
			
		}
	}*/
	
	/*public void removeE(String input){
		current = head.next();
		while(current.get() != null){
			if(current.get().getE().equals(input)){
				Node<E> prevNode = current.previous();
				Node<E> nextNode = current.next();
				prevNode.setNext(nextNode);
				nextNode.setPrevious(prevNode);
				size--;
			}
			
		}
	}*/
	
	/*public String artistToString(String name){
		current = head;
		String Es = "";
		
		while(current.get() != null){
			if(current.get().getArtist().equals(name)){
				Es += current.get().getE() + "\n";
			}
		}
		return Es;
	}*/
	
	public void set(int location, E data){
		Node<E> node = getNode(location);
		node.setData(data);
	}
	
	/*public void remove(int index){
		Node<E> node = getNode(index);
		remove(node.get());
	}*/
  
	/*public String toString(){
		String text = "";
		Node<E> current = head.next();
		while(current.get() != null){
			text = text + current.get() + "\n";
			current = current.next();
		}
		return text;
	}*/
	
	/*public void timeSort(){
		current = head;
		while(current != null){
			Node<E> current2 = current.next();
			while(current2 != null){
				if(current.getData().getTime() > current2.getData().getTime()){
					E temp = current.getData();
					current.setData(current2.getData());
					current2.setData(temp);
				}
				
				current2 = current2.next();
			}
			current = current.next();
		}
	}*/

	/*public void artistSort(){
		current = head;
		while(current != null){
			Node<E> current2 = current.next();
			while(current2 != null){
				if(current.getData().getArtist().compareTo(current2.getData().getArtist()) > 0){
					E temp = current.getData();
					current.setData(current2.getData());
					current2.setData(temp);
				}
				
				current2 = current2.next();
			}
			current = current.next();
		}
	}*/

	/*public void Sort(){
		current = head;
		while(current != null){
			Node<E> current2 = current.next();
			while(current2 != null){
				if(current.getData().getValue() > current2.getData().getValue()){
					E temp = current.getData();
					current.setData(current2.getData());
					current2.setData(temp);
				}
				
				current2 = current2.next();
			}
			current = current.next();
		}
	} 	*/
}