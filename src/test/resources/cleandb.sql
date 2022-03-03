-- tables
DROP TABLE if exists post_tag;
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
                      profile_image varchar(255) NOT NULL DEFAULT 'https://www.personality-insights.com/wp-content/uploads/2017/12/default-profile-pic-e1513291410505.jpg',
                      summary text NOT NULL,
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
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `password`, `profile_image`, `summary`) VALUES ('user1', 'Pam', 'Smith', 'pam@gmail.com', 'password', 'generated_photo_1.jpg', 'Pam Smith has been a staff reporter for Otter since August 2011. She currently covers breaking news and education. Before that, Ms. Smith was a beat reporter covering the boroughs of Brooklyn, Queens and Manhattan.');
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `password`, `profile_image`, `summary`) VALUES ('user2', 'David', 'Johnson', 'dave@gmail.com', 'password', 'generated_photo_2.jpg', 'David Johnson is the Los Angeles correspondent at Otter. He began his career covering the 2004 elections and later worked in the White House during the Obama Administration.');
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `password`, `profile_image`, `summary`) VALUES ('user3', 'Gina', 'Lucas', 'gina@gmail.com', 'password', 'generated_photo_3.jpg', 'Gina Lucas is a journalist at Otter. Prior to joining Otter, Lucas was part of the Assignment Desk at CNN International, working with reporters in areas from the Middle East, Asia, Africa, Europe, and Latin America. Lucas also edited and produced stories for CNN.com''s features division, before moving on to edit video and produce stories for Sports Illustrated''s website.');
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `password`, `profile_image`, `summary`) VALUES ('user4', 'Tony', 'Davis', 'tony@gmail.com', 'password', 'generated_photo_4.jpg', 'Tony David joined Otter in 2013 as a sports reporter. He primarily covers the N.B.A. but has also made forays into the worlds of track and field, soccer and Olympic curling. He previously worked for The Wall Street Journal and The Commercial Appeal in Memphis, Tenn.  He is a graduate of Middlebury College and Columbia University. Raised in Vermont, he now resides in Los Angeles.');
INSERT INTO `blog`.`user` (`user_name`, `first_name`, `last_name`, `email`, `password`, `profile_image`, `summary`) VALUES ('user5', 'Megan', 'Qualley', 'megan@gmail.com', 'password', 'qualley.jpg', 'Maggie has been a reporter at Otter since 2013. She covers a broad range of issues, from the latest developments out of the Middle East to science research news. She started her journalism career at the beginning of the Egyptian uprising in 2011 and chronicled the ousting of two presidents, eight rounds of elections, and numerous major outbreaks of violence for Otter and other outlets.');

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

-- inserts 5 test posts, each with a tag or two
INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('Masks in school',
                                                                             '<p>SANDS POINT, N.Y. — Just before classes began on Wednesday morning, Jordan Goldberg, a fifth grader at Guggenheim Elementary School on Long Island, strode through the doors and stopped short.</p>
<p>“This doesn’t feel normal!” he said, clutching his bare, unmasked chin.</p>
<p>For the first time since schools reopened during the pandemic, Jordan and many other public school students across the state entered homerooms, gymnasiums and class without masks. Citing low virus caseloads and a desire to return to a sense of normalcy, Gov. Kathy Hochul lifted the state’s school mask mandate starting Wednesday, leaving mask policy to local officials.</p>
<p>But with low vaccination rates among minors and the threat of the coronavirus still present, if lessened, what once had been anticipated as a milestone moment was instead met with a mixed response.</p>
<p>From students to teachers to parents, the rush of fresh-faced jubilation on Wednesday was tempered by concerns: On one side was a belief that the order was long overdue; on the other was a fear that the decision was dangerously premature.</p>
<p>“It feels like Covid is kind of over, even though it is not,” said Jordan, 11, after first period began and the initial rush of masklessness wore off. “It feels like everyone just kind of gave up on it.”</p>
<p>With the statewide mandate lifted, local officials are now left to decide mask policy in their schools. In Nassau County, the Port Washington Union Free School District, which includes Guggenheim, leapt at the earliest chance to shed masks. But New York City’s one million public school students will have to wait until at least Monday, when Mayor Eric Adams has said he expects to lift the mask requirement.</p>
<p>As faces in the schoolyard at Guggenheim began to look as they did in the Before Times of the 2019 school year, the new protocols were met with emotions that ranged from elation to trepidation.</p>
<p>“I think it feels great!” said Mario Alfonso-Paz, 9, his mask in his pocket and dimples visible as he beamed between posters of Dr. Seuss characters in a Guggenheim hallway. “It’s like it’s finally over!”</p>
<p>But his mother, Silvia Alfonso, who put on a mask to enter the school lobby, did not share her son’s joy.</p>
<p>Ms. Alfonso said she had hypertension and her husband had type 1 diabetes, preconditions that made them vulnerable to serious complications from the coronavirus. She fears her unmasked son could bring the virus home.</p>
<p>Throughout the pandemic, she said, they have told their children that “they have to put them on — for us.” She is allowing her son to go maskless but said: “I think it still wasn’t a good idea, because people are being infected.”</p>'
                                                                             , 1, 'Disagreements at school on the first day for some school districts can go maskless');
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

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('‘Butter’ Review: High-Stakes Popularity',
'<p>Why do movies that take on bullying so often fetishize the very acts they seem to be critiquing? “Butter,” directed by Paul A. Kaufman — who adapted Erin Jade Lange’s young adult novel — seems to wallow in the brutality.</p>
<p>Fed up with his classmates’ contempt, a high school junior, Butter (Alex Kersting), decides he’ll show them by eating himself to death on New Year’s Eve. Just tune in to buttersfinalmeal.com, he announces online. But far from shaming or freaking out his peers, Butter’s promise makes him a celebrity at his school.</p>
<p>Once the movie gets going, Kersting, a newcomer, gives an all-in performance. Butter is a gifted saxophone player, a thoughtful soul. He’s gaining ground in his incognito courting of the school’s popular girl (McKaley Miller). Onetime foes are becoming friends. Is there any wonder he’s approaching his big night with less and less verve?</p>
<p>The adults in Butter’s life are less compelling. Mira Sorvino plays Butter’s flummoxed mom. His dad (Brian Van Holt) is even more confounded and distant. And Butter’s physician (Ravi Patel) is a tad too madcap. The only adults who seem to really see him are his band teacher (Mykelti Williamson) and a hospital psychiatrist (Annabeth Gish).</p>
<p>The movie is a good-hearted dramatic comedy about the bedeviling issues of bullying, and the hazards of social media. But the lessons become stand-ins for richer characters who could have been memorable — and persuasive. For all its ache and churning emotions, “Butter” winds up being little more than a meager “Afterschool Special.”</p>', 3, 'Fed up with his classmates’ contempt, an obese student decides he’ll gorge himself to death.');
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (3, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('Twin Brothers on a Quest',
'<p>A line of imperfect telepathic communication runs between twin brothers. They can speak to each other without opening their mouths. At night they eavesdrop on each other’s dreams. Experiences pass between the two “like books in a library.” When one twin drinks, the other gets a hangover.</p>
<p>The twins are Mun and Chuluun, 23 years old in 2015, when Quan Barry’s mesmerizing and delicate new novel, her third, takes place. Chuluun studies at a Buddhist monastery tucked in the shadow of a volcano in Mongolia. Mun wears Western-style clothes and lives in the capital of Ulaanbaatar, where he indulges in technology, tattoos, curse words and cigarettes. One of the men is placid and the other mutinous. Their eerie mental overlap is a source of mutual resentment. Each twin wants the other out of his head.</p>
<p>They are brought together when tasked with the mission of roaming the country to find a tulku, the reincarnation of a great spiritual leader — “a being destined to help carry on the ancient truths in a world that seeks to destroy the ancient truths.”  There are three candidates for the brothers to visit: one in sub-Siberian hill country, one in a mountainous far western province and one in the southern reaches of the country. Two boys and a girl, all young children.</p>
<p>In this sense, the novel takes the familiar form of a quest. Along the journey there is an accident, a self-sacrifice, disasters, death. There are natural wonders and metaphysical conundrums. There is yak butter.</p>
<p>Mun, it turns out, is himself a reincarnation of a historical figure. At 8 years old, he is recognized as the fifth incarnation of the Paljor Jamgon, the “Redeemer Who Sounds the Conch in the Darkness.” It’s a long name for a little kid. Mun is “discovered” on the remote grasslands in much the way that a future pop star might be discovered on YouTube. He is then ordained at a monastery — somewhat against his will — and assumes a raft of responsibilities. Privileges are heaped upon him, too: tutors, a private cook, gifts, separate living quarters. A golden cushion cradles his blessed butt.</p>
<p>Chuluun, who has also been swept off to the monastery, adapts easily to the institution’s routines and restraints. He enjoys chanting and meditation. Mun does not — he’d rather play games on horseback than demonstrate the unending compassion required of his position, and his recalcitrance causes whispers to circulate. Some monks question whether Mun deserves his material distinctions. One asks a colleague whether a mistake has been made — perhaps, he suggests, the leadership has recognized the wrong brother as a reincarnation.</p>
<p>Eventually — under circumstances that are initially cloudy — Mun renounces his robes and finds his way to the city, leaving Chuluun behind to practice calligraphy and ruminate on his sense of abandonment. When the two are forced to reunite, a chasm of spite yawns between them. The conflict alternately fuels and impedes their voyage in a rickety car across Mongolia’s steppes and dunes.</p>',
5, 'Quan Barry’s dazzling new novel, “When I’m Gone, Look for Me in the East,” is centered on themes of faith, history, language and yearning.');
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (4, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('Wilt Chamberlain’s 100-Point Game ',
'<p>OAKLAND, Calif. — On Sunday afternoon, Al Attles eased onto his living room couch next to his friend Tom Meschery, and they soon found themselves transported to March 2, 1962.</p>
<p>They were listening to an old radio broadcast, Meschery for the first time. For a few blissful minutes, Attles’s home in Oakland was filled with the smoky baritone of Bill Campbell, who was the play-by-play voice for the N.B.A.’s Philadelphia Warriors when Attles, 85, and Meschery, 83, were teammates.</p>
<p>“I remember thinking in the third quarter that something special was happening,” Meschery said.</p>
<p>That something special was Wilt Chamberlain, Philadelphia’s dominant center, scoring 100 points in a 169-147 win over the Knicks in Hershey, Pa., where Attles reveled in his role of shuffling the ball to Chamberlain as often as possible and Meschery helped make history amid a haze produced by the nearby candy factories.</p>
<p>“I can’t tell you how much the aroma of chocolate disturbed me for years,” he said.</p>
<p>Bill Campbell, the play-by-play announcer for the Philadelphia Warriors, described Chamberlain’s record-setting moment in 1962.</p>
<blockquote>On the game’s 60th anniversary, it lives on as a part of the country’s cultural fabric — a touchstone for a transcendent athlete when the N.B.A., by design, was predominantly white. Gary M. Pomerantz, whose book, “Wilt, 1962: The Night of 100 Points and the Dawn of a New Era,” offers the definitive account of the game, said Chamberlain’s performance marked an important shift.</blockquote>
<p>“We remember Wilt’s 100-point game in part for its symbolism,” Pomerantz said in a telephone interview. “It symbolically exploded the racial quota N.B.A. owners had that limited opportunities for Black players. If this wasn’t the intended effect, it was the ultimate result: The N.B.A. would be a white man’s enclave no more.”</p>
<p>Attles, who was one of Philadelphia’s three Black players at the time, spent his entire 11-year career with the Warriors as a player and player-coach, then stayed with the franchise as its head coach, guiding the team, which had by then moved to California, to its first championship in 1975. He was enshrined in the Basketball Hall of Fame in 2019.</p>
',
                                                                             4, 'For the game’s 60th anniversary, two of Chamberlain’s teammates relived the night that was a touchstone for a transcendent athlete and the white-by-design N.B.A.');
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (5, last_insert_id());
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (10, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('Takeaways From the Texas Primaries',
'<p>For nearly a decade, the refrain from Texas Democrats has been that they are on the verge of making their state competitive, even though no Democrat has won a statewide race since 1994.</p>
<p>Tuesday’s primary results illustrated that Democrats still have a long way to go.</p>
<p>With more than three-quarters of the votes counted, nearly 800,000 more Republicans than Democrats voted for a candidate for governor — a gap far larger than the one in 2018, the last midterm primary election in Texas.</p>
<p>To be sure, Republicans had a more competitive primary than Democrats. Gov. Greg Abbott’s contest against Republican challengers from his right may have been more of a draw than Beto O’Rourke’s glide path to the Democratic nomination. And Democrats will be quick to note that primary turnout is not always a predictor of big turnout in November.</p>
<p>Still, Republicans demonstrated they are energized — even when divided between far-right and mainstream factions — and hardly ceding their hold on the state.</p>
<p><b>Abbott’s right turn paid off.</b></p>
<p>Before this year, Mr. Abbott had never faced a competitive Republican primary in his 25-year political career. But in a moment of conservative energy, with Republicans furious about the 2020 election and President Biden’s immigration policies, a field of Republicans bet that Mr. Abbott would be vulnerable to a challenger from his right.</p>
<p>Turns out they were wrong.</p>
<p>Armed with a $60 million war chest, Mr. Abbott easily dispatched seven Republicans, taking more than two-thirds of the vote. It was a win that was a year in the making. Mr. Abbott has spent much of last year placating the state’s conservative base by passing new restrictions on abortion, easing gun laws and enacting new limits on how Texas schools teach about the history of racism. Days before the primary, Mr. Abbott directed state health agencies to classify medical treatments commonly provided to transgender adolescents as “child abuse.”</p>
<p>Mr. Abbott’s record was a striking demonstration of how a primary threat can help the right wing of the Republican Party drive the agenda, even in a state that has been trending toward Democrats.</p>',
                                                                             5, 'Republicans’ turnout swamped Democrats’, while progressives claimed wins in the first elections of the 2022 midterms.');
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (1, last_insert_id());

INSERT INTO `blog`.`post` (`title`, `content`, `user_id`, `summary`) VALUES ('Ethical Design in Social Media',
'<p>The concept of ethical design holds especial relevance for technologies like social media. That’s because many social media platforms aren’t necessarily designed to help users make good decisions. Rather, many platforms have features designed to incentivize addictive behavior. Common examples of such addictive design features include default settings for private data, ‘like’ buttons, and infinite scrolling. Eliminating these addictive designs could be an effective way to begin applying ethical design in social media.</p>
<h2>Choice Architecture: From Buffet Tables to Ethical Design in Social Med/ia<h2>
<p>One way to think about technology ethics (for instance, ethical design in social media, which we’ll get to shortly) is by thinking about “choice architecture.” The term comes from the book Nudge: Improving Decisions about Health, Wealth, and Happiness, by behavioral economist Richard Thaler and legal scholar Cass Sunstein.</p>
<p>Simply put, choice architecture refers to how choices (say, decisions about what product or service to choose) are presented to consumers. Choice architecture matters, because presenting the same consumer choices to someone in different ways can strongly influence what decisions that person makes. As a result, different types of choice architectures can sway people to make different kinds of decisions.</p>
<p>If that sounds a bit abstract, let’s take a specific example: a buffet table with vegetables and desserts. Typically, when a buffet table has vegetables at the start of its line, more people will eat more vegetables. Conversely, when a buffet table has desserts at the start of its line, more people will eat more desserts. In sum, the design of how a product or service presents choices to consumers will strongly influence what decisions they make.</p>
<p>Thaler and Sunstein refer to these sorts of design-based influences on our behavior and decision making as “nudges.”</p>
<h2>There is No ‘Neutral’ Design</h2>
<p>If different types of choice architectures can “nudge” people to make different kinds of decisions, then it’s often impossible to design products or services that don’t make some decisions more likely than others. After all, designers of a product or service have to make design choices, such as what it will look like and how it should work. And those design choices can thus influence how people decide to use that product or service. Hence, Thaler and Sunstein conclude:</p>
<blockquote>there is no such thing as a “neutral” design (Thaler and Sunstein, 2008, p 3).</blockquote>
<p>Suffice to say, this fact—that there is no ‘neutral’ design—rings true for digital technology too, including smart devices and social networking sites.</p>
<p>Whether it’s default settings on smartphones (most folks tend to accept the defaults, as opposed to manually resetting them all) or advertising incentives on social media (many individuals are much more susceptible to online ads than they think), these design choices will inevitably affect what people say, think, and do with those technologies.</p>
<h2>From ‘Neutral’ Design to Ethical Design</h2>
<p>So, if there’s no such thing as a ‘neutral’ design—that is, a design that doesn’t make certain choices more likely than others—then designers of technology might want to consider whether or not their designs help people make good decisions. As Thaler and Sunstein argue, designs ought to nudge people toward decisions that help them improve their lives. For instance, does the technology design help people make decisions that improve their health, wealth, or well-being?</p>
<p>This intersection of professional ethics and technology design is often known as ethical design. Granted, ethical design may apply to any kind of technology, although the concept holds especial relevance for social media today. That’s because many social media platforms, such as Facebook or Twitter, don’t seem well-designed to help users make good decisions. At times, it almost feels like they do the opposite. To quote media scholar Siva Vaidhyanathan from his book Anti-Social Media: How Facebook Disconnects Us and Undermines Democracy:</p>
<p>If you wanted to build a machine that would distribute propaganda to millions of people, distract them from important issues, energize hatred and bigotry, erode social trust, undermine journalism, foster doubts about science, and engage in massive surveillance all at once, you would make something a lot like Facebook (Vaidhyanathan, 2018, p 19).</p>
<h2>Ethical Design vs. Addictive Design</h2>
<p>To illustrate the concept of ethical design, let’s contrast it with a near opposite approach: addictive design.</p>
<p>Addictive design refers to technology designs that induce behavioral addictions. Many people are familiar with the notion of substance addiction—for example, feeling addicted to drugs. Similarly, behavioral addiction refers to feeling addicted to certain behaviors, such as gambling.</p>
<p>Precisely defined, behavioral addiction refers to an association between an unfulfilled psychological need and an action that assuages that need in the short term—but nevertheless leads to harmful behavior in the long term. In the book Irresistible: The Rise of Addictive Technology and The Business of Keeping Us Hooked, psychologist Adam Alter defines behavioral addiction thus:</p>
<p>A behavior is addictive only if the rewards it brings now are eventually outweighed by damaging consequences. … Addiction is a deep attachment to an experience that is harmful and difficult to do without. Behavioral addictions don’t involve eating, drinking, injecting, or smoking substances. They arise when a person can’t resist a behavior, which, despite addressing a deep psychological need in the short-term, produces significant harm in the long-term (Alter, 2017, p 20).</p>
<p>For a while, addiction was commonly thought to apply only to substance abuse. However, as Alter argues, recent research has shown that addictive behaviors produce the same brain responses as addictive substances. (For instance, there’s a rush of dopamine and a corresponding ‘high’ feeling, which provides temporary relief from psychological pain.)  Which brings us to the underlying psychology of addictive design.</p>
<h2>The Psychology of Addictive Design</h2>
<p>The key point to bear in mind is that addiction, whether substance-based or behavior-based, is not just a physical response. It’s also a psychological response. As Alter writes,</p>
<p>The substance or behavior itself isn’t addictive until we learn to use it as a salve for our psychological troubles. If you’re anxious or depressed, for example, you might learn that heroin, food, or gambling lessen your pain. If you’re lonely, you might turn to an immersive video game that encourages you to build new social networks (p 73).</p>
<p>Now, the question is, how do we help people overcome behavioral addiction? Of course, it’s possible to put the full burden of overcoming addiction on the user. For instance, if someone feels addicted to cigarettes, we could say, “Just stop smoking!” Likewise, if someone feels addicted to social media, we could say, “Just stop using social media!”</p>
<p>Then again, the point of acknowledging such behavior as addictive is to realize it’s out of someone’s conscious control. (Or, at the very least, it’s out of someone’s conscious control to a significant degree—otherwise, it wouldn’t be addictive.)</p>
<p>In other words, if a product, service, or technology is addictive by design, it’s unreasonable to put the onus of overcoming addition solely on the addict. Surely, the professionals responsible for designing that addictive product, service, or technology also bear some responsibility.</p>',
                                                                             3, '‘Neutral’ Design, Addictive Design, and Ethical Design in Social Media');
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (5, last_insert_id());
INSERT INTO `blog`.`post_tag` (`tag_id`, `post_id`) VALUES (8, last_insert_id());

-- End of file.