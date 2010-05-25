package org.footware.client.services;

import org.footware.shared.dto.UserDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService{
	public UserDTO getUser(String email);
}
