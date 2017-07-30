package cr.intertec.userapp.presentacion;

import java.util.List;

/**
 * 
 * Result contains the Boolean and the list which identifies the result of the operation
 *
 */
public class Result {

	private boolean valid;
	private List<String> list;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

}
