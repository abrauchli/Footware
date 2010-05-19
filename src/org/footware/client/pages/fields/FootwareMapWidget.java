/**
 * 
 */
package org.footware.client.pages.fields;

import java.util.LinkedList;
import java.util.List;

import org.footware.client.GreetingService;
import org.footware.client.GreetingServiceAsync;
import org.footware.client.TrackService;
import org.footware.client.TrackServiceAsync;
import org.footware.client.model.TrackDTO2;
import org.footware.client.model.TrackPointDTO;
import org.footware.client.model.TrackSegmentDTO;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapUnits;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.Style;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.LineString;
import org.gwtopenmaps.openlayers.client.geometry.Point;
import org.gwtopenmaps.openlayers.client.layer.OSM;
import org.gwtopenmaps.openlayers.client.layer.OSMOptions;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.layer.VectorOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * @author rene
 * 
 * 
 *         WGS84 projection : EPSG:4326
 */

public class FootwareMapWidget extends Composite {

	private final Panel panel;
	private Map map;

	private final TrackServiceAsync trackService = GWT
			.create(TrackService.class);

	public FootwareMapWidget() {
		super();
		panel = new SimplePanel();
		initWidget(panel);
		initMapWidget();

	}

	private void initMapWidget() {
		MapOptions defaultMapOptions = new MapOptions();
		// defaultMapOptions.setControls(new JObjectArray(new JSObject[] {}));
		defaultMapOptions.setNumZoomLevels(16);
		// defaultMapOptions.setMaxExtent(new
		// Bounds(-20037508.34,-20037508.34,20037508.34,20037508.34));
		// defaultMapOptions.setMaxResolution((float) 156543.0399);
		// defaultMapOptions.setProjection("EPSG:900913");
		defaultMapOptions.setProjection("EPSG:4326");
		defaultMapOptions.setMaxExtent(new Bounds(-180.0, -90.0, 180.0, 90.0));
		defaultMapOptions.setMaxResolution((float) 1.40625);
		defaultMapOptions.setUnits(MapUnits.DEGREES);
		// defaultMapOptions.setProjection("EPSG:4326");
		// defaultMapOptions.setUnits("m");
		// defaultMapOptions.setDisplayProjection(new Projection("EPSG:4326"));

		MapWidget mapWidget = new MapWidget("800px", "400px", defaultMapOptions);
		map = mapWidget.getMap();
		
		map.addControl(new LayerSwitcher());

		OSMOptions openStreetMapOptions = new OSMOptions();
		// openStreetMapOptions.setProjection("EPSG:4326");
		OSM openStreetMap = OSM.Osmarender("Base Map", openStreetMapOptions);
		openStreetMap.setIsBaseLayer(true);
		map.addLayer(openStreetMap);

		System.out.println(map.getProjection());
		System.out.println(map.getMaxResolution());
		System.out.println(map.getMaxExtent());
		System.out.println(map.getUnits());

		VectorOptions vectorLayerOptions = new VectorOptions();
		vectorLayerOptions.setProjection(map.getProjection());
		final Vector tracksLayer = new Vector("Trackname", vectorLayerOptions);
		tracksLayer.setIsBaseLayer(true);
		trackService.getTracks(null, new AsyncCallback<List<TrackDTO2>>() {

			@Override
			public void onSuccess(List<TrackDTO2> result) {
				System.out.println("hello from client");
				for (TrackDTO2 track : result) {

					for (TrackSegmentDTO segment : track.getSegments()) {
						Point[] pointArray = new Point[segment.getPoints()
								.size()];

						List<TrackPointDTO> points = segment.getPoints();
						for (int i = 0; i < points.size(); i++) {
							LonLat lonLat = new LonLat(points.get(i)
									.getLongitude(), points.get(i)
									.getLatitude());
							lonLat.transform("EPSG4326", map.getProjection());
							// System.out.println(lonLat.lon() + " : " +
							// lonLat.lat());
							pointArray[i] = new Point(lonLat.lon(), lonLat
									.lat());
						}

						LineString line = new LineString(pointArray);
						Style lineStyle = new Style();
						lineStyle.setStrokeWidth(1.0);
						lineStyle.setStrokeWidth(4.0);
						VectorFeature feature = new VectorFeature(line,
								lineStyle);

						tracksLayer.addFeature(feature);
					}
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();

			}
		});

		map.addLayer(tracksLayer);
		map.setCenter(new LonLat(0.0, 0.0), 3);

		panel.add(mapWidget);

	}

}
