package org.footware.client.framework.views;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTree;
import org.footware.client.framework.tree.AbstractTreeNode;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class TreeView extends Tree {
	private AbstractTree myTree;
	private DataView myDataView;
	private SearchView mySearchView;

	public TreeView(AbstractTree tree, DataView data, SearchView search) {
		myTree = tree;
		myDataView = data;
		mySearchView = search;
		init();
	}

	public void init() {
		loadTreeItems();
		addSelectionHandler(new SelectionHandler<TreeItem>() {

			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				if (getSelectedItem() instanceof AbstractTreeNode) {
					AbstractSearchForm sf = ((AbstractTreeNode) getSelectedItem())
							.getConfiguredSearchForm();
					AbstractPage p = ((AbstractTreeNode) getSelectedItem())
					.getPage();
					myDataView
							.displayPage(p);
					if (sf != null) {
						p.setSearchForm(sf);
						mySearchView.display(sf);
					} else {
						mySearchView.clear();
					}
				}
			}
		});
	}

	private void loadTreeItems() {
		if (myTree != null && myTree.getToplevel() != null
				&& !myTree.getToplevel().isEmpty())
			for (AbstractTreeNode node : myTree.getToplevel()) {
				addTreeNode(node);
			}
	}

	private void addTreeNode(AbstractTreeNode node) {
		addItem(node);
	}

}
