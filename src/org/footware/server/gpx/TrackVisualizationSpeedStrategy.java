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

public class TrackVisualizationSpeedStrategy implements TrackVisualizationStrategy {

    private GPXTrack track;

    @Override
    public void execute(TrackVisualizationFactory factory) {
        for(GPXTrackSegment gpxSegment : track.getSegments()) {
            for(GPXTrackPoint gpxPoint : gpxSegment.getPoints()) {
                factory.addPoint(gpxPoint.getTime().getMillis(), gpxPoint.getSpeed());
            }
        }
    }

    @Override
    public String getType() {
        return TrackVisualizationDTO.TYPE_SPEED;
    }

    @Override
    public void setTrack(GPXTrack track) {
        this.track = track;   
    }

}
