package org.davidcalabrese.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.Tag;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.testUtil.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
// TODO: write tag tests
public class TagTest {
    GenericDao<Tag> tagDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        logger.info("Starting new tag test");
        tagDao = new GenericDao<>(Tag.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    public void insertTest() {
        Tag newTag = new Tag("basketball", "tomato");
        tagDao.insert(newTag);

        assertEquals(15, tagDao.getAll().size());
    }


}
