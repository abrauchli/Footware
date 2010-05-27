package org.footware.client.tree.nodes;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.SpeedPlotPage;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackVisualizationDTO;

public class ElevationPlotNode extends AbstractTreeNode {
	private TrackDTO myTrack;

	public ElevationPlotNode(TrackDTO track) {
		myTrack = track;
		init();
	}

	@Override
	protected void execCreateChildren() {
	}

	@Override
	public String getConfiguredName() {
		return "Elevation plot";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new SpeedPlotPage(this, myTrack, TrackVisualizationDTO.TYPE_ELEVATION);
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return null;
	}

}
