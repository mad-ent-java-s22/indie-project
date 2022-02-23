package org.davidcalabrese.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.testUtil.Database;
import org.junit.jupiter.api.BeforeEach;

public class UserTest {
    GenericDao userDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        logger.info("setting up new user test");
        userDao = new GenericDao<User>(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }
}
