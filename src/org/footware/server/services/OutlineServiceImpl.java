package org.footware.server.services;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.services.OutlineService;
import org.footware.server.db.Tag;
import org.footware.server.db.Track;
import org.footware.server.db.User;
import org.footware.server.db.util.TrackUtil;
import org.footware.server.db.util.UserUtil;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackSearchData;
import org.footware.shared.dto.UserDTO;
import org.footware.shared.dto.UserSearchData;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class OutlineServiceImpl extends RemoteServiceServlet implements
		OutlineService {

	private static final long serialVersionUID = 1L;

	@Override
	public String[][] getUsersTable(UserSearchData filter) {
		List<User> l = UserUtil.getAll();
		if (!filter.admin) {
			List<User> ll = new ArrayList<User>();
			for (User u : l) {
				if (!u.isDisabled()) {
					ll.add(u);
				}
			}
			l = ll;
		}
		int n = 2;
		if (filter.admin) {
			n = 4;
		}
		if (l != null) {
			String[][] result = new String[l.size()][n];

			for (int i = 0; i < l.size(); i++) {
				result[i][0] = l.get(i).getFullName();
				result[i][1] = Integer.toString(l.get(i).getTracks().size());
				if (filter.admin) {
					result[i][2] = Boolean.toString(l.get(i).isDisabled());
					result[i][3] = Boolean.toString(l.get(i).getIsAdmin());
				}
			}
			return result;
		} else {
			return new String[1][1];
		}
	}

	@Override
	public List<UserDTO> getUserList(UserSearchData filter) {
		// TODO implement filtering
		ArrayList<UserDTO> children = new ArrayList<UserDTO>();

		List<User> users = UserUtil.getAll();
		if (users != null) {
			for (User u : users) {
				children.add(u.getUserDTO());
			}
		}
		return children;
	}

	@Override
	public List<TrackDTO> getTrackList(TrackSearchData filter) {
//		List<TrackDTO> children = new ArrayList<TrackDTO>();
//		// List<Track> tracks = TrackUtil.getAllPublicTracks();
//		List<Track> tracks = new ArrayList<Track>();
//		tracks.add(new Track(new User(new UserDTO()), "f", "ff"));
//		for (Track t : tracks) {
//			children.add(t.getTrackDTO());
//		}
//		return children;
		return getDemoTracks();
	}

	public String[][] getTracksTable(TrackSearchData filter) {
		// List<Track> tracks = TrackUtil.getAllPublicTracks();
//		List<Track> tracks = new ArrayList<Track>();
//		tracks.add(new Track(new User(new UserDTO()), "f", "ff"));
//		String[][] result = new String[tracks.size()][6];
//		for (int i = 0; i < tracks.size(); i++) {
//			result[i][0] = tracks.get(i).getUser().getFullName();
//			result[i][1] = Integer.toString(tracks.get(i).getTrackpoints());
//			result[i][2] = Double.toString(tracks.get(i).getLength());
//			result[i][3] = tracks.get(i).getStartTime().toString();
//			result[i][4] = Integer.toString(tracks.get(i).getComments().size());
//			StringBuffer b = new StringBuffer();
//			for (Tag tag : tracks.get(i).getTags()) {
//				b.append(tag.getTag() + ", ");
//			}
//			result[i][5] = b.toString();
//
//		}
//		System.out.println("This is the 5000th line of code :)");
//		return result;
		String[][] result = new String[][]{{"Florian Widmer","5","2000.0","12.1.2010","0"}};
		return result;
	}

	private List<TrackDTO> getDemoTracks() {
		List<TrackDTO> result = new ArrayList<TrackDTO>();
		TrackDTO t = new TrackDTO();
		t.setCommentsEnabled(true);
		t.setLength(2000.0);
		t.setFilename("aaa");
		t.setPublicity(5);
		UserDTO u = new UserDTO();
		u.setEmail("t@t.c");
		u.setFullName("tt");
		t.setUser(u);
		u.addTrackDTO(t);
		result.add(t);
		return result;
	}

}
