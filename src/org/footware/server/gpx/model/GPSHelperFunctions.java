package org.footware.server.gpx.model;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class GPSHelperFunctions {

	private static int EARTHRADIUS = 6371;

	/**
	 * returns the distance between two points approximated by the haversine
	 * formula.
	 * 
	 * @param startPoint
	 *            the start point to calculate the distance from
	 * @param endPoint
	 *            the end point to calculate the distance to
	 * @return
	 */
	public static double getDistance(GPXTrackPoint startPoint,
			GPXTrackPoint endPoint) {
		double lat1 = startPoint.getLatitude().doubleValue();
		double lat2 = endPoint.getLatitude().doubleValue();
		double lon1 = startPoint.getLongitude().doubleValue();
		double lon2 = endPoint.getLongitude().doubleValue();
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
				* Math.sin(dLon / 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return EARTHRADIUS * c;
	}

	public static Period getTimeDifference(GPXTrackPoint startPoint,
			GPXTrackPoint endPoint) {
		DateTime startTime = startPoint.getTime();
		DateTime endTime = endPoint.getTime();
		return new Period(startTime, endTime);
	}

}
