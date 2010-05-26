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

import java.util.Set;

import org.footware.client.Session;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.services.OutlineService;
import org.footware.client.services.OutlineServiceAsync;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackSearchData;
import org.footware.shared.dto.UserDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MyTracksTablePage extends AllTracksPage {

	public MyTracksTablePage(AbstractTreeNode treeNode) {
		super(treeNode);
	}

	@Override
	public void execLoadTableData(AbstractSearchData search) {
		// TODO get user and find suitable stuff to do here
		// TODO andy methode um tracks eines users als tabelle zu laden
		// (abhängig von suche)
		UserDTO u = Session.getUser();
		TrackSearchData sd = (TrackSearchData) search;
		sd.user = u;
		OutlineServiceAsync svc = GWT.create(OutlineService.class);
		svc.getTracksTable(sd, new AsyncCallback<String[][]>() {

			@Override
			public void onSuccess(String[][] result) {
				setTableData(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				displayError("No connection to server");
			}
		});
	}

	@Override
	public void execLoadTableData() {
		// TODO do table load
		// TODO andy wie oben aber mit leerer suche
		execLoadTableData(new TrackSearchData());
	}
}
