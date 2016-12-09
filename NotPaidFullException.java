
public class NotPaidFullException extends RuntimeException {
private String message;
private long value;
	
	public NotPaidFullException(String string, long remaining){
		this.message  = string;
		this.value = remaining;
	}
	
	@Override
	public String getMessage(){
		return message;
	}
	public long getValue(){
		return value;
	}
	

}
