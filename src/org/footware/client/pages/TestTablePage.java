package org.footware.client.pages;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractTablePage;


public class TestTablePage extends AbstractTablePage {

	public TestTablePage() {
		super();
	}

	@Override
	public String[][] execLoadTableData() {
		return new String[][] { { "one", "two" }, { "three", "four" } };
	}

	@Override
	protected List<String> getConfiguredHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("h1");
		headers.add("h2");
		return headers;
	}

	@Override
	public int getConfiguredBorderWidth() {
		return 1;
	}

	@Override
	public int getconfiguredCellPadding() {
		return 1;
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}
}
