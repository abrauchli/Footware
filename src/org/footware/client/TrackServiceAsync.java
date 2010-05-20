/**
 * 
 */
package org.footware.client;

import java.util.List;

import org.footware.shared.dto.ConfigDTO;
import org.footware.shared.dto.TrackDTO2;
import org.footware.shared.dto.TrackVisualizationDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author rene
 *
 */
public interface TrackServiceAsync {

	/**
	 * 
	 * @see org.footware.client.TrackService#greetServer(org.footware.shared.dto.ConfigDTO)
	 */
	void getTracks(ConfigDTO config, AsyncCallback<List<TrackDTO2>> callback);

    void getTrackVisualization(ConfigDTO config, AsyncCallback<TrackVisualizationDTO> callback);

}
