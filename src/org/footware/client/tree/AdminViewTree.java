package org.footware.client.tree;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.tree.AbstractTree;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.tree.nodes.AllTracksNode;
import org.footware.client.tree.nodes.AllUsersNode;

public class AdminViewTree extends AbstractTree {

	@Override
	public List<AbstractTreeNode> execCreateChildren(AbstractSearchData search) {
		// TODO implement
		List<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		children.add(new AllUsersNode());
		children.add(new AllTracksNode());

		return children;
	}

}
