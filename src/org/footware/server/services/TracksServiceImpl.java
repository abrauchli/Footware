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

package org.footware.server.services;

import java.util.LinkedList;
import java.util.List;

import org.footware.client.services.TrackService;
import org.footware.server.gpx.GPXImport;
import org.footware.server.gpx.TrackFactory;
import org.footware.server.gpx.TrackVisualizationElevationStrategy;
import org.footware.server.gpx.TrackVisualizationFactory;
import org.footware.server.gpx.model.GPXTrack;
import org.footware.shared.dto.ConfigDTO;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackVisualizationDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TracksServiceImpl  extends RemoteServiceServlet implements
TrackService {

	@Override
	public List<TrackDTO> getTracks(ConfigDTO config)
			throws IllegalArgumentException {
		GPXImport importer = new GPXImport();
		List<GPXTrack> tracks = new LinkedList<GPXTrack>();
		
//		try {
//			tracks = importer.parseXML(new FileInputStream(new File("foo_trk.gpx")));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		LinkedList<TrackDTO> result = new LinkedList<TrackDTO>();
		for(GPXTrack gpxTrack : tracks) {
			result.add(TrackFactory.create(gpxTrack));
		}
		return result;
	}

    @Override
    public TrackVisualizationDTO getTrackVisualization(ConfigDTO config) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        GPXImport importer = new GPXImport();
        List<GPXTrack> tracks = new LinkedList<GPXTrack>();
        
//        try {
//            tracks = importer.parseXML(new FileInputStream(new File("foo_trk.gpx")));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        
        TrackVisualizationFactory factory = new TrackVisualizationFactory(new TrackVisualizationElevationStrategy());
        return factory.create(tracks.get(6));
    }

}
