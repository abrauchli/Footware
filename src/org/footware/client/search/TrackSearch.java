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

package org.footware.client.search;

import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.shared.dto.TrackSearchData;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TrackSearch extends AbstractSearchForm {

	public TrackSearch(AbstractTreeNode node) {
		super(node);
	}

	@Override
	public AbstractSearchData getSearch() {
		TrackSearchData sd = new TrackSearchData();
//		sd.value = Integer.parseInt(searchform.length.getValue());
		return sd;
	}

	private SearchForm searchform;

	@Override
	protected Widget getConfiguredContent() {
		searchform = new SearchForm();
		return searchform;
	}

	public class SearchForm extends VerticalPanel {
		CheckBox commentsEnabled;
		TextBox trackpoints;
		TextBox length;
		TextBox start;

		public SearchForm() {
			commentsEnabled = new CheckBox();
			trackpoints = new TextBox();
			length = new TextBox();
			start = new TextBox();
			Grid g = new Grid(2, 6);
			g.setCellPadding(5);
			g.setWidget(0, 2, new HTML("Comments enabled"));
			g.setWidget(0, 3, commentsEnabled);
			g.setWidget(0, 4, new HTML("# of Trackpoints"));
			g.setWidget(0, 5, trackpoints);
			g.setWidget(1, 0, new HTML("length"));
			g.setWidget(1, 1, length);
			g.setWidget(1, 2, new HTML("Start"));
			g.setWidget(1, 3, start);
			add(g);
			Button b = new Button("Search!");
			b.addClickHandler(new ClickHandler() {

				public void onClick(ClickEvent event) {
					doSearch();
				}
			});
			add(b);
		}
	}
}
