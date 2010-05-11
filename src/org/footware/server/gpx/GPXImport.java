package org.footware.server.gpx;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.footware.server.gpx.model.GPXTrack;
import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.server.gpx.model.GPXTrackSegment;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.xml.sax.SAXException;

public class GPXImport {

	private static String GPX_NAMESPACE_URI = "http://www.topografix.com/GPX/1/1";
	private static String GPX_QNAME = "tpx";
	private static String LATITUDE = "lat";
	private static String LONGITUDE = "lon";
	private static String ELEVATION = "ele";
	private static String TIME = "time";

	public GPXImport() {
		// TODO Auto-generated constructor stub
	}

	public List<GPXTrack> parseXML(InputStream in) {
		LinkedList<GPXTrack> tracks = new LinkedList<GPXTrack>();
		try {

			// Import gpx
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			factory.setSchema(schemaFactory.newSchema(new File("gpx.xsd")));

			SAXParser parser = factory.newSAXParser();
			SAXReader reader = new SAXReader(parser.getXMLReader());
			reader.setValidation(true);
			reader.setErrorHandler(new SimpleErrorHandler());

			Document document = reader.read(in);

			// Define namespaces
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(GPX_QNAME, GPX_NAMESPACE_URI);

			// Find tracks

			XPath xpathTrk = DocumentHelper.createXPath("//" + GPX_QNAME
					+ ":trk");
			xpathTrk.setNamespaceURIs(map);

			List<Element> xmlTracks = xpathTrk.selectNodes(document);
			GPXTrack track;
			System.out.println("found " + xmlTracks.size() + " tracks");
			for (Element xmlTrack : xmlTracks) {
				track = new GPXTrack();

				// Find track segments
				XPath xpathTrkseq = DocumentHelper.createXPath("//" + GPX_QNAME
						+ ":trkseq");
				xpathTrkseq.setNamespaceURIs(map);

				GPXTrackSegment trackSegment;

				List<Element> xmlTrackSegs = xpathTrkseq.selectNodes(xmlTrack);
				System.out.println("found " + xmlTrackSegs.size() + " segments");
				for (Element xmlTrackSeq : xmlTrackSegs) {
					trackSegment = new GPXTrackSegment();

					// Find track points
					XPath xpathTrkPt = DocumentHelper.createXPath("//"
							+ GPX_QNAME + ":trkpt");
					xpathTrkPt.setNamespaceURIs(map);

					GPXTrackPoint trackPoint;

					List<Element> xmlTrackPts = xpathTrkPt
							.selectNodes(xmlTrackSeq);
//					List<Element> xmlTrackPts = xmlTrackSeq.selectNodes("//trk:trkpt");

					BigDecimal latitude;
					BigDecimal longitude;
					BigDecimal elevation;
					DateTime time;
					System.out.println("found " + xmlTrackPts.size() + " points");
					for (Element xmlTrackPt : xmlTrackPts) {

						latitude = new BigDecimal(xmlTrackPt
								.attributeValue(LATITUDE));
						longitude = new BigDecimal(xmlTrackPt
								.attributeValue(LONGITUDE));
						elevation = new BigDecimal(xmlTrackPt
								.element(ELEVATION).getText());
						time = ISODateTimeFormat.dateTimeNoMillis()
								.parseDateTime(
										xmlTrackPt.element(TIME).getText());

						trackPoint = new GPXTrackPoint(latitude, longitude,
								elevation, time);
						trackSegment.addPoint(trackPoint);
					}
					track.addTrackSegment(trackSegment);
				}
				System.out.println(track.getLength());
				tracks.add(track);
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tracks;
	}
}