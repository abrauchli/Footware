/*
 * Copyright 2010 Andreas Brauchli, René Buffat, Florian Widmer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
	/**
	 * override this finction in order to lazily load stuff on a page
	 */
	public void execLazyload() {
	}

}
