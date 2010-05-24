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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public class UploadTrackBox extends DialogBox {
	private TextBox trackName;
	private CheckBox enableComments;
	private ListBox privacy;
	private TextArea notes;
	private FileUpload file;
	private FormPanel form;

	public UploadTrackBox() {
		setText("Upload a Track");

		Grid g = new Grid(6, 2);

		setAutoHideEnabled(true);
		setGlassEnabled(true);

		trackName = new TextBox();
		trackName.setName("name");
		g.setWidget(0, 0, new HTML("Name"));
		g.setWidget(0, 1, trackName);

		privacy = new ListBox();
		privacy.setName("privacy");
		privacy.addItem("Public", "public");
		privacy.addItem("Private", "private");
		g.setWidget(1, 0, new HTML("Privacy"));
		g.setWidget(1, 1, privacy);

		enableComments = new CheckBox();
		enableComments.setName("comments");
		g.setWidget(2, 0, new HTML("Enable comments"));
		g.setWidget(2, 1, enableComments);

		notes = new TextArea();
		notes.setName("notes");
		notes.setSize("200px", "50px");
		g.setWidget(3, 0, new HTML("Notes"));
		g.setWidget(3, 1, notes);

		file = new FileUpload();
		file.setName("file");
		g.setWidget(4, 0, new HTML("Select File"));
		g.setWidget(4, 1, file);

		Button upload = new Button("Upload");
		upload.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doUpload();
				hide();
			}

		});

		Button close = new Button("Cancel");
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});

		g.setWidget(5, 0, upload);
		g.setWidget(5, 1, close);

		form = new FormPanel();
		// TODO implement httpservlet to accept file.
		form.setAction("/footware/trackUpload");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		form.add(g);
		add(form);
	}

	private void doUpload() {
		// TODO Auto-generated method stub
		form.submit();
	}
}
