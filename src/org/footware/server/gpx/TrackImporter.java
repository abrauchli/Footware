package org.footware.server.gpx;

import java.io.File;
import java.util.List;

import org.footware.server.db.Track;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackVisualizationDTO;

/**
 * @author rene
 * 
 */
public interface TrackImporter {

	/**
	 * @param file
	 *            the track file to import. This method has to be called before
	 *            Object can retrieved from the TrackImporter.
	 */
	public void importTrack(File file);

	/**
	 * @return a list of tracks of the import file. Ensure that importTrack is
	 *         called before executing this method!
	 */
	public List<Track> getTracks();

	/**
	 * @return a list of speed visualizations of the import file. Ensure that
	 *         importTrack is called before executing this method!
	 */
	public List<TrackVisualizationDTO> getSpeedVisualizations();

	/**
	 * @return a list of elevation visualizations of the import file. Ensure
	 *         that importTrack is called before executing this method!
	 */
	public List<TrackVisualizationDTO> getElevationVisualizations();

}
