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
@Entity(name = "Post")
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "summary")
    private String summary;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "post_user"))
    private User user;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            fetch = FetchType.EAGER)
    @JoinTable(name = "post_tag",
        joinColumns = { @JoinColumn(name = "post_id") },
        inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<PostTag> postTags = new HashSet<>();

    /** No arg constructor */
    public Post() {}

    /**
     * Constructor that takes all fields except id
     *
     * @param title post title
     * @param content content of post
     * @param dateCreated datetime during which post was create
     * @param user user who created post (fk relationship)
     */
    public Post(String title, String content, LocalDate dateCreated, User user) {
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.user = user;
    }

    /**
     * Constructor taking only title and content
     *
     * @param title blog post title
     * @param content text inside blog post
     */
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
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
     * Gets the value of <code>summary</code>
     *
     * @return value of <code>summary</code>
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the value of <code>summary</code>
     *
     * @param summary the value of <code>summary</code>
     */
    public void setSummary(String summary) {
        this.summary = summary;
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

    /**
     * Gets the value of <code>postTags</code>
     *
     * @return value of <code>postTags</code>
     */
    public Set<PostTag> getPostTags() {
        return postTags;
    }

    /**
     * Sets the value of <code>postTags</code>
     *
     * @param postTags the value of <code>postTags</code>
     */
    public void setPostTags(Set<PostTag> postTags) {
        this.postTags = postTags;
    }

    /**
     * Adds a PostTag bridging entity to set of PostTags
     *
     * @param postTag - a bridging entity between Post and Tag
     */
    public void addPostTag(PostTag postTag) {
        postTags.add(postTag);
    }

    /**
     * Removes a PostTag bridging entity from a set of PostTags
     *
     * @param postTag - a bridging entity between Post and Tag
     */
    public void removePostTag(PostTag postTag) {
        postTags.remove(postTag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return id == post.id && title.equals(post.title) && content.equals(post.content) && dateCreated.equals(post.dateCreated) && user.equals(post.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, dateCreated, user);
    }
}
