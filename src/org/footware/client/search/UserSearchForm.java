package org.footware.client.search;

import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.tree.nodes.TrackNode;
import org.footware.shared.dto.UserSearchData;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserSearchForm extends AbstractSearchForm {

	SearchForm searchForm;

	public UserSearchForm(AbstractTreeNode node) {
		super(node);
	}

	@Override
	protected Widget getConfiguredContent() {
		searchForm = new SearchForm();
		return searchForm;
	}

	@Override
	public AbstractSearchData getSearch() {
		UserSearchData p = new UserSearchData();
		p.value = Integer.parseInt(searchForm.tracknumberFrom.getValue());
		return p;
	}

	@Override
	public void reload() {

	}

	public class SearchForm extends VerticalPanel {

		TextBox username;
		TextBox fullname;
		TextBox tracknumberFrom;
		TextBox tracknumberTo;

		public SearchForm() {
			Grid g = new Grid(3, 2);
			username = new TextBox();
			fullname = new TextBox();
			tracknumberFrom = new TextBox();
			tracknumberFrom.setWidth("50px");
			tracknumberTo = new TextBox();
			tracknumberTo.setWidth("50px");
			g.setWidget(0, 0, new HTML("Username"));
			g.setWidget(0, 1, username);
			g.setWidget(1, 0, new HTML("Full name"));
			g.setWidget(1, 1, fullname);
			g.setWidget(2, 0, new HTML("Number of tracks from / to"));
			HorizontalPanel hp = new HorizontalPanel();
			hp.add(tracknumberFrom);
			hp.add(tracknumberTo);
			g.setWidget(2, 1, hp);
			Button b = new Button("Search");
			b.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					doSearch();
				}
			});
			add(g);
			add(b);
		}
	}

}
