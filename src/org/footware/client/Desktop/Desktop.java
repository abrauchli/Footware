package org.footware.client.Desktop;

import org.footware.client.framework.views.DataView;
import org.footware.client.framework.views.SearchView;
import org.footware.client.framework.views.TreeView;
import org.footware.client.tree.PrivateViewTree;
import org.footware.client.tree.PublicViewTree;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * the main view, contains all other view stuff.
 * 
 * @author flwidmer
 * 
 */
public class Desktop extends VerticalPanel {
	TreeView myTree;
	TreeView priv;

	public Desktop() {
		// super(1, 2);
//		setWidth("100%");
//		setHeight("100%");
		DataView dv = new DataView();
		SearchView sv = new SearchView();
		// add the tree
		myTree = new TreeView(new PublicViewTree(), dv, sv);
		priv = new TreeView(new PrivateViewTree(), dv, sv);
		StackPanel sp = new StackPanel();
		sp.add(myTree, "Public view");
		sp.add(priv, "Private view");
		sp.setWidth("300px");
//		sp.setHeight("100%");

		MenuBar mb = new TopMenu();
//		mb.setWidth("100%");
		add(mb);
		HorizontalPanel hp2 = new HorizontalPanel();
//		hp2.setHeight("100%");
		hp2.add(sp);
		VerticalPanel vp = new VerticalPanel();
		hp2.add(vp);
		vp.add(dv);
		vp.add(sv);
		add(hp2);
	}
}
