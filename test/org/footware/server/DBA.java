package org.footware.server;

import org.footware.server.db.HibernateUtil;
import org.footware.server.db.Comment;
import org.footware.server.db.Tag;
import org.footware.server.db.Track;
import org.footware.server.db.User;
import org.footware.shared.dto.TagDTO;
import org.footware.shared.dto.UserDTO;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBA {

	private Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
	
	@Test
	public void t10_newUser() {
		String email = "test@footware.org";
		String pwd = "test";
		UserDTO new_user = new UserDTO();
		new_user.setEmail(email);
		new_user.setPassword(pwd);
		Long id = (Long) session.save(new User(new_user));
		assert (id != null);
		
		User u = (User) session.load(User.class, id);
		assert (u != null);
		assert (u.getEmail().equals(email));
	}
	
	@Test
	public void t20_deactivateUser() {
		
	}
	
	@Test
	public void t30_addTrack() {
		
	}
	
	@Test
	public void t40_addTrackComment() {
		
	}
	
	@Test
	public void t50_addTag() {
		TagDTO new_tag = new TagDTO("tag");
		Long id = (Long) session.save(new Tag(new_tag));
		assert (id != null);
		
		Tag t = (Tag) session.load(Tag.class, id);
		assert (t != null);
		assert (t.getTag().equals("tag"));
	}

}
