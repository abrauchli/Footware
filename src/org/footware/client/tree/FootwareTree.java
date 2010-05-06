package org.footware.client.tree;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.tree.AbstractTree;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.tree.nodes.PersonNode;


public class FootwareTree extends AbstractTree {

	public FootwareTree() {
		super();
	}
	@Override
	public List<AbstractTreeNode> execCreateChildren(AbstractSearchData search) {
		List<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		children.add(new PersonNode());
		return children;
	}

	@Override
	public void search(Object search) {
		//TODO		
	}

}
