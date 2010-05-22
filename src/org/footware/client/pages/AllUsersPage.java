package org.footware.client.pages;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractTablePage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.shared.dto.UserSearchData;

public class AllUsersPage extends AbstractTablePage {

	public AllUsersPage(AbstractTreeNode treeNode) {
		super(treeNode);
	}

	@Override
	public String[][] execLoadTableData() {
		return null;
	}

	@Override
	protected List<String> getConfiguredHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("Name");
		headers.add("Tracks");
		return headers;
	}

	@Override
	public String[][] execLoadTableData(AbstractSearchData search) {
		// TODO andy methode um alle user als tabelle anzuzeigen abhängig von
		// suche. analog AllTracksPage
		UserSearchData sd = (UserSearchData) search;
		String[][] result = new String[sd.value][];
		for (int i = 0; i < sd.value; i++) {
			result[i] = new String[] { "ttt", "23" };
		}
		return result;
	}

	@Override
	public String getConfiguredTitle() {
		return "All users";
	}

	@Override
	public void reload() {
		loadTableData(getSearchFilter());
	}

	@Override
	public boolean getconfiguredClickEnabled() {
		return true;
	}

	@Override
	public void rowClicked(int rowNum) {
		getTreeNode().openChildPage(rowNum);
	}
}
