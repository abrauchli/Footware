package org.footware.client.search;


import org.footware.client.framework.AbstractSearchData;
import org.footware.client.framework.AbstractSearchForm;
import org.footware.client.framework.AbstractTreeNode;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class PersonSearchForm extends AbstractSearchForm {

	private TextBox box;

	public PersonSearchForm(AbstractTreeNode node) {
		super(node);
	}

	@Override
	protected Widget getConfiguredContent() {
		Grid g = new Grid(2, 2);
		g.setWidget(0, 0, new Label("Name"));
		box = new TextBox();
		g.setWidget(0, 1, box);
		Button b = new Button("search!");
		b.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doSearch();
			}
		});
		g.setWidget(1, 0, b);
		return g;
	}

	@Override
	public AbstractSearchData getSearch() {
		PersonSearchData p = new PersonSearchData();
		p.value = Integer.parseInt(box.getValue());
		return p;
	}

	@Override
	public void reload() {

	}

}
