package cr.intertec.user.presentacion.generate.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import cr.intertec.user.dominio.User;
import cr.intertec.user.exception.UserException;
import cr.intertec.user.presentacion.Result;
import cr.intertec.user.presentacion.generate.IUserGenerate;

/**
 * 
 * Implementation of methods for generate username
 *
 */
public class UserGenerateImpl implements IUserGenerate {

	@Override
	public Result userName(User user, String maxNumber, String maxWords, String maxPossible) throws UserException {
		Result result = new Result();
		result.setValid(false);
		result.setList(generateList(user, maxNumber, maxWords, maxPossible));
		return result;
	}

	/**
	 * Generate using dependency org.apache.commons
	 * 
	 * @param user
	 * @param maxNumber
	 *            Maximum numbers to combine
	 * @param maxWords
	 *            maximum words to combine
	 * @param maxPossible
	 *            maximum posible to generate
	 * @return
	 */
	private List<String> generateList(User user, String maxNumber, String maxWords, String maxPossible) {
		List<String> listUserName = new ArrayList<String>();
		/** Solution one */
		String generate = "";
		for (int i = 0; i <= Integer.parseInt(maxPossible); i++) {
			generate = RandomStringUtils.randomAlphanumeric(Integer.parseInt(maxWords))
					+ RandomStringUtils.random(Integer.parseInt(maxNumber), false, true);
			generate = user.getUserName() + generate;
			listUserName.add(generate);
		}
		return listUserName;
	}

}
