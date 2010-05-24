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

import org.joda.time.DateTime;
import org.joda.time.Period;

public class GPSHelperFunctions {

	private static int EARTHRADIUS = 6371000;

	/**
	 * returns the distance in meter between two points approximated by the haversine
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
	
	/**
	 * Return the speed in m/s 
	 * @param startPoint the start point
	 * @param endPoint the end point
	 * @return
	 */
	public static double getSpeed(GPXTrackPoint startPoint,
			GPXTrackPoint endPoint) {
		double distance = getDistance(startPoint, endPoint);
		Period time = getTimeDifference(startPoint, endPoint);
		return distance / time.getSeconds();
	}

}
