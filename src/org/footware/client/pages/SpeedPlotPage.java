package org.footware.client.pages;

import org.footware.client.framework.pages.AbstractFormPage;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.fields.VisualizationWidget;
import org.footware.client.services.TrackService;
import org.footware.client.services.TrackServiceAsync;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackVisualizationDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

public class SpeedPlotPage extends AbstractFormPage {

	private TrackDTO mytrack;
	private int type;

	public SpeedPlotPage(AbstractTreeNode t, TrackDTO track, int type) {
		super(t);
		mytrack = track;
		this.type = type;
		init();
	}

	private Grid g;
	private TrackVisualizationDTO vd;

	private Widget content;

	@Override
	protected Widget getConfiguredContent() {
		if (content == null) {
			g = new Grid(1, 1);
			;
			TrackServiceAsync svc = GWT.create(TrackService.class);
			svc.getTrackVisualization(type,
					new AsyncCallback<TrackVisualizationDTO>() {

						@Override
						public void onSuccess(TrackVisualizationDTO result) {
							vd = result;
							execLazyload();
						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub

						}
					});
			content = g;
		}
		return content;
	}

	@Override
	public void execLazyload() {
		// TODO implement
		// VisualizationWidget v = new VisualizationWidget();
		//
		// v.displayVisualization(vd);
		// g.setWidget(0, 0, v);
	}
}
