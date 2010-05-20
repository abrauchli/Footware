/**
 * 
 */
package org.footware.server.gpx;

import org.footware.server.gpx.model.GPXTrack;
import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.server.gpx.model.GPXTrackSegment;
import org.footware.shared.dto.TrackDTO2;
import org.footware.shared.dto.TrackPointDTO;
import org.footware.shared.dto.TrackSegmentDTO;

/**
 * @author rene
 */
public class TrackFactory {

    public static TrackDTO2 create(GPXTrack inputTrack) {
        double minLatitude = Double.MAX_VALUE;
        double maxLatitude = Double.MIN_VALUE;
        double minLongitude = Double.MAX_VALUE;
        double maxLongitude = Double.MIN_VALUE;

        TrackDTO2 track = new TrackDTO2();

        for (GPXTrackSegment gpxSegment : inputTrack.getSegments()) {
            TrackSegmentDTO segment = new TrackSegmentDTO();
            for (GPXTrackPoint gpxPoint : gpxSegment.getPoints()) {
                double longitude = gpxPoint.getLongitude().doubleValue();
                double latitude = gpxPoint.getLatitude().doubleValue();
                if (minLongitude > longitude) {
                    minLongitude = longitude;
                }
                if (maxLongitude < longitude) {
                    maxLongitude = longitude;
                }
                if (minLatitude > latitude) {
                    minLatitude = latitude;
                }
                if (maxLatitude < latitude) {
                    maxLatitude = latitude;
                }
                segment.addPoint(new TrackPointDTO(longitude, latitude));
            }
            track.addSegment(segment);
        }
        
        track.setMidLatitude((minLatitude+maxLatitude)/2);
        track.setMidLongitude((minLongitude+maxLongitude)/2);
        return track;
    }

}
