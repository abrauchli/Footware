/**
 * 
 */
package org.footware.server.gpx;

import org.footware.client.model.TrackVisualizationDTO;
import org.footware.server.gpx.model.GPXTrack;
import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.server.gpx.model.GPXTrackSegment;

/**
 * @author rene
 *
 */
public class TrackVisualizationElevationStrategy implements TrackVisualizationStrategy {

    
    private GPXTrack track;
    
    /* (non-Javadoc)
     * @see org.footware.server.gpx.TrackVisualizationStrategy#execute(org.footware.server.gpx.TrackVisualizationFactory)
     */
    @Override
    public void execute(TrackVisualizationFactory factory) {
        for(GPXTrackSegment gpxSegment : track.getSegments()) {
            for(GPXTrackPoint gpxPoint : gpxSegment.getPoints()) {
                factory.addPoint(gpxPoint.getTime().getMillis(), gpxPoint.getElevation().doubleValue());
            }
        }
    }

    /* (non-Javadoc)
     * @see org.footware.server.gpx.TrackVisualizationStrategy#getType()
     */
    @Override
    public String getType() {
        return TrackVisualizationDTO.TYPE_ELEVATION;
    }

    /* (non-Javadoc)
     * @see org.footware.server.gpx.TrackVisualizationStrategy#setTrack(org.footware.server.gpx.model.GPXTrack)
     */
    @Override
    public void setTrack(GPXTrack track) {
        this.track = track;  
    }

}
