import java.util.List;

public class aaa {

	public static void main(String[] args) {
		VendingMachine newVendingMachine = new VendingMachine();
	//printing stats:
		// 	newVendingMachine.printStats();
		
		
		
		//how many coins are there?
		
		//System.out.println(""+newVendingMachine.coinInventory.getSize());
		

		//checking if getChange works - it works but need to update inventory after withdrawal - need new method for that
		 List<Coin> changes = newVendingMachine.getChange(550);
		
		 for(Coin c : changes){
			 System.out.println("moneta: "+c.toString());
		 }
	}

}
