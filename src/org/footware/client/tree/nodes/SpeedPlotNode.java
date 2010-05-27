package org.footware.client.tree.nodes;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.SpeedPlotPage;
import org.footware.shared.dto.TrackDTO;

public class SpeedPlotNode extends AbstractTreeNode {
	private TrackDTO myTrack;

	public SpeedPlotNode(TrackDTO track) {
		myTrack = track;
		init();
	}

	@Override
	protected void execCreateChildren() {

	}

	@Override
	public String getConfiguredName() {
		return "Speed plot";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new SpeedPlotPage(this, myTrack, 1);
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return null;
	}

}
