-- foreign keys
ALTER TABLE post
    DROP FOREIGN KEY Post_User;

ALTER TABLE post_tag
    DROP FOREIGN KEY post_tag_post;

ALTER TABLE post_tag
    DROP FOREIGN KEY post_tag_tag;

-- tables
DROP TABLE if exists post;
DROP TABLE if exists post_tag;
DROP TABLE if exists tag;
DROP TABLE if exists user;

-- Table: post
CREATE TABLE post (
                      id int NOT NULL AUTO_INCREMENT,
                      title varchar(50) NOT NULL,
                      content varchar(255) NOT NULL,
                      date_created timestamp NOT NULL,
                      user_id int NOT NULL,
                      CONSTRAINT post_pk PRIMARY KEY (id)
) COMMENT 'represents a blog post';

-- Table: post_tag
CREATE TABLE post_tag (
                          tag_id int NOT NULL,
                          post_id int NOT NULL,
                          CONSTRAINT post_tag_pk PRIMARY KEY (tag_id,post_id)
);

-- Table: tag
CREATE TABLE tag (
                     id int NOT NULL AUTO_INCREMENT,
                     name varchar(50) NOT NULL,
                     CONSTRAINT tag_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE user (
                      id int NOT NULL AUTO_INCREMENT,
                      user_name varchar(50) NOT NULL,
                      first_name varchar(50) NOT NULL,
                      last_name varchar(75) NOT NULL,
                      date_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      email varchar(50) NOT NULL,
                      password varchar(255) NOT NULL,
                      access_privileges varchar(5) NOT NULL DEFAULT 'user',
                      CONSTRAINT user_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: Post_User (table: post)
ALTER TABLE post ADD CONSTRAINT post_user FOREIGN KEY post_user (user_id)
    REFERENCES user (id);

-- Reference: post_tag_post (table: post_tag)
ALTER TABLE post_tag ADD CONSTRAINT post_tag_post FOREIGN KEY post_tag_post (post_id)
    REFERENCES post (id);

-- Reference: post_tag_tag (table: post_tag)
ALTER TABLE post_tag ADD CONSTRAINT post_tag_tag FOREIGN KEY post_tag_tag (tag_id)
    REFERENCES tag (id);

-- End of file.