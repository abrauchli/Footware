package org.footware.client.tree.nodes;

import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.TrackDetailPage;
import org.footware.shared.dto.TrackDTO;


public class TrackNode extends AbstractTreeNode{

	
	public TrackNode() {
		
	}
	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		return null;
	}

	private TrackDTO myTrack;
	
	
	public TrackDTO getMyTrack() {
		return myTrack;
	}
	public void setMyTrack(TrackDTO myTrack) {
		this.myTrack = myTrack;
	}
	@Override
	public String getConfiguredName() {
		//TODO use trackname from DTO or something like that.
		return "GETTRACKNAME";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		TrackDetailPage p = new TrackDetailPage(this);
		p.setMyTrack(myTrack);
		return p;
		
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return null;
	}

}
