/*
 * Copyright 2010 Andreas Brauchli, René Buffat, Florian Widmer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 */
package org.footware.server.gpx;

import java.util.Date;

import org.footware.server.db.Track;
import org.footware.server.db.TrackSegment;
import org.footware.server.db.Trackpoint;
import org.footware.server.gpx.model.GPXTrack;
import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.server.gpx.model.GPXTrackSegment;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackSegmentDTO;
import org.footware.shared.dto.TrackpointDTO;

/**
 * @author rene
 */
public class TrackFactory {

    public static Track create(GPXTrack inputTrack) {
        double minLatitude = Double.MAX_VALUE;
        double maxLatitude = Double.MIN_VALUE;
        double minLongitude = Double.MAX_VALUE;
        double maxLongitude = Double.MIN_VALUE;
        
        Track track = new Track(null,null,null);

        for (GPXTrackSegment gpxSegment : inputTrack.getSegments()) {
            TrackSegment segment = new TrackSegment();
            for (GPXTrackPoint gpxPoint : gpxSegment.getPoints()) {
                double longitude = gpxPoint.getLongitude().doubleValue();
                double latitude = gpxPoint.getLatitude().doubleValue();
                double elevation = gpxPoint.getElevation().doubleValue();
                Date time = gpxPoint.getTime().toDate();
                
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
                segment.addTrackpoint(new Trackpoint(segment,longitude, latitude,elevation,time,gpxPoint.getSpeed()));
            }
            track.addSegment(segment);
        }
        
        track.setMidLatitude((minLatitude+maxLatitude)/2);
        track.setMidLongitude((minLongitude+maxLongitude)/2);
        track.setLength(inputTrack.getLength());
        track.setStartTime(inputTrack.getSegments().get(0).getPoints().get(0).getTime().toDate());
        track.setTrackpoints(inputTrack.getNumberOfDataPoints());
        return track;
    }

}
