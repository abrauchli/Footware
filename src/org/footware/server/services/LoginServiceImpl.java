package org.footware.server.services;

import org.footware.client.services.LoginService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

	@Override
	public String login(String username, String password)
			throws IllegalArgumentException {
		//TODO andy hier sollte eine session id generiert und der user authentifiziert werden.
		return "1234";
	}

}
