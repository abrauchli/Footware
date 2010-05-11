package org.footware.server.gpx.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GPXTrackSegment {

	private double length = 0;
	private List<GPXTrackPoint> points = new LinkedList<GPXTrackPoint>();

	public GPXTrackSegment() {

	}

	public GPXTrackSegment(List<GPXTrackPoint> points) {
		addPoint(points);
	}

	public void addPoint(GPXTrackPoint point) {
		// Update length of track
		if (!points.isEmpty()) {
			length += GPSHelperFunction.getDistance(points
					.get(points.size() - 1), point);
		}
		points.add(point);
	}

	public void addPoint(List<GPXTrackPoint> points) {
		// Update length of track
		if (!points.isEmpty() && !this.points.isEmpty()) {
			length += GPSHelperFunction.getDistance(this.points.get(this.points
					.size() - 1), points.get(0));
		}
		if (!points.isEmpty()) {
			for (int i = 0; i < points.size() - 1; i++) {
				length += GPSHelperFunction.getDistance(points.get(i), points
						.get(i + 1));
			}
		}
		
		this.points.addAll(points);
	}

	public List<GPXTrackPoint> getPoints() {
		return Collections.unmodifiableList(points);
	}

	public int getNumberOfDataPoints() {
		return points.size();
	}

	public double getLength() {
		return length;
	}

}
