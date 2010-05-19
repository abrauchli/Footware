package org.footware.client;

import java.util.List;

import org.footware.client.model.ConfigDTO;
import org.footware.client.model.TrackDTO2;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("track")
public interface TrackService extends RemoteService {
	List<TrackDTO2> getTracks(ConfigDTO config) throws IllegalArgumentException;
}
