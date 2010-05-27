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

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.footware.server.db.Track;
import org.footware.server.db.TrackVisualization;
import org.footware.server.db.User;
import org.footware.shared.dto.TrackVisualizationDTO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Utility class for track specific operations
 */
public class TrackUtil {

	/**
	 * Gets a list of all enabled public tracks
	 * 
	 * @return a list of all enabled public tracks
	 */
	@SuppressWarnings("unchecked")
	public static List<Track> getAllPublicTracks() {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		Query q = s.getNamedQuery("tracks.getAllPublic");
		List<Track> res = null;
		try {
			res = (List<Track>) q.list();
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
		}
		return res;
	}

	public static Track getTrackById(Long id) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		Query q = s.getNamedQuery("tracks.getTrackById");
		q.setParameter("id", id);
		Track res = null;
		try {
			res = (Track) q.uniqueResult();
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
		}
		return res;
	}
	
	public static Set<TrackVisualization> getTrackVisualizationsById(Long id) {
		Track track = getTrackById(id);
		return track.getVisualizations();
	}

}
