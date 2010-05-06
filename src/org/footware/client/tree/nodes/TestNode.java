package org.footware.client.tree.nodes;

import java.util.List;

import org.footware.client.framework.AbstractPage;
import org.footware.client.framework.AbstractSearchData;
import org.footware.client.framework.AbstractSearchForm;
import org.footware.client.framework.AbstractTreeNode;
import org.footware.client.pages.TestTablePage;

public class TestNode extends AbstractTreeNode {

	public TestNode() {
		super();
	}

	@Override
	protected void execInit() {

	}

	@Override
	public String getConfiguredName() {
		return "testnode";
	}

	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractPage getConfiguredPage() {
		// TODO Auto-generated method stub
		return new TestTablePage();
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void search(AbstractSearchData search) {
		// TODO Auto-generated method stub
		
	}
	
	

}
