package org.footware.client.services;

import org.footware.shared.dto.UserSearchData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OutlineServiceAsync {
	public void getUsersTable(UserSearchData filter,AsyncCallback<String[][]> callback) throws IllegalArgumentException;
}
