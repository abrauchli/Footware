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

import java.util.LinkedList;

import org.footware.server.db.Track;
import org.footware.server.gpx.model.GPXTrack;
import org.footware.shared.dto.TrackVisualizationDTO;
import org.footware.shared.dto.TrackVisualizationPointDTO;

public class TrackVisualizationFactoryImpl implements
		TrackVisualizationFactory, TrackVisualizationFactoryStrategyView {

	private TrackVisualizationStrategy strategy;
	private TrackVisualizationDTO data;
	private LinkedList<TrackVisualizationPointDTO> pointList;

	/**
	 * Constructor for visualization factory.
	 * 
	 * @param strategy
	 *            the strategy that should be used
	 */
	public TrackVisualizationFactoryImpl(TrackVisualizationStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * The method should only be called from the strategy of the factory
	 * 
	 * @param xValue
	 *            x axis value
	 * @param yValue
	 *            y axis value
	 */
	public void addPoint(double xValue, double yValue) {
		pointList.add(new TrackVisualizationPointDTO(xValue, yValue));
	}

	/**
	 * Creates a TrackVisualizationDTO of the the track according to the
	 * strategy of the factory
	 * 
	 * @param track
	 *            the track the visualization has to be calculated from
	 * @return visualizaton
	 */
	public TrackVisualizationDTO create(Track track) {
		// Init factory
		data = new TrackVisualizationDTO();
		data.setType(strategy.getType());
		pointList = new LinkedList<TrackVisualizationPointDTO>();

		// Init strategy
		strategy.setTrack(track);

		strategy.execute(this);
		data.setData(pointList);

		return data;
	}
}
