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
import org.footware.shared.dto.UserSearchData;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserSearchForm extends AbstractSearchForm {

	SearchForm searchForm;

	public UserSearchForm(AbstractTreeNode node) {
		super(node);
		init();
	}

	@Override
	protected Widget getConfiguredContent() {
		searchForm = new SearchForm();
		return searchForm;
	}

	@Override
	public AbstractSearchData getSearch() {
		UserSearchData p = new UserSearchData();
//		p.value = Integer.parseInt(searchForm.tracknumberFrom.getValue());
		return p;
	}

	@Override
	public void reload() {

	}

	public class SearchForm extends VerticalPanel {

	//	TextBox username;
		TextBox fullname;
		TextBox tracknumberFrom;
		TextBox tracknumberTo;

		public SearchForm() {
			Grid g = new Grid(3, 2);
			//username = new TextBox();
			fullname = new TextBox();
			tracknumberFrom = new TextBox();
			tracknumberFrom.setWidth("50px");
			tracknumberTo = new TextBox();
			tracknumberTo.setWidth("50px");
			//g.setWidget(0, 0, new HTML("Username"));
			//g.setWidget(0, 1, username);
			g.setWidget(0, 0, new HTML("Full name"));
			g.setWidget(0, 1, fullname);
			g.setWidget(1, 0, new HTML("Number of tracks from / to"));
			HorizontalPanel hp = new HorizontalPanel();
			hp.add(tracknumberFrom);
			hp.add(tracknumberTo);
			g.setWidget(1, 1, hp);
			Button b = new Button("Search");
			b.addClickHandler(new ClickHandler() {

				public void onClick(ClickEvent event) {
					doSearch();
				}
			});
			add(g);
			add(b);
		}
	}

}
