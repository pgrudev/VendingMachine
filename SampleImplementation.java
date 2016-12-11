import java.util.List;
import java.util.Scanner;

public class SampleImplementation {

	public static Coin coinInput(int coin){
			
		switch(coin){
			case 1: 	
				return Coin.DZIESIECGR;
			case 2:
				return Coin.DWADZIESCIAGR;
			case 3:
				return Coin.PIECDZIESIATGR;
			case 4:
				return Coin.JEDEN;
			case 5: 
				return Coin.DWA;
			case 6: 
				return Coin.PIEC;
			default:
				return null;
		}
	}
	public static Item selectItem(int item){
			
			switch(item){
				case 1: 
					return Item.SNACK;
				case 2:
					return Item.SODA;
				case 3:
					return Item.WATER;
				default: 
					System.out.println("wrong number");
					break;
			}
			return null;
	}
	
	
	
		public static void main(String[] args) {
			VendingMachine vm = new VendingMachine(10,2); //vm- instance of Vending Machine
			
			Scanner input = new Scanner(System.in);
			
			
			
			
			
			
			
			boolean finished=false;
			while(!finished){
			
				
				System.out.println("MENU Select one of following:");
				System.out.println("1: Go to item selection menu");
				System.out.println("2: Input coins");
				System.out.println("3: CANCEL");
				System.out.println("4: RESET MACHINE");
				System.out.println("5: PRINT STATS");
				System.out.println("99: SHUT ME DOWN");
				System.out.println("Current balance:" + vm.getCurrentBalance());
				
				long balance=vm.getCurrentBalance();
				
				int choice = input.nextInt();	
			
			switch(choice){
				case 1: 	
					System.out.println("What do you want to buy?");
					System.out.println("1: Snack - twix");
					System.out.println("2: Soda - cola");
					System.out.println("3: Water");
					choice = input.nextInt();
					Item item = selectItem(choice);
					
				if (item!=null) {
					System.out.println("Selected item: " + item.getName());
					System.out.println("Selected item's price: " + item.getPrice());
					try {
						long price = vm.SelectAndGetValue(item);
					} catch (NotEnoughProductsException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
						break;
					}
					try {
						Container output = vm.returnOrderAndChange();
					} catch (NotPaidFullException e) {
						System.out.println(e.getMessage() +", remaining: " +  e.getValue());

						e.printStackTrace();
						break;
					}
					System.out.println("Buying process: completed ");
				}
				else  System.out.println("Wrong item number");
				break;
				
				
				case 2:
					System.out.println("What coin was inserterd?");
					int coin=input.nextInt();
					Coin coinNext = coinInput(coin);
				if (coinNext!=null) {
					balance += coinNext.getValue();
					vm.InsertCoin(coinNext);	
				}
				else System.out.println("Wrong number");
				break;
				case 3:
				try {
					vm.refund();
				} catch (NotEnoughChangeException e) {
					System.out.println(""+e.getMessage());
					e.printStackTrace();
				}
					System.out.println("Refund complited");
					break;
				case 4:
					vm.reset();
					System.out.println("Reset complited");
					break;
				case 5:
					vm.printStats();
					break;
				case 99:
					//switch off machine
					finished=true;
					break;
				default: 
					System.out.println("Wrong number");
					break;
				}
		}	
		}

}

