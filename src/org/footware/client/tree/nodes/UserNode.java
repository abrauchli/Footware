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
import org.footware.client.pages.UserPage;
import org.footware.client.services.OutlineService;
import org.footware.client.services.OutlineServiceAsync;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackSearchData;
import org.footware.shared.dto.UserDTO;
import org.footware.shared.dto.UserSearchData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UserNode extends AbstractTreeNode {

	private UserDTO myUser;

	public UserNode(UserDTO user) {
		super();
		myUser = user;
	}

	@Override
	protected void execInit() {

	}

	@Override
	public String getConfiguredName() {
		return "Person";
	}

	@Override
	protected void execCreateChildren() {
		ArrayList<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		// TODO andy methode um tracks eines users zu laden
		for (TrackDTO t : myUser.getTracks()) {
			TrackNode child = new TrackNode(t);
			children.add(child);
		}
		setChildNodes(children);
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new UserPage(this);
	}

	@Override
	protected void execCreateChildren(AbstractSearchData search) {
		UserSearchData sd = (UserSearchData) search;
		OutlineServiceAsync svc = GWT.create(OutlineService.class);
		svc.getUserList(sd, new AsyncCallback<List<UserDTO>>() {

			@Override
			public void onSuccess(List<UserDTO> result) {
				List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
				for (UserDTO u : result) {
					list.add(new UserNode(u));
				}
				setChildNodes(list);
			}

			@Override
			public void onFailure(Throwable caught) {
				noConnection();
			}
		});

	}

	@Override
	public void search(AbstractSearchData search) {
		super.search(search);
	}

	public void setMyUser(UserDTO myUser) {
		this.myUser = myUser;
	}

	public UserDTO getMyUser() {
		return myUser;
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean admin = false;

	public void startAdmin() {
		admin = true;
	}
}
