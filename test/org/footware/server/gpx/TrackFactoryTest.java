/**
 * 
 */
package org.footware.server.gpx;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.footware.server.db.Track;
import org.footware.server.db.TrackSegment;
import org.footware.server.db.Trackpoint;
import org.footware.server.gpx.model.GPXTrack;
import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.server.gpx.model.GPXTrackSegment;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackSegmentDTO;
import org.footware.shared.dto.TrackpointDTO;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author rene
 * 
 */
public class TrackFactoryTest {

	GPXTrack track = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		track = new GPXTrack();
		GPXTrackSegment segment = new GPXTrackSegment();
		segment.addPoint(new GPXTrackPoint(new BigDecimal(0),
				new BigDecimal(0), new BigDecimal(0), new DateTime()));
		segment.addPoint(new GPXTrackPoint(new BigDecimal(10), new BigDecimal(
				10), new BigDecimal(0), new DateTime()));
		track.addTrackSegment(segment);

		GPXTrackSegment segment2 = new GPXTrackSegment();
		segment2.addPoint(new GPXTrackPoint(new BigDecimal(0),
				new BigDecimal(0), new BigDecimal(0), new DateTime()));
		segment2.addPoint(new GPXTrackPoint(new BigDecimal(10), new BigDecimal(
				10), new BigDecimal(0), new DateTime()));
		track.addTrackSegment(segment2);

	}

	@After
	public void tearDown() throws Exception {
		track = null;
	}

	/**
	 * Test method for
	 * {@link org.footware.server.gpx.TrackFactory#create(org.footware.server.gpx.model.GPXTrack)}
	 * .
	 */
	@Test
	public void testCreateSize() {
		TrackDTO newTrack = TrackFactory.create(track);
		assertTrue("Equal segment size", newTrack.getSegments().size() == track
				.getSegments().size());
	}

	/**
	 * Test method for
	 * {@link org.footware.server.gpx.TrackFactory#create(org.footware.server.gpx.model.GPXTrack)}
	 * .
	 */
	@Test
	public void testCreateLength() {
		TrackDTO newTrack = TrackFactory.create(track);
		assertTrue("Equal length", newTrack.getLength() == track.getLength());
	}

	/**
	 * Test method for
	 * {@link org.footware.server.gpx.TrackFactory#create(org.footware.server.gpx.model.GPXTrack)}
	 * .
	 */
	@Test
	public void testCreateStartTime() {
		TrackDTO newTrack = TrackFactory.create(track);
		assertTrue("Equal start time", newTrack.getStartTime().compareTo(
				track.getSegments().get(0).getPoints().get(0).getTime()
						.toDate()) == 0);
	}

	/**
	 * Test method for
	 * {@link org.footware.server.gpx.TrackFactory#create(org.footware.server.gpx.model.GPXTrack)}
	 * .
	 */
	@Test
	public void testCreateNumberOfPoints() {
		TrackDTO newTrack = TrackFactory.create(track);
		assertTrue("Equal number of point", newTrack.getTrackpoints() == track
				.getNumberOfDataPoints());
	}

	/**
	 * Test method for
	 * {@link org.footware.server.gpx.TrackFactory#create(org.footware.server.gpx.model.GPXTrack)}
	 * .
	 */
	@Test
	public void testCreateMidLatitude() {
		TrackDTO newTrack = TrackFactory.create(track);
		double minLatitude = 0;
		double maxLatitude = 0;
		for (TrackSegmentDTO segment : newTrack.getSegments()) {
			for (TrackpointDTO point : segment.getPoints()) {
				double latitude = point.getLatitude();

				if (minLatitude > latitude) {
					minLatitude = latitude;
				}

				if (maxLatitude < latitude) {
					maxLatitude = latitude;
				}
			}
		}
		assertTrue("Equal mid latitude",
				(minLatitude + maxLatitude) / 2 == newTrack.getMidLatitude());
	}

	/**
	 * Test method for
	 * {@link org.footware.server.gpx.TrackFactory#create(org.footware.server.gpx.model.GPXTrack)}
	 * .
	 */
	@Test
	public void testCreateMidLongitude() {
		TrackDTO newTrack = TrackFactory.create(track);
		double minLongitude = 0;
		double maxLongitude = 0;
		for (TrackSegmentDTO segment : newTrack.getSegments()) {
			for (TrackpointDTO point : segment.getPoints()) {
				double longitude = point.getLongitude();

				if (minLongitude > longitude) {
					minLongitude = longitude;
				}

				if (maxLongitude < longitude) {
					maxLongitude = longitude;
				}

			}
		}
		assertTrue("Equal mid longitude",
				(minLongitude + maxLongitude) / 2 == newTrack.getMidLongitude());
	}

}
