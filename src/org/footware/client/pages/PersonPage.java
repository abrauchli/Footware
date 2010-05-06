package org.footware.client.pages;

import org.footware.client.framework.pages.AbstractFormPage;
import org.footware.client.search.PersonSearchData;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class PersonPage extends AbstractFormPage {
	private Grid content;

	@Override
	protected Widget getConfiguredContent() {
		content = new Grid(1, 2);
		HTML a = new HTML("test1");
		HTML b = new HTML("some stuff");
		content.setWidget(0, 0, a);
		content.setWidget(0, 1, b);
		return content;
	}

	@Override
	public void reload() {
		PersonSearchData sd = (PersonSearchData) getSearchFilter();
		content.setWidget(0, 0, new HTML(Integer.toString(sd.value)));
	}

}
