package org.davidcalabrese.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a blog post in the application.
 *
 * @author David Calabrese
 */
@Entity
@Table(name = "post")
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
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "post_user"))
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private Set<PostTag> tags = new HashSet<>();

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
     * Gets the value of <code>tags</code>
     *
     * @return value of <code>tags</code>
     */
    public Set<PostTag> getTags() {
        return tags;
    }

    /**
     * Sets the value of <code>tags</code>
     *
     * @param tags the value of <code>tags</code>
     */
    public void setTags(HashSet<PostTag> tags) {
        this.tags = tags;
    }
}
