package org.footware.server.services;

import org.footware.client.services.LoginService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

	@Override
	public String login(String username, String password)
			throws IllegalArgumentException {
		//TODO do something meaningful here.
		return "1234";
	}

}
