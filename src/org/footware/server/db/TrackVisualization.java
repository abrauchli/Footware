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
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.footware.shared.dto.TrackVisualizationDTO;
import org.footware.shared.dto.TrackVisualizationPointDTO;
import org.hibernate.Hibernate;

/**
 * Class for ER mapping of track visualizations
 */
@Entity
@Table(name="visualization")
public class TrackVisualization extends DbEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String TYPE_SPEED = "Speed plot";
    public static String TYPE_ELEVATION = "Elevation plot";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false,nullable=false)
	private long id;
	
    @Column(length=64)
    private String type;
    
    @Column(name="x_unit",length=32)
    private String xUnit;
    
    @Column(name="y_unit",length=32)
    private String yUnit;
    
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name="visualization_id")
    private List<TrackVisualizationPoint> data = new LinkedList<TrackVisualizationPoint>();

    /**
     * Constructor for hibernate initialization
     */
    public TrackVisualization() {
		Hibernate.initialize(data);
		for (TrackVisualizationPoint p : data)
			Hibernate.initialize(p);
    }
    
    public TrackVisualization(List<TrackVisualizationPoint> data, String type, String xUnit, String yUnit) {
        this.data = data;
        this.type = type;
        this.xUnit = xUnit;
        this.yUnit = yUnit;
    }

	/**
	 * Gets the id of the corresponding DB row
	 * @return the ID of the row in the DB
	 */
	public long getId() {
		return id;
	}

    /**
     * @return the xUnit
     */
    public String getxUnit() {
        return xUnit;
    }

    /**
     * @param xUnit the xUnit to set
     */
    public void setxUnit(String xUnit) {
        this.xUnit = xUnit;
    }

    /**
     * @return the yUnit
     */
    public String getyUnit() {
        return yUnit;
    }

    /**
     * @param yUnit the yUnit to set
     */
    public void setyUnit(String yUnit) {
        this.yUnit = yUnit;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the data
     */
    public List<TrackVisualizationPoint> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<TrackVisualizationPoint> data) {
        this.data = data;
    }

    /**
     * Gets the TrackVisualizationDTO representing this object's current state
     * @return TrackVisualizationDTO representing this object's current state
     */
    public TrackVisualizationDTO getTrackVisualizationDTO() {
    	TrackVisualizationDTO t = new TrackVisualizationDTO();
    	t.setType(type);
    	t.setxUnit(xUnit);
    	t.setyUnit(yUnit);

    	List<TrackVisualizationPointDTO> ps = t.getData();
    	for (TrackVisualizationPoint p : getData())
    		ps.add(p.getTrackVisualizationPointDTO());

    	return t;
    }
}
