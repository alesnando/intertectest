package cr.intertec.user.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * List errors
 *
 */
public class Trace implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final List<String> errors;

	private Trace() {
		this.errors = new ArrayList<String>();
	}

	public List<String> getErrors() {
		return errors;
	}

	public final static Trace getInstancia() {
		return new Trace();
	}

}
