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

package org.footware.server.db;

import java.io.Serializable;

import org.footware.shared.dto.TrackVisualizationPointDTO;

/**
 * Class for ER mapping of single points of a track visualization
 */
public class TrackVisualizationPoint extends DbEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public String getTable() {
		return "visualization_point";
	}

	private double xValue;
    private double yValue;
    //@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    //@JoinColumn(name="visualization_id", nullable=false)
    private TrackVisualization visualization;

    /**
     * Constructor for special initialization
     * it is advised not to use this directly
     */
    public TrackVisualizationPoint() {
    }

    /**
     * Constructor for initialization
     */
    public TrackVisualizationPoint(Long id) {
    	this.id = id;
    }

    /**
     * Constructor for initialization with x and y values
     * @param xValue x value to set
     * @param yValue y value to set
     */
    public TrackVisualizationPoint(double xValue, double yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }
    
    public void update() {
    	//TODO
    }

	/**
	 * Gets the x value
	 * @return x value
	 */
    public double getX() {
    	return getDblValue("x_value", 0.0);
    }

	/**
	 * Sets the x value
	 * @param x value to set
	 */
    public void setX(double xValue) {
    	setDblValue("x_value", 0.0);
    }

	/**
	 * Gets the y value
	 * @return y value
	 */
    public double getY() {
    	return getDblValue("y_value", 0.0);
    }

	/**
	 * Sets the y value
	 * @param y value to set
	 */
    public void setY(double yValue) {
    	setDblValue("x_value", 0.0);
    }
    
    /**
     * Gets the track visualization belonging to this point
     * @return the track visualization belonging to this point
     */
    public TrackVisualization getTrackVisualization() {
    	return new TrackVisualization(getLongValue("visualization_id", defaultId));
    }    
    
    /**
     * Sets the track visualization belonging to this point
     * @param tv the track visualization belonging to this point
     */
    public void setTrackVisualization(TrackVisualization tv) {
    	setLongValue("visualization_id", tv.getId());
    }

    /**
     * Gets the DTO for this object's current state
     * @return DTO for this object's current state
     */
	public TrackVisualizationPointDTO getTrackVisualizationPointDTO() {
		TrackVisualizationPointDTO p = new TrackVisualizationPointDTO();
		p.setX(xValue);
		p.setY(yValue);
		return p;
	}

}
