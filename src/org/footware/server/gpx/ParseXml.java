package org.footware.server.gpx;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.footware.server.gpx.model.GPXTrack;
import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.server.gpx.model.GPXTrackSegment;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.xml.sax.SAXException;

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
			importer.parseXML(new FileInputStream(new File("test.gpx")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
