package org.davidcalabrese.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "PostTag")
@Table(name = "post_tag")
public class PostTag implements Serializable {
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
                ", post=" + post +
                ", tag=" + tag +
                '}';
    }
}