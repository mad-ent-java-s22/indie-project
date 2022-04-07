package org.davidcalabrese.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.davidcalabrese.entity.Comment;
import org.davidcalabrese.entity.Post;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.testUtil.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentTest {
    GenericDao<User> userDao;
    GenericDao<Post> postDao;
    GenericDao<Comment> commentDao;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        logger.info("Starting new comment test");
        userDao = new GenericDao<>(User.class);
        postDao = new GenericDao<>(Post.class);
        commentDao = new GenericDao<>(Comment.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    public void insertTest() {
        User commentAuthor = userDao.getById(1);
        Post commentedPost = postDao.getById(1);
        Comment newComment = new Comment("Cool post!", LocalDate.now(), commentAuthor, commentedPost);
        commentDao.insert(newComment);

        assertEquals(6, commentDao.getAll().size());

    }

    @Test
    public void getAllCommentsSuccess() {
        logger.info("in getAllCommentsSuccess");
        List<Comment> comments = commentDao.getAll();
        assertEquals(5, comments.size());
    }

    @Test
    public void getCommentByIdSuccess() {
        Comment comment1 = commentDao.getById(1);
        User user1 = userDao.getById(1);
        assertEquals("comment 1", comment1.getContent());
        assertEquals(user1, comment1.getAuthor());
        assertEquals(1, comment1.getId());
    }

    @Test
    public void getAllPostsCommentsSuccess() {
        List<Comment> post1Comments = commentDao.findByPropertyEqual("post", 1);
        assertEquals(3, post1Comments.size());
    }

    @Test
    public void getAllUsersCommentsSuccess() {
        List<Comment> user2Comments = commentDao.findByPropertyEqual("author", 2);
        assertEquals(3, user2Comments.size());
    }
}
