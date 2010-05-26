package org.footware.server.services;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.services.OutlineService;
import org.footware.server.db.Track;
import org.footware.server.db.User;
import org.footware.server.db.UserUtil;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackSearchData;
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
		ArrayList<UserDTO> children = new ArrayList<UserDTO>();
		
		List<User> users = UserUtil.getAll();

		for (int i = 0; i < filter.value; i++) {
			children.add(users.get(i).getUserDTO());
		}
		return children;
	}

	@Override
	public List<TrackDTO> getTrackList(TrackSearchData filter) {
		//TODO implement
		List<TrackDTO> children = new ArrayList<TrackDTO>();
		for (int i = 0; i < filter.value; i++) {
			//TODO: children.add(new TrackDTO());
		}
		return children;
	}

}
