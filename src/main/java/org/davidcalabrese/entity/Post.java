package org.davidcalabrese.entity;

import java.time.LocalDate;

public class Post {
    private int id;
    private String title;
    private String content;
    private LocalDate timeCreated;
    private int userId;

    public Post() {}

    public Post(int id, String title, String content, LocalDate timeCreated, int userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeCreated = timeCreated;
        this.userId = userId;
    }
}
