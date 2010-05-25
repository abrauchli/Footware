package org.footware.server.gpx.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.footware.server.gpx.model.GPSHelperFunctions;
import org.footware.server.gpx.model.GPXTrackPoint;
import org.joda.time.DateTime;
import org.junit.Test;

public class GPSHelperFunctionTest {

	@Test
	public void testDistanceInversibility() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(10),
				new BigDecimal(10), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(0), new BigDecimal(
				0), new BigDecimal(0), new DateTime());

		double dist1 = GPSHelperFunctions.getDistance(p1, p2);
		double dist2 = GPSHelperFunctions.getDistance(p2, p1);
		assertEquals("distance inverse", dist1, dist2, 0.1);
	}

	@Test
	public void testDistanceGreaterZero1() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		double dist1 = GPSHelperFunctions.getDistance(p1, p2);
		assertTrue("distance greater zero 1", dist1 >= 0.0);
	}

	@Test
	public void testDistanceGreaterZero2() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(-90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		double dist1 = GPSHelperFunctions.getDistance(p1, p2);
		assertTrue("distance greater zero 2", dist1 >= 0.0);
	}
	
	@Test
	public void testDistanceGreaterZero3() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(-180), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		double dist1 = GPSHelperFunctions.getDistance(p1, p2);
		assertTrue("distance greater zero 3", dist1 >= 0.0);
	}
	
	@Test
	public void testDistanceGreaterZero4() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(-90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		double dist1 = GPSHelperFunctions.getDistance(p1, p2);
		assertTrue("distance greater zero 4", dist1 >= 0.0);
	}
	
	@Test
	public void testDistanceGreaterZero5() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(-180), new BigDecimal(0), new DateTime());
		double dist1 = GPSHelperFunctions.getDistance(p1, p2);
		assertTrue("distance greater zero 5", dist1 >= 0.0);
	}
	
	@Test
	public void testDistanceGreaterZero6() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(-90),
				new BigDecimal(-180), new BigDecimal(0), new DateTime());
		double dist1 = GPSHelperFunctions.getDistance(p1, p2);
		assertTrue("distance greater zero 6", dist1 >= 0.0);
	}
	
	@Test
	public void testDistanceGreaterZero7() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(-90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(-90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		double dist1 = GPSHelperFunctions.getDistance(p1, p2);
		assertTrue("distance greater zero 7", dist1 >= 0.0);
	}
	
	@Test
	public void testDistanceGreaterZero8() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(-180), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(-90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		double dist1 = GPSHelperFunctions.getDistance(p1, p2);
		assertTrue("distance greater zero 8", dist1 >= 0.0);
	}
	
	@Test
	public void testDistanceGreaterZero9() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(-90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(-180), new BigDecimal(0), new DateTime());
		double dist1 = GPSHelperFunctions.getDistance(p1, p2);
		assertTrue("distance greater zero 9", dist1 >= 0.0);
	}
	
	@Test
	public void testDistanceGreaterZero10() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(0),
				new BigDecimal(0), new BigDecimal(0), new DateTime());
		double dist1 = GPSHelperFunctions.getDistance(p1, p2);
		assertTrue("distance greater zero 10", dist1 >= 0.0);
	}
	
	@Test
	public void testTimeDifference() {
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(180), new BigDecimal(0), new DateTime(0));
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(0),
				new BigDecimal(0), new BigDecimal(0), new DateTime(100));
		assertTrue(GPSHelperFunctions.getTimeDifference(p1, p2).getMillis() == 100);	
	}

}
