package org.footware.client.Desktop;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class TopMenu extends MenuBar {
	private LoginBox lb = new LoginBox();
	private UploadTrackBox ub = new UploadTrackBox();
	private MenuItem logout;
	private MenuItem login;

	public TopMenu() {
		setWidth("1300px");

		login = new MenuItem("Login", new Command() {

			@Override
			public void execute() {
				lb.center();
			}
		});
		addItem(login);

		logout = new MenuItem("Logout", new Command() {

			@Override
			public void execute() {
				logout();
			}
		});
		logout.setVisible(false);
		addItem(logout);

		MenuItem uploadTrack = new MenuItem("Upload Track", new Command() {
			@Override
			public void execute() {
				ub.center();
			}
		});
		addItem(uploadTrack);

	}

	public void login() {
		login.setVisible(false);
		logout.setVisible(true);
	}

	public void logout() {
		logout.setVisible(false);
		login.setVisible(true);
	}

	private class LoginBox extends DialogBox {
		public LoginBox() {
			setText("Login");
			Button close = new Button("close");
			close.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					lb.hide();
				}
			});
			Button login = new Button("Login");
			login.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					login();
					hide();
				}
			});
			HorizontalPanel hp = new HorizontalPanel();
			hp.add(login);
			hp.add(close);
			add(hp);
		}
	}

}
