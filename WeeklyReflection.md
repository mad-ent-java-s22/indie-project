### Week 4
Spent a lot of this week getting caught up. I finally have my first checkpoint work done. 

I have found it very hard to sincerely answer questions such as "what kind of app do you want to build?", or "what kind of problem are you trying to solve?". I have lots of problems in my life that need solving but none can really be neatly solved with a web application. I finally landed on a blog engine, which has been done many times before and by programmers much more skilled than me. However, I have been reading about ethical design, especially when it comes to social media apps, and it gave me the idea of designing an interface that isn't meant to be addictive. A lot of the design choices that make social media addictive are "enhancements" in the sense that they had to be created to get people to engage more with the product. When success is measured by how many hours a user is on your app, engineers are going to think of designs that cause users to spend more time on the app. Infinite scrolling is one of these "enhancements" that gets people to stay on longer, but it doesn't actually solve a problem of the user or make them happier. So not including things like infinite scrolling is one way to make a blog engine less addictive, but it's not a technical solution to the problem. Rather, the solution is to *not* include the technical enhancements. But this isn't a very interesting solution. I'd like to try to find a technical way to fight against the addictive application problem. 

[This article](https://mindfultechnics.com/reform-social-media-part-v-ethical-design-in-social-media/) introduced me to Richard Thaler and Cass Sunstein, who research ethical design. They coined the phrase "choice architecture" as a way to describe design choices in things like social media applications. They give the example of a buffet table with vegetables and deserts. 

> Typically, when a buffet table has vegetables at the start of its line, more people will eat more vegetables. Conversely, when a buffet table has desserts at the start of its line, more people will eat more desserts. In sum, the design of how a product or service presents choices to consumers will strongly influence what decisions they make.

This interested me because they framed the issue as a technical challenge. So creating a non-addictive social media app shouldn't just be a matter of *not* including the addictive technology but also a matter of designing it so that users can make good decisions about how much time they're spending and what kind of content they are exposed to. 

### Week 5
Things are coming together! I have redesigned my data model and thinking over this has given me clarity on how the filter functionality of the blog will work. I have a home page jsp created as well as a page listing all blog posts. It looks pretty sharp! I'm using a bootstrap theme so I can't take any credit for the design though. 

I've implemented the genericDao class successfully for my post entities and all of my CRUD tests are passing except for the insert test. I'm getting the following error during the save operation:

org.hibernate.exception.SQLGrammarException: error performing isolated work 
... Caused by: java.sql.SQLSyntaxErrorException: Table 'blog.hibernate_sequence' doesn't exist

I have no idea what this error is about, I can't even imagine what the SQL statement is hibernate is trying to write. I know if I weren't using Hibernate I would be using some MySQL functions to perform the insert in the post_tag table. I would need to get the id of the post and id of the tag in order to insert. I'm not sure how hibernate does this or what I can do to help it along.

I found an interesting solution to a problem I was having formatting the dates of blog posts in the jsp. The jstl/fmt tag library can be used to format Date objects in a jsp but it doesn't work for LocalDate objects. I found [this stack overflow page](https://stackoverflow.com/questions/30230517/taglib-to-display-java-time-localdate-formatted) that gave me the solution of creating a custom tag library in the WEB-INF directory. I just copied and pasted it. Now I can format LocalDate objects in the jsp as follows:

> <tags:localDate date="${yourDateToPrint}"/>

### Week 6
Got some helpful advice from Carson and Quinn on how to solve some problems I was running into. 