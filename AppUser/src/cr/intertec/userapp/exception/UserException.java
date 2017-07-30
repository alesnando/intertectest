package cr.intertec.userapp.exception;

import cr.intertec.userapp.util.CodeStatus;
import cr.intertec.userapp.util.Trace;

/**
 * 
 * Class to define exceptions
 *
 */
public class UserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Trace trace;
	private final CodeStatus status;

	public static final String ERROR_IN_REQUEST = "Syntax error, one or more data is null, empty or restricted words";
	public static final String ERROR_PROPERTY_NULL_EMPTY_SHORT = "Property {0} is null, empty or short: {1}";
	public static final String ERROR_RESTRICTED_WORDS = "Contains a restricted word:{0} with value:{1}";

	public Trace getTrace() {
		return this.trace;
	}

	public CodeStatus getStatus() {
		return this.status;
	}

	protected UserException(CodeStatus status, String message) {
		super(message);
		this.trace = Trace.getInstancia();
		this.status = status;
	}

	protected UserException(CodeStatus status, Throwable cause) {
		super(cause);
		this.trace = Trace.getInstancia();
		this.status = status;
	}

	protected UserException(CodeStatus status, String message, Throwable origen) {
		super(message, origen);
		this.trace = Trace.getInstancia();
		this.status = status;
	}

	public static UserException getInstanciaDesdeMensaje(CodeStatus estatus, String message) {
		return new UserException(estatus, message);
	}

	public static UserException getInstanciaDesdeOrigen(CodeStatus status, Throwable origin) {
		return new UserException(status, origin);
	}

	public static UserException getInstanciaDesdeMensajeOrigen(CodeStatus status, String message,
			Throwable origin) {
		return new UserException(status, message, origin);
	}

}
