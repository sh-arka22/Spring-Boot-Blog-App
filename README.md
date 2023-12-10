[![Build Status](https://travis-ci.com/coma123/Spring-Boot-Blog-REST-API.svg?branch=development)](https://travis-ci.com/coma123/Spring-Boot-Blog-REST-API) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=coma123_Spring-Boot-Blog-REST-API&metric=alert_status)](https://sonarcloud.io/dashboard?id=coma123_Spring-Boot-Blog-REST-API) [![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/3706/badge)](https://bestpractices.coreinfrastructure.org/projects/3706)

# Spring Boot, MySQL, Spring Security, JWT, JPA, Rest API

Build Restful CRUD API for a blog using Spring Boot,JWT Autntication Role based, Mysql, JPA and Hibernate.

## NOTE: ``` In case the downloded code from github is not working I am also sharing the zipped folder in google-drive``` 
`` link : !()[https://drive.google.com/file/d/1085TgaBj0HZgEMKvjNa42wqUOJuXsdM0/view?usp=sharing]https://drive.google.com/file/d/1085TgaBj0HZgEMKvjNa42wqUOJuXsdM0/view?usp=sharing``
## Steps to Setup

**1. Clone the application**

```bash
git clone git@github.com:sh-arka22/Spring-Boot-Blog-App.git
```

**2. Create Mysql database**
```bash
create a database : myblog
```
- run `src/main/resources/myblog.sql`

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

## Explore Rest APIs

The app defines following CRUD APIs.

### Auth

| Method | Url | Decription | Sample Valid Request Body | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /api/auth/signup | Sign up | [JSON](#signup) |
| POST   | /api/auth/signin | Log in | [JSON](#signin) |

### Users

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/users/ | Get logged in user profile | |
| GET    | /api/users/{id}/profile | Get user profile by user-id | |
| GET    | /api/users/{user-id}/posts | Get posts created by user | |
| GET    | /api/users/{coment-id}/albums | Get albums created by user | |
| GET    | /api/users/checkEmailAvailability | Check if email is available to register | |
| PUT    | /api/users/{user-id} | Update user (If profile belongs to logged in user or logged in user is admin) | [JSON](#userupdate) |
| DELETE | /api/users/{user-id} | Delete user (For logged in user or admin) | |
| PUT    | /api/users/setOrUpdateInfo | Update user profile (If profile belongs to logged in user or logged in user is admin) | [JSON](#userinfoupdate) |

### Posts

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/posts | Get all posts | |
| GET    | /api/posts/{id} | Get post by id | |
| POST   | /api/posts | Create new post (By logged in user) | [JSON](#postcreate) |
| PUT    | /api/posts/{id} | Update post (If post belongs to logged in user or logged in user is admin) | [JSON](#postupdate) |
| DELETE | /api/posts/{id} | Delete post (If post belongs to logged in user or logged in user is admin) | |

### Comments

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/posts/{postId}/comments | Get all comments which belongs to post with id = postId | |
| GET    | /api/posts/{postId}/comments/{id} | Get comment by id if it belongs to post with id = postId | |
| POST   | /api/posts/{postId}/comments | Create new comment for post with id = postId (By logged in user) | [JSON](#commentcreate) |
| PUT    | /api/posts/{postId}/comments/{id} | Update comment by id if it belongs to post with id = postId (If comment belongs to logged in user or logged in user is admin) | [JSON](#commentupdate) |
| DELETE | /api/posts/{postId}/comments/{id} | Delete comment by id if it belongs to post with id = postId (If comment belongs to logged in user or logged in user is admin) | |
Test them using postman or any other rest client.

## Sample Valid JSON Request Bodys
##### 
##### <POST /api/auth/signin>Sign Up -> Admin Logs In
```json
{
    "usernameOrEmail" : "admin@gmail.com",
    "password" : "admin"
}
```
Response
```
{
    "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MDE2NzgzMDUsImV4cCI6MTcwMjI4MzEwNX0.XUO9pWdmPirtzBf19bnf1TcBK5d3DJtggqYMdAPpEuKeE0BZpCjtWm5bJPyTFl-H",
    "tokenType": "Bearer"
}
```
##### <PSOT /api/categories/>Admin Creates Post
```json
{
    "name" : "category-3",
    "description" : "this is another category-3"
}
```
Response
```
{
    "id": 4,
    "name": "category-3",
    "description": "this is another category-3"
}
```
##### </api/posts>Get All post
Response
```
{
    "content": [
        {
            "id": 1,
            "title": "post1",
            "description": "post1 description",
            "content": "post1",
            "comments": [
                {
                    "id": 5,
                    "name": "user5",
                    "email": "user5@gmail.com",
                    "body": "i am comment for post 2"
                },
                {
                    "id": 3,
                    "name": "user3",
                    "email": "user3@gmail.com",
                    "body": "i am coment for post 1"
                }
            ],
            "categoryId": 1
        },
        {
            "id": 2,
            "title": "post with category 2",
            "description": "only admin with access token can post",
            "content": "lets check if the admin with the token can post",
            "comments": [
                {
                    "id": 2,
                    "name": "user2",
                    "email": "user2@gmail.com",
                    "body": "i am comment for post 2"
                },
                {
                    "id": 4,
                    "name": "user4",
                    "email": "user4@gmail.com",
                    "body": "i am comment for post 2"
                }
            ],
            "categoryId": 2
        },
        {
            "id": 3,
            "title": "post2",
            "description": "post2 is the new description",
            "content": "post2",
            "comments": [
                {
                    "id": 1,
                    "name": "user",
                    "email": "user@gmail.com",
                    "body": "i am comment for Post1"
                }
            ],
            "categoryId": 2
        },
        {
            "id": 4,
            "title": "post3",
            "description": "post3 description",
            "content": "post3",
            "comments": [],
            "categoryId": 2
        },
        {
            "id": 5,
            "title": "post4",
            "description": "post4 description",
            "content": "post4",
            "comments": [],
            "categoryId": 2
        }
    ],
    "pageNo": 0,
    "pageSize": 10,
    "totalElements": 5,
    "totalPages": 1,
    "last": true
}
````
![segment](https://api.segment.io/v1/pixel/track?data=ewogICJ3cml0ZUtleSI6ICJwcDJuOTU4VU1NT21NR090MWJXS0JQd0tFNkcydW51OCIsCiAgInVzZXJJZCI6ICIxMjNibG9nYXBpMTIzIiwKICAiZXZlbnQiOiAiQmxvZ0FwaSB2aXNpdGVkIiwKICAicHJvcGVydGllcyI6IHsKICAgICJzdWJqZWN0IjogIkJsb2dBcGkgdmlzaXRlZCIsCiAgICAiZW1haWwiOiAiY29tcy5zcHVyc0BnbWFpbC5jb20iCiAgfQp9)

### Swagger View

<img width="944" alt="image" src="https://github.com/sh-arka22/Spring-Boot-Blog-App/assets/91637787/73b6a774-7778-4c13-85ea-d400f3d4d09d">

<img width="941" alt="image" src="https://github.com/sh-arka22/Spring-Boot-Blog-App/assets/91637787/616017c5-5e16-401c-af97-54055a8d04c9">


<img width="936" alt="image" src="https://github.com/sh-arka22/Spring-Boot-Blog-App/assets/91637787/27482529-d4cc-4fed-be74-d6929170b143">


<img width="941" alt="image" src="https://github.com/sh-arka22/Spring-Boot-Blog-App/assets/91637787/dfb3da09-ba58-4e04-a3a8-8e0313806447">


<img width="935" alt="image" src="https://github.com/sh-arka22/Spring-Boot-Blog-App/assets/91637787/03ebea47-3dda-4b13-97a9-88b9c45e43cd">




