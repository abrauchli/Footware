package org.footware.client.views;

import org.footware.client.tree.FootwareTree;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * the main view, contains all other view stuff.
 * 
 * @author flwidmer
 * 
 */
public class Desktop extends HorizontalPanel {
	TreeView myTree;
	TreeView another;

	public Desktop() {
		// super(1, 2);
		DataView dv = new DataView();
		SearchView sv = new SearchView();
		// add the tree
		myTree = new TreeView(new FootwareTree(), dv, sv);
		another = new TreeView(new FootwareTree(), dv, sv);
		StackPanel sp = new StackPanel();
		sp.add(myTree, "Public view");
		sp.add(another,"another view");
		sp.setWidth("300px");
		add(sp);
		VerticalPanel vp = new VerticalPanel();
		add(vp);
		vp.add(dv);
		vp.add(sv);
		vp.setCellHeight(dv, "500px");
		vp.setCellHeight(sv, "300px");
		setCellWidth(dv, "800px");
	}
}
