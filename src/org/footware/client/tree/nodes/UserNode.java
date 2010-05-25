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
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.UserDTO;
import org.footware.shared.dto.UserSearchData;

public class UserNode extends AbstractTreeNode {

	private UserDTO myUser;

	public UserNode() {
		super();
	}

	@Override
	protected void execInit() {

	}

	@Override
	public String getConfiguredName() {
		return "Person";
	}

	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		ArrayList<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		// TODO andy methode um tracks eines users zu laden
		TrackNode child = new TrackNode();
		child.setMyTrack(new TrackDTO());
		children.add(child);
		return children;
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new UserPage(this);
	}


	@Override
	protected List<AbstractTreeNode> execCreateChildren(
			AbstractSearchData search) {
		UserSearchData ps = (UserSearchData) search;
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		for (int i = 0; i < ps.value; i++) {
			list.add(new TestNode());
		}
		return list;
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
}
