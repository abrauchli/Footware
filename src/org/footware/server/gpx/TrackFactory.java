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
