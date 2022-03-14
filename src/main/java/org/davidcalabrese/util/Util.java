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

    public static String getCorrespondingTagColor(String tagName) {
        String color;
        switch(tagName) {
            case "politics": color = "red";       break;
            case "education": color = "pink";     break;
            case "movies": color = "purple";      break;
            case "books": color = "indigo";       break;
            case "entertainment": color = "blue"; break;
            case "food": color = "cyan";          break;
            case "personal": color = "teal";      break;
            case "technology": color = "green";   break;
            case "business": color = "lime";      break;
            case "sorts": color = "yellow";       break;
            case "opinion": color = "amber";      break;
            case "science": color = "orange";     break;
            case "health": color = "brown";       break;
            case "travel": color = "grey";        break;
            default: color = "blue-grey";
        }
        return color;
    }
}
