
public class NotEnoughProductsException extends RuntimeException {
	private String message;
	
	public NotEnoughProductsException(String string){
		this.message  = string;
	}
	
	@Override
	public String getMessage(){
		return message;
	}
}
