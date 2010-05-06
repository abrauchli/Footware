package org.footware.client.framework.tree;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.search.AbstractSearchData;

/**
 * the tree represents the model part for the tree
 * 
 * @author flwidmer
 * 
 */
public abstract class AbstractTree {
	List<AbstractTreeNode> toplevel;

	public AbstractTree() {
		toplevel = new ArrayList<AbstractTreeNode>();
		init();
	}
	public void init(){
		toplevel.addAll(execCreateChildren(null));
		
	}
	public List<AbstractTreeNode> getToplevel(){
		return toplevel;
	}
	
	public abstract List<AbstractTreeNode> execCreateChildren(AbstractSearchData search);

	
	
}
