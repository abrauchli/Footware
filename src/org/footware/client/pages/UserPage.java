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

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class UserPage extends AbstractFormPage {
	public UserPage(AbstractTreeNode treeNode) {
		super(treeNode);
	}

	private UserPageForm content;

	@Override
	protected Widget getConfiguredContent() {
		content = new UserPageForm();
		loadContent();
		return content;
	}

	private void loadContent() {
		// TODO flöru load user data here

	}

	@Override
	public void reload() {
	}

	public class UserPageForm extends Grid {

		TextBox username;
		TextBox fullname;
		TextBox numberOfTracks;

		public UserPageForm() {
			super(3, 2);
			username = new TextBox();
			username.setEnabled(false);
			fullname = new TextBox();
			fullname.setEnabled(false);
			numberOfTracks = new TextBox();
			numberOfTracks.setEnabled(false);
			setWidget(0, 0, new HTML("Username"));
			setWidget(0, 1, username);
			setWidget(1, 0, new HTML("Full name"));
			setWidget(1, 1, fullname);
			setWidget(2, 0, new HTML("Number of tracks"));
			setWidget(2, 1, numberOfTracks);
		}
	}
}
