package org.footware.client.dialogs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

		Button submit = new Button("Submit");
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

}
