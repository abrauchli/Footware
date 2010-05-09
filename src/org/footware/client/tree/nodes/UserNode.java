package org.footware.client.tree.nodes;

import java.util.ArrayList;
import java.util.List;

import org.footware.client.framework.pages.AbstractPage;
import org.footware.client.framework.search.AbstractSearchData;
import org.footware.client.framework.search.AbstractSearchForm;
import org.footware.client.framework.tree.AbstractTreeNode;
import org.footware.client.pages.UserPage;
import org.footware.client.search.UserSearchData;
import org.footware.client.search.UserSearchForm;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.UserDTO;

public class UserNode extends AbstractTreeNode {

	private UserDTO myUser;

	public UserNode() {
		super();
	}

	@Override
	protected void execInit() {

	}

	@Override
	public String getConfiguredName() {
		return "Person";
	}

	@Override
	protected List<AbstractTreeNode> execCreateChildren() {
		ArrayList<AbstractTreeNode> children = new ArrayList<AbstractTreeNode>();
		// TODO load tracks of this particular user here
		TrackNode child = new TrackNode();
		child.setMyTrack(new TrackDTO());
		children.add(child);
		return children;
	}

	@Override
	public AbstractPage getConfiguredPage() {
		return new UserPage(this);
	}


	@Override
	protected List<AbstractTreeNode> execCreateChildren(
			AbstractSearchData search) {
		UserSearchData ps = (UserSearchData) search;
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		for (int i = 0; i < ps.value; i++) {
			list.add(new TestNode());
		}
		return list;
	}

	@Override
	public void search(AbstractSearchData search) {
		super.search(search);
	}

	public void setMyUser(UserDTO myUser) {
		this.myUser = myUser;
	}

	public UserDTO getMyUser() {
		return myUser;
	}

	@Override
	public AbstractSearchForm getConfiguredSearchForm() {
		// TODO Auto-generated method stub
		return null;
	}
}
