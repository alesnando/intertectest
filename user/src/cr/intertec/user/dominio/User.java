package cr.intertec.user.dominio;

import java.io.Serializable;
import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;


import cr.intertec.user.exception.UserException;
import cr.intertec.user.util.CodeStatus;

/**
 * 
 * Clase entidad
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;;

	private int id;
	private String userName;
	private String firstName;
	private String lastName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	/**
	 * Method that validates username length and restricted words
	 * @param restricted
	 * @throws UserException
	 */
	public void validar(String restricted) throws UserException {
		UserException excepcion = UserException.getInstanciaDesdeMensaje(CodeStatus.ERROR_IN_REQUEST,
				UserException.ERROR_IN_REQUEST);
		String restrictedArr[] = StringUtils.split(restricted, ",");
		if (StringUtils.isBlank(getUserName()) || StringUtils.length(getUserName()) < 6) {
			excepcion.getTrace().getErrors().add(MessageFormat.format(UserException.ERROR_PROPERTY_NULL_EMPTY_SHORT, "username", getUserName()));
		}else{
			for (int i = 0; i < restrictedArr.length; i++) {
				if (StringUtils.containsIgnoreCase(getUserName(),restrictedArr[i])){
					excepcion.getTrace().getErrors().add(MessageFormat.format(UserException.ERROR_RESTRICTED_WORDS, "username", getUserName()));
					break;
				}
			}			
		}
		if (!excepcion.getTrace().getErrors().isEmpty()) {
		    throw excepcion;
		}
	}
}
