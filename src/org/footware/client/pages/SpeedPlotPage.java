package org.footware.client.pages;

import java.util.List;

import org.footware.client.framework.pages.AbstractFormPage;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.fields.VisualizationWidget;
import org.footware.client.services.OutlineService;
import org.footware.client.services.OutlineServiceAsync;
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
	private String type;

	public SpeedPlotPage(AbstractTreeNode t, TrackDTO track, String type) {
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
			OutlineServiceAsync svc = GWT.create(OutlineService.class);
			svc.getTrackVisualizationList(mytrack.getId(),
					new AsyncCallback<List<TrackVisualizationDTO>>() {

						@Override
						public void onFailure(Throwable caught) {

						}

						@Override
						public void onSuccess(List<TrackVisualizationDTO> result) {
							if (result.get(0).getType().equals(type)) {
								vd = result.get(0);
								execLazyload();
							} else {
								vd = result.get(1);
								execLazyload();
								
							}
						}
					});

			content = g;
		}
		return content;
	}

	@Override
	public void execLazyload() {
		VisualizationWidget v = new VisualizationWidget();
		v.displayVisualization(vd);
		g.setWidget(0, 0, v);
	}
}
