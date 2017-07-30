package cr.intertec.user.util;

/**
 * 
 * Enum errors
 *
 */
public enum CodeStatus {
    OK(200), ERROR_IN_REQUEST(400), ERROR_IN_PARAMETER(422), RESOURCE_NO_FOUND(
	    404), INTERNAL_SERVER_ERROR(
		    500), PERISTENCE_PROVIDER_ERROR(561);

    private int value;

    private CodeStatus(int code) {
	this.setCode(code);
    }

    public int getCode() {
	return value;
    }

    private void setCode(int value) {
	this.value = value;
    }
}
