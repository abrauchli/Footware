package org.footware.client.views;


import org.footware.client.framework.AbstractSearchForm;

import com.google.gwt.user.client.ui.Grid;

public class SearchView extends Grid {

	public SearchView() {
		super(1, 1);
	}

	public void display(AbstractSearchForm form) {
		setWidget(0, 0, form);
	}

}
