package org.footware.server;

import java.util.List;
import java.util.Set;

import org.footware.server.db.Comment;
import org.footware.server.db.Tag;
import org.footware.server.db.Track;
import org.footware.server.db.TrackSegment;
import org.footware.server.db.User;
import org.footware.server.db.util.HibernateUtil;
import org.footware.server.db.util.TrackUtil;
import org.footware.server.db.util.UserUtil;
import org.footware.shared.dto.TagDTO;
import org.footware.shared.dto.UserDTO;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.junit.Test;

public class DBA {

	private Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
	private final String email = "test@footware.org";

//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
	
	@Test
	public void t10_newUser() {
		String pwd = "test";
		
		User u = UserUtil.getByEmail(email);
		if (u != null) {
			System.out.println("Deleting existing test user");
			u.delete();
		}

		UserDTO new_user = new UserDTO();
		new_user.setEmail(email);
		new_user.setPassword(pwd);
		new User(new_user).store();

		u = UserUtil.getByEmail(email);
		assert (u != null);
		assert (u.getEmail().equals(email));
	}

	@Test
	public void t20_deactivateUser() {
		User u = UserUtil.getByEmail(email);
		assert (u != null);

		u.setDisabled(true);
		u.store();

		u = null;
		u = UserUtil.getByEmail(email);
		assert (u.isDisabled());
	}

	@Test
	public void t30_addTrack() {
		User u = UserUtil.getByEmail(email);
		assert (u != null);
		assert (u.getTracks().size() == 0);
		Track t = new Track(u, "foo", "/foo");
		u.addTrack(t);

		//store just the user
		u.store();
		u = null;

		u = UserUtil.getByEmail(email);
		assert (u.getTracks().size() == 1);
	}
	
	@Test
	public void t31_addTrack() {
		User u = UserUtil.getByEmail(email);
		assert (u != null);
		Track t = new Track(u, "foo", "/foo");
		//this time store the track
		t.store();

		u = null;
		u = UserUtil.getByEmail(email);
		assert (u.getTracks().size() == 2);
	}

	@Test
	public void t35_addTrackSegmentToTrack() {
		List<Track> tracks = TrackUtil.getAllPublicTracks();
		assert (tracks.size() > 0);
		Track t = tracks.get(0);
		Long tid = t.getId();
		
		TrackSegment s = new TrackSegment();
		s.setLength(16.5);
		t.addSegment(s);
		session.update(t);
		
		t = null;
		t = (Track)session.get(Track.class, tid);
		assert (t != null);
		Set<TrackSegment> segs = t.getSegments();
		assert (segs.size() == 1);
		for (TrackSegment seg : segs)
			assert (seg.getLength() == 16.5);
	}
	
	@Test
	public void t40_addTrackComment() {
		User u = UserUtil.getByEmail(email);
		assert (u != null);

		Track[] tracks = new Track[0];
		u.getTracks().toArray(tracks);
		assert (tracks.length > 0);

		Comment c = new Comment("test comment", u);
		tracks[0].addComment(c);
		session.update(tracks[0]);

		Long id = (Long) session.save(c);
		assert (id != null);

		u = UserUtil.getByEmail(email);
		u.getTracks().toArray(tracks);
		boolean found = false;
		for (Comment tc : tracks[0].getComments()) {
			if (tc.getText().equals("test comment")) {
				found = true;
				break;
			}
		}
		assert (found);
	}

	@Test
	public void t50_addTag() {
		List<Track> tracks = TrackUtil.getAllPublicTracks();
		assert (tracks.size() > 0);
		Track t = tracks.get(0);

//		TagDTO new_tag = new TagDTO(t.getTrackDTO(), "tag");
//		Long id = (Long) session.save(new Tag(new_tag));
//		assert (id != null);

//		Tag tag = (Tag) session.load(Tag.class, id);
//		assert (tag != null);
//		assert (tag.getTag().equals("tag"));
	}

}
