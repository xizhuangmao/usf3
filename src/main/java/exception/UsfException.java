package exception;

public class UsfException extends RuntimeException{

	private String message;

	public UsfException(String msg) {
		message = msg;
	}

	public UsfException(String msg, Exception e) {
		super(e);
		message = msg;
	}

	public String getMessage() {
		return message;
	}
}
