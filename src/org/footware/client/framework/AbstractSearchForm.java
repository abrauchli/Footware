package org.footware.client.framework;

public abstract class AbstractSearchForm extends AbstractPage {

	private AbstractTreeNode myTreeNode;

	public AbstractSearchForm(AbstractTreeNode node) {
		super();
		myTreeNode = node;
	}

	public abstract AbstractSearchData getSearch();

	public void doSearch() {
		AbstractSearchData s = getSearch();
		myTreeNode.search(s);
	}
}
