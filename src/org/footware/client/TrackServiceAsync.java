/**
 * 
 */
package org.footware.client;

import java.util.List;

import org.footware.client.model.ConfigDTO;
import org.footware.client.model.TrackDTO2;
import org.footware.client.model.TrackVisualizationDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author rene
 *
 */
public interface TrackServiceAsync {

	/**
	 * 
	 * @see org.footware.client.TrackService#greetServer(org.footware.client.model.ConfigDTO)
	 */
	void getTracks(ConfigDTO config, AsyncCallback<List<TrackDTO2>> callback);

    void getTrackVisualization(ConfigDTO config, AsyncCallback<TrackVisualizationDTO> callback);

}
