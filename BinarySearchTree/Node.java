import java.io.Serializable;
public class Node<E> implements Serializable{
	private E data;
	private Node<E> left;
	private Node<E> right;
	private Node<E> parent;
	
	public Node(E data){
		this.data = data;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	public E get(){
		return data;
	}

	public void setParent(Node<E> node){
		parent = node;
	}

	public Node<E> getParent(){
		return parent;
	}
	
	public Node<E> getLeft(){
		return left;
	}
	
	public Node<E> getRight(){
		return right;
	}
	
	public void setLeft(Node<E> node){
		left = node;
	}
	
	public void setRight(Node<E> node){
		right = node;
	}
}