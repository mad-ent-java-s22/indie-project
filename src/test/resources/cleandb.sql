-- tables
DROP TABLE if exists post_tag;
DROP TABLE if exists comment;
DROP TABLE if exists post;
DROP TABLE if exists tag;
DROP TABLE if exists user;

-- Table: post
CREATE TABLE post (
                      id int NOT NULL AUTO_INCREMENT,
                      title varchar(50) NOT NULL,
                      content text NOT NULL,
                      date_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      user_id int NOT NULL,
                      summary varchar(255) NOT NULL,
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
                     color varchar(50) NOT NULL,
                     CONSTRAINT tag_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE user (
                      id int NOT NULL AUTO_INCREMENT,
                      user_name varchar(50) NOT NULL,
                      first_name varchar(50) NOT NULL,
                      last_name varchar(75) NOT NULL,
                      date_created timestamp DEFAULT CURRENT_TIMESTAMP,
                      email varchar(255) NOT NULL,
                      access_privileges varchar(5) DEFAULT 'user',
                      profile_image varchar(255) DEFAULT 'default-profile-pic.jpg',
                      summary text NOT NULL,
                      CONSTRAINT user_pk PRIMARY KEY (id)
);

-- Table: comment
CREATE TABLE comment (
                         id int NOT NULL AUTO_INCREMENT,
                         content text NOT NULL,
                         date_created timestamp DEFAULT CURRENT_TIMESTAMP,
                         post_id int NOT NULL,
                         user_id int NOT NULL,
                         CONSTRAINT comment_pk PRIMARY KEY (id)
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

-- Reference: comment_post (table: comment)
ALTER TABLE comment ADD CONSTRAINT comment_post FOREIGN KEY comment_post (post_id)
    REFERENCES post (id);

-- Reference: comment_user (table: comment)
ALTER TABLE comment ADD CONSTRAINT comment_user FOREIGN KEY comment_user (user_id)
    REFERENCES user (id);

-- inserts 4 test users
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `profile_image`, `summary`) VALUES ('user1', 'Pam', 'Smith', 'pam@gmail.com', 'generated_photo_1.jpg', 'Pam Smith has been a staff reporter for Otter since August 2011. She currently covers breaking news and education. Before that, Ms. Smith was a beat reporter covering the boroughs of Brooklyn, Queens and Manhattan.');
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `profile_image`, `summary`) VALUES ('user2', 'David', 'Johnson', 'dave@gmail.com', 'generated_photo_2.jpg', 'David Johnson is the Los Angeles correspondent at Otter. He began his career covering the 2004 elections and later worked in the White House during the Obama Administration.');
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `profile_image`, `summary`) VALUES ('user3', 'Gina', 'Lucas', 'gina@gmail.com', 'generated_photo_3.jpg', 'Gina Lucas is a journalist at Otter. Prior to joining Otter, Lucas was part of the Assignment Desk at CNN International, working with reporters in areas from the Middle East, Asia, Africa, Europe, and Latin America. Lucas also edited and produced stories for CNN.com''s features division, before moving on to edit video and produce stories for Sports Illustrated''s website.');
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `profile_image`, `summary`) VALUES ('user4', 'Tony', 'Davis', 'tony@gmail.com', 'generated_photo_4.jpg', 'Tony David joined Otter in 2013 as a sports reporter. He primarily covers the N.B.A. but has also made forays into the worlds of track and field, soccer and Olympic curling. He previously worked for The Wall Street Journal and The Commercial Appeal in Memphis, Tenn.  He is a graduate of Middlebury College and Columbia University. Raised in Vermont, he now resides in Los Angeles.');
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `profile_image`, `summary`) VALUES ('user5', 'Megan', 'Qualley', 'megan@gmail.com', 'qualley.jpg', 'Maggie has been a reporter at Otter since 2013. She covers a broad range of issues, from the latest developments out of the Middle East to science research news. She started her journalism career at the beginning of the Egyptian uprising in 2011 and chronicled the ousting of two presidents, eight rounds of elections, and numerous major outbreaks of violence for Otter and other outlets.');

-- inserts 10 tags
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('politics', 'red');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('education', 'pink');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('movies', 'purple');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('books', 'indigo');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('entertainment', 'blue');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('food', 'cyan');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('personal', 'teal');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('technology', 'green');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('business', 'lime');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('sports', 'yellow');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('opinion', 'amber');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('science', 'orange');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('health', 'brown');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('travel', 'grey');
INSERT INTO `blog`.`tag` (`name`, `color`) VALUES ('art', 'blue-grey');

-- inserts 5 test posts, each with a tag or two
INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('Post 1', 'This is post 1, it is about politics and education', 1, 'This is post 1, it is about politics and education');
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (1, last_insert_id());
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (2, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('Substitutes in Camouflage',
                                                                             '<p>ESTANCIA, N.M. — The chorus of small voices ringing from a third-grade classroom on a recent morning signaled how far Estancia Elementary School had come in resuming a sense of normalcy after the latest coronavirus surge.</p>
                                                                             <p>Students in the small, remote community of Estancia, N.M., were enthusiastically engaged in a vocabulary lesson, enunciating words with a “bossy r,” as well as homophones and homonyms, and spelling them on white boards.</p>
                                                                             <p>But there was also a sign of how far the district, about an hour outside Albuquerque, still had to go. The teacher moving about the classroom and calling on students to use the words in a sentence was clad in camouflage. “My substitute is wearing gear,” one student responded.</p>
                                                                             <p>“Yes,” Lt. Col. Susana Corona replied, beaming. “The superintendent allows me to wear my uniform. I’m wearing a pair of boots.”</p>
                                                                             <p>For the last month, dozens of soldiers and airmen and women in the New Mexico National Guard have been deployed to classrooms throughout the state to help with crippling pandemic-related staff shortages. Gov. Michelle Lujan Grisham has also enlisted civilian state employees — herself included — to volunteer as substitute teachers.</p>
                                                                             <p>New Mexico has been the only state to deploy National Guard troops in classrooms. But since the fall, when districts around the country began recruiting any qualified adult to take over classrooms temporarily, several other states have turned to uniformed personnel. National Guard members in Massachusetts have driven school buses, and last month, police officers in one city in Oklahoma served as substitutes</p>',
                                                                             2, 'Deployed to classrooms in New Mexico, National Guard troops employ their informal motto, “Semper Gumby” — Always Flexible.');
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (2, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('Post 3', 'This is post 3, it is about movies', 3, 'This is post 3, it is about movies');
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (3, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('Post 4', 'This is post 4, it is about literature', 5, 'This is post 4, it is about literature');
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (4, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('Post 5', 'This is post 5, it is about entertainment and sports', 4, 'This is post 5, it is about entertainment and sports');
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (5, last_insert_id());
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (10, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('Post 6', 'This is post 6, it is about politics', 5, 'This is post 6, it is about politics');
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (1, last_insert_id());

INSERT INTO `blog`.`comment` (`content`, `post_id`, `user_id`) VALUES ("comment 1", 1, 1);
INSERT INTO `blog`.`comment` (`content`, `post_id`, `user_id`) VALUES ("comment 2", 1, 2);
INSERT INTO `blog`.`comment` (`content`, `post_id`, `user_id`) VALUES ("comment 3", 1, 3);
INSERT INTO `blog`.`comment` (`content`, `post_id`, `user_id`) VALUES ("comment 4", 2, 2);
INSERT INTO `blog`.`comment` (`content`, `post_id`, `user_id`) VALUES ("comment 5", 2, 2);

-- End of file.