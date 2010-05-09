package org.footware.client.tree.nodes;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.AllUsersPage;
import org.footware.client.search.UserSearchData;
import org.footware.client.search.UserSearchForm;

public class AllUsersNode extends AbstractTreeNode {

	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<AbstractTreeNode> execCreateChildren(
			AbstractSearchData search) {
		UserSearchData sd = (UserSearchData) search;
		List<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		for (int i = 0; i < sd.value; i++) {
			children.add(new UserNode());
		}
		return children;
	}

	@Override
	public String getConfiguredName() {
		return "All user profiles";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new AllUsersPage(this);
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return new UserSearchForm(this);
	}
}
