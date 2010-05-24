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

import org.footware.client.framework.pages.AbstractFormPage;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.hibernate.envers.synchronization.work.AddWorkUnit;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OwnPage extends AbstractFormPage {

	public OwnPage(AbstractTreeNode treeNode) {
		super(treeNode);
	}

	@Override
	protected Widget getConfiguredContent() {
		return new MyOwnPage();
	}

	public class MyOwnPage extends DockPanel {
		TextBox username;
		TextBox name;
		TextBox email;
		PasswordTextBox password;
		Button edit;
		Button ok;

		public MyOwnPage() {
			username = new TextBox();
			username.setEnabled(false);
			name = new TextBox();
			name.setEnabled(false);
			email = new TextBox();
			email.setEnabled(false);
			password = new PasswordTextBox();
			password.setEnabled(false);
			Grid g = new Grid(4, 2);
			g.setWidget(0, 0, new HTML("Username"));
			g.setWidget(0, 1, username);
			g.setWidget(1, 0, new HTML("Name"));
			g.setWidget(1, 1, name);
			g.setWidget(2, 0, new HTML("Email"));
			g.setWidget(2, 1, email);
			g.setWidget(3, 0, new HTML("Password"));
			g.setWidget(3, 1, password);
			Image img = new Image(
					"http://kilburnhall.files.wordpress.com/2009/05/moron.jpg");
			img.setHeight("100px");
			HorizontalPanel hp = new HorizontalPanel();
			edit = new Button("Edit my Profile");
			edit.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					switchMode(true);
				}
			});
			ok = new Button("Save");
			ok.setEnabled(false);
			ok.setVisible(false);
			ok.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					switchMode(false);
				}
			});
			hp.add(edit);
			hp.add(ok);
			add(hp, SOUTH);
			add(img, EAST);
			add(g, CENTER);

		}

		/**
		 * unlock the fields
		 */
		public void switchMode(boolean to) {
			name.setEnabled(to);
			email.setEnabled(to);
			password.setEnabled(to);
			ok.setVisible(to);
			ok.setEnabled(to);
			edit.setEnabled(!to);

		}
	}
}
