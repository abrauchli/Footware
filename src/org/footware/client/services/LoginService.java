package org.footware.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{
	public String login(String username, String password)
			throws IllegalArgumentException;

}
