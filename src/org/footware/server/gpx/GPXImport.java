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

package org.footware.server.gpx;

import java.io.File;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class GPXImport {

	private static String GPX_NAMESPACE_URI_1_1 = "http://www.topografix.com/GPX/1/1";
	private static String GPX_NAMESPACE_URI_1_0 = "http://www.topografix.com/GPX/1/0";
	private static String GPX_NS = "tpx";
	private static String LATITUDE = "lat";
	private static String LONGITUDE = "lon";
	private static String ELEVATION = "ele";
	private static String TIME = "time";
	private Logger logger;

	private String GPX_NAMESPACE_URI = GPX_NAMESPACE_URI_1_1;

	public GPXImport() {
		logger = LoggerFactory.getLogger(GPXImport.class);
	}

	public void importTrack(File file) {
		logger.info("Import file: " + file.getAbsolutePath());
		List<GPXTrack> tracks = parseXML(file);

	}

	private List<GPXTrack> parseXML(File file) {
		LinkedList<GPXTrack> tracks = new LinkedList<GPXTrack>();
		try {
			long startTime = System.currentTimeMillis();
			logger.info("Start parsing @" + startTime);

			// Determine GPX Version
			SAXReader xmlReader = new SAXReader();
			Document document = xmlReader.read(file);
			String version = document.getRootElement()
					.attributeValue("version");

			File xsd = null;

			if (version.equals("1.1")) {
				logger.info("Detected gpx version " + version + "  +"
						+ (System.currentTimeMillis() - startTime));
				xsd = new File("gpx_1_1.xsd");
				GPX_NAMESPACE_URI = GPX_NAMESPACE_URI_1_1;
			} else if (version.equals("1.0")) {
				logger.info("Detected gpx version '" + version + "'  +"
						+ (System.currentTimeMillis() - startTime));
				xsd = new File("gpx_1_0.xsd");
				GPX_NAMESPACE_URI = GPX_NAMESPACE_URI_1_0;
			} else {
				logger.info("No supported version detected: " + version + "  +"
						+ (System.currentTimeMillis() - startTime));
				// As default we try version 1.1
				xsd = new File("gpx_1_1.xsd");
				GPX_NAMESPACE_URI = GPX_NAMESPACE_URI_1_1;
			}

			// Parse GPX
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");

			factory.setSchema(schemaFactory.newSchema(xsd));

			SAXParser parser = factory.newSAXParser();
			SAXReader reader = new SAXReader(parser.getXMLReader());
			reader.setValidation(true);
			reader.setErrorHandler(new SimpleErrorHandler());

			document = reader.read(file);
			logger.info("End parsing +"
					+ (System.currentTimeMillis() - startTime));

			// Define namespaces
			HashMap<String, String> namespacesMap = new HashMap<String, String>();
			namespacesMap.put(GPX_NS, GPX_NAMESPACE_URI);

			// Find tracks
			logger.info("Search tracks +"
					+ (System.currentTimeMillis() - startTime));
			XPath xpathTrk = DocumentHelper.createXPath("//" + GPX_NS + ":trk");
			xpathTrk.setNamespaceURIs(namespacesMap);

			List<Element> xmlTracks = xpathTrk.selectNodes(document);
			logger.info("Found " + xmlTracks.size() + " tracks +"
					+ (System.currentTimeMillis() - startTime));

			GPXTrack track;

			// for (Element xmlTrack : xmlTracks) {
			// Iterate about all tracks of the gpx file
			for (int currentTrackNummer = 1; currentTrackNummer <= xmlTracks
					.size(); currentTrackNummer++) {
				logger.info("Start track " + currentTrackNummer + " +"
						+ (System.currentTimeMillis() - startTime));

				track = new GPXTrack();
				// System.out.println(xmlTrack.asXML());

				// Find track segments
				XPath xpathTrkseg = DocumentHelper.createXPath("//" + GPX_NS
						+ ":trk[" + currentTrackNummer + "]/" + GPX_NS
						+ ":trkseg");
				xpathTrkseg.setNamespaceURIs(namespacesMap);

				List<Element> xmlTrackSegs = xpathTrkseg.selectNodes(document);
				logger.info("Found " + xmlTrackSegs.size()
						+ " segments for track " + currentTrackNummer + " +"
						+ (System.currentTimeMillis() - startTime));

				// List<Element> xmlTrackSegs =
				// xmlTrack.selectNodes("//trkseg");

				GPXTrackSegment trackSegment;

				// for (Element xmlTrackSeq : xmlTrackSegs) {
				// Iterate above all segments of a track
				for (int currentTrackSegmentNummer = 1; currentTrackSegmentNummer <= xmlTrackSegs
						.size(); currentTrackSegmentNummer++) {
					trackSegment = new GPXTrackSegment();

					// Find track points
					XPath xpathTrkPt = DocumentHelper.createXPath("//" + GPX_NS
							+ ":trk[" + currentTrackNummer + "]/" + GPX_NS
							+ ":trkseg[" + currentTrackSegmentNummer + "]/"
							+ GPX_NS + ":trkpt");
					xpathTrkPt.setNamespaceURIs(namespacesMap);
					List<Element> xmlTrackPts = xpathTrkPt
							.selectNodes(document);

					logger.info("Found " + xmlTrackPts.size()
							+ " points for segment "
							+ currentTrackSegmentNummer + " for track "
							+ currentTrackNummer + " +"
							+ (System.currentTimeMillis() - startTime));

					GPXTrackPoint trackPoint;
					BigDecimal latitude;
					BigDecimal longitude;
					BigDecimal elevation;
					DateTime time;

					// Iterate above all points of a segment of a track
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
				tracks.add(track);
				
			}
			logger.info("Done parsing +"
					+ (System.currentTimeMillis() - startTime));

		} catch (ParserConfigurationException e) {
			logger.error("ParserConfigurationException", e);
			e.printStackTrace();
		} catch (SAXException e) {
			logger.error("SAXException", e);
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.error("DocumentException", e);
			e.printStackTrace();
		}

		return tracks;
	}
}