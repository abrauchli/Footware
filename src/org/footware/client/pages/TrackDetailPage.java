package org.footware.client.pages;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractFormPage;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.shared.dto.CommentDTO;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.UserDTO;

import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class TrackDetailPage extends AbstractFormPage {

	public TrackDetailPage(AbstractTreeNode treeNode) {
		super(treeNode);
	}

	private TrackDTO myTrack;
	private TrackDetailForm content;

	@Override
	protected Widget getConfiguredContent() {
		content = new TrackDetailForm();
		loadData();
		return content;
	}

	private void loadData() {
		// TODO load data from myTrack here
	}

	public void setMyTrack(TrackDTO myTrack) {
		this.myTrack = myTrack;
	}

	public TrackDTO getMyTrack() {
		return myTrack;
	}

	public class TrackDetailForm extends DockPanel {
		TextBox user;
		TextArea notes;
		TextBox trackpoints;
		TextBox length;
		DateBox startdate;

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
			startdate = new DateBox();
			startdate.setEnabled(false);
			Grid g = new Grid(4, 2);
			g.setWidget(0, 0, new HTML("Track by"));
			g.setWidget(0, 1, user);
			g.setWidget(1, 0, new HTML("Number of trackpoints"));
			g.setWidget(1, 1, trackpoints);
			g.setWidget(2, 0, new HTML("Length"));
			g.setWidget(2, 1, length);
			g.setWidget(3, 0, new HTML("Date"));
			g.setWidget(3, 1, startdate);
			VerticalPanel vp = new VerticalPanel();
			vp.add(g);
			vp.add(notes);
			ScrollPanel sp = new ScrollPanel();
			sp.setHeight("300px");
			sp.add(loadComments());
			// TODO use map widget here
			Frame map = new Frame("http://maps.google.com");
			map.setHeight("400px");
			map.setWidth("400px");
			add(sp, SOUTH);
			// TODO use tracktitle here
			add(new HTML("<b>" + "TRACKTITLE" + "</b>"), NORTH);
			add(map, EAST);
			add(vp, CENTER);
		}

		private Widget loadComments() {
			// TODO remove stuffe here so it works
			List<CommentDTO> c;// = myTrack.getComments();
			c = new ArrayList<CommentDTO>();
			for (int i = 0; i < 20; i++) {
				c
						.add(new CommentDTO(
								"balblablabla stussA DisclosurePanel has a header area and underneath, a content area. By clicking on the header, you can open or close (show or hide) the content. Before 1.4, I had written my own which had been quite popular - it's not indispensable, but in the right conditions it's really handy. \n Useful where space is short. For example, you might have a long list (telephone directory?) which displays basic identifying information (name and number) but by clicking the header, you can display more details.",
								new UserDTO()));
			}
			VerticalPanel vp = new VerticalPanel();
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

	}

	public void editableMode() {
		content.notes.setReadOnly(false);
	}

	@Override
	public void reload() {
		super.reload();
	}
}