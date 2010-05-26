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
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackSearchData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AllTracksPage extends AbstractTablePage {

	public AllTracksPage(AbstractTreeNode treeNode) {
		super(treeNode);
	}

	@Override
	public void execLoadTableData() {
		// return new String[][] { {
		// "Please search first to narrow down the selection" } };
	}
	@Override
	public void execLoadTableData(AbstractSearchData search) {
		TrackSearchData sd = (TrackSearchData) search;// getSearchFilter();
		OutlineServiceAsync svc = GWT.create(OutlineService.class);
		svc.getTracksTable(sd, new AsyncCallback<String[][]>() {

			@Override
			public void onSuccess(String[][] result) {
				setTableData(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				displayError("No vonnection to server");

			}
		});
	}

	@Override
	protected List<String> getConfiguredHeaders() {
		ArrayList<String> headers = new ArrayList<String>();
		//headers.add("Name");
		headers.add("User");
		headers.add("Trackpoints");
		headers.add("Length");
		headers.add("Date");
		headers.add("Number of comments");
		headers.add("Tags");
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
