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
import org.footware.client.services.OutlineService;
import org.footware.client.services.OutlineServiceAsync;
import org.footware.shared.dto.UserSearchData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AllUsersPage extends AbstractTablePage {

	public AllUsersPage(AbstractTreeNode treeNode) {
		super(treeNode);
	}

	@Override
	public void execLoadTableData() {
		// dont do anything here...
	}

	@Override
	protected List<String> getConfiguredHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("Name");
		headers.add("Tracks");
		return headers;
	}

	@Override
	public void execLoadTableData(AbstractSearchData search) {
		// TODO service call
		OutlineServiceAsync svc = GWT.create(OutlineService.class);
		svc.getUsersTable((UserSearchData) search,
				new AsyncCallback<String[][]>() {

					@Override
					public void onSuccess(String[][] result) {
						setTableData(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						displayError("Unable to contact server");
					}
				});

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

	private boolean admin = false;

	public void startAdmin() {
		admin = true;
	}
}
