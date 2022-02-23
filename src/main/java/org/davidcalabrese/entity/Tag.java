package org.davidcalabrese.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Tag")
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NaturalId
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private Set<Post> posts = new HashSet<>();

    /**
     * No arg constructor
     */
    public Tag() {
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
     * Gets the value of <code>name</code>
     *
     * @return value of <code>name</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of <code>name</code>
     *
     * @param name the value of <code>name</code>
     */
    public void setName(String name) {
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
        return name == tag.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}