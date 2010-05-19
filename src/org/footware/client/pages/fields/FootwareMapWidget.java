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
 * @author rene WGS84 projection : EPSG:4326
 */

public class FootwareMapWidget extends Composite {

    private static String EPSG4326 = "EPSG:4326";

    private final Panel panel;
    private Map map;

    public FootwareMapWidget() {
        super();
        panel = new SimplePanel();
        initWidget(panel);
        initMapWidget();
    }

    private void initMapWidget() {
        MapOptions defaultMapOptions = new MapOptions();
        defaultMapOptions.setNumZoomLevels(19);
        defaultMapOptions.setMaxExtent(new Bounds(-20037508.34, -20037508.34, 20037508.34, 20037508.34));
        defaultMapOptions.setMaxResolution((float) 156543.0399);
        defaultMapOptions.setProjection("EPSG:900913");
        defaultMapOptions.setDisplayProjection(new Projection(EPSG4326));

        MapWidget mapWidget = new MapWidget("800px", "400px", defaultMapOptions);
        map = mapWidget.getMap();

        map.addControl(new LayerSwitcher());
        OSM openStreetMap = OSM.Osmarender("Base Map");
        openStreetMap.setIsBaseLayer(true);
        map.addLayer(openStreetMap);

        LonLat center =new LonLat(47.0, 8.0);
        center.transform(EPSG4326, map.getProjection());
        map.setCenter(center, 3);

        panel.add(mapWidget);
    }

    public void displayTracks(TrackDTO2 track) {
        VectorOptions vectorLayerOptions = new VectorOptions();
        vectorLayerOptions.setProjection(map.getProjection());
        final Vector tracksLayer = new Vector("Trackname", vectorLayerOptions);

        for (TrackSegmentDTO segment : track.getSegments()) {
            Point[] pointArray = new Point[segment.getPoints().size()];

            List<TrackPointDTO> points = segment.getPoints();
            for (int i = 0; i < points.size(); i++) {
                LonLat lonLat = new LonLat(points.get(i).getLongitude(), points.get(i).getLatitude());
                lonLat.transform(EPSG4326, map.getProjection());
                pointArray[i] = new Point(lonLat.lon(), lonLat.lat());
            }

            LineString line = new LineString(pointArray);
            Style lineStyle = new Style();
            lineStyle.setFillColor("#000000");
            lineStyle.setFill(true);
            lineStyle.setFillOpacity(0.5);
            lineStyle.setStroke(true);
            lineStyle.setStrokeColor("#009933");
            lineStyle.setStrokeWidth(3.0);
            lineStyle.setStrokeOpacity(0.8);
            VectorFeature feature = new VectorFeature(line, lineStyle);

            tracksLayer.addFeature(feature);
        }
        map.addLayer(tracksLayer);

    }

    public void test() {
        final TrackServiceAsync trackService = GWT.create(TrackService.class);
        trackService.getTracks(null, new AsyncCallback<List<TrackDTO2>>() {

            @Override
            public void onSuccess(List<TrackDTO2> result) {
                for (TrackDTO2 track : result) {
                    displayTracks(track);
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                caught.printStackTrace();

            }
        });

    }

}
