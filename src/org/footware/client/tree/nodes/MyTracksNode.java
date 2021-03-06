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
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.MyTracksTablePage;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.UserDTO;

public class MyTracksNode extends AbstractTreeNode {
	private UserDTO myUser;

	public MyTracksNode(UserDTO user) {
		super();
		myUser = user;
		init();
	}
	@Override
	protected void execInit() {
	}
	@Override
	protected void execCreateChildren() {
		//FIXME
		List<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		for (TrackDTO t : myUser.getTracks()) {
			children.add(new MyTrackNode(t));
		}

		setChildNodes(children);
	}

	@Override
	public String getConfiguredName() {
		return "My Tracks";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new MyTracksTablePage(this);
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return null;
	}

}
