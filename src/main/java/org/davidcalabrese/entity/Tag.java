package org.davidcalabrese.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private int name;

    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();

    /** No arg constructor */
    public Tag() {}

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
     * Gets the value of <code>name</code>
     *
     * @return value of <code>name</code>
     */
    public int getName() {
        return name;
    }

    /**
     * Sets the value of <code>name</code>
     *
     * @param name the value of <code>name</code>
     */
    public void setName(int name) {
        this.name = name;
    }

    /**
     * Gets the value of <code>posts</code>
     *
     * @return value of <code>posts</code>
     */
    public Set<Post> getPosts() {
        return posts;
    }

    /**
     * Sets the value of <code>posts</code>
     *
     * @param posts the value of <code>posts</code>
     */
    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return id == tag.id && name == tag.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name=" + name +
                ", posts=" + posts +
                '}';
    }
}