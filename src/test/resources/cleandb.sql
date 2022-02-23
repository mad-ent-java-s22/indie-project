




-- tables
DROP TABLE if exists post_tag;
DROP TABLE if exists post;
DROP TABLE if exists tag;
DROP TABLE if exists user;





-- Table: post
CREATE TABLE post (
                      id int NOT NULL AUTO_INCREMENT,
                      title varchar(50) NOT NULL,
                      content varchar(255) NOT NULL,
                      date_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      user_id int NOT NULL,
                      CONSTRAINT post_pk PRIMARY KEY (id)
) COMMENT 'represents a blog post';

-- Table: post_tag
CREATE TABLE post_tag (
                          post_id int NOT NULL,
                          tag_id int NOT NULL,
                          CONSTRAINT post_tag_pk PRIMARY KEY (tag_id, post_id)
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
-- Reference: post_user (table: post)
ALTER TABLE post ADD CONSTRAINT post_user FOREIGN KEY post_user (user_id)
    REFERENCES user (id);

-- Reference: post_tag_post (table: post_tag)
ALTER TABLE post_tag ADD CONSTRAINT post_tag_post FOREIGN KEY post_tag_post (post_id)
    REFERENCES post (id);

-- Reference: post_tag_tag (table: post_tag)
ALTER TABLE post_tag ADD CONSTRAINT post_tag_tag FOREIGN KEY post_tag_tag (tag_id)
    REFERENCES tag (id);

-- inserts 4 test users
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `password`) VALUES ('user1', 'Pam', 'Smith', 'pam@gmail.com', 'password');
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `password`) VALUES ('user2', 'Dave', 'Johnson', 'dave@gmail.com', 'password');
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `password`) VALUES ('user3', 'Gina', 'Lucas', 'gina@gmail.com', 'password');
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `password`) VALUES ('user4', 'Tony', 'Davis', 'tony@gmail.com', 'password');

-- inserts 10 tags
INSERT INTO `blog`.`tag` (`name`) VALUES ('politics');
INSERT INTO `blog`.`tag` (`name`) VALUES ('education');
INSERT INTO `blog`.`tag` (`name`) VALUES ('movies');
INSERT INTO `blog`.`tag` (`name`) VALUES ('literature');
INSERT INTO `blog`.`tag` (`name`) VALUES ('entertainment');
INSERT INTO `blog`.`tag` (`name`) VALUES ('food');
INSERT INTO `blog`.`tag` (`name`) VALUES ('personal');
INSERT INTO `blog`.`tag` (`name`) VALUES ('technology');
INSERT INTO `blog`.`tag` (`name`) VALUES ('funny');
INSERT INTO `blog`.`tag` (`name`) VALUES ('sports');

-- inserts 5 test posts, each with a tag
INSERT INTO `blog`.`post` (`title`, `content`, `user_id`) VALUES ('post1', 'This is post 1, it is about politics and education', 1);
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (1, last_insert_id());
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (2, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`) VALUES ('post2', 'This is post 2, it is about education', 2);
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (2, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`) VALUES ('post3', 'This is post 3, it is about movies', 3);
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (3, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`) VALUES ('post4', 'This is post 4, it is about literature', 2);
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (4, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`) VALUES ('post5', 'This is post 5, it is about entertainment and sports', 4);
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (5, last_insert_id());
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (10, last_insert_id());


-- End of file.