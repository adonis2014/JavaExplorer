package name.chenyuelin.webapp.custom;

public class RequestArgumentNotValidException extends Exception {

	private static final long serialVersionUID = -1642510001962580975L;

	public RequestArgumentNotValidException(){
	}
	
	public RequestArgumentNotValidException(String msg){
		super(msg);
	}
}
