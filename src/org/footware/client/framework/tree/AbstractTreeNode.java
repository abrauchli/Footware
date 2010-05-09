package org.footware.client.framework.tree;

import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;

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
		childNodes = execCreateChildren();
		if (childNodes != null && !childNodes.isEmpty()) {
			for (AbstractTreeNode node : childNodes) {
				addItem(node);
			}
		}
		setText(getConfiguredName());
		page = getConfiguredPage();
	}

	/**
	 * the name of the node
	 * 
	 * @return
	 */
	public abstract String getConfiguredName();

	/**
	 * create the child nodes. This is usually done using a service.
	 * 
	 * @return
	 */
	protected abstract List<AbstractTreeNode> execCreateChildren();

	/**
	 * create the children using a filter. This is by default just the same as
	 * without filter.
	 * 
	 * @param search
	 *            the filter
	 * @return a list of children
	 */
	protected List<AbstractTreeNode> execCreateChildren(
			AbstractSearchData search) {
		return execCreateChildren();
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
		childNodes = execCreateChildren(search);
		if (childNodes != null && !childNodes.isEmpty()) {
			for (AbstractTreeNode node : childNodes) {
				addItem(node);
			}
		}
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

	public void openChildPage(int rowNumber) {
		Tree t = getTree();
		if (childNodes != null) {
			AbstractTreeNode item = childNodes.get(rowNumber);
			t.setSelectedItem(item);
			t.ensureSelectedItemVisible();
		}
	}

}
