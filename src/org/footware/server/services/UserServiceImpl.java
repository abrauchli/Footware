package org.footware.server.services;

import org.footware.client.services.UserService;
import org.footware.server.db.User;
import org.footware.server.db.UserUtil;
import org.footware.shared.dto.UserDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class UserServiceImpl extends RemoteServiceServlet implements
		UserService {

	@Override
	public UserDTO getUser(String email) {
		User u = UserUtil.getByEmail(email);
		if (u != null)
			return u.getUserDTO();
		return null;
	}

}
