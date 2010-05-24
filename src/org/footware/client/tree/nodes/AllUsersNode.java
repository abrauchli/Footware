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
import org.footware.shared.dto.UserSearchData;

public class AllUsersNode extends AbstractTreeNode {

	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		//leaving this empty forces search
		return null;
	}

	@Override
	protected List<AbstractTreeNode> execCreateChildren(
			AbstractSearchData search) {
		//TODO andy methode um user gemäss suche als list<userDTO> zu erhalten
		UserSearchData sd = (UserSearchData) search;
		List<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		for (int i = 0; i < sd.value; i++) {
			children.add(new UserNode());
		}
		return children;
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
