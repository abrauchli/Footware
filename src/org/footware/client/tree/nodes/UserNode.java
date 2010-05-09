package org.footware.client.tree.nodes;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.UserPage;
import org.footware.client.search.UserSearchData;
import org.footware.client.search.UserSearchForm;

public class UserNode extends AbstractTreeNode {

	public UserNode() {
		super();
	}

	@Override
	protected void execInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getConfiguredName() {
		return "Person";
	}

	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		ArrayList<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		children.add(new TestNode());
		children.add(new TestNode());
		return children;
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new UserPage(this);
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return new UserSearchForm(this);
	}


	@Override
	protected List<AbstractTreeNode> execCreateChildren(AbstractSearchData search) {
		UserSearchData ps = (UserSearchData) search;
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		for (int i = 0; i < ps.value; i++) {
			list.add(new TestNode());
		}
		return list;
	}
	@Override
	public void search(AbstractSearchData search) {
		super.search(search);
	}
}
