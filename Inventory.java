import java.util.HashMap;

public class Inventory<T> {
	
	private HashMap<T, Integer> inventory = new HashMap<T, Integer>();
	
	public int countItems(T item){
		Integer quantity;
		quantity = inventory.get(item);
		if(quantity==null) return 0;
		return quantity;
	}
	
	public void addItem(T item){
		int previousCount = countItems(item);
		inventory.put(item, previousCount+1);
	}
	
	public boolean isThere(T item){
		return inventory.containsKey(item);
	}
	
	public void withdrawItem(T item){
		if(isThere(item)){
			int count = inventory.get(item);
			inventory.put(item, count-1);
		}		
	}
	
	public void resetInventory(){
		inventory.clear();
	}
	
	public void putItems(T item, int count){
		inventory.put(item,count);
	}
	public void printInventory(){
		for (HashMap.Entry<T, Integer> entry : inventory.entrySet()) {
		    T key = entry.getKey();
		    Integer value = entry.getValue();
		    System.out.println(key.toString() + ": quantity " + value);
		}
	}
	public int getSize(){
		int size=0;
		for(int e : inventory.values()){
			size=size+e;
		}
		
		return size;
	}

}
