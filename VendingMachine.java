import java.util.ArrayList;
import java.util.List;



public class VendingMachine implements VendingMachineInteface{
	
	private Inventory<Coin> coinInventory = new Inventory<Coin>();
	private Inventory<Item> itemInventory = new Inventory<Item>();
	private Item selectedItem;
	private long currentBalance;	
	private long totalSales;
	
	
	
	public VendingMachine(){
		initialize();
	}
	
	
	
	private void initialize(){
		//creating machine inventory with 10 of each product and 10 of each coin type
		for(Coin c : Coin.values()){
			coinInventory.putItems(c,10);
		}
		
		for(Item i : Item.values()){
			itemInventory.putItems(i,10);
		}
		
	}
	
	public VendingMachine(int coinsNumber, int itemsNumber){
		initializeCustom(coinsNumber,itemsNumber);
	}
	
	private void initializeCustom(int coinsNumber, int itemsNumber){
		for(Coin c : Coin.values()){
			coinInventory.putItems(c,coinsNumber);
		}
		
		for(Item i : Item.values()){
			itemInventory.putItems(i,itemsNumber);
		}
	}

	@Override
	public List<Coin> refund() {
		List<Coin>refund = getChange(currentBalance);
		currentBalance=0;
		selectedItem=null;
		updateCoinInventory(refund);
		for(Coin c : refund){
			System.out.println("Refunding: " + c.name());
		}
		
		return refund;
	}

	@Override
	public void reset() {
		coinInventory.resetInventory();
		itemInventory.resetInventory();
		totalSales=0;
		selectedItem=null;
		currentBalance=0;
		
	}

	@Override
	public void InsertCoin(Coin coin) {
		currentBalance=currentBalance+coin.getValue();
		coinInventory.addItem(coin);	
	}

	@Override
	public Container<Item, List<Coin>> returnOrderAndChange() {
		
		Item item = collectItem();
		List<Coin> change = collectChange();
		
		
		System.out.println(""+ item.getName() + " bought");
		for(Coin c : change){
			System.out.println("Change: " + c.name());
		}
		
		
		
		totalSales+=selectedItem.getPrice();
		currentBalance=0;
		selectedItem=null;
		
		
		
		return new Container(change,item);
	}

	@Override
	public long SelectAndGetValue(Item item) {
		if(itemInventory.isThere(item)){
			selectedItem=item;
			return item.getPrice();
		}
		throw new NotEnoughProductsException("There isn't any of this product left, buy something else");
	}
	
	public boolean isFullyPaid(){
		if(currentBalance>=selectedItem.getPrice()) return true;
		
		return false;
	}
	
	public long getTotalSales(){
		return totalSales;
	}
	
	public void printStats(){
		System.out.println("Total Sales:" + getTotalSales());
		System.out.println("Coins type and quantity:");
		coinInventory.printInventory();
		System.out.println("Items type and quantity:");
		itemInventory.printInventory();
	}
	
	public boolean hasSufficientChange(){
		return hasSufficientChangeforAmount(currentBalance - selectedItem.getPrice());
	}
	
	public boolean hasSufficientChangeforAmount(long amount){
		boolean hasChange=true;
		try{
			getChange(amount);
			
		}catch(NotEnoughChangeException e){
			return false;
		}
		
		return hasChange;
	}
	public List<Coin> getChange(long amount) throws NotEnoughChangeException{
		int c=0;
		 //List<Coin> changes = Collections.emptyList();
		List<Coin> changes = new ArrayList<Coin>();
		 
		 for(int i=0;i<coinInventory.getSize();i++){
			 c++;
			 if(amount==0) return changes;

			 else if(amount%500==0 && coinInventory.countItems(Coin.PIEC)>0){
				 amount=amount - Coin.PIEC.getValue();
				 changes.add(Coin.PIEC);
				 
			 }
			 else if(amount%200==0 && coinInventory.countItems(Coin.DWA)>0){
				 amount=amount - Coin.DWA.getValue();
				 changes.add(Coin.DWA);
				
			 }
			 
			 else if(amount%100==0 && coinInventory.countItems(Coin.JEDEN)>0){
				 amount=amount - Coin.JEDEN.getValue();
				 changes.add(Coin.JEDEN);
				 
			 }
			 else if(amount%50==0 && coinInventory.countItems(Coin.PIECDZIESIATGR)>0){
				 amount=amount - Coin.PIECDZIESIATGR.getValue();
				 changes.add(Coin.PIECDZIESIATGR);
				 
			 }
			 else if(amount%20==0 && coinInventory.countItems(Coin.DWADZIESCIAGR)>0){
				 amount=amount - Coin.DWADZIESCIAGR.getValue();
				 changes.add(Coin.DWADZIESCIAGR);
				 
			 }
			 else if(amount%10==0 ){
				 amount=amount - Coin.DZIESIECGR.getValue();
				 changes.add(Coin.DZIESIECGR);
				
			 }	
			 if(amount==0) return changes;
		 }
		 
		 
		 throw new NotEnoughChangeException("Not enough change");
	}
	
	public void updateCoinInventory(List<Coin> changes){
		if(!changes.isEmpty()){
			for(Coin c: changes){
				coinInventory.withdrawItem(c);
			}
		}
	}
	
	public List<Coin> collectChange(){
		long changeValue = currentBalance-selectedItem.getPrice();
		List<Coin> change = getChange(changeValue);
		updateCoinInventory(change);
		return change;
	}
	public Item collectItem() throws NotPaidFullException,NotEnoughChangeException{
		if(isFullyPaid()){
			if(hasSufficientChange()){
			itemInventory.withdrawItem(selectedItem);
			
			return selectedItem;
			} 
			else throw new NotEnoughChangeException("Not enough change in machine");
		}
		else{
			long whatToPay = selectedItem.getPrice()-currentBalance;
			throw new NotPaidFullException("Price not fully paid", whatToPay);
		}
	}
	
	public long getCurrentBalance(){
		return currentBalance;
	}
	}





