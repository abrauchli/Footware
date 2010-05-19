/**
 * 
 */
package org.footware.server.gpx;

import java.util.List;

import org.footware.client.model.TrackDTO2;
import org.footware.client.model.TrackPointDTO;
import org.footware.client.model.TrackSegmentDTO;
import org.footware.server.gpx.model.GPXTrack;
import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.server.gpx.model.GPXTrackSegment;

/**
 * @author rene
 *
 */
public class TrackFactory {
	
	public static TrackDTO2 create(GPXTrack inputTrack) {
		TrackDTO2 track = new TrackDTO2();
		
		for(GPXTrackSegment gpxSegment : inputTrack.getSegments()) {
			TrackSegmentDTO segment = new TrackSegmentDTO();
			for(GPXTrackPoint gpxPoint : gpxSegment.getPoints()) {
				segment.addPoint(new TrackPointDTO(gpxPoint.getLongitude().doubleValue(),gpxPoint.getLatitude().doubleValue()));
			}
			track.addSegment(segment);
		}
		return track;
	}
	

}
