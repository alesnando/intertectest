package cr.intertec.user.api.v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cr.intertec.user.dominio.User;
import cr.intertec.user.exception.UserException;
import cr.intertec.user.persistencia.IUserDao;
import cr.intertec.user.presentacion.Response;
import cr.intertec.user.presentacion.Result;
import cr.intertec.user.presentacion.generate.IUserGenerate;
import cr.intertec.user.presentacion.json.IJsonTransformer;
import cr.intertec.user.util.CodeStatus;
import cr.intertec.user.util.Constants;

/**
 * 
 * Class controller methods endpoints
 *
 */
@Controller
public class UserController {

	@Autowired
	private IUserDao userDAO;

	@Autowired
	private IUserGenerate generate;

	@Autowired
	private IJsonTransformer jsonTransformer;

	/**
	 * Method exposed to check user
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param jsonIn
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void checkUserName(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String jsonIn) {
		Response<Result> response = null;
		Result result = new Result();
		try {
			/**
			 * Convert json to object
			 */
			User userNew = (User) jsonTransformer.fromJson(jsonIn, User.class);
			/**
			 * Validate length and restricted words, username is invalid throws
			 * exception
			 */
			userNew.validar(userDAO.getProperty(Constants.RESTRICTED_WORDS));
			/**
			 * Search user is registered
			 */
			User user = userDAO.findByUserName(userNew.getUserName());
			/**
			 * Unregistered user returns true and null list Registered user
			 * returns false and username list
			 */
			if (user == null) {
				result.setValid(true);
				result.setList(null);
				response = new Response<Result>(CodeStatus.OK, result);
			} else {
				result = generate.userName(userNew, userDAO.getProperty(Constants.MAXIMUM_QUANTITY_NUMBERS),
						userDAO.getProperty(Constants.MAXIMUM_QUANTITY_WORDS),
						userDAO.getProperty(Constants.MAXIMUM_POSSIBLE_USERNAME));
				List<String> listRemove = new ArrayList<String>();
				for (String userName : result.getList()) {
					User userAux = userDAO.findByUserName(userName);
					if(userAux != null){
						listRemove.add(userName);
					}
				}
				result.getList().removeAll(listRemove);
				response = new Response<Result>(CodeStatus.OK, result);
			}
			/**
			 * Convert response to json
			 */
			String jsonOut = jsonTransformer.toJson(response);
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			httpServletResponse.setContentType("application/json; charset=UTF-8");
			httpServletResponse.getWriter().println(jsonOut);

		} catch (UserException ex) {
			response = new Response<Result>(ex);
			String jsonOut = jsonTransformer.toJson(response);

			httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			httpServletResponse.setContentType("application/json; charset=UTF-8");
			try {
				httpServletResponse.getWriter().println(jsonOut);
			} catch (IOException ex1) {
				Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex1);
			}

		} catch (Exception ex) {
			httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
