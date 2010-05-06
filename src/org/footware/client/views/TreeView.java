package org.footware.client.views;


import org.footware.client.framework.AbstractTree;
import org.footware.client.framework.AbstractTreeNode;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class TreeView extends Tree{
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
					myDataView
							.displayPage(((AbstractTreeNode) getSelectedItem())
									.getPage());
					if(((AbstractTreeNode) getSelectedItem())
							.getConfiguredSearchForm() != null){
					mySearchView.display(((AbstractTreeNode) getSelectedItem())
							.getConfiguredSearchForm());
		}else{
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
