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

package org.footware.client.desktop;

import org.footware.client.Session;
import org.footware.client.framework.views.DataView;
import org.footware.client.framework.views.SearchView;
import org.footware.client.framework.views.TreeView;
import org.footware.client.tree.AdminViewTree;
import org.footware.client.tree.PrivateViewTree;
import org.footware.client.tree.PublicViewTree;
import org.footware.shared.dto.UserDTO;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * the main view, contains all other view stuff.
 * 
 * @author flwidmer
 * 
 */
public class Desktop extends VerticalPanel {
	private TreeView myTree;
	private TreeView priv;
	private TreeView admin;
	private StackPanel myStackPanel;
	private DataView dv;
	private SearchView sv;

	public Desktop() {
		// super(1, 2);
		// setWidth("100%");
		// setHeight("100%");
		dv = new DataView();
		sv = new SearchView();
		// add the tree
		myTree = new TreeView(new PublicViewTree(), dv, sv);

		// TODO: CHECK if we really need this prior to the user's login!
		// PrivateViewTree requires a User to be logged in

		priv = new TreeView(new PrivateViewTree(), dv, sv);
		// admin = new TreeView(new AdminViewTree(), dv, sv);

		myStackPanel = new StackPanel();
		myStackPanel.add(myTree, "Public view");
		// sp.add(priv, "Private view");
		myStackPanel.setWidth("300px");
		// sp.setHeight("100%");

		MenuBar mb = new TopMenu(this);
		// mb.setWidth("100%");
		add(mb);
		HorizontalPanel hp2 = new HorizontalPanel();
		// hp2.setHeight("100%");
		hp2.add(myStackPanel);
		VerticalPanel vp = new VerticalPanel();
		hp2.add(vp);
		vp.add(dv);
		vp.add(sv);
		add(hp2);
	}

	public void login() {
		UserDTO u = Session.getUser();
		if (u != null) {
			if (u.getIsAdmin()) {
				admin.setVisible(true);
			}
			priv = new TreeView(new PrivateViewTree(), dv, sv);
			myStackPanel.insert(priv,2);
		}
	}

	public void logout() {
		admin.setVisible(false);
		myStackPanel.remove(priv);
		priv = null;
	}
}
