package org.footware.server.gpx;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
	private static String GPX_NS = "tpx";
	private static String LATITUDE = "lat";
	private static String LONGITUDE = "lon";
	private static String ELEVATION = "ele";
	private static String TIME = "time";

	public GPXImport() {
		// TODO Auto-generated constructor stub
	}
	
	public void importTrack(File file) {
		List<GPXTrack> tracks = parseXML(file);
		
	}

	private List<GPXTrack> parseXML(File file) {
        LinkedList<GPXTrack> tracks = new LinkedList<GPXTrack>();
        try {

            // Parse GPX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            File xsd = new File("gpx.xsd");
            System.out.println(xsd.toString());
            factory.setSchema(schemaFactory.newSchema(xsd));

            SAXParser parser = factory.newSAXParser();
            SAXReader reader = new SAXReader(parser.getXMLReader());
            reader.setValidation(true);
            reader.setErrorHandler(new SimpleErrorHandler());


            Document document = reader.read(file);

            // Define namespaces
            HashMap<String, String> namespacesMap = new HashMap<String, String>();
            namespacesMap.put(GPX_NS, GPX_NAMESPACE_URI);

            // Find tracks
            XPath xpathTrk = DocumentHelper.createXPath("//" + GPX_NS + ":trk");
            xpathTrk.setNamespaceURIs(namespacesMap);

            List<Element> xmlTracks = xpathTrk.selectNodes(document);

            GPXTrack track;
            System.out.println("found " + xmlTracks.size() + " tracks");

            // for (Element xmlTrack : xmlTracks) {
            // Iterate about all tracks of the gpx file
            for (int currentTrackNummer = 1; currentTrackNummer <= xmlTracks.size(); currentTrackNummer++) {
                track = new GPXTrack();
                // System.out.println(xmlTrack.asXML());

                // Find track segments
                XPath xpathTrkseg = DocumentHelper.createXPath("//" + GPX_NS + ":trk[" + currentTrackNummer + "]/"
                        + GPX_NS + ":trkseg");
                xpathTrkseg.setNamespaceURIs(namespacesMap);

                List<Element> xmlTrackSegs = xpathTrkseg.selectNodes(document);
                // List<Element> xmlTrackSegs =
                // xmlTrack.selectNodes("//trkseg");

                System.out.println("found " + xmlTrackSegs.size() + " segments");

                GPXTrackSegment trackSegment;

                // for (Element xmlTrackSeq : xmlTrackSegs) {
                // Iterate above all segments of a track
                for (int currentTrackSegmentNummer = 1; currentTrackSegmentNummer <= xmlTrackSegs.size(); currentTrackSegmentNummer++) {
                    trackSegment = new GPXTrackSegment();

                    // Find track points
                    XPath xpathTrkPt = DocumentHelper.createXPath("//" + GPX_NS + ":trk[" + currentTrackNummer + "]/"
                            + GPX_NS + ":trkseg[" + currentTrackSegmentNummer + "]/" + GPX_NS + ":trkpt");
                    xpathTrkPt.setNamespaceURIs(namespacesMap);
                    List<Element> xmlTrackPts = xpathTrkPt.selectNodes(document);

                    GPXTrackPoint trackPoint;
                    BigDecimal latitude;
                    BigDecimal longitude;
                    BigDecimal elevation;
                    DateTime time;
                    System.out.println("found " + xmlTrackPts.size() + " points");
                    // Iterate above all points of a segment of a track
                    for (Element xmlTrackPt : xmlTrackPts) {

                        latitude = new BigDecimal(xmlTrackPt.attributeValue(LATITUDE));
                        longitude = new BigDecimal(xmlTrackPt.attributeValue(LONGITUDE));
                        elevation = new BigDecimal(xmlTrackPt.element(ELEVATION).getText());
                        time = ISODateTimeFormat.dateTimeNoMillis().parseDateTime(xmlTrackPt.element(TIME).getText());

                        trackPoint = new GPXTrackPoint(latitude, longitude, elevation, time);
                        trackSegment.addPoint(trackPoint);
                    }
                    track.addTrackSegment(trackSegment);
                    System.out.println();
                }
                System.out.println(track.getLength());
                System.out.println();
                System.out.println();
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