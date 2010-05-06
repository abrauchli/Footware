package org.footware.client.framework;

import java.util.ArrayList;
import java.util.List;

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

	public abstract void search(Object search);
	
	
}
