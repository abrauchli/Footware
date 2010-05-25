package org.footware.server.services;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.services.OutlineService;
import org.footware.shared.dto.UserDTO;
import org.footware.shared.dto.UserSearchData;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class OutlineServiceImpl extends RemoteServiceServlet implements
		OutlineService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String[][] getUsersTable(UserSearchData filter) {
		// TODO implement
		// suche. analog AllTracksPage
		String[][] result = new String[filter.value][];
		for (int i = 0; i < filter.value; i++) {
			result[i] = new String[] { "ttt", "23" };
		}
		return result;
	}

	@Override
	public List<UserDTO> getUserList(UserSearchData filter) {
		// TODO implement
		ArrayList<UserDTO> children = new ArrayList<UserDTO>();
		for (int i = 0; i < filter.value; i++) {
			children.add(new UserDTO());
		}
		return children;
	}

}
