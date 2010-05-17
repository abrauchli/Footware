package org.footware.client.tree.nodes;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.MyTracksTablePage;

public class MyTracksNode extends AbstractTreeNode {

	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		// TODO load children of user here
		List<AbstractTreeNode> chidlren = new ArrayList<AbstractTreeNode>();
		chidlren.add(new MyTrackNode());
		return chidlren;
	}

	@Override
	public String getConfiguredName() {
		return "My Tracks";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new MyTracksTablePage(this);
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return null;
	}

}