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
		UserDTO u = Session.getUser();
		TrackSearchData sd = (TrackSearchData) search;
		sd.user = u;
		//FIXME currently, we ignore the search
		createTableData(u.getTracks());
		// OutlineServiceAsync svc = GWT.create(OutlineService.class);
		// svc.getTracksTable(sd, new AsyncCallback<String[][]>() {
		//
		// @Override
		// public void onSuccess(String[][] result) {
		// setTableData(result);
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// displayError("No connection to server");
		// }
		// });
	}

	private String[][] createTableData(Set<TrackDTO> tracks) {
		String[][] result = new String[tracks.size()][6];
		TrackDTO[] t = tracks.toArray(new TrackDTO[tracks.size()]);
		for (int i = 0; i < tracks.size(); i++) {
			result[i][0] = t[i].getUser().getFullName();
			result[i][1] = Integer.toString(t[i].getTrackpoints());
			result[i][2] = Double.toString(t[i].getLength());
			result[i][3] = t[i].getStartTime().toString();
			result[i][4] = Integer.toString(t[4].getComments().size());
			//TODO add tags
			result[i][5] = "";
			// result[i][0] = t[5].get
		}
		return result;
	}

	@Override
	public void execLoadTableData() {
		execLoadTableData(new TrackSearchData());
	}
}
