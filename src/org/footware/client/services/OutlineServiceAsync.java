package org.footware.client.services;

import java.util.List;

import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackSearchData;
import org.footware.shared.dto.TrackVisualizationDTO;
import org.footware.shared.dto.UserDTO;
import org.footware.shared.dto.UserSearchData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OutlineServiceAsync {
	public void getUsersTable(UserSearchData filter,
			AsyncCallback<String[][]> callback) throws IllegalArgumentException;

	public void getUserList(UserSearchData filter,
			AsyncCallback<List<UserDTO>> callback);

	public void getTrackList(TrackSearchData filter,
			AsyncCallback<List<TrackDTO>> callback);

	public void getTracksTable(TrackSearchData filter,
			AsyncCallback<String[][]> callback);

	public void getTrackVisualizationList(Long id,
			AsyncCallback<List<TrackVisualizationDTO>> callback);
}
