package org.footware.client.pages;

import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.tree.AbstractTreeNode;

public class MyTracksTablePage extends AllTracksPage{

	public MyTracksTablePage(AbstractTreeNode treeNode) {
		super(treeNode);
	}
	@Override
	public String[][] execLoadTableData(AbstractSearchData search) {
		//TODO get user and find suitable stuff to do here
		return new String[][] {{ "bogus track", "dumbo user", "666",
				"500 miles", "8.11.1984", "0" }};
	}
	@Override
	public String[][] execLoadTableData() {
		// TODO Auto-generated method stub
		return new String[][] {{ "bogus track", "dumbo user", "666",
			"500 miles", "8.11.1984", "0" }};
	}
}
