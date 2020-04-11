import java.util.*;
import java.io.Serializable;
public class HashMap<K, V> implements Serializable{
	private DLList<V>[] table;
	private DLList<K> keys;
	
	@SuppressWarnings("unchecked")
	public HashMap(){
		table = new DLList[10000];
		keys = new DLList<K>();
	}
	public void put(K key, V value){
		int index = key.hashCode();
		if(table[index] == null){
			table[index] = new DLList<V>();
			keys.add(key);
		}
		table[index].add(value);
	}
	public DLList<V> get(K key){
		int index = key.hashCode();
		return table[index];
	}

	public DLList<K> getKeys(){
		return keys;
	}
	public String toString(){
		String text = "";
		for(int i = 0; i < table.length; i++){
			DLList<V> bucket = table[i];
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
		return text;
	}
/*	public void remove(K key, V value){
		int index = key.hashCode();
		DLList<V> list = table[index];
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).equals(value)){
				list.remove(i);
			}
		}
	}*/
	public void remove(K key){
		int index = key.hashCode();
		DLList<V> list = table[index];
		for(int i = 0; i < list.size(); i++){
			list.remove(list.get(i));
			i--;
		}
	}
}