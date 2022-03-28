package org.davidcalabrese.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.testUtil.Database;
import org.davidcalabrese.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    GenericDao<User> userDao;
    GenericDao<Post> postDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        logger.info("setting up new user test");
        userDao = new GenericDao<>(User.class);
        postDao = new GenericDao<>(Post.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    public void getAllUsersSuccess() {
        logger.info("in getAllUsersSuccess");
        List<User> users = userDao.getAll();
        assertEquals(5, users.size());
    }

    @Test
    public void getUserByIdSuccess() {
        logger.info("in getUserByIdSuccess");
        User user1 = userDao.getById(1);
        assertEquals("Pam", user1.getFirstName());
    }

    @Test
    public void getUserFromPostSuccess() {
        Post post1 = postDao.getById(1);
        assertEquals("Pam", post1.getUser().getFirstName());
        assertEquals("Smith", post1.getUser().getLastName());
    }

    @Test
    public void getUserByUserNameSuccess() {
        List<User> users = userDao.findByPropertyEqual("userName", "user1");
        assertEquals("Pam", users.get(0).getFirstName());
        assertEquals(users.size(), 1);

        List<User> noUsers = userDao.findByPropertyEqual("userName", "user100");
        assertEquals(noUsers.size(), 0);
    }

    @Test void createUserSuccess() {
        User newUser = new User("user6", "user6@gmail.com");
        newUser.setFirstName("Meadow");
        newUser.setLastName("Soprano");
        newUser.setSummary("Meadow is a writer.");

        int id = userDao.insert(newUser);
        User insertedUser = userDao.getById(id);

        assertEquals("Meadow", insertedUser.getFirstName());
        assertEquals(insertedUser, newUser);
        List<User> users = userDao.getAll();
        assertEquals(6, users.size());
    }

    @Test void fetchUserByIdAndUpdateSuccess() {
        User user1 = userDao.getById(1);
        user1.setFirstName("Meadow");
        user1.setLastName("Soprano");
        user1.setSummary("Meadow is a writer.");

        userDao.saveOrUpdate(user1);

        assertEquals("Meadow", user1.getFirstName());
        assertEquals("Soprano", user1.getLastName());
        assertEquals("Meadow is a writer.", user1.getSummary());
    }

    @Test void fetchUserByUsernameAndUpdateSuccess() {
        User user1 = Util.getUser("user1");
        user1.setFirstName("Meadow");
        user1.setLastName("Soprano");
        user1.setSummary("Meadow is a writer.");

        userDao.saveOrUpdate(user1);

        assertEquals("Meadow", user1.getFirstName());
        assertEquals("Soprano", user1.getLastName());
        assertEquals("Meadow is a writer.", user1.getSummary());

    }
}