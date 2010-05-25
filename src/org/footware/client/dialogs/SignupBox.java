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

import org.footware.shared.dto.UserDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class SignupBox extends DialogBox {
	private TextBox username;
	private TextBox name;
	private TextBox firstName;
	private TextBox email;
	private PasswordTextBox password;

	public SignupBox() {
		setAutoHideEnabled(true);
		setGlassEnabled(true);
		username = new TextBox();
		name = new TextBox();
		firstName = new TextBox();
		email = new TextBox();
		password = new PasswordTextBox();
		Grid g = new Grid(6, 2);

		g.setWidget(0, 0, new HTML("Username"));
		g.setWidget(0, 1, username);

		g.setWidget(1, 0, new HTML("Password"));
		g.setWidget(1, 1, password);

		g.setWidget(2, 0, new HTML("First name"));
		g.setWidget(2, 1, firstName);

		g.setWidget(3, 0, new HTML("Last name"));
		g.setWidget(3, 1, name);

		g.setWidget(4, 0, new HTML("Email"));
		g.setWidget(4, 1, email);

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
		u.setFullName(name.getValue() + " " + firstName.getValue());
		u.setEmail(email.getValue());
		u.setPassword(password.getValue());
		// TODO andy hier müsste der user persistiert werden... u.persist()?
		// TODO sign user in directly?
		
	}

	private void validate() {
		if (username.getValue().isEmpty() || name.getValue().isEmpty()
				|| firstName.getValue().isEmpty()
				|| password.getValue().isEmpty() || email.getValue().isEmpty()) {
			Window.alert("Please fill out all fields");
		}
	}
}
