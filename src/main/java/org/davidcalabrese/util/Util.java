package org.davidcalabrese.util;

import org.davidcalabrese.entity.Tag;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Contains utility functions used in app
 */
public class Util {
    /**
     * Returns the id in url
     *
     * @param pathInfo the url path info containing a user or post id
     * @return just the id part of path
     */
    public static int getId(String pathInfo) {
        // grab everything after slash in url, should be the post, user or tag id
        return Integer.parseInt(pathInfo.substring(1));
    }

    /**
     * Returns a user object when passed a userName string
     *
     * @param userName a user's username
     * @return the User object associated with that username
     */
    public static User getUser(String userName) {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        List<User> users = userDao.findByPropertyEqual("userName", userName);
        return users.get(0);
    }

    /**
     * Returns a set of tags when passed an array of tags
     *
     * @param tagArray array of tags retrieved from form select tag
     * @return a set of tags which will become the tags property of a post object
     */
    public static Set<Tag> makeTagSet(String[] tagArray) {
        GenericDao<Tag> tagDao = new GenericDao<>(Tag.class);
        Set<Tag> tagSet = new HashSet<>();

        // for each tag selected, create tag object and add to tagSet
        for (String tagName : tagArray) {
            List<Tag> tagList = tagDao.findByPropertyEqual("name", tagName);
            Tag tag = tagList.get(0);
            tagSet.add(tag);
        }
        return tagSet;
    }

    /**
     * Returns a list of tags when passed an array of tags
     *
     * @param tagArray array of tags retrieved from form select tag
     * @return a list of tags which will form the prompt sent to openAI API
     */
    public static List<Tag> makeTagList(String[] tagArray) {
        GenericDao<Tag> tagDao = new GenericDao<>(Tag.class);
        List<Tag> tagList = new ArrayList<>();

        // for each tag selected, create tag object and add to tagSet
        for (String tagName : tagArray) {
            List<Tag> tags = tagDao.findByPropertyEqual("name", tagName);
            Tag tag = tags.get(0);
            tagList.add(tag);
        }

        Tag aiGeneratedTag = tagDao.getById(22);
        tagList.add(aiGeneratedTag);
        return tagList;
    }
}
