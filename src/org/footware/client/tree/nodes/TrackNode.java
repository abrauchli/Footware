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

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.TrackDetailPage;
import org.footware.shared.dto.TrackDTO;

public class TrackNode extends AbstractTreeNode {

	private TrackDTO myTrack;

	public TrackNode(TrackDTO track) {
		myTrack = track;
	}

	@Override
	protected void execCreateChildren() {
	}

	public TrackDTO getMyTrack() {
		return myTrack;
	}

	public void setMyTrack(TrackDTO myTrack) {
		this.myTrack = myTrack;
	}

	@Override
	public String getConfiguredName() {
		// TODO use trackname from DTO or something like that.
		return "GETTRACKNAME";
	}

	@Override
	public AbstractPage getConfiguredPage() {
		TrackDetailPage p = new TrackDetailPage(this);
		p.setMyTrack(myTrack);
		return p;

	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		return null;
	}

}
