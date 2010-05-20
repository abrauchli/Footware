package org.footware.server.gpx;

import java.util.LinkedList;

import org.footware.server.gpx.model.GPXTrack;
import org.footware.shared.dto.TrackVisualizationDTO;
import org.footware.shared.dto.TrackVisualizationPointDTO;

public class TrackVisualizationFactory {
    
    private TrackVisualizationStrategy strategy;
    private TrackVisualizationDTO data;
    private LinkedList<TrackVisualizationPointDTO> pointList;

    public TrackVisualizationFactory(TrackVisualizationStrategy strategy) {
        this.strategy = strategy;
    }
    public void addPoint(double xValue, double yValue) {
        pointList.add(new TrackVisualizationPointDTO(xValue, yValue));
    }
    
    public  TrackVisualizationDTO create(GPXTrack track) {
        //Init factory
        data = new TrackVisualizationDTO();
        data.setType(strategy.getType());
        pointList = new LinkedList<TrackVisualizationPointDTO>();
        
        //Init strategy
        strategy.setTrack(track);
        
        strategy.execute(this);
        data.setData(pointList);
        
        return data;
    }
}
