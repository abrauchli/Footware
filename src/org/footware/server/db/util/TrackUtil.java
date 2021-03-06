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

package org.footware.server.db.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.footware.server.db.Track;
import org.footware.server.db.TrackVisualization;
import org.footware.server.db.User;

/**
 * Utility class for track specific operations
 */
public class TrackUtil {

	
	//@NamedQueries(value = {
//	//Get all public tracks
//	@NamedQuery(name = "tracks.getAllPublic", query = "FROM Track t WHERE t.disabled=0 AND t.publicity=5"),
//	@NamedQuery(name="tracks.getTrackById", query= "FROM Track t where t.id = :id")
//})
	
	/**
	 * Gets a list of all enabled public tracks
	 * 
	 * @return a list of all enabled public tracks
	 */
	public static List<Track> getAllPublicTracks() {
		ResultSet r = null;
		String qry = "SELECT id FROM Track t WHERE t.disabled=0 AND t.publicity=5";
		try {
			r = DB.query(qry);
		} catch (Exception e) {
			System.err.println("Error with query: "+ qry);
			e.printStackTrace();
		}

		LinkedList<Track> res = new LinkedList<Track>();
		if (r != null) {
			int i = 1;
			try {
				while(r.next()) {
					res.add(new Track(r.getLong(i)));
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return res;
	}

}
