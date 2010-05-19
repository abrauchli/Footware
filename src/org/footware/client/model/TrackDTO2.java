package org.footware.client.model;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TrackDTO2 implements IsSerializable {
	List<TrackSegmentDTO> segments = new LinkedList<TrackSegmentDTO>();

	public TrackDTO2() {
	}

	/**
	 * @return the segments
	 */
	public List<TrackSegmentDTO> getSegments() {
		return segments;
	}
	
	/**
	 * @param segment the segment to add
	 */
	public void addSegment(TrackSegmentDTO segment) {
		this.segments.add(segment);
	}

	/**
	 * @param segments the segments to add
	 */
	public void addSegments(List<TrackSegmentDTO> segments) {
		this.segments.addAll(segments);
	}

}
