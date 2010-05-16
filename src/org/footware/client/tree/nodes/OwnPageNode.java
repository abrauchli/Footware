package org.footware.client.tree.nodes;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.OwnPage;

/**
 * 
 * @author flwidmer
 * 
 */
public class OwnPageNode extends AbstractTreeNode {

	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		List<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		children.add(new MyTracksNode());
		return children;
	}

	@Override
	public String getConfiguredName() {
		return "My Profile";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new OwnPage(this);
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return null;
	}

}
