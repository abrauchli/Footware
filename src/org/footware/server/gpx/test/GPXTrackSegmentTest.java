package org.footware.server.gpx.test;


import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.server.gpx.model.GPXTrackSegment;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GPXTrackSegmentTest {

	private GPXTrackSegment tracksegment;
	
	@Before
	public void setUp() throws Exception {
		tracksegment = new GPXTrackSegment();
	}
	
	@After
	public void tearDown() throws Exception {
		tracksegment = null;
	}
	
	
	@Test
	public void testZeroPoints() {
		assertTrue("zero length", tracksegment.getNumberOfDataPoints() == 0);
	}
	
	@Test
	public void testAddPoint() {
		assertTrue("zero length", tracksegment.getNumberOfDataPoints() == 0);
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		tracksegment.addPoint(p1);
		assertTrue("add point to track segment", tracksegment.getNumberOfDataPoints() == 1);
	}

	@Test
	public void testAddPoints() {
		assertTrue("zero length", tracksegment.getNumberOfDataPoints() == 0);
		List<GPXTrackPoint> points = new LinkedList<GPXTrackPoint>();
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(180), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(90),
				new BigDecimal(-180), new BigDecimal(0), new DateTime());
		points.add(p1);
		points.add(p2);
		tracksegment.addPoint(points);
		assertTrue("add point to track segment", tracksegment.getNumberOfDataPoints() == 2);
	}
	

}
