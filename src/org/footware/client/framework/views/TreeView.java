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

package org.footware.client.framework.views;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTree;
import org.footware.client.framework.tree.AbstractTreeNode;

import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class TreeView extends ScrollPanel {
	private AbstractTree myTree;
	private Tree treeWidget;
	private DataView myDataView;
	private SearchView mySearchView;

	public TreeView(AbstractTree tree, DataView data, SearchView search) {
		myTree = tree;
		myDataView = data;
		mySearchView = search;
		init();
	}

	public void init() {
		treeWidget = new Tree();
		loadTreeItems();
		treeWidget.addSelectionHandler(new SelectionHandler<TreeItem>() {

			public void onSelection(SelectionEvent<TreeItem> event) {
				if (treeWidget.getSelectedItem() instanceof AbstractTreeNode) {
					AbstractSearchForm sf = ((AbstractTreeNode) treeWidget
							.getSelectedItem()).getConfiguredSearchForm();
					// ((AbstractTreeNode) treeWidget.getSelectedItem())
					// .loadChildren();
					((AbstractTreeNode) treeWidget.getSelectedItem())
							.lazyLoad();
					AbstractPage p = ((AbstractTreeNode) treeWidget
							.getSelectedItem()).getPage();
					myDataView.displayPage(p);
					if (sf != null) {
						p.setSearchForm(sf);
						mySearchView.display(sf);
					} else {
						mySearchView.clear();
					}
				}
			}
		});
//		treeWidget.addOpenHandler(new OpenHandler<TreeItem>() {
//
//			@Override
//			public void onOpen(OpenEvent<TreeItem> event) {
//				if (event.getTarget() instanceof AbstractTreeNode) {
//					AbstractTreeNode t = (AbstractTreeNode) event.getTarget();
//				//	t.loadChildren();
//				}
//			}
//		});
		setHeight("600px");
		add(treeWidget);
	}

	private void loadTreeItems() {
		if (myTree != null && myTree.getToplevel() != null
				&& !myTree.getToplevel().isEmpty())
			for (AbstractTreeNode node : myTree.getToplevel()) {
				addTreeNode(node);
			}
	}

	private void addTreeNode(AbstractTreeNode node) {
		treeWidget.addItem(node);
	}

}
