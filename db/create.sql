DROP TABLE user     IF EXISTS;
DROP TABLE track    IF EXISTS;
DROP TABLE tag      IF EXISTS;
DROP TABLE track_tag IF EXISTS;
DROP TABLE `comment` IF EXISTS;
DROP VIEW  user_tag IF EXISTS;

CREATE TABLE user (
    id          INTEGER         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email       VARCHAR(128)    NOT NULL UNIQUE,
    password    CHAR(32)        NOT NULL,           /* MD5 hash */
    full_name   VARCHAR(64),
    is_admin    BOOLEAN         DEFAULT(0)
);
COMMENT ON COLUMN user.password IS 'MD5 hash';

CREATE TABLE track (
    id          INTEGER         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id     INTEGER         NOT NULL,
    filename    VARCHAR(128)    NOT NULL,           /* original filename as uploaded */
    path        VARCHAR(128),                       /* path on server where the file is saved */
    notes       VARCHAR(256),                       /* owner comments on the track */
    publicity   TINYINT         DEFAULT(0),         /* 0 track is private, (1 selected users, 2 selected group, 3 friends,) 4 registered users, 5 public */ 
    comments_enabled BOOLEAN    DEFAULT(1),         /* can other users post comments */

    trackpoints INTEGER         DEFAULT(0),         /* number of points in the track */
    length      INTEGER         DEFAULT(0),         /* track length in meters */
    mid_latitude    DOUBLE      DEFAULT(0),         /* mean latitude of the track */,
    mid_longitude   DOUBLE      DEFAULT(0),         /* mean longitude of the track */
    time_start  DATETIME,                           /* timestamp of the first trackpoint; timezones? */

    FOREIGN KEY (user_id)    REFERENCES user (id)
);
COMMENT ON COLUMN track.filename    IS 'original filename as uploaded';
COMMENT ON COLUMN track.path        IS 'path on server where the file is saved';
COMMENT ON COLUMN track.notes       IS 'owner comments on the track';
COMMENT ON COLUMN track.publicity   IS '0 track is private, (1 selected users, 2 selected group, 3 friends,) 4 registered users, 5 public';
COMMENT ON COLUMN track.comments_enabled IS 'can other users post comments';
COMMENT ON COLUMN track.trackpoints IS 'number of points in the track';
COMMENT ON COLUMN track.length      IS 'track length in meters';
COMMENT ON COLUMN track.mid_latitude IS 'mean latitude of the track';
COMMENT ON COLUMN track.mid_longitude IS 'mean longitude of the track';
COMMENT ON COLUMN track.time_start  IS 'timestamp of the first trackpoint; timezones?';

CREATE TABLE tag (
    id          INTEGER         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tag         VARCHAR(16)     NOT NULL UNIQUE
);

CREATE TABLE track_tag (
    track_id    INTEGER         NOT NULL,
    tag_id      INTEGER         NOT NULL,

    PRIMARY KEY (track_id, tag_id),
    FOREIGN KEY (track_id)      REFERENCES track (id),
    FOREIGN KEY (tag_id)        REFERENCES tag (id)
);

CREATE VIEW user_tag (
    tag,
    user_id
) AS
    SELECT DISTINCT (T.tag, K.user_id)
    FROM track K
    JOIN track_tag KT ON K.id=KT.track_id
    JOIN tag T ON T.id=KT.tag_id;

CREATE TABLE `comment` (
    id          INTEGER         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    track_id    INTEGER         NOT NULL,
    user_id     INTEGER         NOT NULL,
    text        VARCHAR(256)    NOT NULL,

    FOREIGN KEY (track_id)      REFERENCES track (id),
    FOREIGN KEY (user_id)       REFERENCES user (id)
);

CREATE TABLE trackpoint (
    id            BIGINT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tracksegment_id    INTEGER      NOT NULL,
    latitude     DOUBLE             NOT NULL DEFAULT(0),
    longitude    DOUBLE             NOT NULL DEFAULT(0),
    time         DATETIME,
    speed        DOUBLE,

    FOREIGN KEY (tracksegment_id) REFERENCES tracksegment (id)
);

CREATE TABLE tracksegment (
    id           INTEGER            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    max_speed    INTEGER            NOT NULL DEFAULT(0),
    max_elevation    INTEGER        NOT NULL DEFAULT(0),
    min_elevation    INTEGER        NOT NULL DEFAULT(0),
    length       INTEGER            NOT NULL DEFAULT(0)
);

