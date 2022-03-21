package org.davidcalabrese.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.Tag;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.testUtil.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
// TODO: write tag tests
public class TagTest {
    GenericDao<Tag> tagDao;
    GenericDao<User> userDao;
    GenericDao<Post> postDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        logger.info("Starting new tag test");
        tagDao = new GenericDao<>(Tag.class);
        userDao = new GenericDao<>(User.class);
        postDao = new GenericDao<>(Post.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    public void insertTest() {
        Tag newTag = new Tag("basketball", "tomato");
        tagDao.insert(newTag);

        assertEquals(16, tagDao.getAll().size());
    }

    @Test
    public void getAllTagsSuccess() {
        logger.info("in getAllTagsSuccess");
        List<Tag> tags = tagDao.getAll();
        assertEquals(15, tagDao.getAll().size());
    }

    @Test
    public void getTagByIdSuccess() {
        logger.info("in getTagByIdSuccess");
        Tag tagOne = tagDao.getById(1);
        Set<Post> tagOnePosts = tagOne.getPosts();

        assertEquals("politics", tagOne.getName());
        assertEquals("red", tagOne.getColor());
        assertEquals(2, tagOnePosts.size());
    }

    @Test
    public void getTagsByPostIdSuccess() {
        logger.info("in getTagsByPostIdSuccess");
        Post postOne = postDao.getById(1);
        Set<Tag> postOneTags = postOne.getTags();

        assertEquals(2, postOneTags.size());
    }

    @Test
    public void updateTagSuccess() {
        logger.info("in updateTagSuccess");
        Tag tagOne = tagDao.getById(1);
        tagOne.setColor("purple-rain");
        tagDao.saveOrUpdate(tagOne);

        Tag tagOneEdited = tagDao.getById(1);
        assertEquals("purple-rain", tagOneEdited.getColor());
    }

//    @Test
//    public void deleteTagSuccess() {
//        logger.info("in deleteTagSuccess");
        /* TODO: can I delete a tag? I am getting a constraint violation
                since there is no post_tag entity I'm not sure how to
                remove the parent row before removing the tag

                remove from post */
//        Tag tagOne = tagDao.getById(1);
//        tagDao.delete(tagOne);
//
//        assertEquals(14, tagDao.getAll().size());
//    }

    @Test
    public void getAllMatchingTagsSuccess() {
        logger.info("in getAllMatchingTagsSuccess");
        Tag politicsTag = tagDao.getById(1);
        Set<Post> politicsPosts = politicsTag.getPosts();

        assertEquals(2, politicsPosts.size());
    }

    /*  TODO: test for inserting post with a politics tag
     *      assert total posts tagged "politics" == 3
     */
    @Test
    public void addingPostWithTagIncreasesTotalPostsWithThatTag() {
        // this is the user adding the post
        User user = userDao.getById(1);
        // This array represents the tags coming in from form
        String[] postTags = new String[]{"politics", "education"};
        // this is the Set of Tag objects associated with a post
        Set<Tag> tagSet = new HashSet<>();

        for (String tagName : postTags) {
            List<Tag> tagList = tagDao.findByPropertyEqual("name", tagName);
            Tag tag = tagList.get(0);
            tagSet.add(tag);
        }

        // create the new post object
        Post newPost = new Post("Post 100", "post content here", LocalDate.now(), user);
        // set the tags
        newPost.setTags(tagSet);
        newPost.setSummary("post summary here");
        // add post to db
        postDao.insert(newPost);

        // access politics and edu tags to get their posts
        Tag politicsTag = tagDao.getById(1);
        Tag educationTag = tagDao.getById(2);

        // test that added post increased number of posts tagged
        // edu and politics from 2 to 3
        assertEquals(3, politicsTag.getPosts().size());
        assertEquals(3, educationTag.getPosts().size());
    }


}
