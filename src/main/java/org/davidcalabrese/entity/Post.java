package org.davidcalabrese.entity;

import java.time.LocalDate;

/**
 * Represents a blog post in the application.
 *
 * @author David Calabrese
 */
public class Post {
    private int id;
    private String title;
    private String content;
    private LocalDate timeCreated;
    private int userId;

    /** No arg constructor */
    public Post() {}

    /**
     * Constructor that takes all fields except id
     *
     * @param title post title
     * @param content content of post
     * @param timeCreated datetime during which post was create
     * @param userId id of user (corresponds to User.id)
     */
    public Post(int id, String title, String content, LocalDate timeCreated, int userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeCreated = timeCreated;
        this.userId = userId;
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
     * Gets the value of <code>userId</code>
     *
     * @return value of <code>userId</code>
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the value of <code>userId</code>
     *
     * @param userId the value of <code>userId</code>
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
