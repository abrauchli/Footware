package org.footware.client.services;

import org.footware.shared.dto.UserDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
	
	/**
	 * Gets a user by the unique email address
	 * @param email the email address identifying the user
	 * @return UserDTO object with this users current state informations
	 */
	public UserDTO getUser(String email);
	
	/**
	 * Register a new user
	 * @param user user to register
	 * @return UserDTO object of the created user, null if email already exists
	 */
	public UserDTO registerUser(UserDTO user);
}
