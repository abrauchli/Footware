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

package org.footware.shared.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TrackSegmentDTO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<TrackpointDTO> points = new LinkedList<TrackpointDTO>();
	
	public TrackSegmentDTO() {
	}

	/**
	 * @return the points
	 */
	public List<TrackpointDTO> getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(List<TrackpointDTO> points) {
		this.points = points;
	}
	
	/**
	 * Adds a Trackpoint to this segment
	 * @param point point to add
	 */
	public void addPoint(TrackpointDTO point) {
		points.add(point);
	}

}
