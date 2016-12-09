
public class NotEnoughChangeException extends RuntimeException {
private String message;
	
	public NotEnoughChangeException(String string){
		this.message  = string;
	}
	
	@Override
	public String getMessage(){
		return message;
	}
}
