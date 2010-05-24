/*
 * Copyright 2010 Andreas Brauchli, René Buffat, Florian Widmer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
