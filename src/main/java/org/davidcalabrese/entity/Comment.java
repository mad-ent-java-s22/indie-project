package org.davidcalabrese.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Represents a blog post in the application.
 *
 * @author David Calabrese
 */
@Entity(name = "Comment")
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "author")
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "comment_post"))
    private Post post;

    /** No arg constructor */
    public Comment() {}

    /**
     * Constructor taking everything but id
     *
     * @param content - the text that makes up the comment
     * @param dateCreated the datetime during which the comment was posted
     * @param author - the user who wrote the comment
     * @param post - the post on which the comment is made
     */
    public Comment(String content, LocalDate dateCreated, User author, Post post) {
        this.content = content;
        this.dateCreated = dateCreated;
        this.author = author;
        this.post = post;
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
     * Gets the value of <code>dateCreated</code>
     *
     * @return value of <code>dateCreated</code>
     */
    public LocalDate getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of <code>dateCreated</code>
     *
     * @param dateCreated the value of <code>dateCreated</code>
     */
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets the value of <code>author</code>
     *
     * @return value of <code>author</code>
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets the value of <code>author</code>
     *
     * @param author the value of <code>author</code>
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Gets the value of <code>post</code>
     *
     * @return value of <code>post</code>
     */
    public Post getPost() {
        return post;
    }

    /**
     * Sets the value of <code>post</code>
     *
     * @param post the value of <code>post</code>
     */
    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return id == comment.id && content.equals(comment.content) && dateCreated.equals(comment.dateCreated) && author.equals(comment.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, dateCreated, author);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", dateCreated=" + dateCreated +
                ", author='" + author + '\'' +
                '}';
    }
}
