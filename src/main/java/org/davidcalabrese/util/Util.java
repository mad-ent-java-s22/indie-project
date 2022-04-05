package org.davidcalabrese.util;

import org.davidcalabrese.entity.Tag;
import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Util {
    public static int getId(String pathInfo) {
        // grab everything after slash in url, should be the post, user or tag id
        return Integer.parseInt(pathInfo.substring(1));
    }

    public static User getUser(String userName) {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        List<User> users = userDao.findByPropertyEqual("userName", userName);
        return users.get(0);
    }

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

}
