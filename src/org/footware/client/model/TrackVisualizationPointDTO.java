package org.footware.client.model;

public class TrackVisualizationPointDTO {
    
    private double xValue;
    private double yValue;
    
    public TrackVisualizationPointDTO() {
    }
    
    public TrackVisualizationPointDTO(double xValue, double yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public double getX() {
        return xValue;
    }

    public void setX(double xValue) {
        this.xValue = xValue;
    }

    public double getY() {
        return yValue;
    }

    public void setY(double yValue) {
        this.yValue = yValue;
    }
    
    

}
