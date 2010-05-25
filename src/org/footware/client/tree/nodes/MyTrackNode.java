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

import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.TrackDetailPage;

public class MyTrackNode extends AbstractTreeNode {

	@Override
	protected void execCreateChildren() {
	}

	@Override
	public String getConfiguredName() {
		return "my track";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		TrackDetailPage dp = new TrackDetailPage(this);
		dp.editableMode();
		return dp;
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return null;
	}

}
