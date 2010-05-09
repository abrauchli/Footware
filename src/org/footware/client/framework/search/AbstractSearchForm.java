package org.footware.client.framework.search;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.tree.AbstractTreeNode;

public abstract class AbstractSearchForm extends AbstractPage {


	public AbstractSearchForm(AbstractTreeNode node) {
		super(node);
	}

	public abstract AbstractSearchData getSearch();

	public void doSearch() {
		AbstractSearchData s = getSearch();
		getTreeNode().search(s);
	}
}
