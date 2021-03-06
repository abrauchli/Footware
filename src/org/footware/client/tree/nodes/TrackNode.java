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
import org.footware.client.pages.TrackDetailPage;
import org.footware.shared.dto.TrackDTO;

public class TrackNode extends AbstractTreeNode {

	private TrackDTO myTrack;

	public TrackNode(TrackDTO track) {
		myTrack = track;
		init();

	}

	@Override
	protected void execCreateChildren() {
		List<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		children.add(new SpeedPlotNode(myTrack));
		children.add(new ElevationPlotNode(myTrack));
		setChildNodes(children);
	}

	public TrackDTO getMyTrack() {
		return myTrack;
	}

	public void setMyTrack(TrackDTO myTrack) {
		this.myTrack = myTrack;
	}

	@Override
	public String getConfiguredName() {
		return myTrack.getFilename();
	}

	private TrackDetailPage content;

	@Override
	public AbstractPage getConfiguredPage() {
		if (content == null) {
			content = new TrackDetailPage(this, myTrack);
		}
		return content;

	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return null;
	}

	private boolean admin = false;

	public void startAdmin() {
		admin = true;
		getConfiguredPage();
		content.startAdmin();
	}

}
