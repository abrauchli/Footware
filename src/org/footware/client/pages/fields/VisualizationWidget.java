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

package org.footware.client.pages.fields;

import org.footware.client.services.TrackService;
import org.footware.client.services.TrackServiceAsync;
import org.footware.shared.dto.TrackVisualizationDTO;
import org.footware.shared.dto.TrackVisualizationPointDTO;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.PlotWithOverview;
import ca.nanometrics.gflot.client.PlotWithOverviewModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.options.GridOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;
import ca.nanometrics.gflot.client.options.SelectionOptions;
import ca.nanometrics.gflot.client.options.TimeSeriesAxisOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

public class VisualizationWidget extends Composite {

	private final Panel panel;
	private String width = "100%";
	private String height = "100%";

	public VisualizationWidget() {
		panel = new SimplePanel();
		initWidget(panel);
	}

	public VisualizationWidget(String width, String height) {
		this();
		this.width = width;
		this.height = height;
	}

	public VisualizationWidget(TrackVisualizationDTO dataDTO) {
		this();
		displayVisualization(dataDTO);
	}

	public VisualizationWidget(String width, String height,
			TrackVisualizationDTO dataDTO) {
		this(width, height);
		displayVisualization(dataDTO);
	}

	/**
	 * @return the widht
	 */
	public String getWidht() {
		return width;
	}

	/**
	 * @param widht
	 *            the widht to set
	 */
	public void setWidht(String widht) {
		this.width = widht;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	public void displayVisualization(TrackVisualizationDTO dataDTO) {
		// Empty panel
		panel.clear();

		// Create plot
		PlotWithOverviewModel model = new PlotWithOverviewModel(
				PlotModelStrategy.defaultStrategy());
		PlotOptions plotOptions = new PlotOptions();
		plotOptions.setDefaultLineSeriesOptions(new LineSeriesOptions()
				.setLineWidth(1).setShow(true));
		plotOptions.setDefaultPointsOptions(new PointsSeriesOptions()
				.setRadius(0).setShow(true));
		plotOptions.setDefaultShadowSize(0);
		plotOptions.setXAxisOptions(new TimeSeriesAxisOptions());
		plotOptions.setLegendOptions(new LegendOptions().setShow(false));
		plotOptions.setGridOptions(new GridOptions().setHoverable(true));

		PlotOptions overviewPlotOptions = new PlotOptions();
		overviewPlotOptions.setDefaultShadowSize(0).setLegendOptions(
				new LegendOptions().setShow(false));
		overviewPlotOptions.setDefaultLineSeriesOptions(new LineSeriesOptions()
				.setLineWidth(1).setFill(true));
		overviewPlotOptions.setSelectionOptions(new SelectionOptions().setMode(
				SelectionOptions.X_SELECTION_MODE).setDragging(true));
		overviewPlotOptions.setXAxisOptions(new TimeSeriesAxisOptions());

		PlotWithOverview plot = new PlotWithOverview(model, plotOptions,
				overviewPlotOptions);

		// TODO make this better!!
		plot.setHeight(height);
		plot.setWidth(width);

		SeriesHandler series = model.addSeries(dataDTO.getType());
		for (TrackVisualizationPointDTO datapoint : dataDTO.getData()) {
			series.add(new DataPoint(datapoint.getX(), datapoint.getY()));
		}

		panel.add(plot);

	}

	public void test() {
		final TrackServiceAsync trackService = GWT.create(TrackService.class);
		trackService.getTrackVisualization(-1,
				new AsyncCallback<TrackVisualizationDTO>() {

					@Override
					public void onSuccess(TrackVisualizationDTO result) {
						displayVisualization(result);

					}

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();

					}
				});
	}

}
