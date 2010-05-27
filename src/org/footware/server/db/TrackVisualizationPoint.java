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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.footware.shared.dto.TrackVisualizationPointDTO;
import org.hibernate.Hibernate;

/**
 * Class for ER mapping of single points of a track visualization
 */
@Entity
@Table(name="visualization_point")
public class TrackVisualizationPoint extends DbEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false,nullable=false)
	private long id;
	
	@Column(name="x_value")
	private double xValue;
	
	@Column(name="y_value")
    private double yValue;
	
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="visualization_id", nullable=false)
    private TrackVisualization visualization;

    /**
     * Constructor for hibernate initialization
     */
    public TrackVisualizationPoint() {
    	Hibernate.initialize(visualization);
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
    
	/**
	 * Gets the id of the corresponding DB row
	 * @return the ID of the row in the DB
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the x value
	 * @return x value
	 */
    public double getX() {
        return xValue;
    }

	/**
	 * Sets the x value
	 * @param x value to set
	 */
    public void setX(double xValue) {
        this.xValue = xValue;
    }

	/**
	 * Gets the y value
	 * @return y value
	 */
    public double getY() {
        return yValue;
    }

	/**
	 * Sets the y value
	 * @param y value to set
	 */
    public void setY(double yValue) {
        this.yValue = yValue;
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
