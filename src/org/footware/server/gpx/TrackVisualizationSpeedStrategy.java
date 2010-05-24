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

import org.footware.server.gpx.model.GPXTrack;
import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.server.gpx.model.GPXTrackSegment;
import org.footware.shared.dto.TrackVisualizationDTO;

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
