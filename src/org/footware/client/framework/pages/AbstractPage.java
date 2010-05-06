package org.footware.client.framework.pages;

import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPage extends Grid {

	private AbstractSearchForm searchForm;

	public AbstractPage() {
		super(1, 1);
		init();
	}

	protected void init() {
		setWidget(0, 0, getConfiguredContent());
	}

	/**
	 * return a widget representing the content.
	 * 
	 * @return
	 */
	protected abstract Widget getConfiguredContent();

	/**
	 * hook to override if you want to do something when the page is reloaded.
	 * You may for example want to reload data using the searchfilter:
	 * <code>getSearchFilter()</code>
	 */
	public void reload() {
	}

	public void setSearchForm(AbstractSearchForm sf) {
		searchForm = sf;
	}

	public AbstractSearchData getSearchFilter() {
		if (searchForm != null) {
			return searchForm.getSearch();
		} else {
			return null;
		}
	}
}
