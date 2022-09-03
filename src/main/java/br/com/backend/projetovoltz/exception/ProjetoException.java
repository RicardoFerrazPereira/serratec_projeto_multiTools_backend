package br.com.backend.projetovoltz.exception;

public class ProjetoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProjetoException() {
		super();
	}

	public ProjetoException(String message) {
		super(message);
	}

	public ProjetoException(String message, Exception cause) {
		super(message, cause);
	}

	public ProjetoException(Exception e) {
		super(e);
	}
}
