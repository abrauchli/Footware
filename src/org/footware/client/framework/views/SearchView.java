package org.footware.client.framework.views;

import org.footware.client.framework.search.AbstractSearchForm;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;

public class SearchView extends Grid {

	public SearchView() {
		super(1, 1);
	}

	public void display(AbstractSearchForm form) {
		setHeight("300px");
		ScrollPanel sp = new ScrollPanel();
		sp.add(form);
		setWidget(0, 0, sp);
	}

}
