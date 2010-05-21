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
//					((AbstractTreeNode) treeWidget.getSelectedItem())
//							.loadChildren();
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
		treeWidget.addOpenHandler(new OpenHandler<TreeItem>() {

			@Override
			public void onOpen(OpenEvent<TreeItem> event) {
//				if (event.getTarget() instanceof AbstractTreeNode)
//					((AbstractTreeNode) event.getTarget()).loadChildren();
			}
		});
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
