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
		// TODO Auto-generated method stub

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
