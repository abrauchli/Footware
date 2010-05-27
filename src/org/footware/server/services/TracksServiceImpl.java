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

import java.util.List;

import org.footware.client.services.TrackService;
import org.footware.server.db.Comment;
import org.footware.server.db.Track;
import org.footware.server.db.util.TrackUtil;
import org.footware.shared.dto.CommentDTO;
import org.footware.shared.dto.ConfigDTO;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackVisualizationDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TracksServiceImpl extends RemoteServiceServlet implements
		TrackService {

	private static final long serialVersionUID = 1L;

	@Override
	public List<TrackDTO> getTracks(ConfigDTO config)
			throws IllegalArgumentException {
		// GPXImport importer = new GPXImport();
		// List<GPXTrack> tracks = new LinkedList<GPXTrack>();
		//		
		// importer.importTrack(new File("foo_trk.gpx"));
		//
		//		
		// LinkedList<TrackDTO> result = new LinkedList<TrackDTO>();
		// for(GPXTrack gpxTrack : tracks) {
		// result.add(TrackFactory.create(gpxTrack));
		// }
		// return result;
		return null;
	}

	@Override
	public TrackVisualizationDTO getTrackVisualization(ConfigDTO config)
			throws IllegalArgumentException {
		// // TODO Auto-generated method stub
		// GPXImport importer = new GPXImport();
		// List<GPXTrack> tracks = new LinkedList<GPXTrack>();
		//        
		// TrackVisualizationFactory factory = new TrackVisualizationFactory(new
		// TrackVisualizationElevationStrategy());
		// return factory.create(tracks.get(6));
		return null;
	}

	@Override
	public Boolean saveChanges(TrackDTO track) {
		Track t = TrackUtil.getTrackById(track.getId());
		t.setNotes(track.getNotes());
		t.setPublicity(track.getPublicity());
//		t.store();
		return true;
	}

	public Boolean deactivateTrack(TrackDTO track) {
		Track t = TrackUtil.getTrackById(track.getId());
		t.setDisabled(true);
//		t.store();
		return true;
	}

	public Boolean addComment(CommentDTO comment) {
		Comment c = new Comment(comment);
//		c.store(); TODO
		return true;
	}
}
