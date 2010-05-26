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

package org.footware.client.pages;

import java.util.List;

import org.footware.client.dialogs.CommentBox;
import org.footware.client.framework.pages.AbstractFormPage;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.fields.FootwareMapWidget;
import org.footware.client.services.TrackService;
import org.footware.client.services.TrackServiceAsync;
import org.footware.shared.dto.CommentDTO;
import org.footware.shared.dto.TrackDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class TrackDetailPage extends AbstractFormPage {

	public TrackDetailPage(AbstractTreeNode treeNode, TrackDTO track) {
		super(treeNode);
		myTrack = track;
		init();
	}

	private TrackDTO myTrack;
	private TrackDetailForm content;

	@Override
	protected Widget getConfiguredContent() {
		content = new TrackDetailForm();
		return content;
	}

	public void setMyTrack(TrackDTO myTrack) {
		this.myTrack = myTrack;
	}

	public TrackDTO getMyTrack() {
		return myTrack;
	}

	public class TrackDetailForm extends DockPanel {
		private TextBox user;
		private TextArea notes;
		private TextBox trackpoints;
		private TextBox length;
		private DateBox startdate;
		private ListBox publicity;
		private HorizontalPanel mapPlaceholder;
		private FootwareMapWidget map;
		private Button delete;
		private Button save;
		private Button download;
		private FormPanel form;

		// TODO change publicity of a track
		public TrackDetailForm() {
			super();
			user = new TextBox();
			user.setReadOnly(true);
			notes = new TextArea();
			notes.setReadOnly(true);
			notes.setHeight("150px");
			notes.setWidth("250px");
			notes.setTitle("Notes");
			trackpoints = new TextBox();
			trackpoints.setReadOnly(true);
			length = new TextBox();
			length.setReadOnly(true);
			publicity = new ListBox();
			publicity.addItem("private", "0");
			publicity.addItem("public", "5");
			publicity.setEnabled(false);
			startdate = new DateBox();
			startdate.setEnabled(false);
			save = new Button("save", new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					doSave();
				}

			});
			delete = new Button("delete", new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					doDelete();
				}

			});
			save.setVisible(false);
			delete.setVisible(false);
			Grid g = new Grid(5, 2);
			g.setWidget(0, 0, new HTML("Track by"));
			g.setWidget(0, 1, user);
			g.setWidget(1, 0, new HTML("Number of trackpoints"));
			g.setWidget(1, 1, trackpoints);
			g.setWidget(2, 0, new HTML("Length"));
			g.setWidget(2, 1, length);
			g.setWidget(3, 0, new HTML("Date"));
			g.setWidget(3, 1, startdate);
			g.setWidget(4, 0, new HTML("publicity"));
			g.setWidget(4, 1, publicity);

			VerticalPanel vp = new VerticalPanel();
			vp.add(g);
			vp.add(notes);

			HorizontalPanel hp = new HorizontalPanel();
			hp.add(save);
			hp.add(delete);
			form = new FormPanel();
			HorizontalPanel fp = new HorizontalPanel();
			download = new Button("Download this track", new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					form.submit();
				}
			});
			TextBox trackid = new TextBox();
			trackid.setName("trackid");
			trackid.setValue(Long.toString(myTrack.getId()));
			trackid.setVisible(false);
			TextBox userid = new TextBox();
			userid.setName("userid");
			userid.setVisible(false);
			userid.setValue(myTrack.getUser().getEmail());
			fp.add(download);
			fp.add(userid);
			fp.add(trackid);
			form.setMethod(FormPanel.METHOD_POST);
//			form.setEncoding(FormPanel.)
			form.setAction("/footware/trackDownload");
			form.add(fp);
			hp.add(form);
			vp.add(hp);
			ScrollPanel sp = new ScrollPanel();
			sp.setHeight("300px");
			sp.add(loadComments());
			mapPlaceholder = new HorizontalPanel();
			add(sp, SOUTH);
			add(new HTML("<b>" + myTrack.getFilename() + "</b>"), NORTH);
			add(mapPlaceholder, EAST);
			add(vp, CENTER);
		}

		CommentBox cb;

		private Widget loadComments() {
			List<CommentDTO> c = myTrack.getComments();
			VerticalPanel vp = new VerticalPanel();
			vp.add(new Button("add comment", new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if (cb == null) {
						cb = new CommentBox(myTrack);
					}
					cb.doClear();
					cb.center();
				}
			}));

			for (CommentDTO comment : c) {
				DisclosurePanel dc = new DisclosurePanel();
				dc.setHeader(new HTML(comment.getUser().getFullName() + "-"
						+ comment.getTime()));
				HTML content = new HTML(comment.getText(), true);
				content.setWidth("500px");
				dc.setContent(content);
				vp.add(dc);
			}
			return vp;
		}

		private void loadData(TrackDTO t) {
			length.setValue(Double.toString(t.getLength()));
			notes.setValue(t.getNotes());
			startdate.setValue(t.getStartTime());
			trackpoints.setValue(Integer.toString(t.getTrackpoints()));
			user.setValue(t.getUser().getFullName());
			if (t.getPublicity() == 0) {
				publicity.setSelectedIndex(0);
			} else {
				publicity.setSelectedIndex(1);
			}
		}

		private void doSave() {
			myTrack.setNotes(notes.getValue());
			// XXX here be dragons
			myTrack.setPublicity(publicity.getSelectedIndex() * 5);
			TrackServiceAsync svc = GWT.create(TrackService.class);
			svc.saveChanges(myTrack, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						Window.alert("Success");
					} else {
						Window.alert("Server side failure");
					}

				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Failure");
				}
			});
		}

		private void doDelete() {
			TrackServiceAsync svc = GWT.create(TrackService.class);
			svc.deactivateTrack(myTrack, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						Window.alert("Success");
					} else {
						Window.alert("Server side failure");
					}

				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Failure");
				}
			});
		}

	}

	public void editableMode() {
		content.notes.setReadOnly(false);
		content.save.setVisible(true);
		content.delete.setVisible(true);
		content.publicity.setVisible(true);
	}

	@Override
	public void reload() {
		super.reload();
	}

	@Override
	public String getConfiguredWidth() {
		return "1200px";
	}

	@Override
	public void execLazyload() {
		content.loadData(getMyTrack());
		content.map = new FootwareMapWidget();
		content.mapPlaceholder.add(content.map);
	}

	private boolean admin = false;

	public void startAdmin() {
		admin = true;
		editableMode();
	}
}
