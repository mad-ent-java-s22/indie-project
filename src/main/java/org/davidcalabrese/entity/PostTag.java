package org.davidcalabrese.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "PostTag")
@Table(name = "post_tag")
public class PostTag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private Tag tag;

    /** Instantiates a new PostTag */
    public PostTag() {}

    /** Instantiates a new PostTag
     *
     * @param post the blog post
     * @param tag the tag
     */
    public PostTag(Post post, Tag tag) {
        this.post = post;
        this.tag = tag;
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

    /**
     * Gets the value of <code>tag</code>
     *
     * @return value of <code>tag</code>
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * Sets the value of <code>tag</code>
     *
     * @param tag the value of <code>tag</code>
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "PostTag{" +
                "id=" + id +
                ", post=" + post +
                ", tag=" + tag +
                '}';
    }
}
