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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.footware.shared.dto.TrackVisualizationDTO;
import org.footware.shared.dto.TrackVisualizationPointDTO;

/**
 * Class for ER mapping of track visualizations
 */
public class TrackVisualization extends DbEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String TYPE_SPEED = "Speed plot";
    public static String TYPE_ELEVATION = "Elevation plot";
    
    @Override
    public String getTable() {
    	return "visualization";
    }
	
    //@Column(length=64)
    private String type;
    
    //@Column(name="x_unit",length=32)
    private String xUnit;
    
    //@Column(name="y_unit",length=32)
    private String yUnit;
    
    private Track track;
    
    //@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
    //@JoinColumn(name="visualization_id")
    private List<TrackVisualizationPoint> data = new LinkedList<TrackVisualizationPoint>();

    /**
     * Constructor for special initializations
     * It is advised not to use this diretly
     */
    public TrackVisualization() {
    }
    
    public TrackVisualization(List<TrackVisualizationPoint> data, String type, String xUnit, String yUnit) {
        this.data = data;
        this.type = type;
        this.xUnit = xUnit;
        this.yUnit = yUnit;
    }

	/**
	 * Create a new object from the db id
	 * @param id
	 */
	public TrackVisualization(Long id) {
		this.id = id;
	}

    /**
     * @return the xUnit
     */
    public String getxUnit() {
        return getStrValue("x_unit", null);
    }

    /**
     * @param xUnit the xUnit to set
     */
    public void setxUnit(String xUnit) {
        setStrValue("x_unit", xUnit);
    }

    /**
     * @return the yUnit
     */
    public String getyUnit() {
        return getStrValue("y_unit", null);
    }

    /**
     * @param yUnit the yUnit to set
     */
    public void setyUnit(String yUnit) {
        setStrValue("y_unit", null);
    }

    /**
     * @return the type
     */
    public String getType() {
        return getStrValue("type", null);
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        setStrValue("type", null);
    }

    /**
     * @return the data
     */
    public List<TrackVisualizationPoint> getData() {
        //return data;
		List<Long> ids = getForeignKeys("visualization_point", "visualization_id");
		List<TrackVisualizationPoint> res = new LinkedList<TrackVisualizationPoint>();
		for (Long l : ids)
			res.add(new TrackVisualizationPoint(l));
		return res;
    }

    /**
     * Sets the data for this visualization
     * Do this only after calling store() to persist the object
     * @param data the data to set
     */
    public void setData(List<TrackVisualizationPoint> data) {
        //this.data = data;
    	assert (this.exists());
    	for (TrackVisualizationPoint p : data)
    		p.setTrackVisualization(this);
    }

    /**
     * Gets the TrackVisualizationDTO representing this object's current state
     * @return TrackVisualizationDTO representing this object's current state
     */
    public TrackVisualizationDTO getTrackVisualizationDTO() {
    	TrackVisualizationDTO t = new TrackVisualizationDTO();
    	t.setType(getType());
    	t.setxUnit(getxUnit());
    	t.setyUnit(getyUnit());

    	List<TrackVisualizationPointDTO> ps = t.getData();
    	for (TrackVisualizationPoint p : getData())
    		ps.add(p.getTrackVisualizationPointDTO());

    	return t;
    }

    /**
     * Gets the track this visualization is for
     * @return track this visualization is for
     */
	public Track getTrack() {
		//return track;
		return new Track(getLongValue("track_id", defaultId));
	}

	/**
	 * Sets the track this visualization is for
	 * @param track the track this visualization is for
	 */
	public void setTrack(Track track) {
		//this.track = track;
		setDblValue("track_id", track.getId());
	}
}
