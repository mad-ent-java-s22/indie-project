# indie-project

Repository for Enterprise Java project.

### Problem statement
Need a non-addictive, non-manipulative application to share one's thoughts with community. 

### Solution
Taciturn is the micro-blogging solution many don't know they need. 
The application will be designed so a user can get caught up on the latest news in a minimal amount of time without being ensnared in an unhealthy cycle of social media use.  
* no data monitoring 
* default settings is for private data
* no addictive or "neutral" design 
* [ethical design](https://mindfultechnics.com/reform-social-media-part-v-ethical-design-in-social-media/) 
* not possible to doomscroll
* no hateful content allowed 
* very difficult to harass people
* builds rather than erodes social trust
* not built to hijack your attention
* does not track users or sell their data
* does not amplify harmful or misleading info

### User stories
* as a social media user, I do not want my data to be collected so that I do not have a creepy feeling of being watched every time I log in 
* as an app user, I do not want to be treated as a resource by the app creator
* I do not want to be fed controversial or misleading content
* I do not want to be distracted from meaningful issues
* I do not want to be a part of a system that energizes hatred and bigotry

### Links
[Project Plan](/ProjectPlan.md)
[Journal](/Journal.md)
[Time Log](/TimeLog.md)
[Weekly Reflections](/WeeklyReflection.md)

### Project Technologies/Techniques
* Security/Authentication
    * AWS's Cognito
* Database
    * MySQL 8
* ORM Framework
    * Hibernate 5
* Dependency Management
    * Maven
* Web Services consumed using Java
    * Potential APIs to use in a blog 
      * [HTTP Cat](https://http.cat/?ref=apilist.fun) provides a cat image for every http status code. This could be used with custom http error pages
      * [Google Calendar](https://developers.google.com/calendar) Could be used to add to calendar events that are posted in blog
* CSS
    * Bootstrap
* Data Validation
    * Hibernate Validator
* Logging
    * Log4J2
* Hosting
    * AWS
* Independent Research Topic/s
    * AWS
    * Ethical Design 
* Unit Testing
    * JUnit tests to achieve 80%+ code coverage
* IDE: IntelliJ IDEA
