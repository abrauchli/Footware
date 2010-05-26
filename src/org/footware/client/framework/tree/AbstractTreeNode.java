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

package org.footware.client.framework.tree;

import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * the AbstractTreeNode is an element in the tree
 * 
 * @author flwidmer
 * 
 */
public abstract class AbstractTreeNode extends TreeItem {
	private AbstractPage page;
	private List<AbstractTreeNode> childNodes;

	public AbstractTreeNode() {
		super();
		init();
	}

	public void init() {
		execInit();
		execCreateChildren();
		setText(getConfiguredName());
		page = getConfiguredPage();
	}

	private void reloadChildren() {
		if (childNodes != null && !childNodes.isEmpty()) {
			removeItems();
			for (AbstractTreeNode node : childNodes) {
				addItem(node);
			}
		}
	}

	/**
	 * the name of the node
	 * 
	 * @return
	 */
	public abstract String getConfiguredName();

	/**
	 * create the child nodes. This is usually done using a service. call
	 * setChildnodes asynchronously...
	 * 
	 * @return
	 */
	protected abstract void execCreateChildren();

	public void setChildNodes(List<AbstractTreeNode> data) {
		childNodes = data;
		reloadChildren();
	}

	/**
	 * create the children using a filter. This is by default just the same as
	 * without filter. call setChildnodes asynchronously...
	 * 
	 * @param search
	 *            the filter
	 * @return a list of children
	 */
	protected void execCreateChildren(AbstractSearchData search) {
		execCreateChildren();
	}

	/**
	 * hook in case you want to do something before initialization
	 */
	protected void execInit() {
	}

	/**
	 * return an instance of a page to display when this node is clicked
	 * 
	 * @return
	 */
	public abstract AbstractPage getConfiguredPage();

	/**
	 * this is usually: <code>return new constructor(this);</code>
	 * 
	 * @return an instance of a searchpage
	 */
	public abstract AbstractSearchForm getConfiguredSearchForm();

	protected void reload(AbstractSearchData search) {
		removeItems();
		execCreateChildren(search);
		page.reload();
	}

	/**
	 * what to do with a search typically you want to:
	 * <code>reload(search)</code>
	 * 
	 * @param search
	 */
	public void search(AbstractSearchData search) {
		reload(search);
	}

	public AbstractPage getPage() {
		return page;
	}

	/**
	 * call this function to open a childpage.
	 * 
	 * @param rowNumber
	 *            if it's a table page, the translation is obvious...
	 */
	public void openChildPage(int rowNumber) {
		Tree t = getTree();
		if (childNodes != null) {
			AbstractTreeNode item = childNodes.get(rowNumber);
			t.setSelectedItem(item);
			t.ensureSelectedItemVisible();
		}
	}

	public void lazyLoad() {
		getPage().execLazyload();
	}
	public void noConnection(){
		Window.alert("No connection to server");
	}
}
