package org.footware.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import org.footware.client.TrackService;
import org.footware.client.model.ConfigDTO;
import org.footware.client.model.TrackDTO2;
import org.footware.server.gpx.GPXImport;
import org.footware.server.gpx.TrackFactory;
import org.footware.server.gpx.model.GPXTrack;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TracksServiceImpl  extends RemoteServiceServlet implements
TrackService {

	@Override
	public List<TrackDTO2> getTracks(ConfigDTO config)
			throws IllegalArgumentException {
		GPXImport importer = new GPXImport();
		List<GPXTrack> tracks = new LinkedList<GPXTrack>();
		
		try {
			tracks = importer.parseXML(new FileInputStream(new File("foo_trk.gpx")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LinkedList<TrackDTO2> result = new LinkedList<TrackDTO2>();
		for(GPXTrack gpxTrack : tracks) {
			result.add(TrackFactory.create(gpxTrack));
		}
		return result;
	}

}
