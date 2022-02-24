package org.davidcalabrese.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.testUtil.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest {
    GenericDao<Post> postDao;
    GenericDao<User> userDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        logger.info("Starting new post test");
        postDao = new GenericDao<>(Post.class);
        userDao = new GenericDao<>(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    public void insertTest() {
        User user1 = userDao.getById(1);
        Post testPost = new Post("Test title", "post content here");
        testPost.setUser(user1);

        assertEquals(6, postDao.getAll().size());
    }

    @Test
    public void getAllPostsSuccess() {
        logger.info("in getAllPostsSuccess");
        List<Post> posts = postDao.getAll();
        assertEquals(6, posts.size());
    }

    @Test
    public void getPostByIdSuccess() {
        logger.info("in getPostByIdSuccess");
        Post testPost = postDao.getById(1);
        assertEquals("Post 1", testPost.getTitle());
        assertEquals("This is post 1, it is about politics and education", testPost.getContent());
        assertEquals("politics", testPost.getTags().iterator().next().getName());
    }

    @Test
    public void getPostsByUserIdSuccess() {
        logger.info("in getPostsByUserIdSuccess");
        User user2 = userDao.getById(2);

        List<Post> user2Posts = postDao.getByUser(user2);
        assertEquals(2, user2Posts.size());
    }

    @Test
    public void updatePostSuccess() {
        Post testPost = postDao.getById(1);
        testPost.setTitle("new title");
        postDao.saveOrUpdate(testPost);

        Post editedPost = postDao.getById(1);
        assertEquals("new title", editedPost.getTitle());
    }

    @Test
    public void deletePostSuccess() {
        logger.info("in deletePostSuccess");
        Post testPost = postDao.getById(1);
        postDao.delete(testPost);

        assertEquals(5, postDao.getAll().size());
    }
}
