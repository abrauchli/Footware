package org.footware.server.gpx;

import org.footware.server.gpx.model.GPXTrack;

public interface TrackVisualizationStrategy {
    
    public String getType();
    
    public void setTrack(GPXTrack track);
    
    public void execute(TrackVisualizationFactory factory);

}
