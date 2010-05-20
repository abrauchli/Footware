package org.footware.client.dialogs;

import org.footware.client.Desktop.TopMenu;
import org.footware.client.services.LoginService;
import org.footware.client.services.LoginServiceAsync;

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

public class LoginBox extends DialogBox {
	private TopMenu myTopMenu;
	private TextBox username;
	private PasswordTextBox password;

	public LoginBox(TopMenu container) {
		setAutoHideEnabled(true);
		setGlassEnabled(true);
		
		myTopMenu = container;
		setText("Login");

		Grid g = new Grid(4, 2);
		username = new TextBox();
		password = new PasswordTextBox();

		g.setWidget(0, 0, new HTML("Username"));
		g.setWidget(0, 1, username);

		g.setWidget(1, 0, new HTML("Password"));
		g.setWidget(1, 1, password);

		Button close = new Button("close");
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		Button login = new Button("Login");
		login.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				login();
			}
		});
		g.setWidget(2, 0, login);
		g.setWidget(2, 1, close);
		HTML registerLink = new HTML("Register");
		registerLink.setStylePrimaryName("a");
		registerLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
				myTopMenu.startSignup();
			}
		});
		g.setWidget(3, 0, registerLink);
		add(g);
	}

	private void login() {
		// input verification
		if (username.getValue().equals("") || password.getValue().equals("")) {
			Window.alert("Username and Password must be filled out");
			return;
		}

		// call server, get authentication
		LoginServiceAsync svc = GWT.create(LoginService.class);
		svc.login(username.getValue(), password.getValue(),
				new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						// save session key
						Window.alert(result);
						// do gui changes
						myTopMenu.login();
						hide();
					}

					@Override
					public void onFailure(Throwable caught) {
						Window
								.alert("There was a Problem contacting the server: "
										+ caught.getMessage());
					}
				});

	}
}