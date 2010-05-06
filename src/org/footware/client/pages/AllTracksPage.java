package org.footware.client.pages;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractTablePage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.search.TrackSearchData;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

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

	@Override
	public boolean getconfiguredClickEnabled() {
		return true;
	}

	DialogBox db;

	@Override
	public void rowClicked(int row) {
		if (db == null) {
			db = new DialogBox();
			Button b = new Button("close");
			b.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					db.hide();
				}
			});
			db.add(b);
		}
		db.setText("Row clicked: " + Integer.toString(row));
		db.center();
	}
}
