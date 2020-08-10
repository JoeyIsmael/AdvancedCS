public class SLList<E>{
	private Node<Item> head;
	private Node<Item> current;

	public SLList(){
		head = null;
	}

	public void add(Item data, int count){
		Node<Item> newNode = new Node<Item>(data);
		if(head == null){
			head = newNode;
			newNode.setNext(null);
		} else {
			Node<Item> current = head;
			if(current.getData().equals(data)){
				current.getData().updateQuantity();
				current.getData().setTime(count);
				return;
			}
			while(current.next() != null){
				if(current.next().getData().equals(data)){
					current.next().getData().updateQuantity();
					current.next().getData().setTime(count);
					return;
				}
				current = current.next();
			}
			current.setNext(newNode);
		}
	}

	public void remove(Item data){
		Node<Item> current = head;
		if(current.getData().equals(data)){
			head = current.next();
			return;
		}
		while(current.next() != null){
			if(current.next().getData().equals(data)){
				current.setNext(current.next().next());
				break;
			}
			current = current.next();
		}
	}

	public double getPrice(){
		double price = 0;
		Node<Item> current = head;
		while(current != null){
			price = price + current.getData().getPrice();
			current = current.next();
		}
		return price;
	}

	public String toString(){
		String text = "";
		Node<Item> current = head;
		while(current != null){
			text = text + current.getData().toString() + "\n";
			current = current.next();
		}
		return text;
	}

	public void timeSort(){
		current = head;
		while(current != null){
			Node<Item> current2 = current.next();
			while(current2 != null){
				if(current.getData().getTime() > current2.getData().getTime()){
					Item temp = current.getData();
					current.setData(current2.getData());
					current2.setData(temp);
				}
				
				current2 = current2.next();
			}
			current = current.next();
		}
	}

	public void priceSort(){
		current = head;
		while(current != null){
			Node<Item> current2 = current.next();
			while(current2 != null){
				if(current.getData().getPrice() > current2.getData().getPrice()){
					Item temp = current.getData();
					current.setData(current2.getData());
					current2.setData(temp);
				}
				
				current2 = current2.next();
			}
			current = current.next();
		}
	}

	public void nameSort(){
		current = head;
		while(current != null){
			Node<Item> current2 = current.next();
			while(current2 != null){
				if(current.getData().getName().compareTo(current2.getData().getName()) > 0){
					Item temp = current.getData();
					current.setData(current2.getData());
					current2.setData(temp);
				}
				
				current2 = current2.next();
			}
			current = current.next();
		}
	}

	public double getQuantityPrice(String name, double price){
		Node<Item> current = head;
		while(current != null){
			if(current.getData().getName().equals(name) && current.getData().getPrice() == price){
				return current.getData().getQuantity() * current.getData().getPrice();
			}
			current = current.next();
		}
		return 0;
	}

	public boolean contains(Item data){
		Node<Item> current = head;
		while(current != null){
			if(current.getData().equals(data)){
				return true;
			} 
			current = current.next();
		}
		return false;
	}
}