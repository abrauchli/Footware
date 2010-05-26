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

package org.footware.client.dialogs;

import org.footware.client.Session;
import org.footware.client.desktop.TopMenu;
import org.footware.client.services.UserService;
import org.footware.client.services.UserServiceAsync;
import org.footware.shared.dto.UserDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class SignupBox extends DialogBox {
	private TextBox name;
	private TextBox firstName;
	private TextBox email;
	private PasswordTextBox password;
	private TopMenu container;

	public SignupBox(TopMenu container) {
		this.container = container;
		setAutoHideEnabled(true);
		setGlassEnabled(true);
		name = new TextBox();
		firstName = new TextBox();
		email = new TextBox();
		password = new PasswordTextBox();
		Grid g = new Grid(6, 2);

		g.setWidget(0, 0, new HTML("Email (used to login)"));
		g.setWidget(0, 1, email);

		g.setWidget(1, 0, new HTML("Password"));
		g.setWidget(1, 1, password);

		g.setWidget(2, 0, new HTML("First name"));
		g.setWidget(2, 1, firstName);

		g.setWidget(3, 0, new HTML("Last name"));
		g.setWidget(3, 1, name);
		
		Button submit = new Button("Submit", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doSignup();
				hide();
			}
		});

		Button close = new Button("Close", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				hide();
			}
		});

		g.setWidget(5, 0, submit);
		g.setWidget(5, 1, close);
		setText("Sign up");
		add(g);
	}

	private void doSignup() {
		validate();
		UserDTO u = new UserDTO();
		u.setFullName(firstName.getValue() + " " + name.getValue());
		u.setEmail(email.getValue());
		u.setPassword(password.getValue());

		// call server and register user
		UserServiceAsync svc = GWT.create(UserService.class);
		svc.registerUser(u, new AsyncCallback<UserDTO>() {

					@Override
					public void onSuccess(UserDTO user) {
						if (user != null) {
							Window.alert("Welcome "+ user.getFullName());
							Session.setUser(user);
							container.login();
							hide();
						} else {
							Window.alert("Failure: This email address is already registered");
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("There was a Problem contacting the server: "
										+ caught.getMessage());
					}
				});
	}

	private void validate() {
		if (name.getValue().isEmpty()
				|| firstName.getValue().isEmpty()
				|| password.getValue().isEmpty()
				|| email.getValue().isEmpty()) {
			Window.alert("Please fill out all fields");
		}
	}
}
