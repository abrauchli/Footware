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

package org.footware.server.gpx.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GPXTrack {

	List<GPXTrackSegment> segments = new LinkedList<GPXTrackSegment>();

	public GPXTrack() {
	}
	
	public void addTrackSegment(GPXTrackSegment segment) {
		segments.add(segment);
	}
	
	public void addTrackSegment(List<GPXTrackSegment> segments) {
		segments.addAll(segments);
	}
	
	public int getNumberOfDataPoints()  {
		int numberOfPoints = 0;
		for(GPXTrackSegment segment : segments) {
			numberOfPoints += segment.getNumberOfDataPoints();
		}
		return numberOfPoints;
	}
	
	public double getLength() {
		double length = 0;
		for(GPXTrackSegment segment : segments) {
			length += segment.getLength();
		}
		return length;		
	}

	public List<GPXTrackSegment> getSegments() {
		return Collections.unmodifiableList(segments);
	}
}
