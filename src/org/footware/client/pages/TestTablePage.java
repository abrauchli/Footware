/*
 * Copyright 2010 Andreas Brauchli, René Buffat, Florian Widmer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.footware.client.pages;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractTablePage;
import org.footware.client.framework.tree.AbstractTreeNode;

public class TestTablePage extends AbstractTablePage {

	public TestTablePage(AbstractTreeNode treeNode) {
		super(treeNode);
	}

	@Override
	public void execLoadTableData() {
		setTableData(new String[][] { 
				{ "one", "two" }, 
				{ "three", "four" } });
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

	}
}
