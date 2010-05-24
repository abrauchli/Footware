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

public class TrackDTO2 implements IsSerializable {
    
	List<TrackSegmentDTO> segments = new LinkedList<TrackSegmentDTO>();
	private double midLongitude;
	private double midLatitude;

	public TrackDTO2() {
	}

	/**
	 * @return the segments
	 */
	public List<TrackSegmentDTO> getSegments() {
		return segments;
	}
	
	/**
	 * @param segment the segment to add
	 */
	public void addSegment(TrackSegmentDTO segment) {
		this.segments.add(segment);
	}

	/**
	 * @param segments the segments to add
	 */
	public void addSegments(List<TrackSegmentDTO> segments) {
		this.segments.addAll(segments);
	}

    /**
     * @return the midLongitude
     */
    public double getMidLongitude() {
        return midLongitude;
    }

    /**
     * @param midLongitude the midLongitude to set
     */
    public void setMidLongitude(double midLongitude) {
        this.midLongitude = midLongitude;
    }

    /**
     * @return the midLatitude
     */
    public double getMidLatitude() {
        return midLatitude;
    }

    /**
     * @param midLatitude the midLatitude to set
     */
    public void setMidLatitude(double midLatitude) {
        this.midLatitude = midLatitude;
    }

}
