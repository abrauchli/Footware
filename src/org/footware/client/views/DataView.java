package org.footware.client.views;


import org.footware.client.framework.AbstractPage;

import com.google.gwt.user.client.ui.Grid;

/**
 * the dataview class represents the part of the app where data is displayed
 * @author flwidmer
 *
 */
public class DataView extends Grid{
	
	public DataView() {
		super(1,1);
	}
	public void displayPage(AbstractPage page){
		clearCell(0, 0);
		setWidget(0, 0, page);
	}
}
