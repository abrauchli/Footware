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
import org.footware.client.services.UserService;
import org.footware.client.services.UserServiceAsync;
import org.footware.shared.dto.UserDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sun.java.swing.plaf.windows.resources.windows;

public class UserPage extends AbstractFormPage {
	private UserDTO myUser;

	public UserPage(AbstractTreeNode treeNode, UserDTO user) {
		super(treeNode);
		myUser = user;
	}

	private UserPageForm content;
	private boolean admin = false;

	@Override
	protected Widget getConfiguredContent() {
		content = new UserPageForm();
		content.loadContent();
		return content;
	}

	@Override
	public void reload() {
	}

	public class UserPageForm extends Grid {

		// private TextBox username;
		private TextBox fullname;
		private TextBox numberOfTracks;
		private Button save;
		private Button delete;

		public UserPageForm() {
			super(3, 2);
			// username = new TextBox();
			// username.setEnabled(false);
			fullname = new TextBox();
			fullname.setEnabled(false);
			numberOfTracks = new TextBox();
			numberOfTracks.setEnabled(false);
			// setWidget(0, 0, new HTML("Username"));
			// setWidget(0, 1, username);
			setWidget(0, 0, new HTML("Full name"));
			setWidget(0, 1, fullname);
			setWidget(1, 0, new HTML("Number of tracks"));
			setWidget(1, 1, numberOfTracks);
			save = new Button("save", new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					doSave();
				}
			});
			delete = new Button("delete", new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					doDelete();
				}
			});
			save.setVisible(false);
			delete.setVisible(false);
			setWidget(2, 0, save);
			setWidget(2, 1, delete);
		}

		private void loadContent() {
			fullname.setValue(myUser.getFullName());
			numberOfTracks.setValue(Integer
					.toString((myUser.getTracks().size())));
		}

		private void doSave() {
			myUser.setFullName(fullname.getValue());
			UserServiceAsync svc = GWT.create(UserService.class);
			svc.saveChanges(myUser, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						Window.alert("Successfully saved changes");
					} else {
						Window.alert("Could not save changes");
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Could not save changes");

				}
			});
		}

		private void doDelete() {
			UserServiceAsync svc = GWT.create(UserService.class);
			svc.deactivateUser(myUser, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						Window.alert("Successfully deleted user");
					} else {
						Window.alert("Could not delete");
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Could not delete");

				}
			});
		}
	}

	public void startAdmin() {
		admin = true;
		content.delete.setVisible(true);
		content.save.setVisible(true);
		content.fullname.setEnabled(true);
	}
}
