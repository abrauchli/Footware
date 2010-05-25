package org.footware.client.services;

import org.footware.shared.dto.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	public void getUser(String email, AsyncCallback<UserDTO> callback)
			throws IllegalArgumentException;
}