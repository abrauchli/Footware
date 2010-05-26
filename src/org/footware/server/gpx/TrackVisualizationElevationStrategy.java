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

import org.footware.server.db.Track;
import org.footware.server.db.TrackSegment;
import org.footware.server.db.Trackpoint;
import org.footware.shared.dto.TrackVisualizationDTO;

/**
 * @author rene
 *
 */
public class TrackVisualizationElevationStrategy implements TrackVisualizationStrategy {

    
    private Track track;
    
    /* (non-Javadoc)
     * @see org.footware.server.gpx.TrackVisualizationStrategy#execute(org.footware.server.gpx.TrackVisualizationFactory)
     */
    @Override
    public void execute(TrackVisualizationFactoryStrategyView factory) {
		for (TrackSegment segment : track.getSegments()) {
            for(Trackpoint point : segment.getTrackpoints()) {
                factory.addPoint(point.getTime().getTime(), point.getElevation());
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
    public void setTrack(Track track) {
        this.track = track;  
    }

}
