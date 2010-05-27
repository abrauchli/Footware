package org.footware.client.dialogs;

import java.util.Date;

import org.footware.client.Session;
import org.footware.client.pages.TrackDetailPage.TrackDetailForm;
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
	private TrackDetailForm container;

	public CommentBox(TrackDTO track, TrackDetailForm container) {
		super();
		this.container = container;
		this.track = track;
		text = new TextArea();
		text.setWidth("200px");
		text.setHeight("100px");
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
		vp.add(hp);
		add(vp);
		setText("Add a comment");
	}

	private void doSave() {
		CommentDTO c = new CommentDTO(text.getValue(), Session.getUser(), track);
		c.setTime(new Date());
		container.addComment(c);

	}

	public void doClear() {
		text.setValue("");
	}
}
