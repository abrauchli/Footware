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

package org.footware.client.tree.nodes;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.AllTracksPage;
import org.footware.client.search.TrackSearch;
import org.footware.client.services.OutlineService;
import org.footware.client.services.OutlineServiceAsync;
import org.footware.client.tree.AdminViewTree;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackSearchData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AllTracksNode extends AbstractTreeNode {

	@Override
	protected void execCreateChildren() {
	}

	@Override
	protected void execCreateChildren(AbstractSearchData search) {
		OutlineServiceAsync svc = GWT.create(OutlineService.class);
		TrackSearchData sd = (TrackSearchData) search;
		svc.getTrackList(sd, new AsyncCallback<List<TrackDTO>>() {

			@Override
			public void onSuccess(List<TrackDTO> result) {
				ArrayList<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
				for (TrackDTO t : result) {
					TrackNode tn = new TrackNode(t);
					if (admin) {
						tn.startAdmin();
					}
					children.add(tn);
				}
				setChildNodes(children);
			}

			@Override
			public void onFailure(Throwable caught) {
				noConnection();
			}
		});

	}

	@Override
	public String getConfiguredName() {
		return "All public tracks";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new AllTracksPage(this);
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return new TrackSearch(this);
	}

	private boolean admin = false;

	public void startAdmin() {
		admin = true;
	}
}
