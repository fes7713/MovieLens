# MovieLens


https://user-images.githubusercontent.com/71058334/169448585-c82fe177-3a10-4099-a6f8-b5b43663a589.mp4


# Requirement
- JDK 17
- Netbeans IDE 12.5


# External library 
- mysql connecter java 8.0.1.1
- jsoup 1.1.4.3

# Introduction
This application provides information about movies and help users find new movies. 
Through interactive UI, users can find their favorite movies and application can suggest new movies depending on user's interest.
This app can show some information about movies such as genre, rating, tag, trailer video link, external movie database links, release year and duration. 

# Available functionalities.

| Keyword | Description 
---- | ----
| List view of movies | Show bunch of movies to users with card style. Card contains basic infomration of movie, title, 
release date and duration. Circle icon on bottom right of card show rating of movie. 
| Movie Thumnails | Thumbnail of movie is set to the background of cards so that user can easily identify movies and interact with it.
| Detailed view of movies | User can go to detailed view from card view by clicking card in list view. Detailed view shows more information about the movie to user.
| Related movies | In detailed view, user can find related movies by going down to related movie section in the bottom of deyailed view.
| Genre search | User can interfact with genre icons attached to each movie in detailed view. By clicking genre button of specific genre, user can search movies with the same genre information attached.
| Tag search | Just like genre search, user can search movies by tag created by other users. Color from green to red shows the popularity of tag. Green tag indicates that many users puts the same tag on the movie.
| Keyword search | By typing keyword in search box located at the top of list view and middle of detailed view, users can search moviews by name.

> List view with genre search action
<img width="1261" alt="image" src="https://user-images.githubusercontent.com/71058334/168511505-da666867-9999-4d2a-9393-a8a9928d41ae.png" width=600px>

> Detailed view with genre search action
<img width="1258" alt="image" src="https://user-images.githubusercontent.com/71058334/168512571-d461e36f-943c-4bac-9c78-301a931ba3ec.png" width=600px>

> Related movies in detailed view
<img width="1259" alt="image" src="https://user-images.githubusercontent.com/71058334/168512690-a6ff7503-58b7-496a-a0dd-2e1f78d3b9b0.png" width=600px>

# Launch
In order to start application, you need to have Java 17 installed on your pc. Compiling is needed to test this project. 
Since I used Apatch Netbeans to develop this application, it is easier to use Netbeans to compile and run this project. 
1. Download project file from this repository
2. Import project
3. Compile and run MovieLens.java under movelens package in src folder.

# UI design 
Designing the app is the biggest part that I spent my time. Many students often tend to focus on programming part of project but I like to spend on UI and UX designing as much as core programming of application and I think that this is very important thing when it comes to making application. The reason why I say is not only the fact that final product looks good and appearing but also the fact that application is meant to be used by wide range of peole. <br>
In my application, I developed user friendly icons and simple designs.
## Key design ideas
1. Icon
<img width="736" alt="image" src="https://user-images.githubusercontent.com/71058334/168516146-da7a6f59-70c3-4069-98f9-afaa160e422f.png" width=450px>

2. Brief animation for clickable icons

![ezgif com-gif-maker (8)](https://user-images.githubusercontent.com/71058334/168517259-1d1ac6c4-1faf-4200-bd6c-6999cfd6fe19.gif)

3. Loading animation


![ezgif com-gif-maker (9)](https://user-images.githubusercontent.com/71058334/168518073-5629ce61-7654-4b15-8dc5-bdb149aa6ab4.gif)

I could use letters to tell user to wait for loading or give instruction that icon is interactable but it is not smart way. In term of giving instructions, icons are extremely useful because it does not require any written information and does not force user to focus on those instruction to understand.

Since I like Physics a lot, you may notice that icon animations has acceleration in it.

## Key programming ideaa
### Multithreading
In the last semester, I learned how to use thread and make multi-threaded application. I found it very interesting and useful so I used it for first time in my big project.

I used threads mainly for IO operations.
1. Loading movie information from DB.
2. Webscraping URLs from website.
3. Loading image from internet.

With help of threads, I could load image much faster and update UI independent to IO (Loading animation) -> Run animation in one thread and load image in another thread. Also, multiple images can be loaded at the same time, it was extremely useful and satisfying to explore movies without waiting.


![ezgif com-gif-maker (10)](https://user-images.githubusercontent.com/71058334/168519641-e4ba61f4-32f9-4256-ba50-0bc2b8356337.gif)


# Serch functions
There are 3 types of search functions in this app
1. Genre search
2. Tag search
3. Keyword search

<img width="1247" alt="image" src="https://user-images.githubusercontent.com/71058334/168520371-214f6de0-374c-4779-80be-1860fa4628a6.png" width=600px>

Genre and tag search can be initiated by clicking genre icon or tag icon in detailed view. They are interactive icons and directs user to search results when clicked.

<img width="836" alt="image" src="https://user-images.githubusercontent.com/71058334/168520567-d6f5d1b2-c52e-4433-8cf2-613b3eace05f.png" width=600px>

Keyword search can be started from search window

<img width="299" alt="image" src="https://user-images.githubusercontent.com/71058334/168520645-ca4ca95e-cdee-4feb-a78c-1943a1fc7cf6.png" width=300px>

This keyword search is premitive function that it only takes title in account and ignores genre and tag information. I might be able to improve this function in the future.

Also, for searchig algorithm, I simply used MySQL to find matching and order the result set so I did not spend my time developing efficient seaching algorithm.

# Rating circle
This is my favorite icon in this project. It animates when mouses hovers on it and changes color depending on the rating. Green for great rating and yellow for okay, and red for bad rating.

<img width="741" alt="image" src="https://user-images.githubusercontent.com/71058334/168521578-d203501c-1564-42b3-999e-1fe421f8fcf1.png" width=600px>

# Database
I used AWS RDS as my database storage for this project. I had an experience with AWS before so I did not have any problem with DB in this project. However, I could try integrating SQL in this apprication like using SQLite for example. 

Here is the database structure for this project. 

<img width="489" alt="image" src="https://user-images.githubusercontent.com/71058334/168521993-32824135-29dc-41ea-96f7-fc23f8de543f.png" width=600px>

# Future improvement
- Since it was my first multithreaded apprication project, there are some parts that I think I could use thread to improve speed. For some database operation, I used main thread to load data but I think I can fully offload IO related operations to worker threads in the future. 

- I like OOP programming and I used a lot of classes and interfaces to manage movie information. In the last semester I learned more about OOP and this time I tried to use more interface than before. It was very good practice but I feel that this time, my code is little messy and especially naming of classes are horrible I feel. I tried to group each class files by introducing packages to the project files and I would liek to improve file management more because i feel that I waisted so much time looking for a specific file.

- Also, in this project, I spent too much time on UI designing so I hope I can improve designing steps in the future.

- I did not have mich time so I could not do it this time but I could try more functionaloties using DB. For example, I first planned to make fovarite list and play list. I would be very easily just by creating new relational table in DB. Also, adding new rating from user or tag to movie would have been great function too.

- I made desktop application this time but I want to try smart phone application in the future since it would come in more handy in that way.


# Impression
I really enjoyed this project although I spend whole 10 days in this project. Especially, trying multithreading in my application was amazing and i would definitly use more in my game programming in the future. Making UI always takes me a lot of time but it was definitly enjoyablbe. I think that this project was perfect dificult for me because it had both component of designing and core programming and topic was my favorite (GUI and database) and I learned so much stuff from this project.




