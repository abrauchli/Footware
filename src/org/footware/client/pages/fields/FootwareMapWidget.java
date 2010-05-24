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

/**
 * 
 */
package org.footware.client.pages.fields;

import org.footware.shared.dto.TrackDTO2;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * @author rene WGS84 projection : EPSG:4326
 */

public class FootwareMapWidget extends Composite {

    private static String EPSG4326 = "EPSG:4326";

    private final Panel panel;
//    private Map map;
    private int width = 800;
    private int height = 400;

    public FootwareMapWidget() {
    	this(800,400);
    }

    public FootwareMapWidget(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        panel = new SimplePanel();
        initWidget(panel);
        initMapWidget();
    }

    private void initMapWidget() {
//        MapOptions defaultMapOptions = new MapOptions();
//        defaultMapOptions.setNumZoomLevels(19);
//        defaultMapOptions.setMaxExtent(new Bounds(-20037508.34, -20037508.34, 20037508.34, 20037508.34));
//        defaultMapOptions.setMaxResolution((float) 156543.0399);
//        defaultMapOptions.setProjection("EPSG:900913");
//        defaultMapOptions.setDisplayProjection(new Projection(EPSG4326));
//
//        MapWidget mapWidget = new MapWidget(width + "px", height + "px", defaultMapOptions);
//        map = mapWidget.getMap();
//
//        map.addControl(new LayerSwitcher());
//        OSM openStreetMap = OSM.Osmarender("Base Map");
//        openStreetMap.setIsBaseLayer(true);
//        map.addLayer(openStreetMap);
//
//        LonLat center = new LonLat(47.0, 8.0);
//        center.transform(EPSG4326, map.getProjection());
//        map.setCenter(center, 3);
//
//        panel.add(mapWidget);
    }

    public void displayTracks(TrackDTO2 track) {
//        VectorOptions vectorLayerOptions = new VectorOptions();
//        vectorLayerOptions.setProjection(map.getProjection());
//        final Vector tracksLayer = new Vector("Trackname", vectorLayerOptions);
//
//        for (TrackSegmentDTO segment : track.getSegments()) {
//            Point[] pointArray = new Point[segment.getPoints().size()];
//
//            List<TrackPointDTO> points = segment.getPoints();
//            for (int i = 0; i < points.size(); i++) {
//                LonLat lonLat = new LonLat(points.get(i).getLongitude(), points.get(i).getLatitude());
//                lonLat.transform(EPSG4326, map.getProjection());
//                pointArray[i] = new Point(lonLat.lon(), lonLat.lat());
//            }
//
//            LineString line = new LineString(pointArray);
//            Style lineStyle = new Style();
//            lineStyle.setFillColor("#000000");
//            lineStyle.setFill(true);
//            lineStyle.setFillOpacity(0.5);
//            lineStyle.setStroke(true);
//            lineStyle.setStrokeColor("#009933");
//            lineStyle.setStrokeWidth(3.0);
//            lineStyle.setStrokeOpacity(0.8);
//            VectorFeature feature = new VectorFeature(line, lineStyle);
//            tracksLayer.addFeature(feature);
//        }
//
//        map.addLayer(tracksLayer);
//
//
//        LonLat center = new LonLat(track.getMidLongitude(), track.getMidLatitude());
//        System.out.println(center.lat() + " " + center.lon());
//        center.transform(EPSG4326, map.getProjection());
//        System.out.println(center.lat() + " " + center.lon());
//        map.setCenter(center, 10);

    }

//    public void test() {
//        final TrackServiceAsync trackService = GWT.create(TrackService.class);
//        trackService.getTracks(null, new AsyncCallback<List<TrackDTO2>>() {
//
//            @Override
//            public void onSuccess(List<TrackDTO2> result) {
//                for (TrackDTO2 track : result) {
//                    displayTracks(track);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable caught) {
//                caught.printStackTrace();
//            }
//        });
//
//    }

    /**
     * @return the widht
     */
    public int getWidht() {
        return width;
    }

    /**
     * @param widht
     *            the widht to set
     */
    public void setWidht(int widht) {
        this.width = widht;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height
     *            the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
