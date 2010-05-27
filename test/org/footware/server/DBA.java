package org.footware.server;

import java.util.List;
import java.util.Set;

import org.footware.server.db.Comment;
import org.footware.server.db.Tag;
import org.footware.server.db.Track;
import org.footware.server.db.TrackSegment;
import org.footware.server.db.User;
import org.footware.server.db.util.TrackUtil;
import org.footware.server.db.util.UserUtil;
import org.footware.shared.dto.TagDTO;
import org.footware.shared.dto.UserDTO;
import org.junit.Test;

public class DBA {
	
	private final String email = "test@footware.org";

//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
	
	/**
	 * Removes the test user if it exists
	 * Such that all tests runs are on the same db set
	 */
	@Test
	public void t00_deleteUserIfExists() {
		User u = UserUtil.getByEmail(email);
		if (u != null) {
			System.out.println("Deleting existing test user");
			u.delete();
		}
	}
	
	/**
	 * Creates a new test user
	 */
	@Test
	public void t10_newUser() {
		String pwd = "test";
		
		UserDTO new_user = new UserDTO();
		new_user.setEmail(email);
		new_user.setPassword(pwd);
		new User(new_user).store();

		User u = UserUtil.getByEmail(email);
		assert (u != null);
		assert (u.getEmail().equals(email));
	}

	/**
	 * Deactivates the test user
	 */
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

	/**
	 * Adds a track to the user
	 */
	@Test
	public void t30_addTrackToUser() {
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
	
	/**
	 * Adds a track with the user in it
	 */
	@Test
	public void t31_addTrackWithUser() {
		User u = UserUtil.getByEmail(email);
		assert (u != null);
		Track t = new Track(u, "bar", "/bar");
		//this time store the track
		t.store();

		u = null;
		u = UserUtil.getByEmail(email);
		assert (u.getTracks().size() == 2);
	}
	
	/**
	 * Make track private
	 */
	@Test
	public void t32_makeTrackPrivate() {
		List<Track> ts = TrackUtil.getAllPublicTracks();
		int numtracks = ts.size();
		Track trk = null;
		for (Track t : ts) {
			if (t.getFilename().equals("foo")) {
				trk = t;
				break;
			}
		}
		assert (trk != null);
		trk.setPublicity(0);
		trk.store();
		ts = TrackUtil.getAllPublicTracks();
		assert (ts.size() == numtracks -1);
	}
	
	/**
	 * Disables a track
	 */
	@Test
	public void t32_disableTrack() {
		List<Track> ts = TrackUtil.getAllPublicTracks();
		int numtracks = ts.size();
		Track trk = null;
		for (Track t : ts) {
			if (t.getFilename().equals("bar")) {
				trk = t;
				break;
			}
		}
		assert (trk != null);
		
		trk.setDisabled(true);
		trk.store();
		
		ts = TrackUtil.getAllPublicTracks();
		assert (ts.size() == numtracks - 1);
	}

	/**
	 * Add a segment to a track
	 */
	@Test
	public void t35_addTrackSegmentToTrack() {
		List<Track> tracks = TrackUtil.getAllPublicTracks();
		assert (tracks.size() > 0);
		Track t = tracks.get(0);
		Long tid = t.getId();
		
		TrackSegment s = new TrackSegment();
		s.setLength(16.5);
		t.addSegment(s);
//		session.update(t);
		
		t = null;
//		t = (Track)session.get(Track.class, tid);
		assert (t != null);
		Set<TrackSegment> segs = t.getSegments();
		assert (segs.size() == 1);
		for (TrackSegment seg : segs)
			assert (seg.getLength() == 16.5);
	}
	
	/**
	 * Adds a comment to a track
	 */
	@Test
	public void t40_addTrackComment() {
		User u = UserUtil.getByEmail(email);
		assert (u != null);

		Track[] tracks = new Track[0];
		u.getTracks().toArray(tracks);
		assert (tracks.length > 0);

		Comment c = new Comment("test comment", u);
		tracks[0].addComment(c);
		tracks[0].store();

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

	/**
	 * Adds a tag to a track
	 */
	@Test
	public void t50_addTag() {
		List<Track> tracks = TrackUtil.getAllPublicTracks();
		assert (tracks.size() > 0);
		Track t = tracks.get(0);

		TagDTO new_tag = new TagDTO(t.getTrackDTO(), "tag");
		new Tag(new_tag).store();

		boolean found = false;
		for (Tag tag : t.getTags()) {
			if (tag.getTag().equals("tag")) {
				found = true;
				break;
			}
		}
		assert (found);
	}

	/**
	 * Removes a tag from a track
	 */
	@Test
	public void t51_deleteTag() {
		List<Track> tracks = TrackUtil.getAllPublicTracks();
		for (Track t : tracks) {
			for (Tag tag : t.getTags()) {
				if (tag.getTag().equals("tag"))
					tag.delete();
			}
		}
	}
}
