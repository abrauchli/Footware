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

package org.footware.client.Desktop;

import org.footware.client.framework.views.DataView;
import org.footware.client.framework.views.SearchView;
import org.footware.client.framework.views.TreeView;
import org.footware.client.tree.PrivateViewTree;
import org.footware.client.tree.PublicViewTree;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * the main view, contains all other view stuff.
 * 
 * @author flwidmer
 * 
 */
public class Desktop extends VerticalPanel {
	TreeView myTree;
	TreeView priv;

	public Desktop() {
		// super(1, 2);
//		setWidth("100%");
//		setHeight("100%");
		DataView dv = new DataView();
		SearchView sv = new SearchView();
		// add the tree
		myTree = new TreeView(new PublicViewTree(), dv, sv);
		priv = new TreeView(new PrivateViewTree(), dv, sv);
		StackPanel sp = new StackPanel();
		sp.add(myTree, "Public view");
		sp.add(priv, "Private view");
		sp.setWidth("300px");
//		sp.setHeight("100%");

		MenuBar mb = new TopMenu();
//		mb.setWidth("100%");
		add(mb);
		HorizontalPanel hp2 = new HorizontalPanel();
//		hp2.setHeight("100%");
		hp2.add(sp);
		VerticalPanel vp = new VerticalPanel();
		hp2.add(vp);
		vp.add(dv);
		vp.add(sv);
		add(hp2);
	}
}
