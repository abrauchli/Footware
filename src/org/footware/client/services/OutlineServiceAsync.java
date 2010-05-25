package org.footware.client.services;

import java.util.List;

import org.footware.shared.dto.UserDTO;
import org.footware.shared.dto.UserSearchData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OutlineServiceAsync {
	public void getUsersTable(UserSearchData filter,
			AsyncCallback<String[][]> callback) throws IllegalArgumentException;

	public void getUserList(UserSearchData filter,
			AsyncCallback<List<UserDTO>> callback);
}
