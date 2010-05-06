package org.footware.client.framework;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPage extends Grid {
	public AbstractPage() {
		super(1, 1);
		init();
	}

	protected void init() {
		setWidget(0, 0, getConfiguredContent());
	}

	protected abstract Widget getConfiguredContent();

	public void reload(){
		
	}
}
