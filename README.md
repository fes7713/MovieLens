# MovieLens

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





