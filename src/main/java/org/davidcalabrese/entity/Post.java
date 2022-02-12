package org.davidcalabrese.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents a blog post in the application.
 *
 * @author David Calabrese
 */
@Entity(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    // TODO
    //  figure out how to convert this from timestamp
    //  check out @CreationTimeStamp & @Convert tags
    @Column(name = "time_created")
    private LocalDate timeCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "post_user"))
    private User user;

    /** No arg constructor */
    public Post() {}

    /**
     * Constructor that takes all fields except id
     *
     * @param title post title
     * @param content content of post
     * @param timeCreated datetime during which post was create
     * @param user user who created post (fk relationship)
     */
    public Post(String title, String content, LocalDate timeCreated, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeCreated = timeCreated;
        this.user = user;
    }

    /**
     * Gets the value of <code>id</code>
     *
     * @return value of <code>id</code>
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of <code>id</code>
     *
     * @param id the value of <code>id</code>
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the value of <code>title</code>
     *
     * @return value of <code>title</code>
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of <code>title</code>
     *
     * @param title the value of <code>title</code>
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the value of <code>content</code>
     *
     * @return value of <code>content</code>
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of <code>content</code>
     *
     * @param content the value of <code>content</code>
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the value of <code>timeCreated</code>
     *
     * @return value of <code>timeCreated</code>
     */
    public LocalDate getTimeCreated() {
        return timeCreated;
    }

    /**
     * Sets the value of <code>timeCreated</code>
     *
     * @param timeCreated the value of <code>timeCreated</code>
     */
    public void setTimeCreated(LocalDate timeCreated) {
        this.timeCreated = timeCreated;
    }

    /**
     * Gets the value of <code>user</code>
     *
     * @return value of <code>user</code>
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of <code>user</code>
     *
     * @param user the value of <code>user</code>
     */
    public void setUser(User user) {
        this.user = user;
    }
}
