package br.com.backend.projetovoltz.exception;

public class FerramentaException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public FerramentaException() {
		super();
	}
	
	public FerramentaException(String message) {
		super(message);
	}

	public FerramentaException(String message, Exception cause) {
		super(message, cause);
	}
	
	public FerramentaException(Exception e) {
		super(e);
	}

}
