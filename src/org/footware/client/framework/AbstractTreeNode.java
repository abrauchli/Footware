package org.footware.client.framework;

import java.util.List;

import com.google.gwt.user.client.ui.TreeItem;

/**
 * the AbstractTreeNode is an element in the tree
 * 
 * @author flwidmer
 * 
 */
public abstract class AbstractTreeNode extends TreeItem {
	private AbstractPage page;

	public AbstractTreeNode() {
		super();
		init();
	}

	public void init() {
		execInit();
		List<AbstractTreeNode> children = execCreateChildren();
		if (children != null && !children.isEmpty()) {
			for (AbstractTreeNode node : children) {
				addItem(node);
			}
		}
		setText(getConfiguredName());
		page = getConfiguredPage();
	}

	public abstract String getConfiguredName();

	protected abstract List<AbstractTreeNode> execCreateChildren();

	protected List<AbstractTreeNode> execCreateChildren(AbstractSearchData search) {
		return execCreateChildren();
	}

	protected void execInit() {
	}

	public abstract AbstractPage getConfiguredPage();

	public abstract AbstractSearchForm getConfiguredSearchForm();

	protected void reload(AbstractSearchData search) {
		removeItems();
		List<AbstractTreeNode> children = execCreateChildren(search);
		if (children != null && !children.isEmpty()) {
			for (AbstractTreeNode node : children) {
				addItem(node);
			}
		}
		page.reload();
	}

	public abstract void search(AbstractSearchData search);

	public AbstractPage getPage() {
		return page;
	}
}
