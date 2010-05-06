package org.footware.client.tree.nodes;

import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.AllTracksPage;
import org.footware.client.search.TrackSearch;

public class AllTracksNode extends AbstractTreeNode {

	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConfiguredName() {
		return "All public tracks";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new AllTracksPage();
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return new TrackSearch(this);
	}


}
