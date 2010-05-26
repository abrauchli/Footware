package org.footware.server.gpx;

import org.footware.server.gpx.model.GPXTrack;
import org.footware.shared.dto.TrackVisualizationDTO;

public interface TrackVisualizationFactory {
	
	/**
     * Creates a TrackVisualizationDTO of the the track according to the strategy of the factory
     * @param track the track the visualization has to be calculated from
     * @return visualizaton
     */
    public  TrackVisualizationDTO create(GPXTrack track);

}
