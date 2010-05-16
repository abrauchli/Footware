package org.footware.client.tree;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.tree.AbstractTree;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.tree.nodes.OwnPageNode;

public class PrivateViewTree extends AbstractTree {

	@Override
	public List<AbstractTreeNode> execCreateChildren(AbstractSearchData search) {
		List<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		children.add(new OwnPageNode());
		return children;
	}

}
