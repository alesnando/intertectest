package cr.intertec.user.presentacion;

import cr.intertec.user.exception.UserException;
import cr.intertec.user.util.CodeStatus;
import cr.intertec.user.util.Trace;

/**
 * 
 * Service response class with result contains the Boolean and the list which identifies the result of the operation
 *
 * @param <T>
 */
public class Response<T> {

	private final int code;
	private final String message;
	private final T dato;
	private final Trace trace;

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

	public Response(CodeStatus code, T dato) {
		this.code = code.getCode();
		this.message = code.toString();
		this.trace = Trace.getInstancia();
		this.dato = dato;
	}

	public Response(UserException excepcion) {
		this.code = excepcion.getStatus().getCode();
		this.message = excepcion.getStatus().toString();
		this.trace = excepcion.getTrace();
		this.dato = null;
	}
}
