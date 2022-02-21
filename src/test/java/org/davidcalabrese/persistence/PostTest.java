package org.davidcalabrese.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.testUtil.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PostTest {
    GenericDao postDao;
    GenericDao userDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        logger.info("Starting new post test");
        postDao = new GenericDao<Post>(Post.class);
        userDao = new GenericDao<User>(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    public void getAllPostsSuccess() {
        logger.info("in getAllPostsSuccess");
        List<Post> posts = postDao.getAll();
        assertEquals(5, posts.size());
    }

    @Test
    public void getPostByIdSuccess() {
        logger.info("in getPostByIdSuccess");
        Post testPost = (Post) postDao.getById(1);
        assertEquals("post1", testPost.getTitle());
        assertEquals("This is post 1", testPost.getContent());
    }

    @Test
    public void getPostsByUserIdSuccess() {
        logger.info("in getPostsByUserIdSuccess");

        List<Post> user2Posts = userDao.get
    }

}
