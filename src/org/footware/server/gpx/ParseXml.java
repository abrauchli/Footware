package org.footware.server.gpx;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

import org.footware.server.gpx.model.GPSHelperFunctions;
import org.footware.server.gpx.model.GPXTrackPoint;
import org.joda.time.DateTime;

/**
 * 
 */

/**
 * @author rene
 * 
 */
public class ParseXml {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		GPXImport importer = new GPXImport();
//		
//		try {
//			importer.parseXML(new FileInputStream(new File("foo_trk.gpx")));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		GPXTrackPoint p1 = new GPXTrackPoint(new BigDecimal(10), new BigDecimal(10), new BigDecimal(0), new DateTime());
		GPXTrackPoint p2 = new GPXTrackPoint(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new DateTime());
		
		System.out.println(GPSHelperFunctions.getDistance(p1, p2));
	}
}
