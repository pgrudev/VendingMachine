import java.util.List;

public interface VendingMachineInteface {
	public List<Coin> refund();
	public void reset();
	public void InsertCoin(Coin coin);
	public Container<Item, List<Coin>> returnOrderAndChange();
	public long SelectAndGetValue(Item item);
	
}
