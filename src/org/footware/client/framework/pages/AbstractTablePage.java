package org.footware.client.framework.pages;

import java.util.List;

import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.tree.AbstractTreeNode;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractTablePage extends AbstractPage {
	private FlexTable table;
	private List<String> headers;

	public AbstractTablePage(AbstractTreeNode treeNode) {
		super(treeNode);
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
				if (getconfiguredClickEnabled()) {
					table.addClickHandler(new ClickHandler() {

						public void onClick(ClickEvent event) {
							rowClickedInternal(table.getCellForEvent(event)
									.getRowIndex());
						}
					});
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
		if (getConfiguredDisplayHeaders()) {
			loadHeaders();
		}
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
				table.setWidget(0, i, new HTML("<b>" + headers.get(i)+"</b>"));
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

	/**
	 * override to enable clicking on table rows.
	 * 
	 * @return
	 */
	public boolean getconfiguredClickEnabled() {
		return false;
	}

	private void rowClickedInternal(int row) {
			rowClicked(row - 1);
	}

	/**
	 * override to react to the event of a row being clicked
	 * 
	 * @param rowNum
	 *            this is the rownumber. It is zero based.
	 */
	public void rowClicked(int rowNum) {
	}

	/**
	 * override this to set headers display to off.
	 * 
	 * @return
	 */
	public boolean getConfiguredDisplayHeaders() {
		return true;
	}
}
