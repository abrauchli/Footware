package org.footware.client.dialogs;

import java.util.Date;

import org.footware.client.Session;
import org.footware.shared.dto.CommentDTO;
import org.footware.shared.dto.TrackDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CommentBox extends DialogBox {
	private TextArea text;
	private Button clear;
	private Button cancel;
	private Button save;
	private TrackDTO track;

	public CommentBox(TrackDTO track) {
		super();
		this.track = track;
		text = new TextArea();
		clear = new Button("clear", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doClear();
			}
		});
		cancel = new Button("Cancel", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		save = new Button("save", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doSave();
			}
		});
		VerticalPanel vp = new VerticalPanel();
		vp.add(text);
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(save);
		hp.add(clear);
		hp.add(cancel);
	}

	private void doSave() {
		CommentDTO c = new CommentDTO(text.getValue(), Session.getUser(), track);
		c.setTime(new Date());

	}
	public void doClear(){
		text.setValue("");
	}
}
