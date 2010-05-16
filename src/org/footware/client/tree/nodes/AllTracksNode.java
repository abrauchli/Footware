package org.footware.client.tree.nodes;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.AllTracksPage;
import org.footware.client.search.TrackSearch;
import org.footware.shared.dto.TrackSearchData;

public class AllTracksNode extends AbstractTreeNode {

	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<AbstractTreeNode> execCreateChildren(
			AbstractSearchData search) {
		ArrayList<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		TrackSearchData sd = (TrackSearchData) search;
		for (int i = 0; i < sd.value; i++) {
			children.add(new TrackNode());
		}
		return children;
	}

	@Override
	public String getConfiguredName() {
		return "All public tracks";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new AllTracksPage(this);
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return new TrackSearch(this);
	}

}
