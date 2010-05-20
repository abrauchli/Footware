package org.footware.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	public void login(String username, String password,
			AsyncCallback<String> callback) throws IllegalArgumentException;
}
