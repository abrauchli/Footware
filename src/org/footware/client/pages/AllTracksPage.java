package org.footware.client.pages;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractTablePage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.shared.dto.TrackSearchData;

public class AllTracksPage extends AbstractTablePage {

	public AllTracksPage(AbstractTreeNode treeNode) {
		super(treeNode);
	}

	@Override
	public String[][] execLoadTableData() {
		return null;
		// return new String[][] { {
		// "Please search first to narrow down the selection" } };
	}

	@Override
	public String[][] execLoadTableData(AbstractSearchData search) {
		TrackSearchData sd = (TrackSearchData) search;//getSearchFilter();
		String[][] result = new String[sd.value][2];
		for (int i = 0; i < sd.value; i++) {
			result[i] = new String[] { "bogus track", "dumbo user", "666",
					"500 miles", "8.11.1984", "0" };
		}
		return result;
	}

	@Override
	protected List<String> getConfiguredHeaders() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Name");
		headers.add("User");
		headers.add("Trackpoints");
		headers.add("Length");
		headers.add("Date");
		headers.add("Number of comments");
		return headers;
	}

	@Override
	public void reload() {
		loadTableData(getSearchFilter());
	}
	

	@Override
	public int getconfiguredCellPadding() {
		return 3;
	}

	@Override
	public boolean getconfiguredClickEnabled() {
		return true;
	}


	@Override
	public void rowClicked(int rowNum) {
		getTreeNode().openChildPage(rowNum);
	}

	@Override
	public String getConfiguredTitle() {
		return "Tracks";
	}
}
