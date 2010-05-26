package org.footware.server.gpx;

public interface TrackVisualizationFactoryStrategyView {
	
    /**
     * The method should only be called from the strategy of the factory
     * 
     * @param xValue x axis value 
     * @param yValue y axis value
     */
    public void addPoint(double xValue, double yValue);

}
