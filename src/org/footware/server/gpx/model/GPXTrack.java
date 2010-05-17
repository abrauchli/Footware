package org.footware.server.gpx.model;

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
}
