### Week 4
Spent a lot of this week getting caught up. I finally have my first checkpoint work done. 

I have found it very hard to sincerely answer questions such as "what kind of app do you want to build?", or "what kind of problem are you trying to solve?". I have lots of problems in my life that need solving but none can really be neatly solved with a web application. I finally landed on a blog engine, which has been done many times before and by programmers much more skilled than me. However, I have been reading about ethical design, especially when it comes to social media apps, and it gave me the idea of designing an interface that isn't meant to be addictive. A lot of the design choices that make social media addictive are "enhancements" in the sense that they had to be created to get people to engage more with the product. When success is measured by how many hours a user is on your app, engineers are going to think of designs that cause users to spend more time on the app. Infinite scrolling is one of these "enhancements" that gets people to stay on longer, but it doesn't actually solve a problem of the user or make them happier. So not including things like infinite scrolling is one way to make a blog engine less addictive, but it's not a technical solution to the problem. Rather, the solution is to *not* include the technical enhancements. But this isn't a very interesting solution. I'd like to try to find a technical way to fight against the addictive application problem. 

[This article](https://mindfultechnics.com/reform-social-media-part-v-ethical-design-in-social-media/) introduced me to Richard Thaler and Cass Sunstein, who research ethical design. They coined the phrase "choice architecture" as a way to describe design choices in things like social media applications. They give the example of a buffet table with vegetables and deserts. 

> Typically, when a buffet table has vegetables at the start of its line, more people will eat more vegetables. Conversely, when a buffet table has desserts at the start of its line, more people will eat more desserts. In sum, the design of how a product or service presents choices to consumers will strongly influence what decisions they make.

This interested me because they framed the issue as a technical challenge. So creating a non-addictive social media app shouldn't just be a matter of *not* including the addictive technology but also a matter of designing it so that users can make good decisions about how much time they're spending and what kind of content they are exposed to. 

### Week 5
Things are coming together! I have redesigned my data model and thinking over this has given me clarity on how the filter functionality of the blog will work. I have a home page jsp created as well as a page listing all blog posts. It looks pretty sharp! I'm using a bootstrap theme so I can't take any credit for the design though. 

I've implemented the genericDao class successfully for my post entities and all of my CRUD tests are passing except for the insert test. I'm getting the following error during the save operation:

```
org.hibernate.exception.SQLGrammarException: error performing isolated work 
... 
Caused by: java.sql.SQLSyntaxErrorException: Table 'blog.hibernate_sequence' doesn't exist
```

I have no idea what this error is about, I can't even imagine what the SQL statement is hibernate is trying to write. I know if I weren't using Hibernate I would be using some MySQL functions to perform the insert in the post_tag table. I would need to get the id of the post and id of the tag in order to insert. I'm not sure how hibernate does this or what I can do to help it along.

I found an interesting solution to a problem I was having formatting the dates of blog posts in the jsp. The jstl/fmt tag library can be used to format Date objects in a jsp but it doesn't work for LocalDate objects. I found [this stack overflow page](https://stackoverflow.com/questions/30230517/taglib-to-display-java-time-localdate-formatted) that gave me the solution of creating a custom tag library in the WEB-INF directory. I just copied and pasted it. Now I can format LocalDate objects in the jsp as follows:

> <tags:localDate date="${yourDateToPrint}"/>

### Week 6
Got some helpful advice from Carson and Quinn on how to solve some problems I was running into. 
#### "Twosday" 2/22
* Adjusted the data model to make the many-to-many relationship work. I had found some stuff online that mapped a many-to-many relationship with @ManyToMany hibernate annotations. However, I couldn't get this to work. I had overlooked the week 5 video on many to many, so I went and watched it. Then I decided to implement the relationship as two one-to-many relationships and actually creating an entity for the bridging/joining table (post_tag). Originally my joining table only had two columns, one for the post_id (fk to post.id) and one for the tag_id (fk to tag.id). In the video Paula says that its possible to implement the relationship with a table like this but then I would need to make another entity that represented the composite key or something like that. I felt it would be easier to follow her example and add a surrogate pk to the post_tag table. The new ERD can be found in DesignDocuments directory in database_model.PNG file.

* I'm getting this error when I try to run tests with the new data model:

```
org.hibernate.exception.DataException: Could not read entity state from ResultSet : EntityKey[org.davidcalabrese.entity.Tag#1]
...
Caused by: java.sql.SQLDataException: Cannot determine value type from string 'politics'
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:114)
```

* Looks like maybe this is because I the tag name type in the db is string, but the java entity is of type `Set<PostTag> tags`. I did this because I want to be able to have each post have multiple tags. And each individual tag is a string in the database. Not sure what to do now.  

#### Wednesday 2/23
* Asked a bunch of questions in class. Was able to figure out solution to all the issues I was having. Data model works well now. I am going with the @ManyToMany annotation rather than the @OneToMany. Not sure what the difference is anyway, I still have the bridging entity in org.davidcalabrese.entity

* Following Paula's advice I am looping through and displaying every tag associated with a post. I initially was just trying to display a single tag and then work on displaying multiples but might as well start doing the right thing from the start. I got the tags to display, messed with the css so that they display side by side rather than in rows. Also used JSTL to color the tag name dynamically, so "politics" is always blue, "sports" is always green, etc. 

#### Thursday 2/24
* Got some AI-generated faces to serve as profile pictures for the blog post authors. Right now I am storing the name of the img file as a column in the blog.user table and storing the picture itself in and img directory under src/main/webapp. This is working but ideally I will have it so the db stores a url hosted elsewhere or, even better, using a AWS s3 to store and serve the images. Paula reminded me that, if using s3, I can also take advantage of features such as resizing/optimizing the image and also filter out inappropriate pictures. 

* Started building a jsp template that will display articles. 
* Made several small tweaks to data model:
  * "content" column in blog.post is now type "TEXT" rather than varchar(255) so I am not limited to very short articles. I believe I will end up storing the articles as HTML so they display nicely, unless I find a better way to do that. 
  * added "summary" column in blog.post. the post summary will contain a short summary of the article to be displayed on the main page of the application. I was originally thinking of just taking a substring of the first 150 or characters of the article and using that as a summary, but this frequently wouldn't look good so I decided against it.
  * added "summary" column in blog.user - this holds a short background on the user, to be displayed to the side of the user's posts. Ideally I will also create pages for each author as well, and in that case I will want the user summary displayed there as well. 
* working on creating dynamic url patterns for the posts so one servlet can route the any number of posts. I found [this article](https://stackoverflow.com/questions/6678029/dynamic-urls-in-java-web-application-like-in-rails) to help.

### Week 7
* Blog is looking good! I have made it so a user can view all posts, view posts by author or view posts by tag. 
* Made some changes to data model - added color column in blog.tag to simplify the styling.
* Cognito exercise completed, I have not integrated that into the app yet.
  * I first plan to deploy week 7 exercise to beanstalk and then try with my app


### Week 8
I thought I had fixed my issue with the Cannot resolve symbol 'Base64' problem but the fix is no good. The code compiles fine, but it leads to an error later on when the user tries logging in.

When Paula tried logging in she got this error:
java.lang.IllegalArgumentException: Illegal base64 character 5f

So theBase64.getDecoder().decode() method is not a good substitute for Base64.decodeBase64(). According to [this SO post](https://stackoverflow.com/questions/7688644/java-lang-nosuchmethoderror-org-apache-commons-codec-binary-base64-encodebase64),  someone fixed the same issue by adding the commons-codec.jar dependency.

This worked for me. I still have no idea why the Base64 method was working fine in the week-7 exercise. The commons-codec dependency is not included in the pom.xml there. Maybe I have a conflicting dependency in my indie-project classpath that got precedence in classloading.
* cognito integrated into app
* removed password column from user table

### Week 9
* productive week
* figured out the tag filtering thing many-to-many relationship
  * I was adding a new tag for every new article
  * what I wanted to do was add a row in the post_tag table and reference the already made tag in the tag column
  * I think the way the app will work is there are a preset number of tags to choose from, those will be the only items in the tag table. It would be nice to have users be able to add tags of their own but not sure if I'll have time to implement that
* I got the tinyMCE API working, now users can format their own posts
* I was using a single jsp for the profile page and using JSTL conditionals to display profile info or the profile form depending on whether user was viewing the profile or editing it.
* I changed this so that there is a jsp for viewing the profile and a jsp for editing the profile. 
* ran into this error when trying to save the profile edits
 > Illegal attempt to associate a collection with two open sessions. Collection : [org.davidcalabrese.entity.User.posts#6]
 
* the way the edit profile logic goes is this:
  * get userName from session, use that to pull User object from db
  * receive form parameters and make those changes to user object
  * call `saveOrUpdate` on the User object 
  * set the new user attribute on the session object

```java
  userDao.saveOrUpdate(user);

  // update user in session
  req.getSession().setAttribute("user", user);
```

* this last line must be the problem
* I did some research on updating a session variable 
* hibernate has a merge method that might work here
* otherwise, maybe I can just change the object and it doesn't need to be updated
  * [resource](https://coderanch.com/t/354487/java/updating-object-session)

### Week 10
* successfully implemented logic to edit profile
* successfully implemented logic to delete a post
* I made the mistake of making the post.title column type varchar(50), when I should have done varchar(255), I tried doing an alter table statement on the db and it broke my project. The command wasn't able to complete, it lost connection to the server somehow. I read that whenever an alter table statement is executed the db has to copy all of the current info to remake it and that can take a lot of memory so that might be part of the problem. 
* So now my app works for retrieving blog posts, but I can't edit profiles, create new posts, edit posts, anything like that. 

### Week 11

### Week 12

### Week 13

### Week 14

### Week 15

### Week 16