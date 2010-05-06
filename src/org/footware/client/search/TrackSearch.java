package org.footware.client.search;

import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TrackSearch extends AbstractSearchForm {

	private TextBox tb;

	public TrackSearch(AbstractTreeNode node) {
		super(node);
	}

	@Override
	public AbstractSearchData getSearch() {
		TrackSearchData sd = new TrackSearchData();
		sd.value = Integer.parseInt(tb.getValue());
		return sd;
	}

	@Override
	protected Widget getConfiguredContent() {
		tb = new TextBox();
		Button b = new Button("Search!");
		HorizontalPanel hp = new HorizontalPanel();
		b.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doSearch();
			}
		});
		hp.add(tb);
		hp.add(b);
		return hp;
	}
}
