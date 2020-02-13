package logic.controller;

import logic.bean.UserBean;
import logic.dao.AbstractUserDao;
import logic.util.Session;
import logic.util.enumeration.UserTypes;

/**
 * Controller del caso d'uso "Login"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class LoginController {

	public UserTypes loginUser(UserBean bean) {
		String user = bean.getUsername();
		String passwd = bean.getPassword();
		
		UserTypes type = AbstractUserDao.getInstance().findUserByUsernameAndPassword(user, passwd);
		
		if (!type.equals(UserTypes.INVALID_USER))
			Session.getSession().setCurrUser(user);

		return type;
	}

}