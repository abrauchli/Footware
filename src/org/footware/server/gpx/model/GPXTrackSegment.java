package org.footware.server.gpx.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.Period;

public class GPXTrackSegment {

	// Statistics
	private double maxSpeed = 0.0;
	private BigDecimal minElevation = new BigDecimal(Double.MAX_VALUE);
	private BigDecimal maxElevation = new BigDecimal(Double.MIN_VALUE);
	private double length = 0;

	private List<GPXTrackPoint> points = new LinkedList<GPXTrackPoint>();

	public GPXTrackSegment() {

	}

	public GPXTrackSegment(List<GPXTrackPoint> points) {
		addPoint(points);
	}

	/**
	 * Append a point to the segment
	 * 
	 * @param point
	 *            to append
	 */
	public void addPoint(GPXTrackPoint point) {
		if (!points.isEmpty()) {
			// Update length of track
			length += GPSHelperFunctions.getDistance(points
					.get(points.size() - 1), point);

			// Update max speed
			double speed = GPSHelperFunctions.getSpeed(points
					.get(points.size() - 1), point);
			point.setSpeed(speed);
			if (maxSpeed < speed) {
				maxSpeed = speed;
			}
		}

		// Update elevation
		updateEleavtion(point);

		points.add(point);
	}

	/**
	 * Append connected points to the segment.
	 * 
	 * @param points
	 *            to append
	 */
	public void addPoint(List<GPXTrackPoint> points) {
		// Update length of track:

		// Update connection between old points and new points
		if (!points.isEmpty() && !this.points.isEmpty()) {
			length += GPSHelperFunctions.getDistance(this.points
					.get(this.points.size() - 1), points.get(0));

			// Update max speed
			double speed = GPSHelperFunctions.getSpeed(this.points
					.get(this.points.size() - 1), points.get(0));
			points.get(0).setSpeed(speed);
			if (maxSpeed < speed) {
				maxSpeed = speed;
			}
		}

		if (!points.isEmpty()) {
			if (points.size() == 1) {
				addPoint(points.get(0));
			} else {
				updateEleavtion(points.get(0));
				for (int i = 1; i < points.size(); i++) {
					// Add length of new points
					length += GPSHelperFunctions.getDistance(points.get(i-1),
							points.get(i));
					// Update max speed
					double speed = GPSHelperFunctions.getSpeed(points.get(i-1),
							points.get(i));
					points.get(i).setSpeed(speed);
					if (maxSpeed < speed) {
						maxSpeed = speed;
					}
					// update elevation
					updateEleavtion(points.get(i));
				}
			}

		}

		this.points.addAll(points);
	}

	/**
	 * Returns a unmodifiable list with all points of the segment
	 * 
	 * @return list with points
	 */
	public List<GPXTrackPoint> getPoints() {
		return Collections.unmodifiableList(points);
	}

	/**
	 * 
	 * @return number of points of segment
	 */
	public int getNumberOfDataPoints() {
		return points.size();
	}

	/**
	 * 
	 * @return length in meter of the segment
	 */
	public double getLength() {
		return length;
	}

	/**
	 * 
	 * @return time difference between first and last point of segment
	 */
	public Period getPeriod() {
		return GPSHelperFunctions.getTimeDifference(points.get(0), points
				.get(points.size() - 1));
	}

	/**
	 * 
	 * @return average speed of segment in m / s
	 */
	public double getAverageSpeed() {
		return length / getPeriod().getSeconds();
	}
	
	public double getMaxSpeed() {
		return maxSpeed;
	}
	
	public BigDecimal getMaxElevation() {
		return maxElevation;
	}
	
	public BigDecimal getMinElevation() {
		return minElevation;
	}

	private void updateEleavtion(GPXTrackPoint point) {
		BigDecimal elevation = point.getElevation();
		if (elevation.compareTo(minElevation) < 0) {
			minElevation = elevation;
		}
		if (elevation.compareTo(maxElevation) > 0) {
			maxElevation = elevation;
		}
	}

}
