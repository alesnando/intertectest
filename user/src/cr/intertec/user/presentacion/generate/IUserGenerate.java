package cr.intertec.user.presentacion.generate;

import cr.intertec.user.dominio.User;
import cr.intertec.user.exception.UserException;
import cr.intertec.user.presentacion.Result;

/**
 * 
 * Definition of methods for generate username
 *
 */
public interface IUserGenerate {

	public Result userName(User user, String maxNumber, String maxWords, String maxPossible) throws UserException;
}
