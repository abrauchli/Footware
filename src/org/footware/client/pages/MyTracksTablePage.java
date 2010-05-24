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

import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.tree.AbstractTreeNode;

public class MyTracksTablePage extends AllTracksPage{

	public MyTracksTablePage(AbstractTreeNode treeNode) {
		super(treeNode);
	}
	@Override
	public String[][] execLoadTableData(AbstractSearchData search) {
		//TODO get user and find suitable stuff to do here
		//TODO andy methode um tracks eines users als tabelle zu laden (abhängig von suche)
		return new String[][] {{ "bogus track", "dumbo user", "666",
				"500 miles", "8.11.1984", "0" }};
	}
	@Override
	public String[][] execLoadTableData() {
		// TODO do table load
		//TODO andy wie oben aber mit leerer suche
		return new String[][] {{ "bogus track", "dumbo user", "666",
			"500 miles", "8.11.1984", "0" }};
	}
}
