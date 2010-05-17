package org.footware.client.tree.nodes;

import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.TrackDetailPage;

public class MyTrackNode extends AbstractTreeNode {

	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		return null;
	}

	@Override
	public String getConfiguredName() {
		return "my track";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		TrackDetailPage dp = new TrackDetailPage(this);
		dp.editableMode();
		return dp;
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return null;
	}

}
