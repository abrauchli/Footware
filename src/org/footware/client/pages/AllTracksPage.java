package org.footware.client.pages;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractTablePage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.search.TrackSearchData;

public class AllTracksPage extends AbstractTablePage {

	@Override
	public String[][] execLoadTableData() {
		return new String[][] { { "Please search first to narrow down the selection" } };
	}

	@Override
	public String[][] execLoadTableData(AbstractSearchData search) {
		TrackSearchData sd = (TrackSearchData) getSearchFilter();
		String[][] result = new String[sd.value][2];
		for (int i = 0; i < sd.value; i++) {
			result[i] = new String[] { Integer.toString(i), "bogus track" };
		}
		return result;
	}

	@Override
	protected List<String> getConfiguredHeaders() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("id");
		headers.add("data");
		return null;
	}

	@Override
	public void reload() {
		loadTableData(getSearchFilter());
	}
	
	@Override
	public int getconfiguredCellPadding() {
		return 3;
	}

}
