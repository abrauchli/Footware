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
import org.footware.client.pages.AllUsersPage;
import org.footware.client.search.UserSearchForm;
import org.footware.client.services.OutlineService;
import org.footware.client.services.OutlineServiceAsync;
import org.footware.shared.dto.UserDTO;
import org.footware.shared.dto.UserSearchData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AllUsersNode extends AbstractTreeNode {

	@Override
	protected void execCreateChildren() {
		//leaving this empty forces search
	}

	@Override
	protected void execCreateChildren(
			AbstractSearchData search) {
		//TODO andy methode um user gemäss suche als list<userDTO> zu erhalten
		UserSearchData sd = (UserSearchData) search;
		OutlineServiceAsync svc = GWT.create(OutlineService.class);
		svc.getUserList(sd, new AsyncCallback<List<UserDTO>>() {
			
			@Override
			public void onSuccess(List<UserDTO> result) {
				List<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
				for(UserDTO u : result){
					children.add(new UserNode(u));
				}
				setChildNodes(children);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Mo connection to server");
			}
		});
	}

	@Override
	public String getConfiguredName() {
		return "All user profiles";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new AllUsersPage(this);
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return new UserSearchForm(this);
	}
	
}
