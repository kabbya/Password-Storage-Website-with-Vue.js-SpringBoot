
# Password Storage

An website where users can store Passwords of their different websites.

#Backend Technology

- Java 11
- Spring Boot 2.6.3 with 
    - Spring Security
    - Spring Web
    - Spring Data JPA
    - Spring Validation
- jjwt 0.9.1
- MySQL
- Maven 3.8.1

# Frontend Technology 

- vue 3
- vue-router 4
- vuex 4
- axios: 0.21.1
- vee-validate 4
- bootstrap 5
- vue-fontawesome 3

# Feactures

- User login, logout, Registraion
- Password Create, Search, Update, Delete
- Role based Authentication

# Frontend Setup 

- npm install vue-router@4
- npm install vuex@4
- npm install vee-validate@4 yup
- npm install axios
- npm install bootstrap --save
- npm install @fortawesome/fontawesome-svg-core @fortawesome/free-solid-svg-icons @fortawesome/vue-fontawesome@prerelease

# APIs

These are APIs that we need to provide. Testing with Postman:

Methods	   |   Urls           |	  Actions        | Body |
:--------: | :-------------:  |  :-------------: | :-------------:
POST	   | /api/auth/signup |  signup new account|{"username":"tester02","email":"tester02@gmail.com","password":"tester02"}
POST	   | /api/auth/signin |  login an account  | {"username":"tester02","password":"tester02"} |
GET	       | /api/test/all	  |  retrieve public content|
GET        | /api/test/passwords/user/{user_id} | retrive all passwords of an user|  |
GET        | /api/test/passwords/user/{user_id} | search password of an user| {"siteName":"abc"} |
POST |  /api/test/passwords/{user_id} | create new password | {"siteName":"github", "siteUserName":"tester02@gmail.com", "sitePassword":"12345"} |
GET | /api/test/passwords/password_id} | get password id |  |
PUT | /api/test/passwords/{user_id}/{password_id}  | update password id | {"siteName": "abc update","sitePassword": "update Password","siteUserName": "update@gmail.com"}|
DELETE |  /api/test/passwords/{passwor_id} | delete password | |
