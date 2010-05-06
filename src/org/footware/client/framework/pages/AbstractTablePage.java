package org.footware.client.framework.pages;

import java.util.List;

import org.apache.commons.httpclient.methods.GetMethod;
import org.footware.client.framework.search.AbstractSearchData;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractTablePage extends AbstractPage {
	private FlexTable table;
	private List<String> headers;

	public AbstractTablePage() {
		super();
	}

	protected abstract List<String> getConfiguredHeaders();

	protected void loadTableData() {
		loadTableData(null);
	}

	protected void loadTableData(AbstractSearchData search) {
		String[][] tableData;
		if (search == null) {
			tableData = execLoadTableData();
		} else {
			tableData = execLoadTableData(search);
		}
		if (tableData != null) {
			for (int i = 0; i < tableData.length; i++) {
				for (int k = 0; k < tableData[i].length; k++) {
					table.setWidget(i + 1, k, new HTML(tableData[i][k]));
				}
			}
		}
	}

	@Override
	protected Widget getConfiguredContent() {
		if (table == null) {
			table = new FlexTable();
		}
		table.setBorderWidth(getConfiguredBorderWidth());
		table.setCellPadding(getconfiguredCellPadding());
		if (getConfiguredStyle() != null) {
			table.setStyleName(getConfiguredStyle());
		}
		table.clear();
		loadHeaders();
		loadTableData();
		return table;
	}

	/**
	 * override to set the style
	 * 
	 * @return
	 */
	public String getConfiguredStyle() {
		return null;
	}

	/**
	 * override to set cellpadding
	 * 
	 * @return
	 */
	public int getconfiguredCellPadding() {
		return 0;
	}

	/**
	 * override to set Borderwidth
	 * 
	 * @return
	 */
	public int getConfiguredBorderWidth() {
		return 0;
	}

	protected void loadHeaders() {
		headers = getConfiguredHeaders();
		if (headers != null) {
			for (int i = 0; i < headers.size(); i++) {
				table.setWidget(0, i, new HTML(headers.get(i)));
			}
		}
	}

	public abstract String[][] execLoadTableData();

	public String[][] execLoadTableData(AbstractSearchData search) {
		return execLoadTableData();
	}

	@Override
	public void reload() {
	}
}
