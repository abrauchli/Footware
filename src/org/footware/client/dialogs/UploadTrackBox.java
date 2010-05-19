package org.footware.client.dialogs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

public class UploadTrackBox extends DialogBox {
	public UploadTrackBox() {
		Button close = new Button("close");
		setText("Upload a Track");
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		add(close);
	}
}
