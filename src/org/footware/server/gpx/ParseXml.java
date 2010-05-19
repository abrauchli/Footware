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
		GPXImport importer = new GPXImport();
		
		try {
			File test = new File("");
			System.out.println(test.getAbsolutePath());
			importer.parseXML(new FileInputStream(new File("foo_trk.gpx")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
