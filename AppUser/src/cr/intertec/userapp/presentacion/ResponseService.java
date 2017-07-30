package cr.intertec.userapp.presentacion;

import cr.intertec.userapp.exception.UserException;
import cr.intertec.userapp.util.CodeStatus;
import cr.intertec.userapp.util.Trace;

/**
 * 
 * Service response class with result contains the Boolean and the list which identifies the result of the operation
 *
 * @param <T>
 */
public class ResponseService<T> {

	private int code;
	private String message;
	private T dato;
	private Trace trace;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getDato() {
		return dato;
	}

	public Trace getTrace() {
		return trace;
	}
	
	public ResponseService() {
	}

	public ResponseService(CodeStatus code, T dato) {
		this.code = code.getCode();
		this.message = code.toString();
		this.trace = Trace.getInstancia();
		this.dato = dato;
	}

	public ResponseService(UserException excepcion) {
		this.code = excepcion.getStatus().getCode();
		this.message = excepcion.getStatus().toString();
		this.trace = excepcion.getTrace();
		this.dato = null;
	}
}
