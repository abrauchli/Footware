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

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TrackSegmentDTO  implements IsSerializable {
	List<TrackPointDTO> points = new LinkedList<TrackPointDTO>();
	
	public TrackSegmentDTO() {
	}

	/**
	 * @return the points
	 */
	public List<TrackPointDTO> getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(List<TrackPointDTO> points) {
		this.points = points;
	}
	
	public void addPoint(TrackPointDTO point) {
		points.add(point);
	}
	
	public void addPoint(List<TrackPointDTO> points) {
		points.addAll(points);
	}

}
