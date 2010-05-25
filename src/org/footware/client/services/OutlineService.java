package org.footware.client.services;

import org.footware.shared.dto.UserSearchData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("outline")
public interface OutlineService extends RemoteService {
	public String[][] getUsersTable(UserSearchData filter);
		
}
