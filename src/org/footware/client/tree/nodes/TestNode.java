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

package org.footware.client.tree.nodes;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.TestTablePage;

public class TestNode extends AbstractTreeNode {

	public TestNode() {
		super();
	}

	@Override
	protected void execInit() {

	}

	@Override
	public String getConfiguredName() {
		return "testnode";
	}

	@Override
	protected void execCreateChildren() {
		// TODO Auto-generated method stub
	}

	@Override
	public AbstractPage getConfiguredPage() {
		// TODO Auto-generated method stub
		return new TestTablePage(this);
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void search(AbstractSearchData search) {
		// TODO Auto-generated method stub
		
	}
	
	

}
