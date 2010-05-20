package org.footware.shared.dto;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TrackSegmentDTO  implements IsSerializable {
	List<TrackPointDTO> points = new LinkedList<TrackPointDTO>();
	
	public TrackSegmentDTO() {
	}

	/**
	 * @return the points
	 */
	public List<TrackPointDTO> getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(List<TrackPointDTO> points) {
		this.points = points;
	}
	
	public void addPoint(TrackPointDTO point) {
		points.add(point);
	}
	
	public void addPoint(List<TrackPointDTO> points) {
		points.addAll(points);
	}

}
