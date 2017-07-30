package cr.intertec.appuser.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import cr.intertec.appuser.client.UserClient;
import cr.intertec.appuser.dominio.User;
import cr.intertec.userapp.presentacion.ResponseService;
import cr.intertec.userapp.presentacion.Result;

/**
 * 
 * View Controller Class
 *
 */
@ManagedBean
public class UserView {

	private User user;
	private List<String> list;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public UserView() {
		this.user = new User();
		this.list = new ArrayList<String>();
	}

	/**
	 * Event acept
	 */
	public void acept() {
		UserClient userClient = new UserClient();
		ResponseService<Result> response = userClient.checkUsername(user);
		if (response.getCode() == 200) {
			if (response.getDato().isValid()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Welcome " + user.getFirstName() + " " + user.getLastName()));
			} else {		
				list = response.getDato().getList();
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(response.getTrace().getErrors().get(0)));
		}
	}
}
