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

public class UserTest {
    GenericDao userDao;
    GenericDao postDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        logger.info("setting up new user test");
        userDao = new GenericDao<User>(User.class);
        postDao = new GenericDao<Post>(Post.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    public void getAllUsersSuccess() {
        logger.info("in getAllUsersSuccess");
        List<User> users = userDao.getAll();
        assertEquals(4, users.size());
    }

    @Test
    public void getUserByIdSuccess() {
        logger.info("in getUserByIdSuccess");
        User user1 = (User) userDao.getById(1);
        assertEquals("Pam", user1.getFirstName());
    }

    @Test
    public void getUserFromPostSuccess() {
        Post post1 = (Post) postDao.getById(1);
        assertEquals("Pam", post1.getUser().getFirstName());
    }
}