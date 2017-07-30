package cr.intertec.user.persistencia;

import cr.intertec.user.dominio.User;
import cr.intertec.user.exception.UserException;

/**
 * 
 * Definition of methods for connection to database
 *
 */
public interface IUserDao {

	public User findByUserName(String userName) throws UserException;
	
	public String getProperty(String key) throws UserException;
	
}
