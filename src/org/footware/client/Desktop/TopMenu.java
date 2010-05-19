package org.footware.client.Desktop;

import org.footware.client.dialogs.LoginBox;
import org.footware.client.dialogs.UploadTrackBox;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class TopMenu extends MenuBar {
	private LoginBox lb = new LoginBox(this);
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

}
