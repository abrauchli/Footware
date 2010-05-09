package org.footware.client.framework.pages;


import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPage extends Grid {

	private AbstractSearchForm searchForm;
	private AbstractTreeNode treeNode;

	public AbstractPage(AbstractTreeNode treeNode) {
		super(2, 1);
		init();
		this.setTreeNode(treeNode);
	}

	protected void init() {
		if (getConfiguredScrollbars()) {
			ScrollPanel sp = new ScrollPanel();
			sp.add(getConfiguredContent());
			sp.setWidth(getConfiguredWidth());
			sp.setHeight(getConfiguredHeight());
			setWidget(1, 0, sp);
			setWidget(0, 0, new HTML("<h3>"+getConfiguredTitle()+"</h3>"));
		} else {
			setWidget(0, 0, getConfiguredContent());
		}

	}

	/**
	 * return a widget representing the content.
	 * 
	 * @return
	 */
	protected abstract Widget getConfiguredContent();

	/**
	 * hook to override if you want to do something when the page is reloaded.
	 * You may for example want to reload data using the searchfilter:
	 * <code>getSearchFilter()</code>
	 */
	public void reload() {
	}

	public void setSearchForm(AbstractSearchForm sf) {
		searchForm = sf;
	}

	public AbstractSearchData getSearchFilter() {
		if (searchForm != null) {
			return searchForm.getSearch();
		} else {
			return null;
		}
	}

	/**
	 * set the width of the table in px
	 * 
	 * @return
	 */
	public String getConfiguredWidth() {
		return "800px";
	}

	/**
	 * set the height of the table
	 * 
	 * @return
	 */
	public String getConfiguredHeight() {
		return "600px";
	}

	/**
	 * override to disable scrolling inside the page
	 * 
	 * @return
	 */
	public boolean getConfiguredScrollbars() {
		return true;
	}
	/**
	 * This will be put into <code><h3></code> and set as title of the page.
	 * @return
	 */
	public String getConfiguredTitle(){
		return "";
	}

	private void setTreeNode(AbstractTreeNode treeNode) {
		this.treeNode = treeNode;
	}

	public AbstractTreeNode getTreeNode() {
		return treeNode;
	}
}
