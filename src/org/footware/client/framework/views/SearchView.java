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

package org.footware.client.framework.views;

import org.footware.client.framework.search.AbstractSearchForm;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;

public class SearchView extends Grid {

	public SearchView() {
		super(1, 1);
	}

	public void display(AbstractSearchForm form) {
		setHeight("300px");
		ScrollPanel sp = new ScrollPanel();
		sp.add(form);
		setWidget(0, 0, sp);
	}

}
