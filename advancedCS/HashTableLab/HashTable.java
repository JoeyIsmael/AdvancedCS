public class HashTable<E>{
	private DLList<E>[] table;
	
	@SuppressWarnings("unchecked")
	public HashTable(){
		table = new DLList[17576];
	}
	
	public void add(E data){
		int index = data.hashCode();
		System.out.println(data.toString() + " " + index);

		if(table[index] == null){
			table[index] = new DLList<E>();
		}
		table[index].add(data);
	}

	/*public DLList<E> get(E data){
		int index = data.hashCode()%table.length;
		return table[index];
	}*/

	public String getBucket(int num) {
		String text = "";
		System.out.println("get bucket" + " " + num);
		DLList<E> bucket = table[num];
		System.out.println(bucket.size());
		for(int i = 0; i < bucket.size(); i++) {
			System.out.println(i);
			text += (bucket.get(i).toString() + ",");
		}
	return text;
	}

	public E getCar(int num, int location){
		return table[num].get(location);
	}

	public void removeBucket(int num, int location){
		System.out.println("remove bucket" + " " + num);
		E data = table[num].get(location);
		System.out.println(data.toString());
		System.out.println(table[num].size());
		table[num].remove(data);
		System.out.println(table[num].size());
	}
	
	public String toString(){
		String text = "";
		for(int i = 0; i < table.length; i++){
			DLList<E> bucket = table[i];
			if(bucket == null || bucket.size() == 0){
				continue;
			}
			text = text + (i + ": ");
			for(int j= 0; j < bucket.size(); j++){
				text = text + bucket.get(j).toString() + " ";
                if( j < table[i].size()-1  ){
                   text = text + "-> ";	
				} else {
					text = text + "\n";
				}
			}
		}
		System.out.println(text);
		return text;
	}
}