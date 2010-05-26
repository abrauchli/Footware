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

import org.footware.client.dialogs.LoginBox;
import org.footware.client.dialogs.SignupBox;
import org.footware.client.dialogs.UploadTrackBox;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class TopMenu extends MenuBar {

	private LoginBox lb = new LoginBox(this);
	private UploadTrackBox ub = new UploadTrackBox();
	private SignupBox sb = new SignupBox(this);

	private MenuItem logout;
	private MenuItem login;
	private MenuItem signUp;
	private MenuItem uploadTrack;

	private Desktop myDesktop;

	public TopMenu(Desktop desktop) {

		myDesktop = desktop;
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

		signUp = new MenuItem("Sign up", new Command() {

			@Override
			public void execute() {
				sb.center();
			}
		});
		addItem(signUp);

		addSeparator();

		uploadTrack = new MenuItem("Upload Track", new Command() {
			@Override
			public void execute() {
				ub.center();
			}
		});
		uploadTrack.setVisible(false);
		addItem(uploadTrack);

	}

	public void login() {
		login.setVisible(false);
		logout.setVisible(true);
		signUp.setVisible(false);
		uploadTrack.setVisible(true);
		myDesktop.login();
	}

	public void logout() {
		logout.setVisible(false);
		login.setVisible(true);
		signUp.setVisible(true);
		uploadTrack.setVisible(false);
		myDesktop.logout();
	}

	public void startSignup() {
		sb.center();
	}

}
