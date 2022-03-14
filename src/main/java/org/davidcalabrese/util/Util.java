package org.davidcalabrese.util;

import org.davidcalabrese.entity.User;
import org.davidcalabrese.persistence.GenericDao;

import java.util.List;

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
}
