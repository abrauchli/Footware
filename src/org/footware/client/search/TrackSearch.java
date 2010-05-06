package org.footware.client.search;

import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
		sd.value = Integer.parseInt(searchform.length.getValue());
		return sd;
	}

	private SearchForm searchform;

	@Override
	protected Widget getConfiguredContent() {
		searchform = new SearchForm();
		return searchform;
	}

	public class SearchForm extends VerticalPanel {
		TextBox username; // TODO replace this with dropdown / suggestbox?
		CheckBox commentsEnabled;
		TextBox trackpoints;
		TextBox length;
		TextBox start;

		public SearchForm() {
			username = new TextBox();
			commentsEnabled = new CheckBox();
			trackpoints = new TextBox();
			length = new TextBox();
			start = new TextBox();
			Grid g = new Grid(2, 6);
			g.setCellPadding(5);
			g.setWidget(0, 0, new HTML("User"));
			g.setWidget(0, 1, username);
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

				@Override
				public void onClick(ClickEvent event) {
					doSearch();
				}
			});
			add(b);
		}
	}
}
