package org.footware.client.pages.fields;

import org.footware.client.TrackService;
import org.footware.client.TrackServiceAsync;
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
    private int widht = 800;
    private int height = 400;

    public VisualizationWidget() {
        panel = new SimplePanel();
        initWidget(panel); 
    }
    
    public VisualizationWidget(int widht, int height) {
        this();
        this.widht = widht;
        this.height = height;
    }
    
    public VisualizationWidget(TrackVisualizationDTO dataDTO) {
        this();
        displayVisualization(dataDTO);
    }

    public VisualizationWidget(int widht, int height, TrackVisualizationDTO dataDTO) {
        this(widht,height);
        displayVisualization(dataDTO);
    }
    

    /**
     * @return the widht
     */
    public int getWidht() {
        return widht;
    }

    /**
     * @param widht the widht to set
     */
    public void setWidht(int widht) {
        this.widht = widht;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    public void displayVisualization(TrackVisualizationDTO dataDTO) {
        // Empty panel
        panel.clear();

        // Create plot
        PlotWithOverviewModel model = new PlotWithOverviewModel(PlotModelStrategy.defaultStrategy());
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setDefaultLineSeriesOptions(new LineSeriesOptions().setLineWidth(1).setShow(true));
        plotOptions.setDefaultPointsOptions(new PointsSeriesOptions().setRadius(0).setShow(true));
        plotOptions.setDefaultShadowSize(0);
        plotOptions.setXAxisOptions(new TimeSeriesAxisOptions());
        plotOptions.setLegendOptions(new LegendOptions().setShow(false));
        plotOptions.setGridOptions(new GridOptions().setHoverable(true));

        PlotOptions overviewPlotOptions = new PlotOptions();
        overviewPlotOptions.setDefaultShadowSize(0).setLegendOptions(new LegendOptions().setShow(false));
        overviewPlotOptions.setDefaultLineSeriesOptions(new LineSeriesOptions().setLineWidth(1).setFill(true));
        overviewPlotOptions.setSelectionOptions(new SelectionOptions().setMode(SelectionOptions.X_SELECTION_MODE).setDragging(
                true));
        overviewPlotOptions.setXAxisOptions(new TimeSeriesAxisOptions());

        PlotWithOverview plot = new PlotWithOverview(model, plotOptions, overviewPlotOptions);
        // TODO make this better!!
        plot.setHeight(height + "px");
        plot.setWidth(widht + "px");

        SeriesHandler series = model.addSeries(dataDTO.getType());
        for (TrackVisualizationPointDTO datapoint : dataDTO.getData()) {
            series.add(new DataPoint(datapoint.getX(), datapoint.getY()));
        }

        panel.add(plot);

    }

    public void test() {
        final TrackServiceAsync trackService = GWT.create(TrackService.class);
        trackService.getTrackVisualization(null, new AsyncCallback<TrackVisualizationDTO>() {

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

    protected void onLoad() {
        super.onLoad();
        test();
    }

}
