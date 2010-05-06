package org.footware.client.framework.pages;

import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractTablePage extends AbstractPage {
	private FlexTable table;
	private List<String> headers;

	public AbstractTablePage() {
		super();
	}

	

	protected abstract List<String> getConfiguredHeaders();

	protected void loadTableData() {
		String[][] tableData = execLoadTableData();
		for (int i = 0; i < tableData.length; i++) {
			for (int k = 0; k < tableData[i].length; k++) {
				table.setWidget(i+1, k, new HTML(tableData[i][k]));
			}
		}
	}

	@Override
	protected Widget getConfiguredContent() {
		if(table == null){
			table = new FlexTable();
		}
		table.setBorderWidth(getConfiguredBorderWidth());
		table.setCellPadding(getconfiguredCellPadding());
		if(getConfiguredStyle() != null){
			table.setStyleName(getConfiguredStyle());
		}
		loadHeaders();
		loadTableData();
		return table;
	}



	public String getConfiguredStyle() {
		return null;
	}



	public int getconfiguredCellPadding() {
		return 0;
	}



	public int getConfiguredBorderWidth() {
		return 0;
	}



	protected void loadHeaders() {
		headers = getConfiguredHeaders();
		for (int i = 0; i < headers.size(); i++) {
			table.setWidget(0, i, new HTML(headers.get(i)));
		}
	}
	public abstract String[][] execLoadTableData();
	
}
